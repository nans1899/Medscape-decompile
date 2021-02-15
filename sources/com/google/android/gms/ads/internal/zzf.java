package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzbbg;
import com.google.android.gms.internal.ads.zzbbx;
import com.google.android.gms.internal.ads.zzbbz;
import com.google.android.gms.internal.ads.zzcw;
import com.google.android.gms.internal.ads.zzdp;
import com.google.android.gms.internal.ads.zzdw;
import com.google.android.gms.internal.ads.zzed;
import com.google.android.gms.internal.ads.zzwm;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzf implements zzdw, Runnable {
    private final List<Object[]> zzboq = new Vector();
    private final AtomicReference<zzdw> zzbor = new AtomicReference<>();
    private final AtomicReference<zzdw> zzbos = new AtomicReference<>();
    private zzbbx zzbot;
    private CountDownLatch zzbou = new CountDownLatch(1);
    private Context zzvr;
    private final int zzxo;

    public zzf(Context context, zzbbx zzbbx) {
        this.zzvr = context;
        this.zzbot = zzbbx;
        int intValue = ((Integer) zzwm.zzpx().zzd(zzabb.zzcqq)).intValue();
        if (intValue == 1) {
            this.zzxo = zzcw.zznk;
        } else if (intValue != 2) {
            this.zzxo = zzcw.zznj;
        } else {
            this.zzxo = zzcw.zznl;
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcrg)).booleanValue()) {
            zzbbz.zzeep.execute(this);
            return;
        }
        zzwm.zzpt();
        if (zzbbg.zzyn()) {
            zzbbz.zzeep.execute(this);
        } else {
            run();
        }
    }

    private final boolean zzka() {
        try {
            this.zzbou.await();
            return true;
        } catch (InterruptedException e) {
            zzayp.zzd("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    private final void zzkb() {
        zzdw zzca = zzca();
        if (!this.zzboq.isEmpty() && zzca != null) {
            for (Object[] next : this.zzboq) {
                if (next.length == 1) {
                    zzca.zza((MotionEvent) next[0]);
                } else if (next.length == 3) {
                    zzca.zza(((Integer) next[0]).intValue(), ((Integer) next[1]).intValue(), ((Integer) next[2]).intValue());
                }
            }
            this.zzboq.clear();
        }
    }

    private static Context zze(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    public final String zzb(Context context) {
        zzdw zzdw;
        if (!zzka()) {
            return "";
        }
        if (this.zzxo == zzcw.zznk || this.zzxo == zzcw.zznl) {
            zzdw = this.zzbos.get();
        } else {
            zzdw = this.zzbor.get();
        }
        if (zzdw == null) {
            return "";
        }
        zzkb();
        return zzdw.zzb(zze(context));
    }

    public final String zza(Context context, View view, Activity activity) {
        zzdw zzca = zzca();
        return zzca != null ? zzca.zza(context, view, activity) : "";
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, (Activity) null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        zzdw zzca;
        if (!zzka() || (zzca = zzca()) == null) {
            return "";
        }
        zzkb();
        return zzca.zza(zze(context), str, view, activity);
    }

    public final void zzb(View view) {
        zzdw zzca = zzca();
        if (zzca != null) {
            zzca.zzb(view);
        }
    }

    public final void zza(MotionEvent motionEvent) {
        zzdw zzca = zzca();
        if (zzca != null) {
            zzkb();
            zzca.zza(motionEvent);
            return;
        }
        this.zzboq.add(new Object[]{motionEvent});
    }

    public final void zza(int i, int i2, int i3) {
        zzdw zzca = zzca();
        if (zzca != null) {
            zzkb();
            zzca.zza(i, i2, i3);
            return;
        }
        this.zzboq.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public final void run() {
        boolean z = false;
        try {
            boolean z2 = this.zzbot.zzeen;
            if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcos)).booleanValue() && z2) {
                z = true;
            }
            if (this.zzxo != zzcw.zznk) {
                this.zzbor.set(zzed.zzb(this.zzbot.zzbre, zze(this.zzvr), z, this.zzxo));
            }
            if (this.zzxo != zzcw.zznj) {
                this.zzbos.set(zzdp.zza(this.zzbot.zzbre, zze(this.zzvr), z));
            }
        } finally {
            this.zzbou.countDown();
            this.zzvr = null;
            this.zzbot = null;
        }
    }

    private final zzdw zzca() {
        if (this.zzxo == zzcw.zznk) {
            return this.zzbos.get();
        }
        return this.zzbor.get();
    }
}
