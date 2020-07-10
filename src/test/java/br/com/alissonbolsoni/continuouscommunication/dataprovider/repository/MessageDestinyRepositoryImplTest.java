package br.com.alissonbolsoni.continuouscommunication.dataprovider.repository;

import br.com.alissonbolsoni.continuouscommunication.core.entity.MessageDestiny;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageDestinyDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageDestinyTable;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageDestinyRepositoryImplTest {

    private final MessageDestinyDao messageDestinyDao = mock(MessageDestinyDao.class);
    private final List<MessageDestiny> messageDestinies = new ArrayList<>();
    private final UUID messageId = UUID.randomUUID();
    private final String destiny = "destiny";
    private List<MessageDestinyTable> messageDestiniesTable = new ArrayList<>();

    @Test
    void testSaveMessageDestinies() throws Exception {
        messageDestinies.add(new MessageDestiny(null, destiny));
        messageDestiniesTable.add(new MessageDestinyTable(1, destiny, messageId.toString()));
        when(messageDestinyDao.saveAll(any())).thenReturn(messageDestiniesTable);

        MessageDestinyRepositoryImpl messageDestinyRepository = new MessageDestinyRepositoryImpl(messageDestinyDao);
        List<MessageDestiny> result = messageDestinyRepository.saveMessageDestinies(this.messageDestinies, messageId);

        assertNotNull(result.get(0).getMessageDestinyId());
        assertEquals(destiny, result.get(0).getDestiny());
        assertEquals(messageId.toString(), result.get(0).getMessageId());

    }
}