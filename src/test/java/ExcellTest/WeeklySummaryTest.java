/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcellTest;

import com.faruk.karadenizkonfeksiyon.domain.Company;
import com.faruk.karadenizkonfeksiyon.domain.Expenses;
import com.faruk.karadenizkonfeksiyon.domain.Invoice;
import com.faruk.karadenizkonfeksiyon.domain.RemainingBalance;
import com.faruk.karadenizkonfeksiyon.util.ZonedDateTimeFormatterUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Faruk
 */
public class WeeklySummaryTest {
    
    String filePath = "C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\ESKİLER\\yapılan işler\\YILLIK YAPILAN İŞLER\\2005'de  yapılan  iş\\2005 ARALIK\\23,12,2005.xls";
    String date = "2005-07-29";
    ZonedDateTimeFormatterUtil dateTimeFormatterUtil = new ZonedDateTimeFormatterUtil();
    
    @Test
    @Ignore
    public void getCompanyInvoices() throws FileNotFoundException, IOException {
        org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("1350");
        
        File file = new File(filePath);
        System.out.println(file.getName());
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        Iterator<Row> rows = sheet.rowIterator();
        rows.next();
        rows.next();
        rows.next();
        
        List<Invoice> invoices = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            
            Row nextrow = rows.next();
            Invoice invoice = new Invoice();
            invoice.setDate(dateTimeFormatterUtil.convertToZonedDateTime(date));
            Iterator<Cell> cells = nextrow.cellIterator();
            
            for (int j = 0; j < 5; j++) {
                
                Cell nextCell = cells.next();
                
                if (nextCell.getCellType() == Cell.CELL_TYPE_STRING) {
                    invoice = setInvoicesValues(nextCell.getStringCellValue(), nextCell.getColumnIndex(), invoice);
                } else if (nextCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    invoice = setInvoicesValues(String.valueOf(nextCell.getNumericCellValue()), nextCell.getColumnIndex(), invoice);
                }
                
            }
            
            if (invoice.getCompany() != null) {
                invoices.add(invoice);
            }
            
        }
        
        System.out.println("Mission Completed");
        
    }
    
    private Invoice setInvoicesValues(String data, int columnIndex, Invoice invoice) {
        
        switch (columnIndex) {
            case 0: {
                invoice.setCompany(new Company(data));
                break;
            }
            case 1: {
                invoice.setDescription(data);
                break;
            }
            case 2: {
                invoice.setPiece(Integer.valueOf(data.substring(0, data.indexOf("."))));
                break;
            }
            case 3: {
                invoice.setUnitPrice(BigDecimal.valueOf(Double.valueOf(data)));
                invoice.setTotalPrice(BigDecimal.valueOf(invoice.getPiece() * invoice.getUnitPrice().doubleValue()));
                break;
            }
            case 4: {
                invoice.setTotalPrice(BigDecimal.valueOf(Double.valueOf(data)));
                break;
            }
        }
        
        return invoice;
    }
    
    @Test
    @Ignore
    public void getExpensesForWeekly() throws FileNotFoundException, IOException {
        
        org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("1350");
        
        File file = new File(filePath);
        System.out.println(file.getName());
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        List<Row> rowList = IteratorUtils.toList(sheet.rowIterator());
        
        List<Expenses> expenses = new ArrayList<>();
        
        for (int i = 3; i < 19; i++) {
            
            List<Cell> cellList = IteratorUtils.toList(rowList.get(i).cellIterator());
            
            Expenses expense = new Expenses();
            
            for (int j = 7; j < 10; j++) {
                
                if (cellList.get(j).getCellType() == Cell.CELL_TYPE_STRING) {
                    expense = setExpenseValues(cellList.get(j).getStringCellValue(), j, expense);
                } else if (cellList.get(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    expense = setExpenseValues(String.valueOf(cellList.get(j).getNumericCellValue()), j, expense);
                }
                
            }
            
            if (expense.getPrice() != null) {
                expense.setExpenseDate(dateTimeFormatterUtil.convertToZonedDateTime(date));
                expenses.add(expense);
            }
            
        }
        
        System.out.println("Tests are finished");
        
    }
    
    private Expenses setExpenseValues(String data, int index, Expenses expense) {
        
        switch (index) {
            
            case 7: {
                expense.setExpenseType(data);
                break;
            }
            case 8: {
                expense.setPrice(BigDecimal.valueOf(Double.valueOf(data)));
                break;
            }
            case 9: {
                expense.setDescription(data);
                break;
            }
            
        }
        
        return expense;
    }
    
    @Test
    @Ignore
    public void getRemainingBalances() throws FileNotFoundException, IOException {
        
        org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("1350");
        
        File file = new File(filePath);
        
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        List<RemainingBalance> remainingBalances = new ArrayList<>();
        
        List<Row> rowList = IteratorUtils.toList(sheet.rowIterator());
        
        for (int i = 10; i < 19; i++) {
            
            List<Cell> cellList = IteratorUtils.toList(rowList.get(i).cellIterator());
            RemainingBalance remainingBalance = new RemainingBalance();
            
            for (int j = 0; j < 4; j++) {
                
                if (cellList.get(j).getCellType() == Cell.CELL_TYPE_STRING) {
                    remainingBalance = setRemainingBalanceValues(cellList.get(j).getStringCellValue(), j, cellList.get(j).getCellType(), remainingBalance);
                } else if (cellList.get(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    remainingBalance = setRemainingBalanceValues(String.valueOf(cellList.get(j).getNumericCellValue()), j, cellList.get(j).getCellType(), remainingBalance);
                }
                
            }
            
            if (remainingBalance.getCompany() != null) {
                remainingBalance.setBalance_date(dateTimeFormatterUtil.convertToZonedDateTime(date));
                remainingBalances.add(remainingBalance);
            }
            
        }
        
        System.out.println("Mission Completed");
        
    }
    
    private RemainingBalance setRemainingBalanceValues(String data, int index, int cellType, RemainingBalance remainingBalance) {
        
        switch (index) {
            case 0: {
                remainingBalance.setCompany(new Company(data));
                break;
            }
            case 3: {
                if (cellType == Cell.CELL_TYPE_NUMERIC) {
                    remainingBalance.setPrice(BigDecimal.valueOf(Double.valueOf(data)));
                }else if (cellType == Cell.CELL_TYPE_STRING) {
                    remainingBalance.setDescription(data);
                }
                break;
            }
        }
        
        return remainingBalance;
    }
    
}
