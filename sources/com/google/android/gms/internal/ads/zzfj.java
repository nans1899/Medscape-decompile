package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzfj extends zzgm {
    private final Activity zzaas;
    private final View zzaat;

    public zzfj(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2, View view, Activity activity) {
        super(zzex, str, str2, zza, i, 62);
        this.zzaat = view;
        this.zzaas = activity;
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        if (this.zzaat != null) {
            boolean booleanValue = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcqw)).booleanValue();
            Object[] objArr = (Object[]) this.zzabm.invoke((Object) null, new Object[]{this.zzaat, this.zzaas, Boolean.valueOf(booleanValue)});
            synchronized (this.zzabc) {
                this.zzabc.zzai(((Long) objArr[0]).longValue());
                this.zzabc.zzaj(((Long) objArr[1]).longValue());
                if (booleanValue) {
                    this.zzabc.zzab((String) objArr[2]);
                }
            }
        }
    }
}
