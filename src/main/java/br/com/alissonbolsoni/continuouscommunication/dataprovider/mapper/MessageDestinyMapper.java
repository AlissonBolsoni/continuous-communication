package br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageDestinyMapper {

    public static List<MessageDestinyTable> toTable(final List<MessageDestiny> messages, final UUID messageId){
        return messages.stream()
                .map( msg ->
                        toTable(msg, messageId)
                ).collect(Collectors.toList());
    }

    public static MessageDestinyTable toTable(final MessageDestiny message,  final UUID messageId){
        return new MessageDestinyTable(
                message.getMessageDestinyId(),
                message.getDestiny(),
                messageId.toString()
        );
    }

    public static List<MessageDestiny> tableToEntity(final List<MessageDestinyTable> messages){
        return messages.stream()
                .map( msg ->
                        tableToEntity(msg)
                ).collect(Collectors.toList());
    }

    public static MessageDestiny tableToEntity(final MessageDestinyTable message){
        return new MessageDestiny(
                message.getMessageDestinyId(),
                message.getDestiny()
        );
    }
}
