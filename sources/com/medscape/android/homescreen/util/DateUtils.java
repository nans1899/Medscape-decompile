package com.medscape.android.homescreen.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/medscape/android/homescreen/util/DateUtils;", "", "()V", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DateUtils.kt */
public final class DateUtils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u0006\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/medscape/android/homescreen/util/DateUtils$Companion;", "", "()V", "getDate", "", "dateString", "getDateWithMonth", "getDisplayDate", "calendar", "Ljava/util/Calendar;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: DateUtils.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getDate(String str) {
            Intrinsics.checkNotNullParameter(str, "dateString");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss'Z'");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
            try {
                String format = simpleDateFormat2.format(simpleDateFormat.parse(str));
                Intrinsics.checkNotNullExpressionValue(format, "formatter.format(parser.parse(dateString))");
                return format;
            } catch (Exception unused) {
                String format2 = simpleDateFormat2.format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
                Intrinsics.checkNotNullExpressionValue(format2, "formatter.format(parser.parse(dateString))");
                return format2;
            }
        }

        public final String getDateWithMonth(String str) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss'Z'");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMMM dd, yyyy");
            CharSequence charSequence = str;
            if (charSequence == null || charSequence.length() == 0) {
                return "";
            }
            try {
                String format = simpleDateFormat2.format(simpleDateFormat.parse(str));
                Intrinsics.checkNotNullExpressionValue(format, "formatter.format(parser.parse(dateString))");
                return format;
            } catch (Exception unused) {
                String format2 = simpleDateFormat2.format(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
                Intrinsics.checkNotNullExpressionValue(format2, "formatter.format(parser.parse(dateString))");
                return format2;
            }
        }

        public final String getDisplayDate(Calendar calendar) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            String format = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "formatter.format(calendar.time)");
            return format;
        }
    }
}
