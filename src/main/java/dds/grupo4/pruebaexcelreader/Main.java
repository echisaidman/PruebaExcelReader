package dds.grupo4.pruebaexcelreader;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        LectorExcel<RowDatoActividad> lectorExcel = new LectorExcel<>();
        List<RowDatoActividad> rows = lectorExcel.leerFilasDeExcel("static/PruebaExcelReader.xlsx", RowDatoActividad::fromRow, 2);
    }

}
