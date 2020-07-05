package br.com.alissonbolsoni.continuouscommunication.core.contants;

public enum MessageStatus {
    WAITING(1), SENT(2);
    private Integer status;

    MessageStatus(Integer status) {
        this.status = status;
    }

    public static MessageStatus findByValue(Integer status){
        for (MessageStatus ms : MessageStatus.values()) {
            if (ms.status.equals(status)) return ms;
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }
}
