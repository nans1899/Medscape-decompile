package bo.app;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ef {
    private static final BigDecimal a = new BigDecimal("100");

    public static BigDecimal a(BigDecimal bigDecimal) {
        return bigDecimal.setScale(2, RoundingMode.HALF_UP);
    }
}
