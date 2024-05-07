package com.viewnext.batchxlsx.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.viewnext.batchxlsx.listener.JobCompletionNotificationListener;
import com.viewnext.batchxlsx.model.Terminal;
import com.viewnext.batchxlsx.processor.TerminalItemProcessor;


@Configuration
public class BatchConfig {

	@Bean
	public Job importUserJob (JobRepository jobRepository, Step step1, Step step2, JobCompletionNotificationListener listener) {
		return new JobBuilder("importUserJob", jobRepository)
				.listener(listener)
				.start(step1)
				.build();
	}
	
	/**
	 * Escribir en un csv en local
	 * 
	 * @param jobRepository
	 * @param transactionManager
	 * @param reader
	 * @param processor
	 * @param writer
	 * @return
	 */
	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<Terminal> reader,
			@Qualifier("noPolicyProcessor") TerminalItemProcessor processor, @Qualifier("writerLocal") FlatFileItemWriter<Terminal> writer) {
		return new StepBuilder("step1", jobRepository)
				.<Terminal, Terminal> chunk(3, transactionManager)
				.allowStartIfComplete(true)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
}
