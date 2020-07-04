package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(nullable = true)
    private String messageId;

    @Column(nullable = false)
    private String message;

    public Message() {
        this(null);
    }

    public Message(String message) {
        this.messageId = UUID.randomUUID().toString();
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
