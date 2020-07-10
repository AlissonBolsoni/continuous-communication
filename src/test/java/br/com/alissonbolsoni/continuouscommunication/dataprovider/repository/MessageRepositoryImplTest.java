package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageType;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDestinyDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTypeTable;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper.MessageDestinyMapper;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.mapper.MessagesMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageRepositoryImplTest {

    private final MessageDao messageDao = mock(MessageDao.class);
    private final MessageDestinyDao messageDestinyDao = mock(MessageDestinyDao.class);

    private final UUID messageId = UUID.randomUUID();
    private final String messageText = "message";
    private final MessageType messageType = new MessageType(1, "email");
    private final MessageTypeTable messageTypeTable = new MessageTypeTable(1, "email");
    private final Date sendTime = new Date();
    private final MessageStatus status = MessageStatus.WAITING;
    private final List<MessageDestiny> destinies = new ArrayList<>();
    private final List<MessageDestinyTable> destiniesTable = new ArrayList<>();
    private final Message base = new Message(messageId, messageText, messageType, sendTime, status, destinies);
    private final MessageTable table = new MessageTable(messageId, messageText, messageTypeTable, sendTime, status.getStatus());

    @Test
    void testFindMessageById(){
        when(messageDao.findByMessageId(any())).thenReturn(table);
        when(messageDestinyDao.findByMessageId(any())).thenReturn(destiniesTable);

        MessageRepositoryImpl messageRepository = new MessageRepositoryImpl(messageDao, messageDestinyDao);
        Message message = messageRepository.getMessage(messageId);

        assertEquals(base.hashCode(), message.hashCode());
        assertEquals(base, message);
    }

    @Test
    void testRemoveMessageById(){
        doNothing().when(messageDao).deleteById(anyString());
        when(messageDao.findByMessageId(any())).thenReturn(table);
        when(messageDestinyDao.findByMessageId(any())).thenReturn(destiniesTable);

        MessageRepositoryImpl messageRepository = new MessageRepositoryImpl(messageDao, messageDestinyDao);
        Message message = messageRepository.removeMessage(messageId);

        assertEquals(base.hashCode(), message.hashCode());
        assertEquals(base, message);
    }

    @Test
    void testSaveMessage(){
        when(messageDao.save(any())).thenReturn(table);

        MessageRepositoryImpl messageRepository = new MessageRepositoryImpl(messageDao, messageDestinyDao);
        Message message = messageRepository.saveMessage(base);

        assertEquals(messageId, message.getMessageId());
        assertEquals(messageText, message.getMessage());
        assertEquals(messageType, message.getMessageType());
        assertEquals(sendTime, message.getSendTime());
        assertEquals(status, message.getStatus());
    }

    @Test
    void testUpdateMessageWithSuccess() {
        when(messageDao.save(any())).thenReturn(table);

        MessageRepositoryImpl messageRepository = new MessageRepositoryImpl(messageDao, messageDestinyDao);
        Boolean aBoolean = messageRepository.updateMessage(base);

        assertTrue(aBoolean);
    }

    @Test
    void testUpdateMessageWithFail() {
        when(messageDao.save(any())).thenReturn(null);

        MessageRepositoryImpl messageRepository = new MessageRepositoryImpl(messageDao, messageDestinyDao);
        Boolean aBoolean = messageRepository.updateMessage(null);

        assertFalse(aBoolean);
    }

}