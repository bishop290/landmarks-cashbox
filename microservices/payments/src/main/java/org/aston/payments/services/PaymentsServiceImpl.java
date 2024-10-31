package org.aston.payments.services;

import lombok.RequiredArgsConstructor;
import org.aston.payments.dto.PaymentsResponseDto;
import org.aston.payments.dto.messages.OrdersToPayments;
import org.aston.payments.dto.messages.PaymentsToOrders;
import org.aston.payments.mappers.PaymentsMapper;
import org.aston.payments.model.Payment;
import org.aston.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentsServiceImpl implements PaymentsService {
    private final PaymentRepository repository;
    private final PaymentsMapper mapper;
    private final KafkaTemplate<String, PaymentsToOrders> paymentsToOrders;

    @Value("${app.kafka.payments-orders-topic}")
    private String paymentsToOrdersTopic;

    @Override
    public List<PaymentsResponseDto> all() {
        return mapper.entityToDto(repository.findAll());
    }

    @Override
    @Transactional
    public void ordersRequest(OrdersToPayments message) {
        if (!message.cancel()) {
            Payment payment = repository.save(Payment.builder()
                    .order(message.order())
                    .price(message.price())
                    .cancel(false)
                    .build());
            paymentsToOrders.send(
                    paymentsToOrdersTopic,
                    new PaymentsToOrders(true, payment.getId(), message.order()));
            return;
        }
        Optional<Payment> maybePayment = repository.findByOrder(message.order());
        if (maybePayment.isPresent()) {
            Payment payment = maybePayment.get();
            payment.setCancel(true);
            repository.save(payment);
        }
    }
}
