package br.com.alissonbolsoni.continuouscommunication.core.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;

import java.util.UUID;

public interface MessageRepository {

    public Message getMessage(UUID messageId);
    public Message removeMessage(UUID messageId) throws Exception;
    public Message saveMessage(Message message) throws Exception;
    public Boolean updateMessage(Message message) throws Exception;
}
