package br.com.alissonbolsoni.continuouscommunication.configuration;

import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageTypeDao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class StartupTest {

    @Test
    void testPopulateDatabase(){
        MessageTypeDao messageTypeDao = mock(MessageTypeDao.class);
        when(messageTypeDao.findAll()).thenReturn(new ArrayList<>());
        when(messageTypeDao.saveAll(any())).thenReturn(new ArrayList<>());

        Startup startup = new Startup(messageTypeDao);
        startup.prepareDatabase();
        verify(messageTypeDao).saveAll(any());
    }

}