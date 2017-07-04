package ExcellTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Faruk-pc
 */
public class KazancTestYillik {

    @Test
    @Ignore
    public void excellTest() {
        try {
            org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("1350");

            List<BigDecimal> gelirler = new ArrayList<>();
            List<BigDecimal> giderler = new ArrayList<>();

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\K Z TABLOSU 2007\\YILLIK K Z.xls")));

            HSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.rowIterator();

            rows.next();

            String cellValueType = "";

            while (rows.hasNext()) {

                Row nextRow = rows.next();

                Iterator<Cell> cells = nextRow.cellIterator();

                while (cells.hasNext()) {

                    Cell nextCell = cells.next();

                    if (nextCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        if (cellValueType.equals("GELİR")) {
                            gelirler.add(BigDecimal.valueOf(nextCell.getNumericCellValue()));
                        } else if (cellValueType.equals("GİDER")) {
                            giderler.add(BigDecimal.valueOf(nextCell.getNumericCellValue()));
                        }
                        
                    } else if (nextCell.getCellType() == Cell.CELL_TYPE_STRING) {
                        System.out.println("asd");
                        cellValueType = nextCell.getStringCellValue();
                    }

                }
            }
            
            System.out.println("finiş");
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
