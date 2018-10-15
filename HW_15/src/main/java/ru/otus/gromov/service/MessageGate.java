package ru.otus.gromov.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.gromov.domain.Book;

@MessagingGateway
public interface MessageGate {

	@SuppressWarnings("UnresolvedMessageChannel")
	@Gateway(requestChannel = "bookserv.input")
	Book getBook(long id);

}
