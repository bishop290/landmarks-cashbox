package org.aston.tickets.services.listeners;

import lombok.RequiredArgsConstructor;
import org.aston.tickets.dto.messages.OrdersToTickets;
import org.aston.tickets.services.TicketsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrdersListener implements Listener<OrdersToTickets> {
    private final TicketsService ticketsService;

    @KafkaListener(
            topics = "${app.kafka.orders-tickets-topic}",
            groupId = "${app.kafka.from-orders-id}",
            containerFactory = "listenerFactoryOrdersToTickets"
    )
    @Override
    public void listen(OrdersToTickets message, UUID key, String topic, Integer partition, Long timestamp) {
        ticketsService.ordersRequest(message);
    }
}
