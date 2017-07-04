/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java8Test;

import com.faruk.karadenizkonfeksiyon.util.ZonedDateTimeFormatterUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.Ignore;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;

/**
 *
 * @author Faruk
 */
public class ZonedDateTimeTest {

    @Test
    @Ignore
    public void test() {

        System.out.println(ZonedDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2017-07-03", formatter);

        ZonedDateTime dateTime = ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, ZoneId.of("Europe/Istanbul"));
        System.out.println(dateTime);

    }

    @Test
    @Ignore
    public void testDate() {

        String startDate = "" + ZonedDateTime.now().getYear() + "-" + Month.JANUARY.getValue() + "-" + "01";

        String endDate = "" + ZonedDateTime.now().getYear() + "-" + Month.MAY.getValue() + "-" + "01";

        ZonedDateTimeFormatterUtil dateTimeFormatterUtil = new ZonedDateTimeFormatterUtil();

        System.out.println(dateTimeFormatterUtil.convertToZonedDateTime(endDate));
        System.out.println(dateTimeFormatterUtil.convertToZonedDateTime(endDate));

        System.out.println(startDate);
        System.out.println(endDate);

    }

}
