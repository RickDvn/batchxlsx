package com.viewnext.batchxlsx.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import com.viewnext.batchxlsx.model.Terminal;

@Configuration
@Component
public class TerminalWriter {
	
	/**
	 * El writer para escribir en el csv
	 * 
	 * @return El writer que escribirá en el csv
	 */
	@Bean(value = "writerLocal")
	public FlatFileItemWriter<Terminal> writerLocal() {
		return new FlatFileItemWriterBuilder<Terminal>().name("terminalItemWriter")
				.resource(new PathResource("src/main/resources/data/local/terminales.csv")).delimited()
				.names("id", "name", "description", "code")
				.build();
	}
}
