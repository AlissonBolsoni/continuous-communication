package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageDestinyRepository;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDestinyDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper.MessageDestinyMapper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MessageDestinyRepositoryImpl implements MessageDestinyRepository {

    private final MessageDestinyDao messageDestinyDao;

    public MessageDestinyRepositoryImpl(final MessageDestinyDao messageDestinyDao) {
        this.messageDestinyDao = messageDestinyDao;
    }

    @Override
    public List<MessageDestiny> saveMessageDestinies(final List<MessageDestiny> messageDestinies, final UUID messageId) throws Exception {

        List<MessageDestinyTable> messageDestinyTables = MessageDestinyMapper.toTable(messageDestinies, messageId);

        Iterable<MessageDestinyTable> messageDestinies1 = messageDestinyDao.saveAll(messageDestinyTables);
        List<MessageDestinyTable> list = Lists.newArrayList(messageDestinies1);

        return MessageDestinyMapper.tableToEntity(list);
    }
}
