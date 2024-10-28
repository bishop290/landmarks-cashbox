package org.aston.orders.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.aston.orders.dto.messages.PaymentsToOrders;
import org.aston.orders.dto.messages.TicketsToOrders;
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
    public ProducerFactory<String, TicketsToOrders> producerFactoryTicketsToOrders(ObjectMapper mapper) {
        return new DefaultKafkaProducerFactory<>(
                defaultConfigForProducer(),
                new StringSerializer(),
                new JsonSerializer<>(mapper));
    }

    @Bean
    public ProducerFactory<String, PaymentsToOrders> producerFactoryPaymentsToOrders(ObjectMapper mapper) {
        return new DefaultKafkaProducerFactory<>(
                defaultConfigForProducer(),
                new StringSerializer(),
                new JsonSerializer<>(mapper));
    }

    @Bean
    public KafkaTemplate<String, TicketsToOrders> templateTicketsToOrders(
            ProducerFactory<String, TicketsToOrders> factory) {
        return new KafkaTemplate<>(factory);
    }

    @Bean
    public KafkaTemplate<String, PaymentsToOrders> templatePaymentsToOrders(
            ProducerFactory<String, PaymentsToOrders> factory) {
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
