package org.aston.orders.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.aston.orders.dto.messages.OrdersToPayments;
import org.aston.orders.dto.messages.OrdersToTickets;
import org.aston.orders.dto.messages.PaymentsToOrders;
import org.aston.orders.dto.messages.TicketsToOrders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {
    @Value("${spring.kafka.service}")
    private String service;

    @Value("${app.kafka.from-tickets-id}")
    private String fromTicketsId;

    @Value("${app.kafka.from-payments-id}")
    private String fromPaymentsId;

    @Bean
    public ProducerFactory<String, OrdersToTickets> producerFactoryOrdersToTickets(ObjectMapper mapper) {
        return new DefaultKafkaProducerFactory<>(
                defaultConfigForProducer(),
                new StringSerializer(),
                new JsonSerializer<>(mapper));
    }

    @Bean
    public ProducerFactory<String, OrdersToPayments> producerFactoryOrdersToPayments(ObjectMapper mapper) {
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

    @Bean
    public KafkaTemplate<String, OrdersToPayments> templateOrdersToPayments(
            ProducerFactory<String, OrdersToPayments> factory) {
        return new KafkaTemplate<>(factory);
    }

    @Bean
    public ConsumerFactory<String, TicketsToOrders> consumerFactoryTicketsToOrder() {
        return new DefaultKafkaConsumerFactory<>(
                defaultConfigForConsumer(fromTicketsId),
                new StringDeserializer(),
                new JsonDeserializer<>(TicketsToOrders.class, false));
    }

    @Bean
    public ConsumerFactory<String, PaymentsToOrders> consumerFactoryPaymentsToOrders() {
        return new DefaultKafkaConsumerFactory<>(
                defaultConfigForConsumer(fromPaymentsId),
                new StringDeserializer(),
                new JsonDeserializer<>(PaymentsToOrders.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TicketsToOrders> listenerFactoryTicketsToOrders(
            ConsumerFactory<String, TicketsToOrders> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, TicketsToOrders> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentsToOrders> listenerFactoryPaymentsToOrders(
            ConsumerFactory<String, PaymentsToOrders> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, PaymentsToOrders> factory =
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
