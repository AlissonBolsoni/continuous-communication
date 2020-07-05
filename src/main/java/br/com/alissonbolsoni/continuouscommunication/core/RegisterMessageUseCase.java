package br.com.alissonbolsoni.continuouscommunication.core;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.exception.DateWrongException;
import br.com.alissonbolsoni.continuouscommunication.exception.TypeNotExistsException;


public interface RegisterMessageUseCase {

    public Message RegisterMessage(MessageDto messageDto) throws TypeNotExistsException, DateWrongException, Exception;
}
