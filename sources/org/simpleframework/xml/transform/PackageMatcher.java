package org.simpleframework.xml.transform;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

class PackageMatcher implements Matcher {
    public Transform match(Class cls) throws Exception {
        String name = cls.getName();
        if (name.startsWith("java.lang")) {
            return matchLanguage(cls);
        }
        if (name.startsWith("java.util")) {
            return matchUtility(cls);
        }
        if (name.startsWith("java.net")) {
            return matchURL(cls);
        }
        if (name.startsWith("java.io")) {
            return matchFile(cls);
        }
        if (name.startsWith("java.sql")) {
            return matchSQL(cls);
        }
        if (name.startsWith("java.math")) {
            return matchMath(cls);
        }
        return matchEnum(cls);
    }

    private Transform matchEnum(Class cls) {
        Class superclass = cls.getSuperclass();
        if (superclass == null) {
            return null;
        }
        if (superclass.isEnum()) {
            return new EnumTransform(cls);
        }
        if (cls.isEnum()) {
            return new EnumTransform(cls);
        }
        return null;
    }

    private Transform matchLanguage(Class cls) throws Exception {
        if (cls == Boolean.class) {
            return new BooleanTransform();
        }
        if (cls == Integer.class) {
            return new IntegerTransform();
        }
        if (cls == Long.class) {
            return new LongTransform();
        }
        if (cls == Double.class) {
            return new DoubleTransform();
        }
        if (cls == Float.class) {
            return new FloatTransform();
        }
        if (cls == Short.class) {
            return new ShortTransform();
        }
        if (cls == Byte.class) {
            return new ByteTransform();
        }
        if (cls == Character.class) {
            return new CharacterTransform();
        }
        if (cls == String.class) {
            return new StringTransform();
        }
        if (cls == Class.class) {
            return new ClassTransform();
        }
        return null;
    }

    private Transform matchMath(Class cls) throws Exception {
        if (cls == BigDecimal.class) {
            return new BigDecimalTransform();
        }
        if (cls == BigInteger.class) {
            return new BigIntegerTransform();
        }
        return null;
    }

    private Transform matchUtility(Class cls) throws Exception {
        if (cls == Date.class) {
            return new DateTransform(cls);
        }
        if (cls == Locale.class) {
            return new LocaleTransform();
        }
        if (cls == Currency.class) {
            return new CurrencyTransform();
        }
        if (cls == GregorianCalendar.class) {
            return new GregorianCalendarTransform();
        }
        if (cls == TimeZone.class) {
            return new TimeZoneTransform();
        }
        if (cls == AtomicInteger.class) {
            return new AtomicIntegerTransform();
        }
        if (cls == AtomicLong.class) {
            return new AtomicLongTransform();
        }
        return null;
    }

    private Transform matchSQL(Class cls) throws Exception {
        if (cls == Time.class) {
            return new DateTransform(cls);
        }
        if (cls == java.sql.Date.class) {
            return new DateTransform(cls);
        }
        if (cls == Timestamp.class) {
            return new DateTransform(cls);
        }
        return null;
    }

    private Transform matchFile(Class cls) throws Exception {
        if (cls == File.class) {
            return new FileTransform();
        }
        return null;
    }

    private Transform matchURL(Class cls) throws Exception {
        if (cls == URL.class) {
            return new URLTransform();
        }
        return null;
    }
}
