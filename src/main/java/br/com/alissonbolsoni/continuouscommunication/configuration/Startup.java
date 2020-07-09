package br.com.alissonbolsoni.continuouscommunication.configuration;

import br.com.alissonbolsoni.continuouscommunication.dataprovider.dao.MessageTypeDao;
import br.com.alissonbolsoni.continuouscommunication.dataprovider.entity.MessageTypeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Startup {
    private final MessageTypeDao messageTypeDao;

    @Autowired
    public Startup(final MessageTypeDao messageTypeDao) {
        this.messageTypeDao = messageTypeDao;
    }

    @PostConstruct
    protected void prepareDatabase() {
        Iterable<MessageTypeTable> iterable = messageTypeDao.findAll();
        List<MessageTypeTable> messageTypeTableList = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

        if (messageTypeTableList.isEmpty())
            createMessageTypeValues();
    }

    private void createMessageTypeValues() {

        ArrayList<MessageTypeTable> list = new ArrayList<MessageTypeTable>();
        list.add(new MessageTypeTable(null, "email"));
        list.add(new MessageTypeTable(null, "sms"));
        list.add(new MessageTypeTable(null, "push"));
        list.add(new MessageTypeTable(null, "whatsapp"));
        messageTypeDao.saveAll(list);
    }
}
