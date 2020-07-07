package br.com.alissonbolsoni.continuouscommunication.controller;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.core.MessagesUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.RegisterMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.DateWrongException;
import br.com.alissonbolsoni.continuouscommunication.core.exception.RegisterFailException;
import br.com.alissonbolsoni.continuouscommunication.core.exception.TypeNotExistsException;
import br.com.alissonbolsoni.continuouscommunication.controller.mapper.MessagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.alissonbolsoni.continuouscommunication.controller.MessageController.PATH;

@RestController
@RequestMapping(PATH)
public class MessageController {
    protected static final String PATH = "/message";
    protected static final String PATH_REGISTER = "/register";
    protected static final String PATH_MESSAGE_BY_ID = "/{id}";

    private final MessagesUseCase messagesUseCase;
    private final RegisterMessageUseCase registerMessageUseCase;

    @Autowired
    public MessageController(MessagesUseCase messagesUseCase, RegisterMessageUseCase registerMessageUseCase) {
        this.messagesUseCase = messagesUseCase;
        this.registerMessageUseCase = registerMessageUseCase;
    }

    @PostMapping(PATH_REGISTER)
    public ResponseEntity<MessageDto> registerMessage(@RequestBody MessageDto messageDto) {

        try {
            Message message = MessagesMapper.messageDtoToMessageEntity(messageDto);
            Message messageReturned = registerMessageUseCase.RegisterMessage(message);

            return new ResponseEntity(
                    MessagesMapper.messageEntityToMessageDto(messageReturned),
                    HttpStatus.OK
            );
        } catch (TypeNotExistsException e) {
            return new ResponseEntity(
                    new MessageDto(e.getLocalizedMessage()),
                    HttpStatus.NOT_FOUND
            );
        } catch (DateWrongException e) {
            return new ResponseEntity(
                    new MessageDto(e.getLocalizedMessage()),
                    HttpStatus.EXPECTATION_FAILED
            );
        } catch (RegisterFailException e) {
            return new ResponseEntity(
                    new MessageDto(e.getLocalizedMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = PATH_MESSAGE_BY_ID)
    public ResponseEntity<Page<MessageDto>> getMessageById(@PathVariable String id) {
        try {
            Message messageById = messagesUseCase.getMessageById(id);

            return new ResponseEntity(
                    MessagesMapper.messageEntityToMessageDto(messageById),
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
