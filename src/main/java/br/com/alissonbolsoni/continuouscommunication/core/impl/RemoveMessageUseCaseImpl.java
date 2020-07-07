package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.RemoveMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.RemoveFailException;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveMessageUseCaseImpl implements RemoveMessageUseCase {

    private final MessageRepository messageRepository;

    @Autowired
    public RemoveMessageUseCaseImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message removeMessageById(String id) throws RemoveFailException {
        try {
            return messageRepository.removeMessage(id);
        }catch (Exception e){
            throw new RemoveFailException("Falha ao deletar a mensagem");
        }
    }
}
