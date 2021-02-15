package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzs {
    private final String name;
    private int weight = 1;
    private String zzaa;
    private boolean zzab;
    private boolean zzac;
    private final List<zzm> zzad = new ArrayList();
    private String zzae;

    public zzs(String str) {
        this.name = str;
    }

    public final zzs zzc(String str) {
        this.zzaa = str;
        return this;
    }

    public final zzs zzb(boolean z) {
        this.zzab = true;
        return this;
    }

    public final zzs zzc(boolean z) {
        this.zzac = true;
        return this;
    }

    public final zzs zzd(String str) {
        this.zzae = str;
        return this;
    }

    public final zzt zzc() {
        String str = this.name;
        String str2 = this.zzaa;
        boolean z = this.zzab;
        int i = this.weight;
        boolean z2 = this.zzac;
        List<zzm> list = this.zzad;
        return new zzt(str, str2, z, i, z2, (String) null, (zzm[]) list.toArray(new zzm[list.size()]), this.zzae, (zzu) null);
    }
}
