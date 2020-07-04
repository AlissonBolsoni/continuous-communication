package br.com.alissonbolsoni.continuouscommunication.controller;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.controller.mapper.MessageMapper;
import br.com.alissonbolsoni.continuouscommunication.core.MessageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.alissonbolsoni.continuouscommunication.controller.MessageController.PATH;

@RestController
@RequestMapping(PATH)
public class MessageController {
    protected static final String PATH = "/message";
    protected static final String PATH_MESSAGES = "/all";

    private MessageUseCase messageUseCase;

    @Autowired
    public MessageController(MessageUseCase messageUseCase) {
        this.messageUseCase = messageUseCase;
    }

    @GetMapping(value = PATH_MESSAGES)
    public ResponseEntity<Page<MessageDto>> getAllMessages() {
        try {
            return new ResponseEntity(
                    MessageMapper.toDto(messageUseCase.getAllMessages()),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(
                    new MessageDto(e.getLocalizedMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
