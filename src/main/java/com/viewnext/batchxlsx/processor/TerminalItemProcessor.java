package com.viewnext.batchxlsx.processor;

import org.springframework.batch.item.ItemProcessor;

import com.viewnext.batchxlsx.model.Terminal;

/**
 * Processor sin skip policy
 */
public class TerminalItemProcessor implements ItemProcessor<Terminal, Terminal>{

	/**
	 * Devuelve el objeto sin modificar
	 */
	@Override
	public Terminal process(Terminal item) throws Exception {
		return item;
	}

}
