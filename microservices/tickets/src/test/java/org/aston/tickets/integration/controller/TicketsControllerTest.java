package org.aston.tickets.integration.controller;

import lombok.RequiredArgsConstructor;
import org.aston.tickets.integration.IntegrationTest;
import org.aston.tickets.integration.PostgresTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@DisplayName("Tickets controller integration tests")
class TicketsControllerTest extends PostgresTestContainer {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Find all")
    void testFind() throws Exception {
        mockMvc.perform(get("/api/%s"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
