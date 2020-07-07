package br.com.alissonbolsoni.continuouscommunication.controller.dto;

import java.util.Date;
import java.util.List;

public class MessageDto {

    public MessageDto() {
    }

    public MessageDto(String message) {
        this.message = message;
    }

    public MessageDto(String uuid, String message, List<String> destiny, String messageType, Date sendTime, String messageStatus) {
        this.uuid = uuid;
        this.message = message;
        this.destiny = destiny;
        this.messageType = messageType;
        this.sendTime = sendTime;
        this.messageStatus = messageStatus;
    }

    private String uuid;
    private String message;
    private List<String> destiny;
    private String messageType;
    private Date sendTime;
    private String messageStatus;

    public String getUuid() {
        return uuid;
    }

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

    public String getMessageStatus() {
        return messageStatus;
    }
}
