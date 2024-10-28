package org.aston.orders.servises.listeners;

import lombok.RequiredArgsConstructor;
import org.aston.orders.dto.messages.TicketsToOrders;
import org.aston.orders.servises.OrdersService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TicketsListener implements Listener<TicketsToOrders> {
    private final OrdersService orderService;

    @KafkaListener(
            topics = "${app.kafka.tickets-orders-topic}",
            groupId = "${app.kafka.from-tickets-id}",
            containerFactory = "listenerFactoryTicketsToOrders"
    )
    @Override
    public void listen(TicketsToOrders message, UUID key, String topic, Integer partition, Long timestamp) {
        orderService.ticketsResponse(message);
    }
}
