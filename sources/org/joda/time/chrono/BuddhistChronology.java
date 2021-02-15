package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.pool.TypePool;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.chrono.AssembledChronology;
import org.joda.time.field.DelegatedDateTimeField;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.OffsetDateTimeField;
import org.joda.time.field.RemainderDateTimeField;
import org.joda.time.field.SkipUndoDateTimeField;
import org.joda.time.field.UnsupportedDurationField;

public final class BuddhistChronology extends AssembledChronology {
    public static final int BE = 1;
    private static final int BUDDHIST_OFFSET = 543;
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("BE");
    private static final BuddhistChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final ConcurrentHashMap<DateTimeZone, BuddhistChronology> cCache = new ConcurrentHashMap<>();
    private static final long serialVersionUID = -3474595157769370126L;

    public static BuddhistChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static BuddhistChronology getInstance() {
        return getInstance(DateTimeZone.getDefault());
    }

    public static BuddhistChronology getInstance(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        BuddhistChronology buddhistChronology = cCache.get(dateTimeZone);
        if (buddhistChronology != null) {
            return buddhistChronology;
        }
        BuddhistChronology buddhistChronology2 = new BuddhistChronology(GJChronology.getInstance(dateTimeZone, (ReadableInstant) null), (Object) null);
        BuddhistChronology buddhistChronology3 = new BuddhistChronology(LimitChronology.getInstance(buddhistChronology2, new DateTime(1, 1, 1, 0, 0, 0, 0, (Chronology) buddhistChronology2), (ReadableDateTime) null), "");
        BuddhistChronology putIfAbsent = cCache.putIfAbsent(dateTimeZone, buddhistChronology3);
        return putIfAbsent != null ? putIfAbsent : buddhistChronology3;
    }

    private BuddhistChronology(Chronology chronology, Object obj) {
        super(chronology, obj);
    }

    private Object readResolve() {
        Chronology base = getBase();
        return base == null ? getInstanceUTC() : getInstance(base.getZone());
    }

    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        if (dateTimeZone == getZone()) {
            return this;
        }
        return getInstance(dateTimeZone);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BuddhistChronology) {
            return getZone().equals(((BuddhistChronology) obj).getZone());
        }
        return false;
    }

    public int hashCode() {
        return 499287079 + getZone().hashCode();
    }

    public String toString() {
        DateTimeZone zone = getZone();
        if (zone == null) {
            return "BuddhistChronology";
        }
        return "BuddhistChronology" + TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH + zone.getID() + ']';
    }

    /* access modifiers changed from: protected */
    public void assemble(AssembledChronology.Fields fields) {
        if (getParam() == null) {
            fields.eras = UnsupportedDurationField.getInstance(DurationFieldType.eras());
            fields.year = new OffsetDateTimeField(new SkipUndoDateTimeField(this, fields.year), BUDDHIST_OFFSET);
            DateTimeField dateTimeField = fields.yearOfEra;
            fields.yearOfEra = new DelegatedDateTimeField(fields.year, fields.eras, DateTimeFieldType.yearOfEra());
            fields.weekyear = new OffsetDateTimeField(new SkipUndoDateTimeField(this, fields.weekyear), BUDDHIST_OFFSET);
            fields.centuryOfEra = new DividedDateTimeField(new OffsetDateTimeField(fields.yearOfEra, 99), fields.eras, DateTimeFieldType.centuryOfEra(), 100);
            fields.centuries = fields.centuryOfEra.getDurationField();
            fields.yearOfCentury = new OffsetDateTimeField(new RemainderDateTimeField((DividedDateTimeField) fields.centuryOfEra), DateTimeFieldType.yearOfCentury(), 1);
            fields.weekyearOfCentury = new OffsetDateTimeField(new RemainderDateTimeField(fields.weekyear, fields.centuries, DateTimeFieldType.weekyearOfCentury(), 100), DateTimeFieldType.weekyearOfCentury(), 1);
            fields.era = ERA_FIELD;
        }
    }
}
