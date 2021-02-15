package com.google.android.gms.internal.measurement;

import android.content.Context;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public abstract class zzdh<T> {
    private static final Object zza = new Object();
    @Nullable
    private static volatile zzdp zzb = null;
    private static volatile boolean zzc = false;
    private static final AtomicReference<Collection<zzdh<?>>> zzd = new AtomicReference<>();
    private static zzdq zze = new zzdq(zzdj.zza);
    private static final AtomicInteger zzi = new AtomicInteger();
    private final zzdm zzf;
    private final String zzg;
    private final T zzh;
    private volatile int zzj;
    private volatile T zzk;
    private final boolean zzl;

    @Deprecated
    public static void zza(Context context) {
        synchronized (zza) {
            zzdp zzdp = zzb;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzdp == null || zzdp.zza() != context) {
                zzct.zzc();
                zzdo.zza();
                zzcy.zza();
                zzb = new zzcq(context, zzef.zza(new zzdg(context)));
                zzi.incrementAndGet();
            }
        }
    }

    static final /* synthetic */ boolean zzd() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public abstract T zza(Object obj);

    static void zza() {
        zzi.incrementAndGet();
    }

    private zzdh(zzdm zzdm, String str, T t, boolean z) {
        this.zzj = -1;
        if (zzdm.zza != null) {
            this.zzf = zzdm;
            this.zzg = str;
            this.zzh = t;
            this.zzl = z;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    private final String zza(String str) {
        if (str != null && str.isEmpty()) {
            return this.zzg;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(this.zzg);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String zzb() {
        return zza(this.zzf.zzc);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ef  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T zzc() {
        /*
            r7 = this;
            boolean r0 = r7.zzl
            if (r0 != 0) goto L_0x0011
            com.google.android.gms.internal.measurement.zzdq r0 = zze
            java.lang.String r1 = r7.zzg
            boolean r0 = r0.zza(r1)
            java.lang.String r1 = "Attempt to access PhenotypeFlag not via codegen. All new PhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error."
            com.google.android.gms.internal.measurement.zzeb.zzb((boolean) r0, (java.lang.Object) r1)
        L_0x0011:
            java.util.concurrent.atomic.AtomicInteger r0 = zzi
            int r0 = r0.get()
            int r1 = r7.zzj
            if (r1 >= r0) goto L_0x0115
            monitor-enter(r7)
            int r1 = r7.zzj     // Catch:{ all -> 0x0112 }
            if (r1 >= r0) goto L_0x0110
            com.google.android.gms.internal.measurement.zzdp r1 = zzb     // Catch:{ all -> 0x0112 }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0028
            r4 = 1
            goto L_0x0029
        L_0x0028:
            r4 = 0
        L_0x0029:
            java.lang.String r5 = "Must call PhenotypeFlag.init() first"
            com.google.android.gms.internal.measurement.zzeb.zzb((boolean) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x0112 }
            android.content.Context r4 = r1.zza()     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzcy r4 = com.google.android.gms.internal.measurement.zzcy.zza((android.content.Context) r4)     // Catch:{ all -> 0x0112 }
            java.lang.String r5 = "gms:phenotype:phenotype_flag:debug_bypass_phenotype"
            java.lang.Object r4 = r4.zza((java.lang.String) r5)     // Catch:{ all -> 0x0112 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x0112 }
            if (r4 == 0) goto L_0x004d
            java.util.regex.Pattern r5 = com.google.android.gms.internal.measurement.zzcp.zzb     // Catch:{ all -> 0x0112 }
            java.util.regex.Matcher r4 = r5.matcher(r4)     // Catch:{ all -> 0x0112 }
            boolean r4 = r4.matches()     // Catch:{ all -> 0x0112 }
            if (r4 == 0) goto L_0x004d
            goto L_0x004e
        L_0x004d:
            r2 = 0
        L_0x004e:
            r3 = 0
            if (r2 != 0) goto L_0x0091
            com.google.android.gms.internal.measurement.zzdm r2 = r7.zzf     // Catch:{ all -> 0x0112 }
            android.net.Uri r2 = r2.zza     // Catch:{ all -> 0x0112 }
            if (r2 == 0) goto L_0x0078
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzdm r4 = r7.zzf     // Catch:{ all -> 0x0112 }
            android.net.Uri r4 = r4.zza     // Catch:{ all -> 0x0112 }
            boolean r2 = com.google.android.gms.internal.measurement.zzdf.zza(r2, r4)     // Catch:{ all -> 0x0112 }
            if (r2 == 0) goto L_0x0076
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0112 }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzdm r4 = r7.zzf     // Catch:{ all -> 0x0112 }
            android.net.Uri r4 = r4.zza     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzct r2 = com.google.android.gms.internal.measurement.zzct.zza(r2, r4)     // Catch:{ all -> 0x0112 }
            goto L_0x0080
        L_0x0076:
            r2 = r3
            goto L_0x0080
        L_0x0078:
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzdo r2 = com.google.android.gms.internal.measurement.zzdo.zza((android.content.Context) r2, (java.lang.String) r3)     // Catch:{ all -> 0x0112 }
        L_0x0080:
            if (r2 == 0) goto L_0x00ba
            java.lang.String r4 = r7.zzb()     // Catch:{ all -> 0x0112 }
            java.lang.Object r2 = r2.zza(r4)     // Catch:{ all -> 0x0112 }
            if (r2 == 0) goto L_0x00ba
            java.lang.Object r2 = r7.zza((java.lang.Object) r2)     // Catch:{ all -> 0x0112 }
            goto L_0x00bb
        L_0x0091:
            java.lang.String r2 = "PhenotypeFlag"
            r4 = 3
            boolean r2 = android.util.Log.isLoggable(r2, r4)     // Catch:{ all -> 0x0112 }
            if (r2 == 0) goto L_0x00ba
            java.lang.String r2 = "PhenotypeFlag"
            java.lang.String r4 = "Bypass reading Phenotype values for flag: "
            java.lang.String r5 = r7.zzb()     // Catch:{ all -> 0x0112 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0112 }
            int r6 = r5.length()     // Catch:{ all -> 0x0112 }
            if (r6 == 0) goto L_0x00b1
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x0112 }
            goto L_0x00b7
        L_0x00b1:
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x0112 }
            r5.<init>(r4)     // Catch:{ all -> 0x0112 }
            r4 = r5
        L_0x00b7:
            android.util.Log.d(r2, r4)     // Catch:{ all -> 0x0112 }
        L_0x00ba:
            r2 = r3
        L_0x00bb:
            if (r2 == 0) goto L_0x00be
            goto L_0x00df
        L_0x00be:
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzcy r2 = com.google.android.gms.internal.measurement.zzcy.zza((android.content.Context) r2)     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzdm r4 = r7.zzf     // Catch:{ all -> 0x0112 }
            java.lang.String r4 = r4.zzb     // Catch:{ all -> 0x0112 }
            java.lang.String r4 = r7.zza((java.lang.String) r4)     // Catch:{ all -> 0x0112 }
            java.lang.Object r2 = r2.zza(r4)     // Catch:{ all -> 0x0112 }
            if (r2 == 0) goto L_0x00d9
            java.lang.Object r2 = r7.zza((java.lang.Object) r2)     // Catch:{ all -> 0x0112 }
            goto L_0x00da
        L_0x00d9:
            r2 = r3
        L_0x00da:
            if (r2 == 0) goto L_0x00dd
            goto L_0x00df
        L_0x00dd:
            T r2 = r7.zzh     // Catch:{ all -> 0x0112 }
        L_0x00df:
            com.google.android.gms.internal.measurement.zzec r1 = r1.zzb()     // Catch:{ all -> 0x0112 }
            java.lang.Object r1 = r1.zza()     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzdy r1 = (com.google.android.gms.internal.measurement.zzdy) r1     // Catch:{ all -> 0x0112 }
            boolean r4 = r1.zza()     // Catch:{ all -> 0x0112 }
            if (r4 == 0) goto L_0x010c
            java.lang.Object r1 = r1.zzb()     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzdd r1 = (com.google.android.gms.internal.measurement.zzdd) r1     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzdm r2 = r7.zzf     // Catch:{ all -> 0x0112 }
            android.net.Uri r2 = r2.zza     // Catch:{ all -> 0x0112 }
            com.google.android.gms.internal.measurement.zzdm r4 = r7.zzf     // Catch:{ all -> 0x0112 }
            java.lang.String r4 = r4.zzc     // Catch:{ all -> 0x0112 }
            java.lang.String r5 = r7.zzg     // Catch:{ all -> 0x0112 }
            java.lang.String r1 = r1.zza(r2, r3, r4, r5)     // Catch:{ all -> 0x0112 }
            if (r1 != 0) goto L_0x0108
            T r2 = r7.zzh     // Catch:{ all -> 0x0112 }
            goto L_0x010c
        L_0x0108:
            java.lang.Object r2 = r7.zza((java.lang.Object) r1)     // Catch:{ all -> 0x0112 }
        L_0x010c:
            r7.zzk = r2     // Catch:{ all -> 0x0112 }
            r7.zzj = r0     // Catch:{ all -> 0x0112 }
        L_0x0110:
            monitor-exit(r7)     // Catch:{ all -> 0x0112 }
            goto L_0x0115
        L_0x0112:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0112 }
            throw r0
        L_0x0115:
            T r0 = r7.zzk
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzdh.zzc():java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static zzdh<Long> zzb(zzdm zzdm, String str, long j, boolean z) {
        return new zzdi(zzdm, str, Long.valueOf(j), true);
    }

    /* access modifiers changed from: private */
    public static zzdh<Boolean> zzb(zzdm zzdm, String str, boolean z, boolean z2) {
        return new zzdl(zzdm, str, Boolean.valueOf(z), true);
    }

    /* access modifiers changed from: private */
    public static zzdh<Double> zzb(zzdm zzdm, String str, double d, boolean z) {
        return new zzdk(zzdm, str, Double.valueOf(-3.0d), true);
    }

    /* access modifiers changed from: private */
    public static zzdh<String> zzb(zzdm zzdm, String str, String str2, boolean z) {
        return new zzdn(zzdm, str, str2, true);
    }

    static final /* synthetic */ zzdy zzb(Context context) {
        new zzdc();
        return zzdc.zza(context);
    }

    /* synthetic */ zzdh(zzdm zzdm, String str, Object obj, boolean z, zzdi zzdi) {
        this(zzdm, str, obj, z);
    }
}
