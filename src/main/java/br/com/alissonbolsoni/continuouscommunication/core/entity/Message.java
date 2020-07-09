package br.com.alissonbolsoni.continuouscommunication.core.entity;

import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Message {
    private UUID messageId;
    private String message;
    private MessageType messageType;
    private Date sendTime;
    private MessageStatus status;
    private List<MessageDestiny> destinies;

    public Message() {
    }

    public Message(final UUID messageId, final String message, final MessageType messageType, final Date sendTime, final MessageStatus status, final List<MessageDestiny> destinies) {
        this.messageId = messageId;
        this.message = message;
        this.messageType = messageType;
        this.sendTime = sendTime;
        this.status = status;
        this.destinies = destinies;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(final UUID messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(final MessageType messageType) {
        this.messageType = messageType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(final Date sendTime) {
        this.sendTime = sendTime;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(final MessageStatus status) {
        this.status = status;
    }

    public List<MessageDestiny> getDestinies() {
        return destinies;
    }

    public void setDestinies(final List<MessageDestiny> destinies) {
        this.destinies = destinies;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(messageId, message1.messageId) &&
                Objects.equals(message, message1.message) &&
                Objects.equals(messageType, message1.messageType) &&
                Objects.equals(sendTime, message1.sendTime) &&
                status == message1.status &&
                Objects.equals(destinies, message1.destinies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, message, messageType, sendTime, status, destinies);
    }
}
