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
public class HellService implements MessageHandler {
    private List<Soul> boiler;
    private final PublishSubscribeChannel gossip;

    @Autowired
    public HellService(@Qualifier("BeyondRumors") PublishSubscribeChannel gossip) {
        this.boiler = new ArrayList<>();
        this.gossip = gossip;
        gossip.subscribe(this);
    }

    public void burnInHell(Message message) {
        Soul sinner = (Soul) message.getPayload();
        boiler.add(sinner);
        gossip.send(MessageBuilder
                .withPayload(String.format("Sinner %s come to hell!", sinner.getName()))
                .setHeader("Smell", "Bad")
                .build());
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        if ("Bad".equals(message.getHeaders().get("Smell"))) System.out.println("Hell gossip: " + message.getPayload());
    }

    public int getHellPopulation(){
        return boiler.size();

    }

}
