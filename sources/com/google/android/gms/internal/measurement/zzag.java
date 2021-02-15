package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzgw;
import com.google.android.gms.measurement.internal.zzgx;
import com.google.android.gms.measurement.internal.zzih;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
public class zzag {
    private static volatile zzag zzb = null;
    /* access modifiers changed from: private */
    public static Boolean zzh = null;
    private static String zzi = "allow_remote_dynamite";
    private static boolean zzj = false;
    protected final Clock zza;
    /* access modifiers changed from: private */
    public final String zzc;
    private final ExecutorService zzd;
    private final AppMeasurementSdk zze;
    /* access modifiers changed from: private */
    public List<Pair<zzgw, zzd>> zzf;
    private int zzg;
    /* access modifiers changed from: private */
    public boolean zzk;
    private String zzl;
    /* access modifiers changed from: private */
    public zzv zzm;

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
    class zzc implements Application.ActivityLifecycleCallbacks {
        zzc() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            zzag.this.zza((zzb) new zzbp(this, bundle, activity));
        }

        public final void onActivityStarted(Activity activity) {
            zzag.this.zza((zzb) new zzbo(this, activity));
        }

        public final void onActivityResumed(Activity activity) {
            zzag.this.zza((zzb) new zzbr(this, activity));
        }

        public final void onActivityPaused(Activity activity) {
            zzag.this.zza((zzb) new zzbq(this, activity));
        }

        public final void onActivityStopped(Activity activity) {
            zzag.this.zza((zzb) new zzbt(this, activity));
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzt zzt = new zzt();
            zzag.this.zza((zzb) new zzbs(this, activity, zzt));
            Bundle zzb = zzt.zzb(50);
            if (zzb != null) {
                bundle.putAll(zzb);
            }
        }

        public final void onActivityDestroyed(Activity activity) {
            zzag.this.zza((zzb) new zzbu(this, activity));
        }
    }

    public static zzag zza(Context context) {
        return zza(context, (String) null, (String) null, (String) null, (Bundle) null);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
    abstract class zzb implements Runnable {
        final long zza;
        final long zzb;
        private final boolean zzc;

        zzb(zzag zzag) {
            this(true);
        }

        /* access modifiers changed from: package-private */
        public abstract void zza() throws RemoteException;

        /* access modifiers changed from: protected */
        public void zzb() {
        }

        zzb(boolean z) {
            this.zza = zzag.this.zza.currentTimeMillis();
            this.zzb = zzag.this.zza.elapsedRealtime();
            this.zzc = z;
        }

        public void run() {
            if (zzag.this.zzk) {
                zzb();
                return;
            }
            try {
                zza();
            } catch (Exception e) {
                zzag.this.zza(e, false, this.zzc);
                zzb();
            }
        }
    }

    public static zzag zza(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzb == null) {
            synchronized (zzag.class) {
                if (zzb == null) {
                    zzb = new zzag(context, str, str2, str3, bundle);
                }
            }
        }
        return zzb;
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
    static class zza extends zzaa {
        private final zzgx zza;

        zza(zzgx zzgx) {
            this.zza = zzgx;
        }

        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.interceptEvent(str, str2, bundle, j);
        }

        public final int zza() {
            return System.identityHashCode(this.zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
    static class zzd extends zzaa {
        private final zzgw zza;

        zzd(zzgw zzgw) {
            this.zza = zzgw;
        }

        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.onEvent(str, str2, bundle, j);
        }

        public final int zza() {
            return System.identityHashCode(this.zza);
        }
    }

    public final AppMeasurementSdk zza() {
        return this.zze;
    }

    private zzag(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zzc(str2, str3)) {
            this.zzc = "FA";
        } else {
            this.zzc = str;
        }
        this.zza = DefaultClock.getInstance();
        this.zzd = zzi.zza().zza(new zzat(this), zzr.zza);
        this.zze = new AppMeasurementSdk(this);
        boolean z = false;
        if (!(!zze(context) || zzk())) {
            this.zzl = null;
            this.zzk = true;
            Log.w(this.zzc, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
            return;
        }
        if (!zzc(str2, str3)) {
            this.zzl = "fa";
            if (str2 == null || str3 == null) {
                if ((str2 == null) ^ (str3 == null ? true : z)) {
                    Log.w(this.zzc, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzc, "Deferring to Google Analytics for Firebase for event data collection. https://goo.gl/J1sWQy");
            }
        } else {
            this.zzl = str2;
        }
        zza((zzb) new zzaj(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            Log.w(this.zzc, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zzc());
        }
    }

    private static boolean zze(Context context) {
        try {
            return zzih.zza(context, "google_app_id") != null;
        } catch (IllegalStateException unused) {
        }
    }

    /* access modifiers changed from: private */
    public static boolean zzc(String str, String str2) {
        return (str2 == null || str == null || zzk()) ? false : true;
    }

    /* access modifiers changed from: private */
    public final void zza(zzb zzb2) {
        this.zzd.execute(zzb2);
    }

    /* access modifiers changed from: protected */
    public final zzv zza(Context context, boolean z) {
        DynamiteModule.VersionPolicy versionPolicy;
        if (z) {
            try {
                versionPolicy = DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION;
            } catch (DynamiteModule.LoadingException e) {
                zza((Exception) e, true, false);
                return null;
            }
        } else {
            versionPolicy = DynamiteModule.PREFER_LOCAL;
        }
        return zzu.asInterface(DynamiteModule.load(context, versionPolicy, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
    }

    /* access modifiers changed from: private */
    public static int zzf(Context context) {
        return DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID);
    }

    /* access modifiers changed from: private */
    public static int zzg(Context context) {
        return DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID);
    }

    /* access modifiers changed from: private */
    public final void zza(Exception exc, boolean z, boolean z2) {
        this.zzk |= z;
        if (z) {
            Log.w(this.zzc, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zza(5, "Error with data collection. Data lost.", (Object) exc, (Object) null, (Object) null);
        }
        Log.w(this.zzc, "Error with data collection. Data lost.", exc);
    }

    private static boolean zzk() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final void zza(zzgx zzgx) {
        zza((zzb) new zzbc(this, zzgx));
    }

    public final void zza(zzgw zzgw) {
        Preconditions.checkNotNull(zzgw);
        zza((zzb) new zzbl(this, zzgw));
    }

    public final void zzb(zzgw zzgw) {
        Preconditions.checkNotNull(zzgw);
        zza((zzb) new zzbk(this, zzgw));
    }

    public final void zza(String str, Bundle bundle) {
        zza((String) null, str, bundle, false, true, (Long) null);
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, (Long) null);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, bundle, true, false, Long.valueOf(j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        zza((zzb) new zzbn(this, l, str, str2, bundle, z, z2));
    }

    public final void zza(String str, String str2) {
        zza((String) null, str, (Object) str2, false);
    }

    public final void zza(String str, String str2, Object obj) {
        zza(str, str2, obj, true);
    }

    private final void zza(String str, String str2, Object obj, boolean z) {
        zza((zzb) new zzbm(this, str, str2, obj, z));
    }

    public final void zza(Bundle bundle) {
        zza((zzb) new zzai(this, bundle));
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza((zzb) new zzal(this, str, str2, bundle));
    }

    public final List<Bundle> zzb(String str, String str2) {
        zzt zzt = new zzt();
        zza((zzb) new zzak(this, str, str2, zzt));
        List<Bundle> list = (List) zzt.zza(zzt.zzb(CoroutineLiveDataKt.DEFAULT_TIMEOUT), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final void zza(String str) {
        zza((zzb) new zzan(this, str));
    }

    public final void zza(Activity activity, String str, String str2) {
        zza((zzb) new zzam(this, activity, str, str2));
    }

    public final void zza(Boolean bool) {
        zza((zzb) new zzap(this, bool));
    }

    public final void zzb(Bundle bundle) {
        zza((zzb) new zzao(this, bundle));
    }

    public final void zzb() {
        zza((zzb) new zzar(this));
    }

    public final void zza(long j) {
        zza((zzb) new zzaq(this, j));
    }

    public final void zzb(long j) {
        zza((zzb) new zzas(this, j));
    }

    public final void zzb(String str) {
        zza((zzb) new zzav(this, str));
    }

    public final void zzc(String str) {
        zza((zzb) new zzau(this, str));
    }

    public final String zzc() {
        zzt zzt = new zzt();
        zza((zzb) new zzax(this, zzt));
        return zzt.zza(500);
    }

    public final String zzd() {
        zzt zzt = new zzt();
        zza((zzb) new zzaw(this, zzt));
        return zzt.zza(50);
    }

    public final long zze() {
        zzt zzt = new zzt();
        zza((zzb) new zzaz(this, zzt));
        Long l = (Long) zzt.zza(zzt.zzb(500), Long.class);
        if (l != null) {
            return l.longValue();
        }
        long nextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
        int i = this.zzg + 1;
        this.zzg = i;
        return nextLong + ((long) i);
    }

    public final String zzf() {
        zzt zzt = new zzt();
        zza((zzb) new zzay(this, zzt));
        return zzt.zza(500);
    }

    public final String zzg() {
        zzt zzt = new zzt();
        zza((zzb) new zzbb(this, zzt));
        return zzt.zza(500);
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        zzt zzt = new zzt();
        zza((zzb) new zzba(this, str, str2, z, zzt));
        Bundle zzb2 = zzt.zzb(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        if (zzb2 == null || zzb2.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(zzb2.size());
        for (String str3 : zzb2.keySet()) {
            Object obj = zzb2.get(str3);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                hashMap.put(str3, obj);
            }
        }
        return hashMap;
    }

    public final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zza((zzb) new zzbd(this, false, 5, str, obj, (Object) null, (Object) null));
    }

    public final Bundle zza(Bundle bundle, boolean z) {
        zzt zzt = new zzt();
        zza((zzb) new zzbf(this, bundle, zzt));
        if (z) {
            return zzt.zzb(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        }
        return null;
    }

    public final int zzd(String str) {
        zzt zzt = new zzt();
        zza((zzb) new zzbe(this, str, zzt));
        Integer num = (Integer) zzt.zza(zzt.zzb(10000), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    public final String zzh() {
        zzt zzt = new zzt();
        zza((zzb) new zzbh(this, zzt));
        return zzt.zza(120000);
    }

    public final String zzi() {
        return this.zzl;
    }

    public final Object zza(int i) {
        zzt zzt = new zzt();
        zza((zzb) new zzbg(this, zzt, i));
        return zzt.zza(zzt.zzb(15000), Object.class);
    }

    public final void zza(boolean z) {
        zza((zzb) new zzbj(this, z));
    }

    public final void zzc(Bundle bundle) {
        zza((zzb) new zzbi(this, bundle));
    }

    /* access modifiers changed from: private */
    public static void zzh(Context context) {
        synchronized (zzag.class) {
            try {
                if (zzh == null) {
                    if (zza(context, "app_measurement_internal_disable_startup_flags")) {
                        zzh = false;
                        return;
                    }
                    SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
                    zzh = Boolean.valueOf(sharedPreferences.getBoolean(zzi, false));
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.remove(zzi);
                    edit.apply();
                }
            } catch (Exception e) {
                Log.e("FA", "Exception reading flag from SharedPreferences.", e);
                zzh = false;
            }
        }
    }

    private static boolean zza(Context context, String str) {
        Preconditions.checkNotEmpty(str);
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                if (applicationInfo.metaData != null) {
                    return applicationInfo.metaData.getBoolean(str);
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }
}
