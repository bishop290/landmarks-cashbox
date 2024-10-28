package org.aston.orders.dto.messages;

public record OrdersToPayments(Long order, Long price, Boolean cancel) {
}
