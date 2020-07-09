package br.com.alissonbolsoni.continuouscommunication.core.impl;

import br.com.alissonbolsoni.continuouscommunication.core.MessagesUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.NotFoundException;
import br.com.alissonbolsoni.continuouscommunication.core.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessagesUseCaseImpl implements MessagesUseCase {

    private final MessageRepository messageRepository;

    @Autowired
    public MessagesUseCaseImpl(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message getMessageById(final UUID id) throws NotFoundException {
        try {
            Message messageReturn = messageRepository.getMessage(id);

            if (messageReturn == null) throw new NotFoundException("Mensagem não foi encontrada com esse ID");
            return messageReturn;
        }catch (Throwable t){
            throw new NotFoundException("Mensagem não foi encontrada com esse ID");
        }
    }
}
