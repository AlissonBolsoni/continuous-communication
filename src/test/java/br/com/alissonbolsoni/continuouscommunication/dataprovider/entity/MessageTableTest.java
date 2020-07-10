package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MessageTableTest {

    private final UUID messageId = UUID.randomUUID();
    private final String messageText = "message";
    private final MessageTypeTable messageType = new MessageTypeTable(1, "email");
    private final Date sendTime = new Date();
    private final Integer status = MessageStatus.WAITING.getStatus();
    private final List<MessageDestiny> destinies = new ArrayList<>();

    @Test
    void testCreateFullConstructor(){
        MessageTable message = new MessageTable(
                messageId, messageText, messageType, sendTime, status
        );

        assertEquals(messageId.toString(), message.getMessageId().toString());
        assertEquals(messageText, message.getMessage());
        assertEquals(messageType, message.getMessageType());
        assertEquals(sendTime, message.getSendTime());
        assertEquals(status, message.getStatus());

    }

    @Test
    void testCreate(){
        MessageTable message = new MessageTable(
                messageText, messageType, sendTime, status
        );

        assertEquals(messageText, message.getMessage());
        assertEquals(messageType, message.getMessageType());
        assertEquals(sendTime, message.getSendTime());
        assertEquals(status, message.getStatus());

    }

    @Test
    void testNotNull(){
        assertNotNull(new MessageTable());
    }


}