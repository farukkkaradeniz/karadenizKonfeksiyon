/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.faruk.karadenizkonfeksiyon.Application;
import com.faruk.karadenizkonfeksiyon.builder.ExpenseBuilder;
import com.faruk.karadenizkonfeksiyon.domain.Company;
import com.faruk.karadenizkonfeksiyon.domain.Employee;
import com.faruk.karadenizkonfeksiyon.domain.Invoice;
import com.faruk.karadenizkonfeksiyon.repository.CompanyRepository;
import com.faruk.karadenizkonfeksiyon.repository.EmployeeRepository;
import com.faruk.karadenizkonfeksiyon.repository.ExpensesRepository;
import com.faruk.karadenizkonfeksiyon.repository.InvoiceRepository;
import com.faruk.karadenizkonfeksiyon.util.ZonedDateTimeFormatterUtil;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.websocket.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Faruk
 */
@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ImportRecordsToDbTest {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private ZonedDateTimeFormatterUtil zonedDateTimeFormatterUtil;

    @Test
    @Ignore
    public void insertEmployee() {

        Employee employee = new Employee();
        employee.setEnabled(Boolean.TRUE);
        employee.setFirstName("Faruk");
        employee.setLastName("Karadeniz");
        employee.setSalary(BigDecimal.valueOf(450.00));
        employee.setShiftSalary(BigDecimal.ZERO);

        employeeRepository.save(employee);

    }

    @Test
    @Ignore
    public void insertExpense() {

        List<Employee> employess = employeeRepository.findAllByEnabled(Boolean.TRUE);

        double prices = 0.00;

        for (Employee employes : employess) {
            prices += employes.getSalary().doubleValue();
            prices += employes.getShiftSalary().doubleValue();
        }

        ExpenseBuilder builder = new ExpenseBuilder();
        expensesRepository.save(builder
                .description("İşçilik")
                .expenseType("İşçilik")
                .price(prices)
                .expenseDate(zonedDateTimeFormatterUtil.convertToZonedDateTime("2017-06-18"))
                .build());

    }

    private Company insertCompany() {

        Company company = new Company();
        company.setCompanyName("Karadeniz Konfeksiyon");
        return companyRepository.save(company);

    }

    @Test
    @Ignore
    public void insertInvoice() {

        Invoice invoice = new Invoice();

        invoice.setCompany(insertCompany());
        invoice.setDescription("Hakim Yaka");
        invoice.setPiece(355);
        invoice.setUnitPrice(BigDecimal.valueOf(35.00));
        invoice.setTotalPrice(BigDecimal.valueOf(35.00 * 355));
        invoice.setDate(zonedDateTimeFormatterUtil.convertToZonedDateTime("2017-06-18"));

        invoiceRepository.save(invoice);
    }
    
    
}
