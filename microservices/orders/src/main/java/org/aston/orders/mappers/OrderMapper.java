package org.aston.orders.mappers;

import org.aston.orders.dto.OrderRequestNewDto;
import org.aston.orders.dto.OrderRequestUpdateDto;
import org.aston.orders.dto.OrderResponseDto;
import org.aston.orders.model.Order;
import org.aston.orders.model.Status;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponseDto entityToDto(Order entity);

    default Order dtoToEntityCreate(OrderRequestNewDto dto) {
        return Order.builder()
                .numberOfVisitors(dto.numberOfVisitors())
                .price(dto.price())
                .status(Status.RESERVED)
                .build();
    }

    default Order dtoToEntityUpdate(OrderRequestUpdateDto dto, Order entity) {
        entity.setStatus(dto.status());
        return entity;
    }
}
