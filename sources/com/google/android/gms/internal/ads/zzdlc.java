package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdlc<R> implements zzdqy {
    public final Executor executor;
    public final String zzbuu;
    public final zzvg zzdqr;
    public final zzvs zzgvr;
    public final zzdlx<R> zzhbq;
    public final zzdlw zzhbr;
    @Nullable
    private final zzdqj zzhbs;

    public zzdlc(zzdlx<R> zzdlx, zzdlw zzdlw, zzvg zzvg, String str, Executor executor2, zzvs zzvs, @Nullable zzdqj zzdqj) {
        this.zzhbq = zzdlx;
        this.zzhbr = zzdlw;
        this.zzdqr = zzvg;
        this.zzbuu = str;
        this.executor = executor2;
        this.zzgvr = zzvs;
        this.zzhbs = zzdqj;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    @Nullable
    public final zzdqj zzasx() {
        return this.zzhbs;
    }

    public final zzdqy zzasy() {
        return new zzdlc(this.zzhbq, this.zzhbr, this.zzdqr, this.zzbuu, this.executor, this.zzgvr, this.zzhbs);
    }
}
