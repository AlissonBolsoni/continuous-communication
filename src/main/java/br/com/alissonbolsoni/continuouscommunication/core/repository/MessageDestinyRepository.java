package br.com.alissonbolsoni.continuouscommunication.core.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;

import java.util.List;
import java.util.UUID;

public interface MessageDestinyRepository {

    public List<MessageDestiny> saveMessageDestinies(List<MessageDestiny> messageDestinies, UUID messageId) throws Exception;
}
