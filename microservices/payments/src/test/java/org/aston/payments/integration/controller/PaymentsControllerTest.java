package org.aston.payments.integration.controller;

import lombok.RequiredArgsConstructor;
import org.aston.payments.integration.IntegrationTest;
import org.aston.payments.integration.PostgresTestContainer;
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
@DisplayName("Payments controller integration tests")
class PaymentsControllerTest extends PostgresTestContainer {

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
