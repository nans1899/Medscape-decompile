package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdxu;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
abstract class zzdyd<V, C> extends zzdxu<V, C> {
    private List<zzdyf<V>> zzhsv;

    zzdyd(zzdwk<? extends zzdzc<? extends V>> zzdwk, boolean z) {
        super(zzdwk, true, true);
        List<zzdyf<V>> list;
        if (zzdwk.isEmpty()) {
            list = zzdwp.zzaxo();
        } else {
            list = zzdwx.zzep(zzdwk.size());
        }
        for (int i = 0; i < zzdwk.size(); i++) {
            list.add((Object) null);
        }
        this.zzhsv = list;
    }

    /* access modifiers changed from: package-private */
    public abstract C zzk(List<zzdyf<V>> list);

    /* access modifiers changed from: package-private */
    public final void zzb(int i, @NullableDecl V v) {
        List<zzdyf<V>> list = this.zzhsv;
        if (list != null) {
            list.set(i, new zzdyf(v));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaxz() {
        List<zzdyf<V>> list = this.zzhsv;
        if (list != null) {
            set(zzk(list));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzdxu.zza zza) {
        super.zza(zza);
        this.zzhsv = null;
    }
}
