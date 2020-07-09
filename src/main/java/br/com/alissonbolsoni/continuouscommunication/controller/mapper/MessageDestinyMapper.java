package br.com.alissonbolsoni.continuouscommunication.controller.mapper;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageDestinyMapper {

    public static List<String> toDto(final List<MessageDestiny> messages){
        return messages.stream()
                .map( msg ->
                        toDto(msg)
                ).collect(Collectors.toList());
    }

    public static String toDto(final MessageDestiny message){
        return message.getDestiny();
    }

    public static List<MessageDestiny> toEntity(final List<String> messages){
        return messages.stream()
                .map( msg ->
                        toEntity(msg)
                ).collect(Collectors.toList());
    }

    public static MessageDestiny toEntity(final String message){
        return new MessageDestiny(
                null,
                message
        );
    }

}
