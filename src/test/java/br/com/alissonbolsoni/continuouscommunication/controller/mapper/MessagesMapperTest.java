package br.com.alissonbolsoni.continuouscommunication.controller.mapper;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessagesMapperTest {

    private final String uuid = "id";
    private final String message = "message";
    private final List<String> destiny = new ArrayList<>();
    private final String messageType = "email";
    private final Date sendMessage = new Date();
    private final String messageStatus = "2";

    @Test
    void testMapperMessageDto() {
        destiny.add("destiny");
        MessageDto messageDto = new MessageDto(uuid, message, destiny, messageType, sendMessage, messageStatus);

        Message message = MessagesMapper.messageDtoToMessageEntity(messageDto);

        assertEquals(uuid, messageDto.getUuid());
        assertEquals(this.message, messageDto.getMessage());
        assertEquals(destiny, messageDto.getDestiny());
        assertEquals(messageType, messageDto.getMessageType());
        assertEquals(sendMessage, messageDto.getSendTime());
        assertEquals(messageStatus, messageDto.getMessageStatus());

        MessageDto dto = MessagesMapper.messageEntityToMessageDto(message);
        assertEquals(messageDto.getUuid(), dto.getUuid());
        assertEquals(messageDto.getMessage(), dto.getMessage());
        assertEquals(messageDto.getDestiny(), dto.getDestiny());
        assertEquals(messageDto.getMessageType(), dto.getMessageType());
        assertEquals(messageDto.getSendTime(), dto.getSendTime());
    }


}