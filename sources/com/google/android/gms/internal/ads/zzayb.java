package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzayb {
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public zzbbx zzbpe;
    private final zzayi zzdyh = new zzayi(zzwm.zzpy(), this.zzdzb);
    private zzqn zzdza;
    private final zzayq zzdzb = new zzayq();
    /* access modifiers changed from: private */
    public zzabi zzdzc = null;
    private Boolean zzdzd = null;
    private final AtomicInteger zzdze = new AtomicInteger(0);
    private final zzayc zzdzf = new zzayc((zzayd) null);
    private final Object zzdzg = new Object();
    private zzdzc<ArrayList<String>> zzdzh;
    /* access modifiers changed from: private */
    public Context zzvr;
    private boolean zzzh = false;

    public final zzabi zzwn() {
        zzabi zzabi;
        synchronized (this.lock) {
            zzabi = this.zzdzc;
        }
        return zzabi;
    }

    public final void zza(Boolean bool) {
        synchronized (this.lock) {
            this.zzdzd = bool;
        }
    }

    public final Boolean zzwo() {
        Boolean bool;
        synchronized (this.lock) {
            bool = this.zzdzd;
        }
        return bool;
    }

    public final void zzwp() {
        this.zzdzf.zzwp();
    }

    public final void zzd(Context context, zzbbx zzbbx) {
        synchronized (this.lock) {
            if (!this.zzzh) {
                this.zzvr = context.getApplicationContext();
                this.zzbpe = zzbbx;
                zzp.zzku().zza(this.zzdyh);
                zzabi zzabi = null;
                this.zzdzb.zza(this.zzvr, (String) null, true);
                zzasf.zzc(this.zzvr, this.zzbpe);
                this.zzdza = new zzqn(context.getApplicationContext(), this.zzbpe);
                zzp.zzla();
                if (!zzacp.zzdap.get().booleanValue()) {
                    zzayp.zzei("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                } else {
                    zzabi = new zzabi();
                }
                this.zzdzc = zzabi;
                if (zzabi != null) {
                    zzbcc.zza(new zzayd(this).zzwz(), "AppState.registerCsiReporter");
                }
                this.zzzh = true;
                zzwu();
            }
        }
        zzp.zzkr().zzs(context, zzbbx.zzbre);
    }

    public final Resources getResources() {
        if (this.zzbpe.zzeen) {
            return this.zzvr.getResources();
        }
        try {
            zzbbt.zzbu(this.zzvr).getResources();
            return null;
        } catch (zzbbv e) {
            zzayp.zzd("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public final void zza(Throwable th, String str) {
        zzasf.zzc(this.zzvr, this.zzbpe).zza(th, str);
    }

    public final void zzb(Throwable th, String str) {
        zzasf.zzc(this.zzvr, this.zzbpe).zza(th, str, zzadb.zzdch.get().floatValue());
    }

    public final void zzwq() {
        this.zzdze.incrementAndGet();
    }

    public final void zzwr() {
        this.zzdze.decrementAndGet();
    }

    public final int zzws() {
        return this.zzdze.get();
    }

    public final zzayr zzwt() {
        zzayq zzayq;
        synchronized (this.lock) {
            zzayq = this.zzdzb;
        }
        return zzayq;
    }

    public final Context getApplicationContext() {
        return this.zzvr;
    }

    public final zzdzc<ArrayList<String>> zzwu() {
        if (PlatformVersion.isAtLeastJellyBean() && this.zzvr != null) {
            if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcra)).booleanValue()) {
                synchronized (this.zzdzg) {
                    if (this.zzdzh != null) {
                        zzdzc<ArrayList<String>> zzdzc2 = this.zzdzh;
                        return zzdzc2;
                    }
                    zzdzc<ArrayList<String>> zze = zzbbz.zzeep.zze(new zzaya(this));
                    this.zzdzh = zze;
                    return zze;
                }
            }
        }
        return zzdyq.zzaf(new ArrayList());
    }

    private static ArrayList<String> zzam(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getApplicationInfo().packageName, 4096);
            if (!(packageInfo.requestedPermissions == null || packageInfo.requestedPermissionsFlags == null)) {
                for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                    if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                        arrayList.add(packageInfo.requestedPermissions[i]);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }

    public final zzayi zzwv() {
        return this.zzdyh;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ArrayList zzww() throws Exception {
        return zzam(zzatx.zzab(this.zzvr));
    }
}
