package org.simpleframework.xml.transform;

import java.math.BigInteger;

class BigIntegerTransform implements Transform<BigInteger> {
    BigIntegerTransform() {
    }

    public BigInteger read(String str) {
        return new BigInteger(str);
    }

    public String write(BigInteger bigInteger) {
        return bigInteger.toString();
    }
}
