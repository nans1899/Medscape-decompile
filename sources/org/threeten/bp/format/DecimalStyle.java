package org.threeten.bp.format;

import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import org.threeten.bp.jdk8.Jdk8Methods;

public final class DecimalStyle {
    private static final ConcurrentMap<Locale, DecimalStyle> CACHE = new ConcurrentHashMap(16, 0.75f, 2);
    public static final DecimalStyle STANDARD = new DecimalStyle('0', SignatureVisitor.EXTENDS, '-', '.');
    private final char decimalSeparator;
    private final char negativeSign;
    private final char positiveSign;
    private final char zeroDigit;

    public static Set<Locale> getAvailableLocales() {
        return new HashSet(Arrays.asList(DecimalFormatSymbols.getAvailableLocales()));
    }

    public static DecimalStyle ofDefaultLocale() {
        return of(Locale.getDefault());
    }

    public static DecimalStyle of(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        DecimalStyle decimalStyle = (DecimalStyle) CACHE.get(locale);
        if (decimalStyle != null) {
            return decimalStyle;
        }
        CACHE.putIfAbsent(locale, create(locale));
        return (DecimalStyle) CACHE.get(locale);
    }

    private static DecimalStyle create(Locale locale) {
        DecimalFormatSymbols instance = DecimalFormatSymbols.getInstance(locale);
        char zeroDigit2 = instance.getZeroDigit();
        char minusSign = instance.getMinusSign();
        char decimalSeparator2 = instance.getDecimalSeparator();
        if (zeroDigit2 == '0' && minusSign == '-' && decimalSeparator2 == '.') {
            return STANDARD;
        }
        return new DecimalStyle(zeroDigit2, SignatureVisitor.EXTENDS, minusSign, decimalSeparator2);
    }

    private DecimalStyle(char c, char c2, char c3, char c4) {
        this.zeroDigit = c;
        this.positiveSign = c2;
        this.negativeSign = c3;
        this.decimalSeparator = c4;
    }

    public char getZeroDigit() {
        return this.zeroDigit;
    }

    public DecimalStyle withZeroDigit(char c) {
        if (c == this.zeroDigit) {
            return this;
        }
        return new DecimalStyle(c, this.positiveSign, this.negativeSign, this.decimalSeparator);
    }

    public char getPositiveSign() {
        return this.positiveSign;
    }

    public DecimalStyle withPositiveSign(char c) {
        if (c == this.positiveSign) {
            return this;
        }
        return new DecimalStyle(this.zeroDigit, c, this.negativeSign, this.decimalSeparator);
    }

    public char getNegativeSign() {
        return this.negativeSign;
    }

    public DecimalStyle withNegativeSign(char c) {
        if (c == this.negativeSign) {
            return this;
        }
        return new DecimalStyle(this.zeroDigit, this.positiveSign, c, this.decimalSeparator);
    }

    public char getDecimalSeparator() {
        return this.decimalSeparator;
    }

    public DecimalStyle withDecimalSeparator(char c) {
        if (c == this.decimalSeparator) {
            return this;
        }
        return new DecimalStyle(this.zeroDigit, this.positiveSign, this.negativeSign, c);
    }

    /* access modifiers changed from: package-private */
    public int convertToDigit(char c) {
        int i = c - this.zeroDigit;
        if (i < 0 || i > 9) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public String convertNumberToI18N(String str) {
        char c = this.zeroDigit;
        if (c == '0') {
            return str;
        }
        int i = c - '0';
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            charArray[i2] = (char) (charArray[i2] + i);
        }
        return new String(charArray);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DecimalStyle)) {
            return false;
        }
        DecimalStyle decimalStyle = (DecimalStyle) obj;
        if (this.zeroDigit == decimalStyle.zeroDigit && this.positiveSign == decimalStyle.positiveSign && this.negativeSign == decimalStyle.negativeSign && this.decimalSeparator == decimalStyle.decimalSeparator) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.zeroDigit + this.positiveSign + this.negativeSign + this.decimalSeparator;
    }

    public String toString() {
        return "DecimalStyle[" + this.zeroDigit + this.positiveSign + this.negativeSign + this.decimalSeparator + "]";
    }
}
