package br.com.alissonbolsoni.continuouscommunication.core.contants;

import org.junit.jupiter.api.Test;

import static br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus.*;
import static org.junit.jupiter.api.Assertions.*;

class MessageStatusTest {

    @Test
    void testParseIntToEnum(){
        assertEquals(WAITING, findByValue(WAITING.getStatus()));
        assertNotEquals(WAITING, findByValue(SENT.getStatus()));
        assertNull(findByValue(SENT.getStatus() + 1));
    }

}