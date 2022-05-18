package dds.grupo4.pruebaexcelreader;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class LectorExcel<T> {

    public List<T> leerFilasDeExcel(String rutaArchivo, Function<Row, T> rowCreator, int cantidadDeFilasParaHeader) {
        URL resource = getClass().getClassLoader().getResource(rutaArchivo);
        String filePath = resource.getPath();

        try (Workbook wb = WorkbookFactory.create(new File(filePath))) {
            Sheet sheet = wb.getSheet("Hoja1");

            Iterator<Row> rowIterator = sheet.rowIterator();

            // Me salteo las primeras filas, que son los titulos de la tabla
            for (int i = 0; i < cantidadDeFilasParaHeader; i++) {
                rowIterator.next();
            }

            List<T> rows = new ArrayList<>();
            // Recorro todas las filas, y freno cuando la primera celda de la fila este vacia
            Row row = rowIterator.next();
            while (!row.getCell(0).getStringCellValue().isEmpty()) {
                T processedRow = rowCreator.apply(row);
                rows.add(processedRow);

                String message = "F" + row.getRowNum() + ": " + processedRow.toString();
                System.out.println(message);

                row = rowIterator.next();
            }

            return rows;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
