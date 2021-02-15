package org.threeten.bp.chrono;

import com.google.common.base.Ascii;
import com.medscape.android.cache.Cache;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.ValueRange;

final class ChronoLocalDateTimeImpl<D extends ChronoLocalDate> extends ChronoLocalDateTime<D> implements Temporal, TemporalAdjuster, Serializable {
    private static final int HOURS_PER_DAY = 24;
    private static final long MICROS_PER_DAY = 86400000000L;
    private static final long MILLIS_PER_DAY = 86400000;
    private static final int MINUTES_PER_DAY = 1440;
    private static final int MINUTES_PER_HOUR = 60;
    private static final long NANOS_PER_DAY = 86400000000000L;
    private static final long NANOS_PER_HOUR = 3600000000000L;
    private static final long NANOS_PER_MINUTE = 60000000000L;
    private static final long NANOS_PER_SECOND = 1000000000;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final long serialVersionUID = 4556003607393004514L;
    private final D date;
    private final LocalTime time;

    static <R extends ChronoLocalDate> ChronoLocalDateTimeImpl<R> of(R r, LocalTime localTime) {
        return new ChronoLocalDateTimeImpl<>(r, localTime);
    }

    private ChronoLocalDateTimeImpl(D d, LocalTime localTime) {
        Jdk8Methods.requireNonNull(d, OmnitureConstants.OMNITURE_FILTER_DATE);
        Jdk8Methods.requireNonNull(localTime, Cache.Caches.TIME);
        this.date = d;
        this.time = localTime;
    }

    private ChronoLocalDateTimeImpl<D> with(Temporal temporal, LocalTime localTime) {
        if (this.date == temporal && this.time == localTime) {
            return this;
        }
        return new ChronoLocalDateTimeImpl<>(this.date.getChronology().ensureChronoLocalDate(temporal), localTime);
    }

    public D toLocalDate() {
        return this.date;
    }

    public LocalTime toLocalTime() {
        return this.time;
    }

    public boolean isSupported(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (temporalField.isDateBased() || temporalField.isTimeBased()) {
                return true;
            }
            return false;
        } else if (temporalField == null || !temporalField.isSupportedBy(this)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isSupported(TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            if (temporalUnit.isDateBased() || temporalUnit.isTimeBased()) {
                return true;
            }
            return false;
        } else if (temporalUnit == null || !temporalUnit.isSupportedBy(this)) {
            return false;
        } else {
            return true;
        }
    }

    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isTimeBased() ? this.time.range(temporalField) : this.date.range(temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    public int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isTimeBased() ? this.time.get(temporalField) : this.date.get(temporalField);
        }
        return range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isTimeBased() ? this.time.getLong(temporalField) : this.date.getLong(temporalField);
        }
        return temporalField.getFrom(this);
    }

    public ChronoLocalDateTimeImpl<D> with(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof ChronoLocalDate) {
            return with((Temporal) (ChronoLocalDate) temporalAdjuster, this.time);
        }
        if (temporalAdjuster instanceof LocalTime) {
            return with((Temporal) this.date, (LocalTime) temporalAdjuster);
        }
        if (temporalAdjuster instanceof ChronoLocalDateTimeImpl) {
            return this.date.getChronology().ensureChronoLocalDateTime((ChronoLocalDateTimeImpl) temporalAdjuster);
        }
        return this.date.getChronology().ensureChronoLocalDateTime((ChronoLocalDateTimeImpl) temporalAdjuster.adjustInto(this));
    }

    public ChronoLocalDateTimeImpl<D> with(TemporalField temporalField, long j) {
        if (!(temporalField instanceof ChronoField)) {
            return this.date.getChronology().ensureChronoLocalDateTime(temporalField.adjustInto(this, j));
        }
        if (temporalField.isTimeBased()) {
            return with((Temporal) this.date, this.time.with(temporalField, j));
        }
        return with((Temporal) this.date.with(temporalField, j), this.time);
    }

    public ChronoLocalDateTimeImpl<D> plus(long j, TemporalUnit temporalUnit) {
        if (!(temporalUnit instanceof ChronoUnit)) {
            return this.date.getChronology().ensureChronoLocalDateTime(temporalUnit.addTo(this, j));
        }
        switch (AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
            case 1:
                return plusNanos(j);
            case 2:
                return plusDays(j / MICROS_PER_DAY).plusNanos((j % MICROS_PER_DAY) * 1000);
            case 3:
                return plusDays(j / 86400000).plusNanos((j % 86400000) * 1000000);
            case 4:
                return plusSeconds(j);
            case 5:
                return plusMinutes(j);
            case 6:
                return plusHours(j);
            case 7:
                return plusDays(j / 256).plusHours((j % 256) * 12);
            default:
                return with((Temporal) this.date.plus(j, temporalUnit), this.time);
        }
    }

    /* renamed from: org.threeten.bp.chrono.ChronoLocalDateTimeImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoUnit;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.threeten.bp.temporal.ChronoUnit[] r0 = org.threeten.bp.temporal.ChronoUnit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit = r0
                org.threeten.bp.temporal.ChronoUnit r1 = org.threeten.bp.temporal.ChronoUnit.NANOS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x001d }
                org.threeten.bp.temporal.ChronoUnit r1 = org.threeten.bp.temporal.ChronoUnit.MICROS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.threeten.bp.temporal.ChronoUnit r1 = org.threeten.bp.temporal.ChronoUnit.MILLIS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.threeten.bp.temporal.ChronoUnit r1 = org.threeten.bp.temporal.ChronoUnit.SECONDS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x003e }
                org.threeten.bp.temporal.ChronoUnit r1 = org.threeten.bp.temporal.ChronoUnit.MINUTES     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.threeten.bp.temporal.ChronoUnit r1 = org.threeten.bp.temporal.ChronoUnit.HOURS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoUnit     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.threeten.bp.temporal.ChronoUnit r1 = org.threeten.bp.temporal.ChronoUnit.HALF_DAYS     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.ChronoLocalDateTimeImpl.AnonymousClass1.<clinit>():void");
        }
    }

    private ChronoLocalDateTimeImpl<D> plusDays(long j) {
        return with((Temporal) this.date.plus(j, (TemporalUnit) ChronoUnit.DAYS), this.time);
    }

    private ChronoLocalDateTimeImpl<D> plusHours(long j) {
        return plusWithOverflow(this.date, j, 0, 0, 0);
    }

    private ChronoLocalDateTimeImpl<D> plusMinutes(long j) {
        return plusWithOverflow(this.date, 0, j, 0, 0);
    }

    /* access modifiers changed from: package-private */
    public ChronoLocalDateTimeImpl<D> plusSeconds(long j) {
        return plusWithOverflow(this.date, 0, 0, j, 0);
    }

    private ChronoLocalDateTimeImpl<D> plusNanos(long j) {
        return plusWithOverflow(this.date, 0, 0, 0, j);
    }

    private ChronoLocalDateTimeImpl<D> plusWithOverflow(D d, long j, long j2, long j3, long j4) {
        D d2 = d;
        if ((j | j2 | j3 | j4) == 0) {
            return with((Temporal) d2, this.time);
        }
        long j5 = (j4 / NANOS_PER_DAY) + (j3 / 86400) + (j2 / 1440) + (j / 24);
        long j6 = (j4 % NANOS_PER_DAY) + ((j3 % 86400) * NANOS_PER_SECOND) + ((j2 % 1440) * NANOS_PER_MINUTE) + ((j % 24) * NANOS_PER_HOUR);
        long nanoOfDay = this.time.toNanoOfDay();
        long j7 = j6 + nanoOfDay;
        long floorDiv = j5 + Jdk8Methods.floorDiv(j7, (long) NANOS_PER_DAY);
        long floorMod = Jdk8Methods.floorMod(j7, (long) NANOS_PER_DAY);
        return with((Temporal) d2.plus(floorDiv, (TemporalUnit) ChronoUnit.DAYS), floorMod == nanoOfDay ? this.time : LocalTime.ofNanoOfDay(floorMod));
    }

    public ChronoZonedDateTime<D> atZone(ZoneId zoneId) {
        return ChronoZonedDateTimeImpl.ofBest(this, zoneId, (ZoneOffset) null);
    }

    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        ChronoLocalDateTime localDateTime = toLocalDate().getChronology().localDateTime(temporal);
        if (!(temporalUnit instanceof ChronoUnit)) {
            return temporalUnit.between(this, localDateTime);
        }
        ChronoUnit chronoUnit = (ChronoUnit) temporalUnit;
        if (chronoUnit.isTimeBased()) {
            long j = localDateTime.getLong(ChronoField.EPOCH_DAY) - this.date.getLong(ChronoField.EPOCH_DAY);
            switch (AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoUnit[chronoUnit.ordinal()]) {
                case 1:
                    j = Jdk8Methods.safeMultiply(j, (long) NANOS_PER_DAY);
                    break;
                case 2:
                    j = Jdk8Methods.safeMultiply(j, (long) MICROS_PER_DAY);
                    break;
                case 3:
                    j = Jdk8Methods.safeMultiply(j, 86400000);
                    break;
                case 4:
                    j = Jdk8Methods.safeMultiply(j, 86400);
                    break;
                case 5:
                    j = Jdk8Methods.safeMultiply(j, 1440);
                    break;
                case 6:
                    j = Jdk8Methods.safeMultiply(j, 24);
                    break;
                case 7:
                    j = Jdk8Methods.safeMultiply(j, 2);
                    break;
            }
            return Jdk8Methods.safeAdd(j, this.time.until(localDateTime.toLocalTime(), temporalUnit));
        }
        ChronoLocalDate localDate = localDateTime.toLocalDate();
        if (localDateTime.toLocalTime().isBefore(this.time)) {
            localDate = localDate.minus(1, (TemporalUnit) ChronoUnit.DAYS);
        }
        return this.date.until(localDate, temporalUnit);
    }

    private Object writeReplace() {
        return new Ser(Ascii.FF, this);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.date);
        objectOutput.writeObject(this.time);
    }

    static ChronoLocalDateTime<?> readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        return ((ChronoLocalDate) objectInput.readObject()).atTime((LocalTime) objectInput.readObject());
    }
}
