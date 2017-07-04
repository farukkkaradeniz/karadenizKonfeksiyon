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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Faruk-pc
 */
@RestController
public class TestController {

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
    public ResponseEntity<ProfitPerMonth> generateMonthProfit(@PathVariable Month value){
    
        return ResponseEntity.ok().body(calculateMonthProfit.calculeteMonthProfit(value));
        
    }
    
    
}
