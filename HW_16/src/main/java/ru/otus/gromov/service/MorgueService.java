package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otus.gromov.domain.Person;
import ru.otus.gromov.repository.NecrologueRepository;

@Service
public class MorgueService {

	final PollableChannel toJudge;
	final NecrologueRepository repository;

	@Autowired
	public MorgueService(PollableChannel toJudge, NecrologueRepository repository) {
		this.toJudge = toJudge;
		this.repository = repository;
	}

	public void welcome(Person person) {
		System.out.println(String.format("Welcome  %s ", person.getSoul().getName()));
		repository.save(person);
		toJudge.send(MessageBuilder.withPayload(person.getSoul()).build());
	}

	public long deadCount() {
		return repository.count();
	}

}
