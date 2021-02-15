package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzp;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzakr {
    /* access modifiers changed from: private */
    public final Object lock;
    /* access modifiers changed from: private */
    public int status;
    private final zzbbx zzbpe;
    private final String zzdhf;
    /* access modifiers changed from: private */
    public zzbad<zzaki> zzdhg;
    private zzbad<zzaki> zzdhh;
    /* access modifiers changed from: private */
    public zzalm zzdhi;
    private final Context zzvr;

    public zzakr(Context context, zzbbx zzbbx, String str) {
        this.lock = new Object();
        this.status = 1;
        this.zzdhf = str;
        this.zzvr = context.getApplicationContext();
        this.zzbpe = zzbbx;
        this.zzdhg = new zzalf();
        this.zzdhh = new zzalf();
    }

    public zzakr(Context context, zzbbx zzbbx, String str, zzbad<zzaki> zzbad, zzbad<zzaki> zzbad2) {
        this(context, zzbbx, str);
        this.zzdhg = zzbad;
        this.zzdhh = zzbad2;
    }

    /* access modifiers changed from: protected */
    public final zzalm zza(zzeg zzeg) {
        zzalm zzalm = new zzalm(this.zzdhh);
        zzbbz.zzeet.execute(new zzaku(this, zzeg, zzalm));
        zzalm.zza(new zzale(this, zzalm), new zzald(this, zzalm));
        return zzalm;
    }

    public final zzali zzb(zzeg zzeg) {
        synchronized (this.lock) {
            synchronized (this.lock) {
                if (this.zzdhi != null && this.status == 0) {
                    this.zzdhi.zza(new zzakt(this), zzakw.zzdho);
                }
            }
            if (this.zzdhi != null) {
                if (this.zzdhi.getStatus() != -1) {
                    if (this.status == 0) {
                        zzali zztk = this.zzdhi.zztk();
                        return zztk;
                    } else if (this.status == 1) {
                        this.status = 2;
                        zza((zzeg) null);
                        zzali zztk2 = this.zzdhi.zztk();
                        return zztk2;
                    } else if (this.status == 2) {
                        zzali zztk3 = this.zzdhi.zztk();
                        return zztk3;
                    } else {
                        zzali zztk4 = this.zzdhi.zztk();
                        return zztk4;
                    }
                }
            }
            this.status = 2;
            zzalm zza = zza((zzeg) null);
            this.zzdhi = zza;
            zzali zztk5 = zza.zztk();
            return zztk5;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzaki zzaki) {
        if (zzaki.isDestroyed()) {
            this.status = 1;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzeg zzeg, zzalm zzalm) {
        zzaki zzaki;
        try {
            Context context = this.zzvr;
            zzbbx zzbbx = this.zzbpe;
            if (zzadh.zzdcu.get().booleanValue()) {
                zzaki = new zzaju(context, zzbbx);
            } else {
                zzaki = new zzakk(context, zzbbx, zzeg, (zzb) null);
            }
            zzaki.zza(new zzakv(this, zzalm, zzaki));
            zzaki.zza("/jsLoaded", new zzala(this, zzalm, zzaki));
            zzbaz zzbaz = new zzbaz();
            zzakz zzakz = new zzakz(this, zzeg, zzaki, zzbaz);
            zzbaz.set(zzakz);
            zzaki.zza("/requestReload", zzakz);
            if (this.zzdhf.endsWith(".js")) {
                zzaki.zzcz(this.zzdhf);
            } else if (this.zzdhf.startsWith("<html>")) {
                zzaki.zzda(this.zzdhf);
            } else {
                zzaki.zzdb(this.zzdhf);
            }
            zzayu.zzeba.postDelayed(new zzalc(this, zzalm, zzaki), (long) zzalg.zzdhw);
        } catch (Throwable th) {
            zzayp.zzc("Error creating webview.", th);
            zzp.zzkv().zza(th, "SdkJavascriptFactory.loadJavascriptEngine");
            zzalm.reject();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(com.google.android.gms.internal.ads.zzalm r4, com.google.android.gms.internal.ads.zzaki r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            int r1 = r4.getStatus()     // Catch:{ all -> 0x002a }
            r2 = -1
            if (r1 == r2) goto L_0x0028
            int r1 = r4.getStatus()     // Catch:{ all -> 0x002a }
            r2 = 1
            if (r1 != r2) goto L_0x0012
            goto L_0x0028
        L_0x0012:
            r4.reject()     // Catch:{ all -> 0x002a }
            com.google.android.gms.internal.ads.zzdzb r4 = com.google.android.gms.internal.ads.zzbbz.zzeet     // Catch:{ all -> 0x002a }
            r5.getClass()     // Catch:{ all -> 0x002a }
            java.lang.Runnable r5 = com.google.android.gms.internal.ads.zzakx.zzb(r5)     // Catch:{ all -> 0x002a }
            r4.execute(r5)     // Catch:{ all -> 0x002a }
            java.lang.String r4 = "Could not receive loaded message in a timely manner. Rejecting."
            com.google.android.gms.internal.ads.zzayp.zzei(r4)     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x002a:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakr.zza(com.google.android.gms.internal.ads.zzalm, com.google.android.gms.internal.ads.zzaki):void");
    }
}
