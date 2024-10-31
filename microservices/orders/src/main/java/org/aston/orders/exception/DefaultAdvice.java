package org.aston.orders.exception;

import org.aston.orders.dto.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DefaultAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderNotFoundException.class)
    public DefaultResponse handleOrderNotFoundException(OrderNotFoundException e) {
        return new DefaultResponse(false, e.getMessage(), 0L);
    }
}
