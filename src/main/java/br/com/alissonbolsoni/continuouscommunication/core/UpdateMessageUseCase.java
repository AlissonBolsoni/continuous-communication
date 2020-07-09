package br.com.alissonbolsoni.continuouscommunication.core;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.UpdateFailException;


public interface UpdateMessageUseCase {

    public void updateMessage(Message message) throws UpdateFailException;
}
