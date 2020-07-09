package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.MessagesUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.NotFoundException;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MessagesUseCaseImplTest {

    private final static UUID MESSAGE_ID = UUID.randomUUID();
    private final static String MESSAGE = "MESSAGE";
    private final MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
    private final Message messageSuccess = new Message(MESSAGE_ID, MESSAGE, null, null, null, null);

    @Test
    void testGetMessageByIdWithSuccess() throws NotFoundException {
        Mockito.when(messageRepository.getMessage(MESSAGE_ID)).thenReturn(messageSuccess);

        MessagesUseCase useCase = new MessagesUseCaseImpl(messageRepository);
        Message message = useCase.getMessageById(MESSAGE_ID);

        assertEquals(message.getMessageId(), MESSAGE_ID);
        assertEquals(message.getMessage(), MESSAGE);

    }

    @Test
    void testGetMessageByIdWithAnswerNull() throws NotFoundException {
        Mockito.when(messageRepository.getMessage(MESSAGE_ID)).thenReturn(null);
        MessagesUseCase useCase = new MessagesUseCaseImpl(messageRepository);

        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                useCase.getMessageById(MESSAGE_ID);
            }
        });
    }

}