package com.google.android.gms.internal.ads;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.ads.zzcf;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzex {
    private static final String TAG = zzex.class.getSimpleName();
    protected Context zzvr;
    private volatile boolean zzxj;
    private zzev zzyt;
    private ExecutorService zzyu;
    private DexClassLoader zzyv;
    private zzei zzyw;
    private byte[] zzyx;
    private volatile AdvertisingIdClient zzyy = null;
    private Future zzyz;
    private boolean zzza;
    /* access modifiers changed from: private */
    public volatile zzcf.zza zzzb;
    private Future zzzc;
    private zzdu zzzd;
    private boolean zzze;
    private boolean zzzf;
    private Map<Pair<String, String>, zzgk> zzzg;
    private boolean zzzh;

    /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|(1:4)|5|6|7|8|(1:10)(1:11)|12|(1:14)(1:15)|16|17|18|(2:20|(1:22)(2:23|24))|25|26|27|28|29|(2:31|(1:33)(2:34|35))|36|(1:38)|39|40|41|42|43|44|45|66) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004d */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056 A[Catch:{ zzeh -> 0x012f, zzeu -> 0x0136 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0087 A[Catch:{ all -> 0x00fe, FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b2 A[Catch:{ all -> 0x00fe, FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzex zza(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12) {
        /*
            java.lang.String r0 = "%s/%s.dex"
            com.google.android.gms.internal.ads.zzex r1 = new com.google.android.gms.internal.ads.zzex
            r1.<init>(r9)
            com.google.android.gms.internal.ads.zzfa r9 = new com.google.android.gms.internal.ads.zzfa     // Catch:{ zzeu -> 0x0136 }
            r9.<init>()     // Catch:{ zzeu -> 0x0136 }
            java.util.concurrent.ExecutorService r9 = java.util.concurrent.Executors.newCachedThreadPool(r9)     // Catch:{ zzeu -> 0x0136 }
            r1.zzyu = r9     // Catch:{ zzeu -> 0x0136 }
            r1.zzxj = r12     // Catch:{ zzeu -> 0x0136 }
            if (r12 == 0) goto L_0x0023
            java.util.concurrent.ExecutorService r9 = r1.zzyu     // Catch:{ zzeu -> 0x0136 }
            com.google.android.gms.internal.ads.zzez r12 = new com.google.android.gms.internal.ads.zzez     // Catch:{ zzeu -> 0x0136 }
            r12.<init>(r1)     // Catch:{ zzeu -> 0x0136 }
            java.util.concurrent.Future r9 = r9.submit(r12)     // Catch:{ zzeu -> 0x0136 }
            r1.zzyz = r9     // Catch:{ zzeu -> 0x0136 }
        L_0x0023:
            java.util.concurrent.ExecutorService r9 = r1.zzyu     // Catch:{ zzeu -> 0x0136 }
            com.google.android.gms.internal.ads.zzfb r12 = new com.google.android.gms.internal.ads.zzfb     // Catch:{ zzeu -> 0x0136 }
            r12.<init>(r1)     // Catch:{ zzeu -> 0x0136 }
            r9.execute(r12)     // Catch:{ zzeu -> 0x0136 }
            r9 = 1
            r12 = 0
            com.google.android.gms.common.GoogleApiAvailabilityLight r2 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()     // Catch:{ all -> 0x004d }
            android.content.Context r3 = r1.zzvr     // Catch:{ all -> 0x004d }
            int r3 = r2.getApkVersion(r3)     // Catch:{ all -> 0x004d }
            if (r3 <= 0) goto L_0x003d
            r3 = 1
            goto L_0x003e
        L_0x003d:
            r3 = 0
        L_0x003e:
            r1.zzze = r3     // Catch:{ all -> 0x004d }
            android.content.Context r3 = r1.zzvr     // Catch:{ all -> 0x004d }
            int r2 = r2.isGooglePlayServicesAvailable(r3)     // Catch:{ all -> 0x004d }
            if (r2 != 0) goto L_0x004a
            r2 = 1
            goto L_0x004b
        L_0x004a:
            r2 = 0
        L_0x004b:
            r1.zzzf = r2     // Catch:{ all -> 0x004d }
        L_0x004d:
            r1.zza((int) r12, (boolean) r9)     // Catch:{ zzeu -> 0x0136 }
            boolean r2 = com.google.android.gms.internal.ads.zzfg.isMainThread()     // Catch:{ zzeu -> 0x0136 }
            if (r2 == 0) goto L_0x0071
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r2 = com.google.android.gms.internal.ads.zzabb.zzcrf     // Catch:{ zzeu -> 0x0136 }
            com.google.android.gms.internal.ads.zzaax r3 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ zzeu -> 0x0136 }
            java.lang.Object r2 = r3.zzd(r2)     // Catch:{ zzeu -> 0x0136 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ zzeu -> 0x0136 }
            boolean r2 = r2.booleanValue()     // Catch:{ zzeu -> 0x0136 }
            if (r2 != 0) goto L_0x0069
            goto L_0x0071
        L_0x0069:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ zzeu -> 0x0136 }
            java.lang.String r10 = "Task Context initialization must not be called from the UI thread."
            r9.<init>(r10)     // Catch:{ zzeu -> 0x0136 }
            throw r9     // Catch:{ zzeu -> 0x0136 }
        L_0x0071:
            com.google.android.gms.internal.ads.zzei r2 = new com.google.android.gms.internal.ads.zzei     // Catch:{ zzeu -> 0x0136 }
            r3 = 0
            r2.<init>(r3)     // Catch:{ zzeu -> 0x0136 }
            r1.zzyw = r2     // Catch:{ zzeu -> 0x0136 }
            byte[] r10 = r2.zzap(r10)     // Catch:{ zzeh -> 0x012f }
            r1.zzyx = r10     // Catch:{ zzeh -> 0x012f }
            android.content.Context r10 = r1.zzvr     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            java.io.File r10 = r10.getCacheDir()     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            if (r10 != 0) goto L_0x0098
            android.content.Context r10 = r1.zzvr     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            java.lang.String r2 = "dex"
            java.io.File r10 = r10.getDir(r2, r12)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            if (r10 == 0) goto L_0x0092
            goto L_0x0098
        L_0x0092:
            com.google.android.gms.internal.ads.zzeu r9 = new com.google.android.gms.internal.ads.zzeu     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r9.<init>()     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            throw r9     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
        L_0x0098:
            java.lang.String r2 = "1588462714860"
            java.io.File r4 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            java.lang.String r5 = "%s/%s.jar"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r7[r12] = r10     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r7[r9] = r2     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            java.lang.String r5 = java.lang.String.format(r5, r7)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            boolean r5 = r4.exists()     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            if (r5 != 0) goto L_0x00c9
            com.google.android.gms.internal.ads.zzei r5 = r1.zzyw     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            byte[] r7 = r1.zzyx     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            byte[] r11 = r5.zza(r7, r11)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r4.createNewFile()     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r5.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            int r7 = r11.length     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r5.write(r11, r12, r7)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r5.close()     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
        L_0x00c9:
            r1.zzb((java.io.File) r10, (java.lang.String) r2)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            dalvik.system.DexClassLoader r11 = new dalvik.system.DexClassLoader     // Catch:{ all -> 0x00fe }
            java.lang.String r5 = r4.getAbsolutePath()     // Catch:{ all -> 0x00fe }
            java.lang.String r7 = r10.getAbsolutePath()     // Catch:{ all -> 0x00fe }
            android.content.Context r8 = r1.zzvr     // Catch:{ all -> 0x00fe }
            java.lang.ClassLoader r8 = r8.getClassLoader()     // Catch:{ all -> 0x00fe }
            r11.<init>(r5, r7, r3, r8)     // Catch:{ all -> 0x00fe }
            r1.zzyv = r11     // Catch:{ all -> 0x00fe }
            zzc(r4)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r1.zza((java.io.File) r10, (java.lang.String) r2)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            java.lang.Object[] r11 = new java.lang.Object[r6]     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r11[r12] = r10     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r11[r9] = r2     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            java.lang.String r10 = java.lang.String.format(r0, r11)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            zzaq(r10)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            com.google.android.gms.internal.ads.zzdu r10 = new com.google.android.gms.internal.ads.zzdu     // Catch:{ zzeu -> 0x0136 }
            r10.<init>(r1)     // Catch:{ zzeu -> 0x0136 }
            r1.zzzd = r10     // Catch:{ zzeu -> 0x0136 }
            r1.zzzh = r9     // Catch:{ zzeu -> 0x0136 }
            goto L_0x0136
        L_0x00fe:
            r11 = move-exception
            zzc(r4)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r1.zza((java.io.File) r10, (java.lang.String) r2)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r3[r12] = r10     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            r3[r9] = r2     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            java.lang.String r9 = java.lang.String.format(r0, r3)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            zzaq(r9)     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
            throw r11     // Catch:{ FileNotFoundException -> 0x0128, IOException -> 0x0121, zzeh -> 0x011a, NullPointerException -> 0x0113 }
        L_0x0113:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzeu r10 = new com.google.android.gms.internal.ads.zzeu     // Catch:{ zzeu -> 0x0136 }
            r10.<init>(r9)     // Catch:{ zzeu -> 0x0136 }
            throw r10     // Catch:{ zzeu -> 0x0136 }
        L_0x011a:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzeu r10 = new com.google.android.gms.internal.ads.zzeu     // Catch:{ zzeu -> 0x0136 }
            r10.<init>(r9)     // Catch:{ zzeu -> 0x0136 }
            throw r10     // Catch:{ zzeu -> 0x0136 }
        L_0x0121:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzeu r10 = new com.google.android.gms.internal.ads.zzeu     // Catch:{ zzeu -> 0x0136 }
            r10.<init>(r9)     // Catch:{ zzeu -> 0x0136 }
            throw r10     // Catch:{ zzeu -> 0x0136 }
        L_0x0128:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzeu r10 = new com.google.android.gms.internal.ads.zzeu     // Catch:{ zzeu -> 0x0136 }
            r10.<init>(r9)     // Catch:{ zzeu -> 0x0136 }
            throw r10     // Catch:{ zzeu -> 0x0136 }
        L_0x012f:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzeu r10 = new com.google.android.gms.internal.ads.zzeu     // Catch:{ zzeu -> 0x0136 }
            r10.<init>(r9)     // Catch:{ zzeu -> 0x0136 }
            throw r10     // Catch:{ zzeu -> 0x0136 }
        L_0x0136:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzex.zza(android.content.Context, java.lang.String, java.lang.String, boolean):com.google.android.gms.internal.ads.zzex");
    }

    public final Context getContext() {
        return this.zzvr;
    }

    public final boolean isInitialized() {
        return this.zzzh;
    }

    public final ExecutorService zzcg() {
        return this.zzyu;
    }

    public final DexClassLoader zzch() {
        return this.zzyv;
    }

    public final zzei zzci() {
        return this.zzyw;
    }

    public final byte[] zzcj() {
        return this.zzyx;
    }

    public final boolean zzck() {
        return this.zzze;
    }

    public final zzdu zzcl() {
        return this.zzzd;
    }

    public final boolean zzcm() {
        return this.zzzf;
    }

    public final boolean zzcb() {
        return this.zzyt.zzcb();
    }

    /* access modifiers changed from: package-private */
    public final zzev zzcn() {
        return this.zzyt;
    }

    public final zzcf.zza zzco() {
        return this.zzzb;
    }

    public final Future zzcp() {
        return this.zzzc;
    }

    private zzex(Context context) {
        boolean z = false;
        this.zzxj = false;
        this.zzyz = null;
        this.zzzb = null;
        this.zzzc = null;
        this.zzze = false;
        this.zzzf = false;
        this.zzzh = false;
        Context applicationContext = context.getApplicationContext();
        z = applicationContext != null ? true : z;
        this.zzza = z;
        this.zzvr = z ? applicationContext : context;
        this.zzzg = new HashMap();
        if (this.zzyt == null) {
            this.zzyt = new zzev(this.zzvr);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:20|21|22|23|24|25|26|27|28|30) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x00c0 */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d4 A[SYNTHETIC, Splitter:B:42:0x00d4] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00db A[SYNTHETIC, Splitter:B:46:0x00db] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e5 A[SYNTHETIC, Splitter:B:54:0x00e5] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ec A[SYNTHETIC, Splitter:B:58:0x00ec] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.io.File r10, java.lang.String r11) {
        /*
            r9 = this;
            java.lang.String r0 = "test"
            java.io.File r1 = new java.io.File
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r4 = 0
            r3[r4] = r10
            r5 = 1
            r3[r5] = r11
            java.lang.String r6 = "%s/%s.tmp"
            java.lang.String r3 = java.lang.String.format(r6, r3)
            r1.<init>(r3)
            boolean r3 = r1.exists()
            if (r3 == 0) goto L_0x001d
            return
        L_0x001d:
            java.io.File r3 = new java.io.File
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r4] = r10
            r2[r5] = r11
            java.lang.String r10 = "%s/%s.dex"
            java.lang.String r10 = java.lang.String.format(r10, r2)
            r3.<init>(r10)
            boolean r10 = r3.exists()
            if (r10 != 0) goto L_0x0035
            return
        L_0x0035:
            long r5 = r3.length()
            r7 = 0
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 > 0) goto L_0x0040
            return
        L_0x0040:
            int r10 = (int) r5
            byte[] r10 = new byte[r10]
            r2 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00e2, all -> 0x00d0 }
            r5.<init>(r3)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00e2, all -> 0x00d0 }
            int r6 = r5.read(r10)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            if (r6 > 0) goto L_0x0056
            r5.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            zzc(r3)
            return
        L_0x0056:
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            r6.print(r0)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            r6.print(r0)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            r6.print(r0)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzcf$zzc$zza r0 = com.google.android.gms.internal.ads.zzcf.zzc.zzbe()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            byte[] r6 = r6.getBytes()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzeip r6 = com.google.android.gms.internal.ads.zzeip.zzu(r6)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzcf$zzc$zza r0 = r0.zzd(r6)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            byte[] r11 = r11.getBytes()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzeip r11 = com.google.android.gms.internal.ads.zzeip.zzu(r11)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzcf$zzc$zza r11 = r0.zzc(r11)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzei r0 = r9.zzyw     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            byte[] r6 = r9.zzyx     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            java.lang.String r10 = r0.zzb(r6, r10)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            byte[] r10 = r10.getBytes()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzeip r0 = com.google.android.gms.internal.ads.zzeip.zzu(r10)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzcf$zzc$zza r0 = r11.zza(r0)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            byte[] r10 = com.google.android.gms.internal.ads.zzcx.zzb(r10)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzeip r10 = com.google.android.gms.internal.ads.zzeip.zzu(r10)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            r0.zzb(r10)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            r1.createNewFile()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            r10.<init>(r1)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00cd, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzelj r11 = r11.zzbgt()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ce, all -> 0x00c7 }
            com.google.android.gms.internal.ads.zzejz r11 = (com.google.android.gms.internal.ads.zzejz) r11     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ce, all -> 0x00c7 }
            com.google.android.gms.internal.ads.zzcf$zzc r11 = (com.google.android.gms.internal.ads.zzcf.zzc) r11     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ce, all -> 0x00c7 }
            byte[] r11 = r11.toByteArray()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ce, all -> 0x00c7 }
            int r0 = r11.length     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ce, all -> 0x00c7 }
            r10.write(r11, r4, r0)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ce, all -> 0x00c7 }
            r10.close()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ce, all -> 0x00c7 }
            r5.close()     // Catch:{ IOException -> 0x00c0 }
        L_0x00c0:
            r10.close()     // Catch:{ IOException -> 0x00c3 }
        L_0x00c3:
            zzc(r3)
            return
        L_0x00c7:
            r11 = move-exception
            goto L_0x00cb
        L_0x00c9:
            r11 = move-exception
            r10 = r2
        L_0x00cb:
            r2 = r5
            goto L_0x00d2
        L_0x00cd:
            r10 = r2
        L_0x00ce:
            r2 = r5
            goto L_0x00e3
        L_0x00d0:
            r11 = move-exception
            r10 = r2
        L_0x00d2:
            if (r2 == 0) goto L_0x00d9
            r2.close()     // Catch:{ IOException -> 0x00d8 }
            goto L_0x00d9
        L_0x00d8:
        L_0x00d9:
            if (r10 == 0) goto L_0x00de
            r10.close()     // Catch:{ IOException -> 0x00de }
        L_0x00de:
            zzc(r3)
            throw r11
        L_0x00e2:
            r10 = r2
        L_0x00e3:
            if (r2 == 0) goto L_0x00ea
            r2.close()     // Catch:{ IOException -> 0x00e9 }
            goto L_0x00ea
        L_0x00e9:
        L_0x00ea:
            if (r10 == 0) goto L_0x00ef
            r10.close()     // Catch:{ IOException -> 0x00ef }
        L_0x00ef:
            zzc(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzex.zza(java.io.File, java.lang.String):void");
    }

    private static void zzaq(String str) {
        zzc(new File(str));
    }

    private static void zzc(File file) {
        if (!file.exists()) {
            Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
            return;
        }
        file.delete();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00cc */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e4 A[SYNTHETIC, Splitter:B:55:0x00e4] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00eb A[SYNTHETIC, Splitter:B:59:0x00eb] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00f2 A[SYNTHETIC, Splitter:B:66:0x00f2] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f9 A[SYNTHETIC, Splitter:B:70:0x00f9] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzb(java.io.File r10, java.lang.String r11) {
        /*
            r9 = this;
            java.io.File r0 = new java.io.File
            r1 = 2
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r3 = 0
            r2[r3] = r10
            r4 = 1
            r2[r4] = r11
            java.lang.String r5 = "%s/%s.tmp"
            java.lang.String r2 = java.lang.String.format(r5, r2)
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 != 0) goto L_0x001b
            return r3
        L_0x001b:
            java.io.File r2 = new java.io.File
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r3] = r10
            r1[r4] = r11
            java.lang.String r10 = "%s/%s.dex"
            java.lang.String r10 = java.lang.String.format(r10, r1)
            r2.<init>(r10)
            boolean r10 = r2.exists()
            if (r10 == 0) goto L_0x0033
            return r3
        L_0x0033:
            r10 = 0
            long r5 = r0.length()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ef, all -> 0x00e0 }
            r7 = 0
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 > 0) goto L_0x0042
            zzc(r0)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ef, all -> 0x00e0 }
            return r3
        L_0x0042:
            int r1 = (int) r5     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ef, all -> 0x00e0 }
            byte[] r1 = new byte[r1]     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ef, all -> 0x00e0 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ef, all -> 0x00e0 }
            r5.<init>(r0)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00ef, all -> 0x00e0 }
            int r6 = r5.read(r1)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            if (r6 > 0) goto L_0x005e
            java.lang.String r11 = TAG     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            java.lang.String r1 = "Cannot read the cache data."
            android.util.Log.d(r11, r1)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            zzc(r0)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            r5.close()     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            return r3
        L_0x005e:
            com.google.android.gms.internal.ads.zzejm r6 = com.google.android.gms.internal.ads.zzejm.zzbfu()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            com.google.android.gms.internal.ads.zzcf$zzc r1 = com.google.android.gms.internal.ads.zzcf.zzc.zzb((byte[]) r1, (com.google.android.gms.internal.ads.zzejm) r6)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            java.lang.String r6 = new java.lang.String     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            com.google.android.gms.internal.ads.zzeip r7 = r1.zzbc()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            byte[] r7 = r7.toByteArray()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            r6.<init>(r7)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            boolean r11 = r11.equals(r6)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            if (r11 == 0) goto L_0x00d2
            com.google.android.gms.internal.ads.zzeip r11 = r1.zzbb()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            byte[] r11 = r11.toByteArray()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            com.google.android.gms.internal.ads.zzeip r6 = r1.zzba()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            byte[] r6 = r6.toByteArray()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            byte[] r6 = com.google.android.gms.internal.ads.zzcx.zzb(r6)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            boolean r11 = java.util.Arrays.equals(r11, r6)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            if (r11 == 0) goto L_0x00d2
            com.google.android.gms.internal.ads.zzeip r11 = r1.zzbd()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            byte[] r11 = r11.toByteArray()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            byte[] r6 = r6.getBytes()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            boolean r11 = java.util.Arrays.equals(r11, r6)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            if (r11 != 0) goto L_0x00a8
            goto L_0x00d2
        L_0x00a8:
            com.google.android.gms.internal.ads.zzei r11 = r9.zzyw     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            byte[] r0 = r9.zzyx     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            java.lang.String r6 = new java.lang.String     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            com.google.android.gms.internal.ads.zzeip r1 = r1.zzba()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            byte[] r1 = r1.toByteArray()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            r6.<init>(r1)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            byte[] r11 = r11.zza(r0, r6)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            r2.createNewFile()     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            r0.<init>(r2)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            int r10 = r11.length     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00d0 }
            r0.write(r11, r3, r10)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00d0 }
            r5.close()     // Catch:{ IOException -> 0x00cc }
        L_0x00cc:
            r0.close()     // Catch:{ IOException -> 0x00cf }
        L_0x00cf:
            return r4
        L_0x00d0:
            r11 = move-exception
            goto L_0x00db
        L_0x00d2:
            zzc(r0)     // Catch:{ zzeh | IOException | NoSuchAlgorithmException -> 0x00dd, all -> 0x00d9 }
            r5.close()     // Catch:{ IOException -> 0x00d8 }
        L_0x00d8:
            return r3
        L_0x00d9:
            r11 = move-exception
            r0 = r10
        L_0x00db:
            r10 = r5
            goto L_0x00e2
        L_0x00dd:
            r0 = r10
        L_0x00de:
            r10 = r5
            goto L_0x00f0
        L_0x00e0:
            r11 = move-exception
            r0 = r10
        L_0x00e2:
            if (r10 == 0) goto L_0x00e9
            r10.close()     // Catch:{ IOException -> 0x00e8 }
            goto L_0x00e9
        L_0x00e8:
        L_0x00e9:
            if (r0 == 0) goto L_0x00ee
            r0.close()     // Catch:{ IOException -> 0x00ee }
        L_0x00ee:
            throw r11
        L_0x00ef:
            r0 = r10
        L_0x00f0:
            if (r10 == 0) goto L_0x00f7
            r10.close()     // Catch:{ IOException -> 0x00f6 }
            goto L_0x00f7
        L_0x00f6:
        L_0x00f7:
            if (r0 == 0) goto L_0x00fc
            r0.close()     // Catch:{ IOException -> 0x00fc }
        L_0x00fc:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzex.zzb(java.io.File, java.lang.String):boolean");
    }

    public final boolean zza(String str, String str2, Class<?>... clsArr) {
        if (this.zzzg.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzzg.put(new Pair(str, str2), new zzgk(this, str, str2, clsArr));
        return true;
    }

    public final Method zza(String str, String str2) {
        zzgk zzgk = this.zzzg.get(new Pair(str, str2));
        if (zzgk == null) {
            return null;
        }
        return zzgk.zzdb();
    }

    /* access modifiers changed from: private */
    public final void zzcq() {
        try {
            if (this.zzyy == null && this.zzza) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzvr);
                advertisingIdClient.start();
                this.zzyy = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzyy = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, boolean z) {
        if (this.zzzf) {
            Future<?> submit = this.zzyu.submit(new zzfc(this, i, z));
            if (i == 0) {
                this.zzzc = submit;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final zzcf.zza zzb(int i, boolean z) {
        if (i > 0 && z) {
            try {
                Thread.sleep((long) (i * 1000));
            } catch (InterruptedException unused) {
            }
        }
        return zzcr();
    }

    /* access modifiers changed from: private */
    public static boolean zza(int i, zzcf.zza zza) {
        if (i >= 4) {
            return false;
        }
        if (zza != null && zza.zzaj() && !zza.zzaf().equals("0000000000000000000000000000000000000000000000000000000000000000") && zza.zzao() && zza.zzap().zzbg() && zza.zzap().zzbh() != -2) {
            return false;
        }
        return true;
    }

    private final zzcf.zza zzcr() {
        try {
            return zzdtj.zzj(this.zzvr, this.zzvr.getPackageName(), Integer.toString(this.zzvr.getPackageManager().getPackageInfo(this.zzvr.getPackageName(), 0).versionCode));
        } catch (Throwable unused) {
            return null;
        }
    }

    public final AdvertisingIdClient zzcs() {
        if (!this.zzxj) {
            return null;
        }
        if (this.zzyy != null) {
            return this.zzyy;
        }
        Future future = this.zzyz;
        if (future != null) {
            try {
                future.get(2000, TimeUnit.MILLISECONDS);
                this.zzyz = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.zzyz.cancel(true);
            }
        }
        return this.zzyy;
    }

    public final int zzbu() {
        if (this.zzzd != null) {
            return zzdu.zzbu();
        }
        return Integer.MIN_VALUE;
    }
}
