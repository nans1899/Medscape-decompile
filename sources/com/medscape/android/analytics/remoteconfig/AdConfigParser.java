package com.medscape.android.analytics.remoteconfig;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/AdConfigParser;", "", "()V", "parseAdRemoteConfig", "Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;", "jsonString", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdConfigParser.kt */
public final class AdConfigParser {
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0018 A[Catch:{ all -> 0x010d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel parseAdRemoteConfig(java.lang.String r12) {
        /*
            r11 = this;
            com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel r0 = new com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel
            r0.<init>()
            r1 = r12
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x010d }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0015
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)     // Catch:{ all -> 0x010d }
            if (r1 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r1 = 0
            goto L_0x0016
        L_0x0015:
            r1 = 1
        L_0x0016:
            if (r1 != 0) goto L_0x0111
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ all -> 0x010d }
            r1.<init>(r12)     // Catch:{ all -> 0x010d }
            java.lang.String r12 = "positionStart"
            int r12 = r1.optInt(r12, r3)     // Catch:{ all -> 0x010d }
            r0.setPositionStart(r12)     // Catch:{ all -> 0x010d }
            java.lang.String r12 = "positionEvery"
            int r12 = r1.optInt(r12, r3)     // Catch:{ all -> 0x010d }
            r0.setPositionEvery(r12)     // Catch:{ all -> 0x010d }
            java.lang.String r12 = "preloadCount"
            int r12 = r1.optInt(r12, r3)     // Catch:{ all -> 0x010d }
            r0.setPreloadCount(r12)     // Catch:{ all -> 0x010d }
            java.lang.String r12 = "adUnit"
            java.lang.String r12 = r1.optString(r12)     // Catch:{ all -> 0x010d }
            r0.setAdUnit(r12)     // Catch:{ all -> 0x010d }
            java.lang.String r12 = "customTargeting"
            org.json.JSONObject r12 = r1.getJSONObject(r12)     // Catch:{ all -> 0x010d }
            java.util.Iterator r4 = r12.keys()     // Catch:{ all -> 0x010d }
            java.lang.String r5 = "customObject.keys()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ all -> 0x010d }
        L_0x0050:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x010d }
            if (r5 == 0) goto L_0x0079
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x010d }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x010d }
            java.util.HashMap r6 = r0.getCustomTargeting()     // Catch:{ all -> 0x010d }
            java.util.Map r6 = (java.util.Map) r6     // Catch:{ all -> 0x010d }
            if (r5 == 0) goto L_0x0071
            java.lang.String r7 = r12.optString(r5)     // Catch:{ all -> 0x010d }
            java.lang.String r8 = "customObject.optString(key)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)     // Catch:{ all -> 0x010d }
            r6.put(r5, r7)     // Catch:{ all -> 0x010d }
            goto L_0x0050
        L_0x0071:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException     // Catch:{ all -> 0x010d }
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            r12.<init>(r1)     // Catch:{ all -> 0x010d }
            throw r12     // Catch:{ all -> 0x010d }
        L_0x0079:
            java.lang.String r12 = "validAdSizes"
            org.json.JSONArray r12 = r1.optJSONArray(r12)     // Catch:{ all -> 0x010d }
            if (r12 == 0) goto L_0x00e9
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x010d }
            r4.<init>()     // Catch:{ all -> 0x010d }
            int r5 = r12.length()     // Catch:{ all -> 0x010d }
            int r5 = r5 - r2
            if (r5 < 0) goto L_0x00d1
            r6 = 0
        L_0x008e:
            java.lang.Object r7 = r12.get(r6)     // Catch:{ all -> 0x010d }
            if (r7 == 0) goto L_0x00c9
            org.json.JSONArray r7 = (org.json.JSONArray) r7     // Catch:{ all -> 0x010d }
            com.google.android.gms.ads.AdSize r8 = new com.google.android.gms.ads.AdSize     // Catch:{ all -> 0x010d }
            java.lang.Object r9 = r7.get(r3)     // Catch:{ all -> 0x010d }
            java.lang.String r10 = "null cannot be cast to non-null type kotlin.Int"
            if (r9 == 0) goto L_0x00c3
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ all -> 0x010d }
            int r9 = r9.intValue()     // Catch:{ all -> 0x010d }
            java.lang.Object r7 = r7.get(r2)     // Catch:{ all -> 0x010d }
            if (r7 == 0) goto L_0x00bd
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x010d }
            int r7 = r7.intValue()     // Catch:{ all -> 0x010d }
            r8.<init>(r9, r7)     // Catch:{ all -> 0x010d }
            r4.add(r8)     // Catch:{ all -> 0x010d }
            if (r6 == r5) goto L_0x00d1
            int r6 = r6 + 1
            goto L_0x008e
        L_0x00bd:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException     // Catch:{ all -> 0x010d }
            r12.<init>(r10)     // Catch:{ all -> 0x010d }
            throw r12     // Catch:{ all -> 0x010d }
        L_0x00c3:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException     // Catch:{ all -> 0x010d }
            r12.<init>(r10)     // Catch:{ all -> 0x010d }
            throw r12     // Catch:{ all -> 0x010d }
        L_0x00c9:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException     // Catch:{ all -> 0x010d }
            java.lang.String r1 = "null cannot be cast to non-null type org.json.JSONArray"
            r12.<init>(r1)     // Catch:{ all -> 0x010d }
            throw r12     // Catch:{ all -> 0x010d }
        L_0x00d1:
            java.util.Collection r4 = (java.util.Collection) r4     // Catch:{ all -> 0x010d }
            com.google.android.gms.ads.AdSize[] r12 = new com.google.android.gms.ads.AdSize[r3]     // Catch:{ all -> 0x010d }
            java.lang.Object[] r12 = r4.toArray(r12)     // Catch:{ all -> 0x010d }
            if (r12 == 0) goto L_0x00e1
            com.google.android.gms.ads.AdSize[] r12 = (com.google.android.gms.ads.AdSize[]) r12     // Catch:{ all -> 0x010d }
            r0.setAdSizes(r12)     // Catch:{ all -> 0x010d }
            goto L_0x00e9
        L_0x00e1:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException     // Catch:{ all -> 0x010d }
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Array<T>"
            r12.<init>(r1)     // Catch:{ all -> 0x010d }
            throw r12     // Catch:{ all -> 0x010d }
        L_0x00e9:
            java.lang.String r12 = "specialAds"
            org.json.JSONArray r12 = r1.optJSONArray(r12)     // Catch:{ all -> 0x010d }
            if (r12 == 0) goto L_0x0111
            com.medscape.android.analytics.remoteconfig.AdConfigParser$parseAdRemoteConfig$myType$1 r1 = new com.medscape.android.analytics.remoteconfig.AdConfigParser$parseAdRemoteConfig$myType$1     // Catch:{ all -> 0x010d }
            r1.<init>()     // Catch:{ all -> 0x010d }
            java.lang.reflect.Type r1 = r1.getType()     // Catch:{ all -> 0x010d }
            com.google.gson.Gson r2 = new com.google.gson.Gson     // Catch:{ all -> 0x010d }
            r2.<init>()     // Catch:{ all -> 0x010d }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x010d }
            java.lang.Object r12 = r2.fromJson((java.lang.String) r12, (java.lang.reflect.Type) r1)     // Catch:{ all -> 0x010d }
            java.util.List r12 = (java.util.List) r12     // Catch:{ all -> 0x010d }
            r0.setSpecialAds(r12)     // Catch:{ all -> 0x010d }
            goto L_0x0111
        L_0x010d:
            r12 = move-exception
            r12.printStackTrace()
        L_0x0111:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.analytics.remoteconfig.AdConfigParser.parseAdRemoteConfig(java.lang.String):com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel");
    }
}
