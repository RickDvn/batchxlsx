package com.viewnext.batchxlsx.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class TerminalProcessor{

	@Bean("noPolicyProcessor")
	public TerminalItemProcessor processorTerminal() {
		return new TerminalItemProcessor();
	}
}
