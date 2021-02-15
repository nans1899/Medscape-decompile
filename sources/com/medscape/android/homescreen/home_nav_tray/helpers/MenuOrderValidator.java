package com.medscape.android.homescreen.home_nav_tray.helpers;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¨\u0006\n"}, d2 = {"Lcom/medscape/android/homescreen/home_nav_tray/helpers/MenuOrderValidator;", "", "()V", "validate", "", "order", "", "", "menuIds", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MenuOrderValidator.kt */
public final class MenuOrderValidator {
    public final boolean validate(List<Integer> list, Set<Integer> set) {
        Intrinsics.checkNotNullParameter(list, "order");
        Intrinsics.checkNotNullParameter(set, "menuIds");
        List<Number> distinct = CollectionsKt.distinct(list);
        if (distinct.size() != set.size() || distinct.isEmpty()) {
            return false;
        }
        for (Number intValue : distinct) {
            if (!set.contains(Integer.valueOf(intValue.intValue()))) {
                return false;
            }
        }
        return true;
    }
}
