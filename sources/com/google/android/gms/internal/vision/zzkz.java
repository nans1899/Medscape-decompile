package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzkz extends zzkx<zzkw, zzkw> {
    zzkz() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzkc zzkc) {
        return false;
    }

    private static void zza(Object obj, zzkw zzkw) {
        ((zzid) obj).zzxz = zzkw;
    }

    /* access modifiers changed from: package-private */
    public final void zzj(Object obj) {
        ((zzid) obj).zzxz.zzej();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzu(Object obj) {
        return ((zzkw) obj).zzgz();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzaa(Object obj) {
        return ((zzkw) obj).zzjc();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzh(Object obj, Object obj2) {
        zzkw zzkw = (zzkw) obj;
        zzkw zzkw2 = (zzkw) obj2;
        if (zzkw2.equals(zzkw.zzja())) {
            return zzkw;
        }
        return zzkw.zza(zzkw, zzkw2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Object obj, zzlq zzlq) throws IOException {
        ((zzkw) obj).zza(zzlq);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, zzlq zzlq) throws IOException {
        ((zzkw) obj).zzb(zzlq);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(Object obj, Object obj2) {
        zza(obj, (zzkw) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzz(Object obj) {
        zzkw zzkw = ((zzid) obj).zzxz;
        if (zzkw != zzkw.zzja()) {
            return zzkw;
        }
        zzkw zzjb = zzkw.zzjb();
        zza(obj, zzjb);
        return zzjb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzy(Object obj) {
        return ((zzid) obj).zzxz;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Object obj, Object obj2) {
        zza(obj, (zzkw) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzq(Object obj) {
        zzkw zzkw = (zzkw) obj;
        zzkw.zzej();
        return zzkw;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzjd() {
        return zzkw.zzjb();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzkw) obj).zzb((i << 3) | 3, (zzkw) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, zzgs zzgs) {
        ((zzkw) obj).zzb((i << 3) | 2, zzgs);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzkw) obj).zzb((i << 3) | 1, Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(Object obj, int i, int i2) {
        ((zzkw) obj).zzb((i << 3) | 5, Integer.valueOf(i2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzkw) obj).zzb(i << 3, Long.valueOf(j));
    }
}
