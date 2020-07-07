package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageDestinyRepository;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDestinyDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper.MessageDestinyMapper;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper.MessagesMapper;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@Repository
public class MessageDestinyRepositoryImpl implements MessageDestinyRepository {

    private final MessageDestinyDao messageDestinyDao;

    public MessageDestinyRepositoryImpl(MessageDestinyDao messageDestinyDao) {
        this.messageDestinyDao = messageDestinyDao;
    }

    @Override
    public List<MessageDestiny> saveMessageDestinies(List<MessageDestiny> messageDestinies, String messageId) throws Exception {

        List<MessageDestinyTable> messageDestinyTables = MessageDestinyMapper.toTable(messageDestinies, messageId);

        Iterable<MessageDestinyTable> messageDestinies1 = messageDestinyDao.saveAll(messageDestinyTables);
        List<MessageDestinyTable> list = ImmutableList.copyOf(messageDestinies1);

        return MessageDestinyMapper.tableToEntity(list);
    }
}