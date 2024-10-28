package org.aston.orders.mappers;

import org.aston.orders.dto.messages.OrdersToTickets;
import org.aston.orders.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderTicketsMessageMapper {

    default OrdersToTickets OrderToMessage(Order entity) {
        return new OrdersToTickets(entity.getId());
    }
}
