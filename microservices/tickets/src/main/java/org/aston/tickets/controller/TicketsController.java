package org.aston.tickets.controller;

import org.aston.tickets.dto.TicketsResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/api")
public interface TicketsController {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<TicketsResponseDto> get();
}
