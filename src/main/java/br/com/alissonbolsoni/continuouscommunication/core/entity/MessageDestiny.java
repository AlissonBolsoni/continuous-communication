package br.com.alissonbolsoni.continuouscommunication.core.entity;

import java.util.Objects;

public class MessageDestiny {
    private Integer messageDestinyId;
    private String destiny;
    private String messageId;

    public MessageDestiny() {
    }

    public MessageDestiny(final Integer messageDestinyId, final String destiny) {
        this.messageDestinyId = messageDestinyId;
        this.destiny = destiny;
    }

    public MessageDestiny(final Integer messageDestinyId, final String destiny, final String messageId) {
        this.messageDestinyId = messageDestinyId;
        this.destiny = destiny;
        this.messageId = messageId;
    }

    public Integer getMessageDestinyId() {
        return messageDestinyId;
    }

    public void setMessageDestinyId(final Integer messageDestinyId) {
        this.messageDestinyId = messageDestinyId;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(final String destiny) {
        this.destiny = destiny;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDestiny that = (MessageDestiny) o;
        return Objects.equals(messageDestinyId, that.messageDestinyId) &&
                Objects.equals(destiny, that.destiny) &&
                Objects.equals(messageId, that.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageDestinyId, destiny, messageId);
    }
}
