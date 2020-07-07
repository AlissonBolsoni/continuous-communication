package br.com.alissonbolsoni.continuouscommunication.controller;

import br.com.alissonbolsoni.continuouscommunication.controller.dto.MessageDto;
import br.com.alissonbolsoni.continuouscommunication.controller.mapper.MessagesMapper;
import br.com.alissonbolsoni.continuouscommunication.core.MessagesUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.RegisterMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.RemoveMessageUseCase;
import br.com.alissonbolsoni.continuouscommunication.core.entity.Message;
import br.com.alissonbolsoni.continuouscommunication.core.exception.DateWrongException;
import br.com.alissonbolsoni.continuouscommunication.core.exception.RegisterFailException;
import br.com.alissonbolsoni.continuouscommunication.core.exception.RemoveFailException;
import br.com.alissonbolsoni.continuouscommunication.core.exception.TypeNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.alissonbolsoni.continuouscommunication.controller.MessageController.PATH;

@RestController
@RequestMapping(PATH)
public class MessageController {
    protected static final String PATH = "/message";
    protected static final String PATH_REGISTER = "/register";
    protected static final String PATH_MESSAGE_BY_ID = "/{id}";

    private final MessagesUseCase messagesUseCase;
    private final RegisterMessageUseCase registerMessageUseCase;
    private final RemoveMessageUseCase removeMessageUseCase;

    @Autowired
    public MessageController(MessagesUseCase messagesUseCase, RegisterMessageUseCase registerMessageUseCase, RemoveMessageUseCase removeMessageUseCase) {
        this.messagesUseCase = messagesUseCase;
        this.registerMessageUseCase = registerMessageUseCase;
        this.removeMessageUseCase = removeMessageUseCase;
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
    public ResponseEntity<MessageDto> getMessageById(@PathVariable String id) {
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

    @DeleteMapping(value = PATH_MESSAGE_BY_ID)
    public ResponseEntity<MessageDto> removeMessageById(@PathVariable String id) {
        try {
            Message messageById = removeMessageUseCase.removeMessageById(id);
            return new ResponseEntity(
                    MessagesMapper.messageEntityToMessageDto(messageById),
                    HttpStatus.OK
            );
        } catch (RemoveFailException e) {
            return new ResponseEntity(
                    new MessageDto(e.getLocalizedMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
