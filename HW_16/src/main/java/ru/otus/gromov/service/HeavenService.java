package ru.otus.gromov.service;

import io.micrometer.core.instrument.MeterRegistry;
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

@Service
public class HeavenService implements MessageHandler, World {
	private String slogan;
	private String chief;
	private final HeavenRepository reposetory;
	private final MeterRegistry mr;

	private final PublishSubscribeChannel gossip;

	@Autowired
	public HeavenService(@Qualifier("BeyondRumors") PublishSubscribeChannel gossip, HeavenRepository reposetory, MeterRegistry mr) {
		this.gossip = gossip;
		this.reposetory = reposetory;
		this.mr = mr;
		this.slogan = "Welcome to home";
		this.chief = "God";
		gossip.subscribe(this);
	}

	public void ascendToHeaven(Message message) {
		Soul saint = (Soul) message.getPayload();
		mr.counter("Heaven-income").increment();
		reposetory.save(saint);
		gossip.send(MessageBuilder
				.withPayload(String.format("Saint %s come to paradise!", saint.getName()))
				.setHeader("Smell", "Good")
				.build());
	}

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		if ("Good".equals(message.getHeaders().get("Smell")))
			System.out.println("Paradise gossip: " + message.getPayload());
	}

	public long getParadisePopulation() {
		return reposetory.count();
	}

	@Override
	public String getChiefName() {
		return chief;
	}

	@Override
	public String getSlogan() {
		return slogan;
	}

	@Override
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public long getPrayDuration() {
		return (long) (Math.random() * 100000);
	}
}
