package ru.otus.gromov.batch;

import de.flapdoodle.embed.mongo.MongodExecutable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.service.BookService;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BatchTest {
	@Autowired
	private JobLauncher launcher;
	@Autowired
	private Job job;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private BookService bookService;

	@Test
	public void importTestBook() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
		launcher.run(job, new JobParameters());
		int EXPECTED_COUNT = bookService.getAll().size();
		Book EXPECTED_BOOK = bookService.getById(1L);
		Assert.assertEquals(EXPECTED_COUNT, mongoTemplate.findAll(Book.class).size());
		Assert.assertEquals(EXPECTED_BOOK, mongoTemplate.findById(1L, Book.class));
	}
}