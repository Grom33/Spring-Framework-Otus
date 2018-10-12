package ru.otus.gromov.batch;

import com.mongodb.MongoClient;
import com.sun.prism.impl.Disposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.repository.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private BookRepository bookRepository;



    @Bean
    public MongoItemWriter<Book> writer() throws Exception {
        return new MongoItemWriterBuilder<Book>()
                .template(mongoTemplate())
                .collection(Book.class.getSimpleName())
                .build();
    }

    @Bean
    public ItemReader<Book> reader(){
        RepositoryItemReader<Book> reader = new RepositoryItemReader<>();
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("name", Sort.Direction.ASC);

        reader.setRepository(bookRepository);
        reader.setSort(sort);
        reader.setMethodName("findAll");
        return reader;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), "db-name");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

    @Bean
    public ItemProcessor processor() {
        return (ItemProcessor<Book, Book>) book -> book;
    }

    /*@Bean
    public FlatFileItemWriter writer() {
        return new FlatFileItemWriterBuilder<>()
                .name("personItemWriter")
                .resource(new FileSystemResource("output.csv"))
                .lineAggregator(new DelimitedLineAggregator<>())
                .build();
    }*/

    @Bean
    public Job importBookJob(Step step1) {
        return jobBuilderFactory.get("importBookJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        logger.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step step1(MongoItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .listener(new ItemReadListener() {
                    public void beforeRead() { logger.info("Начало чтения"); }
                    public void afterRead(Object o) { logger.info("Конец чтения"); }
                    public void onReadError(Exception e) { logger.info("Ошибка чтения"); }
                })
                .listener(new ItemWriteListener() {
                    public void beforeWrite(List list) { logger.info("Начало записи"); }
                    public void afterWrite(List list) { logger.info("Конец записи"); }
                    public void onWriteError(Exception e, List list) { logger.info("Ошибка записи"); }
                })
                .listener(new ItemProcessListener() {
                    public void beforeProcess(Object o) {logger.info("Начало обработки");}
                    public void afterProcess(Object o, Object o2) {logger.info("Конец обработки");}
                    public void onProcessError(Object o, Exception e) {logger.info("Ошбка обработки");}
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {logger.info("Начало пачки");}
                    public void afterChunk(ChunkContext chunkContext) {logger.info("Конец пачки");}
                    public void afterChunkError(ChunkContext chunkContext) {logger.info("Ошибка пачки");}
                })
                .build();
    }

    /*@Bean
    public JobLauncher jobLauncher(JobRepository repository){
        JobLauncher launcher = new SimpleJobLauncher();
        ((SimpleJobLauncher) launcher).setJobRepository(repository);
        ((SimpleJobLauncher) launcher).setTaskExecutor(Runnable::run);
    return launcher;
    }*/

}
