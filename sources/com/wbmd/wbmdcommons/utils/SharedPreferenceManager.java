package com.wbmd.wbmdcommons.utils;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\rJ\u0016\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\rJ\u0016\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/wbmd/wbmdcommons/utils/SharedPreferenceManager;", "", "()V", "IS_MED_TEAM_TUTORIAL_SHOWN", "", "IS_USER_CLOSED_RX", "IS_USER_VISITED_PRICING_SCREEN", "PREF_NAME", "PRIVATE_MODE", "", "RX_SEARCH_TEXT_OVERLAY", "TEXT_OVERLAY", "getIsMedTeamTutorialShown", "", "context", "Landroid/content/Context;", "getSharedPreferences", "Landroid/content/SharedPreferences;", "isRxSearchInstructionOverlayShown", "isSavedInstructionOverlayShown", "isUserClosedTheRxCard", "isUserVisitedTheRxPricingScreen", "setIsMedTeamTutorialShown", "", "bool", "setIsRxSearchInstructionOverlayShown", "setIsSavedInstructionOverlayShown", "setIsUserClosedTheRxCard", "setIsUserVisitedTheRxPricingScreen", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SharedPreferenceManager.kt */
public final class SharedPreferenceManager {
    public static final SharedPreferenceManager INSTANCE = new SharedPreferenceManager();
    private static final String IS_MED_TEAM_TUTORIAL_SHOWN = "is_med_team_tutorial_shown";
    private static final String IS_USER_CLOSED_RX = "is_user_closed_rx";
    public static final String IS_USER_VISITED_PRICING_SCREEN = "is_user_visited_pricing_screen";
    private static final String PREF_NAME = "wbmd_commons";
    private static final int PRIVATE_MODE = 0;
    private static final String RX_SEARCH_TEXT_OVERLAY = "rx_search_text_overlay";
    private static final String TEXT_OVERLAY = "text_overlay";

    private SharedPreferenceManager() {
    }

    public final SharedPreferences getSharedPreferences(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…(PREF_NAME, PRIVATE_MODE)");
        return sharedPreferences;
    }

    public final boolean isSavedInstructionOverlayShown(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getSharedPreferences(context).getBoolean(TEXT_OVERLAY, false);
    }

    public final void setIsSavedInstructionOverlayShown(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        getSharedPreferences(context).edit().putBoolean(TEXT_OVERLAY, z).apply();
    }

    public final boolean isRxSearchInstructionOverlayShown(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getSharedPreferences(context).getBoolean(RX_SEARCH_TEXT_OVERLAY, false);
    }

    public final void setIsRxSearchInstructionOverlayShown(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        getSharedPreferences(context).edit().putBoolean(RX_SEARCH_TEXT_OVERLAY, z).apply();
    }

    public final void setIsMedTeamTutorialShown(boolean z, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        getSharedPreferences(context).edit().putBoolean(IS_MED_TEAM_TUTORIAL_SHOWN, z).commit();
    }

    public final boolean getIsMedTeamTutorialShown(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getSharedPreferences(context).getBoolean(IS_MED_TEAM_TUTORIAL_SHOWN, false);
    }

    public final void setIsUserClosedTheRxCard(boolean z, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        getSharedPreferences(context).edit().putBoolean(IS_USER_CLOSED_RX, z).commit();
    }

    public final boolean isUserClosedTheRxCard(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getSharedPreferences(context).getBoolean(IS_USER_CLOSED_RX, false);
    }

    public final void setIsUserVisitedTheRxPricingScreen(boolean z, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        getSharedPreferences(context).edit().putBoolean(IS_USER_VISITED_PRICING_SCREEN, z).commit();
    }

    public final boolean isUserVisitedTheRxPricingScreen(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getSharedPreferences(context).getBoolean(IS_USER_VISITED_PRICING_SCREEN, false);
    }
}
