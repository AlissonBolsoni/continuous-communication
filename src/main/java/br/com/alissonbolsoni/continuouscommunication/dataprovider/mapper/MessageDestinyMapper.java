package br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageDestinyMapper {

    public static List<MessageDestinyTable> toTable(List<MessageDestiny> messages, String messageId){
        return StreamSupport.stream(messages.spliterator(), false)
                .map( msg ->
                        toTable(msg, messageId)
                ).collect(Collectors.toList());
    }

    public static MessageDestinyTable toTable(MessageDestiny message,  String messageId){
        return new MessageDestinyTable(
                message.getMessageDestinyId(),
                message.getDestiny(),
                messageId
        );
    }

    public static List<MessageDestiny> tableToEntity(List<MessageDestinyTable> messages){
        return StreamSupport.stream(messages.spliterator(), false)
                .map( msg ->
                        tableToEntity(msg)
                ).collect(Collectors.toList());
    }

    public static MessageDestiny tableToEntity(MessageDestinyTable message){
        return new MessageDestiny(
                message.getMessageDestinyId(),
                message.getDestiny()
        );
    }
}
