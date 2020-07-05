package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(nullable = false)
    private Integer messageTypeId;

    @Column(nullable = false)
    private Date sendTime;

    @Column(nullable = false)
    private Integer status;

    public MessageTable() {
    }

    public MessageTable(String message, Integer messageTypeId, Date sendTime, Integer status) {
        this.messageId = UUID.randomUUID().toString();
        this.message = message;
        this.messageTypeId = messageTypeId;
        this.sendTime = sendTime;
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public Integer getStatus() {
        return status;
    }
}
