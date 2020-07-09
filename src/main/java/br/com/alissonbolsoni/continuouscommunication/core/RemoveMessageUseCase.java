package br.com.alissonbolsoni.continuouscommunication.core;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.RemoveFailException;

import java.util.UUID;

public interface RemoveMessageUseCase {
    public Message removeMessageById(UUID id) throws RemoveFailException;
}
