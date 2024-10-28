package org.aston.orders.integration.controller;

import lombok.RequiredArgsConstructor;
import org.aston.orders.dto.OrderRequestNewDto;
import org.aston.orders.integration.IntegrationTest;
import org.aston.orders.integration.PostgresTestContainer;
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
@DisplayName("Orders controller integration tests")
class OrdersControllerTest extends PostgresTestContainer {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Find by id")
    void testFindById() throws Exception {
        Long id = 1L;
        Long price = 300L;
        mockMvc.perform(get(String.format("/api/%s", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(price));
    }

    @Test
    @DisplayName("New order")
    void testSave() throws Exception {
        OrderRequestNewDto dto = new OrderRequestNewDto(2L, 222L);
        mockMvc.perform(MockMvcHelper.postJson("/api", dto))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("true"));
    }

    @Deprecated
    @Test
    @DisplayName("Update order")
    void testUpdate() throws Exception {
        String id = "1";
        /*
        OrderRequestUpdateDto dto = new OrderRequestUpdateDto(Status.CANCELLED);
        mockMvc.perform(MockMvcHelper.putJson(String.format("/api/%s", id), dto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("true"));
         */
    }
}
