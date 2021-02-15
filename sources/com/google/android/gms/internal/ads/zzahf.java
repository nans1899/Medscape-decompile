package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzahf implements zzahq<zzbgj> {
    zzahf() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        JSONObject zzsi;
        zzbgj zzbgj = (zzbgj) obj;
        zzads zzach = zzbgj.zzach();
        if (zzach == null || (zzsi = zzach.zzsi()) == null) {
            zzbgj.zza("nativeAdViewSignalsReady", new JSONObject());
        } else {
            zzbgj.zza("nativeAdViewSignalsReady", zzsi);
        }
    }
}
