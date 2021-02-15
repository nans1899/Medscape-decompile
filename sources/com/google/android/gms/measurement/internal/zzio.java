package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzmj;
import com.google.android.gms.internal.measurement.zzw;
import com.medscape.android.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzio extends zzg {
    /* access modifiers changed from: private */
    public final zzji zza;
    /* access modifiers changed from: private */
    public zzej zzb;
    private volatile Boolean zzc;
    private final zzaj zzd;
    private final zzkf zze;
    private final List<Runnable> zzf = new ArrayList();
    private final zzaj zzg;

    protected zzio(zzfv zzfv) {
        super(zzfv);
        this.zze = new zzkf(zzfv.zzl());
        this.zza = new zzji(this);
        this.zzd = new zzir(this, zzfv);
        this.zzg = new zzjb(this, zzfv);
    }

    /* access modifiers changed from: protected */
    public final boolean zzy() {
        return false;
    }

    public final boolean zzaa() {
        zzc();
        zzv();
        return this.zzb != null;
    }

    /* access modifiers changed from: protected */
    public final void zzab() {
        zzc();
        zzv();
        zza((Runnable) new zzjd(this, zzb(true)));
    }

    /* access modifiers changed from: protected */
    public final void zza(boolean z) {
        if (zzmj.zzb() && zzs().zza(zzat.zzcg)) {
            zzc();
            zzv();
            if (z) {
                zzi().zzaa();
            }
            if (zzai()) {
                zza((Runnable) new zzjc(this, zzb(false)));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzej zzej, AbstractSafeParcelable abstractSafeParcelable, zzn zzn) {
        int i;
        zzc();
        zzv();
        int i2 = 0;
        int i3 = 100;
        while (i2 < 1001 && i3 == 100) {
            ArrayList arrayList = new ArrayList();
            List<AbstractSafeParcelable> zza2 = zzi().zza(100);
            if (zza2 != null) {
                arrayList.addAll(zza2);
                i = zza2.size();
            } else {
                i = 0;
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList2.get(i4);
                i4++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzar) {
                    try {
                        zzej.zza((zzar) abstractSafeParcelable2, zzn);
                    } catch (RemoteException e) {
                        zzq().zze().zza("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzkr) {
                    try {
                        zzej.zza((zzkr) abstractSafeParcelable2, zzn);
                    } catch (RemoteException e2) {
                        zzq().zze().zza("Failed to send user property to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzw) {
                    try {
                        zzej.zza((zzw) abstractSafeParcelable2, zzn);
                    } catch (RemoteException e3) {
                        zzq().zze().zza("Failed to send conditional user property to the service", e3);
                    }
                } else {
                    zzq().zze().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i2++;
            i3 = i;
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzar zzar, String str) {
        Preconditions.checkNotNull(zzar);
        zzc();
        zzv();
        zza((Runnable) new zzjf(this, true, zzi().zza(zzar), zzar, zzb(true), str));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzw zzw) {
        Preconditions.checkNotNull(zzw);
        zzc();
        zzv();
        zza((Runnable) new zzje(this, true, zzi().zza(zzw), new zzw(zzw), zzb(true), zzw));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzw>> atomicReference, String str, String str2, String str3) {
        zzc();
        zzv();
        zza((Runnable) new zzjh(this, atomicReference, str, str2, str3, zzb(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzw zzw, String str, String str2) {
        zzc();
        zzv();
        zza((Runnable) new zzjg(this, str, str2, zzb(false), zzw));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzkr>> atomicReference, String str, String str2, String str3, boolean z) {
        zzc();
        zzv();
        zza((Runnable) new zzjj(this, atomicReference, str, str2, str3, z, zzb(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzw zzw, String str, String str2, boolean z) {
        zzc();
        zzv();
        zza((Runnable) new zziq(this, str, str2, z, zzb(false), zzw));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzkr zzkr) {
        zzc();
        zzv();
        zza((Runnable) new zzit(this, zzi().zza(zzkr), zzkr, zzb(true)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzkr>> atomicReference, boolean z) {
        zzc();
        zzv();
        zza((Runnable) new zzis(this, atomicReference, zzb(false), z));
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
        zzc();
        zzv();
        zzn zzb2 = zzb(false);
        zzi().zzaa();
        zza((Runnable) new zziv(this, zzb2));
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzc();
        zzv();
        zza((Runnable) new zziu(this, atomicReference, zzb(false)));
    }

    public final void zza(zzw zzw) {
        zzc();
        zzv();
        zza((Runnable) new zzix(this, zzb(false), zzw));
    }

    /* access modifiers changed from: protected */
    public final void zzad() {
        zzc();
        zzv();
        zzn zzb2 = zzb(true);
        zzi().zzab();
        zza((Runnable) new zziw(this, zzb2));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzig zzig) {
        zzc();
        zzv();
        zza((Runnable) new zziz(this, zzig));
    }

    public final void zza(Bundle bundle) {
        zzc();
        zzv();
        zza((Runnable) new zziy(this, bundle, zzb(false)));
    }

    /* access modifiers changed from: private */
    public final void zzaj() {
        zzc();
        this.zze.zza();
        this.zzd.zza(zzat.zzai.zza(null).longValue());
    }

    /* access modifiers changed from: package-private */
    public final void zzae() {
        zzc();
        zzv();
        if (!zzaa()) {
            if (zzak()) {
                this.zza.zzb();
            } else if (!zzs().zzw()) {
                List<ResolveInfo> queryIntentServices = zzm().getPackageManager().queryIntentServices(new Intent().setClassName(zzm(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                if (queryIntentServices != null && queryIntentServices.size() > 0) {
                    Intent intent = new Intent("com.google.android.gms.measurement.START");
                    intent.setComponent(new ComponentName(zzm(), "com.google.android.gms.measurement.AppMeasurementService"));
                    this.zza.zza(intent);
                    return;
                }
                zzq().zze().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzaf() {
        return this.zzc;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzak() {
        /*
            r5 = this;
            r5.zzc()
            r5.zzv()
            java.lang.Boolean r0 = r5.zzc
            if (r0 != 0) goto L_0x0101
            r5.zzc()
            r5.zzv()
            com.google.android.gms.measurement.internal.zzfd r0 = r5.zzr()
            java.lang.Boolean r0 = r0.zzi()
            r1 = 1
            if (r0 == 0) goto L_0x0023
            boolean r2 = r0.booleanValue()
            if (r2 == 0) goto L_0x0023
            goto L_0x00fb
        L_0x0023:
            com.google.android.gms.measurement.internal.zzek r2 = r5.zzf()
            int r2 = r2.zzaf()
            r3 = 0
            if (r2 != r1) goto L_0x0031
        L_0x002e:
            r0 = 1
            goto L_0x00d7
        L_0x0031:
            com.google.android.gms.measurement.internal.zzer r2 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzw()
            java.lang.String r4 = "Checking service availability"
            r2.zza(r4)
            com.google.android.gms.measurement.internal.zzkw r2 = r5.zzo()
            r4 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r2 = r2.zza((int) r4)
            if (r2 == 0) goto L_0x00c8
            if (r2 == r1) goto L_0x00b8
            r4 = 2
            if (r2 == r4) goto L_0x0098
            r0 = 3
            if (r2 == r0) goto L_0x0089
            r0 = 9
            if (r2 == r0) goto L_0x007b
            r0 = 18
            if (r2 == r0) goto L_0x006d
            com.google.android.gms.measurement.internal.zzer r0 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzh()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            java.lang.String r2 = "Unexpected service status"
            r0.zza(r2, r1)
            goto L_0x0096
        L_0x006d:
            com.google.android.gms.measurement.internal.zzer r0 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzh()
            java.lang.String r2 = "Service updating"
            r0.zza(r2)
            goto L_0x002e
        L_0x007b:
            com.google.android.gms.measurement.internal.zzer r0 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzh()
            java.lang.String r1 = "Service invalid"
            r0.zza(r1)
            goto L_0x0096
        L_0x0089:
            com.google.android.gms.measurement.internal.zzer r0 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzh()
            java.lang.String r1 = "Service disabled"
            r0.zza(r1)
        L_0x0096:
            r0 = 0
            goto L_0x00c6
        L_0x0098:
            com.google.android.gms.measurement.internal.zzer r2 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzv()
            java.lang.String r4 = "Service container out of date"
            r2.zza(r4)
            com.google.android.gms.measurement.internal.zzkw r2 = r5.zzo()
            int r2 = r2.zzi()
            r4 = 17443(0x4423, float:2.4443E-41)
            if (r2 >= r4) goto L_0x00b2
            goto L_0x00c5
        L_0x00b2:
            if (r0 != 0) goto L_0x00b5
            goto L_0x00b6
        L_0x00b5:
            r1 = 0
        L_0x00b6:
            r0 = 0
            goto L_0x00d7
        L_0x00b8:
            com.google.android.gms.measurement.internal.zzer r0 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzw()
            java.lang.String r2 = "Service missing"
            r0.zza(r2)
        L_0x00c5:
            r0 = 1
        L_0x00c6:
            r1 = 0
            goto L_0x00d7
        L_0x00c8:
            com.google.android.gms.measurement.internal.zzer r0 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzw()
            java.lang.String r2 = "Service available"
            r0.zza(r2)
            goto L_0x002e
        L_0x00d7:
            if (r1 != 0) goto L_0x00f1
            com.google.android.gms.measurement.internal.zzy r2 = r5.zzs()
            boolean r2 = r2.zzw()
            if (r2 == 0) goto L_0x00f1
            com.google.android.gms.measurement.internal.zzer r0 = r5.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zze()
            java.lang.String r2 = "No way to upload. Consider using the full version of Analytics"
            r0.zza(r2)
            goto L_0x00f2
        L_0x00f1:
            r3 = r0
        L_0x00f2:
            if (r3 == 0) goto L_0x00fb
            com.google.android.gms.measurement.internal.zzfd r0 = r5.zzr()
            r0.zza((boolean) r1)
        L_0x00fb:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r5.zzc = r0
        L_0x0101:
            java.lang.Boolean r0 = r5.zzc
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzio.zzak():boolean");
    }

    /* access modifiers changed from: protected */
    public final void zza(zzej zzej) {
        zzc();
        Preconditions.checkNotNull(zzej);
        this.zzb = zzej;
        zzaj();
        zzam();
    }

    public final void zzag() {
        zzc();
        zzv();
        this.zza.zza();
        try {
            ConnectionTracker.getInstance().unbindService(zzm(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    /* access modifiers changed from: private */
    public final void zza(ComponentName componentName) {
        zzc();
        if (this.zzb != null) {
            this.zzb = null;
            zzq().zzw().zza("Disconnected from device MeasurementService", componentName);
            zzc();
            zzae();
        }
    }

    /* access modifiers changed from: private */
    public final void zzal() {
        zzc();
        if (zzaa()) {
            zzq().zzw().zza("Inactivity, disconnecting from the service");
            zzag();
        }
    }

    private final void zza(Runnable runnable) throws IllegalStateException {
        zzc();
        if (zzaa()) {
            runnable.run();
        } else if (((long) this.zzf.size()) >= 1000) {
            zzq().zze().zza("Discarding data. Max runnable queue size reached");
        } else {
            this.zzf.add(runnable);
            this.zzg.zza(Constants.MINUTE_IN_MILLIS);
            zzae();
        }
    }

    /* access modifiers changed from: private */
    public final void zzam() {
        zzc();
        zzq().zzw().zza("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        for (Runnable run : this.zzf) {
            try {
                run.run();
            } catch (Exception e) {
                zzq().zze().zza("Task exception while flushing queue", e);
            }
        }
        this.zzf.clear();
        this.zzg.zzc();
    }

    private final zzn zzb(boolean z) {
        return zzf().zza(z ? zzq().zzx() : null);
    }

    public final void zza(zzw zzw, zzar zzar, String str) {
        zzc();
        zzv();
        if (zzo().zza((int) GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) != 0) {
            zzq().zzh().zza("Not bundling data. Service unavailable or out of date");
            zzo().zza(zzw, new byte[0]);
            return;
        }
        zza((Runnable) new zzja(this, zzar, str, zzw));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzah() {
        zzc();
        zzv();
        if (zzak() && zzo().zzi() < 200900) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzai() {
        zzc();
        zzv();
        if (!zzs().zza(zzat.zzci)) {
            return false;
        }
        if (zzak() && zzo().zzi() < zzat.zzcj.zza(null).intValue()) {
            return false;
        }
        return true;
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
