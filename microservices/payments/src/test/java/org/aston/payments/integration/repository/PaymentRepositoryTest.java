package org.aston.payments.integration.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.aston.payments.integration.IntegrationTest;
import org.aston.payments.integration.PostgresTestContainer;
import org.aston.payments.model.Payment;
import org.aston.payments.repository.PaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Payment repository integration test")
class PaymentRepositoryTest extends PostgresTestContainer {
    private final PaymentRepository paymentRepository;
    private final EntityManager manager;

    @Test
    @DisplayName("Save payment to db")
    void testSaveToDb() {
        Payment payment = Payment.builder()
                .price(333L)
                .order(1L)
                .cancel(false)
                .build();

        paymentRepository.saveAndFlush(payment);
        manager.detach(payment);

        Optional<Payment> maybeTicket = paymentRepository.findById(payment.getId());
        assertTrue(maybeTicket.isPresent());
    }
}
