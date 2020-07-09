package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.RegisterMessageUseCase;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegisterMessageUseCaseImpl implements RegisterMessageUseCase {

    private final MessageTypeRepository messageTypeRepository;
    private final MessageRepository messageRepository;
    private final MessageDestinyRepository messageDestinyRepository;
    private final AmqpSender amqpSender;
    private final ObjectMapper objectMapper;

    @Autowired
    public RegisterMessageUseCaseImpl(
            final MessageTypeRepository messageTypeRepository,
            final MessageRepository messageRepository,
            final MessageDestinyRepository messageDestinyRepository,
            final AmqpSender amqpSender,
            final ObjectMapper objectMapper) {
        this.messageTypeRepository = messageTypeRepository;
        this.messageRepository = messageRepository;
        this.messageDestinyRepository = messageDestinyRepository;
        this.amqpSender = amqpSender;
        this.objectMapper = objectMapper;
    }

    @Override
    public Message registerMessage(final Message message) throws TypeNotExistsException, DateWrongException, RegisterFailException {

        MessageType messageType = this.messageTypeRepository.findByType(message.getMessageType().getType());
        if (messageType == null) throw new TypeNotExistsException("Tipo de mensagem solicitado não existe");

        message.setMessageType(messageType);

        if (message.getSendTime().before(new Date())) throw new DateWrongException("Data para envio inválida");

        try {
            Message messageToReturn = messageRepository.saveMessage(message);
            List<MessageDestiny> messageDestiny = messageDestinyRepository.saveMessageDestinies(message.getDestinies(), messageToReturn.getMessageId());

            messageToReturn.setDestinies(messageDestiny);
            messageToReturn.setMessageType(messageType);

            if (messageType.getType().equals("email")){
                amqpSender.sendMessage(RoutingKeys.ROUTING_KEY_EMAIL, objectMapper.writeValueAsString(messageToReturn));
            }
            return messageToReturn;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RegisterFailException("Falha ao registrar a mensagem.");
        }
    }
}
