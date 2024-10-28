package org.aston.orders.integration.listeners;

import lombok.RequiredArgsConstructor;
import org.aston.orders.dto.messages.PaymentsToOrders;
import org.aston.orders.dto.messages.TicketsToOrders;
import org.aston.orders.integration.IntegrationTest;
import org.aston.orders.integration.KafkaPostgresTestContainer;
import org.aston.orders.model.Order;
import org.aston.orders.model.Status;
import org.aston.orders.repository.OrderRepository;
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
@DisplayName("Orders listener integration tests")
class OrdersListenerTest extends KafkaPostgresTestContainer {
    private final KafkaTemplate<String, PaymentsToOrders> paymentsToOrdersTemplate;
    private final KafkaTemplate<String, TicketsToOrders> ticketsToOrdersTemplate;
    private final OrderRepository orderRepository;

    @Value("${app.kafka.tickets-orders-topic}")
    private String ticketsTopic;

    @Value("${app.kafka.payments-orders-topic}")
    private String paymentsTopic;

    private static final CountDownLatch latch = new CountDownLatch(1);

    @Test
    @DisplayName("Listen to Payments")
    void testListenToPayments() throws InterruptedException {
        Long cashReceiptId = 1L;
        Long orderId = 1L;
        String key = UUID.randomUUID().toString();
        PaymentsToOrders event = new PaymentsToOrders(true, cashReceiptId, orderId);

        paymentsToOrdersTemplate.send(paymentsTopic, key, event);
        latch.await(3, TimeUnit.SECONDS);
        Optional<Order> maybeOrder = orderRepository.findById(orderId);
        assertTrue(maybeOrder.isPresent());
        assertEquals(Status.PAID, maybeOrder.get().getStatus());
    }

    @Test
    @DisplayName("Listen to Tickets")
    void testListenToTickets() throws InterruptedException {
        Long ticketId = 1L;
        Long orderId = 1L;
        String key = UUID.randomUUID().toString();
        TicketsToOrders event = new TicketsToOrders(true, ticketId, orderId);

        ticketsToOrdersTemplate.send(ticketsTopic, key, event);
        latch.await(3, TimeUnit.SECONDS);
        Optional<Order> maybeOrder = orderRepository.findById(orderId);
        assertTrue(maybeOrder.isPresent());
        assertEquals(ticketId, maybeOrder.get().getTicket());
    }
}
