package org.aston.payments.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException() { super("Payment not found."); }
}
