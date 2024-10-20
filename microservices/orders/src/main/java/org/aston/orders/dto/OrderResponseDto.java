package org.aston.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aston.orders.model.Status;
import org.aston.orders.model.TypeOfAttraction;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private String customer;
    private Integer visitors;
    private Long amount;
    private String landscape;
    private TypeOfAttraction attraction;
    private Status status;
}
