package com.webmd.webmdrx.manager;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/webmd/webmdrx/manager/RxSharedPreferenceManager;", "", "()V", "Companion", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: RxSharedPreferenceManager.kt */
public final class RxSharedPreferenceManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String HAS_USER_VISITED_PRICING_SCREEN = "has_user_visited_pricing_screen";
    /* access modifiers changed from: private */
    public static final String PREF_NAME = "rx_pref_name";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\f\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/webmd/webmdrx/manager/RxSharedPreferenceManager$Companion;", "", "()V", "HAS_USER_VISITED_PRICING_SCREEN", "", "PREF_NAME", "getEditor", "Landroid/content/SharedPreferences$Editor;", "context", "Landroid/content/Context;", "getSharedPreference", "Landroid/content/SharedPreferences;", "hasUserVisitedPricingScreen", "", "setHasUserVisitedRxPricingScreen", "", "hasUserVisitedRxPricingScreen", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: RxSharedPreferenceManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final SharedPreferences getSharedPreference(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(RxSharedPreferenceManager.PREF_NAME, 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
            return sharedPreferences;
        }

        private final SharedPreferences.Editor getEditor(Context context) {
            SharedPreferences.Editor edit = getSharedPreference(context).edit();
            Intrinsics.checkNotNullExpressionValue(edit, "getSharedPreference(context).edit()");
            return edit;
        }

        public final boolean hasUserVisitedPricingScreen(Context context) {
            if (context == null) {
                return false;
            }
            return getSharedPreference(context).getBoolean(RxSharedPreferenceManager.HAS_USER_VISITED_PRICING_SCREEN, false);
        }

        public final void setHasUserVisitedRxPricingScreen(boolean z, Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            getEditor(context).putBoolean(RxSharedPreferenceManager.HAS_USER_VISITED_PRICING_SCREEN, z).commit();
        }
    }
}
