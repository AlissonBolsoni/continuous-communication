package br.com.alissonbolsoni.continuouscommunication.core;

import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import br.com.alissonbolsoni.continuouscommunication.exception.DateWrongException;
import br.com.alissonbolsoni.continuouscommunication.exception.TypeNotExistsException;

public interface MessagesUseCase {
    public MessageTable getAllMessages() throws TypeNotExistsException, DateWrongException;
}
