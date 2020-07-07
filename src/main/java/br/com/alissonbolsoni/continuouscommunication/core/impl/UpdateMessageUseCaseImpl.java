package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.RegisterMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.UpdateMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.contants.RoutingKeys;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.core.exception.DateWrongException;
import br.com.alissonbolsoni.continuouscommunication.core.exception.RegisterFailException;
import br.com.alissonbolsoni.continuouscommunication.core.exception.TypeNotExistsException;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageDestinyRepository;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageTypeRepository;
import br.com.alissonbolsoni.continuouscommunication.core.services.AmqpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UpdateMessageUseCaseImpl implements UpdateMessageUseCase {

    private final MessageRepository messageRepository;

    @Autowired
    public UpdateMessageUseCaseImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Boolean UpdateMessage(Message message) {
        try {
            message.setStatus(MessageStatus.SENT);
            return messageRepository.updateMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
