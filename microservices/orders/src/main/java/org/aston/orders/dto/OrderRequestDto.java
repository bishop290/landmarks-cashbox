package org.aston.orders.dto;

import org.aston.orders.model.Status;
import org.aston.orders.model.TypeOfAttraction;

public record OrderRequestDto(
        String customer,
        Integer visitors,
        Long amount,
        String landscape,
        TypeOfAttraction attraction,
        Status status) {
}
