package org.aston.payments.services;

import org.aston.payments.dto.PaymentsResponseDto;
import org.aston.payments.dto.messages.OrdersToPayments;

import java.util.List;

public interface PaymentsService {
    List<PaymentsResponseDto> all();

    void ordersRequest(OrdersToPayments message);
}
