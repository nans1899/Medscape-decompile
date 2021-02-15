package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpj implements zzayh, zzbui {
    private final zzayi zzdyh;
    private final HashSet<zzaxw> zzhgl = new HashSet<>();
    private final Context zzvr;

    public zzdpj(Context context, zzayi zzayi) {
        this.zzvr = context;
        this.zzdyh = zzayi;
    }

    public final synchronized void zzk(zzva zzva) {
        if (zzva.errorCode != 3) {
            this.zzdyh.zzb(this.zzhgl);
        }
    }

    public final synchronized void zza(HashSet<zzaxw> hashSet) {
        this.zzhgl.clear();
        this.zzhgl.addAll(hashSet);
    }

    public final Bundle zzatu() {
        return this.zzdyh.zza(this.zzvr, (zzayh) this);
    }
}
