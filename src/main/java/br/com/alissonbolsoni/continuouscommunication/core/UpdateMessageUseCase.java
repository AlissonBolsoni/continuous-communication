package br.com.alissonbolsoni.continuouscommunication.core;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;


public interface UpdateMessageUseCase {

    public Boolean UpdateMessage(Message message);
}