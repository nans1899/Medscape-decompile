package com.medscape.android.analytics.remoteconfig.reference;

import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f`\u00102\u0006\u0010\u0011\u001a\u00020\u000eJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¨\u0006\u0016"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/reference/ParseFeatureConfig;", "", "()V", "parseConditionsArray", "Ljava/util/ArrayList;", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigConditionModel;", "Lkotlin/collections/ArrayList;", "jsonArray", "Lorg/json/JSONArray;", "parseCriteriaArray", "", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigCriteriaModel;", "parseFeatureConfig", "Ljava/util/HashMap;", "", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigModel;", "Lkotlin/collections/HashMap;", "jsonString", "parseIndicator", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigIndicatorModel;", "jsonObject", "Lorg/json/JSONObject;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ParseFeatureConfig.kt */
public final class ParseFeatureConfig {
    public final HashMap<String, FeatureConfigModel> parseFeatureConfig(String str) {
        Intrinsics.checkNotNullParameter(str, "jsonString");
        HashMap<String, FeatureConfigModel> hashMap = new HashMap<>();
        try {
            if (!StringsKt.isBlank(str)) {
                JSONArray jSONArray = new JSONArray(str);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    FeatureConfigModel featureConfigModel = new FeatureConfigModel((String) null, (FeatureConfigIndicatorModel) null, (List) null, 7, (DefaultConstructorMarker) null);
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject.has("newIndicator")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("newIndicator");
                        Intrinsics.checkNotNullExpressionValue(jSONObject2, "jsonObject.getJSONObject(\"newIndicator\")");
                        featureConfigModel.setIndicator(parseIndicator(jSONObject2));
                    }
                    String optString = jSONObject.optString("type");
                    Intrinsics.checkNotNullExpressionValue(optString, "jsonObject.optString(\"type\")");
                    featureConfigModel.setType(optString);
                    featureConfigModel.setCriteria(parseCriteriaArray(jSONObject.optJSONArray("criteria")));
                    hashMap.put(featureConfigModel.getType(), featureConfigModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    private final FeatureConfigIndicatorModel parseIndicator(JSONObject jSONObject) {
        FeatureConfigIndicatorModel featureConfigIndicatorModel = new FeatureConfigIndicatorModel(0, 0, 3, (DefaultConstructorMarker) null);
        featureConfigIndicatorModel.setTtl(jSONObject.optInt(Constants.FirelogAnalytics.PARAM_TTL));
        featureConfigIndicatorModel.setEndDate(jSONObject.optLong("endDate") * ((long) 1000));
        return featureConfigIndicatorModel;
    }

    private final List<FeatureConfigCriteriaModel> parseCriteriaArray(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                FeatureConfigCriteriaModel featureConfigCriteriaModel = new FeatureConfigCriteriaModel((String) null, (ArrayList) null, 3, (DefaultConstructorMarker) null);
                String optString = jSONObject.optString("target");
                Intrinsics.checkNotNullExpressionValue(optString, "jsonObject.optString(\"target\")");
                featureConfigCriteriaModel.setTarget(optString);
                featureConfigCriteriaModel.setConditions(parseConditionsArray(jSONObject.getJSONArray("conditions")));
                arrayList.add(featureConfigCriteriaModel);
            }
        }
        return arrayList;
    }

    private final ArrayList<FeatureConfigConditionModel> parseConditionsArray(JSONArray jSONArray) {
        ArrayList<FeatureConfigConditionModel> arrayList = new ArrayList<>();
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                FeatureConfigConditionModel featureConfigConditionModel = new FeatureConfigConditionModel((String) null, (ArrayList) null, false, 7, (DefaultConstructorMarker) null);
                featureConfigConditionModel.setInverse(jSONObject.optBoolean("inverse", false));
                String optString = jSONObject.optString("key");
                Intrinsics.checkNotNullExpressionValue(optString, "jsonObject.optString(\"key\")");
                featureConfigConditionModel.setKey(optString);
                JSONArray jSONArray2 = jSONObject.getJSONArray("value");
                int length2 = jSONArray2.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    featureConfigConditionModel.getValue().add(jSONArray2.get(i2).toString());
                }
                arrayList.add(featureConfigConditionModel);
            }
        }
        return arrayList;
    }
}
