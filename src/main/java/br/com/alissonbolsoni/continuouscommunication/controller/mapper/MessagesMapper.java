package br.com.alissonbolsoni.continuouscommunication.controller.mapper;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessagesMapper {

    public static MessageDto messageEntityToMessageDto(Message message) {
        return new MessageDto(
                message.getMessageId(),
                message.getMessage(),
                MessageDestinyMapper.toDto(message.getDestinies()),
                message.getMessageType().getType(),
                message.getSendTime(),
                message.getStatus().name());
    }

    public static Message messageDtoToMessageEntity(MessageDto messageDto) {
        return new Message(
                messageDto.getUuid(),
                messageDto.getMessage(),
                new MessageType(null, messageDto.getMessageType()),
                messageDto.getSendTime(),
                MessageStatus.WAITING,
                MessageDestinyMapper.toEntity(messageDto.getDestiny())
        );
    }

}
