package com.medscape.android.analytics.remoteconfig;

import kotlin.Metadata;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigParser;", "", "()V", "getClinicalAdvancesJsonObject", "Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigModel;", "clinicalAdvancesJsonObject", "Lorg/json/JSONObject;", "parseClinicalAdvancesConfig", "", "jsonString", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClinicalAdvancesConfigParser.kt */
public final class ClinicalAdvancesConfigParser {
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030 A[Catch:{ all -> 0x0046 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.medscape.android.analytics.remoteconfig.ClinicalAdvancesConfigModel> parseClinicalAdvancesConfig(java.lang.String r6) {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = r6
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0046 }
            r2 = 0
            if (r1 == 0) goto L_0x0014
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r1 = 0
            goto L_0x0015
        L_0x0014:
            r1 = 1
        L_0x0015:
            if (r1 != 0) goto L_0x004a
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ all -> 0x0046 }
            r1.<init>(r6)     // Catch:{ all -> 0x0046 }
            java.lang.String r6 = "clinical_advances"
            org.json.JSONArray r6 = r1.optJSONArray(r6)     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x004a
            int r1 = r6.length()     // Catch:{ all -> 0x0046 }
            if (r1 <= 0) goto L_0x004a
            int r1 = r6.length()     // Catch:{ all -> 0x0046 }
        L_0x002e:
            if (r2 >= r1) goto L_0x004a
            java.lang.Object r3 = r6.get(r2)     // Catch:{ all -> 0x0046 }
            boolean r4 = r3 instanceof org.json.JSONObject     // Catch:{ all -> 0x0046 }
            if (r4 == 0) goto L_0x0043
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ all -> 0x0046 }
            com.medscape.android.analytics.remoteconfig.ClinicalAdvancesConfigModel r3 = r5.getClinicalAdvancesJsonObject(r3)     // Catch:{ all -> 0x0046 }
            if (r3 == 0) goto L_0x0043
            r0.add(r3)     // Catch:{ all -> 0x0046 }
        L_0x0043:
            int r2 = r2 + 1
            goto L_0x002e
        L_0x0046:
            r6 = move-exception
            r6.printStackTrace()
        L_0x004a:
            java.util.List r0 = (java.util.List) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.analytics.remoteconfig.ClinicalAdvancesConfigParser.parseClinicalAdvancesConfig(java.lang.String):java.util.List");
    }

    public final ClinicalAdvancesConfigModel getClinicalAdvancesJsonObject(JSONObject jSONObject) {
        ClinicalAdvancesConfigModel clinicalAdvancesConfigModel = new ClinicalAdvancesConfigModel();
        if (jSONObject != null) {
            clinicalAdvancesConfigModel.setSpeciality(jSONObject.optString("speciality"));
            clinicalAdvancesConfigModel.setUrl(jSONObject.optString("url"));
        }
        return clinicalAdvancesConfigModel;
    }
}
