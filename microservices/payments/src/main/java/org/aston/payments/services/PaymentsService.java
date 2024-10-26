package org.aston.payments.services;

import org.aston.payments.dto.PaymentsResponseDto;

import java.util.List;

public interface PaymentsService {
    List<PaymentsResponseDto> all();
}
