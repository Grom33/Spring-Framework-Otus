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
import ru.otus.gromov.repository.HeavenRepository;

import java.util.ArrayList;

@Service
public class HeavenService implements MessageHandler {

    HeavenRepository reposetory;

    private final PublishSubscribeChannel gossip;

    @Autowired
    public HeavenService(@Qualifier("BeyondRumors") PublishSubscribeChannel gossip, HeavenRepository reposetory) {
        this.gossip = gossip;
        this.reposetory = reposetory;
        gossip.subscribe(this);
    }

    public void ascendToHeaven(Message message) {
        Soul saint = (Soul) message.getPayload();

        reposetory.save(saint);
        gossip.send(MessageBuilder
                .withPayload(String.format("Saint %s come to paradise!", saint.getName()))
                .setHeader("Smell", "Good")
                .build());
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        if ("Good".equals(message.getHeaders().get("Smell"))) System.out.println("Paradise gossip: "+message.getPayload());
    }

    public long getParadisePopulation(){
        return reposetory.count();
    }
}
