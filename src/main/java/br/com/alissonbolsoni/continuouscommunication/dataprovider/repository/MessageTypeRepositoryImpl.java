package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageTypeRepository;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageTypeDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTypeTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper.MessageTypesMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageTypeRepositoryImpl implements MessageTypeRepository {

    private final MessageTypeDao messageTypeDao;

    public MessageTypeRepositoryImpl(final MessageTypeDao messageTypeDao) {
        this.messageTypeDao = messageTypeDao;
    }

    @Override
    public MessageType findByType(final String type) {
        MessageTypeTable messageTypeTable = this.messageTypeDao.findByTypeIgnoreCase(type);

        return MessageTypesMapper.messageTypeTableToMessageType(messageTypeTable);
    }
}
