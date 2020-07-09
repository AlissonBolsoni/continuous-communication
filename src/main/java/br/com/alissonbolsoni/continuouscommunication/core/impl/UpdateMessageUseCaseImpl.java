package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.UpdateMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.contants.MessageStatus;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.UpdateFailException;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateMessageUseCaseImpl implements UpdateMessageUseCase {

    private final MessageRepository messageRepository;

    @Autowired
    public UpdateMessageUseCaseImpl(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void updateMessage(final Message message) throws UpdateFailException {
        try {
            message.setStatus(MessageStatus.SENT);
            Boolean result = messageRepository.updateMessage(message);

            if (!result)
                throw new UpdateFailException("Falha ao atualizar o status da mensagem");
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateFailException("Não foi possível atualizar " + e.getMessage());
        }
    }
}
