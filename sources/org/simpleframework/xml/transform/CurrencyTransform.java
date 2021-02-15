package org.simpleframework.xml.transform;

import java.util.Currency;

class CurrencyTransform implements Transform<Currency> {
    CurrencyTransform() {
    }

    public Currency read(String str) {
        return Currency.getInstance(str);
    }

    public String write(Currency currency) {
        return currency.toString();
    }
}
