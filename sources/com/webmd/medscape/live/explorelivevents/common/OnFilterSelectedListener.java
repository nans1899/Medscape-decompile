package com.webmd.medscape.live.explorelivevents.common;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.ZonedDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016¨\u0006\r"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/OnFilterSelectedListener;", "", "onClick", "", "title", "", "datesSelected", "Lkotlin/Pair;", "Lorg/threeten/bp/ZonedDateTime;", "onDateRangeFilterSelected", "onLocationFilterSelected", "onSpecialtyFilterSelected", "onSpecificFilterSelected", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OnFilterSelectedListener.kt */
public interface OnFilterSelectedListener {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    /* compiled from: OnFilterSelectedListener.kt */
    public static final class DefaultImpls {
        public static void onClick(OnFilterSelectedListener onFilterSelectedListener, String str, Pair<ZonedDateTime, ZonedDateTime> pair) {
            Intrinsics.checkNotNullParameter(str, "title");
            Intrinsics.checkNotNullParameter(pair, "datesSelected");
        }

        public static void onDateRangeFilterSelected(OnFilterSelectedListener onFilterSelectedListener) {
        }

        public static void onLocationFilterSelected(OnFilterSelectedListener onFilterSelectedListener) {
        }

        public static void onSpecialtyFilterSelected(OnFilterSelectedListener onFilterSelectedListener) {
        }

        public static void onSpecificFilterSelected(OnFilterSelectedListener onFilterSelectedListener) {
        }
    }

    void onClick(String str, Pair<ZonedDateTime, ZonedDateTime> pair);

    void onDateRangeFilterSelected();

    void onLocationFilterSelected();

    void onSpecialtyFilterSelected();

    void onSpecificFilterSelected();
}
