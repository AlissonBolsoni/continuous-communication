package br.com.alissonbolsoni.continuouscommunication.core;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;

public interface MessagesUseCase {
    public Message getMessageById(String id);
}
