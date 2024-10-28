package org.aston.payments.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.aston.payments.dto.messages.OrdersToPayments;
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
    public ProducerFactory<String, OrdersToPayments> producerFactoryOrdersToPayments(ObjectMapper mapper) {
        return new DefaultKafkaProducerFactory<>(
                defaultConfigForProducer(),
                new StringSerializer(),
                new JsonSerializer<>(mapper));
    }


    @Bean
    public KafkaTemplate<String, OrdersToPayments> templateOrdersToPayments(
            ProducerFactory<String, OrdersToPayments> factory) {
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
