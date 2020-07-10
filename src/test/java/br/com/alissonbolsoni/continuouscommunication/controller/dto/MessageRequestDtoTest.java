package br.com.alissonbolsoni.continuouscommunication.controller.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageRequestDtoTest {

    private final String message = "message";
    private final List<String> destiny = new ArrayList<>();
    private final String messageType = "email";

    @Test
    void testCreateWithFullConstructor(){
        destiny.add("destiny");
        MessageRequestDto messageDto = new MessageRequestDto(message, destiny, messageType, null);

        assertEquals(message, messageDto.getMessage());
        assertEquals(destiny, messageDto.getDestiny());
        assertEquals(messageType, messageDto.getMessageType());
        assertNull(messageDto.getSendTime());
    }
}