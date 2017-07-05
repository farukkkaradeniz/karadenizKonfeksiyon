/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcellTest;

import com.faruk.karadenizkonfeksiyon.domain.Company;
import com.faruk.karadenizkonfeksiyon.domain.Invoice;
import com.faruk.karadenizkonfeksiyon.domain.RemainingBalance;
import com.faruk.karadenizkonfeksiyon.util.ZonedDateTimeFormatterUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Faruk
 */
public class CompanySummaryTest {

    private ZonedDateTimeFormatterUtil zonedDateTimeFormatterUtil = new ZonedDateTimeFormatterUtil();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd,MM,yyyy");

    @Test
    @Ignore
    public void testCompanyInformations() {

        try {

            org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("1350");

            Company company = new Company();

            File file = new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/ESKİLER/FİRMALAR2012/MEBA.xls");

            company.setCompanyName(file.getName().substring(0, file.getName().indexOf(".xls")));

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));

            List<Row> rowList = IteratorUtils.toList(workbook.getSheetAt(0).rowIterator());

            List<Invoice> invoices = new ArrayList<>();

            List<RemainingBalance> remainingBalances = new ArrayList<>();

            for (int i = 2; i < rowList.size(); i++) {

                Row nextRow = rowList.get(i);

                List<Cell> cellList = IteratorUtils.toList(nextRow.cellIterator());

                Invoice invoice = new Invoice();

                invoice.setCompany(company);

                RemainingBalance remainingBalance = new RemainingBalance();

                remainingBalance.setCompany(company);

                for (int j = 0; j < cellList.size(); j++) {
                    if (j < 6) {
                        invoice = setInvoiceValues(invoice, cellList.get(j));
                    } else if (j > 7) {
                        remainingBalance = setRemainingBalancesValues(remainingBalance, cellList.get(j));
                    }
                }

                if (invoice.getDate() != null) {
                    invoices.add(invoice);
                }

                if (remainingBalance.getBalance_date() != null) {
                    remainingBalances.add(remainingBalance);
                }

            }

            System.out.println("dedsa");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Invoice setInvoiceValues(Invoice invoice, Cell cellVal) {

        String data = "0.00";

        if (cellVal.getCellType() == Cell.CELL_TYPE_STRING) {
            data = cellVal.getStringCellValue();
        } else if (cellVal.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            data = String.valueOf(cellVal.getNumericCellValue());
        } else if (cellVal.getCellType() == Cell.CELL_TYPE_BLANK) {
            return invoice;
        }

        switch (cellVal.getColumnIndex()) {
            case 0: {
                invoice.setDate(zonedDateTimeFormatterUtil.convertToZonedDateTime(data, dtf));
                break;
            }
            case 1: {
                invoice.setDescription(data);
                break;
            }
            case 3: {
                if (data.contains(".")) {
                    invoice.setPiece(Integer.valueOf(data.substring(0, data.indexOf("."))));
                } else {
                    invoice.setPiece(Integer.valueOf(data));
                }

                break;

            }
            case 4: {

                invoice.setUnitPrice(BigDecimal.valueOf(Double.valueOf(data)));
                break;

            }
            case 5: {
                invoice.setTotalPrice(BigDecimal.valueOf(Double.valueOf(data)));
                break;
            }
        }

        return invoice;

    }

    private RemainingBalance setRemainingBalancesValues(RemainingBalance remainingBalance, Cell cellVal) {
        String data = "";

        if (cellVal.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            data = String.valueOf(cellVal.getNumericCellValue());
        } else if (cellVal.getCellType() == Cell.CELL_TYPE_STRING) {
            data = cellVal.getStringCellValue();
        } else if (cellVal.getCellType() == Cell.CELL_TYPE_BLANK) {
            return remainingBalance;
        }

        switch (cellVal.getColumnIndex()) {
            case 8: {
                remainingBalance.setBalance_date(zonedDateTimeFormatterUtil.convertToZonedDateTime(data, dtf));
                break;
            }
            case 9: {
                remainingBalance.setDescription(data);
                break;
            }
            case 11: {
                remainingBalance.setPrice(BigDecimal.valueOf(Double.valueOf(data)));
                break;
            }
        }

        return remainingBalance;
    }

}
