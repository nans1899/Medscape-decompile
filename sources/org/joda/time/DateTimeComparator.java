package org.joda.time;

import java.io.Serializable;
import java.util.Comparator;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;

public class DateTimeComparator implements Comparator<Object>, Serializable {
    private static final DateTimeComparator ALL_INSTANCE = new DateTimeComparator((DateTimeFieldType) null, (DateTimeFieldType) null);
    private static final DateTimeComparator DATE_INSTANCE = new DateTimeComparator(DateTimeFieldType.dayOfYear(), (DateTimeFieldType) null);
    private static final DateTimeComparator TIME_INSTANCE = new DateTimeComparator((DateTimeFieldType) null, DateTimeFieldType.dayOfYear());
    private static final long serialVersionUID = -6097339773320178364L;
    private final DateTimeFieldType iLowerLimit;
    private final DateTimeFieldType iUpperLimit;

    public static DateTimeComparator getInstance() {
        return ALL_INSTANCE;
    }

    public static DateTimeComparator getInstance(DateTimeFieldType dateTimeFieldType) {
        return getInstance(dateTimeFieldType, (DateTimeFieldType) null);
    }

    public static DateTimeComparator getInstance(DateTimeFieldType dateTimeFieldType, DateTimeFieldType dateTimeFieldType2) {
        if (dateTimeFieldType == null && dateTimeFieldType2 == null) {
            return ALL_INSTANCE;
        }
        if (dateTimeFieldType == DateTimeFieldType.dayOfYear() && dateTimeFieldType2 == null) {
            return DATE_INSTANCE;
        }
        if (dateTimeFieldType == null && dateTimeFieldType2 == DateTimeFieldType.dayOfYear()) {
            return TIME_INSTANCE;
        }
        return new DateTimeComparator(dateTimeFieldType, dateTimeFieldType2);
    }

    public static DateTimeComparator getDateOnlyInstance() {
        return DATE_INSTANCE;
    }

    public static DateTimeComparator getTimeOnlyInstance() {
        return TIME_INSTANCE;
    }

    protected DateTimeComparator(DateTimeFieldType dateTimeFieldType, DateTimeFieldType dateTimeFieldType2) {
        this.iLowerLimit = dateTimeFieldType;
        this.iUpperLimit = dateTimeFieldType2;
    }

    public DateTimeFieldType getLowerLimit() {
        return this.iLowerLimit;
    }

    public DateTimeFieldType getUpperLimit() {
        return this.iUpperLimit;
    }

    public int compare(Object obj, Object obj2) {
        InstantConverter instantConverter = ConverterManager.getInstance().getInstantConverter(obj);
        Chronology chronology = null;
        Chronology chronology2 = instantConverter.getChronology(obj, chronology);
        long instantMillis = instantConverter.getInstantMillis(obj, chronology2);
        if (obj == obj2) {
            return 0;
        }
        InstantConverter instantConverter2 = ConverterManager.getInstance().getInstantConverter(obj2);
        Chronology chronology3 = instantConverter2.getChronology(obj2, chronology);
        long instantMillis2 = instantConverter2.getInstantMillis(obj2, chronology3);
        DateTimeFieldType dateTimeFieldType = this.iLowerLimit;
        if (dateTimeFieldType != null) {
            instantMillis = dateTimeFieldType.getField(chronology2).roundFloor(instantMillis);
            instantMillis2 = this.iLowerLimit.getField(chronology3).roundFloor(instantMillis2);
        }
        DateTimeFieldType dateTimeFieldType2 = this.iUpperLimit;
        if (dateTimeFieldType2 != null) {
            instantMillis = dateTimeFieldType2.getField(chronology2).remainder(instantMillis);
            instantMillis2 = this.iUpperLimit.getField(chronology3).remainder(instantMillis2);
        }
        int i = (instantMillis > instantMillis2 ? 1 : (instantMillis == instantMillis2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        if (i > 0) {
            return 1;
        }
        return 0;
    }

    private Object readResolve() {
        return getInstance(this.iLowerLimit, this.iUpperLimit);
    }

    public boolean equals(Object obj) {
        DateTimeFieldType dateTimeFieldType;
        DateTimeFieldType dateTimeFieldType2;
        if (!(obj instanceof DateTimeComparator)) {
            return false;
        }
        DateTimeComparator dateTimeComparator = (DateTimeComparator) obj;
        if (this.iLowerLimit != dateTimeComparator.getLowerLimit() && ((dateTimeFieldType2 = this.iLowerLimit) == null || !dateTimeFieldType2.equals(dateTimeComparator.getLowerLimit()))) {
            return false;
        }
        if (this.iUpperLimit == dateTimeComparator.getUpperLimit() || ((dateTimeFieldType = this.iUpperLimit) != null && dateTimeFieldType.equals(dateTimeComparator.getUpperLimit()))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        DateTimeFieldType dateTimeFieldType = this.iLowerLimit;
        int i = 0;
        int hashCode = dateTimeFieldType == null ? 0 : dateTimeFieldType.hashCode();
        DateTimeFieldType dateTimeFieldType2 = this.iUpperLimit;
        if (dateTimeFieldType2 != null) {
            i = dateTimeFieldType2.hashCode();
        }
        return hashCode + (i * 123);
    }

    public String toString() {
        String str = "";
        if (this.iLowerLimit == this.iUpperLimit) {
            StringBuilder sb = new StringBuilder();
            sb.append("DateTimeComparator[");
            DateTimeFieldType dateTimeFieldType = this.iLowerLimit;
            if (dateTimeFieldType != null) {
                str = dateTimeFieldType.getName();
            }
            sb.append(str);
            sb.append("]");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("DateTimeComparator[");
        DateTimeFieldType dateTimeFieldType2 = this.iLowerLimit;
        sb2.append(dateTimeFieldType2 == null ? str : dateTimeFieldType2.getName());
        sb2.append("-");
        DateTimeFieldType dateTimeFieldType3 = this.iUpperLimit;
        if (dateTimeFieldType3 != null) {
            str = dateTimeFieldType3.getName();
        }
        sb2.append(str);
        sb2.append("]");
        return sb2.toString();
    }
}
