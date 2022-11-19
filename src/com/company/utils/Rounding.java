package com.company.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounding {

    public static BigDecimal bigDecimalToHundredths(BigDecimal value){
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
