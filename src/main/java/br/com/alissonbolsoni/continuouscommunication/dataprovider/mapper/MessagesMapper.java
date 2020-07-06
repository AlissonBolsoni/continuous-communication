package br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import org.springframework.data.domain.Page;

public class MessagesMapper {

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
