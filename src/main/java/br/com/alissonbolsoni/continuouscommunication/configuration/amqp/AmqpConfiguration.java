package br.com.alissonbolsoni.continuouscommunication.configuration.amqp;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
    public static final String EXCHANGE_NAME = "EX_CONTINUOUS_COMMUNICATION";

    @Bean
    protected TopicExchange createExchange() {
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }
}
