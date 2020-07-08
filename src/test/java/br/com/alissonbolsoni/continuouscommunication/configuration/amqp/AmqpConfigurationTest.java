package br.com.alissonbolsoni.continuouscommunication.configuration.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.TopicExchange;

import static org.junit.jupiter.api.Assertions.*;

class AmqpConfigurationTest {

    @Test
    void testCreateExchange(){
        AmqpConfiguration amqpConfiguration = new AmqpConfiguration();
        TopicExchange exchange = amqpConfiguration.createExchange();

        assertNotNull(exchange);
        assertTrue(exchange.getClass().isAssignableFrom(TopicExchange.class));
    }

}