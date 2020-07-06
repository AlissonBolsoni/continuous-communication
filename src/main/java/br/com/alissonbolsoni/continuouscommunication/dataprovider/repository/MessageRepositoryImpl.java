package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper.MessagesMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private MessageDao messageDao;

    public MessageRepositoryImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Message saveMessage(Message message) throws Exception {
        MessageTable save = messageDao.save(MessagesMapper.messageToMessageTable(message));
        return MessagesMapper.messageTableToMessage(save);
    }
}
