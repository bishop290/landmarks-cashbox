package org.aston.tickets.controller;

import lombok.RequiredArgsConstructor;
import org.aston.tickets.dto.TicketsResponseDto;
import org.aston.tickets.services.TicketsService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketsControllerImpl implements TicketsController {

    private final TicketsService service;

    @Override
    public List<TicketsResponseDto> get() {
        return service.all();
    }
}
