package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbal extends zzbh {
    private final /* synthetic */ byte[] zzecu;
    private final /* synthetic */ Map zzecv;
    private final /* synthetic */ zzbbk zzecw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbal(zzbag zzbag, int i, String str, zzal zzal, zzai zzai, byte[] bArr, Map map, zzbbk zzbbk) {
        super(i, str, zzal, zzai);
        this.zzecu = bArr;
        this.zzecv = map;
        this.zzecw = zzbbk;
    }

    public final byte[] zzg() throws zzl {
        byte[] bArr = this.zzecu;
        return bArr == null ? super.zzg() : bArr;
    }

    public final Map<String, String> getHeaders() throws zzl {
        Map<String, String> map = this.zzecv;
        return map == null ? super.getHeaders() : map;
    }

    /* access modifiers changed from: protected */
    public final void zzj(String str) {
        this.zzecw.zzez(str);
        super.zza(str);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(Object obj) {
        zza((String) obj);
    }
}
