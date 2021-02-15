package org.threeten.bp.zone;

import com.medscape.android.cache.Cache;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTimeConstants;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.Year;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.zone.ZoneOffsetTransitionRule;

class ZoneRulesBuilder {
    private Map<Object, Object> deduplicateMap;
    private List<TZWindow> windowList = new ArrayList();

    public ZoneRulesBuilder addWindow(ZoneOffset zoneOffset, LocalDateTime localDateTime, ZoneOffsetTransitionRule.TimeDefinition timeDefinition) {
        Jdk8Methods.requireNonNull(zoneOffset, "standardOffset");
        Jdk8Methods.requireNonNull(localDateTime, "until");
        Jdk8Methods.requireNonNull(timeDefinition, "untilDefinition");
        TZWindow tZWindow = new TZWindow(zoneOffset, localDateTime, timeDefinition);
        if (this.windowList.size() > 0) {
            List<TZWindow> list = this.windowList;
            tZWindow.validateWindowOrder(list.get(list.size() - 1));
        }
        this.windowList.add(tZWindow);
        return this;
    }

    public ZoneRulesBuilder addWindowForever(ZoneOffset zoneOffset) {
        return addWindow(zoneOffset, LocalDateTime.MAX, ZoneOffsetTransitionRule.TimeDefinition.WALL);
    }

    public ZoneRulesBuilder setFixedSavingsToWindow(int i) {
        if (!this.windowList.isEmpty()) {
            List<TZWindow> list = this.windowList;
            list.get(list.size() - 1).setFixedSavings(i);
            return this;
        }
        throw new IllegalStateException("Must add a window before setting the fixed savings");
    }

    public ZoneRulesBuilder addRuleToWindow(LocalDateTime localDateTime, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i) {
        Jdk8Methods.requireNonNull(localDateTime, "transitionDateTime");
        return addRuleToWindow(localDateTime.getYear(), localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), (DayOfWeek) null, localDateTime.toLocalTime(), false, timeDefinition, i);
    }

    public ZoneRulesBuilder addRuleToWindow(int i, Month month, int i2, LocalTime localTime, boolean z, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i3) {
        return addRuleToWindow(i, i, month, i2, (DayOfWeek) null, localTime, z, timeDefinition, i3);
    }

    public ZoneRulesBuilder addRuleToWindow(int i, int i2, Month month, int i3, DayOfWeek dayOfWeek, LocalTime localTime, boolean z, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i4) {
        int i5 = i3;
        LocalTime localTime2 = localTime;
        Month month2 = month;
        Jdk8Methods.requireNonNull(month, "month");
        Jdk8Methods.requireNonNull(localTime2, Cache.Caches.TIME);
        Jdk8Methods.requireNonNull(timeDefinition, "timeDefinition");
        ChronoField.YEAR.checkValidValue((long) i);
        ChronoField.YEAR.checkValidValue((long) i2);
        if (i5 < -28 || i5 > 31 || i5 == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        } else if (z && !localTime2.equals(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        } else if (!this.windowList.isEmpty()) {
            List<TZWindow> list = this.windowList;
            list.get(list.size() - 1).addRule(i, i2, month, i3, dayOfWeek, localTime, z ? 1 : 0, timeDefinition, i4);
            return this;
        } else {
            throw new IllegalStateException("Must add a window before adding a rule");
        }
    }

    /* access modifiers changed from: package-private */
    public ZoneRulesBuilder addRuleToWindow(int i, int i2, Month month, int i3, DayOfWeek dayOfWeek, LocalTime localTime, int i4, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i5) {
        int i6 = i3;
        Month month2 = month;
        Jdk8Methods.requireNonNull(month, "month");
        Jdk8Methods.requireNonNull(timeDefinition, "timeDefinition");
        ChronoField.YEAR.checkValidValue((long) i);
        ChronoField.YEAR.checkValidValue((long) i2);
        if (i6 < -28 || i6 > 31 || i6 == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        } else if (!this.windowList.isEmpty()) {
            List<TZWindow> list = this.windowList;
            list.get(list.size() - 1).addRule(i, i2, month, i3, dayOfWeek, localTime, i4, timeDefinition, i5);
            return this;
        } else {
            throw new IllegalStateException("Must add a window before adding a rule");
        }
    }

    public ZoneRules toRules(String str) {
        return toRules(str, new HashMap());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: org.threeten.bp.ZoneOffset} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.threeten.bp.zone.ZoneRules toRules(java.lang.String r22, java.util.Map<java.lang.Object, java.lang.Object> r23) {
        /*
            r21 = this;
            r0 = r21
            java.lang.String r1 = "zoneId"
            r2 = r22
            org.threeten.bp.jdk8.Jdk8Methods.requireNonNull(r2, r1)
            r1 = r23
            r0.deduplicateMap = r1
            java.util.List<org.threeten.bp.zone.ZoneRulesBuilder$TZWindow> r1 = r0.windowList
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x01b7
            java.util.ArrayList r5 = new java.util.ArrayList
            r1 = 4
            r5.<init>(r1)
            java.util.ArrayList r6 = new java.util.ArrayList
            r1 = 256(0x100, float:3.59E-43)
            r6.<init>(r1)
            java.util.ArrayList r7 = new java.util.ArrayList
            r1 = 2
            r7.<init>(r1)
            java.util.List<org.threeten.bp.zone.ZoneRulesBuilder$TZWindow> r1 = r0.windowList
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            org.threeten.bp.zone.ZoneRulesBuilder$TZWindow r1 = (org.threeten.bp.zone.ZoneRulesBuilder.TZWindow) r1
            org.threeten.bp.ZoneOffset r3 = r1.standardOffset
            java.lang.Integer r4 = r1.fixedSavingAmountSecs
            if (r4 == 0) goto L_0x0044
            java.lang.Integer r4 = r1.fixedSavingAmountSecs
            int r4 = r4.intValue()
            goto L_0x0045
        L_0x0044:
            r4 = 0
        L_0x0045:
            int r8 = r3.getTotalSeconds()
            int r8 = r8 + r4
            org.threeten.bp.ZoneOffset r8 = org.threeten.bp.ZoneOffset.ofTotalSeconds(r8)
            java.lang.Object r8 = r0.deduplicate(r8)
            org.threeten.bp.ZoneOffset r8 = (org.threeten.bp.ZoneOffset) r8
            r9 = -999999999(0xffffffffc4653601, float:-916.8438)
            r10 = 1
            org.threeten.bp.LocalDateTime r9 = org.threeten.bp.LocalDateTime.of((int) r9, (int) r10, (int) r10, (int) r2, (int) r2)
            java.lang.Object r9 = r0.deduplicate(r9)
            org.threeten.bp.LocalDateTime r9 = (org.threeten.bp.LocalDateTime) r9
            java.util.List<org.threeten.bp.zone.ZoneRulesBuilder$TZWindow> r11 = r0.windowList
            java.util.Iterator r11 = r11.iterator()
            r12 = r8
        L_0x0069:
            boolean r13 = r11.hasNext()
            if (r13 == 0) goto L_0x01ab
            java.lang.Object r13 = r11.next()
            org.threeten.bp.zone.ZoneRulesBuilder$TZWindow r13 = (org.threeten.bp.zone.ZoneRulesBuilder.TZWindow) r13
            int r14 = r9.getYear()
            r13.tidy(r14)
            java.lang.Integer r14 = r13.fixedSavingAmountSecs
            if (r14 != 0) goto L_0x00b7
            java.lang.Integer r14 = java.lang.Integer.valueOf(r2)
            java.util.List r15 = r13.ruleList
            java.util.Iterator r15 = r15.iterator()
        L_0x008e:
            boolean r16 = r15.hasNext()
            if (r16 == 0) goto L_0x00b7
            java.lang.Object r16 = r15.next()
            r10 = r16
            org.threeten.bp.zone.ZoneRulesBuilder$TZRule r10 = (org.threeten.bp.zone.ZoneRulesBuilder.TZRule) r10
            org.threeten.bp.zone.ZoneOffsetTransition r16 = r10.toTransition(r3, r4)
            long r16 = r16.toEpochSecond()
            long r18 = r9.toEpochSecond(r12)
            int r20 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r20 <= 0) goto L_0x00ad
            goto L_0x00b7
        L_0x00ad:
            int r10 = r10.savingAmountSecs
            java.lang.Integer r14 = java.lang.Integer.valueOf(r10)
            r10 = 1
            goto L_0x008e
        L_0x00b7:
            org.threeten.bp.ZoneOffset r4 = r13.standardOffset
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x00e6
            org.threeten.bp.zone.ZoneOffsetTransition r4 = new org.threeten.bp.zone.ZoneOffsetTransition
            r23 = r11
            long r10 = r9.toEpochSecond(r12)
            org.threeten.bp.LocalDateTime r10 = org.threeten.bp.LocalDateTime.ofEpochSecond(r10, r2, r3)
            org.threeten.bp.ZoneOffset r11 = r13.standardOffset
            r4.<init>((org.threeten.bp.LocalDateTime) r10, (org.threeten.bp.ZoneOffset) r3, (org.threeten.bp.ZoneOffset) r11)
            java.lang.Object r3 = r0.deduplicate(r4)
            r5.add(r3)
            org.threeten.bp.ZoneOffset r3 = r13.standardOffset
            java.lang.Object r3 = r0.deduplicate(r3)
            org.threeten.bp.ZoneOffset r3 = (org.threeten.bp.ZoneOffset) r3
            goto L_0x00e8
        L_0x00e6:
            r23 = r11
        L_0x00e8:
            int r4 = r3.getTotalSeconds()
            int r10 = r14.intValue()
            int r4 = r4 + r10
            org.threeten.bp.ZoneOffset r4 = org.threeten.bp.ZoneOffset.ofTotalSeconds(r4)
            java.lang.Object r4 = r0.deduplicate(r4)
            org.threeten.bp.ZoneOffset r4 = (org.threeten.bp.ZoneOffset) r4
            boolean r10 = r12.equals(r4)
            if (r10 != 0) goto L_0x010f
            org.threeten.bp.zone.ZoneOffsetTransition r10 = new org.threeten.bp.zone.ZoneOffsetTransition
            r10.<init>((org.threeten.bp.LocalDateTime) r9, (org.threeten.bp.ZoneOffset) r12, (org.threeten.bp.ZoneOffset) r4)
            java.lang.Object r4 = r0.deduplicate(r10)
            org.threeten.bp.zone.ZoneOffsetTransition r4 = (org.threeten.bp.zone.ZoneOffsetTransition) r4
            r6.add(r4)
        L_0x010f:
            int r4 = r14.intValue()
            java.util.List r10 = r13.ruleList
            java.util.Iterator r10 = r10.iterator()
        L_0x011b:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x0166
            java.lang.Object r11 = r10.next()
            org.threeten.bp.zone.ZoneRulesBuilder$TZRule r11 = (org.threeten.bp.zone.ZoneRulesBuilder.TZRule) r11
            org.threeten.bp.zone.ZoneOffsetTransition r14 = r11.toTransition(r3, r4)
            java.lang.Object r14 = r0.deduplicate(r14)
            org.threeten.bp.zone.ZoneOffsetTransition r14 = (org.threeten.bp.zone.ZoneOffsetTransition) r14
            long r15 = r14.toEpochSecond()
            long r17 = r9.toEpochSecond(r12)
            int r19 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r19 >= 0) goto L_0x013f
            r15 = 1
            goto L_0x0140
        L_0x013f:
            r15 = 0
        L_0x0140:
            if (r15 != 0) goto L_0x0164
            long r15 = r14.toEpochSecond()
            long r17 = r13.createDateTimeEpochSecond(r4)
            int r19 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r19 >= 0) goto L_0x0164
            org.threeten.bp.ZoneOffset r15 = r14.getOffsetBefore()
            org.threeten.bp.ZoneOffset r2 = r14.getOffsetAfter()
            boolean r2 = r15.equals(r2)
            if (r2 != 0) goto L_0x0164
            r6.add(r14)
            int r2 = r11.savingAmountSecs
            r4 = r2
        L_0x0164:
            r2 = 0
            goto L_0x011b
        L_0x0166:
            java.util.List r2 = r13.lastRuleList
            java.util.Iterator r2 = r2.iterator()
        L_0x016e:
            boolean r9 = r2.hasNext()
            if (r9 == 0) goto L_0x018c
            java.lang.Object r9 = r2.next()
            org.threeten.bp.zone.ZoneRulesBuilder$TZRule r9 = (org.threeten.bp.zone.ZoneRulesBuilder.TZRule) r9
            org.threeten.bp.zone.ZoneOffsetTransitionRule r4 = r9.toTransitionRule(r3, r4)
            java.lang.Object r4 = r0.deduplicate(r4)
            org.threeten.bp.zone.ZoneOffsetTransitionRule r4 = (org.threeten.bp.zone.ZoneOffsetTransitionRule) r4
            r7.add(r4)
            int r4 = r9.savingAmountSecs
            goto L_0x016e
        L_0x018c:
            org.threeten.bp.ZoneOffset r2 = r13.createWallOffset(r4)
            java.lang.Object r2 = r0.deduplicate(r2)
            r12 = r2
            org.threeten.bp.ZoneOffset r12 = (org.threeten.bp.ZoneOffset) r12
            long r9 = r13.createDateTimeEpochSecond(r4)
            r2 = 0
            org.threeten.bp.LocalDateTime r9 = org.threeten.bp.LocalDateTime.ofEpochSecond(r9, r2, r12)
            java.lang.Object r9 = r0.deduplicate(r9)
            org.threeten.bp.LocalDateTime r9 = (org.threeten.bp.LocalDateTime) r9
            r11 = r23
            r10 = 1
            goto L_0x0069
        L_0x01ab:
            org.threeten.bp.zone.StandardZoneRules r9 = new org.threeten.bp.zone.StandardZoneRules
            org.threeten.bp.ZoneOffset r3 = r1.standardOffset
            r2 = r9
            r4 = r8
            r2.<init>((org.threeten.bp.ZoneOffset) r3, (org.threeten.bp.ZoneOffset) r4, (java.util.List<org.threeten.bp.zone.ZoneOffsetTransition>) r5, (java.util.List<org.threeten.bp.zone.ZoneOffsetTransition>) r6, (java.util.List<org.threeten.bp.zone.ZoneOffsetTransitionRule>) r7)
            return r9
        L_0x01b7:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "No windows have been added to the builder"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.zone.ZoneRulesBuilder.toRules(java.lang.String, java.util.Map):org.threeten.bp.zone.ZoneRules");
    }

    /* access modifiers changed from: package-private */
    public <T> T deduplicate(T t) {
        if (!this.deduplicateMap.containsKey(t)) {
            this.deduplicateMap.put(t, t);
        }
        return this.deduplicateMap.get(t);
    }

    class TZWindow {
        /* access modifiers changed from: private */
        public Integer fixedSavingAmountSecs;
        /* access modifiers changed from: private */
        public List<TZRule> lastRuleList = new ArrayList();
        private int maxLastRuleStartYear = Year.MIN_VALUE;
        /* access modifiers changed from: private */
        public List<TZRule> ruleList = new ArrayList();
        /* access modifiers changed from: private */
        public final ZoneOffset standardOffset;
        private final ZoneOffsetTransitionRule.TimeDefinition timeDefinition;
        private final LocalDateTime windowEnd;

        TZWindow(ZoneOffset zoneOffset, LocalDateTime localDateTime, ZoneOffsetTransitionRule.TimeDefinition timeDefinition2) {
            this.windowEnd = localDateTime;
            this.timeDefinition = timeDefinition2;
            this.standardOffset = zoneOffset;
        }

        /* access modifiers changed from: package-private */
        public void setFixedSavings(int i) {
            if (this.ruleList.size() > 0 || this.lastRuleList.size() > 0) {
                throw new IllegalStateException("Window has DST rules, so cannot have fixed savings");
            }
            this.fixedSavingAmountSecs = Integer.valueOf(i);
        }

        /* access modifiers changed from: package-private */
        public void addRule(int i, int i2, Month month, int i3, DayOfWeek dayOfWeek, LocalTime localTime, int i4, ZoneOffsetTransitionRule.TimeDefinition timeDefinition2, int i5) {
            if (this.fixedSavingAmountSecs != null) {
                throw new IllegalStateException("Window has a fixed DST saving, so cannot have DST rules");
            } else if (this.ruleList.size() < 2000) {
                boolean z = false;
                int i6 = i2;
                if (i6 == 999999999) {
                    z = true;
                    i6 = i;
                }
                for (int i7 = i; i7 <= i6; i7++) {
                    TZRule tZRule = new TZRule(i7, month, i3, dayOfWeek, localTime, i4, timeDefinition2, i5);
                    if (z) {
                        this.lastRuleList.add(tZRule);
                        this.maxLastRuleStartYear = Math.max(i, this.maxLastRuleStartYear);
                    } else {
                        int i8 = i;
                        this.ruleList.add(tZRule);
                    }
                }
            } else {
                throw new IllegalStateException("Window has reached the maximum number of allowed rules");
            }
        }

        /* access modifiers changed from: package-private */
        public void validateWindowOrder(TZWindow tZWindow) {
            if (this.windowEnd.isBefore(tZWindow.windowEnd)) {
                throw new IllegalStateException("Windows must be added in date-time order: " + this.windowEnd + " < " + tZWindow.windowEnd);
            }
        }

        /* access modifiers changed from: package-private */
        public void tidy(int i) {
            if (this.lastRuleList.size() != 1) {
                if (this.windowEnd.equals(LocalDateTime.MAX)) {
                    this.maxLastRuleStartYear = Math.max(this.maxLastRuleStartYear, i) + 1;
                    for (TZRule next : this.lastRuleList) {
                        addRule(next.year, this.maxLastRuleStartYear, next.month, next.dayOfMonthIndicator, next.dayOfWeek, next.time, next.adjustDays, next.timeDefinition, next.savingAmountSecs);
                        int unused = next.year = this.maxLastRuleStartYear + 1;
                    }
                    int i2 = this.maxLastRuleStartYear;
                    if (i2 == 999999999) {
                        this.lastRuleList.clear();
                    } else {
                        this.maxLastRuleStartYear = i2 + 1;
                    }
                } else {
                    int year = this.windowEnd.getYear();
                    for (TZRule next2 : this.lastRuleList) {
                        addRule(next2.year, year + 1, next2.month, next2.dayOfMonthIndicator, next2.dayOfWeek, next2.time, next2.adjustDays, next2.timeDefinition, next2.savingAmountSecs);
                    }
                    this.lastRuleList.clear();
                    this.maxLastRuleStartYear = Year.MAX_VALUE;
                }
                Collections.sort(this.ruleList);
                Collections.sort(this.lastRuleList);
                if (this.ruleList.size() == 0 && this.fixedSavingAmountSecs == null) {
                    this.fixedSavingAmountSecs = 0;
                    return;
                }
                return;
            }
            throw new IllegalStateException("Cannot have only one rule defined as being forever");
        }

        /* access modifiers changed from: package-private */
        public boolean isSingleWindowStandardOffset() {
            return this.windowEnd.equals(LocalDateTime.MAX) && this.timeDefinition == ZoneOffsetTransitionRule.TimeDefinition.WALL && this.fixedSavingAmountSecs == null && this.lastRuleList.isEmpty() && this.ruleList.isEmpty();
        }

        /* access modifiers changed from: package-private */
        public ZoneOffset createWallOffset(int i) {
            return ZoneOffset.ofTotalSeconds(this.standardOffset.getTotalSeconds() + i);
        }

        /* access modifiers changed from: package-private */
        public long createDateTimeEpochSecond(int i) {
            ZoneOffset createWallOffset = createWallOffset(i);
            return this.timeDefinition.createDateTime(this.windowEnd, this.standardOffset, createWallOffset).toEpochSecond(createWallOffset);
        }
    }

    class TZRule implements Comparable<TZRule> {
        /* access modifiers changed from: private */
        public int adjustDays;
        /* access modifiers changed from: private */
        public int dayOfMonthIndicator;
        /* access modifiers changed from: private */
        public DayOfWeek dayOfWeek;
        /* access modifiers changed from: private */
        public Month month;
        /* access modifiers changed from: private */
        public int savingAmountSecs;
        /* access modifiers changed from: private */
        public LocalTime time;
        /* access modifiers changed from: private */
        public ZoneOffsetTransitionRule.TimeDefinition timeDefinition;
        /* access modifiers changed from: private */
        public int year;

        TZRule(int i, Month month2, int i2, DayOfWeek dayOfWeek2, LocalTime localTime, int i3, ZoneOffsetTransitionRule.TimeDefinition timeDefinition2, int i4) {
            this.year = i;
            this.month = month2;
            this.dayOfMonthIndicator = i2;
            this.dayOfWeek = dayOfWeek2;
            this.time = localTime;
            this.adjustDays = i3;
            this.timeDefinition = timeDefinition2;
            this.savingAmountSecs = i4;
        }

        /* access modifiers changed from: package-private */
        public ZoneOffsetTransition toTransition(ZoneOffset zoneOffset, int i) {
            LocalDate localDate = toLocalDate();
            ZoneOffset zoneOffset2 = (ZoneOffset) ZoneRulesBuilder.this.deduplicate(ZoneOffset.ofTotalSeconds(zoneOffset.getTotalSeconds() + i));
            return new ZoneOffsetTransition((LocalDateTime) ZoneRulesBuilder.this.deduplicate(this.timeDefinition.createDateTime((LocalDateTime) ZoneRulesBuilder.this.deduplicate(LocalDateTime.of(((LocalDate) ZoneRulesBuilder.this.deduplicate(localDate)).plusDays((long) this.adjustDays), this.time)), zoneOffset, zoneOffset2)), zoneOffset2, (ZoneOffset) ZoneRulesBuilder.this.deduplicate(ZoneOffset.ofTotalSeconds(zoneOffset.getTotalSeconds() + this.savingAmountSecs)));
        }

        /* access modifiers changed from: package-private */
        public ZoneOffsetTransitionRule toTransitionRule(ZoneOffset zoneOffset, int i) {
            if (this.dayOfMonthIndicator < 0 && this.month != Month.FEBRUARY) {
                this.dayOfMonthIndicator = this.month.maxLength() - 6;
            }
            ZoneOffsetTransition transition = toTransition(zoneOffset, i);
            return new ZoneOffsetTransitionRule(this.month, this.dayOfMonthIndicator, this.dayOfWeek, this.time, this.adjustDays, this.timeDefinition, zoneOffset, transition.getOffsetBefore(), transition.getOffsetAfter());
        }

        public int compareTo(TZRule tZRule) {
            int i = this.year - tZRule.year;
            if (i == 0) {
                i = this.month.compareTo(tZRule.month);
            }
            if (i == 0) {
                i = toLocalDate().compareTo((ChronoLocalDate) tZRule.toLocalDate());
            }
            if (i != 0) {
                return i;
            }
            int i2 = (((long) (this.time.toSecondOfDay() + (this.adjustDays * DateTimeConstants.SECONDS_PER_DAY))) > ((long) (tZRule.time.toSecondOfDay() + (tZRule.adjustDays * DateTimeConstants.SECONDS_PER_DAY))) ? 1 : (((long) (this.time.toSecondOfDay() + (this.adjustDays * DateTimeConstants.SECONDS_PER_DAY))) == ((long) (tZRule.time.toSecondOfDay() + (tZRule.adjustDays * DateTimeConstants.SECONDS_PER_DAY))) ? 0 : -1));
            if (i2 < 0) {
                return -1;
            }
            return i2 > 0 ? 1 : 0;
        }

        private LocalDate toLocalDate() {
            int i = this.dayOfMonthIndicator;
            if (i < 0) {
                LocalDate of = LocalDate.of(this.year, this.month, this.month.length(IsoChronology.INSTANCE.isLeapYear((long) this.year)) + 1 + this.dayOfMonthIndicator);
                DayOfWeek dayOfWeek2 = this.dayOfWeek;
                if (dayOfWeek2 != null) {
                    return of.with(TemporalAdjusters.previousOrSame(dayOfWeek2));
                }
                return of;
            }
            LocalDate of2 = LocalDate.of(this.year, this.month, i);
            DayOfWeek dayOfWeek3 = this.dayOfWeek;
            return dayOfWeek3 != null ? of2.with(TemporalAdjusters.nextOrSame(dayOfWeek3)) : of2;
        }
    }
}
