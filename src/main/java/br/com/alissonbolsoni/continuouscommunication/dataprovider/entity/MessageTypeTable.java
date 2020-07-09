package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import javax.persistence.*;

@Entity
@Table(name = "message_types")
public class MessageTypeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Integer messageTypeId;

    @Column(nullable = false)
    private String type;

    public MessageTypeTable() {
    }

    public MessageTypeTable(final Integer messageTypeId, final String type) {
        this.messageTypeId = messageTypeId;
        this.type = type;
    }

    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public String getType() {
        return type;
    }

}
