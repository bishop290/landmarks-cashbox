package org.aston.tickets.services;

import org.aston.tickets.dto.TicketsResponseDto;

import java.util.List;

public interface TicketsService {
    List<TicketsResponseDto> all();
}
