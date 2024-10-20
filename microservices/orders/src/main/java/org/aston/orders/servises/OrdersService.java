package org.aston.orders.servises;

import org.aston.orders.dto.DefaultResponse;
import org.aston.orders.dto.OrderRequestDto;
import org.aston.orders.dto.OrderResponseDto;

public interface OrdersService {

    OrderResponseDto find(Long id);

    DefaultResponse create(OrderRequestDto order);

    DefaultResponse update(Long id, OrderRequestDto order);
}
