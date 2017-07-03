/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.web.rest;

import com.faruk.karadenizkonfeksiyon.domain.ProfitPerYear;
import com.faruk.karadenizkonfeksiyon.service.ImportFromExcellService;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Faruk-pc
 */
@RestController
public class TestController {

    @Autowired
    private ImportFromExcellService excellService;

//    @GetMapping("/test/excell")
//    public ProfitPerYear importFromExcell() {
//
//        //excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\KZ TABLOSU 2005\\YILLIK K Z.xls"), (long) 2005);
//        //excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\KZ TABLOSU 2006\\YILLIK K Z.xls"), (long) 2006);
//        //excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\K Z TABLOSU 2007\\YILLIK K Z.xls"), (long) 2007);
//        //excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\KZ TABLOSU 2009\\YILLIK K Z 2009.xls"), (long) 2009);
//        excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\KZ TABLOSU 2010\\YILLIK K Z 2010.xls"), (long) 2010);
//        excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\KZ TABLOSU 2011\\YILLIK K Z 2011.xls"), (long) 2011);
//        excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\KZ TABLOSU 2012\\YILLIK K Z 2012.xls"), (long) 2012);
//        return excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\KZ TABLOSU 2013\\YILLIK K Z 2013.xls"), (long) 2013);
//        //return excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\KZ TABLOSU 2014\\YILLIK K Z 2014.xls"), (long) 2014);
//        //excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\K.Z TABLOSU 2015\\YILLIK K Z 2015.xls"), (long) 2008);
//        //excellService.importYillikKarZararFromExcell(new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\K.Z TABLOLARI\\K.Z TABLOSU 2016\\YILLIK K Z 2016.xls"), (long) 2008);
//
//    }
}
