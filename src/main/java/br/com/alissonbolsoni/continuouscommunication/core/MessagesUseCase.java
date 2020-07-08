package br.com.alissonbolsoni.continuouscommunication.core;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.NotFoundException;

public interface MessagesUseCase {
    public Message getMessageById(String id) throws NotFoundException;
}
