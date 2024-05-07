package com.viewnext.batchxlsx.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.viewnext.batchxlsx.model.Terminal;

@Configuration
@Component
public class TerminalReader {

	private static final Logger log = LoggerFactory.getLogger(TerminalReader.class);
	
	private static final String urlExcel = "src/main/resources/data/local/terminales.xlsx";
	
	@Bean
	public ItemReader<Terminal> readerTerminalLocal() throws IOException{
		return new ItemReader<Terminal>() {
			private List<Terminal> lTerminales = readExcelFile();
			private int index = 0;
			
			@Override
			public Terminal read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				if (index < lTerminales.size()) {
					return lTerminales.get(index++);
				}else {
					return null;
				}
			}
		};
	}
	
	private ArrayList<Terminal> readExcelFile() throws IOException{
		
		
        log.info(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("LEYENDO XLSX CONVERGENTES");
        File excelFile = new File(this.urlExcel);
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheetAt(0);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();
        ArrayList<Terminal> mapRetorno = new ArrayList<>();
        Terminal xlxsActual = new Terminal();
        int numColumn = 0;

        Row row = rowIt.next();//Saltar Cabecera
        while(rowIt.hasNext()){
            row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();
            numColumn = 0;
            xlxsActual = new Terminal();

            while(cellIterator.hasNext()){
                Cell cell = cellIterator.next();

                switch(numColumn){
                case 0:{
                    xlxsActual.setId(cell.toString());
                    break;
                }
                case 1:{
                    xlxsActual.setName(cell.toString());
                    break;
                }
                case 2:{
                    xlxsActual.setDescription(cell.toString());
                    break;
                }
                case 3:{
                    xlxsActual.setCode(Integer.parseInt(cell.toString().replace(".0", "")));
                    break;
                }
                default:{
                    break;
                }

                }
                numColumn++;

            }
            mapRetorno.add(xlxsActual);

        }

        workbook.close();
        fis.close();

        log.info("Promos Leidas del fichero: " + mapRetorno.size());

        log.info("Fin Lector XLSX");
        return mapRetorno;
    }
}
