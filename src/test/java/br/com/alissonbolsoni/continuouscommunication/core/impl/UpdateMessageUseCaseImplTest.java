package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.UpdateMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.UpdateFailException;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateMessageUseCaseImplTest {
    private final static UUID MESSAGE_ID = UUID.randomUUID();
    private final static String MESSAGE = "MESSAGE";
    private final MessageRepository messageRepository = mock(MessageRepository.class);
    private final Message message = new Message(MESSAGE_ID, MESSAGE, null, null, null, null);

    @Test
    void testUpdateMessageWithSuccess() throws Exception {
        when(messageRepository.updateMessage(message)).thenReturn(true);

        UpdateMessageUseCase useCase = new UpdateMessageUseCaseImpl(messageRepository);
        useCase.updateMessage(message);

        verify(messageRepository.updateMessage(any()));
    }

    @Test
    void testUpdateMessageWithAnswerNull() throws Exception {
        when(messageRepository.updateMessage(message)).thenReturn(false);

        UpdateMessageUseCase useCase = new UpdateMessageUseCaseImpl(messageRepository);
        assertThrows(UpdateFailException.class, () -> useCase.updateMessage(message));
    }

    @Test
    void testUpdateMessageWithThrowException() throws Exception {
        when(messageRepository.updateMessage(Mockito.any())).thenThrow(Exception.class);
        UpdateMessageUseCase useCase = new UpdateMessageUseCaseImpl(messageRepository);

        assertThrows(UpdateFailException.class, () -> useCase.updateMessage(message));
    }
}