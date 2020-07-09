package br.com.alissonbolsoni.continuouscommunication.core;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.NotFoundException;

import java.util.UUID;

public interface MessagesUseCase {
    public Message getMessageById(UUID id) throws NotFoundException;
}
