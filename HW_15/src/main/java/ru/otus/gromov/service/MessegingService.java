package ru.otus.gromov.service;

import jdk.packager.internal.legacy.builders.mac.MacAppImageBuilder;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.otus.gromov.domain.Book;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class MessegingService {
	private final PollableChannel PollableChannel;
	private final DirectChannel recieveChannel;

	public MessegingService(PollableChannel PollableChannel, DirectChannel recieveChannel) {
		this.PollableChannel = PollableChannel;
		this.recieveChannel = recieveChannel;
	}

	public Book getBookById(long id) {
		Map<String, Object> headerMap = new HashMap<>();
		headerMap.put("operation", "get");
		headerMap.put("BookId", id);
		MessageHeaders headers = new MessageHeaders(headerMap);

		Message<String> message = new GenericMessage<>("Balast", headers);

		PollableChannel.send(message);

		return (Book) Objects.requireNonNull(PollableChannel.receive()).getPayload();
	}
}
