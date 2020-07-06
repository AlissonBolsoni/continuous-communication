package br.com.alissonbolsoni.continuouscommunication.core.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;

import java.util.List;

public interface MessageDestinyRepository {

    public List<MessageDestiny> saveMessageDestinies(List<MessageDestiny> messageDestinies, String messageId) throws Exception;
}
