package com.medscape.android.ads.sharethrough;

import android.content.Context;
import com.medscape.android.Constants;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.provider.SharedPreferenceProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\f\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lcom/medscape/android/ads/sharethrough/FallbackManager;", "", "()V", "getFallbackInfo", "Lcom/medscape/android/ads/sharethrough/SharethroughFallback;", "context", "Landroid/content/Context;", "position", "", "getPreviousPositionShown", "isConsultAvailable", "", "saveShownPosition", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FallbackManager.kt */
public final class FallbackManager {
    public final SharethroughFallback getFallbackInfo(Context context) {
        int previousPositionShown = getPreviousPositionShown(context) + 1;
        if (previousPositionShown >= 5) {
            previousPositionShown = 0;
        }
        if (previousPositionShown == 0 && !isConsultAvailable(context)) {
            previousPositionShown++;
        }
        saveShownPosition(context, previousPositionShown);
        return getFallbackInfo(previousPositionShown);
    }

    public final SharethroughFallback getFallbackInfo(int i) {
        String str = "medscape://host/?url=interactions://home";
        String str2 = "Identify adverse drug combinations and learn about the severity of warnings.";
        String str3 = "Medscape Drug Interaction Checker";
        if (i == 0) {
            str3 = "Medscape Consult";
            str2 = "Ask, share, and discuss real clinical cases with a global community of physicians.";
            str = "medscape://host/home?url=consult://timeline";
        } else if (i == 1) {
            str3 = "Medscape Calculators";
            str2 = "Browse or search over 100 medical calculators for quick clinical decision support.";
            str = "medscape://host/?url=calc://home";
        } else if (i != 2) {
            if (i == 3) {
                str3 = "Medscape Formulary";
                str2 = "Choose cost-effective therapies, checking local providers' coverage and co-payments.";
                str = "medscape://host/?url=formulary://home";
            } else if (i == 4) {
                str3 = "Medscape Health Directory";
                str2 = "Need to make a referral? Search by specialty, condition, procedure, and location.";
                str = "medscape://host/?url=healthdirectory://home";
            }
        }
        return new SharethroughFallback(str3, str2, str);
    }

    public final int getPreviousPositionShown(Context context) {
        if (context != null) {
            return SharedPreferenceProvider.get().get(Constants.PREF_FALLBACK_AD_LAST_INDEX, -1);
        }
        return -1;
    }

    public final boolean isConsultAvailable(Context context) {
        if (context == null) {
            return false;
        }
        CapabilitiesManager instance = CapabilitiesManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "CapabilitiesManager.getInstance(context)");
        return instance.isConsultFeatureAvailable();
    }

    public final void saveShownPosition(Context context, int i) {
        if (context != null) {
            SharedPreferenceProvider.get().save(Constants.PREF_FALLBACK_AD_LAST_INDEX, i);
        }
    }
}
