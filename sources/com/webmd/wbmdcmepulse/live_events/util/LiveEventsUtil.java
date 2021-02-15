package com.webmd.wbmdcmepulse.live_events.util;

import com.google.gson.Gson;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import com.webmd.wbmdcmepulse.live_events.model.SpecialityModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0007J \u0010\u000e\u001a\u0004\u0018\u00010\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J.\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/util/LiveEventsUtil;", "", "()V", "correctPattern", "", "targetFormat", "Ljava/text/SimpleDateFormat;", "validPatterns", "", "checkIfDateHasCorrectFormat", "", "dateString", "checkIfPassedDate", "format", "checkIfValidFormat", "formats", "getCorrectFormatString", "dateValue", "getJsonDataForLiveEvents", "context", "Landroid/content/Context;", "getSpecialityNameById", "jsonString", "id", "", "sortLiveEventsByDate", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Lkotlin/collections/ArrayList;", "liveEventsData", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsUtil.kt */
public final class LiveEventsUtil {
    private final String correctPattern = "E, MMMM dd, yyyy";
    private final SimpleDateFormat targetFormat = new SimpleDateFormat(this.correctPattern);
    private final List<String> validPatterns = CollectionsKt.listOf("E d MMM yyyy", "E d MMMM yyyy", "E MMM d yyyy", "E MMMM d yyyy", "E dd MMM yyyy", "E dd MMMM yyyy", "E MMM dd yyyy", "E MMMM dd yyyy");

    public final String getCorrectFormatString(String str) {
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0) {
            return "";
        }
        SimpleDateFormat checkIfValidFormat = checkIfValidFormat(this.validPatterns, str);
        if (checkIfValidFormat == null) {
            return str;
        }
        String format = this.targetFormat.format(checkIfValidFormat.parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "targetFormat.format(date)");
        return format;
    }

    public final SimpleDateFormat checkIfValidFormat(List<String> list, String str) {
        Intrinsics.checkNotNullParameter(list, "formats");
        Intrinsics.checkNotNullParameter(str, "dateString");
        SimpleDateFormat simpleDateFormat = null;
        for (String simpleDateFormat2 : list) {
            try {
                SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(simpleDateFormat2);
                if (!(!Intrinsics.areEqual((Object) str, (Object) simpleDateFormat3.format(simpleDateFormat3.parse(str))))) {
                    return simpleDateFormat3;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return simpleDateFormat;
    }

    public final boolean checkIfDateHasCorrectFormat(String str) {
        Intrinsics.checkNotNullParameter(str, "dateString");
        Date date = null;
        Date date2 = null;
        try {
            if (!(!Intrinsics.areEqual((Object) str, (Object) this.targetFormat.format(this.targetFormat.parse(str))))) {
                date = this.targetFormat.parse(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            date = date2;
        }
        if (date != null) {
            return true;
        }
        return false;
    }

    public final boolean checkIfPassedDate(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "dateString");
        Intrinsics.checkNotNullParameter(str2, "format");
        return new SimpleDateFormat(str2).parse(str).before(new SimpleDateFormat(str2).parse(new SimpleDateFormat(str2).format(new Date())));
    }

    public final ArrayList<LiveEventItem> sortLiveEventsByDate(List<? extends LiveEventItem> list, String str) {
        Intrinsics.checkNotNullParameter(list, "liveEventsData");
        Intrinsics.checkNotNullParameter(str, "correctPattern");
        ArrayList<LiveEventItem> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        if (list.size() <= 1) {
            return (ArrayList) list;
        }
        for (LiveEventItem liveEventItem : list) {
            String eventDate = liveEventItem.getEventDate();
            Boolean bool = null;
            liveEventItem.setEventDate(eventDate != null ? getCorrectFormatString(eventDate) : null);
            String eventDate2 = liveEventItem.getEventDate();
            Boolean valueOf = eventDate2 != null ? Boolean.valueOf(checkIfDateHasCorrectFormat(eventDate2)) : null;
            if (valueOf != null) {
                if (valueOf.booleanValue()) {
                    String eventDate3 = liveEventItem.getEventDate();
                    if (eventDate3 != null) {
                        bool = Boolean.valueOf(checkIfPassedDate(eventDate3, str));
                    }
                    Intrinsics.checkNotNull(bool);
                    if (!bool.booleanValue()) {
                        arrayList.add(liveEventItem);
                    }
                } else {
                    arrayList2.add(liveEventItem);
                }
            }
        }
        CollectionsKt.sortWith(arrayList, new LiveEventsUtil$sortLiveEventsByDate$3(str));
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getJsonDataForLiveEvents(android.content.Context r4) {
        /*
            r3 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 0
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ Exception -> 0x0046 }
            int r1 = com.webmd.wbmdcmepulse.R.raw.live_event_keys     // Catch:{ Exception -> 0x0046 }
            java.io.InputStream r4 = r4.openRawResource(r1)     // Catch:{ Exception -> 0x0046 }
            java.lang.String r1 = "context.resources.openRa…ce(R.raw.live_event_keys)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r1)     // Catch:{ Exception -> 0x0046 }
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8     // Catch:{ Exception -> 0x0046 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0046 }
            r2.<init>(r4, r1)     // Catch:{ Exception -> 0x0046 }
            java.io.Reader r2 = (java.io.Reader) r2     // Catch:{ Exception -> 0x0046 }
            r4 = 8192(0x2000, float:1.14794E-41)
            boolean r1 = r2 instanceof java.io.BufferedReader     // Catch:{ Exception -> 0x0046 }
            if (r1 == 0) goto L_0x0027
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2     // Catch:{ Exception -> 0x0046 }
            goto L_0x002d
        L_0x0027:
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0046 }
            r1.<init>(r2, r4)     // Catch:{ Exception -> 0x0046 }
            r2 = r1
        L_0x002d:
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ Exception -> 0x0046 }
            r4 = r0
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ Exception -> 0x0046 }
            r1 = r2
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1     // Catch:{ all -> 0x003f }
            java.io.Reader r1 = (java.io.Reader) r1     // Catch:{ all -> 0x003f }
            java.lang.String r1 = kotlin.io.TextStreamsKt.readText(r1)     // Catch:{ all -> 0x003f }
            kotlin.io.CloseableKt.closeFinally(r2, r4)     // Catch:{ Exception -> 0x0046 }
            return r1
        L_0x003f:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r4)     // Catch:{ Exception -> 0x0046 }
            throw r1     // Catch:{ Exception -> 0x0046 }
        L_0x0046:
            r4 = move-exception
            r4.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.live_events.util.LiveEventsUtil.getJsonDataForLiveEvents(android.content.Context):java.lang.String");
    }

    public final String getSpecialityNameById(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "jsonString");
        String str2 = null;
        Object fromJson = new Gson().fromJson(str, SpecialityModel[].class);
        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(jsonStri…ialityModel>::class.java)");
        for (SpecialityModel specialityModel : ArraysKt.asList((T[]) (Object[]) fromJson)) {
            if (specialityModel.getId() == i) {
                return specialityModel.getLiveeventid();
            }
        }
        return str2;
    }
}
