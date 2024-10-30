package org.aston.tickets.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.aston.tickets.dto.messages.OrdersToTickets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@TestConfiguration
public class KafkaTestConfiguration {
    @Value("${spring.kafka.service}")
    private String service;

    @Bean
    public ProducerFactory<String, OrdersToTickets> producerFactoryOrdersToTickets(ObjectMapper mapper) {
        return new DefaultKafkaProducerFactory<>(
                defaultConfigForProducer(),
                new StringSerializer(),
                new JsonSerializer<>(mapper));
    }

    @Bean
    public KafkaTemplate<String, OrdersToTickets> templateOrdersToTickets(
            ProducerFactory<String, OrdersToTickets> factory) {
        return new KafkaTemplate<>(factory);
    }

    private Map<String, Object> defaultConfigForProducer() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, service);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }
}
