package org.threeten.bp;

import com.facebook.places.model.PlaceFields;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTimeConstants;
import org.threeten.bp.format.DateTimeParseException;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;

public final class Duration implements TemporalAmount, Comparable<Duration>, Serializable {
    private static final BigInteger BI_NANOS_PER_SECOND = BigInteger.valueOf(1000000000);
    private static final int NANOS_PER_MILLI = 1000000;
    private static final int NANOS_PER_SECOND = 1000000000;
    private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)D)?(T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)(?:[.,]([0-9]{0,9}))?S)?)?", 2);
    public static final Duration ZERO = new Duration(0, 0);
    private static final long serialVersionUID = 3078945930695997490L;
    private final int nanos;
    private final long seconds;

    public static Duration ofDays(long j) {
        return create(Jdk8Methods.safeMultiply(j, (int) DateTimeConstants.SECONDS_PER_DAY), 0);
    }

    public static Duration ofHours(long j) {
        return create(Jdk8Methods.safeMultiply(j, (int) DateTimeConstants.SECONDS_PER_HOUR), 0);
    }

    public static Duration ofMinutes(long j) {
        return create(Jdk8Methods.safeMultiply(j, 60), 0);
    }

    public static Duration ofSeconds(long j) {
        return create(j, 0);
    }

    public static Duration ofSeconds(long j, long j2) {
        return create(Jdk8Methods.safeAdd(j, Jdk8Methods.floorDiv(j2, 1000000000)), Jdk8Methods.floorMod(j2, 1000000000));
    }

    public static Duration ofMillis(long j) {
        long j2 = j / 1000;
        int i = (int) (j % 1000);
        if (i < 0) {
            i += 1000;
            j2--;
        }
        return create(j2, i * NANOS_PER_MILLI);
    }

    public static Duration ofNanos(long j) {
        long j2 = j / 1000000000;
        int i = (int) (j % 1000000000);
        if (i < 0) {
            i += 1000000000;
            j2--;
        }
        return create(j2, i);
    }

    public static Duration of(long j, TemporalUnit temporalUnit) {
        return ZERO.plus(j, temporalUnit);
    }

    public static Duration from(TemporalAmount temporalAmount) {
        Jdk8Methods.requireNonNull(temporalAmount, "amount");
        Duration duration = ZERO;
        for (TemporalUnit next : temporalAmount.getUnits()) {
            duration = duration.plus(temporalAmount.get(next), next);
        }
        return duration;
    }

    public static Duration between(Temporal temporal, Temporal temporal2) {
        long until = temporal.until(temporal2, ChronoUnit.SECONDS);
        long j = 0;
        if (temporal.isSupported(ChronoField.NANO_OF_SECOND) && temporal2.isSupported(ChronoField.NANO_OF_SECOND)) {
            try {
                long j2 = temporal.getLong(ChronoField.NANO_OF_SECOND);
                long j3 = temporal2.getLong(ChronoField.NANO_OF_SECOND) - j2;
                int i = (until > 0 ? 1 : (until == 0 ? 0 : -1));
                if (i > 0 && j3 < 0) {
                    j3 += 1000000000;
                } else if (i < 0 && j3 > 0) {
                    j3 -= 1000000000;
                } else if (i == 0 && j3 != 0) {
                    try {
                        until = temporal.until(temporal2.with(ChronoField.NANO_OF_SECOND, j2), ChronoUnit.SECONDS);
                    } catch (ArithmeticException | DateTimeException unused) {
                    }
                }
                j = j3;
            } catch (ArithmeticException | DateTimeException unused2) {
            }
        }
        return ofSeconds(until, j);
    }

    public static Duration parse(CharSequence charSequence) {
        Jdk8Methods.requireNonNull(charSequence, "text");
        Matcher matcher = PATTERN.matcher(charSequence);
        if (matcher.matches() && !"T".equals(matcher.group(3))) {
            int i = 1;
            boolean equals = "-".equals(matcher.group(1));
            String group = matcher.group(2);
            String group2 = matcher.group(4);
            String group3 = matcher.group(5);
            String group4 = matcher.group(6);
            String group5 = matcher.group(7);
            if (!(group == null && group2 == null && group3 == null && group4 == null)) {
                long parseNumber = parseNumber(charSequence, group, DateTimeConstants.SECONDS_PER_DAY, "days");
                long parseNumber2 = parseNumber(charSequence, group2, DateTimeConstants.SECONDS_PER_HOUR, PlaceFields.HOURS);
                long parseNumber3 = parseNumber(charSequence, group3, 60, "minutes");
                long parseNumber4 = parseNumber(charSequence, group4, 1, "seconds");
                if (group4 != null && group4.charAt(0) == '-') {
                    i = -1;
                }
                try {
                    return create(equals, parseNumber, parseNumber2, parseNumber3, parseNumber4, parseFraction(charSequence, group5, i));
                } catch (ArithmeticException e) {
                    throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: overflow", charSequence, 0).initCause(e));
                }
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Duration", charSequence, 0);
    }

    private static long parseNumber(CharSequence charSequence, String str, int i, String str2) {
        if (str == null) {
            return 0;
        }
        try {
            if (str.startsWith("+")) {
                str = str.substring(1);
            }
            return Jdk8Methods.safeMultiply(Long.parseLong(str), i);
        } catch (NumberFormatException e) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: " + str2, charSequence, 0).initCause(e));
        } catch (ArithmeticException e2) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: " + str2, charSequence, 0).initCause(e2));
        }
    }

    private static int parseFraction(CharSequence charSequence, String str, int i) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        try {
            return Integer.parseInt((str + "000000000").substring(0, 9)) * i;
        } catch (NumberFormatException e) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: fraction", charSequence, 0).initCause(e));
        } catch (ArithmeticException e2) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: fraction", charSequence, 0).initCause(e2));
        }
    }

    private static Duration create(boolean z, long j, long j2, long j3, long j4, int i) {
        long safeAdd = Jdk8Methods.safeAdd(j, Jdk8Methods.safeAdd(j2, Jdk8Methods.safeAdd(j3, j4)));
        if (z) {
            return ofSeconds(safeAdd, (long) i).negated();
        }
        return ofSeconds(safeAdd, (long) i);
    }

    private static Duration create(long j, int i) {
        if ((((long) i) | j) == 0) {
            return ZERO;
        }
        return new Duration(j, i);
    }

    private Duration(long j, int i) {
        this.seconds = j;
        this.nanos = i;
    }

    public List<TemporalUnit> getUnits() {
        return Collections.unmodifiableList(Arrays.asList(new ChronoUnit[]{ChronoUnit.SECONDS, ChronoUnit.NANOS}));
    }

    public long get(TemporalUnit temporalUnit) {
        if (temporalUnit == ChronoUnit.SECONDS) {
            return this.seconds;
        }
        if (temporalUnit == ChronoUnit.NANOS) {
            return (long) this.nanos;
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
    }

    public boolean isZero() {
        return (this.seconds | ((long) this.nanos)) == 0;
    }

    public boolean isNegative() {
        return this.seconds < 0;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public int getNano() {
        return this.nanos;
    }

    public Duration withSeconds(long j) {
        return create(j, this.nanos);
    }

    public Duration withNanos(int i) {
        ChronoField.NANO_OF_SECOND.checkValidIntValue((long) i);
        return create(this.seconds, i);
    }

    public Duration plus(Duration duration) {
        return plus(duration.getSeconds(), (long) duration.getNano());
    }

    public Duration plus(long j, TemporalUnit temporalUnit) {
        Jdk8Methods.requireNonNull(temporalUnit, "unit");
        if (temporalUnit == ChronoUnit.DAYS) {
            return plus(Jdk8Methods.safeMultiply(j, (int) DateTimeConstants.SECONDS_PER_DAY), 0);
        }
        if (temporalUnit.isDurationEstimated()) {
            throw new DateTimeException("Unit must not have an estimated duration");
        } else if (j == 0) {
            return this;
        } else {
            if (temporalUnit instanceof ChronoUnit) {
                int i = AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()];
                if (i == 1) {
                    return plusNanos(j);
                }
                if (i == 2) {
                    return plusSeconds((j / 1000000000) * 1000).plusNanos((j % 1000000000) * 1000);
                }
                if (i == 3) {
                    return plusMillis(j);
                }
                if (i != 4) {
                    return plusSeconds(Jdk8Methods.safeMultiply(temporalUnit.getDuration().seconds, j));
                }
                return plusSeconds(j);
            }
            Duration multipliedBy = temporalUnit.getDuration().multipliedBy(j);
            return plusSeconds(multipliedBy.getSeconds()).plusNanos((long) multipliedBy.getNano());
        }
    }

    /* renamed from: org.threeten.bp.Duration$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoUnit;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.Duration.AnonymousClass1.<clinit>():void");
        }
    }

    public Duration plusDays(long j) {
        return plus(Jdk8Methods.safeMultiply(j, (int) DateTimeConstants.SECONDS_PER_DAY), 0);
    }

    public Duration plusHours(long j) {
        return plus(Jdk8Methods.safeMultiply(j, (int) DateTimeConstants.SECONDS_PER_HOUR), 0);
    }

    public Duration plusMinutes(long j) {
        return plus(Jdk8Methods.safeMultiply(j, 60), 0);
    }

    public Duration plusSeconds(long j) {
        return plus(j, 0);
    }

    public Duration plusMillis(long j) {
        return plus(j / 1000, (j % 1000) * 1000000);
    }

    public Duration plusNanos(long j) {
        return plus(0, j);
    }

    private Duration plus(long j, long j2) {
        if ((j | j2) == 0) {
            return this;
        }
        return ofSeconds(Jdk8Methods.safeAdd(Jdk8Methods.safeAdd(this.seconds, j), j2 / 1000000000), ((long) this.nanos) + (j2 % 1000000000));
    }

    public Duration minus(Duration duration) {
        long seconds2 = duration.getSeconds();
        int nano = duration.getNano();
        if (seconds2 == Long.MIN_VALUE) {
            return plus(Long.MAX_VALUE, (long) (-nano)).plus(1, 0);
        }
        return plus(-seconds2, (long) (-nano));
    }

    public Duration minus(long j, TemporalUnit temporalUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, temporalUnit).plus(1, temporalUnit) : plus(-j, temporalUnit);
    }

    public Duration minusDays(long j) {
        return j == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1) : plusDays(-j);
    }

    public Duration minusHours(long j) {
        return j == Long.MIN_VALUE ? plusHours(Long.MAX_VALUE).plusHours(1) : plusHours(-j);
    }

    public Duration minusMinutes(long j) {
        return j == Long.MIN_VALUE ? plusMinutes(Long.MAX_VALUE).plusMinutes(1) : plusMinutes(-j);
    }

    public Duration minusSeconds(long j) {
        return j == Long.MIN_VALUE ? plusSeconds(Long.MAX_VALUE).plusSeconds(1) : plusSeconds(-j);
    }

    public Duration minusMillis(long j) {
        return j == Long.MIN_VALUE ? plusMillis(Long.MAX_VALUE).plusMillis(1) : plusMillis(-j);
    }

    public Duration minusNanos(long j) {
        return j == Long.MIN_VALUE ? plusNanos(Long.MAX_VALUE).plusNanos(1) : plusNanos(-j);
    }

    public Duration multipliedBy(long j) {
        if (j == 0) {
            return ZERO;
        }
        if (j == 1) {
            return this;
        }
        return create(toSeconds().multiply(BigDecimal.valueOf(j)));
    }

    public Duration dividedBy(long j) {
        if (j == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        } else if (j == 1) {
            return this;
        } else {
            return create(toSeconds().divide(BigDecimal.valueOf(j), RoundingMode.DOWN));
        }
    }

    private BigDecimal toSeconds() {
        return BigDecimal.valueOf(this.seconds).add(BigDecimal.valueOf((long) this.nanos, 9));
    }

    private static Duration create(BigDecimal bigDecimal) {
        BigInteger bigIntegerExact = bigDecimal.movePointRight(9).toBigIntegerExact();
        BigInteger[] divideAndRemainder = bigIntegerExact.divideAndRemainder(BI_NANOS_PER_SECOND);
        if (divideAndRemainder[0].bitLength() <= 63) {
            return ofSeconds(divideAndRemainder[0].longValue(), (long) divideAndRemainder[1].intValue());
        }
        throw new ArithmeticException("Exceeds capacity of Duration: " + bigIntegerExact);
    }

    public Duration negated() {
        return multipliedBy(-1);
    }

    public Duration abs() {
        return isNegative() ? negated() : this;
    }

    public Temporal addTo(Temporal temporal) {
        long j = this.seconds;
        if (j != 0) {
            temporal = temporal.plus(j, ChronoUnit.SECONDS);
        }
        int i = this.nanos;
        return i != 0 ? temporal.plus((long) i, ChronoUnit.NANOS) : temporal;
    }

    public Temporal subtractFrom(Temporal temporal) {
        long j = this.seconds;
        if (j != 0) {
            temporal = temporal.minus(j, ChronoUnit.SECONDS);
        }
        int i = this.nanos;
        return i != 0 ? temporal.minus((long) i, ChronoUnit.NANOS) : temporal;
    }

    public long toDays() {
        return this.seconds / 86400;
    }

    public long toHours() {
        return this.seconds / 3600;
    }

    public long toMinutes() {
        return this.seconds / 60;
    }

    public long toMillis() {
        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(this.seconds, 1000), (long) (this.nanos / NANOS_PER_MILLI));
    }

    public long toNanos() {
        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(this.seconds, 1000000000), (long) this.nanos);
    }

    public int compareTo(Duration duration) {
        int compareLongs = Jdk8Methods.compareLongs(this.seconds, duration.seconds);
        if (compareLongs != 0) {
            return compareLongs;
        }
        return this.nanos - duration.nanos;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Duration)) {
            return false;
        }
        Duration duration = (Duration) obj;
        if (this.seconds == duration.seconds && this.nanos == duration.nanos) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.seconds;
        return ((int) (j ^ (j >>> 32))) + (this.nanos * 51);
    }

    public String toString() {
        if (this == ZERO) {
            return "PT0S";
        }
        long j = this.seconds;
        long j2 = j / 3600;
        int i = (int) ((j % 3600) / 60);
        int i2 = (int) (j % 60);
        StringBuilder sb = new StringBuilder(24);
        sb.append("PT");
        if (j2 != 0) {
            sb.append(j2);
            sb.append('H');
        }
        if (i != 0) {
            sb.append(i);
            sb.append('M');
        }
        if (i2 == 0 && this.nanos == 0 && sb.length() > 2) {
            return sb.toString();
        }
        if (i2 >= 0 || this.nanos <= 0) {
            sb.append(i2);
        } else if (i2 == -1) {
            sb.append("-0");
        } else {
            sb.append(i2 + 1);
        }
        if (this.nanos > 0) {
            int length = sb.length();
            if (i2 < 0) {
                sb.append(2000000000 - this.nanos);
            } else {
                sb.append(this.nanos + 1000000000);
            }
            while (sb.charAt(sb.length() - 1) == '0') {
                sb.setLength(sb.length() - 1);
            }
            sb.setCharAt(length, '.');
        }
        sb.append('S');
        return sb.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(this.seconds);
        dataOutput.writeInt(this.nanos);
    }

    static Duration readExternal(DataInput dataInput) throws IOException {
        return ofSeconds(dataInput.readLong(), (long) dataInput.readInt());
    }
}
