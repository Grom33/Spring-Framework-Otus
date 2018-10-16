package ru.otus.gromov.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otus.gromov.domain.Soul;

@Service
public class GodJudgmentService implements MessageHandler {

    private final PollableChannel toHeaven;
    private final PollableChannel toHell;
    private boolean heavenIsFull;
    private final PublishSubscribeChannel gossip;

    @Autowired
    public GodJudgmentService(@Qualifier("toHeaven") PollableChannel toHeaven,
                              @Qualifier("toHell") PollableChannel toHell,
                              @Qualifier("BeyondRumors") PublishSubscribeChannel gossip) {
        this.toHeaven = toHeaven;
        this.toHell = toHell;
        this.gossip = gossip;
        this.heavenIsFull = false;
        gossip.subscribe(this);
    }


    public void judge(Message message) {
        Soul soul = (Soul) message.getPayload();
        if (soul.getSins() > 0) {
            gossip.send(MessageBuilder
                    .withPayload(String.format("Sinner %s, is coming to the hell!:(", soul.getName()))
                    .setHeader("Smell", "Bad")
                    .build());
            toHell.send(MessageBuilder.withPayload(soul).build());
        } else {
            gossip.send(MessageBuilder
                    .withPayload(String.format("Saint %s, is coming to the paradise Yhoo!:)", soul.getName()))
                    .setHeader("Smell", "Good")
                    .build());
            if (!this.heavenIsFull) {
                toHeaven.send(MessageBuilder.withPayload(soul).build());
            } else {
                gossip.send(MessageBuilder
                        .withPayload(String.format("Saint %s, is waiting on Gate, because Paradise is full!", soul.getName()))
                        .setHeader("Smell", "Bad")
                        .build());
            }
        }
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        if (!message.getHeaders().containsKey("HeavenIsFull")) return;
        if ((boolean) message.getHeaders().get("HeavenIsFull")) {
            this.heavenIsFull = true;
        }
    }

    public boolean isHeavenIsFull() {
        return this.heavenIsFull;
    }
}
