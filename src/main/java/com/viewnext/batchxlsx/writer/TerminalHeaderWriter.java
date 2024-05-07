package com.viewnext.batchxlsx.writer;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

/**
 * Clase que se encarga de escribir la cabecera de los csv
 */
public class TerminalHeaderWriter implements FlatFileHeaderCallback {

	private final String header;

	/**
	 * Constructor de la clase
	 * 
	 * @param header, la cabecera
	 */
	TerminalHeaderWriter(String header) {
        this.header = header;
    }

	/**
	 * Metodo por el cual escribe la cabecera
	 */
	@Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write(header);
    }
}