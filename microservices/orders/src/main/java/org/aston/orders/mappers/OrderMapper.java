package org.aston.orders.mappers;

import org.aston.orders.dto.OrderRequestDto;
import org.aston.orders.dto.OrderResponseDto;
import org.aston.orders.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    Order dtoToEntity(OrderRequestDto dto);

    OrderResponseDto entityToDto(Order entity);

    default Order dtoToEntityUpdate(OrderRequestDto dto, Order entity) {
        Order newEntity = this.dtoToEntity(dto);
        newEntity.setId(entity.getId());
        return newEntity;
    }
}
