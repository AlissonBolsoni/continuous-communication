package br.com.alissonbolsoni.continuouscommunication.core.entity;

import javax.persistence.*;

public class MessageDestiny {
    private Integer messageDestinyId;
    private String destiny;
    private String messageId;

    public MessageDestiny() {
    }

    public MessageDestiny(Integer messageDestinyId, String destiny) {
        this.messageDestinyId = messageDestinyId;
        this.destiny = destiny;
    }

    public Integer getMessageDestinyId() {
        return messageDestinyId;
    }

    public void setMessageDestinyId(Integer messageDestinyId) {
        this.messageDestinyId = messageDestinyId;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
