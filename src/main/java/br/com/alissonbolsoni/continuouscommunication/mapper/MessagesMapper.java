package br.com.alissonbolsoni.continuouscommunication.mapper;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
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
                null,
                messageDto.getSendTime(),
                MessageStatus.WAITING,
                MessageDestinyMapper.toEntity(messageDto.getDestiny())
        );
    }

    public static Page<MessageTable> pageMessageToPageMessageTable(final Page<Message> messages){
        return messages.map(MessagesMapper::messageToMessageTable);
    }

    public static MessageTable messageToMessageTable(Message message) {
        return new MessageTable(
                message.getMessage(),
                message.getMessageType().getMessageTypeId(),
                message.getSendTime(),
                message.getStatus().getStatus()
        );
    }

    public static Page<Message> pageMessageTableToPageMessage(final Page<MessageTable> messages){
        return messages.map(MessagesMapper::messageTableToMessage);
    }

    public static Message messageTableToMessage(MessageTable message) {
        return new Message(
                message.getMessageId(),
                message.getMessage(),
                null,
                message.getSendTime(),
                MessageStatus.findByValue(message.getStatus()),
                null
        );
    }

}
