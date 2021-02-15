package com.medscape.android.activity.calc.helper;

import android.app.Activity;
import com.facebook.places.model.PlaceFields;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.wbmd.omniture.OmnitureState;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\bH\u0007J(\u0010\t\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007¨\u0006\u000f"}, d2 = {"Lcom/medscape/android/activity/calc/helper/CalcOmnitureHelper;", "", "()V", "saveUserProfileData", "", "userData", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "sendOmnitureCall", "activity", "Landroid/app/Activity;", "moduleId", "linkId", "page", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CalcOmnitureHelper.kt */
public final class CalcOmnitureHelper {
    public static final CalcOmnitureHelper INSTANCE = new CalcOmnitureHelper();

    private CalcOmnitureHelper() {
    }

    @JvmStatic
    public static final void saveUserProfileData(HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "userData");
        Map map = hashMap;
        map.put("wapp.site", "app-msp");
        map.put("wapp.chn", "other");
        HashMap hashMap2 = new HashMap();
        hashMap2.putAll(map);
        OmnitureState.Companion.getInstance().setUserData(hashMap2);
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
