package com.viewnext.batchxlsx.writer;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

public class TerminalHeaderWriter implements FlatFileHeaderCallback {

	private final String header;

	TerminalHeaderWriter(String header) {
        this.header = header;
    }

	@Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write(header);
    }
}