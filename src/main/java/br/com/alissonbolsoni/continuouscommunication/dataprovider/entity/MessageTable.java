package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class MessageTable {
    @Id
    @Column(nullable = true)
    private final String messageId;

    @Column(nullable = false)
    private final String message;

    @ManyToOne
    @JoinColumn(name = "message_type_id")
    private final MessageTypeTable messageType;

    @Column(nullable = false)
    private final Date sendTime;

    @Column(nullable = false)
    private final Integer status;

    public MessageTable(String message, MessageTypeTable messageType, Date sendTime, Integer status) {
        this.messageId = UUID.randomUUID().toString();
        this.message = message;
        this.messageType = messageType;
        this.sendTime = sendTime;
        this.status = status;
    }

    public MessageTable(String messageId, String message, MessageTypeTable messageType, Date sendTime, Integer status) {
        this.messageId = messageId;
        this.message = message;
        this.messageType = messageType;
        this.sendTime = sendTime;
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public MessageTypeTable getMessageType() {
        return messageType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public Integer getStatus() {
        return status;
    }
}
