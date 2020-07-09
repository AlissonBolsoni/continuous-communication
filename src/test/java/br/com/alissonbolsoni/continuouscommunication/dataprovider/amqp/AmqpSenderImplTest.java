package br.com.alissonbolsoni.continuouscommunication.dataprovider.amqp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AmqpSenderImplTest {

    private final RabbitTemplate rabbit = mock(RabbitTemplate.class);
    private final TopicExchange topicExchange = mock(TopicExchange.class);

    @Test
    void testSendMessage(){
        doNothing().when(rabbit).convertAndSend(anyString(), anyString(), anyString());
        when(topicExchange.getName()).thenReturn("Exchange");
        AmqpSenderImpl amqpSender = new AmqpSenderImpl(rabbit, topicExchange);

        amqpSender.sendMessage("RoutingKey", "json");
        verify(rabbit).convertAndSend(anyString(), anyString(), anyString());
    }

}