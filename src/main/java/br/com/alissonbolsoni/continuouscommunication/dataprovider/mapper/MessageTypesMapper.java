package br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTypeTable;

public class MessageTypesMapper {

    public static MessageTypeTable messageTypeToMessageTypeTable(final MessageType messageType) {
        return new MessageTypeTable(
                messageType.getMessageTypeId(),
                messageType.getType()
        );
    }

    public static MessageType messageTypeTableToMessageType(final MessageTypeTable messageTypeTable) {
        if (messageTypeTable == null) return null;

        return new MessageType(
                messageTypeTable.getMessageTypeId(),
                messageTypeTable.getType()
        );
    }

}
