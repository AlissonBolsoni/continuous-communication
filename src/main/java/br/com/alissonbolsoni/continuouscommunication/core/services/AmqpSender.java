package br.com.alissonbolsoni.continuouscommunication.core.services;

public interface AmqpSender {
    public void sendMessage(String routingKey, String message);
}
