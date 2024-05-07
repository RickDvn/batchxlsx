package com.viewnext.batchxlsx.processor;

import org.springframework.batch.item.ItemProcessor;

import com.viewnext.batchxlsx.model.Terminal;

public class TerminalItemProcessor implements ItemProcessor<Terminal, Terminal>{

	@Override
	public Terminal process(Terminal item) throws Exception {
		return item;
	}

}
