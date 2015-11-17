package com.github.torczuk.infrastructure.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FormatUtils {

    public static DecimalFormat applicationDecimalFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }

}
