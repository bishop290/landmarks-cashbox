package org.aston.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aston.orders.model.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long numberOfVisitors;
    private Long price;
    private Status status;
    private Long ticket;
    private Long cashReceipt;
}
