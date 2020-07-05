package br.com.alissonbolsoni.continuouscommunication.core.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;

public interface MessageTypeRepository {

    public MessageType findByType(String type);
}
