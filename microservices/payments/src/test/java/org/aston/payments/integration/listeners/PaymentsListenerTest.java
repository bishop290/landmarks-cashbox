package org.aston.payments.integration.listeners;

import lombok.RequiredArgsConstructor;
import org.aston.payments.dto.messages.OrdersToPayments;
import org.aston.payments.integration.IntegrationTest;
import org.aston.payments.integration.KafkaPostgresTestContainer;
import org.aston.payments.model.Payment;
import org.aston.payments.repository.PaymentRepository;
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
@DisplayName("Payments listener integration tests")
class PaymentsListenerTest extends KafkaPostgresTestContainer {
    private final KafkaTemplate<String, OrdersToPayments> ordersToPaymentsTemplate;
    private final PaymentRepository paymentRepository;

    @Value("${app.kafka.orders-payments-topic}")
    private String paymentsTopic;

    private static final CountDownLatch latch = new CountDownLatch(1);

    @Test
    @DisplayName("Listen to Orders")
    void testListenToPayments() throws InterruptedException {
        Long orderId = 1L;
        String key = UUID.randomUUID().toString();
        OrdersToPayments event = new OrdersToPayments(orderId, 500L, false);

        ordersToPaymentsTemplate.send(paymentsTopic, key, event);
        latch.await(3, TimeUnit.SECONDS);
        Optional<Payment> maybePayment = paymentRepository.findByOrder(orderId);
        assertTrue(maybePayment.isPresent());
        assertEquals(orderId, maybePayment.get().getOrder());
    }
}
