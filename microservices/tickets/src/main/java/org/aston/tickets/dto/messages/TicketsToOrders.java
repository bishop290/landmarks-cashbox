package org.aston.tickets.dto.messages;

public record TicketsToOrders(Boolean wasProcessed, Long ticket, Long order) {
}
