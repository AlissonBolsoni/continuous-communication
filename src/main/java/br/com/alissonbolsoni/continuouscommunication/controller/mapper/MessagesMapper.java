package br.com.alissonbolsoni.continuouscommunication.controller.mapper;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageRequestDto;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;

public class MessagesMapper {

    public static MessageDto messageEntityToMessageDto(final Message message) {
        return new MessageDto(
                message.getMessageId().toString(),
                message.getMessage(),
                MessageDestinyMapper.toDto(message.getDestinies()),
                message.getMessageType().getType(),
                message.getSendTime(),
                message.getStatus().name());
    }

    public static Message messageDtoToMessageEntity(final MessageRequestDto messageDto) {
        return new Message(
                null,
                messageDto.getMessage(),
                new MessageType(null, messageDto.getMessageType()),
                messageDto.getSendTime(),
                MessageStatus.WAITING,
                MessageDestinyMapper.toEntity(messageDto.getDestiny())
        );
    }

}
