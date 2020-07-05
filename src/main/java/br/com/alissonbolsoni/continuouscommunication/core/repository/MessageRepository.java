package br.com.alissonbolsoni.continuouscommunication.core.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;

public interface MessageRepository {

    public MessageTable saveMessage(MessageTable messageTable) throws Exception;
}
