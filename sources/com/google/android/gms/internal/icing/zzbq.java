package com.google.android.gms.internal.icing;

import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public abstract class zzbq<T> {
    private static Context zzcs = null;
    private static final Object zzcz = new Object();
    private static boolean zzda = false;
    private static zzcc<zzbx<zzbm>> zzdb;
    private static final AtomicInteger zzde = new AtomicInteger();
    private final String name;
    private final zzbu zzdc;
    private final T zzdd;
    private volatile int zzdf;
    private volatile T zzdg;

    public static void zzg(Context context) {
        synchronized (zzcz) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzcs != context) {
                zzbc.zzp();
                zzbt.zzp();
                zzbh.zzs();
                zzde.incrementAndGet();
                zzcs = context;
                zzdb = zzcb.zza(zzbp.zzcy);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public abstract T zza(Object obj);

    static void zzt() {
        zzde.incrementAndGet();
    }

    private zzbq(zzbu zzbu, String str, T t) {
        this.zzdf = -1;
        if (zzbu.zzdl != null) {
            this.zzdc = zzbu;
            this.name = str;
            this.zzdd = t;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    private final String zzm(String str) {
        if (str != null && str.isEmpty()) {
            return this.name;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(this.name);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String zzu() {
        return zzm(this.zzdc.zzdn);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T get() {
        /*
            r6 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = zzde
            int r0 = r0.get()
            int r1 = r6.zzdf
            if (r1 >= r0) goto L_0x00f8
            monitor-enter(r6)
            int r1 = r6.zzdf     // Catch:{ all -> 0x00f5 }
            if (r1 >= r0) goto L_0x00f3
            android.content.Context r1 = zzcs     // Catch:{ all -> 0x00f5 }
            if (r1 == 0) goto L_0x00eb
            android.content.Context r1 = zzcs     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbh r1 = com.google.android.gms.internal.icing.zzbh.zzc(r1)     // Catch:{ all -> 0x00f5 }
            java.lang.String r2 = "gms:phenotype:phenotype_flag:debug_bypass_phenotype"
            java.lang.Object r1 = r1.zzi(r2)     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x00f5 }
            if (r1 == 0) goto L_0x0031
            java.util.regex.Pattern r2 = com.google.android.gms.internal.icing.zzax.zzbt     // Catch:{ all -> 0x00f5 }
            java.util.regex.Matcher r1 = r2.matcher(r1)     // Catch:{ all -> 0x00f5 }
            boolean r1 = r1.matches()     // Catch:{ all -> 0x00f5 }
            if (r1 == 0) goto L_0x0031
            r1 = 1
            goto L_0x0032
        L_0x0031:
            r1 = 0
        L_0x0032:
            r2 = 0
            if (r1 != 0) goto L_0x006f
            com.google.android.gms.internal.icing.zzbu r1 = r6.zzdc     // Catch:{ all -> 0x00f5 }
            android.net.Uri r1 = r1.zzdl     // Catch:{ all -> 0x00f5 }
            if (r1 == 0) goto L_0x0058
            android.content.Context r1 = zzcs     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbu r3 = r6.zzdc     // Catch:{ all -> 0x00f5 }
            android.net.Uri r3 = r3.zzdl     // Catch:{ all -> 0x00f5 }
            boolean r1 = com.google.android.gms.internal.icing.zzbo.zza(r1, r3)     // Catch:{ all -> 0x00f5 }
            if (r1 == 0) goto L_0x0056
            android.content.Context r1 = zzcs     // Catch:{ all -> 0x00f5 }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbu r3 = r6.zzdc     // Catch:{ all -> 0x00f5 }
            android.net.Uri r3 = r3.zzdl     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbc r1 = com.google.android.gms.internal.icing.zzbc.zza(r1, r3)     // Catch:{ all -> 0x00f5 }
            goto L_0x005e
        L_0x0056:
            r1 = r2
            goto L_0x005e
        L_0x0058:
            android.content.Context r1 = zzcs     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbt r1 = com.google.android.gms.internal.icing.zzbt.zza((android.content.Context) r1, (java.lang.String) r2)     // Catch:{ all -> 0x00f5 }
        L_0x005e:
            if (r1 == 0) goto L_0x0098
            java.lang.String r3 = r6.zzu()     // Catch:{ all -> 0x00f5 }
            java.lang.Object r1 = r1.zzi(r3)     // Catch:{ all -> 0x00f5 }
            if (r1 == 0) goto L_0x0098
            java.lang.Object r1 = r6.zza(r1)     // Catch:{ all -> 0x00f5 }
            goto L_0x0099
        L_0x006f:
            java.lang.String r1 = "PhenotypeFlag"
            r3 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r3)     // Catch:{ all -> 0x00f5 }
            if (r1 == 0) goto L_0x0098
            java.lang.String r1 = "PhenotypeFlag"
            java.lang.String r3 = "Bypass reading Phenotype values for flag: "
            java.lang.String r4 = r6.zzu()     // Catch:{ all -> 0x00f5 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00f5 }
            int r5 = r4.length()     // Catch:{ all -> 0x00f5 }
            if (r5 == 0) goto L_0x008f
            java.lang.String r3 = r3.concat(r4)     // Catch:{ all -> 0x00f5 }
            goto L_0x0095
        L_0x008f:
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x00f5 }
            r4.<init>(r3)     // Catch:{ all -> 0x00f5 }
            r3 = r4
        L_0x0095:
            android.util.Log.d(r1, r3)     // Catch:{ all -> 0x00f5 }
        L_0x0098:
            r1 = r2
        L_0x0099:
            if (r1 == 0) goto L_0x009c
            goto L_0x00bb
        L_0x009c:
            android.content.Context r1 = zzcs     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbh r1 = com.google.android.gms.internal.icing.zzbh.zzc(r1)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbu r3 = r6.zzdc     // Catch:{ all -> 0x00f5 }
            java.lang.String r3 = r3.zzdm     // Catch:{ all -> 0x00f5 }
            java.lang.String r3 = r6.zzm(r3)     // Catch:{ all -> 0x00f5 }
            java.lang.Object r1 = r1.zzi(r3)     // Catch:{ all -> 0x00f5 }
            if (r1 == 0) goto L_0x00b5
            java.lang.Object r1 = r6.zza(r1)     // Catch:{ all -> 0x00f5 }
            goto L_0x00b6
        L_0x00b5:
            r1 = r2
        L_0x00b6:
            if (r1 == 0) goto L_0x00b9
            goto L_0x00bb
        L_0x00b9:
            T r1 = r6.zzdd     // Catch:{ all -> 0x00f5 }
        L_0x00bb:
            com.google.android.gms.internal.icing.zzcc<com.google.android.gms.internal.icing.zzbx<com.google.android.gms.internal.icing.zzbm>> r3 = zzdb     // Catch:{ all -> 0x00f5 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbx r3 = (com.google.android.gms.internal.icing.zzbx) r3     // Catch:{ all -> 0x00f5 }
            boolean r4 = r3.isPresent()     // Catch:{ all -> 0x00f5 }
            if (r4 == 0) goto L_0x00e6
            java.lang.Object r1 = r3.get()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbm r1 = (com.google.android.gms.internal.icing.zzbm) r1     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbu r3 = r6.zzdc     // Catch:{ all -> 0x00f5 }
            android.net.Uri r3 = r3.zzdl     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.icing.zzbu r4 = r6.zzdc     // Catch:{ all -> 0x00f5 }
            java.lang.String r4 = r4.zzdn     // Catch:{ all -> 0x00f5 }
            java.lang.String r5 = r6.name     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = r1.zza(r3, r2, r4, r5)     // Catch:{ all -> 0x00f5 }
            if (r1 != 0) goto L_0x00e2
            T r1 = r6.zzdd     // Catch:{ all -> 0x00f5 }
            goto L_0x00e6
        L_0x00e2:
            java.lang.Object r1 = r6.zza(r1)     // Catch:{ all -> 0x00f5 }
        L_0x00e6:
            r6.zzdg = r1     // Catch:{ all -> 0x00f5 }
            r6.zzdf = r0     // Catch:{ all -> 0x00f5 }
            goto L_0x00f3
        L_0x00eb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = "Must call PhenotypeFlag.init() first"
            r0.<init>(r1)     // Catch:{ all -> 0x00f5 }
            throw r0     // Catch:{ all -> 0x00f5 }
        L_0x00f3:
            monitor-exit(r6)     // Catch:{ all -> 0x00f5 }
            goto L_0x00f8
        L_0x00f5:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x00f5 }
            throw r0
        L_0x00f8:
            T r0 = r6.zzdg
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzbq.get():java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static zzbq<Boolean> zza(zzbu zzbu, String str, boolean z) {
        return new zzbr(zzbu, str, Boolean.valueOf(z));
    }

    static final /* synthetic */ zzbx zzv() {
        new zzbl();
        return zzbl.zzd(zzcs);
    }

    /* synthetic */ zzbq(zzbu zzbu, String str, Object obj, zzbs zzbs) {
        this(zzbu, str, obj);
    }
}
