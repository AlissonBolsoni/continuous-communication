package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class MessageTable {

    @Id
    @Column
    @Type(type = "uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID messageId;

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

    public MessageTable(final String message, final MessageTypeTable messageType, final Date sendTime, final Integer status) {
        this.message = message;
        this.messageType = messageType;
        this.sendTime = sendTime;
        this.status = status;
    }

    public MessageTable(final UUID messageId, final String message, final MessageTypeTable messageType, final Date sendTime, final Integer status) {
        this.messageId = messageId;
        this.message = message;
        this.messageType = messageType;
        this.sendTime = sendTime;
        this.status = status;
    }

    public UUID getMessageId() {
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
