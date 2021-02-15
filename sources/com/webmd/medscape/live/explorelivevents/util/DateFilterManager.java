package com.webmd.medscape.live.explorelivevents.util;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.temporal.TemporalAdjuster;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004J\u001e\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004J\u0016\u0010\n\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004J\u0016\u0010\u000b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004J\u0016\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¨\u0006\r"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/util/DateFilterManager;", "", "()V", "allUpcoming", "Lkotlin/Pair;", "Lorg/threeten/bp/ZonedDateTime;", "getNextWeek", "dayOfWeek", "Lorg/threeten/bp/DayOfWeek;", "getThisWeek", "getThisWeekend", "getToday", "getTommorow", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DateFilterManager.kt */
public final class DateFilterManager {
    public static final DateFilterManager INSTANCE = new DateFilterManager();

    private DateFilterManager() {
    }

    public final Pair<ZonedDateTime, ZonedDateTime> getToday() {
        return new Pair<>(ZonedDateTime.now(), ZonedDateTime.now());
    }

    public final Pair<ZonedDateTime, ZonedDateTime> getTommorow() {
        ZonedDateTime plusDays = ZonedDateTime.now().plusDays(1);
        return new Pair<>(plusDays, plusDays);
    }

    public final Pair<ZonedDateTime, ZonedDateTime> getThisWeek() {
        ZonedDateTime now = ZonedDateTime.now();
        Intrinsics.checkNotNullExpressionValue(now, "today");
        return ExtensionsKt.getNextWeek(now);
    }

    public final Pair<ZonedDateTime, ZonedDateTime> getThisWeekend() {
        ZonedDateTime now = ZonedDateTime.now();
        Intrinsics.checkNotNullExpressionValue(now, "today");
        return ExtensionsKt.getThisWeekend(now);
    }

    public final Pair<ZonedDateTime, ZonedDateTime> getNextWeek(DayOfWeek dayOfWeek) {
        Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
        ZonedDateTime with = ZonedDateTime.now().with((TemporalAdjuster) dayOfWeek);
        Intrinsics.checkNotNullExpressionValue(with, "today");
        return ExtensionsKt.getNextWeek(with);
    }

    public final Pair<ZonedDateTime, ZonedDateTime> allUpcoming() {
        return new Pair<>(ZonedDateTime.now(), null);
    }
}
