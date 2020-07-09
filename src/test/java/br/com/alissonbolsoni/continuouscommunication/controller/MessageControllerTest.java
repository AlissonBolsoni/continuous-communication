package br.com.alissonbolsoni.continuouscommunication.controller;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.MessagesUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.RegisterMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.RemoveMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.core.exception.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageControllerTest {

    private final MessagesUseCase messagesUseCase = mock(MessagesUseCase.class);
    private final RegisterMessageUseCase registerMessageUseCase = mock(RegisterMessageUseCase.class);
    private final RemoveMessageUseCase removeMessageUseCase = mock(RemoveMessageUseCase.class);

    private final Message message = new Message(UUID.randomUUID(), "Text", new MessageType(1, "email"), getDateFuture(), MessageStatus.WAITING, new ArrayList<>());
    private final MessageDto messageDto = new MessageDto("", "Text", new ArrayList(), "email", getDateFuture(), "");

    private Date getDateFuture() {
        Calendar instance = Calendar.getInstance();
        instance.set(2020, Calendar.JULY, 15);
        return instance.getTime();
    }

    @Test
    void testRegisterMessageWithSuccess() throws TypeNotExistsException, RegisterFailException, DateWrongException {
        when(registerMessageUseCase.registerMessage(any())).thenReturn(message);

        MessageController messageController = new MessageController(messagesUseCase, registerMessageUseCase, removeMessageUseCase);

        ResponseEntity<MessageDto> registerMessage = messageController.registerMessage(messageDto);
        assertEquals(HttpStatus.OK, registerMessage.getStatusCode());
        assertEquals(message.getMessageId(), registerMessage.getBody().getUuid());
        assertEquals(message.getMessage(), registerMessage.getBody().getMessage());
        assertEquals(message.getMessageType().getType(), registerMessage.getBody().getMessageType());
    }

    @Test
    void testRegisterMessageThrowingTypeNotExistsException() throws TypeNotExistsException, RegisterFailException, DateWrongException {
        when(registerMessageUseCase.registerMessage(any())).thenThrow(TypeNotExistsException.class);

        MessageController messageController = new MessageController(messagesUseCase, registerMessageUseCase, removeMessageUseCase);

        ResponseEntity<MessageDto> registerMessage = messageController.registerMessage(messageDto);
        assertEquals(HttpStatus.NOT_FOUND, registerMessage.getStatusCode());
    }

    @Test
    void testRegisterMessageThrowingRegisterFailException() throws TypeNotExistsException, RegisterFailException, DateWrongException {
        when(registerMessageUseCase.registerMessage(any())).thenThrow(RegisterFailException.class);

        MessageController messageController = new MessageController(messagesUseCase, registerMessageUseCase, removeMessageUseCase);

        ResponseEntity<MessageDto> registerMessage = messageController.registerMessage(messageDto);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, registerMessage.getStatusCode());
    }

    @Test
    void testRegisterMessageThrowingDateWrongException() throws TypeNotExistsException, RegisterFailException, DateWrongException {
        when(registerMessageUseCase.registerMessage(any())).thenThrow(DateWrongException.class);

        MessageController messageController = new MessageController(messagesUseCase, registerMessageUseCase, removeMessageUseCase);

        ResponseEntity<MessageDto> registerMessage = messageController.registerMessage(messageDto);
        assertEquals(HttpStatus.EXPECTATION_FAILED, registerMessage.getStatusCode());
    }

    @Test
    void testGetMessageByIdWithSuccess() throws NotFoundException {
        when(messagesUseCase.getMessageById(any())).thenReturn(message);
        MessageController messageController = new MessageController(messagesUseCase, registerMessageUseCase, removeMessageUseCase);

        ResponseEntity<MessageDto> messageReturned = messageController.getMessageById("ID");
        assertEquals(HttpStatus.OK, messageReturned.getStatusCode());
        assertEquals(message.getMessageId(), messageReturned.getBody().getUuid());
        assertEquals(message.getMessage(), messageReturned.getBody().getMessage());
        assertEquals(message.getMessageType().getType(), messageReturned.getBody().getMessageType());
    }

    @Test
    void testGetMessageByIdThrowingNotFoundException() throws NotFoundException {
        when(messagesUseCase.getMessageById(any())).thenThrow(NotFoundException.class);

        MessageController messageController = new MessageController(messagesUseCase, registerMessageUseCase, removeMessageUseCase);

        ResponseEntity<MessageDto> messageReturned = messageController.getMessageById("ID");
        assertEquals(HttpStatus.NOT_FOUND, messageReturned.getStatusCode());
    }

    @Test
    void testRemoveMessageByIdWithSuccess() throws RemoveFailException {
        when(removeMessageUseCase.removeMessageById(any())).thenReturn(message);
        MessageController messageController = new MessageController(messagesUseCase, registerMessageUseCase, removeMessageUseCase);

        ResponseEntity<MessageDto> messageReturned = messageController.removeMessageById("ID");
        assertEquals(HttpStatus.OK, messageReturned.getStatusCode());
        assertEquals(message.getMessageId(), messageReturned.getBody().getUuid());
        assertEquals(message.getMessage(), messageReturned.getBody().getMessage());
        assertEquals(message.getMessageType().getType(), messageReturned.getBody().getMessageType());
    }

    @Test
    void testRemoveMessageByIdThrowingNotFoundException() throws RemoveFailException {
        when(removeMessageUseCase.removeMessageById(any())).thenThrow(RemoveFailException.class);

        MessageController messageController = new MessageController(messagesUseCase, registerMessageUseCase, removeMessageUseCase);

        ResponseEntity<MessageDto> messageReturned = messageController.removeMessageById("ID");
        assertEquals(HttpStatus.NOT_FOUND, messageReturned.getStatusCode());
    }

}