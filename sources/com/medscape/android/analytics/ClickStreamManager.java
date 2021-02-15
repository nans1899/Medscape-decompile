package com.medscape.android.analytics;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.ib.clickstream.Clickstream;
import com.ib.clickstream.ClickstreamConstants;
import com.ib.clickstream.Impression;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.wbmd.ads.model.AdContentData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J=\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0002`\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0018\u00010\fj\u0002`\u000eH\u0016¢\u0006\u0002\u0010\u000fJ1\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0002`\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0017H\u0016J\u001c\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u0019j\u0002`\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0015H\u0016¨\u0006\u001c"}, d2 = {"Lcom/medscape/android/analytics/ClickStreamManager;", "Lcom/ib/clickstream/Clickstream;", "()V", "didFailToSend", "", "event", "Lorg/json/JSONObject;", "impressions", "", "Lcom/ib/clickstream/Impression;", "Lcom/ib/clickstream/Impressions;", "errors", "Ljava/util/ArrayList;", "Lcom/ib/clickstream/ClickstreamConstants$ClickstreamError;", "Lcom/ib/clickstream/Errors;", "(Lorg/json/JSONObject;[Lcom/ib/clickstream/Impression;Ljava/util/ArrayList;)V", "didSend", "batchCount", "", "(Lorg/json/JSONObject;[Lcom/ib/clickstream/Impression;I)V", "getSiteName", "", "getSiteParamsFilterList", "", "getSiteSpecificData", "Ljava/util/HashMap;", "Lcom/ib/clickstream/SiteSpecificParameters;", "getVertical", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClickStreamManager.kt */
public final class ClickStreamManager extends Clickstream {
    public static final ClickStreamManager INSTANCE = new ClickStreamManager();

    public void didSend(JSONObject jSONObject, Impression[] impressionArr, int i) {
        Intrinsics.checkNotNullParameter(jSONObject, "event");
    }

    public String getSiteName() {
        return "medscape.com";
    }

    public String getVertical() {
        return "health professional";
    }

    private ClickStreamManager() {
    }

    public List<String> getSiteParamsFilterList() {
        return CollectionsKt.listOf("pf", "occ", "usp", "ct", "cg", "ctype", AdContentData.LEAD_SPECIALITY, AdContentData.LEAD_CONCEPT, "guid");
    }

    public HashMap<String, String> getSiteSpecificData() {
        HashMap<String, String> generateGetProfileSpecificDataMap = AdsSegvar.getInstance().generateGetProfileSpecificDataMap();
        Intrinsics.checkNotNullExpressionValue(generateGetProfileSpecificDataMap, "staticSiteData");
        generateGetProfileSpecificDataMap.put("guid", SharedPreferenceProvider.get().get("registeredIDtxt", ""));
        return AdsSegvar.getInstance().generateGetProfileSpecificDataMap();
    }

    public void didFailToSend(JSONObject jSONObject, Impression[] impressionArr, ArrayList<ClickstreamConstants.ClickstreamError> arrayList) {
        Intrinsics.checkNotNullParameter(jSONObject, "event");
        if (arrayList != null) {
            Iterator<ClickstreamConstants.ClickstreamError> it = arrayList.iterator();
            while (it.hasNext()) {
                FirebaseCrashlytics.getInstance().recordException(new Exception(it.next().getErrorMsg()));
            }
        }
    }
}
