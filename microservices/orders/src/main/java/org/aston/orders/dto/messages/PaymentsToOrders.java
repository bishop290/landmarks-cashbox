package org.aston.orders.dto.messages;

public record PaymentsToOrders(Boolean wasProcessed, Long cashReceipt, Long order) {
}
