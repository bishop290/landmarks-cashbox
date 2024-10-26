package org.aston.orders.servises;

import org.aston.orders.dto.DefaultResponse;
import org.aston.orders.dto.OrderRequestNewDto;
import org.aston.orders.dto.OrderRequestUpdateDto;
import org.aston.orders.dto.OrderResponseDto;

public interface OrdersService {

    OrderResponseDto find(Long id);

    DefaultResponse create(OrderRequestNewDto order);

    DefaultResponse update(Long id, OrderRequestUpdateDto order);
}
