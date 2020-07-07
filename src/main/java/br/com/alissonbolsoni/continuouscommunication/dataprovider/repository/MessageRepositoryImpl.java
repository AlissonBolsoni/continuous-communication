package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDestinyDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper.MessageDestinyMapper;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper.MessagesMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageDao messageDao;
    private final MessageDestinyDao messageDestinyDao;

    public MessageRepositoryImpl(MessageDao messageDao, MessageDestinyDao messageDestinyDao) {
        this.messageDao = messageDao;
        this.messageDestinyDao = messageDestinyDao;
    }

    @Override
    public Message getMessage(String messageId) {
        MessageTable byMessageId = messageDao.findByMessageId(messageId);
        List<MessageDestinyTable> destinies = messageDestinyDao.findByMessageId(messageId);
        Message message = MessagesMapper.messageTableToMessage(byMessageId);
        message.setDestinies(MessageDestinyMapper.tableToEntity(destinies));
        return message;
    }

    @Override
    public Message removeMessage(String messageId) {
        Message message = getMessage(messageId);
        messageDao.deleteById(messageId);
        return message;
    }

    @Override
    public Message saveMessage(Message message) throws Exception {
        MessageTable save = messageDao.save(MessagesMapper.messageToMessageTable(message));
        return MessagesMapper.messageTableToMessage(save);
    }
}
