package org.aston.orders.controller;

import lombok.RequiredArgsConstructor;
import org.aston.orders.dto.DefaultResponse;
import org.aston.orders.dto.OrderRequestDto;
import org.aston.orders.dto.OrderResponseDto;
import org.aston.orders.servises.OrdersService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersControllerImpl implements OrdersController {
    private final OrdersService service;

    @Override
    public OrderResponseDto get(Long id) {
        return service.find(id);
    }

    @Override
    public DefaultResponse create(OrderRequestDto order) {
        return service.create(order);
    }

    @Override
    public DefaultResponse update(Long id, OrderRequestDto order) {
        return service.update(id, order);
    }
}
