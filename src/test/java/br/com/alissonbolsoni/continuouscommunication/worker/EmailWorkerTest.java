package br.com.alissonbolsoni.continuouscommunication.worker;

import br.com.alissonbolsoni.continuouscommunication.core.UpdateMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.exception.UpdateFailException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import java.io.IOException;

import static br.com.alissonbolsoni.continuouscommunication.worker.EmailWorker.Q_EMAIL_WORKER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailWorkerTest {

    private final UpdateMessageUseCase updateMessageUseCase = mock(UpdateMessageUseCase.class);
    private final ObjectMapper mapper = mock(ObjectMapper.class);
    private final String json = "{\"messageId\":\"id\",\"message\":\"message\",\"messageType\":{\"messageTypeId\":null,\"type\":\"email\"},\"sendTime\":1594253893037,\"status\":\"WAITING\",\"destinies\":[{\"messageDestinyId\":null,\"destiny\":\"destiny\",\"messageId\":null}]}";

    @Test
    void createAndBindQueue() {
        EmailWorker emailWorker = new EmailWorker(updateMessageUseCase, mapper);
        Queue emailWorkerQueue = emailWorker.createEmailWorkerQueue();

        assertTrue(emailWorkerQueue.getClass().isAssignableFrom(Queue.class));
        assertEquals(Q_EMAIL_WORKER, emailWorkerQueue.getName());

        Binding binding = emailWorker.bindingEmailWorkerQueue(emailWorkerQueue, new TopicExchange("exchange"));

        assertTrue(binding.getClass().isAssignableFrom(Binding.class));
    }

    @Test
    void testUpdateMessage() throws IOException, UpdateFailException {
        doNothing().when(updateMessageUseCase).updateMessage(any());
        EmailWorker emailWorker = new EmailWorker(updateMessageUseCase, mapper);
        emailWorker.EmailWorkerConsumer(new Message(json.getBytes(), null));

        verify(updateMessageUseCase).updateMessage(any());
    }
}