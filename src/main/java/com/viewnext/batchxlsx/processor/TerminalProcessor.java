package com.viewnext.batchxlsx.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Clase que maneja los distintos processors del batch
 */
@Configuration
@Component
public class TerminalProcessor{

	/**
	 * Processor sin ningun skip policy
	 * 
	 * @return TerminalItemProcessor, el processor sin skip policy
	 */
	@Bean("noPolicyProcessor")
	public TerminalItemProcessor processorTerminal() {
		return new TerminalItemProcessor();
	}
}
