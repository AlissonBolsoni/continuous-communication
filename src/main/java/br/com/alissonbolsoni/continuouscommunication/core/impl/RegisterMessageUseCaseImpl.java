package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.RegisterMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageDestinyRepository;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageTypeRepository;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import br.com.alissonbolsoni.continuouscommunication.exception.DateWrongException;
import br.com.alissonbolsoni.continuouscommunication.exception.TypeNotExistsException;
import br.com.alissonbolsoni.continuouscommunication.mapper.MessageDestinyMapper;
import br.com.alissonbolsoni.continuouscommunication.mapper.MessagesMapper;
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
    public Message RegisterMessage(MessageDto messageDto) throws TypeNotExistsException, DateWrongException, Exception {

        MessageType messageType = this.messageTypeRepository.findByType(messageDto.getMessageType());
        if (messageType == null) throw new TypeNotExistsException("Tipo de mensagem solicitado não existe");

        Message message = MessagesMapper.messageDtoToMessageEntity(messageDto);
        message.setMessageType(messageType);

        if (message.getSendTime().before(new Date())) throw new DateWrongException("Data para envio inválida");

        try {
            MessageTable messageTable = messageRepository.saveMessage(MessagesMapper.messageToMessageTable(message));
            List<MessageDestinyTable> messageDestinyTables = messageDestinyRepository.saveMessageDestinies(MessageDestinyMapper.toTable(message.getDestinies(), messageTable.getMessageId()));

            Message messageToReturn = MessagesMapper.messageTableToMessage(messageTable);
            messageToReturn.setDestinies(MessageDestinyMapper.tableToEntity(messageDestinyTables));
            messageToReturn.setMessageType(messageType);
            return messageToReturn;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao registrar a mensagem.");
        }
    }
}
