package org.aston.tickets.services;

import lombok.RequiredArgsConstructor;
import org.aston.tickets.dto.TicketsResponseDto;
import org.aston.tickets.dto.messages.OrdersToTickets;
import org.aston.tickets.dto.messages.TicketsToOrders;
import org.aston.tickets.mappers.TicketsMapper;
import org.aston.tickets.model.Ticket;
import org.aston.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketsServiceImpl implements TicketsService {
    private final TicketRepository repository;
    private final TicketsMapper mapper;

    private final KafkaTemplate<String, TicketsToOrders> ticketsToOrders;

    @Value("${app.kafka.tickets-orders-topic}")
    private String ticketsToOrdersTopic;

    @Override
    public List<TicketsResponseDto> all() {
        return mapper.entityToDto(repository.findAll());
    }

    @Override
    @Transactional
    public void ordersRequest(OrdersToTickets message) {
        Optional<Ticket> maybeTicket = repository.findByOrder(message.order());
        if (maybeTicket.isEmpty()) {
            Ticket ticket = repository.save(Ticket.builder().order(message.order()).build());
            ticketsToOrders.send(
                    ticketsToOrdersTopic,
                    new TicketsToOrders(true, ticket.getId(), message.order()));
            return;
        }
        ticketsToOrders.send(
                ticketsToOrdersTopic,
                new TicketsToOrders(false, 0L, message.order()));
    }
}
