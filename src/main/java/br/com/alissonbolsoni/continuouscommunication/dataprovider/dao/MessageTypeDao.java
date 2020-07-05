package br.com.alissonbolsoni.continuouscommunication.dataprovider.dao;

import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTypeTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTypeDao extends CrudRepository<MessageTypeTable, Integer> {

    public MessageTypeTable findByTypeIgnoreCase(String type);

}
