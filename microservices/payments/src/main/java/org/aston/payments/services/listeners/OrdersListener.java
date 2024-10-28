package org.aston.payments.services.listeners;

import lombok.RequiredArgsConstructor;
import org.aston.payments.dto.messages.OrdersToPayments;
import org.aston.payments.services.PaymentsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrdersListener implements Listener<OrdersToPayments> {
    private final PaymentsService paymentsService;

    @KafkaListener(
            topics = "${app.kafka.orders-payments-topic}",
            groupId = "${app.kafka.from-orders-id}",
            containerFactory = "listenerFactoryOrdersToPayments"
    )
    @Override
    public void listen(OrdersToPayments message, UUID key, String topic, Integer partition, Long timestamp) {
        paymentsService.ordersRequest(message);
    }
}
