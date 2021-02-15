package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmt {
    private final Map<String, String> zzczo = new ConcurrentHashMap();
    private final /* synthetic */ zzcmu zzghh;

    zzcmt(zzcmu zzcmu) {
        this.zzghh = zzcmu;
    }

    /* access modifiers changed from: private */
    public final zzcmt zzapb() {
        this.zzczo.putAll(this.zzghh.zzghi);
        return this;
    }

    public final zzcmt zza(zzdnw zzdnw) {
        this.zzczo.put("gqi", zzdnw.zzdtb);
        return this;
    }

    public final zzcmt zzd(zzdnv zzdnv) {
        this.zzczo.put("aai", zzdnv.zzdjo);
        return this;
    }

    public final zzcmt zzr(String str, String str2) {
        this.zzczo.put(str, str2);
        return this;
    }

    public final void zzapc() {
        this.zzghh.executor.execute(new zzcmw(this));
    }

    public final String zzapd() {
        return this.zzghh.zzghe.zzo(this.zzczo);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzape() {
        this.zzghh.zzghe.zzn(this.zzczo);
    }
}
