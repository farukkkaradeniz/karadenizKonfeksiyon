/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service;

import com.faruk.karadenizkonfeksiyon.domain.Company;
import com.faruk.karadenizkonfeksiyon.domain.Expenses;
import com.faruk.karadenizkonfeksiyon.domain.Invoice;
import com.faruk.karadenizkonfeksiyon.domain.ProfitPerMonth;
import com.faruk.karadenizkonfeksiyon.domain.ProfitPerYear;
import com.faruk.karadenizkonfeksiyon.domain.RemainingBalance;
import com.faruk.karadenizkonfeksiyon.repository.ProfitPerYearRepository;
import com.faruk.karadenizkonfeksiyon.service.database.CompanyService;
import com.faruk.karadenizkonfeksiyon.service.database.ExpensesService;
import com.faruk.karadenizkonfeksiyon.service.database.InvoiceService;
import com.faruk.karadenizkonfeksiyon.service.database.RemainingBalanceService;
import com.faruk.karadenizkonfeksiyon.util.BigDecimalUtil;
import com.faruk.karadenizkonfeksiyon.util.ZonedDateTimeFormatterUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Faruk-pc
 */
@Service
public class ImportFromExcellService {

    @Autowired
    private BigDecimalUtil bigDecimalUtil;

    @Autowired
    private ProfitPerYearRepository profitPerYearRepository;

    @Autowired
    private ZonedDateTimeFormatterUtil dateTimeFormatterUtil;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ExpensesService expensesService;

    @Autowired
    private RemainingBalanceService remainingBalanceService;

    private String date;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public ProfitPerYear importYillikKarZararFromExcell(File file, Long year) {

        try {

            ProfitPerYear profitPerYear = new ProfitPerYear();

            profitPerYear.setValue(year);

            org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("1350");

            List<BigDecimal> gelirler = new ArrayList<>();
            List<BigDecimal> giderler = new ArrayList<>();

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));

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
                    } else if (nextCell.getCellType() == Cell.CELL_TYPE_BLANK) {
                        if (cellValueType.equals("GELİR")) {
                            gelirler.add(BigDecimal.valueOf(0.00));
                        } else if (cellValueType.equals("GİDER")) {
                            giderler.add(BigDecimal.valueOf(0.00));
                        }
                    }

                }
            }

            List<ProfitPerMonth> profitPerMonths = new ArrayList<>();

            double earningMoney = 0.00;
            double spendingMoney = 0.00;

            for (int i = 0; i < 12; i++) {
                profitPerMonths.add(new ProfitPerMonth(Month.values()[i], giderler.get(i), gelirler.get(i), profitPerYear));
                earningMoney += gelirler.get(i).doubleValue();
                spendingMoney += giderler.get(i).doubleValue();
            }

            profitPerYear.setProfitPerMonths(profitPerMonths);
            profitPerYear.setTotalEarnings(BigDecimal.valueOf(earningMoney));
            profitPerYear.setTotalSpending(BigDecimal.valueOf(spendingMoney));
            profitPerYear.setProfit(BigDecimal.valueOf(profitPerYear.getTotalEarnings().doubleValue() - profitPerYear.getTotalSpending().doubleValue()));
            ProfitPerYear result = profitPerYearRepository.save(profitPerYear);
            return result;

        } catch (IOException e) {
            Logger.getLogger(ImportFromExcellService.class.getName()).log(Level.SEVERE, "Hata", e);
            throw new IllegalStateException(e);
        }

    }

    public void importWeekleySummery(String fileDirPath) {
        File file = new File(fileDirPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File file1 : files) {
                this.date = file1.getName().substring(0, file1.getName().indexOf(".x")).replaceAll(",", "-");
                getCompanyInvoices(file1);
                getExpensesForWeekly(file1);
                getRemainingBalances(file1);
            }

        }

    }

    private List<Invoice> getCompanyInvoices(File file) {

        try {
            org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("1350");

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
                    invoice.setDate(dateTimeFormatterUtil.convertToZonedDateTime(date, dtf));
                    invoice = invoiceService.save(invoice);
                    invoices.add(invoice);
                }

            }

            return invoices;

        } catch (IOException e) {
            Logger.getLogger(ImportFromExcellService.class.getName()).log(Level.SEVERE, "Invoice Error", e);
            throw new IllegalStateException("Invoice Error");
        }

    }

    private Invoice setInvoicesValues(String data, int columnIndex, Invoice invoice) {

        switch (columnIndex) {
            case 0: {
                invoice.setCompany(checkCompany(data));
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

    private List<Expenses> getExpensesForWeekly(File file) {

        try {
            org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("1350");

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
                    expense.setExpenseDate(dateTimeFormatterUtil.convertToZonedDateTime(date, dtf));
                    expense = expensesService.save(expense);
                    expenses.add(expense);
                }

            }

            return expenses;

        } catch (IOException e) {
            Logger.getLogger(ImportFromExcellService.class.getName()).log(Level.SEVERE, "Gider Hatası", e);
            throw new IllegalStateException("Giderleri Kontrol Ediniz");
        }

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

    private List<RemainingBalance> getRemainingBalances(File file) {

        try {
            org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword("1350");

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
                    remainingBalance.setBalance_date(dateTimeFormatterUtil.convertToZonedDateTime(date,dtf));
                    remainingBalance = remainingBalanceService.save(remainingBalance);
                    remainingBalances.add(remainingBalance);
                }
            }

            return remainingBalances;

        } catch (IOException e) {
            Logger.getLogger(ImportFromExcellService.class.getName()).log(Level.SEVERE, "Tahsilat Tipi Çevirilemedi", e);
            throw new IllegalStateException("Tahsilat Tipi Çevirilemedi");
        }

    }

    private RemainingBalance setRemainingBalanceValues(String data, int index, int cellType, RemainingBalance remainingBalance) {
        switch (index) {
            case 0: {
                remainingBalance.setCompany(checkCompany(data));
                break;
            }
            case 3: {
                if (cellType == Cell.CELL_TYPE_NUMERIC) {
                    remainingBalance.setPrice(BigDecimal.valueOf(Double.valueOf(data)));
                } else if (cellType == Cell.CELL_TYPE_STRING) {
                    remainingBalance.setDescription(data);
                }
                break;
            }
        }
        return remainingBalance;
    }

    private Company checkCompany(String companyName) {

        List<Company> companies = companyService.findAllLikeCompanyName(companyName);

        if (companies.size() > 0) {
            return companies.get(0);
        } else {
            System.out.println("şöğĞŞÖ");
            return companyService.save(new Company(companyName));
        }

    }

}
