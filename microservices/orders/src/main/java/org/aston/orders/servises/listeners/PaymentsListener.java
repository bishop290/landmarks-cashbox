package org.aston.orders.servises.listeners;

import lombok.RequiredArgsConstructor;
import org.aston.orders.dto.messages.PaymentsToOrders;
import org.aston.orders.servises.OrdersService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentsListener implements Listener<PaymentsToOrders> {
    private final OrdersService orderService;

    @KafkaListener(
            topics = "${app.kafka.payments-orders-topic}",
            groupId = "${app.kafka.from-payments-id}",
            containerFactory = "listenerFactoryPaymentsToOrders"
    )
    @Override
    public void listen(PaymentsToOrders message, UUID key, String topic, Integer partition, Long timestamp) {
        orderService.paymentsResponse(message);
    }
}
