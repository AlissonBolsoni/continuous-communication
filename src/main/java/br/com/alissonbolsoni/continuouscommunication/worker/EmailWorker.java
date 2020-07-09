package br.com.alissonbolsoni.continuouscommunication.worker;

import br.com.alissonbolsoni.continuouscommunication.core.UpdateMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.contants.RoutingKeys;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailWorker {
    public static final String Q_EMAIL_WORKER = "Q_EMAIL_WORKER";

    private final UpdateMessageUseCase updateMessageUseCase;

    public EmailWorker(UpdateMessageUseCase updateMessageUseCase) {
        this.updateMessageUseCase = updateMessageUseCase;
    }

    @Bean
    protected Queue createEmailWorkerQueue() {
        return new Queue(Q_EMAIL_WORKER, true, false, false, null);
    }

    @Bean
    protected Binding BindingEmailWorkerQueue(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RoutingKeys.ROUTING_KEY_EMAIL);
    }

    @RabbitListener(queues = {Q_EMAIL_WORKER})
    protected void EmailWorkerConsumer(Message message) throws IOException {
        String body = new String(message.getBody());
        System.out.println(body);
        ObjectMapper objectMapper = new ObjectMapper();
        br.com.alissonbolsoni.continuouscommunication.core.entity.Message messageReaded = objectMapper.readValue(body, br.com.alissonbolsoni.continuouscommunication.core.entity.Message.class);
        updateMessageUseCase.updateMessage(messageReaded);
    }
}
