package org.aston.orders.dto.messages;

public record TicketsToOrders(Boolean wasProcessed, Long ticket, Long order) {
}
