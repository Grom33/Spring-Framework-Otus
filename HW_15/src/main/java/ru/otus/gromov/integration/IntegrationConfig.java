package ru.otus.gromov.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.PollableChannel;

@Configuration
@EnableIntegration
public class IntegrationConfig {

	@Bean
	public DirectChannel chanel() {
		return MessageChannels.direct("BookChanel").get();
	}

	@Bean
	public PollableChannel channelPoll() {
		return new QueueChannel(100);
	}

	@Bean
	@Autowired
	public IntegrationFlow bookserv(PollableChannel pollableChannel) {
		return f -> f
				.log()
				.channel(pollableChannel)
				.log();
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller(){
		return Pollers.fixedRate(10).maxMessagesPerPoll(1).get();
	}

}
