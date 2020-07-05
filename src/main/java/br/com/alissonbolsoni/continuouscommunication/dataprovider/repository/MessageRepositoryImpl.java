package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private MessageDao messageDao;

    public MessageRepositoryImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public MessageTable saveMessage(MessageTable messageTable) throws Exception {
        MessageTable save = messageDao.save(messageTable);
        return save;
    }
}
