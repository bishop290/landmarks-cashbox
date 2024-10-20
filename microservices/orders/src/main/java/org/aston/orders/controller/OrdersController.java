package org.aston.orders.controller;

import org.aston.orders.dto.DefaultResponse;
import org.aston.orders.dto.OrderRequestDto;
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
    DefaultResponse create(@RequestBody OrderRequestDto order);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    DefaultResponse update(@PathVariable("id") Long id, @RequestBody OrderRequestDto order);
}
