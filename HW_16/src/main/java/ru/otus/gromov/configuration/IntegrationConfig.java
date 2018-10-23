package ru.otus.gromov.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.PollableChannel;
import ru.otus.gromov.domain.Soul;
import ru.otus.gromov.service.HeavenService;
import ru.otus.gromov.service.HellService;

@Configuration
@EnableIntegration
public class IntegrationConfig {

	@Bean(name = "BeyondRumors")
	public PublishSubscribeChannel gossip() {
		return MessageChannels.publishSubscribe("BeyondRumors").get();
	}

	@Bean(name = "toJudge")
	public PollableChannel channelToJudge() {
		return new QueueChannel(150);
	}

	@Bean(name = "toHeaven")
	public PollableChannel channelToHeaven() {
		return new QueueChannel(150);
	}

	@Bean(name = "toHell")
	public PollableChannel channelToHell() {
		return new QueueChannel(150);
	}

	@Bean
	@Autowired
	public IntegrationFlow flowToJudge(@Qualifier("toJudge") PollableChannel pollableChannel) {
		return IntegrationFlows
				.from(pollableChannel)
				.<Soul, Boolean>route(
						(soul -> soul.getSins() > 0),
						mapping -> mapping
								.subFlowMapping(false, sf -> sf.channel("toHeaven"))
								.subFlowMapping(true, sf -> sf.channel("toHell"))
				).get();
	}

	@Bean
	@Autowired
	public IntegrationFlow flowToParadise(@Qualifier("toHeaven") PollableChannel pollableChannel, HeavenService heavenService) {
		return IntegrationFlows
				.from(pollableChannel)
				.handle(heavenService::ascendToHeaven)
				.get();
	}

	@Bean
	@Autowired
	public IntegrationFlow flowToHell(@Qualifier("toHell") PollableChannel pollableChannel, HellService hellService) {
		return IntegrationFlows
				.from(pollableChannel)
				.handle(hellService::burnInHell).get();
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller() {
		return Pollers.fixedRate(10).maxMessagesPerPoll(1).get();
	}
}
