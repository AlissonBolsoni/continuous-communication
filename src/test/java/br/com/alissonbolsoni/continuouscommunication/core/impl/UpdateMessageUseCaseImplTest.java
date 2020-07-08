package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.UpdateMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateMessageUseCaseImplTest {
    private final static String MESSAGE_ID = "MESSAGE_ID";
    private final static String MESSAGE = "MESSAGE";
    private final MessageRepository messageRepository = mock(MessageRepository.class);
    private final Message message = new Message(MESSAGE_ID, MESSAGE, null, null, null, null);

    @Test
    void testUpdateMessageWithSuccess() throws Exception {
        when(messageRepository.updateMessage(message)).thenReturn(true);

        UpdateMessageUseCase useCase = new UpdateMessageUseCaseImpl(messageRepository);
        Boolean success = useCase.updateMessage(message);

        assertTrue(success);
    }

    @Test
    void testUpdateMessageWithAnswerNull() throws Exception {
        when(messageRepository.updateMessage(message)).thenReturn(false);

        UpdateMessageUseCase useCase = new UpdateMessageUseCaseImpl(messageRepository);
        Boolean success = useCase.updateMessage(message);

        assertFalse(success);
    }

    @Test
    void testUpdateMessageWithThrowException() throws Exception {
        when(messageRepository.updateMessage(Mockito.any())).thenThrow(Exception.class);
        UpdateMessageUseCase useCase = new UpdateMessageUseCaseImpl(messageRepository);

        assertFalse(useCase.updateMessage(message));
    }
}