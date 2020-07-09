package br.com.alissonbolsoni.continuouscommunication.dataprovider.amqp;

import br.com.alissonbolsoni.continuouscommunication.core.services.AmqpSender;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmqpSenderImpl implements AmqpSender {

    private final RabbitTemplate rabbit;
    private final TopicExchange topicExchange;

    @Autowired
    public AmqpSenderImpl(final RabbitTemplate rabbit, final TopicExchange topicExchange) {
        this.rabbit = rabbit;
        this.topicExchange = topicExchange;
    }

    @Override
    public void sendMessage(final String routingKey, final String message){
        rabbit.convertAndSend(
                topicExchange.getName(),
                routingKey,
                message
        );
    }

}
