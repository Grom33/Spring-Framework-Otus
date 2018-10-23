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
import ru.otus.gromov.repository.HellRepository;


@Service
public class HellService implements MessageHandler, World {
	private String slogan;
	private String chief;
	private HellRepository boiler;
	private final PublishSubscribeChannel gossip;
	private final MeterRegistry mr;

	@Autowired
	public HellService(@Qualifier("BeyondRumors") PublishSubscribeChannel gossip, HellRepository repository, MeterRegistry mr) {
		this.boiler = repository;
		this.gossip = gossip;
		this.mr = mr;
		this.slogan = "Lasciate ogni speranza, voi ch'entrate";
		this.chief = "Devil";
		gossip.subscribe(this);
	}

	public void burnInHell(Message message) {
		mr.counter("Hell-income").increment();
		Soul sinner = (Soul) message.getPayload();
		boiler.save(sinner);
		gossip.send(MessageBuilder
				.withPayload(String.format("Sinner %s come to hell!", sinner.getName()))
				.setHeader("Smell", "Bad")
				.build());
	}

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		if ("Bad".equals(message.getHeaders().get("Smell"))) System.out.println("Hell gossip: " + message.getPayload());
	}

	public long getHellPopulation() {
		return boiler.count();

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
}
