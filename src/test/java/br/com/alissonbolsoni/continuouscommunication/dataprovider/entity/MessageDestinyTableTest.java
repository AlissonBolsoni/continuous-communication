package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import org.junit.jupiter.api.Test;

import javax.persistence.Column;

import static org.junit.jupiter.api.Assertions.*;

class MessageDestinyTableTest {
    private final Integer messageDestinyId = 1;
    private final String destiny = "type";
    private final String messageId = "id";

    @Test
    void testCreate(){
        MessageDestinyTable messageDestinyTable = new MessageDestinyTable(messageDestinyId, destiny, messageId);

        assertEquals(messageDestinyId, messageDestinyTable.getMessageDestinyId());
        assertEquals(destiny, messageDestinyTable.getDestiny());
        assertEquals(messageId, messageDestinyTable.getMessageId());
    }
}