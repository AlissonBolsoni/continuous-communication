package br.com.alissonbolsoni.continuouscommunication.core.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTypeTest {

    private final Integer messageTypeId = 1;
    private final String type = "type";
    private final MessageType base = new MessageType(messageTypeId, type);

    @Test
    void testCreateAndEditMessageType(){
        MessageType messageType = new MessageType();
        messageType.setMessageTypeId(messageTypeId);
        messageType.setType(type);

        assertEquals(messageTypeId, messageType.getMessageTypeId());
        assertEquals(type, messageType.getType());

    }

    @Test
    void testCreateMessageType(){
        MessageType messageDestiny = new MessageType(messageTypeId, type);

        assertEquals(base.hashCode(), messageDestiny.hashCode());
        assertEquals(base, messageDestiny);

    }

}