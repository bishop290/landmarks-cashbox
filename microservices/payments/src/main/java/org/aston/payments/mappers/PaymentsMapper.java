package org.aston.payments.mappers;

import org.aston.payments.dto.PaymentsResponseDto;
import org.aston.payments.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentsMapper {

    List<PaymentsResponseDto> entityToDto(List<Payment> entity);
}
