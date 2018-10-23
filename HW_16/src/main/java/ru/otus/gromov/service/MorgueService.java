package ru.otus.gromov.service;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otus.gromov.domain.Person;
import ru.otus.gromov.repository.NecrologueRepository;

@Service
@AllArgsConstructor
public class MorgueService {

	private final PollableChannel toJudge;
	private final NecrologueRepository repository;
	private final MeterRegistry mr;

	public void restInPeace(Person person) {
		mr.counter("Morgue-income").increment();
		System.out.println(String.format("Rest in peace  %s ", person.getSoul().getName()));
		repository.save(person);
		toJudge.send(MessageBuilder.withPayload(person.getSoul()).build());
	}

	public long deadCount() {
		return repository.count();
	}

}
