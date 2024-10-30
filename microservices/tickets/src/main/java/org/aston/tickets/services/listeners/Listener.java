package org.aston.tickets.services.listeners;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.UUID;

public interface Listener<T> {

    void listen(
            @Payload
            T message,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false)
            UUID key,
            @Header(value = KafkaHeaders.RECEIVED_TOPIC)
            String topic,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION)
            Integer partition,
            @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP)
            Long timestamp
    );
}
