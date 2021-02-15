package com.webmd.medscape.live.explorelivevents.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/BaseFiltersView;", "", "sendFilterActionCall", "", "filter", "", "selection", "isTapped", "", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BaseFiltersView.kt */
public interface BaseFiltersView {
    void sendFilterActionCall(String str, String str2, boolean z);

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    /* compiled from: BaseFiltersView.kt */
    public static final class DefaultImpls {
        public static void sendFilterActionCall(BaseFiltersView baseFiltersView, String str, String str2, boolean z) {
            Intrinsics.checkNotNullParameter(str, OmnitureConstants.OMNITURE_MODULE_FILTER);
        }

        public static /* synthetic */ void sendFilterActionCall$default(BaseFiltersView baseFiltersView, String str, String str2, boolean z, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    z = false;
                }
                baseFiltersView.sendFilterActionCall(str, str2, z);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendFilterActionCall");
        }
    }
}
