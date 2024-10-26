package org.aston.payments.dto;

public record PaymentsResponseDto(Long id, Long price, Long order, Boolean cancel) {
}
