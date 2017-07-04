/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.faruk.karadenizkonfeksiyon.Application;
import com.faruk.karadenizkonfeksiyon.domain.Invoice;
import com.faruk.karadenizkonfeksiyon.service.database.InvoiceService;
import com.faruk.karadenizkonfeksiyon.util.ZonedDateTimeFormatterUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Faruk
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(Application.class)
public class InvoiceEntityTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ZonedDateTimeFormatterUtil zonedDateTimeFormatterUtil;

    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void testBeetweenDates() {

        List<Invoice> service = invoiceService.findAllBeetweenDates(zonedDateTimeFormatterUtil.convertToZonedDateTime("2017-06-01"), zonedDateTimeFormatterUtil.convertToZonedDateTime("2017-07-01"));

    }

}
