package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.RegisterMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.core.exception.DateWrongException;
import br.com.alissonbolsoni.continuouscommunication.core.exception.RegisterFailException;
import br.com.alissonbolsoni.continuouscommunication.core.exception.TypeNotExistsException;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageDestinyRepository;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegisterMessageUseCaseImpl implements RegisterMessageUseCase {

    private final MessageTypeRepository messageTypeRepository;
    private final MessageRepository messageRepository;
    private final MessageDestinyRepository messageDestinyRepository;

    @Autowired
    public RegisterMessageUseCaseImpl(MessageTypeRepository messageTypeRepository, MessageRepository messageRepository, MessageDestinyRepository messageDestinyRepository) {
        this.messageTypeRepository = messageTypeRepository;
        this.messageRepository = messageRepository;
        this.messageDestinyRepository = messageDestinyRepository;
    }

    @Override
    public Message RegisterMessage(Message message) throws TypeNotExistsException, DateWrongException, RegisterFailException {

        MessageType messageType = this.messageTypeRepository.findByType(message.getMessageType().getType());
        if (messageType == null) throw new TypeNotExistsException("Tipo de mensagem solicitado não existe");

        message.setMessageType(messageType);

        if (message.getSendTime().before(new Date())) throw new DateWrongException("Data para envio inválida");

        try {
            Message messageToReturn = messageRepository.saveMessage(message);
            List<MessageDestiny> messageDestiny = messageDestinyRepository.saveMessageDestinies(message.getDestinies(), messageToReturn.getMessageId());

            messageToReturn.setDestinies(messageDestiny);
            messageToReturn.setMessageType(messageType);
            return messageToReturn;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RegisterFailException("Falha ao registrar a mensagem.");
        }
    }
}
