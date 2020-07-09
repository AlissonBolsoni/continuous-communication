package br.com.alissonbolsoni.continuouscommunication.controller.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageDtoTest {

    private final String uuid = "id";
    private final String message = "message";
    private final List<String> destiny = new ArrayList<>();
    private final String messageType = "email";
    private final String messageStatus = "2";

    @Test
    void testCreateOnlyWithMessage(){
        MessageDto messageDto = new MessageDto(message);

        assertEquals(message, messageDto.getMessage());
    }

    @Test
    void testCreateWithFullConstructor(){
        destiny.add("destiny");
        MessageDto messageDto = new MessageDto(uuid, message, destiny, messageType, null, messageStatus);

        assertEquals(uuid, messageDto.getUuid());
        assertEquals(message, messageDto.getMessage());
        assertEquals(destiny, messageDto.getDestiny());
        assertEquals(messageType, messageDto.getMessageType());
        assertNull(messageDto.getSendTime());
        assertEquals(messageStatus, messageDto.getMessageStatus());
    }

}