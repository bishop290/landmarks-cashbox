package org.aston.payments.dto.messages;

public record PaymentsToOrders(Boolean wasProcessed, Long cashReceipt, Long order) {
}
