package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.internal.measurement.zzdh;
import com.google.android.gms.internal.measurement.zzmj;
import com.google.android.gms.internal.measurement.zzmu;
import com.google.android.gms.internal.measurement.zzny;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.coroutines.DebugKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public class zzfv implements zzgq {
    private static volatile zzfv zzb;
    final long zza;
    private Boolean zzaa;
    private long zzab;
    private volatile Boolean zzac;
    private Boolean zzad;
    private Boolean zzae;
    private volatile boolean zzaf;
    private int zzag;
    private AtomicInteger zzah = new AtomicInteger(0);
    private final Context zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final boolean zzg;
    private final zzx zzh;
    private final zzy zzi;
    private final zzfd zzj;
    private final zzer zzk;
    private final zzfo zzl;
    private final zzju zzm;
    private final zzkw zzn;
    private final zzep zzo;
    private final Clock zzp;
    private final zzij zzq;
    private final zzgy zzr;
    private final zza zzs;
    private final zzia zzt;
    private zzen zzu;
    private zzio zzv;
    private zzal zzw;
    private zzek zzx;
    private zzfi zzy;
    private boolean zzz = false;

    private zzfv(zzgz zzgz) {
        long j;
        boolean z = false;
        Preconditions.checkNotNull(zzgz);
        zzx zzx2 = new zzx(zzgz.zza);
        this.zzh = zzx2;
        zzeh.zza = zzx2;
        this.zzc = zzgz.zza;
        this.zzd = zzgz.zzb;
        this.zze = zzgz.zzc;
        this.zzf = zzgz.zzd;
        this.zzg = zzgz.zzh;
        this.zzac = zzgz.zze;
        this.zzaf = true;
        zzae zzae2 = zzgz.zzg;
        if (!(zzae2 == null || zzae2.zzg == null)) {
            Object obj = zzae2.zzg.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzad = (Boolean) obj;
            }
            Object obj2 = zzae2.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzae = (Boolean) obj2;
            }
        }
        zzdh.zza(this.zzc);
        this.zzp = DefaultClock.getInstance();
        if (zzgz.zzi != null) {
            j = zzgz.zzi.longValue();
        } else {
            j = this.zzp.currentTimeMillis();
        }
        this.zza = j;
        this.zzi = new zzy(this);
        zzfd zzfd = new zzfd(this);
        zzfd.zzab();
        this.zzj = zzfd;
        zzer zzer = new zzer(this);
        zzer.zzab();
        this.zzk = zzer;
        zzkw zzkw = new zzkw(this);
        zzkw.zzab();
        this.zzn = zzkw;
        zzep zzep = new zzep(this);
        zzep.zzab();
        this.zzo = zzep;
        this.zzs = new zza(this);
        zzij zzij = new zzij(this);
        zzij.zzw();
        this.zzq = zzij;
        zzgy zzgy = new zzgy(this);
        zzgy.zzw();
        this.zzr = zzgy;
        zzju zzju = new zzju(this);
        zzju.zzw();
        this.zzm = zzju;
        zzia zzia = new zzia(this);
        zzia.zzab();
        this.zzt = zzia;
        zzfo zzfo = new zzfo(this);
        zzfo.zzab();
        this.zzl = zzfo;
        if (!(zzgz.zzg == null || zzgz.zzg.zzb == 0)) {
            z = true;
        }
        boolean z2 = !z;
        if (this.zzc.getApplicationContext() instanceof Application) {
            zzgy zzg2 = zzg();
            if (zzg2.zzm().getApplicationContext() instanceof Application) {
                Application application = (Application) zzg2.zzm().getApplicationContext();
                if (zzg2.zza == null) {
                    zzg2.zza = new zzhz(zzg2, (zzhd) null);
                }
                if (z2) {
                    application.unregisterActivityLifecycleCallbacks(zzg2.zza);
                    application.registerActivityLifecycleCallbacks(zzg2.zza);
                    zzg2.zzq().zzw().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzq().zzh().zza("Application context is not an Application");
        }
        this.zzl.zza((Runnable) new zzfx(this, zzgz));
    }

    /* access modifiers changed from: private */
    public final void zza(zzgz zzgz) {
        zzet zzet;
        String str;
        zzp().zzc();
        zzal zzal = new zzal(this);
        zzal.zzab();
        this.zzw = zzal;
        zzek zzek = new zzek(this, zzgz.zzf);
        zzek.zzw();
        this.zzx = zzek;
        zzen zzen = new zzen(this);
        zzen.zzw();
        this.zzu = zzen;
        zzio zzio = new zzio(this);
        zzio.zzw();
        this.zzv = zzio;
        this.zzn.zzac();
        this.zzj.zzac();
        this.zzy = new zzfi(this);
        this.zzx.zzx();
        zzq().zzu().zza("App measurement initialized, version", 32053L);
        zzq().zzu().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzaa2 = zzek.zzaa();
        if (TextUtils.isEmpty(this.zzd)) {
            if (zzh().zze(zzaa2)) {
                zzet = zzq().zzu();
                str = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzet zzu2 = zzq().zzu();
                String valueOf = String.valueOf(zzaa2);
                zzet zzet2 = zzu2;
                str = valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
                zzet = zzet2;
            }
            zzet.zza(str);
        }
        zzq().zzv().zza("Debug-level message logging enabled");
        if (this.zzag != this.zzah.get()) {
            zzq().zze().zza("Not all components initialized", Integer.valueOf(this.zzag), Integer.valueOf(this.zzah.get()));
        }
        this.zzz = true;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzae zzae2) {
        zzp().zzc();
        if (zzmj.zzb() && this.zzi.zza(zzat.zzcg)) {
            zzad zzw2 = zzb().zzw();
            if (!(zzae2 == null || zzae2.zzg == null || !zzb().zza(40))) {
                zzad zzb2 = zzad.zzb(zzae2.zzg);
                if (!zzb2.equals(zzad.zza)) {
                    zzg().zza(zzb2, 40, this.zza);
                    zzw2 = zzb2;
                }
            }
            zzg().zza(zzw2);
        }
        if (zzb().zzc.zza() == 0) {
            zzb().zzc.zza(this.zzp.currentTimeMillis());
        }
        if (Long.valueOf(zzb().zzh.zza()).longValue() == 0) {
            zzq().zzw().zza("Persisting first open", Long.valueOf(this.zza));
            zzb().zzh.zza(this.zza);
        }
        if (this.zzi.zza(zzat.zzcc)) {
            zzg().zzb.zzb();
        }
        if (zzaf()) {
            if (!TextUtils.isEmpty(zzx().zzab()) || !TextUtils.isEmpty(zzx().zzac())) {
                zzh();
                if (zzkw.zza(zzx().zzab(), zzb().zzg(), zzx().zzac(), zzb().zzh())) {
                    zzq().zzu().zza("Rechecking which service to use due to a GMP App Id change");
                    zzb().zzj();
                    zzj().zzaa();
                    this.zzv.zzag();
                    this.zzv.zzae();
                    zzb().zzh.zza(this.zza);
                    zzb().zzj.zza((String) null);
                }
                zzb().zzb(zzx().zzab());
                zzb().zzc(zzx().zzac());
            }
            if (zzmj.zzb() && this.zzi.zza(zzat.zzcg) && !zzb().zzw().zze()) {
                zzb().zzj.zza((String) null);
            }
            zzg().zza(zzb().zzj.zza());
            if (zzmu.zzb() && this.zzi.zza(zzat.zzbo) && !zzh().zzj() && !TextUtils.isEmpty(zzb().zzu.zza())) {
                zzq().zzh().zza("Remote config removed with active feature rollouts");
                zzb().zzu.zza((String) null);
            }
            if (!TextUtils.isEmpty(zzx().zzab()) || !TextUtils.isEmpty(zzx().zzac())) {
                boolean zzaa2 = zzaa();
                if (!zzb().zzy() && !this.zzi.zzf()) {
                    zzb().zzb(!zzaa2);
                }
                if (zzaa2) {
                    zzg().zzah();
                }
                zzd().zza.zza();
                zzv().zza((AtomicReference<String>) new AtomicReference());
                if (zzny.zzb() && this.zzi.zza(zzat.zzby)) {
                    zzv().zza(zzb().zzx.zza());
                }
            }
        } else if (zzaa()) {
            if (!zzh().zzc("android.permission.INTERNET")) {
                zzq().zze().zza("App is missing INTERNET permission");
            }
            if (!zzh().zzc("android.permission.ACCESS_NETWORK_STATE")) {
                zzq().zze().zza("App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!Wrappers.packageManager(this.zzc).isCallerInstantApp() && !this.zzi.zzw()) {
                if (!zzfn.zza(this.zzc)) {
                    zzq().zze().zza("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzkw.zza(this.zzc, false)) {
                    zzq().zze().zza("AppMeasurementService not registered/enabled");
                }
            }
            zzq().zze().zza("Uploading is not possible. App measurement disabled");
        }
        zzb().zzo.zza(this.zzi.zza(zzat.zzax));
    }

    public final zzx zzt() {
        return this.zzh;
    }

    public final zzy zza() {
        return this.zzi;
    }

    public final zzfd zzb() {
        zza((zzgo) this.zzj);
        return this.zzj;
    }

    public final zzer zzq() {
        zzb((zzgr) this.zzk);
        return this.zzk;
    }

    public final zzer zzc() {
        zzer zzer = this.zzk;
        if (zzer == null || !zzer.zzz()) {
            return null;
        }
        return this.zzk;
    }

    public final zzfo zzp() {
        zzb((zzgr) this.zzl);
        return this.zzl;
    }

    public final zzju zzd() {
        zzb((zzg) this.zzm);
        return this.zzm;
    }

    public final zzfi zze() {
        return this.zzy;
    }

    /* access modifiers changed from: package-private */
    public final zzfo zzf() {
        return this.zzl;
    }

    public final zzgy zzg() {
        zzb((zzg) this.zzr);
        return this.zzr;
    }

    public final zzkw zzh() {
        zza((zzgo) this.zzn);
        return this.zzn;
    }

    public final zzep zzi() {
        zza((zzgo) this.zzo);
        return this.zzo;
    }

    public final zzen zzj() {
        zzb((zzg) this.zzu);
        return this.zzu;
    }

    private final zzia zzah() {
        zzb((zzgr) this.zzt);
        return this.zzt;
    }

    public final Context zzm() {
        return this.zzc;
    }

    public final boolean zzk() {
        return TextUtils.isEmpty(this.zzd);
    }

    public final String zzn() {
        return this.zzd;
    }

    public final String zzo() {
        return this.zze;
    }

    public final String zzr() {
        return this.zzf;
    }

    public final boolean zzs() {
        return this.zzg;
    }

    public final Clock zzl() {
        return this.zzp;
    }

    public final zzij zzu() {
        zzb((zzg) this.zzq);
        return this.zzq;
    }

    public final zzio zzv() {
        zzb((zzg) this.zzv);
        return this.zzv;
    }

    public final zzal zzw() {
        zzb((zzgr) this.zzw);
        return this.zzw;
    }

    public final zzek zzx() {
        zzb((zzg) this.zzx);
        return this.zzx;
    }

    public final zza zzy() {
        zza zza2 = this.zzs;
        if (zza2 != null) {
            return zza2;
        }
        throw new IllegalStateException("Component not created");
    }

    public static zzfv zza(Context context, zzae zzae2, Long l) {
        if (zzae2 != null && (zzae2.zze == null || zzae2.zzf == null)) {
            zzae2 = new zzae(zzae2.zza, zzae2.zzb, zzae2.zzc, zzae2.zzd, (String) null, (String) null, zzae2.zzg);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzfv.class) {
                if (zzb == null) {
                    zzb = new zzfv(new zzgz(context, zzae2, l));
                }
            }
        } else if (!(zzae2 == null || zzae2.zzg == null || !zzae2.zzg.containsKey("dataCollectionDefaultEnabled"))) {
            zzb.zza(zzae2.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zzb;
    }

    private static void zzb(zzgr zzgr) {
        if (zzgr == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzgr.zzz()) {
            String valueOf = String.valueOf(zzgr.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zzb(zzg zzg2) {
        if (zzg2 == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzg2.zzu()) {
            String valueOf = String.valueOf(zzg2.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzgo zzgo) {
        if (zzgo == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        this.zzac = Boolean.valueOf(z);
    }

    public final boolean zzz() {
        return this.zzac != null && this.zzac.booleanValue();
    }

    public final boolean zzaa() {
        return zzab() == 0;
    }

    public final int zzab() {
        zzp().zzc();
        if (this.zzi.zzf()) {
            return 1;
        }
        Boolean bool = this.zzae;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        if (zzmj.zzb() && this.zzi.zza(zzat.zzcg) && !zzac()) {
            return 8;
        }
        Boolean zzu2 = zzb().zzu();
        if (zzu2 == null) {
            Boolean zzf2 = this.zzi.zzf("firebase_analytics_collection_enabled");
            if (zzf2 == null) {
                Boolean bool2 = this.zzad;
                if (bool2 != null) {
                    if (bool2.booleanValue()) {
                        return 0;
                    }
                    return 5;
                } else if (GoogleServices.isMeasurementExplicitlyDisabled()) {
                    return 6;
                } else {
                    if (!this.zzi.zza(zzat.zzar) || this.zzac == null || this.zzac.booleanValue()) {
                        return 0;
                    }
                    return 7;
                }
            } else if (zzf2.booleanValue()) {
                return 0;
            } else {
                return 4;
            }
        } else if (zzu2.booleanValue()) {
            return 0;
        } else {
            return 3;
        }
    }

    public final void zzb(boolean z) {
        zzp().zzc();
        this.zzaf = z;
    }

    public final boolean zzac() {
        zzp().zzc();
        return this.zzaf;
    }

    /* access modifiers changed from: package-private */
    public final void zzad() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzgr zzgr) {
        this.zzag++;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzg zzg2) {
        this.zzag++;
    }

    /* access modifiers changed from: package-private */
    public final void zzae() {
        this.zzah.incrementAndGet();
    }

    /* access modifiers changed from: protected */
    public final boolean zzaf() {
        if (this.zzz) {
            zzp().zzc();
            Boolean bool = this.zzaa;
            if (bool == null || this.zzab == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzp.elapsedRealtime() - this.zzab) > 1000)) {
                this.zzab = this.zzp.elapsedRealtime();
                boolean z = true;
                Boolean valueOf = Boolean.valueOf(zzh().zzc("android.permission.INTERNET") && zzh().zzc("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzc).isCallerInstantApp() || this.zzi.zzw() || (zzfn.zza(this.zzc) && zzkw.zza(this.zzc, false))));
                this.zzaa = valueOf;
                if (valueOf.booleanValue()) {
                    if (!zzh().zza(zzx().zzab(), zzx().zzac(), zzx().zzad()) && TextUtils.isEmpty(zzx().zzac())) {
                        z = false;
                    }
                    this.zzaa = Boolean.valueOf(z);
                }
            }
            return this.zzaa.booleanValue();
        }
        throw new IllegalStateException("AppMeasurement is not initialized");
    }

    public final void zzag() {
        zzp().zzc();
        zzb((zzgr) zzah());
        String zzaa2 = zzx().zzaa();
        Pair<String, Boolean> zza2 = zzb().zza(zzaa2);
        if (!this.zzi.zzg().booleanValue() || ((Boolean) zza2.second).booleanValue() || TextUtils.isEmpty((CharSequence) zza2.first)) {
            zzq().zzv().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
        } else if (!zzah().zzf()) {
            zzq().zzh().zza("Network is not available for Deferred Deep Link request. Skipping");
        } else {
            zzkw zzh2 = zzh();
            zzx();
            URL zza3 = zzh2.zza(32053, zzaa2, (String) zza2.first, zzb().zzt.zza() - 1);
            zzia zzah2 = zzah();
            zzfu zzfu = new zzfu(this);
            zzah2.zzc();
            zzah2.zzaa();
            Preconditions.checkNotNull(zza3);
            Preconditions.checkNotNull(zzfu);
            zzah2.zzp().zzc((Runnable) new zzic(zzah2, zzaa2, zza3, (byte[]) null, (Map<String, String>) null, zzfu));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str, int i, Throwable th, byte[] bArr, Map map) {
        List<ResolveInfo> queryIntentActivities;
        boolean z = true;
        if (!((i == 200 || i == 204 || i == 304) && th == null)) {
            zzq().zzh().zza("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
            return;
        }
        zzb().zzs.zza(true);
        if (bArr.length == 0) {
            zzq().zzv().zza("Deferred Deep Link response empty.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String optString = jSONObject.optString("deeplink", "");
            String optString2 = jSONObject.optString("gclid", "");
            double optDouble = jSONObject.optDouble("timestamp", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            if (TextUtils.isEmpty(optString)) {
                zzq().zzv().zza("Deferred Deep Link is empty.");
                return;
            }
            zzkw zzh2 = zzh();
            if (TextUtils.isEmpty(optString) || (queryIntentActivities = zzh2.zzm().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0)) == null || queryIntentActivities.isEmpty()) {
                z = false;
            }
            if (!z) {
                zzq().zzh().zza("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("gclid", optString2);
            bundle.putString("_cis", "ddp");
            this.zzr.zza(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
            zzkw zzh3 = zzh();
            if (!TextUtils.isEmpty(optString) && zzh3.zza(optString, optDouble)) {
                zzh3.zzm().sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
            }
        } catch (JSONException e) {
            zzq().zze().zza("Failed to parse the Deferred Deep Link response. exception", e);
        }
    }
}
