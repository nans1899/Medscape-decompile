package com.google.android.gms.internal.ads;

import android.util.DisplayMetrics;
import android.view.View;
import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzgh extends zzgm {
    private final View zzaat;

    public zzgh(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2, View view) {
        super(zzex, str, str2, zza, i, 57);
        this.zzaat = view;
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        if (this.zzaat != null) {
            Boolean bool = (Boolean) zzwm.zzpx().zzd(zzabb.zzcrl);
            DisplayMetrics displayMetrics = this.zzwg.getContext().getResources().getDisplayMetrics();
            zzff zzff = new zzff((String) this.zzabm.invoke((Object) null, new Object[]{this.zzaat, displayMetrics, bool}));
            zzcf.zza.zzf.C0011zza zzax = zzcf.zza.zzf.zzax();
            zzax.zzcy(zzff.zzaab.longValue()).zzcz(zzff.zzaac.longValue()).zzda(zzff.zzaad.longValue());
            if (bool.booleanValue()) {
                zzax.zzdb(zzff.zzaae.longValue());
            }
            this.zzabc.zza((zzcf.zza.zzf) ((zzejz) zzax.zzbgt()));
        }
    }
}
