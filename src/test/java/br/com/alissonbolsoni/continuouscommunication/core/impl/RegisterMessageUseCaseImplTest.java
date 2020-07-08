package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.RegisterMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
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
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterMessageUseCaseImplTest {

    private final MessageTypeRepository messageTypeRepository = mock(MessageTypeRepository.class);
    private final MessageRepository messageRepository = mock(MessageRepository.class);
    private final MessageDestinyRepository messageDestinyRepository = mock(MessageDestinyRepository.class);
    private final AmqpSender amqpSender = mock(AmqpSender.class);

    private final static String MESSAGE_ID = "MESSAGE_ID";
    private final static String MESSAGE = "MESSAGE";
    private final List<MessageDestiny> list = new ArrayList();
    private final MessageType messageType = new MessageType(1, "type");

    private final Message message = new Message(
            MESSAGE_ID,
            MESSAGE,
            messageType,
            getDateFuture(),
            MessageStatus.WAITING,
            list);

    private Date getDateFuture(){
        Calendar instance = Calendar.getInstance();
        instance.set(2020, Calendar.JULY, 15);
        return instance.getTime();
    }

    private Date getDatePassed(){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH, -1);
        return instance.getTime();
    }

    @Test
    void testRegisterMessageWithMessageTypeNullThisShouldThrowException() throws TypeNotExistsException, DateWrongException, RegisterFailException {
        when(messageTypeRepository.findByType(anyString())).thenReturn(null);

        RegisterMessageUseCase useCase = new RegisterMessageUseCaseImpl(
                messageTypeRepository,
                messageRepository,
                messageDestinyRepository,
                amqpSender);

        list.add(new MessageDestiny(null, "Alisson"));
        message.setDestinies(list);

        assertThrows(TypeNotExistsException.class, () ->
                useCase.registerMessage(this.message)
        );
    }

    @Test
    void testRegisterMessageDatePassedThisShouldThrowException() throws TypeNotExistsException, DateWrongException, RegisterFailException {
        when(messageTypeRepository.findByType(anyString())).thenReturn(messageType);

        RegisterMessageUseCase useCase = new RegisterMessageUseCaseImpl(
                messageTypeRepository,
                messageRepository,
                messageDestinyRepository,
                amqpSender);

        list.add(new MessageDestiny(null, "Alisson"));
        message.setDestinies(list);
        message.setSendTime(getDatePassed());

        assertThrows(DateWrongException.class, () ->
                useCase.registerMessage(this.message)
        );
    }

    @Test
    void testRegisterMessageSaveMessageThrowException() throws Exception {
        when(messageTypeRepository.findByType(anyString())).thenReturn(messageType);
        when(messageRepository.saveMessage(message)).thenThrow(Exception.class);

        RegisterMessageUseCase useCase = new RegisterMessageUseCaseImpl(
                messageTypeRepository,
                messageRepository,
                messageDestinyRepository,
                amqpSender);

        list.add(new MessageDestiny(null, "Alisson"));
        message.setDestinies(list);

        assertThrows(RegisterFailException.class, () ->
                useCase.registerMessage(this.message)
        );
    }

    @Test
    void testRegisterMessageSaveMessageDestiniesThrowException() throws Exception {
        when(messageTypeRepository.findByType(anyString())).thenReturn(messageType);
        when(messageRepository.saveMessage(message)).thenReturn(message);
        when(messageDestinyRepository
                .saveMessageDestinies(message.getDestinies(), message.getMessageId())
            ).thenThrow(Exception.class);

        RegisterMessageUseCase useCase = new RegisterMessageUseCaseImpl(
                messageTypeRepository,
                messageRepository,
                messageDestinyRepository,
                amqpSender);

        list.add(new MessageDestiny(null, "Alisson"));
        message.setDestinies(list);

        assertThrows(RegisterFailException.class, () ->
                useCase.registerMessage(this.message)
        );
    }

    @Test
    void testRegisterMessageWithSuccessAndSendToAmqp() throws Exception {
        list.add(new MessageDestiny(null, "Alisson"));
        when(messageTypeRepository.findByType(anyString())).thenReturn(new MessageType(2, "email"));
        when(messageRepository.saveMessage(message)).thenReturn(message);
        when(messageDestinyRepository
                .saveMessageDestinies(message.getDestinies(), message.getMessageId())
        ).thenReturn(list);
        Mockito.doNothing().when(amqpSender).sendMessage(anyString(), any());

        RegisterMessageUseCase useCase = new RegisterMessageUseCaseImpl(
                messageTypeRepository,
                messageRepository,
                messageDestinyRepository,
                amqpSender);

        message.setDestinies(list);

        Message message = useCase.registerMessage(this.message);
        Mockito.verify(amqpSender).sendMessage(anyString(), any());
    }

    @Test
    void testRegisterMessageWithSuccess() throws Exception {
        list.add(new MessageDestiny(null, "Alisson"));
        when(messageTypeRepository.findByType(anyString())).thenReturn(messageType);
        when(messageRepository.saveMessage(message)).thenReturn(message);
        when(messageDestinyRepository
                .saveMessageDestinies(message.getDestinies(), message.getMessageId())
        ).thenReturn(list);
        Mockito.doNothing().when(amqpSender).sendMessage(anyString(), any());

        RegisterMessageUseCase useCase = new RegisterMessageUseCaseImpl(
                messageTypeRepository,
                messageRepository,
                messageDestinyRepository,
                amqpSender);

        message.setDestinies(list);

        Message message = useCase.registerMessage(this.message);
        assertEquals(this.message, message);
    }


}