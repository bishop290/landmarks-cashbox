package org.aston.tickets.integration.listeners;

import lombok.RequiredArgsConstructor;
import org.aston.tickets.dto.messages.OrdersToTickets;
import org.aston.tickets.integration.IntegrationTest;
import org.aston.tickets.integration.KafkaPostgresTestContainer;
import org.aston.tickets.model.Ticket;
import org.aston.tickets.repository.TicketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Tickets listener integration tests")
class TicketsListenerTest extends KafkaPostgresTestContainer {
    private final KafkaTemplate<String, OrdersToTickets> ordersToTicketsTemplate;
    private final TicketRepository ticketRepository;

    @Value("${app.kafka.orders-tickets-topic}")
    private String ticketTopic;

    private static final CountDownLatch latch = new CountDownLatch(1);

    @Test
    @DisplayName("Listen to Orders")
    void testListenToPayments() throws InterruptedException {
        Long orderId = 1L;
        String key = UUID.randomUUID().toString();
        OrdersToTickets event = new OrdersToTickets(orderId);

        ordersToTicketsTemplate.send(ticketTopic, key, event);
        latch.await(3, TimeUnit.SECONDS);

        Optional<Ticket> maybeTicket = ticketRepository.findByOrder(orderId);

        assertTrue(maybeTicket.isPresent());
        assertEquals(orderId, maybeTicket.get().getOrder());
    }
}
