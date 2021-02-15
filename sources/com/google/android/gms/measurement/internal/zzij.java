package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zznm;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzij extends zzg {
    protected zzig zza;
    private volatile zzig zzb;
    private zzig zzc;
    private final Map<Activity, zzig> zzd = new ConcurrentHashMap();
    private Activity zze;
    private volatile boolean zzf;
    private volatile zzig zzg;
    /* access modifiers changed from: private */
    public zzig zzh;
    private boolean zzi;
    private final Object zzj = new Object();
    private zzig zzk;
    private String zzl;

    public zzij(zzfv zzfv) {
        super(zzfv);
    }

    /* access modifiers changed from: protected */
    public final boolean zzy() {
        return false;
    }

    public final zzig zza(boolean z) {
        zzv();
        zzc();
        if (!zzs().zza(zzat.zzbu) || !z) {
            return this.zza;
        }
        zzig zzig = this.zza;
        return zzig != null ? zzig : this.zzh;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00da, code lost:
        r1 = zzq().zzw();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e4, code lost:
        if (r10 != null) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e6, code lost:
        r3 = "null";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e9, code lost:
        r3 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ea, code lost:
        if (r11 != null) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ec, code lost:
        r4 = "null";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ef, code lost:
        r4 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f0, code lost:
        r1.zza("Logging screen view with name, class", r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00f5, code lost:
        if (r8.zzb != null) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f7, code lost:
        r1 = r8.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fa, code lost:
        r1 = r8.zzb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00fc, code lost:
        r5 = r1;
        r9 = new com.google.android.gms.measurement.internal.zzig(r10, r11, zzo().zzf(), true, r19);
        r8.zzb = r9;
        r8.zzc = r5;
        r8.zzg = r9;
        zzp().zza((java.lang.Runnable) new com.google.android.gms.measurement.internal.zzii(r17, r18, r9, r5, zzl().elapsedRealtime()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x012d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.os.Bundle r18, long r19) {
        /*
            r17 = this;
            r8 = r17
            r0 = r18
            com.google.android.gms.measurement.internal.zzy r1 = r17.zzs()
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzat.zzbu
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r2)
            if (r1 != 0) goto L_0x001e
            com.google.android.gms.measurement.internal.zzer r0 = r17.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzj()
            java.lang.String r1 = "Manual screen reporting is disabled."
            r0.zza(r1)
            return
        L_0x001e:
            java.lang.Object r1 = r8.zzj
            monitor-enter(r1)
            boolean r2 = r8.zzi     // Catch:{ all -> 0x012e }
            if (r2 != 0) goto L_0x0034
            com.google.android.gms.measurement.internal.zzer r0 = r17.zzq()     // Catch:{ all -> 0x012e }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzj()     // Catch:{ all -> 0x012e }
            java.lang.String r2 = "Cannot log screen view event when the app is in the background."
            r0.zza(r2)     // Catch:{ all -> 0x012e }
            monitor-exit(r1)     // Catch:{ all -> 0x012e }
            return
        L_0x0034:
            r2 = 0
            if (r0 == 0) goto L_0x0092
            java.lang.String r2 = "screen_name"
            java.lang.String r2 = r0.getString(r2)     // Catch:{ all -> 0x012e }
            r3 = 100
            if (r2 == 0) goto L_0x0064
            int r4 = r2.length()     // Catch:{ all -> 0x012e }
            if (r4 <= 0) goto L_0x004d
            int r4 = r2.length()     // Catch:{ all -> 0x012e }
            if (r4 <= r3) goto L_0x0064
        L_0x004d:
            com.google.android.gms.measurement.internal.zzer r0 = r17.zzq()     // Catch:{ all -> 0x012e }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzj()     // Catch:{ all -> 0x012e }
            java.lang.String r3 = "Invalid screen name length for screen view. Length"
            int r2 = r2.length()     // Catch:{ all -> 0x012e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x012e }
            r0.zza(r3, r2)     // Catch:{ all -> 0x012e }
            monitor-exit(r1)     // Catch:{ all -> 0x012e }
            return
        L_0x0064:
            java.lang.String r4 = "screen_class"
            java.lang.String r4 = r0.getString(r4)     // Catch:{ all -> 0x012e }
            if (r4 == 0) goto L_0x008f
            int r5 = r4.length()     // Catch:{ all -> 0x012e }
            if (r5 <= 0) goto L_0x0078
            int r5 = r4.length()     // Catch:{ all -> 0x012e }
            if (r5 <= r3) goto L_0x008f
        L_0x0078:
            com.google.android.gms.measurement.internal.zzer r0 = r17.zzq()     // Catch:{ all -> 0x012e }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzj()     // Catch:{ all -> 0x012e }
            java.lang.String r2 = "Invalid screen class length for screen view. Length"
            int r3 = r4.length()     // Catch:{ all -> 0x012e }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x012e }
            r0.zza(r2, r3)     // Catch:{ all -> 0x012e }
            monitor-exit(r1)     // Catch:{ all -> 0x012e }
            return
        L_0x008f:
            r10 = r2
            r2 = r4
            goto L_0x0093
        L_0x0092:
            r10 = r2
        L_0x0093:
            if (r2 != 0) goto L_0x00aa
            android.app.Activity r2 = r8.zze     // Catch:{ all -> 0x012e }
            if (r2 == 0) goto L_0x00a8
            android.app.Activity r2 = r8.zze     // Catch:{ all -> 0x012e }
            java.lang.Class r2 = r2.getClass()     // Catch:{ all -> 0x012e }
            java.lang.String r2 = r2.getCanonicalName()     // Catch:{ all -> 0x012e }
            java.lang.String r2 = zza((java.lang.String) r2)     // Catch:{ all -> 0x012e }
            goto L_0x00aa
        L_0x00a8:
            java.lang.String r2 = "Activity"
        L_0x00aa:
            r11 = r2
            boolean r2 = r8.zzf     // Catch:{ all -> 0x012e }
            if (r2 == 0) goto L_0x00d9
            com.google.android.gms.measurement.internal.zzig r2 = r8.zzb     // Catch:{ all -> 0x012e }
            if (r2 == 0) goto L_0x00d9
            r2 = 0
            r8.zzf = r2     // Catch:{ all -> 0x012e }
            com.google.android.gms.measurement.internal.zzig r2 = r8.zzb     // Catch:{ all -> 0x012e }
            java.lang.String r2 = r2.zzb     // Catch:{ all -> 0x012e }
            boolean r2 = com.google.android.gms.measurement.internal.zzkw.zzc((java.lang.String) r2, (java.lang.String) r11)     // Catch:{ all -> 0x012e }
            com.google.android.gms.measurement.internal.zzig r3 = r8.zzb     // Catch:{ all -> 0x012e }
            java.lang.String r3 = r3.zza     // Catch:{ all -> 0x012e }
            boolean r3 = com.google.android.gms.measurement.internal.zzkw.zzc((java.lang.String) r3, (java.lang.String) r10)     // Catch:{ all -> 0x012e }
            if (r2 == 0) goto L_0x00d9
            if (r3 == 0) goto L_0x00d9
            com.google.android.gms.measurement.internal.zzer r0 = r17.zzq()     // Catch:{ all -> 0x012e }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzj()     // Catch:{ all -> 0x012e }
            java.lang.String r2 = "Ignoring call to log screen view event with duplicate parameters."
            r0.zza(r2)     // Catch:{ all -> 0x012e }
            monitor-exit(r1)     // Catch:{ all -> 0x012e }
            return
        L_0x00d9:
            monitor-exit(r1)     // Catch:{ all -> 0x012e }
            com.google.android.gms.measurement.internal.zzer r1 = r17.zzq()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzw()
            java.lang.String r2 = "Logging screen view with name, class"
            if (r10 != 0) goto L_0x00e9
            java.lang.String r3 = "null"
            goto L_0x00ea
        L_0x00e9:
            r3 = r10
        L_0x00ea:
            if (r11 != 0) goto L_0x00ef
            java.lang.String r4 = "null"
            goto L_0x00f0
        L_0x00ef:
            r4 = r11
        L_0x00f0:
            r1.zza(r2, r3, r4)
            com.google.android.gms.measurement.internal.zzig r1 = r8.zzb
            if (r1 != 0) goto L_0x00fa
            com.google.android.gms.measurement.internal.zzig r1 = r8.zzc
            goto L_0x00fc
        L_0x00fa:
            com.google.android.gms.measurement.internal.zzig r1 = r8.zzb
        L_0x00fc:
            r5 = r1
            com.google.android.gms.measurement.internal.zzig r4 = new com.google.android.gms.measurement.internal.zzig
            com.google.android.gms.measurement.internal.zzkw r1 = r17.zzo()
            long r12 = r1.zzf()
            r14 = 1
            r9 = r4
            r15 = r19
            r9.<init>(r10, r11, r12, r14, r15)
            r8.zzb = r4
            r8.zzc = r5
            r8.zzg = r4
            com.google.android.gms.common.util.Clock r1 = r17.zzl()
            long r6 = r1.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzfo r9 = r17.zzp()
            com.google.android.gms.measurement.internal.zzii r10 = new com.google.android.gms.measurement.internal.zzii
            r1 = r10
            r2 = r17
            r3 = r18
            r1.<init>(r2, r3, r4, r5, r6)
            r9.zza((java.lang.Runnable) r10)
            return
        L_0x012e:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x012e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzij.zza(android.os.Bundle, long):void");
    }

    /* access modifiers changed from: private */
    public final void zza(Bundle bundle, zzig zzig, zzig zzig2, long j) {
        if (bundle != null) {
            bundle.remove(FirebaseAnalytics.Param.SCREEN_NAME);
            bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        }
        zza(zzig, zzig2, j, true, zzo().zza((String) null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, (List<String>) null, true, true));
    }

    @Deprecated
    public final void zza(Activity activity, String str, String str2) {
        if (!zzs().zzh().booleanValue()) {
            zzq().zzj().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
        } else if (this.zzb == null) {
            zzq().zzj().zza("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzd.get(activity) == null) {
            zzq().zzj().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zza(activity.getClass().getCanonicalName());
            }
            boolean zzc2 = zzkw.zzc(this.zzb.zzb, str2);
            boolean zzc3 = zzkw.zzc(this.zzb.zza, str);
            if (zzc2 && zzc3) {
                zzq().zzj().zza("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                zzq().zzj().zza("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                zzq().zzw().zza("Setting current screen to name, class", str == null ? "null" : str, str2);
                zzig zzig = new zzig(str, str2, zzo().zzf());
                this.zzd.put(activity, zzig);
                zza(activity, zzig, true);
            } else {
                zzq().zzj().zza("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    public final zzig zzaa() {
        return this.zzb;
    }

    private final void zza(Activity activity, zzig zzig, boolean z) {
        zzig zzig2;
        zzig zzig3 = zzig;
        zzig zzig4 = this.zzb == null ? this.zzc : this.zzb;
        if (zzig3.zzb == null) {
            zzig2 = new zzig(zzig3.zza, activity != null ? zza(activity.getClass().getCanonicalName()) : null, zzig3.zzc, zzig3.zze, zzig3.zzf);
        } else {
            zzig2 = zzig3;
        }
        this.zzc = this.zzb;
        this.zzb = zzig2;
        zzp().zza((Runnable) new zzil(this, zzig2, zzig4, zzl().elapsedRealtime(), z));
    }

    /* access modifiers changed from: private */
    public final void zza(zzig zzig, zzig zzig2, long j, boolean z, Bundle bundle) {
        boolean z2;
        long j2;
        zzig zzig3;
        zzc();
        boolean z3 = false;
        if (zzs().zza(zzat.zzas)) {
            z2 = z && this.zza != null;
            if (z2) {
                zza(this.zza, true, j);
            }
        } else {
            if (z && (zzig3 = this.zza) != null) {
                zza(zzig3, true, j);
            }
            z2 = false;
        }
        if (zzig2 == null || zzig2.zzc != zzig.zzc || !zzkw.zzc(zzig2.zzb, zzig.zzb) || !zzkw.zzc(zzig2.zza, zzig.zza)) {
            z3 = true;
        }
        if (z3) {
            Bundle bundle2 = new Bundle();
            if (zzs().zza(zzat.zzbu)) {
                bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            }
            Bundle bundle3 = bundle2;
            zza(zzig, bundle3, true);
            if (zzig2 != null) {
                if (zzig2.zza != null) {
                    bundle3.putString("_pn", zzig2.zza);
                }
                if (zzig2.zzb != null) {
                    bundle3.putString("_pc", zzig2.zzb);
                }
                bundle3.putLong("_pi", zzig2.zzc);
            }
            if (zzs().zza(zzat.zzas) && z2) {
                if (!zznm.zzb() || !zzs().zza(zzat.zzau)) {
                    j2 = zzj().zzb.zzb();
                } else {
                    j2 = zzj().zza(j);
                }
                if (j2 > 0) {
                    zzo().zza(bundle3, j2);
                }
            }
            boolean zza2 = zzs().zza(zzat.zzbu);
            String str = DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
            if (zza2) {
                if (!zzs().zzh().booleanValue()) {
                    bundle3.putLong("_mst", 1);
                }
                if (zzig.zze) {
                    str = AdParameterKeys.SECTION_ID;
                }
            }
            String str2 = str;
            if (zzs().zza(zzat.zzbu)) {
                long currentTimeMillis = zzl().currentTimeMillis();
                if (zzig.zze && zzig.zzf != 0) {
                    currentTimeMillis = zzig.zzf;
                }
                zze().zza(str2, "_vs", currentTimeMillis, bundle3);
            } else {
                zze().zzb(str2, "_vs", bundle3);
            }
        }
        this.zza = zzig;
        if (zzs().zza(zzat.zzbu) && zzig.zze) {
            this.zzh = zzig;
        }
        zzg().zza(zzig);
    }

    /* access modifiers changed from: private */
    public final void zza(zzig zzig, boolean z, long j) {
        zzd().zza(zzl().elapsedRealtime());
        if (zzj().zza(zzig != null && zzig.zzd, z, j) && zzig != null) {
            zzig.zzd = false;
        }
    }

    public static void zza(zzig zzig, Bundle bundle, boolean z) {
        if (bundle != null && zzig != null && (!bundle.containsKey("_sc") || z)) {
            if (zzig.zza != null) {
                bundle.putString("_sn", zzig.zza);
            } else {
                bundle.remove("_sn");
            }
            if (zzig.zzb != null) {
                bundle.putString("_sc", zzig.zzb);
            } else {
                bundle.remove("_sc");
            }
            bundle.putLong("_si", zzig.zzc);
        } else if (bundle != null && zzig == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    public final void zza(String str, zzig zzig) {
        zzc();
        synchronized (this) {
            if (this.zzl == null || this.zzl.equals(str) || zzig != null) {
                this.zzl = str;
                this.zzk = zzig;
            }
        }
    }

    private static String zza(String str) {
        String[] split = str.split("\\.");
        String str2 = split.length > 0 ? split[split.length - 1] : "";
        return str2.length() > 100 ? str2.substring(0, 100) : str2;
    }

    private final zzig zzd(Activity activity) {
        Preconditions.checkNotNull(activity);
        zzig zzig = this.zzd.get(activity);
        if (zzig == null) {
            zzig zzig2 = new zzig((String) null, zza(activity.getClass().getCanonicalName()), zzo().zzf());
            this.zzd.put(activity, zzig2);
            zzig = zzig2;
        }
        return (zzs().zza(zzat.zzbu) && this.zzg != null) ? this.zzg : zzig;
    }

    public final void zza(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (zzs().zzh().booleanValue() && bundle != null && (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) != null) {
            this.zzd.put(activity, new zzig(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
        }
    }

    public final void zza(Activity activity) {
        if (zzs().zza(zzat.zzbu)) {
            synchronized (this.zzj) {
                this.zzi = true;
                if (activity != this.zze) {
                    synchronized (this.zzj) {
                        this.zze = activity;
                        this.zzf = false;
                    }
                    if (zzs().zza(zzat.zzbt) && zzs().zzh().booleanValue()) {
                        this.zzg = null;
                        zzp().zza((Runnable) new zzip(this));
                    }
                }
            }
        }
        if (!zzs().zza(zzat.zzbt) || zzs().zzh().booleanValue()) {
            zza(activity, zzd(activity), false);
            zza zzd2 = zzd();
            zzd2.zzp().zza((Runnable) new zze(zzd2, zzd2.zzl().elapsedRealtime()));
            return;
        }
        this.zzb = this.zzg;
        zzp().zza((Runnable) new zzik(this));
    }

    public final void zzb(Activity activity) {
        if (zzs().zza(zzat.zzbu)) {
            synchronized (this.zzj) {
                this.zzi = false;
                this.zzf = true;
            }
        }
        long elapsedRealtime = zzl().elapsedRealtime();
        if (!zzs().zza(zzat.zzbt) || zzs().zzh().booleanValue()) {
            zzig zzd2 = zzd(activity);
            this.zzc = this.zzb;
            this.zzb = null;
            zzp().zza((Runnable) new zzim(this, zzd2, elapsedRealtime));
            return;
        }
        this.zzb = null;
        zzp().zza((Runnable) new zzin(this, elapsedRealtime));
    }

    public final void zzb(Activity activity, Bundle bundle) {
        zzig zzig;
        if (zzs().zzh().booleanValue() && bundle != null && (zzig = this.zzd.get(activity)) != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong("id", zzig.zzc);
            bundle2.putString("name", zzig.zza);
            bundle2.putString("referrer_name", zzig.zzb);
            bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
        }
    }

    public final void zzc(Activity activity) {
        synchronized (this.zzj) {
            if (activity == this.zze) {
                this.zze = null;
            }
        }
        if (zzs().zzh().booleanValue()) {
            this.zzd.remove(activity);
        }
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ zza zzd() {
        return super.zzd();
    }

    public final /* bridge */ /* synthetic */ zzgy zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzek zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzio zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzij zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zzen zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzju zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzep zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzkw zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzfo zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzer zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzfd zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }
}
