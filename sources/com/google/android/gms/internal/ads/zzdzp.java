package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzp extends zzdyy<zzdzc<V>> {
    private final zzdyb<V> zzhtx;
    private final /* synthetic */ zzdzq zzhty;

    zzdzp(zzdzq zzdzq, zzdyb<V> zzdyb) {
        this.zzhty = zzdzq;
        this.zzhtx = (zzdyb) zzdwd.checkNotNull(zzdyb);
    }

    /* access modifiers changed from: package-private */
    public final boolean isDone() {
        return this.zzhty.isDone();
    }

    /* access modifiers changed from: package-private */
    public final String zzaye() {
        return this.zzhtx.toString();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, Throwable th) {
        zzdzc zzdzc = (zzdzc) obj;
        if (th == null) {
            this.zzhty.setFuture(zzdzc);
        } else {
            this.zzhty.setException(th);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzayd() throws Exception {
        return (zzdzc) zzdwd.zza(this.zzhtx.zzarv(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.zzhtx);
    }
}
