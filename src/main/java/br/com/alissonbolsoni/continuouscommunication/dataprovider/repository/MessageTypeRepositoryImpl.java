package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageTypeRepository;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageTypeDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTypeTable;
import br.com.alissonbolsoni.continuouscommunication.mapper.MessageTypesMapper;
import br.com.alissonbolsoni.continuouscommunication.mapper.MessagesMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageTypeRepositoryImpl implements MessageTypeRepository {

    private MessageTypeDao messageTypeDao;

    public MessageTypeRepositoryImpl(MessageTypeDao messageTypeDao) {
        this.messageTypeDao = messageTypeDao;
    }

    @Override
    public MessageType findByType(String type) {
        MessageTypeTable messageTypeTable = this.messageTypeDao.findByTypeIgnoreCase(type);

        return MessageTypesMapper.messageTypeTableToMessageType(messageTypeTable);
    }
}
