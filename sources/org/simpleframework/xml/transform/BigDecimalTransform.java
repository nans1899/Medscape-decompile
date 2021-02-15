package org.simpleframework.xml.transform;

import java.math.BigDecimal;

class BigDecimalTransform implements Transform<BigDecimal> {
    BigDecimalTransform() {
    }

    public BigDecimal read(String str) {
        return new BigDecimal(str);
    }

    public String write(BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }
}
