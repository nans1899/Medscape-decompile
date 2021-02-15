package com.medscape.android.homescreen.home_nav_tray.helpers;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.medscape.android.homescreen.home_nav_tray.repositories.NavItemManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\u0004¨\u0006\n"}, d2 = {"Lcom/medscape/android/homescreen/home_nav_tray/helpers/HomeNavMenuConverter;", "", "()V", "convert", "", "items", "", "", "decode", "itemsString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HomeNavMenuConverter.kt */
public final class HomeNavMenuConverter {
    public final String convert(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        return CollectionsKt.joinToString$default(list, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public final List<Integer> decode(String str) {
        Intrinsics.checkNotNullParameter(str, "itemsString");
        if (!(str.length() > 0)) {
            return new NavItemManager().getDefaultOrder();
        }
        try {
            Iterable<String> split$default = StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
            for (String parseInt : split$default) {
                arrayList.add(Integer.valueOf(Integer.parseInt(parseInt)));
            }
            return (List) arrayList;
        } catch (Exception unused) {
            return new NavItemManager().getDefaultOrder();
        }
    }
}
