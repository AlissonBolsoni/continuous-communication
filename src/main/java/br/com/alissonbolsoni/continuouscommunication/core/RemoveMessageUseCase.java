package br.com.alissonbolsoni.continuouscommunication.core;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.RemoveFailException;

public interface RemoveMessageUseCase {
    public Message removeMessageById(String id) throws RemoveFailException;
}
