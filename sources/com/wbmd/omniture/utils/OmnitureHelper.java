package com.wbmd.omniture.utils;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.wbmd.omniture.OmnitureTracker;
import com.wbmd.omniture.PageView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\u0014\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bJ$\u0010\f\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004¨\u0006\u000f"}, d2 = {"Lcom/wbmd/omniture/utils/OmnitureHelper;", "", "()V", "getCalcPageName", "", "pageName", "getCalculatorIdForOmniture", "calcId", "sendOmniturePageView", "", "pageId", "", "sendOmniturePageViewWithActions", "mModule", "mLink", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureHelper.kt */
public final class OmnitureHelper {
    public static final OmnitureHelper INSTANCE = new OmnitureHelper();

    private OmnitureHelper() {
    }

    public final void sendOmniturePageView(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "pageId");
        new OmnitureTracker().sendPageView(new PageView.PageViewBuilder().page(list).build(), "");
    }

    public final void sendOmniturePageViewWithActions(List<String> list, String str, String str2) {
        Intrinsics.checkNotNullParameter(list, "pageId");
        Intrinsics.checkNotNullParameter(str, "mModule");
        Intrinsics.checkNotNullParameter(str2, "mLink");
        new OmnitureTracker().sendPageView(new PageView.PageViewBuilder().page(list).mmodule(str).mlink(str2).build(), "");
    }

    public final String getCalculatorIdForOmniture(String str) {
        Intrinsics.checkNotNullParameter(str, "calcId");
        String substring = str.substring(StringsKt.indexOf$default((CharSequence) str, "_", 0, false, 6, (Object) null) + 1, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public final String getCalcPageName(String str) {
        if (str != null) {
            CharSequence charSequence = str;
            if (charSequence.length() > 0) {
                String replace = new Regex("[^a-zA-z0-9']+").replace((CharSequence) StringsKt.trim(charSequence).toString(), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                if (replace != null) {
                    return StringsKt.replace$default(StringsKt.trim((CharSequence) replace).toString(), "'", "", false, 4, (Object) null);
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
        return "";
    }
}
