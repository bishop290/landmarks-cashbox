package org.aston.tickets.services;

import org.aston.tickets.dto.TicketsResponseDto;
import org.aston.tickets.dto.messages.OrdersToTickets;

import java.util.List;

public interface TicketsService {
    List<TicketsResponseDto> all();

    void ordersRequest(OrdersToTickets message);
}
