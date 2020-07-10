package br.com.alissonbolsoni.continuouscommunication.dataprovider.dao;

import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageDao extends CrudRepository<MessageTable, String> {

    MessageTable findByMessageId(UUID uuid);
}
