/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author Faruk
 */
@Component
public class ZonedDateTimeFormatterUtil {
    
    public ZonedDateTime convertToZonedDateTime(String date){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        LocalDate localDate = LocalDate.parse(date,formatter);
        
        return ZonedDateTime.of(localDate,LocalTime.MIDNIGHT, ZoneId.of("Europe/Istanbul"));
        
    }
    
    public ZonedDateTime convertToZonedDateTime(String date,DateTimeFormatter dateTimeFormatter){
    
        return ZonedDateTime.of(LocalDate.parse(date, dateTimeFormatter),LocalTime.MIDNIGHT,ZoneId.of("Europe/Istanbul"));
        
    }
    
    public ZonedDateTime convertToZonedDateTime(String date,DateTimeFormatter dateTimeFormatter,String zoneId){
    
        return ZonedDateTime.of(LocalDate.parse(date, dateTimeFormatter),LocalTime.MIDNIGHT,ZoneId.of(zoneId));
        
    }
    
}
