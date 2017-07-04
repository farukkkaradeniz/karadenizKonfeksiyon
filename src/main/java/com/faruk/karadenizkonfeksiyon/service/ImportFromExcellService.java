/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service;

import com.faruk.karadenizkonfeksiyon.domain.ProfitPerMonth;
import com.faruk.karadenizkonfeksiyon.domain.ProfitPerYear;
import com.faruk.karadenizkonfeksiyon.repository.ProfitPerYearRepository;
import com.faruk.karadenizkonfeksiyon.util.BigDecimalUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

            System.out.println("finiş");

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

}
