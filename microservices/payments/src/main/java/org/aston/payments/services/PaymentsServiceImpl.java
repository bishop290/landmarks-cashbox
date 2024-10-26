package org.aston.payments.services;

import lombok.RequiredArgsConstructor;
import org.aston.payments.dto.PaymentsResponseDto;
import org.aston.payments.mappers.PaymentsMapper;
import org.aston.payments.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsServiceImpl implements PaymentsService {
    private final PaymentRepository repository;
    private final PaymentsMapper mapper;

    @Override
    public List<PaymentsResponseDto> all() {
        return mapper.entityToDto(repository.findAll());
    }
}
