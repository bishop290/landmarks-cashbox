package org.aston.payments.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.aston.payments.dto.messages.OrdersToPayments;
import org.aston.payments.dto.messages.PaymentsToOrders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {
    @Value("${spring.kafka.service}")
    private String service;

    @Value("${app.kafka.from-orders-id")
    private String fromOrdersId;

    @Bean
    public ProducerFactory<String, PaymentsToOrders> producerFactoryPaymentsToOrders(ObjectMapper mapper) {
        return new DefaultKafkaProducerFactory<>(
                defaultConfigForProducer(),
                new StringSerializer(),
                new JsonSerializer<>(mapper));
    }

    @Bean
    public KafkaTemplate<String, PaymentsToOrders> templatePaymentsToOrders(
            ProducerFactory<String, PaymentsToOrders> factory) {
        return new KafkaTemplate<>(factory);
    }

    @Bean
    public ConsumerFactory<String, OrdersToPayments> consumerFactoryOrdersToPayments(ObjectMapper mapper) {
        return new DefaultKafkaConsumerFactory<>(
                defaultConfigForConsumer(fromOrdersId),
                new StringDeserializer(),
                new JsonDeserializer<>(mapper));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrdersToPayments> listenerFactoryOrdersToPayments(
            ConsumerFactory<String, OrdersToPayments> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, OrdersToPayments> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    private Map<String, Object> defaultConfigForProducer() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, service);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }

    private Map<String, Object> defaultConfigForConsumer(String consumer) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, service);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, consumer);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return config;
    }
}
