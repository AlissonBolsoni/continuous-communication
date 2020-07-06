package br.com.alissonbolsoni.continuouscommunication.controller.mapper;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageDestinyMapper {

    public static List<String> toDto(List<MessageDestiny> messages){
        return StreamSupport.stream(messages.spliterator(), false)
                .map( msg ->
                        toDto(msg)
                ).collect(Collectors.toList());
    }

    public static String toDto(MessageDestiny message){
        return message.getDestiny();
    }

    public static List<MessageDestiny> toEntity(List<String> messages){
        return StreamSupport.stream(messages.spliterator(), false)
                .map( msg ->
                        toEntity(msg)
                ).collect(Collectors.toList());
    }

    public static MessageDestiny toEntity(String message){
        return new MessageDestiny(
                null,
                message,
                null
        );
    }

}
