package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "message_types")
public class MessageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Integer messageTypeId;

    @Column(nullable = false)
    private String type;

    public MessageType() {
        this(null, null);
    }

    public MessageType(String type) {
        this(null, type);
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
