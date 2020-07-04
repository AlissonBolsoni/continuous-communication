package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.MessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageUseCaseImpl implements MessageUseCase {

    @Override
    public Message getAllMessages() {
        return null;
    }
}
