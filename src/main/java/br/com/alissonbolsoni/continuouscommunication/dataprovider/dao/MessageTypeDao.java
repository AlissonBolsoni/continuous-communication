package br.com.alissonbolsoni.continuouscommunication.dataprovider.dao;

import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTypeDao extends CrudRepository<MessageType, Integer> {

    public MessageType findByTypeIgnoreCase(String type);

}
