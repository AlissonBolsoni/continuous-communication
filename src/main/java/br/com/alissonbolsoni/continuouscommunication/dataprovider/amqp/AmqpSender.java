package br.com.alissonbolsoni.continuouscommunication.dataprovider.amqp;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AmqpSender {

    @Autowired
    private RabbitTemplate rabbit;
    @Autowired
    private TopicExchange topicExchange;

    public void sendMessage(String routingKey, String message){
        rabbit.convertAndSend(
                topicExchange.getName(),
                routingKey,
                message
        );
    }

}
