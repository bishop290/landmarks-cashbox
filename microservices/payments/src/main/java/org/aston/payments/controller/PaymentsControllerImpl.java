package org.aston.payments.controller;

import lombok.RequiredArgsConstructor;
import org.aston.payments.dto.PaymentsResponseDto;
import org.aston.payments.services.PaymentsService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentsControllerImpl implements PaymentsController {

    private final PaymentsService service;

    @Override
    public List<PaymentsResponseDto> get() {
        return service.all();
    }
}
