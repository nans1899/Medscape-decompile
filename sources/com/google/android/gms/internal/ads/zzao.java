package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzao extends Exception {
    private long zzao;
    private final zzy zzbv;

    public zzao() {
        this.zzbv = null;
    }

    public zzao(zzy zzy) {
        this.zzbv = zzy;
    }

    public zzao(String str) {
        super(str);
        this.zzbv = null;
    }

    public zzao(Throwable th) {
        super(th);
        this.zzbv = null;
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j) {
        this.zzao = j;
    }
}
