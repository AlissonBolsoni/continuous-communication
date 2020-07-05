package br.com.alissonbolsoni.continuouscommunication.dataprovider.entity;

import javax.persistence.*;

@Entity
@Table(name = "message_destinies")
public class MessageDestinyTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Integer messageDestinyId;

    @Column(nullable = false)
    private String destiny;

    @Column(nullable = false)
    private String messageId;

    public MessageDestinyTable() {

    }

    public MessageDestinyTable(Integer messageDestinyId, String destiny, String messageId) {
        this.messageDestinyId = messageDestinyId;
        this.destiny = destiny;
        this.messageId = messageId;
    }

    public Integer getMessageDestinyId() {
        return messageDestinyId;
    }

    public String getDestiny() {
        return destiny;
    }

    public String getMessageId() {
        return messageId;
    }
}
