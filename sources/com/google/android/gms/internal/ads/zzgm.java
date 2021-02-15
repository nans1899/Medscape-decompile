package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzgm implements Callable {
    private final String TAG = getClass().getSimpleName();
    private final String className;
    protected final zzcf.zza.C0009zza zzabc;
    private final String zzabk;
    protected Method zzabm;
    private final int zzabq;
    private final int zzabr;
    protected final zzex zzwg;

    public zzgm(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2) {
        this.zzwg = zzex;
        this.className = str;
        this.zzabk = str2;
        this.zzabc = zza;
        this.zzabq = i;
        this.zzabr = i2;
    }

    /* access modifiers changed from: protected */
    public abstract void zzcw() throws IllegalAccessException, InvocationTargetException;

    /* renamed from: zzcy */
    public Void call() throws Exception {
        try {
            long nanoTime = System.nanoTime();
            Method zza = this.zzwg.zza(this.className, this.zzabk);
            this.zzabm = zza;
            if (zza == null) {
                return null;
            }
            zzcw();
            zzdu zzcl = this.zzwg.zzcl();
            if (!(zzcl == null || this.zzabq == Integer.MIN_VALUE)) {
                zzcl.zza(this.zzabr, this.zzabq, (System.nanoTime() - nanoTime) / 1000);
            }
            return null;
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }
}
