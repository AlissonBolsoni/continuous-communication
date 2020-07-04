package br.com.alissonbolsoni.continuouscommunication.controller.mapper;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.Message;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageMapper {

    public static List<MessageDto> toDto(List<Message> messages){
        return StreamSupport.stream(messages.spliterator(), false)
                .map( msg ->
                        toDto(msg)
                ).collect(Collectors.toList());
    }

    public static MessageDto toDto(Message message){
        return new MessageDto(message.getMessageId(), message.getMessage());
    }
}
