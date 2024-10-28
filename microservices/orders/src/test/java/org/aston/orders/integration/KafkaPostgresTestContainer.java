package org.aston.orders.integration;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.kafka.KafkaContainer;

@Import(KafkaTestConfiguration.class)
public abstract class KafkaPostgresTestContainer {
    private static final KafkaContainer KAFKA;
    private static final PostgreSQLContainer<?> POSTGRES;

    static {
        KAFKA = new KafkaContainer("apache/kafka:latest");
        KAFKA.start();
        POSTGRES = new PostgreSQLContainer<>("postgres:15-alpine")
                .withReuse(true);
        POSTGRES.start();
    }

    @DynamicPropertySource
    static void containerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.service", KAFKA::getBootstrapServers);
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }
}
