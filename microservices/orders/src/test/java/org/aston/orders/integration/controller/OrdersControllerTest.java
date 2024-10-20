package org.aston.orders.integration.controller;

import lombok.RequiredArgsConstructor;
import org.aston.orders.dto.OrderRequestDto;
import org.aston.orders.integration.IntegrationTest;
import org.aston.orders.integration.PostgresTestContainer;
import org.aston.orders.model.Status;
import org.aston.orders.model.TypeOfAttraction;
import org.junit.jupiter.api.BeforeEach;
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

    private OrderRequestDto dto;

    @BeforeEach
    void init() {
        dto = new OrderRequestDto(
                "Customer-66",
                2,
                5000L,
                "Landscape-66",
                TypeOfAttraction.ARCHEOLOGY,
                Status.NEW);
    }

    @Test
    @DisplayName("Find by id")
    void testFindById() throws Exception {
        Long id = 1L;
        String name = "Customer-1";
        mockMvc.perform(get(String.format("/api/%s", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer").value(name));
    }

    @Test
    @DisplayName("New order")
    void testSave() throws Exception {
        mockMvc.perform(MockMvcHelper.postJson("/api", dto))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("true"));
    }

    @Test
    @DisplayName("Update order")
    void testUpdate() throws Exception {
        String id = "1";
        mockMvc.perform(MockMvcHelper.putJson(String.format("/api/%s", id), dto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("true"));
    }
}
