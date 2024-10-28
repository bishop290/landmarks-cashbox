package org.aston.orders.mappers;

import org.aston.orders.dto.messages.OrdersToPayments;
import org.aston.orders.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderPaymentsMessageMapper {

    default OrdersToPayments OrderToMessage(Order entity) {
        Long sum = entity.getPrice() * entity.getNumberOfVisitors();
        return new OrdersToPayments(entity.getId(), sum, false);
    }
}
