package br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MessagesMapper {

    public static Page<MessageTable> pageMessageToPageMessageTable(final Page<Message> messages){
        return messages.map(MessagesMapper::messageToMessageTable);
    }

    public static MessageTable messageToMessageTable(Message message) {
        return new MessageTable(
                message.getMessage(),
                MessageTypesMapper.messageTypeToMessageTypeTable(message.getMessageType()),
                message.getSendTime(),
                message.getStatus().getStatus()
        );
    }

    public static MessageTable messageToUpdateMessageTable(Message message) {
        return new MessageTable(
                message.getMessageId(),
                message.getMessage(),
                MessageTypesMapper.messageTypeToMessageTypeTable(message.getMessageType()),
                message.getSendTime(),
                message.getStatus().getStatus()
        );
    }

    public static List<Message> pageMessageTableToPageMessage(final List<MessageTable> messages){
        return messages
                .stream()
                .parallel()
                .map(MessagesMapper::messageTableToMessage).collect(Collectors.toList());
    }

    public static Message messageTableToMessage(MessageTable message) {
        return new Message(
                message.getMessageId(),
                message.getMessage(),
                MessageTypesMapper.messageTypeTableToMessageType(message.getMessageType()),
                message.getSendTime(),
                MessageStatus.findByValue(message.getStatus()),
                null
        );
    }

}
