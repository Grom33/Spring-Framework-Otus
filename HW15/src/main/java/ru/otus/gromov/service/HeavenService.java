package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otus.gromov.domain.Soul;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeavenService implements MessageHandler {
    private final static int HEAVEN_CAPACITY = 100;
    private List<Soul> paradise;

    private final PublishSubscribeChannel gossip;

    @Autowired
    public HeavenService(@Qualifier("BeyondRumors") PublishSubscribeChannel gossip) {
        this.gossip = gossip;
        this.paradise = new ArrayList<>();
        gossip.subscribe(this);
    }

    public void ascendToHeaven(Message message) {
        Soul saint = (Soul) message.getPayload();
        if (paradise.size() >= HEAVEN_CAPACITY) {
            gossip.send(MessageBuilder
                    .withPayload("Paradise is full!!")
                    .setHeader("Smell", "Bad")
                    .setHeader("HeavenIsFull", true)
                    .build());
            return;
        }
        paradise.add(saint);
        gossip.send(MessageBuilder
                .withPayload(String.format("Saint %s come to paradise!", saint.getName()))
                .setHeader("Smell", "Good")
                .build());
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        if ("Good".equals(message.getHeaders().get("Smell"))) System.out.println("Paradise gossip: "+message.getPayload());
    }

    public int getParadisePopulation(){
        return paradise.size();
    }
}
