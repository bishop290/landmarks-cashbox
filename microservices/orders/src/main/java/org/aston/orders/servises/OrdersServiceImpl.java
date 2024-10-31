package org.aston.orders.servises;

import lombok.RequiredArgsConstructor;
import org.aston.orders.dto.DefaultResponse;
import org.aston.orders.dto.OrderRequestNewDto;
import org.aston.orders.dto.OrderRequestUpdateDto;
import org.aston.orders.dto.OrderResponseDto;
import org.aston.orders.dto.messages.OrdersToPayments;
import org.aston.orders.dto.messages.OrdersToTickets;
import org.aston.orders.dto.messages.PaymentsToOrders;
import org.aston.orders.dto.messages.TicketsToOrders;
import org.aston.orders.exception.OrderNotFoundException;
import org.aston.orders.mappers.OrderMapper;
import org.aston.orders.mappers.OrderPaymentsMessageMapper;
import org.aston.orders.mappers.OrderTicketsMessageMapper;
import org.aston.orders.model.Order;
import org.aston.orders.model.Status;
import org.aston.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final KafkaTemplate<String, OrdersToPayments> ordersToPayments;
    private final OrderPaymentsMessageMapper paymentsMessageMapper;
    private final KafkaTemplate<String, OrdersToTickets> ordersToTickets;
    private final OrderTicketsMessageMapper ticketsMessageMapper;

    @Value("${app.kafka.orders-payments-topic}")
    private String ordersPaymentTopic;

    @Value("${app.kafka.orders-tickets-topic}")
    private String ordersTicketsTopic;

    @Override
    public OrderResponseDto find(Long id) {
        return mapper.entityToDto(order(id));
    }

    @Override
    @Transactional
    public DefaultResponse create(OrderRequestNewDto dto) {
        Order order = repository.save(mapper.dtoToEntityCreate(dto));
        return new DefaultResponse(true, "Order saved", order.getId());
    }

    @Override
    @Transactional
    public DefaultResponse update(Long id, OrderRequestUpdateDto dto) {
        Order order = mapper.dtoToEntityUpdate(dto, order(id));
        repository.save(order);
        ordersToPayments.send(ordersPaymentTopic, paymentsMessageMapper.OrderToMessage(order));
        return new DefaultResponse(true, "Order updated", order.getId());
    }

    @Override
    @Transactional
    public void paymentsResponse(PaymentsToOrders message) {
        long erase = 0L;
        Order order = order(message.order());
        if (message.wasProcessed()) {
            order.setCashReceipt(message.cashReceipt());
            order.setStatus(Status.PAID);
            repository.save(order);
            ordersToTickets.send(ordersTicketsTopic, ticketsMessageMapper.OrderToMessage(order));
            return;
        }
        order.setCashReceipt(erase);
        order.setStatus(Status.RESERVED);
    }

    @Override
    @Transactional
    public void ticketsResponse(TicketsToOrders message) {
        long erase = 0L;
        Order order = order(message.order());
        if (message.wasProcessed()) {
            order.setTicket(message.ticket());
            repository.save(order);
            return;
        }
        ordersToPayments.send(ordersPaymentTopic, new OrdersToPayments(order.getId(), erase, true));
        order.setCashReceipt(erase);
        order.setStatus(Status.RESERVED);
        repository.save(order);
    }

    private Order order(long id) {
        return repository.findById(id).orElseThrow(OrderNotFoundException::new);
    }
}
