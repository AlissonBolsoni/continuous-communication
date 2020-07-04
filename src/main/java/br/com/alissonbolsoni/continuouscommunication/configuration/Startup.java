package br.com.alissonbolsoni.continuouscommunication.configuration;

import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageTypeDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Startup {
    private MessageTypeDao messageTypeDao;
    @Autowired
    public Startup (MessageTypeDao messageTypeDao){
        this.messageTypeDao = messageTypeDao;
    }

    @PostConstruct
    private void prepareDatabase() {
        try {
            Iterable<MessageType> iterable = messageTypeDao.findAll();
            List<MessageType> messageTypeList = StreamSupport.stream(iterable.spliterator(), false)
                    .collect(Collectors.toList());

            if (messageTypeList.isEmpty())
                createMessageTypeValues();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createMessageTypeValues() {

        ArrayList<MessageType> list = new ArrayList<MessageType>();
        list.add(new MessageType("email"));
        list.add(new MessageType("sms"));
        list.add(new MessageType("push"));
        list.add(new MessageType("whatsapp"));
        messageTypeDao.saveAll(list);
    }
}
