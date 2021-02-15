package org.threeten.bp.temporal;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.jdk8.Jdk8Methods;

public final class TemporalAdjusters {
    private TemporalAdjusters() {
    }

    public static TemporalAdjuster firstDayOfMonth() {
        return Impl.FIRST_DAY_OF_MONTH;
    }

    public static TemporalAdjuster lastDayOfMonth() {
        return Impl.LAST_DAY_OF_MONTH;
    }

    public static TemporalAdjuster firstDayOfNextMonth() {
        return Impl.FIRST_DAY_OF_NEXT_MONTH;
    }

    public static TemporalAdjuster firstDayOfYear() {
        return Impl.FIRST_DAY_OF_YEAR;
    }

    public static TemporalAdjuster lastDayOfYear() {
        return Impl.LAST_DAY_OF_YEAR;
    }

    public static TemporalAdjuster firstDayOfNextYear() {
        return Impl.FIRST_DAY_OF_NEXT_YEAR;
    }

    private static class Impl implements TemporalAdjuster {
        /* access modifiers changed from: private */
        public static final Impl FIRST_DAY_OF_MONTH = new Impl(0);
        /* access modifiers changed from: private */
        public static final Impl FIRST_DAY_OF_NEXT_MONTH = new Impl(2);
        /* access modifiers changed from: private */
        public static final Impl FIRST_DAY_OF_NEXT_YEAR = new Impl(5);
        /* access modifiers changed from: private */
        public static final Impl FIRST_DAY_OF_YEAR = new Impl(3);
        /* access modifiers changed from: private */
        public static final Impl LAST_DAY_OF_MONTH = new Impl(1);
        /* access modifiers changed from: private */
        public static final Impl LAST_DAY_OF_YEAR = new Impl(4);
        private final int ordinal;

        private Impl(int i) {
            this.ordinal = i;
        }

        public Temporal adjustInto(Temporal temporal) {
            int i = this.ordinal;
            if (i == 0) {
                return temporal.with(ChronoField.DAY_OF_MONTH, 1);
            }
            if (i == 1) {
                return temporal.with(ChronoField.DAY_OF_MONTH, temporal.range(ChronoField.DAY_OF_MONTH).getMaximum());
            }
            if (i == 2) {
                return temporal.with(ChronoField.DAY_OF_MONTH, 1).plus(1, ChronoUnit.MONTHS);
            }
            if (i == 3) {
                return temporal.with(ChronoField.DAY_OF_YEAR, 1);
            }
            if (i == 4) {
                return temporal.with(ChronoField.DAY_OF_YEAR, temporal.range(ChronoField.DAY_OF_YEAR).getMaximum());
            }
            if (i == 5) {
                return temporal.with(ChronoField.DAY_OF_YEAR, 1).plus(1, ChronoUnit.YEARS);
            }
            throw new IllegalStateException("Unreachable");
        }
    }

    public static TemporalAdjuster firstInMonth(DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(1, dayOfWeek);
    }

    public static TemporalAdjuster lastInMonth(DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(-1, dayOfWeek);
    }

    public static TemporalAdjuster dayOfWeekInMonth(int i, DayOfWeek dayOfWeek) {
        Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
        return new DayOfWeekInMonth(i, dayOfWeek);
    }

    private static final class DayOfWeekInMonth implements TemporalAdjuster {
        private final int dowValue;
        private final int ordinal;

        private DayOfWeekInMonth(int i, DayOfWeek dayOfWeek) {
            this.ordinal = i;
            this.dowValue = dayOfWeek.getValue();
        }

        public Temporal adjustInto(Temporal temporal) {
            if (this.ordinal >= 0) {
                Temporal with = temporal.with(ChronoField.DAY_OF_MONTH, 1);
                return with.plus((long) ((int) (((long) (((this.dowValue - with.get(ChronoField.DAY_OF_WEEK)) + 7) % 7)) + ((((long) this.ordinal) - 1) * 7))), ChronoUnit.DAYS);
            }
            Temporal with2 = temporal.with(ChronoField.DAY_OF_MONTH, temporal.range(ChronoField.DAY_OF_MONTH).getMaximum());
            int i = this.dowValue - with2.get(ChronoField.DAY_OF_WEEK);
            if (i == 0) {
                i = 0;
            } else if (i > 0) {
                i -= 7;
            }
            return with2.plus((long) ((int) (((long) i) - ((((long) (-this.ordinal)) - 1) * 7))), ChronoUnit.DAYS);
        }
    }

    public static TemporalAdjuster next(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(2, dayOfWeek);
    }

    public static TemporalAdjuster nextOrSame(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(0, dayOfWeek);
    }

    public static TemporalAdjuster previous(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(3, dayOfWeek);
    }

    public static TemporalAdjuster previousOrSame(DayOfWeek dayOfWeek) {
        return new RelativeDayOfWeek(1, dayOfWeek);
    }

    private static final class RelativeDayOfWeek implements TemporalAdjuster {
        private final int dowValue;
        private final int relative;

        private RelativeDayOfWeek(int i, DayOfWeek dayOfWeek) {
            Jdk8Methods.requireNonNull(dayOfWeek, "dayOfWeek");
            this.relative = i;
            this.dowValue = dayOfWeek.getValue();
        }

        public Temporal adjustInto(Temporal temporal) {
            int i = temporal.get(ChronoField.DAY_OF_WEEK);
            if (this.relative < 2 && i == this.dowValue) {
                return temporal;
            }
            if ((this.relative & 1) == 0) {
                int i2 = i - this.dowValue;
                return temporal.plus((long) (i2 >= 0 ? 7 - i2 : -i2), ChronoUnit.DAYS);
            }
            int i3 = this.dowValue - i;
            return temporal.minus((long) (i3 >= 0 ? 7 - i3 : -i3), ChronoUnit.DAYS);
        }
    }
}
