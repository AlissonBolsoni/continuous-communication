package br.com.alissonbolsoni.continuouscommunication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"br.com.alissonbolsoni.continuouscommunication"})
@ComponentScan(basePackages = {"br.com.alissonbolsoni.continuouscommunication", "br.com.alissonbolsoni.continuouscommunication.configuration"})
public class ContinuousCommunicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContinuousCommunicationApplication.class, args);
    }

}
