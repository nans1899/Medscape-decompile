package com.webmd.medscape.live.explorelivevents.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.widget.DatePicker;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.medscape.android.cache.Cache;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import com.webmd.medscape.live.explorelivevents.data.SearchFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u001a\n\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\u0002\u001a\u000e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u000e\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a2\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\f\u001a:\u0010\u000e\u001a\u00020\u000f\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\t0\f\u001a\u0012\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0003\u001a\n\u0010\u0016\u001a\u00020\u0003*\u00020\u0017\u001a\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\b*\b\u0012\u0004\u0012\u00020\u00030\b\u001a\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u001b*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0014H\u0007\u001a\u001a\u0010\u001d\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001e*\u00020\u001f\u001a\u001a\u0010 \u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001e*\u00020\u001f\u001a\u001a\u0010!\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001e*\u00020\u001f\u001a\u000e\u0010\"\u001a\u0004\u0018\u00010\u001f*\u0004\u0018\u00010\u0003\u001a\n\u0010#\u001a\u00020\u000f*\u00020\u0014\u001a\n\u0010$\u001a\u00020\u000f*\u00020\u0005\u001a\u000e\u0010%\u001a\u0004\u0018\u00010\u0003*\u0004\u0018\u00010\u001f\u001a\f\u0010&\u001a\u00020\u0003*\u0004\u0018\u00010\u0003\u001a\u000e\u0010&\u001a\u0004\u0018\u00010\u0003*\u0004\u0018\u00010\u001f\u001a\u0012\u0010'\u001a\u00020\u0003*\u00020\u00142\u0006\u0010(\u001a\u00020\u0003\u001a$\u0010)\u001a\u00020**\u00020+2\u0006\u0010\u001c\u001a\u00020\u00142\b\b\u0001\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u0003\u001a6\u0010.\u001a\u00020**\u00020/2\u0006\u00100\u001a\u00020\u001f2\"\u00101\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001e\u0012\u0004\u0012\u00020*02\u001a5\u00103\u001a\u00020**\u00020\u00142\u0006\u00104\u001a\u00020\u001f2!\u00105\u001a\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020*02\u001a \u00109\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010:\u001a\u00020\u000f\u001a\u0016\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\b\u0012\u0004\u0012\u00020\u00190\b\u001a\u000e\u0010<\u001a\u0004\u0018\u00010\u0003*\u0004\u0018\u00010\u001f\u001a\u000e\u0010=\u001a\u0004\u0018\u00010\u0003*\u0004\u0018\u00010\u0003\u001a\n\u0010=\u001a\u00020\u0003*\u00020\u001f\u001a\u0012\u0010>\u001a\u00020\u000f*\u00020\u00052\u0006\u0010?\u001a\u00020\u0005¨\u0006@"}, d2 = {"createDateConstraints", "Lcom/google/android/material/datepicker/CalendarConstraints;", "formatDateDisplay", "", "date", "Ljava/util/Calendar;", "formatDateServer", "getSelectedItems", "", "T", "", "chosenItems", "", "alreadySavedItems", "getTickStatus", "", "selectedItems", "previouslySelectedItems", "checkPermission", "", "Landroid/content/Context;", "permission", "createExitUrl", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "fromJsonToListOfFilters", "Lcom/webmd/medscape/live/explorelivevents/data/SearchFilter;", "getErrorImageRes", "Landroid/graphics/drawable/Drawable;", "context", "getNextWeek", "Lkotlin/Pair;", "Lorg/threeten/bp/ZonedDateTime;", "getThisWeek", "getThisWeekend", "getZoneDateTimeFromString", "isNetworkAvailable", "isPastDate", "parse", "parseServerDateToReadableFormat", "readJsonFromAssets", "fileName", "setTitle", "", "Landroid/view/MenuItem;", "title", "color", "showDateRangePicker", "Landroidx/fragment/app/FragmentActivity;", "time", "successCallback", "Lkotlin/Function1;", "showNativeDatePicker", "selectedDate", "calendarCallback", "Lkotlin/ParameterName;", "name", "dateSelected", "toFilterTitle", "isLocationFilter", "toJsonArrayString", "toServerDate", "toUserSegFormat", "validateDates", "endDate", "wbmd.explorelivevents_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: Extensions.kt */
public final class ExtensionsKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[DayOfWeek.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[DayOfWeek.MONDAY.ordinal()] = 1;
            $EnumSwitchMapping$0[DayOfWeek.TUESDAY.ordinal()] = 2;
            $EnumSwitchMapping$0[DayOfWeek.WEDNESDAY.ordinal()] = 3;
            $EnumSwitchMapping$0[DayOfWeek.THURSDAY.ordinal()] = 4;
            $EnumSwitchMapping$0[DayOfWeek.FRIDAY.ordinal()] = 5;
            $EnumSwitchMapping$0[DayOfWeek.SATURDAY.ordinal()] = 6;
            $EnumSwitchMapping$0[DayOfWeek.SUNDAY.ordinal()] = 7;
            int[] iArr2 = new int[DayOfWeek.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[DayOfWeek.MONDAY.ordinal()] = 1;
            $EnumSwitchMapping$1[DayOfWeek.TUESDAY.ordinal()] = 2;
            $EnumSwitchMapping$1[DayOfWeek.WEDNESDAY.ordinal()] = 3;
            $EnumSwitchMapping$1[DayOfWeek.THURSDAY.ordinal()] = 4;
            $EnumSwitchMapping$1[DayOfWeek.FRIDAY.ordinal()] = 5;
            $EnumSwitchMapping$1[DayOfWeek.SATURDAY.ordinal()] = 6;
            int[] iArr3 = new int[DayOfWeek.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[DayOfWeek.MONDAY.ordinal()] = 1;
            $EnumSwitchMapping$2[DayOfWeek.TUESDAY.ordinal()] = 2;
            $EnumSwitchMapping$2[DayOfWeek.WEDNESDAY.ordinal()] = 3;
            $EnumSwitchMapping$2[DayOfWeek.THURSDAY.ordinal()] = 4;
            $EnumSwitchMapping$2[DayOfWeek.FRIDAY.ordinal()] = 5;
            $EnumSwitchMapping$2[DayOfWeek.SATURDAY.ordinal()] = 6;
        }
    }

    public static final boolean isNetworkAvailable(Context context) {
        Intrinsics.checkNotNullParameter(context, "$this$isNetworkAvailable");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        Intrinsics.checkNotNull(connectivityManager);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String readJsonFromAssets(android.content.Context r1, java.lang.String r2) {
        /*
            java.lang.String r0 = "$this$readJsonFromAssets"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "fileName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ IOException -> 0x0048 }
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ IOException -> 0x0048 }
            java.lang.String r2 = "this.assets.open(fileName)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch:{ IOException -> 0x0048 }
            java.nio.charset.Charset r2 = kotlin.text.Charsets.UTF_8     // Catch:{ IOException -> 0x0048 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0048 }
            r0.<init>(r1, r2)     // Catch:{ IOException -> 0x0048 }
            java.io.Reader r0 = (java.io.Reader) r0     // Catch:{ IOException -> 0x0048 }
            r1 = 8192(0x2000, float:1.14794E-41)
            boolean r2 = r0 instanceof java.io.BufferedReader     // Catch:{ IOException -> 0x0048 }
            if (r2 == 0) goto L_0x0029
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch:{ IOException -> 0x0048 }
            goto L_0x002f
        L_0x0029:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0048 }
            r2.<init>(r0, r1)     // Catch:{ IOException -> 0x0048 }
            r0 = r2
        L_0x002f:
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ IOException -> 0x0048 }
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ IOException -> 0x0048 }
            r2 = r0
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2     // Catch:{ all -> 0x0041 }
            java.io.Reader r2 = (java.io.Reader) r2     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = kotlin.io.TextStreamsKt.readText(r2)     // Catch:{ all -> 0x0041 }
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ IOException -> 0x0048 }
            return r2
        L_0x0041:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ IOException -> 0x0048 }
            throw r2     // Catch:{ IOException -> 0x0048 }
        L_0x0048:
            r1 = move-exception
            r1.printStackTrace()
            java.lang.String r1 = ""
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.util.ExtensionsKt.readJsonFromAssets(android.content.Context, java.lang.String):java.lang.String");
    }

    public static final String formatDateServer(Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, OmnitureConstants.OMNITURE_FILTER_DATE);
        String format = new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "formattedDate");
        return StringsKt.replace$default(format, "/", "-", false, 4, (Object) null);
    }

    public static final String toServerDate(ZonedDateTime zonedDateTime) {
        ZonedDateTime withZoneSameInstant;
        LocalDateTime localDateTime;
        if (zonedDateTime == null || (withZoneSameInstant = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())) == null || (localDateTime = withZoneSameInstant.toLocalDateTime()) == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    }

    public static final String toUserSegFormat(String str) {
        if (str == null) {
            return null;
        }
        List split$default = StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null);
        String str2 = (String) split$default.get(0);
        String str3 = (String) split$default.get(1);
        String str4 = (String) split$default.get(2);
        if (str4 != null) {
            String substring = str4.substring(0, 2);
            Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return (str2 + str3) + substring;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    public static final String toUserSegFormat(ZonedDateTime zonedDateTime) {
        Intrinsics.checkNotNullParameter(zonedDateTime, "$this$toUserSegFormat");
        String format = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("MMddyy"));
        Intrinsics.checkNotNullExpressionValue(format, "this.withZoneSameInstant…tter.ofPattern(\"MMddyy\"))");
        return format;
    }

    public static final Pair<ZonedDateTime, ZonedDateTime> getThisWeekend(ZonedDateTime zonedDateTime) {
        Intrinsics.checkNotNullParameter(zonedDateTime, "$this$getThisWeekend");
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();
        ZonedDateTime zonedDateTime2 = null;
        if (dayOfWeek == null) {
            return new Pair<>(null, null);
        }
        switch (WhenMappings.$EnumSwitchMapping$0[dayOfWeek.ordinal()]) {
            case 1:
                return new Pair<>(zonedDateTime.plusDays(5), zonedDateTime.plusDays(6));
            case 2:
                return new Pair<>(zonedDateTime.plusDays(4), zonedDateTime.plusDays(5));
            case 3:
                return new Pair<>(zonedDateTime.plusDays(3), zonedDateTime.plusDays(4));
            case 4:
                return new Pair<>(zonedDateTime.plusDays(2), zonedDateTime.plusDays(3));
            case 5:
                return new Pair<>(zonedDateTime.plusDays(1), zonedDateTime.plusDays(2));
            case 6:
                return new Pair<>(zonedDateTime2, zonedDateTime.plusDays(1));
            case 7:
                return new Pair<>(ZonedDateTime.now(), ZonedDateTime.now());
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final Pair<ZonedDateTime, ZonedDateTime> getThisWeek(ZonedDateTime zonedDateTime) {
        ZonedDateTime zonedDateTime2;
        Intrinsics.checkNotNullParameter(zonedDateTime, "$this$getThisWeek");
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();
        ZonedDateTime zonedDateTime3 = null;
        if (dayOfWeek != null) {
            switch (WhenMappings.$EnumSwitchMapping$1[dayOfWeek.ordinal()]) {
                case 1:
                    zonedDateTime2 = zonedDateTime.plusDays(5);
                    break;
                case 2:
                    zonedDateTime2 = zonedDateTime.plusDays(4);
                    break;
                case 3:
                    zonedDateTime2 = zonedDateTime.plusDays(3);
                    break;
                case 4:
                    zonedDateTime2 = zonedDateTime.plusDays(2);
                    break;
                case 5:
                    zonedDateTime2 = zonedDateTime.plusDays(1);
                    break;
                case 6:
                    zonedDateTime2 = zonedDateTime;
                    break;
            }
        }
        zonedDateTime2 = zonedDateTime.plusDays(6);
        return new Pair<>(zonedDateTime, zonedDateTime2);
    }

    public static final Pair<ZonedDateTime, ZonedDateTime> getNextWeek(ZonedDateTime zonedDateTime) {
        ZonedDateTime zonedDateTime2;
        Intrinsics.checkNotNullParameter(zonedDateTime, "$this$getNextWeek");
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();
        ZonedDateTime zonedDateTime3 = null;
        if (dayOfWeek != null) {
            switch (WhenMappings.$EnumSwitchMapping$2[dayOfWeek.ordinal()]) {
                case 1:
                    zonedDateTime2 = zonedDateTime.plusDays(5);
                    break;
                case 2:
                    zonedDateTime2 = zonedDateTime.plusDays(4);
                    break;
                case 3:
                    zonedDateTime2 = zonedDateTime.plusDays(3);
                    break;
                case 4:
                    zonedDateTime2 = zonedDateTime.plusDays(2);
                    break;
                case 5:
                    zonedDateTime2 = zonedDateTime.plusDays(1);
                    break;
                case 6:
                    zonedDateTime2 = zonedDateTime;
                    break;
            }
        }
        zonedDateTime2 = zonedDateTime.plusDays(6);
        return new Pair<>(zonedDateTime, zonedDateTime2);
    }

    public static final String formatDateDisplay(Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, OmnitureConstants.OMNITURE_FILTER_DATE);
        String format = new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormatter.format(date.time)");
        return format;
    }

    public static final boolean isPastDate(Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "$this$isPastDate");
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "Calendar.getInstance()");
        return formatDateDisplay(calendar).compareTo(formatDateDisplay(instance)) < 0;
    }

    public static final boolean validateDates(Calendar calendar, Calendar calendar2) {
        Intrinsics.checkNotNullParameter(calendar, "$this$validateDates");
        Intrinsics.checkNotNullParameter(calendar2, "endDate");
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "Calendar.getInstance()");
        String formatDateDisplay = formatDateDisplay(instance);
        Log.e("ValidateDates", "Current date " + formatDateDisplay);
        Log.e("ValidateDates", "Start date " + formatDateDisplay(calendar));
        Log.e("ValidateDates", "End date " + formatDateDisplay(calendar2));
        String formatDateDisplay2 = formatDateDisplay(calendar);
        String formatDateDisplay3 = formatDateDisplay(calendar2);
        if (formatDateDisplay2.compareTo(formatDateDisplay) >= 0 && formatDateDisplay3.compareTo(formatDateDisplay) >= 0 && formatDateDisplay3.compareTo(formatDateDisplay2) >= 0) {
            return true;
        }
        return false;
    }

    public static final void showNativeDatePicker(Context context, ZonedDateTime zonedDateTime, Function1<? super ZonedDateTime, Unit> function1) {
        Intrinsics.checkNotNullParameter(context, "$this$showNativeDatePicker");
        Intrinsics.checkNotNullParameter(zonedDateTime, "selectedDate");
        Intrinsics.checkNotNullParameter(function1, "calendarCallback");
        long epochMilli = zonedDateTime.toInstant().toEpochMilli();
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "currentDate");
        instance.setTimeInMillis(epochMilli);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new ExtensionsKt$showNativeDatePicker$datePickerDialog$1(function1), instance.get(1), instance.get(2), instance.get(5));
        DatePicker datePicker = datePickerDialog.getDatePicker();
        Intrinsics.checkNotNullExpressionValue(datePicker, "datePickerDialog.datePicker");
        Calendar instance2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance2, "Calendar.getInstance()");
        datePicker.setMinDate(instance2.getTimeInMillis());
        datePickerDialog.setCancelable(false);
        datePickerDialog.setOnCancelListener(new ExtensionsKt$showNativeDatePicker$1(datePickerDialog));
        datePickerDialog.show();
    }

    public static final int checkPermission(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "$this$checkPermission");
        Intrinsics.checkNotNullParameter(str, "permission");
        return ContextCompat.checkSelfPermission(context, str) != 0 ? -1 : 0;
    }

    public static final void showDateRangePicker(FragmentActivity fragmentActivity, ZonedDateTime zonedDateTime, Function1<? super Pair<String, String>, Unit> function1) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$this$showDateRangePicker");
        Intrinsics.checkNotNullParameter(zonedDateTime, Cache.Caches.TIME);
        Intrinsics.checkNotNullParameter(function1, "successCallback");
        MaterialDatePicker.Builder<androidx.core.util.Pair<Long, Long>> dateRangePicker = MaterialDatePicker.Builder.dateRangePicker();
        Intrinsics.checkNotNullExpressionValue(dateRangePicker, "MaterialDatePicker.Builder.dateRangePicker()");
        dateRangePicker.setCalendarConstraints(createDateConstraints());
        dateRangePicker.setSelection(new androidx.core.util.Pair(Long.valueOf(zonedDateTime.toInstant().toEpochMilli()), Long.valueOf(zonedDateTime.toInstant().toEpochMilli())));
        MaterialDatePicker<androidx.core.util.Pair<Long, Long>> build = dateRangePicker.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        build.show(fragmentActivity.getSupportFragmentManager(), build.toString());
        build.addOnNegativeButtonClickListener(new ExtensionsKt$showDateRangePicker$1(build));
        build.addOnCancelListener(ExtensionsKt$showDateRangePicker$2.INSTANCE);
        build.addOnPositiveButtonClickListener(new ExtensionsKt$showDateRangePicker$3(build, function1));
    }

    private static final CalendarConstraints createDateConstraints() {
        CalendarConstraints.Builder builder = new CalendarConstraints.Builder();
        DateValidatorPointForward from = DateValidatorPointForward.from(ZonedDateTime.now().minusDays(1).toInstant().toEpochMilli());
        Intrinsics.checkNotNullExpressionValue(from, "DateValidatorPointForwar…Instant().toEpochMilli())");
        builder.setValidator(from);
        return builder.build();
    }

    public static final String parse(ZonedDateTime zonedDateTime) {
        ZonedDateTime withZoneSameInstant;
        LocalDateTime localDateTime;
        if (zonedDateTime == null || (withZoneSameInstant = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"))) == null || (localDateTime = withZoneSameInstant.toLocalDateTime()) == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
    }

    public static final ZonedDateTime getZoneDateTimeFromString(String str) {
        if (str == null) {
            return null;
        }
        List split$default = StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null);
        int parseInt = Integer.parseInt((String) split$default.get(0));
        int parseInt2 = Integer.parseInt((String) split$default.get(1));
        int parseInt3 = Integer.parseInt((String) split$default.get(2));
        ZonedDateTime now = ZonedDateTime.now();
        Intrinsics.checkNotNullExpressionValue(now, "now");
        return ZonedDateTime.of(parseInt3, parseInt, parseInt2, now.getHour(), now.getMinute(), now.getSecond(), now.getNano(), ZoneId.of("UTC"));
    }

    public static final String parseServerDateToReadableFormat(ZonedDateTime zonedDateTime) {
        ZonedDateTime withZoneSameInstant;
        LocalDateTime localDateTime;
        if (zonedDateTime == null || (withZoneSameInstant = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"))) == null || (localDateTime = withZoneSameInstant.toLocalDateTime()) == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd"));
    }

    public static final String parseServerDateToReadableFormat(String str) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd", Locale.getDefault());
        if (str == null) {
            return "";
        }
        List split$default = StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null);
        int parseInt = Integer.parseInt((String) split$default.get(0));
        instance.set(Integer.parseInt((String) split$default.get(2)), parseInt - 1, Integer.parseInt((String) split$default.get(1)));
        Intrinsics.checkNotNullExpressionValue(instance, "calendar");
        String format = simpleDateFormat.format(instance.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "formatter.format(calendar.time)");
        return format;
    }

    public static final List<String> toJsonArrayString(List<SearchFilter> list) {
        Intrinsics.checkNotNullParameter(list, "$this$toJsonArrayString");
        List<String> arrayList = new ArrayList<>();
        Iterable<SearchFilter> iterable = list;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (SearchFilter serialize : iterable) {
            arrayList2.add(Boolean.valueOf(arrayList.add(GsonUtils.INSTANCE.serialize(SearchFilter.class, serialize))));
        }
        List list2 = (List) arrayList2;
        return arrayList;
    }

    public static final List<SearchFilter> fromJsonToListOfFilters(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "$this$fromJsonToListOfFilters");
        List<SearchFilter> arrayList = new ArrayList<>();
        Iterable<String> iterable = list;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String deserialize : iterable) {
            Object deserialize2 = GsonUtils.INSTANCE.deserialize(SearchFilter.class, deserialize);
            if (deserialize2 != null) {
                arrayList2.add(Boolean.valueOf(arrayList.add((SearchFilter) deserialize2)));
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.data.SearchFilter");
            }
        }
        List list2 = (List) arrayList2;
        return arrayList;
    }

    public static final String toFilterTitle(List<String> list, Context context, boolean z) {
        Intrinsics.checkNotNullParameter(list, "$this$toFilterTitle");
        Intrinsics.checkNotNullParameter(context, "context");
        if (z) {
            String string = context.getString(R.string.locations_filter_title, new Object[]{Integer.valueOf(list.size())});
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …,\n        this.size\n    )");
            return string;
        }
        String string2 = context.getString(R.string.specialties_filter_title, new Object[]{Integer.valueOf(list.size())});
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(\n     …      this.size\n        )");
        return string2;
    }

    public static final <T> List<T> getSelectedItems(Set<T> set, Set<T> set2) {
        Intrinsics.checkNotNullParameter(set, "chosenItems");
        Intrinsics.checkNotNullParameter(set2, "alreadySavedItems");
        if (set.isEmpty() && (!set2.isEmpty())) {
            return CollectionsKt.toList(set2);
        }
        if (!set.isEmpty()) {
            Collection collection = set2;
            if (!collection.isEmpty()) {
                set.addAll(collection);
                return CollectionsKt.toList(set);
            }
        }
        return CollectionsKt.toList(set);
    }

    public static final void setTitle(MenuItem menuItem, Context context, int i, String str) {
        Intrinsics.checkNotNullParameter(menuItem, "$this$setTitle");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "color");
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(title)");
        Spanned fromHtml = HtmlCompat.fromHtml("<font color='#" + str + "'>" + string + "</>", 0, (Html.ImageGetter) null, (Html.TagHandler) null);
        Intrinsics.checkExpressionValueIsNotNull(fromHtml, "HtmlCompat.fromHtml(this… imageGetter, tagHandler)");
        menuItem.setTitle(fromHtml);
    }

    public static final Drawable getErrorImageRes(int i, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (i == 401) {
            return ContextCompat.getDrawable(context, R.drawable.shutterstock_1711190344);
        }
        if (i == 404) {
            return ContextCompat.getDrawable(context, R.drawable.shutterstock_1711190344);
        }
        if (i != 414) {
            return ContextCompat.getDrawable(context, R.drawable.shutterstock_1125475676_converted);
        }
        return ContextCompat.getDrawable(context, R.drawable.shutterstock_1125475676_converted);
    }

    public static final <T> boolean getTickStatus(Set<T> set, Set<T> set2, Set<T> set3) {
        Intrinsics.checkNotNullParameter(set, "chosenItems");
        Intrinsics.checkNotNullParameter(set2, "selectedItems");
        Intrinsics.checkNotNullParameter(set3, "previouslySelectedItems");
        if ((!set.isEmpty()) && (!Intrinsics.areEqual((Object) set3, (Object) set))) {
            return true;
        }
        if ((!(!set2.isEmpty()) || !(!Intrinsics.areEqual((Object) set3, (Object) set2))) && !(!set3.isEmpty())) {
            return false;
        }
        return true;
    }

    public static final String createExitUrl(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "$this$createExitUrl");
        try {
            return OmnitureUtil.INSTANCE.createExitUrl(liveEvent.getRegisterLink());
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "";
            }
            Log.e("ExitURLCreation", message);
            String message2 = e.getMessage();
            return message2 != null ? message2 : "Invalid event register link";
        }
    }
}
