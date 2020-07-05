package br.com.alissonbolsoni.continuouscommunication.core.entity;

public class MessageType {
    private Integer messageTypeId;
    private String type;

    public MessageType() {
    }

    public MessageType(Integer messageTypeId, String type) {
        this.messageTypeId = messageTypeId;
        this.type = type;
    }

    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
