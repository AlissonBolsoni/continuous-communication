package br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper;

import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;

public class MessagesMapper {

    public static MessageTable messageToMessageTable(final Message message) {
        return new MessageTable(
                message.getMessage(),
                MessageTypesMapper.messageTypeToMessageTypeTable(message.getMessageType()),
                message.getSendTime(),
                message.getStatus().getStatus()
        );
    }

    public static MessageTable messageToUpdateMessageTable(final Message message) {
        return new MessageTable(
                message.getMessageId(),
                message.getMessage(),
                MessageTypesMapper.messageTypeToMessageTypeTable(message.getMessageType()),
                message.getSendTime(),
                message.getStatus().getStatus()
        );
    }

    public static Message messageTableToMessage(final MessageTable message) {
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
