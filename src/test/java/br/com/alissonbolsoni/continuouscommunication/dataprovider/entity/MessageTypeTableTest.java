package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import org.junit.jupiter.api.Test;

import javax.persistence.Column;

import static org.junit.jupiter.api.Assertions.*;

class MessageTypeTableTest {

    private final Integer messageTypeId = 1;
    private final String type = "type";

    @Test
    void testCreate(){
        MessageTypeTable messageTypeTable = new MessageTypeTable(messageTypeId, type);

        assertEquals(messageTypeId, messageTypeTable.getMessageTypeId());
        assertEquals(type, messageTypeTable.getType());
    }

    @Test
    void testNotNull(){
        assertNotNull(new MessageTypeTable());
    }

}