package br.com.alissonbolsoni.continuouscommunication.dataprovider.dao;

import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDestinyDao extends CrudRepository<MessageDestinyTable, Integer> {

    List<MessageDestinyTable> findByMessageId(String uuid);

}
