package org.aston.orders.dto;

import org.aston.orders.model.Status;

public record OrderRequestUpdateDto(Status status) {
}
