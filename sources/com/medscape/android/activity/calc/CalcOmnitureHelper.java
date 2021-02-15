package com.medscape.android.activity.calc;

import android.app.Activity;
import com.facebook.places.model.PlaceFields;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004¨\u0006\n"}, d2 = {"Lcom/medscape/android/activity/calc/CalcOmnitureHelper;", "", "()V", "sendOmnitureCall", "", "activity", "Landroid/app/Activity;", "moduleId", "linkId", "page", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CalcOmnitureHelper.kt */
public final class CalcOmnitureHelper {
    public static final CalcOmnitureHelper INSTANCE = new CalcOmnitureHelper();

    private CalcOmnitureHelper() {
    }

    public final String sendOmnitureCall(Activity activity, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "moduleId");
        Intrinsics.checkNotNullParameter(str2, "linkId");
        Intrinsics.checkNotNullParameter(str3, PlaceFields.PAGE);
        OmnitureManager.get().markModule(str, str2, (Map<String, Object>) null);
        if (activity == null) {
            return "";
        }
        String trackPageView = OmnitureManager.get().trackPageView(activity, Constants.OMNITURE_CHANNEL_REFERENCE, str3, (String) null, (String) null, (String) null, (Map<String, Object>) null);
        Intrinsics.checkNotNullExpressionValue(trackPageView, "OmnitureManager.get().tr…, null, null, null, null)");
        return trackPageView;
    }
}
