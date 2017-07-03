/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.util;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Faruk-pc
 */
@Component
public class BigDecimalUtil {

    public BigDecimal sumValues(List<BigDecimal> list) {

        BigDecimal val = BigDecimal.ZERO;

        list.forEach((bigDecimal) -> {
            val.add(bigDecimal);
        });
        return val;
    }
    
    

}
