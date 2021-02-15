package com.webmd.medscape.live.explorelivevents.util;

import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.tapstream.sdk.http.RequestBuilders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.bytebuddy.description.type.TypeDescription;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\\\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f¨\u0006\u0011"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/util/OmnitureUtil;", "", "()V", "createExitUrl", "", "registerLink", "getUserSegStringFromFilterSelections", "screenType", "", "specialtyFilters", "", "locationsFilters", "datesSelection", "Lkotlin/Pair;", "areAllLocationsSelected", "", "areAllSpecialtiesSelected", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureUtil.kt */
public final class OmnitureUtil {
    public static final OmnitureUtil INSTANCE = new OmnitureUtil();

    private OmnitureUtil() {
    }

    public static /* synthetic */ String getUserSegStringFromFilterSelections$default(OmnitureUtil omnitureUtil, int i, List list, List list2, Pair pair, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list3 = list;
        if ((i2 & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        List list4 = list2;
        if ((i2 & 8) != 0) {
            pair = new Pair(null, null);
        }
        return omnitureUtil.getUserSegStringFromFilterSelections(i, list3, list4, pair, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2);
    }

    public final String getUserSegStringFromFilterSelections(int i, List<String> list, List<String> list2, Pair<String, String> pair, boolean z, boolean z2) {
        int i2 = i;
        List<String> list3 = list;
        List<String> list4 = list2;
        Intrinsics.checkNotNullParameter(list3, "specialtyFilters");
        Intrinsics.checkNotNullParameter(list4, "locationsFilters");
        Intrinsics.checkNotNullParameter(pair, "datesSelection");
        StringBuilder sb = new StringBuilder();
        sb.append(i2 != 0 ? i2 != 1 ? "" : "cntr_rslts" : "cntr_brws");
        sb.append("_");
        Collection collection = list3;
        if ((!collection.isEmpty()) && z2) {
            sb.append("s-");
            sb.append("als");
            sb.append(":");
        } else if (!collection.isEmpty()) {
            Iterable<String> iterable = list3;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (String str : iterable) {
                if (str != null) {
                    String substring = str.substring(0, 3);
                    Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    arrayList.add(substring);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            }
            sb.append("s-");
            sb.append(CollectionsKt.joinToString$default((List) arrayList, ";", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            sb.append(":");
        } else {
            sb.append("s-0");
            sb.append(":");
        }
        Collection collection2 = list4;
        if ((!collection2.isEmpty()) && z) {
            sb.append("l-");
            sb.append(Constants.CONSULT_ALL);
            sb.append(":");
        } else if (!collection2.isEmpty()) {
            Iterable<String> iterable2 = list4;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (String str2 : iterable2) {
                if (str2 != null) {
                    String substring2 = str2.substring(0, 3);
                    Intrinsics.checkNotNullExpressionValue(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    arrayList2.add(substring2);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            }
            sb.append("l-" + CollectionsKt.joinToString$default((List) arrayList2, ";", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            sb.append(":");
        } else {
            sb.append("l-0");
            sb.append(":");
        }
        if (pair.getFirst() != null && pair.getSecond() != null) {
            sb.append("d-");
            sb.append(pair.getFirst());
            sb.append(";");
            sb.append(pair.getSecond());
        } else if (pair.getFirst() != null) {
            sb.append("d-");
            sb.append(pair.getFirst());
            sb.append(";");
            sb.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        } else {
            sb.append("d-0;0");
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }

    public final String createExitUrl(String str) {
        String str2;
        String str3;
        String str4 = str;
        Intrinsics.checkNotNullParameter(str4, "registerLink");
        CharSequence charSequence = str4;
        if (StringsKt.contains$default(charSequence, (CharSequence) "www.", false, 2, (Object) null)) {
            str2 = "//www.";
        } else if (StringsKt.startsWith$default(str4, RequestBuilders.DEFAULT_SCHEME, false, 2, (Object) null)) {
            str2 = "https://";
        } else if (StringsKt.startsWith$default(str4, "http", false, 2, (Object) null)) {
            str2 = "http://";
        } else {
            throw new Exception("Invalid event url!!");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("exit::");
        if (StringsKt.contains$default(charSequence, (CharSequence) TypeDescription.Generic.OfWildcardType.SYMBOL, false, 2, (Object) null)) {
            str3 = (String) StringsKt.split$default((CharSequence) (String) StringsKt.split$default(charSequence, new String[]{TypeDescription.Generic.OfWildcardType.SYMBOL}, false, 0, 6, (Object) null).get(0), new String[]{str2}, false, 0, 6, (Object) null).get(1);
        } else {
            str3 = (String) StringsKt.split$default(charSequence, new String[]{str2}, false, 0, 6, (Object) null).get(1);
        }
        sb.append(str3);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "stringBuilder.toString()");
        return sb2;
    }
}
