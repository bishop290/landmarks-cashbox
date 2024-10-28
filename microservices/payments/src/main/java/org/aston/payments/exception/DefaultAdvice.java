package org.aston.payments.exception;

import org.aston.payments.dto.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DefaultAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PaymentNotFoundException.class)
    public DefaultResponse handlePaymentNotFoundException(PaymentNotFoundException e) {
        return new DefaultResponse(false, e.getMessage());
    }
}
