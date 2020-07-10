package br.com.alissonbolsoni.continuouscommunication.controller.mapper;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageRequestDto;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MessagesMapperTest {

    private final String uuid = UUID.randomUUID().toString();
    private final String message = "message";
    private final List<String> destiny = new ArrayList<>();
    private final String messageType = "email";
    private final Date sendMessage = new Date();
    private final String messageStatus = "2";

    @Test
    void testMapperMessageDto() {
        destiny.add("destiny");
        MessageRequestDto messageDto = new MessageRequestDto(message, destiny, messageType, sendMessage);

        Message message = MessagesMapper.messageDtoToMessageEntity(messageDto);
        message.setMessageId(UUID.randomUUID());
        message.setStatus(MessageStatus.WAITING);

        assertEquals(this.message, messageDto.getMessage());
        assertEquals(destiny, messageDto.getDestiny());
        assertEquals(messageType, messageDto.getMessageType());
        assertEquals(sendMessage, messageDto.getSendTime());

        MessageDto dto = MessagesMapper.messageEntityToMessageDto(message);
        assertEquals(messageDto.getMessage(), dto.getMessage());
        assertEquals(messageDto.getDestiny(), dto.getDestiny());
        assertEquals(messageDto.getMessageType(), dto.getMessageType());
        assertEquals(messageDto.getSendTime(), dto.getSendTime());
    }


}