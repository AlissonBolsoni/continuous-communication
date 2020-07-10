package br.com.alissonbolsoni.continuouscommunication.controller.dto;

import java.util.Date;
import java.util.List;

public class MessageRequestDto {

    public MessageRequestDto(final String message, final List<String> destiny, final String messageType, final Date sendTime) { ;
        this.message = message;
        this.destiny = destiny;
        this.messageType = messageType;
        this.sendTime = sendTime;
    }

    private String message;
    private List<String> destiny;
    private String messageType;
    private Date sendTime;

    public String getMessage() {
        return message;
    }

    public List<String> getDestiny() {
        return destiny;
    }

    public String getMessageType() {
        return messageType;
    }

    public Date getSendTime() {
        return sendTime;
    }

}
