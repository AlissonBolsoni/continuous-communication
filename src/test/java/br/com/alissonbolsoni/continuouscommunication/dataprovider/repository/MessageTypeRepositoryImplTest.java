package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageTypeDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTypeTable;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageTypeRepositoryImplTest {

    private final MessageTypeDao messageTypeDao = mock(MessageTypeDao.class);
    private final MessageTypeTable messageTypeTable = new MessageTypeTable(1, "email");

    @Test
    void testFindByType(){
        when(messageTypeDao.findByTypeIgnoreCase(anyString())).thenReturn(messageTypeTable);

        MessageTypeRepositoryImpl messageTypeRepository = new MessageTypeRepositoryImpl(messageTypeDao);

        MessageType email = messageTypeRepository.findByType("email");

        assertEquals(messageTypeTable.getMessageTypeId(), email.getMessageTypeId());
        assertEquals(messageTypeTable.getType(), email.getType());
    }

}