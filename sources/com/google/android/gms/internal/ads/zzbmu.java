package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.common.util.Clock;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmu implements zzp, zzbuy, zzbuz, zzqu {
    private final Clock zzbqa;
    private final zzbml zzfny;
    private final zzbms zzfnz;
    private final Set<zzbgj> zzfoa = new HashSet();
    private final zzamn<JSONObject, JSONObject> zzfob;
    private final Executor zzfoc;
    private final AtomicBoolean zzfod = new AtomicBoolean(false);
    private final zzbmw zzfoe = new zzbmw();
    private boolean zzfof = false;
    private WeakReference<?> zzfog = new WeakReference<>(this);

    public zzbmu(zzamk zzamk, zzbms zzbms, Executor executor, zzbml zzbml, Clock clock) {
        this.zzfny = zzbml;
        this.zzfob = zzamk.zzb("google.afma.activeView.handleUpdate", zzama.zzdio, zzama.zzdio);
        this.zzfnz = zzbms;
        this.zzfoc = executor;
        this.zzbqa = clock;
    }

    public final void zzum() {
    }

    public final void zzun() {
    }

    public final synchronized void zzahf() {
        if (!(this.zzfog.get() != null)) {
            zzahh();
        } else if (!this.zzfof && this.zzfod.get()) {
            try {
                this.zzfoe.timestamp = this.zzbqa.elapsedRealtime();
                JSONObject zza = this.zzfnz.zzj(this.zzfoe);
                for (zzbgj zzbmt : this.zzfoa) {
                    this.zzfoc.execute(new zzbmt(zzbmt, zza));
                }
                zzbcc.zzb(this.zzfob.zzf(zza), "ActiveViewListener.callActiveViewJs");
            } catch (Exception e) {
                zzayp.zza("Failed to call ActiveViewJS", e);
            }
        }
    }

    private final void zzahg() {
        for (zzbgj zze : this.zzfoa) {
            this.zzfny.zze(zze);
        }
        this.zzfny.zzahe();
    }

    public final synchronized void zzahh() {
        zzahg();
        this.zzfof = true;
    }

    public final synchronized void zzf(zzbgj zzbgj) {
        this.zzfoa.add(zzbgj);
        this.zzfny.zzd(zzbgj);
    }

    public final void zzo(Object obj) {
        this.zzfog = new WeakReference<>(obj);
    }

    public final synchronized void zza(zzqr zzqr) {
        this.zzfoe.zzbro = zzqr.zzbro;
        this.zzfoe.zzfoo = zzqr;
        zzahf();
    }

    public final synchronized void zzca(Context context) {
        this.zzfoe.zzfol = true;
        zzahf();
    }

    public final synchronized void zzcb(Context context) {
        this.zzfoe.zzfol = false;
        zzahf();
    }

    public final synchronized void zzcc(Context context) {
        this.zzfoe.zzfon = "u";
        zzahf();
        zzahg();
        this.zzfof = true;
    }

    public final synchronized void onPause() {
        this.zzfoe.zzfol = true;
        zzahf();
    }

    public final synchronized void onResume() {
        this.zzfoe.zzfol = false;
        zzahf();
    }

    public final synchronized void onAdImpression() {
        if (this.zzfod.compareAndSet(false, true)) {
            this.zzfny.zza(this);
            zzahf();
        }
    }
}
