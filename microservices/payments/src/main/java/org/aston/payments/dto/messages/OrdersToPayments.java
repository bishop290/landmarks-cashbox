package org.aston.payments.dto.messages;

public record OrdersToPayments(Long order, Long price, Boolean cancel) {
}
