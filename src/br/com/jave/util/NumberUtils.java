package br.com.jave.util;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberUtils {
    public static final String BIG_DECIMAL_FORMAT = "#,##0.00";
    public static final DecimalFormat format = new DecimalFormat(BIG_DECIMAL_FORMAT);
    
    static {
        format.setParseBigDecimal(true);
    }

    public static BigDecimal parseBigDecimal(String s) throws ParseException {
        final BigDecimal bigDecimal = (BigDecimal) NumberUtils.format.parse(s);
        return bigDecimal;
    }
}
