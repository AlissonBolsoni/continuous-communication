package br.com.alissonbolsoni.continuouscommunication.core.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageDestinyTest {

    private final Integer messageDestinyId = 1;
    private final String destiny = "destiny";
    private final String messageId = "ID";
    private final MessageDestiny base = new MessageDestiny(messageDestinyId, destiny, messageId);

    @Test
    void testCreateAndEditMessageDestiny(){
        MessageDestiny messageDestiny = new MessageDestiny();
        messageDestiny.setMessageDestinyId(messageDestinyId);
        messageDestiny.setDestiny(destiny);
        messageDestiny.setMessageId(messageId);

        assertEquals(messageDestinyId, messageDestiny.getMessageDestinyId());
        assertEquals(destiny, messageDestiny.getDestiny());
        assertEquals(messageId, messageDestiny.getMessageId());

    }

    @Test
    void testCreateMessageDestiny(){
        MessageDestiny messageDestiny = new MessageDestiny(messageDestinyId, destiny);
        messageDestiny.setMessageId(messageId);

        assertEquals(base.hashCode(), messageDestiny.hashCode());
        assertEquals(base, messageDestiny);

    }

}