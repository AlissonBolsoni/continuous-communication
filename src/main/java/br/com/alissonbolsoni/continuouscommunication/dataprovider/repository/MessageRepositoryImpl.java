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
import java.util.UUID;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageDao messageDao;
    private final MessageDestinyDao messageDestinyDao;

    public MessageRepositoryImpl(final MessageDao messageDao, final MessageDestinyDao messageDestinyDao) {
        this.messageDao = messageDao;
        this.messageDestinyDao = messageDestinyDao;
    }

    @Override
    public Message getMessage(final UUID messageId) {
        MessageTable byMessageId = messageDao.findByMessageId(messageId);
        return getMessageFromTable(byMessageId);
    }

    private Message getMessageFromTable(final MessageTable messageTable) {
        List<MessageDestinyTable> destinies = messageDestinyDao.findByMessageId(messageTable.getMessageId().toString());
        Message message = MessagesMapper.messageTableToMessage(messageTable);
        message.setDestinies(MessageDestinyMapper.tableToEntity(destinies));
        return message;
    }

    @Override
    public Message removeMessage(final UUID messageId) {
        MessageTable byMessageId = messageDao.findByMessageId(messageId);
        messageDao.delete(byMessageId);
        return getMessageFromTable(byMessageId);
    }

    @Override
    public Message saveMessage(final Message message) {
        MessageTable save = messageDao.save(MessagesMapper.messageToMessageTable(message));
        return MessagesMapper.messageTableToMessage(save);
    }

    @Override
    public Boolean updateMessage(final Message message) {
        try {
            MessageTable messageTable = MessagesMapper.messageToUpdateMessageTable(message);
            messageDao.save(messageTable);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
