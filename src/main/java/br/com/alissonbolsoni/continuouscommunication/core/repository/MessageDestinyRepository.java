package br.com.alissonbolsoni.continuouscommunication.core.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;

import java.util.List;

public interface MessageDestinyRepository {

    public List<MessageDestinyTable> saveMessageDestinies(List<MessageDestinyTable> messageDestinies) throws Exception;
}
