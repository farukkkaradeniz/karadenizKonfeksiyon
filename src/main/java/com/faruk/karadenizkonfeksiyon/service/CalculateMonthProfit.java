/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service;

import com.faruk.karadenizkonfeksiyon.domain.Expenses;
import com.faruk.karadenizkonfeksiyon.domain.Invoice;
import com.faruk.karadenizkonfeksiyon.domain.ProfitPerMonth;
import com.faruk.karadenizkonfeksiyon.model.Months;
import com.faruk.karadenizkonfeksiyon.service.database.ExpensesService;
import com.faruk.karadenizkonfeksiyon.service.database.InvoiceService;
import com.faruk.karadenizkonfeksiyon.service.database.ProfitPerMonthService;
import com.faruk.karadenizkonfeksiyon.service.database.ProfitPerYearService;
import com.faruk.karadenizkonfeksiyon.util.ZonedDateTimeFormatterUtil;
import java.math.BigDecimal;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Faruk
 */
@Service
public class CalculateMonthProfit {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ExpensesService expensesService;

    @Autowired
    private ProfitPerYearService profitPerYearService;

    @Autowired
    private ProfitPerMonthService profitPerMonthService;

    @Autowired
    private ZonedDateTimeFormatterUtil zonedDateTimeFormatterUtil;

    public ProfitPerMonth calculeteMonthProfit(Month month) {

        Double expensePrice = 0.00;
        Double invoicePrice = 0.00;
        String startDate;
        String endDate;

        if (month.getValue() >= 10) {
            startDate = "" + ZonedDateTime.now().getYear() + "-" + month.getValue() + "-" + "01";
            endDate = "" + ZonedDateTime.now().getYear() + "-" + (month.getValue() + 1) + "-" + "01";
        } else {
            startDate = "" + ZonedDateTime.now().getYear() + "-0" + month.getValue() + "-" + "01";
            if (month.getValue() == 9) {
                endDate = "" + ZonedDateTime.now().getYear() + "-" + (month.getValue() + 1) + "-" + "01";
            } else {
                endDate = "" + ZonedDateTime.now().getYear() + "-0" + (month.getValue() + 1) + "-" + "01";
            }
        }

        System.out.println(startDate);
        System.out.println(endDate);

        List<Invoice> invoices = invoiceService.findAllBeetweenDates(zonedDateTimeFormatterUtil.convertToZonedDateTime(startDate), zonedDateTimeFormatterUtil.convertToZonedDateTime(endDate));

        for (Invoice invoice : invoices) {
            invoicePrice += invoice.getTotalPrice().doubleValue();
        }

        List<Expenses> expenses = expensesService.findAllBeetweenDates(zonedDateTimeFormatterUtil.convertToZonedDateTime(startDate), zonedDateTimeFormatterUtil.convertToZonedDateTime(endDate));

        for (Expenses expense : expenses) {
            expensePrice += expense.getPrice().doubleValue();
        }

        ProfitPerMonth profitPerMonth = new ProfitPerMonth(month, BigDecimal.valueOf(expensePrice), BigDecimal.valueOf(invoicePrice), profitPerYearService.findOneByValue((long) ZonedDateTime.now().getYear()));

        return profitPerMonth;

    }

}
