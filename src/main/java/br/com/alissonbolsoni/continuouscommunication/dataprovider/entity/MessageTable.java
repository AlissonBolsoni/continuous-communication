package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class MessageTable {
    @Id
    @Column(nullable = true)
    private String messageId;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "message_type_id")
    private MessageTypeTable messageType;

    @Column(nullable = false)
    private Date sendTime;

    @Column(nullable = false)
    private Integer status;

    public MessageTable() {
    }

    public MessageTable(String message, MessageTypeTable messageType, Date sendTime, Integer status) {
        this.messageId = UUID.randomUUID().toString();
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
