package br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTypeTable;

public class MessageTypesMapper {

    public static MessageTypeTable messageTypeToMessageTypeTable(MessageType messageType) {
        return new MessageTypeTable(
                messageType.getMessageTypeId(),
                messageType.getType()
        );
    }

    public static MessageType messageTypeTableToMessageType(MessageTypeTable messageTypeTable) {
        return new MessageType(
                messageTypeTable.getMessageTypeId(),
                messageTypeTable.getType()
        );
    }

}
