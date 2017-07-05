/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcellTest;

import com.faruk.karadenizkonfeksiyon.domain.Employee;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Faruk
 */
public class EmployeSalaryTest {

    @Test
    @Ignore
    public void importEmployesSalary() {

        try {
            File file = new File("");

            HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(new FileInputStream(file));

            List<Row> rowList = IteratorUtils.toList(hSSFWorkbook.getSheetAt(0).rowIterator());

            List<Employee> employees = new ArrayList<>();

            for (int i = 3; i < 19; i++) {

                List<Cell> cellList = IteratorUtils.toList(rowList.get(i).cellIterator());

                Employee employee = new Employee();

                for (int j = 11; j < cellList.size(); j++) {
                    if (cellList.get(j).getCellType() == Cell.CELL_TYPE_STRING) {
                        
                    } else if (cellList.get(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {

                    }
                }

                if (employee.getFirstName() != null) {
                    employees.add(employee);
                }

            }

        } catch (IOException e) {

        }

    }

}
