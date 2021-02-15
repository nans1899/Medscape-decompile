package org.jaxen.function.ext;

import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import org.jaxen.Function;
import org.jaxen.Navigator;
import org.jaxen.function.StringFunction;

public abstract class LocaleFunctionSupport implements Function {
    /* access modifiers changed from: protected */
    public Locale getLocale(Object obj, Navigator navigator) {
        if (obj instanceof Locale) {
            return (Locale) obj;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (!list.isEmpty()) {
                return getLocale(list.get(0), navigator);
            }
            return null;
        }
        String evaluate = StringFunction.evaluate(obj, navigator);
        if (evaluate == null || evaluate.length() <= 0) {
            return null;
        }
        return findLocale(evaluate);
    }

    /* access modifiers changed from: protected */
    public Locale findLocale(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "-");
        if (!stringTokenizer.hasMoreTokens()) {
            return null;
        }
        String nextToken = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens()) {
            return findLocaleForLanguage(nextToken);
        }
        String nextToken2 = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens()) {
            return new Locale(nextToken, nextToken2);
        }
        return new Locale(nextToken, nextToken2, stringTokenizer.nextToken());
    }

    /* access modifiers changed from: protected */
    public Locale findLocaleForLanguage(String str) {
        String country;
        String variant;
        for (Locale locale : Locale.getAvailableLocales()) {
            if (str.equals(locale.getLanguage()) && (((country = locale.getCountry()) == null || country.length() == 0) && ((variant = locale.getVariant()) == null || variant.length() == 0))) {
                return locale;
            }
        }
        return null;
    }
}
