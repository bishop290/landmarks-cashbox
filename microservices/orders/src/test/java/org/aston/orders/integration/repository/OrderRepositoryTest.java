package org.aston.orders.integration.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.aston.orders.integration.IntegrationTest;
import org.aston.orders.integration.PostgresTestContainer;
import org.aston.orders.model.Order;
import org.aston.orders.model.Status;
import org.aston.orders.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Order repository integration test")
class OrderRepositoryTest extends PostgresTestContainer {
    private final OrderRepository orderRepository;
    private final EntityManager manager;

    @Test
    @DisplayName("Save order to db")
    void testSaveToDb() {
        Order order = Order.builder()
                .numberOfVisitors(2L)
                .price(200L)
                .status(Status.RESERVED)
                .build();

        orderRepository.saveAndFlush(order);
        manager.detach(order);

        Optional<Order> maybeOrder = orderRepository.findById(order.getId());
        assertTrue(maybeOrder.isPresent());
    }
}
