package br.com.alissonbolsoni.continuouscommunication.core.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;

import java.util.List;

public interface MessageRepository {

    public Message getMessage(String messageId);
    public Message saveMessage(Message message) throws Exception;
}
