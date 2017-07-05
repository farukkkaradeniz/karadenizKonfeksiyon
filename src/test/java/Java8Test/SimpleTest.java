/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java8Test;

import java.io.File;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Faruk-pc
 */
public class SimpleTest {

    @Test
    @Ignore
    public void testFileName() {
        
         File fileDir = new File("C:\\Users\\Faruk-pc\\Downloads\\05,09,2014\\BELGELER\\konfeksiyon\\ESKİLER\\yapılan işler\\YILLIK YAPILAN İŞLER\\2005'de  yapılan  iş\\2005 ARALIK");
         
         File[] files = fileDir.listFiles();
         
         for (File file : files) {
             String fileName = file.getName().substring(0,file.getName().indexOf(".x")).replaceAll(",", "-");
             System.out.println(fileName);
        }
                 

    }

}
