package org.aston.orders.controller;

import org.aston.orders.dto.DefaultResponse;
import org.aston.orders.dto.OrderRequestNewDto;
import org.aston.orders.dto.OrderRequestUpdateDto;
import org.aston.orders.dto.OrderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
public interface OrdersController {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    OrderResponseDto get(@PathVariable("id") Long id);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    DefaultResponse create(@RequestBody OrderRequestNewDto order);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    DefaultResponse update(@PathVariable("id") Long id, @RequestBody OrderRequestUpdateDto order);
}
