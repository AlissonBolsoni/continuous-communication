package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.RemoveMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.RemoveFailException;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RemoveMessageUseCaseImplTest {

    private final static String MESSAGE_ID = "MESSAGE_ID";
    private final static String MESSAGE = "MESSAGE";
    private final MessageRepository messageRepository = mock(MessageRepository.class);
    private final Message messageSuccess = new Message(MESSAGE_ID, MESSAGE, null, null, null, null);

    @Test
    void testRemoveMessageWithSuccess() throws Exception {
        when(messageRepository.removeMessage(MESSAGE_ID)).thenReturn(messageSuccess);

        RemoveMessageUseCase useCase = new RemoveMessageUseCaseImpl(messageRepository);
        Message message = useCase.removeMessageById(MESSAGE_ID);

        assertEquals(message.getMessageId(), MESSAGE_ID);
        assertEquals(message.getMessage(), MESSAGE);
    }

    @Test
    void testRemoveMessageWithAnswerNull() throws Exception {
        when(messageRepository.removeMessage(MESSAGE_ID)).thenReturn(null);
        RemoveMessageUseCase useCase = new RemoveMessageUseCaseImpl(messageRepository);

        assertThrows(RemoveFailException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                useCase.removeMessageById(MESSAGE_ID);
            }
        });
    }

    @Test
    void testRemoveMessageWithThrowException() throws Exception {
        when(messageRepository.removeMessage(anyString())).thenThrow(Exception.class);
        RemoveMessageUseCase useCase = new RemoveMessageUseCaseImpl(messageRepository);

        assertThrows(RemoveFailException.class,() -> useCase.removeMessageById(MESSAGE_ID));
    }
}