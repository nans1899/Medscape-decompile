package org.threeten.bp.zone;

import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.cache.Cache;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import org.joda.time.DateTimeConstants;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalAdjusters;

public final class ZoneOffsetTransitionRule implements Serializable {
    private static final int SECS_PER_DAY = 86400;
    private static final long serialVersionUID = 6889046316657758795L;
    private final int adjustDays;
    private final byte dom;
    private final DayOfWeek dow;
    private final Month month;
    private final ZoneOffset offsetAfter;
    private final ZoneOffset offsetBefore;
    private final ZoneOffset standardOffset;
    private final LocalTime time;
    private final TimeDefinition timeDefinition;

    public static ZoneOffsetTransitionRule of(Month month2, int i, DayOfWeek dayOfWeek, LocalTime localTime, boolean z, TimeDefinition timeDefinition2, ZoneOffset zoneOffset, ZoneOffset zoneOffset2, ZoneOffset zoneOffset3) {
        int i2 = i;
        LocalTime localTime2 = localTime;
        Month month3 = month2;
        Jdk8Methods.requireNonNull(month2, "month");
        Jdk8Methods.requireNonNull(localTime, Cache.Caches.TIME);
        Jdk8Methods.requireNonNull(timeDefinition2, "timeDefnition");
        Jdk8Methods.requireNonNull(zoneOffset, "standardOffset");
        Jdk8Methods.requireNonNull(zoneOffset2, "offsetBefore");
        Jdk8Methods.requireNonNull(zoneOffset3, "offsetAfter");
        if (i2 < -28 || i2 > 31 || i2 == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        } else if (!z || localTime.equals(LocalTime.MIDNIGHT)) {
            return new ZoneOffsetTransitionRule(month2, i, dayOfWeek, localTime, z ? 1 : 0, timeDefinition2, zoneOffset, zoneOffset2, zoneOffset3);
        } else {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        }
    }

    ZoneOffsetTransitionRule(Month month2, int i, DayOfWeek dayOfWeek, LocalTime localTime, int i2, TimeDefinition timeDefinition2, ZoneOffset zoneOffset, ZoneOffset zoneOffset2, ZoneOffset zoneOffset3) {
        this.month = month2;
        this.dom = (byte) i;
        this.dow = dayOfWeek;
        this.time = localTime;
        this.adjustDays = i2;
        this.timeDefinition = timeDefinition2;
        this.standardOffset = zoneOffset;
        this.offsetBefore = zoneOffset2;
        this.offsetAfter = zoneOffset3;
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        int i;
        int i2;
        int i3;
        int secondOfDay = this.time.toSecondOfDay() + (this.adjustDays * 86400);
        int totalSeconds = this.standardOffset.getTotalSeconds();
        int totalSeconds2 = this.offsetBefore.getTotalSeconds() - totalSeconds;
        int totalSeconds3 = this.offsetAfter.getTotalSeconds() - totalSeconds;
        if (secondOfDay % DateTimeConstants.SECONDS_PER_HOUR != 0 || secondOfDay > 86400) {
            i = 31;
        } else {
            i = secondOfDay == 86400 ? 24 : this.time.getHour();
        }
        int i4 = totalSeconds % 900 == 0 ? (totalSeconds / 900) + 128 : 255;
        if (totalSeconds2 == 0 || totalSeconds2 == 1800 || totalSeconds2 == 3600) {
            i2 = totalSeconds2 / 1800;
        } else {
            i2 = 3;
        }
        if (totalSeconds3 == 0 || totalSeconds3 == 1800 || totalSeconds3 == 3600) {
            i3 = totalSeconds3 / 1800;
        } else {
            i3 = 3;
        }
        DayOfWeek dayOfWeek = this.dow;
        dataOutput.writeInt((this.month.getValue() << 28) + ((this.dom + 32) << 22) + ((dayOfWeek == null ? 0 : dayOfWeek.getValue()) << 19) + (i << 14) + (this.timeDefinition.ordinal() << 12) + (i4 << 4) + (i2 << 2) + i3);
        if (i == 31) {
            dataOutput.writeInt(secondOfDay);
        }
        if (i4 == 255) {
            dataOutput.writeInt(totalSeconds);
        }
        if (i2 == 3) {
            dataOutput.writeInt(this.offsetBefore.getTotalSeconds());
        }
        if (i3 == 3) {
            dataOutput.writeInt(this.offsetAfter.getTotalSeconds());
        }
    }

    static ZoneOffsetTransitionRule readExternal(DataInput dataInput) throws IOException {
        DayOfWeek dayOfWeek;
        int readInt = dataInput.readInt();
        Month of = Month.of(readInt >>> 28);
        int i = ((264241152 & readInt) >>> 22) - 32;
        int i2 = (3670016 & readInt) >>> 19;
        if (i2 == 0) {
            dayOfWeek = null;
        } else {
            dayOfWeek = DayOfWeek.of(i2);
        }
        DayOfWeek dayOfWeek2 = dayOfWeek;
        int i3 = (507904 & readInt) >>> 14;
        TimeDefinition timeDefinition2 = TimeDefinition.values()[(readInt & 12288) >>> 12];
        int i4 = (readInt & 4080) >>> 4;
        int i5 = (readInt & 12) >>> 2;
        int i6 = readInt & 3;
        int readInt2 = i3 == 31 ? dataInput.readInt() : i3 * DateTimeConstants.SECONDS_PER_HOUR;
        ZoneOffset ofTotalSeconds = ZoneOffset.ofTotalSeconds(i4 == 255 ? dataInput.readInt() : (i4 - 128) * 900);
        ZoneOffset ofTotalSeconds2 = ZoneOffset.ofTotalSeconds(i5 == 3 ? dataInput.readInt() : ofTotalSeconds.getTotalSeconds() + (i5 * 1800));
        ZoneOffset ofTotalSeconds3 = ZoneOffset.ofTotalSeconds(i6 == 3 ? dataInput.readInt() : ofTotalSeconds.getTotalSeconds() + (i6 * 1800));
        if (i >= -28 && i <= 31 && i != 0) {
            return new ZoneOffsetTransitionRule(of, i, dayOfWeek2, LocalTime.ofSecondOfDay((long) Jdk8Methods.floorMod(readInt2, 86400)), Jdk8Methods.floorDiv(readInt2, 86400), timeDefinition2, ofTotalSeconds, ofTotalSeconds2, ofTotalSeconds3);
        }
        throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
    }

    public Month getMonth() {
        return this.month;
    }

    public int getDayOfMonthIndicator() {
        return this.dom;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dow;
    }

    public LocalTime getLocalTime() {
        return this.time;
    }

    public boolean isMidnightEndOfDay() {
        return this.adjustDays == 1 && this.time.equals(LocalTime.MIDNIGHT);
    }

    public TimeDefinition getTimeDefinition() {
        return this.timeDefinition;
    }

    public ZoneOffset getStandardOffset() {
        return this.standardOffset;
    }

    public ZoneOffset getOffsetBefore() {
        return this.offsetBefore;
    }

    public ZoneOffset getOffsetAfter() {
        return this.offsetAfter;
    }

    public ZoneOffsetTransition createTransition(int i) {
        LocalDate localDate;
        byte b = this.dom;
        if (b < 0) {
            Month month2 = this.month;
            localDate = LocalDate.of(i, month2, month2.length(IsoChronology.INSTANCE.isLeapYear((long) i)) + 1 + this.dom);
            DayOfWeek dayOfWeek = this.dow;
            if (dayOfWeek != null) {
                localDate = localDate.with(TemporalAdjusters.previousOrSame(dayOfWeek));
            }
        } else {
            localDate = LocalDate.of(i, this.month, (int) b);
            DayOfWeek dayOfWeek2 = this.dow;
            if (dayOfWeek2 != null) {
                localDate = localDate.with(TemporalAdjusters.nextOrSame(dayOfWeek2));
            }
        }
        return new ZoneOffsetTransition(this.timeDefinition.createDateTime(LocalDateTime.of(localDate.plusDays((long) this.adjustDays), this.time), this.standardOffset, this.offsetBefore), this.offsetBefore, this.offsetAfter);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZoneOffsetTransitionRule)) {
            return false;
        }
        ZoneOffsetTransitionRule zoneOffsetTransitionRule = (ZoneOffsetTransitionRule) obj;
        if (this.month == zoneOffsetTransitionRule.month && this.dom == zoneOffsetTransitionRule.dom && this.dow == zoneOffsetTransitionRule.dow && this.timeDefinition == zoneOffsetTransitionRule.timeDefinition && this.adjustDays == zoneOffsetTransitionRule.adjustDays && this.time.equals(zoneOffsetTransitionRule.time) && this.standardOffset.equals(zoneOffsetTransitionRule.standardOffset) && this.offsetBefore.equals(zoneOffsetTransitionRule.offsetBefore) && this.offsetAfter.equals(zoneOffsetTransitionRule.offsetAfter)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int secondOfDay = ((this.time.toSecondOfDay() + this.adjustDays) << 15) + (this.month.ordinal() << 11) + ((this.dom + 32) << 5);
        DayOfWeek dayOfWeek = this.dow;
        return ((((secondOfDay + ((dayOfWeek == null ? 7 : dayOfWeek.ordinal()) << 2)) + this.timeDefinition.ordinal()) ^ this.standardOffset.hashCode()) ^ this.offsetBefore.hashCode()) ^ this.offsetAfter.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TransitionRule[");
        sb.append(this.offsetBefore.compareTo(this.offsetAfter) > 0 ? "Gap " : "Overlap ");
        sb.append(this.offsetBefore);
        sb.append(" to ");
        sb.append(this.offsetAfter);
        sb.append(", ");
        DayOfWeek dayOfWeek = this.dow;
        if (dayOfWeek != null) {
            byte b = this.dom;
            if (b == -1) {
                sb.append(dayOfWeek.name());
                sb.append(" on or before last day of ");
                sb.append(this.month.name());
            } else if (b < 0) {
                sb.append(dayOfWeek.name());
                sb.append(" on or before last day minus ");
                sb.append((-this.dom) - 1);
                sb.append(" of ");
                sb.append(this.month.name());
            } else {
                sb.append(dayOfWeek.name());
                sb.append(" on or after ");
                sb.append(this.month.name());
                sb.append(' ');
                sb.append(this.dom);
            }
        } else {
            sb.append(this.month.name());
            sb.append(' ');
            sb.append(this.dom);
        }
        sb.append(" at ");
        if (this.adjustDays == 0) {
            sb.append(this.time);
        } else {
            long secondOfDay = (long) ((this.time.toSecondOfDay() / 60) + (this.adjustDays * 24 * 60));
            appendZeroPad(sb, Jdk8Methods.floorDiv(secondOfDay, 60));
            sb.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
            appendZeroPad(sb, (long) Jdk8Methods.floorMod(secondOfDay, 60));
        }
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(this.timeDefinition);
        sb.append(", standard offset ");
        sb.append(this.standardOffset);
        sb.append(']');
        return sb.toString();
    }

    private void appendZeroPad(StringBuilder sb, long j) {
        if (j < 10) {
            sb.append(0);
        }
        sb.append(j);
    }

    /* renamed from: org.threeten.bp.zone.ZoneOffsetTransitionRule$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.threeten.bp.zone.ZoneOffsetTransitionRule$TimeDefinition[] r0 = org.threeten.bp.zone.ZoneOffsetTransitionRule.TimeDefinition.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition = r0
                org.threeten.bp.zone.ZoneOffsetTransitionRule$TimeDefinition r1 = org.threeten.bp.zone.ZoneOffsetTransitionRule.TimeDefinition.UTC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition     // Catch:{ NoSuchFieldError -> 0x001d }
                org.threeten.bp.zone.ZoneOffsetTransitionRule$TimeDefinition r1 = org.threeten.bp.zone.ZoneOffsetTransitionRule.TimeDefinition.STANDARD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.zone.ZoneOffsetTransitionRule.AnonymousClass1.<clinit>():void");
        }
    }

    public enum TimeDefinition {
        UTC,
        WALL,
        STANDARD;

        public LocalDateTime createDateTime(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
            int i = AnonymousClass1.$SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition[ordinal()];
            if (i == 1) {
                return localDateTime.plusSeconds((long) (zoneOffset2.getTotalSeconds() - ZoneOffset.UTC.getTotalSeconds()));
            }
            if (i != 2) {
                return localDateTime;
            }
            return localDateTime.plusSeconds((long) (zoneOffset2.getTotalSeconds() - zoneOffset.getTotalSeconds()));
        }
    }
}
