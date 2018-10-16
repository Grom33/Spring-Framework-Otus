package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otus.gromov.domain.Person;

@Service
public class MorgueService {

    final PollableChannel toJudge;

    @Autowired
    public MorgueService(@Qualifier("toJudge") PollableChannel channelPoll) {
        this.toJudge = channelPoll;
    }


    public void welcome(Person person) {
        System.out.println(String.format("Welcome  %s ", person.getSoul().getName()));
        toJudge.send(MessageBuilder.withPayload(person.getSoul()).build());
    }

}
