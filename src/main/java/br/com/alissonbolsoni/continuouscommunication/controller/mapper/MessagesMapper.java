package br.com.alissonbolsoni.continuouscommunication.controller.mapper;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import org.springframework.data.domain.Page;

public class MessagesMapper {

    public static Page<MessageDto> pageMessageToPageMessageDto(final Page<Message> messages){
        return messages.map(MessagesMapper::messageEntityToMessageDto);
    }

    public static MessageDto messageEntityToMessageDto(Message message){
        return new MessageDto(
                message.getMessageId(),
                message.getMessage(),
                MessageDestinyMapper.toDto(message.getDestinies()),
                message.getMessageType().getType(),
                message.getSendTime());
    }

    public static Page<Message> pageMessageDtoToPageMessageEntity(final Page<MessageDto> messagesDto){
        return messagesDto.map(MessagesMapper::messageDtoToMessageEntity);
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
