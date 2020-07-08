package br.com.alissonbolsoni.continuouscommunication.core.entity;

import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    private final String messageId = "ID";
    private final String messageText = "message";
    private final MessageType messageType = new MessageType(1, "email");
    private final Date sendTime = new Date();
    private final MessageStatus status = MessageStatus.WAITING;
    private final List<MessageDestiny> destinies = new ArrayList<>();
    private final Message base = new Message(messageId, messageText, messageType, sendTime, status, destinies);
    
    @Test
    void testCreateAndEditMessage(){
        Message message = new Message();
        message.setMessageId(messageId);
        message.setMessage(messageText);
        message.setMessageType(messageType);
        message.setSendTime(sendTime);
        message.setStatus(status);
        message.setDestinies(destinies);
        
        assertEquals(messageId, message.getMessageId());
        assertEquals(messageText, message.getMessage());
        assertEquals(messageType, message.getMessageType());
        assertEquals(sendTime, message.getSendTime());
        assertEquals(status, message.getStatus());
        assertEquals(destinies, message.getDestinies());

    }

    @Test
    void testCreateMessage(){
        Message message = new Message(messageId, messageText, messageType, sendTime, status, destinies);

        assertEquals(base.hashCode(), message.hashCode());
        assertEquals(base, message);

    }

}