package org.aston.tickets.integration.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.aston.tickets.integration.IntegrationTest;
import org.aston.tickets.integration.PostgresTestContainer;
import org.aston.tickets.model.Ticket;
import org.aston.tickets.repository.TicketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
@RequiredArgsConstructor
@DisplayName("Order repository integration test")
class TicketRepositoryTest extends PostgresTestContainer {
    private final TicketRepository ticketRepository;
    private final EntityManager manager;

    @Test
    @DisplayName("Save ticket to db")
    void testSaveToDb() {
        Ticket ticket = Ticket.builder().order(1L).build();

        ticketRepository.saveAndFlush(ticket);
        manager.detach(ticket);

        Optional<Ticket> maybeTicket = ticketRepository.findById(ticket.getId());
        assertTrue(maybeTicket.isPresent());
    }
}
