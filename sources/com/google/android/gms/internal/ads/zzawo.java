package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.lang.reflect.Method;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzawo {
    private final AtomicReference<ExecutorService> zzdxn = new AtomicReference<>((Object) null);
    private final Object zzdxo = new Object();
    private String zzdxp = null;
    private String zzdxq = null;
    private final AtomicBoolean zzdxr = new AtomicBoolean(false);
    private final AtomicInteger zzdxs = new AtomicInteger(-1);
    private final AtomicReference<Object> zzdxt = new AtomicReference<>((Object) null);
    private final AtomicReference<Object> zzdxu = new AtomicReference<>((Object) null);
    private final ConcurrentMap<String, Method> zzdxv = new ConcurrentHashMap(9);
    private final AtomicReference<zzbiq> zzdxw = new AtomicReference<>((Object) null);
    private final BlockingQueue<FutureTask<?>> zzdxx = new ArrayBlockingQueue(20);
    private final Object zzdxy = new Object();

    public final boolean zzac(Context context) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcnl)).booleanValue() && !this.zzdxr.get()) {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcnv)).booleanValue()) {
                return true;
            }
            if (this.zzdxs.get() == -1) {
                zzwm.zzpt();
                if (!zzbbg.zzd(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                    zzwm.zzpt();
                    if (zzbbg.zzbp(context)) {
                        zzayp.zzfe("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                        this.zzdxs.set(0);
                    }
                }
                this.zzdxs.set(1);
            }
            if (this.zzdxs.get() == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean zzad(Context context) {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcns)).booleanValue()) {
            return false;
        }
        if (DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) < ((Integer) zzwm.zzpx().zzd(zzabb.zzcnt)).intValue()) {
            return false;
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcnu)).booleanValue()) {
            try {
                context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                return false;
            } catch (ClassNotFoundException unused) {
            }
        }
        return true;
    }

    public final void zza(Context context, zzaae zzaae) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcny)).booleanValue() && zzac(context) && zzad(context)) {
            synchronized (this.zzdxy) {
            }
        }
    }

    public final void zza(Context context, zzvg zzvg) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcny)).booleanValue() && zzac(context) && zzad(context)) {
            synchronized (this.zzdxy) {
            }
        }
    }

    public final void zzf(Context context, String str) {
        if (zzac(context)) {
            if (zzad(context)) {
                zza("beginAdUnitExposure", (zzaxe) new zzawr(str));
            } else {
                zza(context, str, "beginAdUnitExposure");
            }
        }
    }

    public final void zzg(Context context, String str) {
        if (zzac(context)) {
            if (zzad(context)) {
                zza("endAdUnitExposure", (zzaxe) new zzawu(str));
            } else {
                zza(context, str, "endAdUnitExposure");
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzae(android.content.Context r7) {
        /*
            r6 = this;
            java.lang.String r0 = "getCurrentScreenName"
            boolean r1 = r6.zzac(r7)
            java.lang.String r2 = ""
            if (r1 != 0) goto L_0x000b
            return r2
        L_0x000b:
            boolean r1 = zzad(r7)
            if (r1 == 0) goto L_0x001c
            com.google.android.gms.internal.ads.zzaxf r7 = com.google.android.gms.internal.ads.zzawx.zzdya
            java.lang.String r0 = "getCurrentScreenNameOrScreenClass"
            java.lang.Object r7 = r6.zza((java.lang.String) r0, r2, r7)
            java.lang.String r7 = (java.lang.String) r7
            return r7
        L_0x001c:
            java.util.concurrent.atomic.AtomicReference<java.lang.Object> r1 = r6.zzdxt
            r3 = 1
            java.lang.String r4 = "com.google.android.gms.measurement.AppMeasurement"
            boolean r1 = r6.zza((android.content.Context) r7, (java.lang.String) r4, (java.util.concurrent.atomic.AtomicReference<java.lang.Object>) r1, (boolean) r3)
            if (r1 != 0) goto L_0x0028
            return r2
        L_0x0028:
            r1 = 0
            java.lang.reflect.Method r3 = r6.zzn(r7, r0)     // Catch:{ Exception -> 0x0056 }
            java.util.concurrent.atomic.AtomicReference<java.lang.Object> r4 = r6.zzdxt     // Catch:{ Exception -> 0x0056 }
            java.lang.Object r4 = r4.get()     // Catch:{ Exception -> 0x0056 }
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0056 }
            java.lang.Object r3 = r3.invoke(r4, r5)     // Catch:{ Exception -> 0x0056 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0056 }
            if (r3 != 0) goto L_0x0052
            java.lang.String r3 = "getCurrentScreenClass"
            java.lang.reflect.Method r7 = r6.zzn(r7, r3)     // Catch:{ Exception -> 0x0056 }
            java.util.concurrent.atomic.AtomicReference<java.lang.Object> r3 = r6.zzdxt     // Catch:{ Exception -> 0x0056 }
            java.lang.Object r3 = r3.get()     // Catch:{ Exception -> 0x0056 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0056 }
            java.lang.Object r7 = r7.invoke(r3, r4)     // Catch:{ Exception -> 0x0056 }
            r3 = r7
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x0056 }
        L_0x0052:
            if (r3 == 0) goto L_0x0055
            return r3
        L_0x0055:
            return r2
        L_0x0056:
            r6.zzh((java.lang.String) r0, (boolean) r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzawo.zzae(android.content.Context):java.lang.String");
    }

    public final void zzh(Context context, String str) {
        if (!zzac(context) || !(context instanceof Activity)) {
            return;
        }
        if (zzad(context)) {
            zza("setScreenName", (zzaxe) new zzaww(context, str));
        } else if (zza(context, "com.google.firebase.analytics.FirebaseAnalytics", this.zzdxu, false)) {
            Method zzo = zzo(context, "setCurrentScreen");
            try {
                zzo.invoke(this.zzdxu.get(), new Object[]{(Activity) context, str, context.getPackageName()});
            } catch (Exception unused) {
                zzh("setCurrentScreen", false);
            }
        }
    }

    public final String zzaf(Context context) {
        if (!zzac(context)) {
            return null;
        }
        synchronized (this.zzdxo) {
            if (this.zzdxp != null) {
                String str = this.zzdxp;
                return str;
            }
            if (zzad(context)) {
                this.zzdxp = (String) zza("getGmpAppId", this.zzdxp, zzawz.zzdya);
            } else {
                this.zzdxp = (String) zza("getGmpAppId", context);
            }
            String str2 = this.zzdxp;
            return str2;
        }
    }

    public final String zzag(Context context) {
        if (!zzac(context)) {
            return null;
        }
        long longValue = ((Long) zzwm.zzpx().zzd(zzabb.zzcnq)).longValue();
        if (zzad(context)) {
            if (longValue >= 0) {
                return (String) zzvx().submit(new zzaxb(this)).get(longValue, TimeUnit.MILLISECONDS);
            }
            try {
                return (String) zza("getAppInstanceId", (Object) null, zzawy.zzdya);
            } catch (TimeoutException unused) {
                return "TIME_OUT";
            } catch (Exception unused2) {
                return null;
            }
        } else if (longValue < 0) {
            return (String) zza("getAppInstanceId", context);
        } else {
            try {
                return (String) zzvx().submit(new zzaxa(this, context)).get(longValue, TimeUnit.MILLISECONDS);
            } catch (TimeoutException unused3) {
                return "TIME_OUT";
            } catch (Exception unused4) {
                return null;
            }
        }
    }

    public final String zzah(Context context) {
        if (!zzac(context)) {
            return null;
        }
        if (zzad(context)) {
            Long l = (Long) zza("getAdEventId", (Object) null, zzaxd.zzdya);
            if (l != null) {
                return Long.toString(l.longValue());
            }
            return null;
        }
        Object zza = zza("generateEventId", context);
        if (zza != null) {
            return zza.toString();
        }
        return null;
    }

    public final String zzai(Context context) {
        if (!zzac(context)) {
            return null;
        }
        synchronized (this.zzdxo) {
            if (this.zzdxq != null) {
                String str = this.zzdxq;
                return str;
            }
            if (zzad(context)) {
                this.zzdxq = (String) zza("getAppIdOrigin", this.zzdxq, zzawq.zzdya);
            } else {
                this.zzdxq = "fa";
            }
            String str2 = this.zzdxq;
            return str2;
        }
    }

    public final void zzi(Context context, String str) {
        zza(context, "_ac", str, (Bundle) null);
    }

    public final void zzj(Context context, String str) {
        zza(context, "_ai", str, (Bundle) null);
    }

    public final void zzk(Context context, String str) {
        zza(context, "_aq", str, (Bundle) null);
    }

    public final void zzl(Context context, String str) {
        zza(context, "_aa", str, (Bundle) null);
    }

    public final void zza(Context context, String str, String str2, String str3, int i) {
        if (zzac(context)) {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str2);
            bundle.putString("reward_type", str3);
            bundle.putInt("reward_value", i);
            zza(context, "_ar", str, bundle);
            StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 75);
            sb.append("Log a Firebase reward video event, reward type: ");
            sb.append(str3);
            sb.append(", reward value: ");
            sb.append(i);
            zzayp.zzei(sb.toString());
        }
    }

    private final void zza(Context context, String str, String str2, Bundle bundle) {
        if (zzac(context)) {
            Bundle zzl = zzl(str2, str);
            if (bundle != null) {
                zzl.putAll(bundle);
            }
            if (zzad(context)) {
                zza("logEventInternal", (zzaxe) new zzawt(str, zzl));
            } else if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzdxt, true)) {
                Method zzaj = zzaj(context);
                try {
                    zzaj.invoke(this.zzdxt.get(), new Object[]{"am", str, zzl});
                } catch (Exception unused) {
                    zzh("logEventInternal", true);
                }
            }
        }
    }

    private static Bundle zzl(String str, String str2) {
        Bundle bundle = new Bundle();
        try {
            bundle.putLong("_aeid", Long.parseLong(str));
        } catch (NullPointerException | NumberFormatException e) {
            String valueOf = String.valueOf(str);
            zzayp.zzc(valueOf.length() != 0 ? "Invalid event ID: ".concat(valueOf) : new String("Invalid event ID: "), e);
        }
        if ("_ac".equals(str2)) {
            bundle.putInt("_r", 1);
        }
        return bundle;
    }

    private final Method zzaj(Context context) {
        Method method = (Method) this.zzdxv.get("logEventInternal");
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", new Class[]{String.class, String.class, Bundle.class});
            this.zzdxv.put("logEventInternal", declaredMethod);
            return declaredMethod;
        } catch (Exception unused) {
            zzh("logEventInternal", true);
            return null;
        }
    }

    private final Method zzm(Context context, String str) {
        Method method = (Method) this.zzdxv.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[]{String.class});
            this.zzdxv.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception unused) {
            zzh(str, false);
            return null;
        }
    }

    private final Method zzn(Context context, String str) {
        Method method = (Method) this.zzdxv.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[0]);
            this.zzdxv.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception unused) {
            zzh(str, false);
            return null;
        }
    }

    private final Method zzo(Context context, String str) {
        Method method = (Method) this.zzdxv.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(str, new Class[]{Activity.class, String.class, String.class});
            this.zzdxv.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception unused) {
            zzh(str, false);
            return null;
        }
    }

    private final void zza(Context context, String str, String str2) {
        if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzdxt, true)) {
            Method zzm = zzm(context, str2);
            try {
                zzm.invoke(this.zzdxt.get(), new Object[]{str});
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 37 + String.valueOf(str).length());
                sb.append("Invoke Firebase method ");
                sb.append(str2);
                sb.append(", Ad Unit Id: ");
                sb.append(str);
                zzayp.zzei(sb.toString());
            } catch (Exception unused) {
                zzh(str2, false);
            }
        }
    }

    private final Object zza(String str, Context context) {
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzdxt, true)) {
            return null;
        }
        try {
            return zzn(context, str).invoke(this.zzdxt.get(), new Object[0]);
        } catch (Exception unused) {
            zzh(str, true);
            return null;
        }
    }

    private final void zzh(String str, boolean z) {
        if (!this.zzdxr.get()) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 30);
            sb.append("Invoke Firebase method ");
            sb.append(str);
            sb.append(" error.");
            zzayp.zzfe(sb.toString());
            if (z) {
                zzayp.zzfe("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
                this.zzdxr.set(true);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v9, types: [java.util.concurrent.ExecutorService] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.concurrent.ExecutorService zzvx() {
        /*
            r9 = this;
            java.util.concurrent.atomic.AtomicReference<java.util.concurrent.ExecutorService> r0 = r9.zzdxn
            java.lang.Object r0 = r0.get()
            if (r0 != 0) goto L_0x0066
            boolean r0 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()
            if (r0 == 0) goto L_0x002d
            com.google.android.gms.internal.ads.zzdvd r0 = com.google.android.gms.internal.ads.zzdvg.zzaxc()
            com.google.android.gms.internal.ads.zzaaq<java.lang.Integer> r1 = com.google.android.gms.internal.ads.zzabb.zzcnr
            com.google.android.gms.internal.ads.zzaax r2 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r1 = r2.zzd(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            java.util.concurrent.ThreadFactory r2 = r9.zzvy()
            int r3 = com.google.android.gms.internal.ads.zzdvl.zzhov
            java.util.concurrent.ExecutorService r0 = r0.zza(r1, r2, r3)
            goto L_0x0060
        L_0x002d:
            java.util.concurrent.ThreadPoolExecutor r0 = new java.util.concurrent.ThreadPoolExecutor
            com.google.android.gms.internal.ads.zzaaq<java.lang.Integer> r1 = com.google.android.gms.internal.ads.zzabb.zzcnr
            com.google.android.gms.internal.ads.zzaax r2 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r1 = r2.zzd(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r2 = r1.intValue()
            com.google.android.gms.internal.ads.zzaaq<java.lang.Integer> r1 = com.google.android.gms.internal.ads.zzabb.zzcnr
            com.google.android.gms.internal.ads.zzaax r3 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r1 = r3.zzd(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r3 = r1.intValue()
            r4 = 1
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MINUTES
            java.util.concurrent.LinkedBlockingQueue r7 = new java.util.concurrent.LinkedBlockingQueue
            r7.<init>()
            java.util.concurrent.ThreadFactory r8 = r9.zzvy()
            r1 = r0
            r1.<init>(r2, r3, r4, r6, r7, r8)
        L_0x0060:
            java.util.concurrent.atomic.AtomicReference<java.util.concurrent.ExecutorService> r1 = r9.zzdxn
            r2 = 0
            r1.compareAndSet(r2, r0)
        L_0x0066:
            java.util.concurrent.atomic.AtomicReference<java.util.concurrent.ExecutorService> r0 = r9.zzdxn
            java.lang.Object r0 = r0.get()
            java.util.concurrent.ExecutorService r0 = (java.util.concurrent.ExecutorService) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzawo.zzvx():java.util.concurrent.ExecutorService");
    }

    private final ThreadFactory zzvy() {
        return new zzaxc(this);
    }

    private final boolean zza(Context context, String str, AtomicReference<Object> atomicReference, boolean z) {
        if (atomicReference.get() == null) {
            try {
                atomicReference.compareAndSet((Object) null, context.getClassLoader().loadClass(str).getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context}));
            } catch (Exception unused) {
                zzh("getInstance", z);
                return false;
            }
        }
        return true;
    }

    private final void zza(String str, zzaxe zzaxe) {
        synchronized (this.zzdxw) {
            FutureTask futureTask = new FutureTask(new zzaws(this, zzaxe, str), (Object) null);
            if (this.zzdxw.get() != null) {
                futureTask.run();
            } else {
                this.zzdxx.offer(futureTask);
            }
        }
    }

    private final <T> T zza(String str, T t, zzaxf<T> zzaxf) {
        synchronized (this.zzdxw) {
            if (this.zzdxw.get() != null) {
                try {
                    T zza = zzaxf.zza(this.zzdxw.get());
                    return zza;
                } catch (Exception unused) {
                    zzh(str, false);
                    return t;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzaxe zzaxe, String str) {
        if (this.zzdxw.get() != null) {
            try {
                zzaxe.zzb(this.zzdxw.get());
            } catch (Exception unused) {
                zzh(str, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzak(Context context) throws Exception {
        return (String) zza("getAppInstanceId", context);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzvz() throws Exception {
        return (String) zza("getAppInstanceId", (Object) null, zzawv.zzdya);
    }
}
