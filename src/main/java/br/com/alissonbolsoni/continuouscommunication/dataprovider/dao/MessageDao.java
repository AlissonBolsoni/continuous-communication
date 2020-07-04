package br.com.alissonbolsoni.continuouscommunication.dataprovider.dao;

import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends CrudRepository<Message, String> {

    Page<Message> findByMessageId(String uuid, final Pageable pageable);

}
