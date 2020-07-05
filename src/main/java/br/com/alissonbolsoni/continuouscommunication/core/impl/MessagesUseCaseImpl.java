package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.MessagesUseCase;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagesUseCaseImpl implements MessagesUseCase {

    @Autowired
    public MessagesUseCaseImpl() {
    }

    @Override
    public MessageTable getAllMessages() {
        return null;
    }
}
