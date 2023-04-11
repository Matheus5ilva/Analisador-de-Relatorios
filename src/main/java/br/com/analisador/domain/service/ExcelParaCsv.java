package br.com.analisador.domain.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelParaCsv {

    private static final Logger logger = LoggerFactory.getLogger(ExcelParaCsv.class);

    public static String convertXlsxToCsv(InputStream inputStream) throws IOException {
        // Cria um workbook a partir do InputStream, detectando automaticamente o formato
        Workbook workbook = WorkbookFactory.create(inputStream);

        // Obtém a primeira planilha do workbook
        Sheet sheet = workbook.getSheetAt(0);

        // Cria um stream para armazenar o resultado em CSV
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Loop pelas linhas da planilha
        for (Row row : sheet) {
            // Loop pelas células de cada linha
            for (Cell cell : row) {
                // Adiciona a célula ao CSV, separando por vírgula
                if (cell.getCellType() == CellType.STRING) {
                    outputStream.write(cell.getStringCellValue().getBytes());
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    outputStream.write(Double.toString(cell.getNumericCellValue()).getBytes());
                }
                outputStream.write(",".getBytes());
            }
            // Adiciona uma quebra de linha no final de cada linha
            outputStream.write("\n".getBytes());
        }

        // Log do CSV gerado
        logger.info("CSV gerado com sucesso.");

        // Retorna o CSV como uma String
        return outputStream.toString();
    }
}
