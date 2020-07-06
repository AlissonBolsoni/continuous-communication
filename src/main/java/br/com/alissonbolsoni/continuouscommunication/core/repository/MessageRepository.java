package br.com.alissonbolsoni.continuouscommunication.core.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;

public interface MessageRepository {

    public Message saveMessage(Message message) throws Exception;
}
