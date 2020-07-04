package br.com.alissonbolsoni.continuouscommunication.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    public MessageDto(String message) {
        this(null, message);
    }

    public MessageDto(String uuid, String message) {
        this.uuid = uuid;
        this.message = message;
    }

    private String uuid;
    private String message;



}
