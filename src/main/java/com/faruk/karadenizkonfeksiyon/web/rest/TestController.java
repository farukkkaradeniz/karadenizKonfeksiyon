/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.web.rest;

import com.faruk.karadenizkonfeksiyon.domain.ProfitPerMonth;
import com.faruk.karadenizkonfeksiyon.domain.ProfitPerYear;
import com.faruk.karadenizkonfeksiyon.service.CalculateMonthProfit;
import com.faruk.karadenizkonfeksiyon.service.ImportFromExcellService;
import com.faruk.karadenizkonfeksiyon.service.database.ProfitPerMonthService;
import java.io.File;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Faruk-pc
 */
@RestController
public class TestController {

    private final String fileDir = "C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\ESKİLER\\yapılan işler\\YILLIK YAPILAN İŞLER";
    @Autowired
    private ImportFromExcellService excellService;

    @Autowired
    private CalculateMonthProfit calculateMonthProfit;

    @GetMapping("/test/excell")
    public ProfitPerYear importFromExcell() {

//        excellService.importYillikKarZararFromExcell(new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/K.Z TABLOLARI/KZ TABLOSU 2013/YILLIK K Z 2013.xls"), (long) 2013);
//        excellService.importYillikKarZararFromExcell(new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/K.Z TABLOLARI/KZ TABLOSU 2012/YILLIK K Z 2012.xls"), (long) 2012);
//        excellService.importYillikKarZararFromExcell(new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/K.Z TABLOLARI/KZ TABLOSU 2011/YILLIK K Z 2010.xls"), (long) 2011);
//        excellService.importYillikKarZararFromExcell(new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/K.Z TABLOLARI/KZ TABLOSU 2010/YILLIK K Z 2010.xls"), (long) 2010);
//        excellService.importYillikKarZararFromExcell(new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/K.Z TABLOLARI/KZ TABLOSU 2009/YILLIK K Z 2009.xls"), (long) 2009);
//        excellService.importYillikKarZararFromExcell(new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/K.Z TABLOLARI/KZ TABLOSU 2006/YILLIK K Z.xls"), (long) 2006);
//        excellService.importYillikKarZararFromExcell(new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/K.Z TABLOLARI/KZ TABLOSU 2005/YILLIK K Z.xls"), (long) 2005);
//        excellService.importYillikKarZararFromExcell(new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/K.Z TABLOLARI/K.Z TABLOSU 2008/YILLIK K Z 2008.xls"), (long) 2008);
        return excellService.importYillikKarZararFromExcell(new File("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/K.Z TABLOLARI/K Z TABLOSU 2007/YILLIK K Z.xls"), (long) 2007);

    }

    @GetMapping("/test/moth/{value}")
    public ResponseEntity<ProfitPerMonth> generateMonthProfit(@PathVariable Month value) {

        return ResponseEntity.ok().body(calculateMonthProfit.calculeteMonthProfit(value));

    }

    @GetMapping("/test/importexcell")
    public void importData() {
//        importFrom2005();
//        importFrom2006();
//        importFrom2007("2007'de yapılan iş");
//        importFrom2007("2008  de yapılan iş");
//        importFrom2007("2009  de yapılan iş");
//        importFrom2007("2010 YAPILAN İŞ");
//        importFrom2007("2011YAPILAN İŞ");
//        importFrom2007("2012YAPILAN İŞ");
//        importFrom2007("2013YAPILAN İŞ");
    }
    
    @GetMapping("/test/importcompany")
    public void importFromCompanySummary(){
    
        excellService.importFromCompanySummery("/Users/Faruk/Downloads/BELGELER-1/konfeksiyon/ESKİLER/FİRMALAR2012/MEBA.xls");
        
    }

    public void importFrom2005() {

        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\AĞUSTOS");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\ocak");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\Şubat");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\ARALIK");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\EKİM");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\EYLÜL");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\HAZİRAN");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\KASIM");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\MART");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\MAYIS");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\NİSAN");
        excellService.importWeekleySummery(fileDir + "\\2005'de  yapılan  iş\\TEMMUZ");

    }

    public void importFrom2006() {

        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\AĞUSTOS");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\ocak");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\Şubat");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\ARALIK");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\EKİM");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\EYLÜL");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\HAZİRAN");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\KASIM");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\MART");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\MAYIS");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\NİSAN");
        excellService.importWeekleySummery(fileDir + "\\2006 yapılan iş\\TEMMUZ");

    }
    
    public void importFrom2007(String folderName) {

        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\AĞUSTOS");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\ocak");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\Şubat");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\ARALIK");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\EKİM");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\EYLÜL");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\HAZİRAN");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\KASIM");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\MART");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\MAYIS");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\NİSAN");
        excellService.importWeekleySummery(fileDir + "\\"+folderName+"\\TEMMUZ");

    }

}
