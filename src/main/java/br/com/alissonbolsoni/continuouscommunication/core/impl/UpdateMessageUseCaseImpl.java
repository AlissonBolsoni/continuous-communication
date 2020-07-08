package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.UpdateMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateMessageUseCaseImpl implements UpdateMessageUseCase {

    private final MessageRepository messageRepository;

    @Autowired
    public UpdateMessageUseCaseImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Boolean updateMessage(Message message) {
        try {
            message.setStatus(MessageStatus.SENT);
            return messageRepository.updateMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
