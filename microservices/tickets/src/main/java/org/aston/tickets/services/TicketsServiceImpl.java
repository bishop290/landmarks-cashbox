package org.aston.tickets.services;

import lombok.RequiredArgsConstructor;
import org.aston.tickets.dto.TicketsResponseDto;
import org.aston.tickets.mappers.TicketsMapper;
import org.aston.tickets.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketsServiceImpl implements TicketsService {
    private final TicketRepository repository;
    private final TicketsMapper mapper;

    @Override
    public List<TicketsResponseDto> all() {
        return mapper.entityToDto(repository.findAll());
    }
}
