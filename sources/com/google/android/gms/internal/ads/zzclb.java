package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.internal.ads.zzua;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzclb implements zzeoy<zzts> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzua.zza.C0021zza> zzfow;
    private final zzeph<String> zzfuc;
    private final zzeph<zzbbx> zzfup;
    private final zzeph<String> zzggd;

    private zzclb(zzeph<Context> zzeph, zzeph<String> zzeph2, zzeph<zzbbx> zzeph3, zzeph<zzua.zza.C0021zza> zzeph4, zzeph<String> zzeph5) {
        this.zzesu = zzeph;
        this.zzfuc = zzeph2;
        this.zzfup = zzeph3;
        this.zzfow = zzeph4;
        this.zzggd = zzeph5;
    }

    public static zzclb zze(zzeph<Context> zzeph, zzeph<String> zzeph2, zzeph<zzbbx> zzeph3, zzeph<zzua.zza.C0021zza> zzeph4, zzeph<String> zzeph5) {
        return new zzclb(zzeph, zzeph2, zzeph3, zzeph4, zzeph5);
    }

    public final /* synthetic */ Object get() {
        String str = this.zzfuc.get();
        zzbbx zzbbx = this.zzfup.get();
        zzua.zza.C0021zza zza = this.zzfow.get();
        String str2 = this.zzggd.get();
        zzts zzts = new zzts(new zztx(this.zzesu.get()));
        zzts.zza((zztr) new zzclc(zza, str, (zzua.zzu) ((zzejz) zzua.zzu.zzot().zzcq(zzbbx.zzeel).zzcr(zzbbx.zzeem).zzcs(zzbbx.zzeen ? 0 : 2).zzbgt()), str2));
        return (zzts) zzepe.zza(zzts, "Cannot return null from a non-@Nullable @Provides method");
    }
}
