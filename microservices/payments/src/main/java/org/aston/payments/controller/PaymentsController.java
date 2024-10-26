package org.aston.payments.controller;

import org.aston.payments.dto.PaymentsResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/api")
public interface PaymentsController {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<PaymentsResponseDto> get();
}
