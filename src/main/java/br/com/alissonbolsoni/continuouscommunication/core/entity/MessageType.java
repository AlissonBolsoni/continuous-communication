package br.com.alissonbolsoni.continuouscommunication.core.entity;

import java.util.Objects;

public class MessageType {
    private Integer messageTypeId;
    private String type;

    public MessageType() {
    }

    public MessageType(final Integer messageTypeId, final String type) {
        this.messageTypeId = messageTypeId;
        this.type = type;
    }

    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(final Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageType that = (MessageType) o;
        return Objects.equals(messageTypeId, that.messageTypeId) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageTypeId, type);
    }
}
