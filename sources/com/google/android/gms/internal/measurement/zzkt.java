package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzkt extends zzkr<zzku, zzku> {
    zzkt() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzka zzka) {
        return false;
    }

    private static void zza(Object obj, zzku zzku) {
        ((zzhz) obj).zzb = zzku;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(Object obj) {
        ((zzhz) obj).zzb.zzc();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzf(Object obj) {
        return ((zzku) obj).zze();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zze(Object obj) {
        return ((zzku) obj).zzd();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(Object obj, Object obj2) {
        zzku zzku = (zzku) obj;
        zzku zzku2 = (zzku) obj2;
        if (zzku2.equals(zzku.zza())) {
            return zzku;
        }
        return zzku.zza(zzku, zzku2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, zzlo zzlo) throws IOException {
        ((zzku) obj).zza(zzlo);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, zzlo zzlo) throws IOException {
        ((zzku) obj).zzb(zzlo);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, Object obj2) {
        zza(obj, (zzku) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(Object obj) {
        zzku zzku = ((zzhz) obj).zzb;
        if (zzku != zzku.zza()) {
            return zzku;
        }
        zzku zzb = zzku.zzb();
        zza(obj, zzb);
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzb(Object obj) {
        return ((zzhz) obj).zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zza(Object obj, Object obj2) {
        zza(obj, (zzku) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj) {
        zzku zzku = (zzku) obj;
        zzku.zzc();
        return zzku;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza() {
        return zzku.zzb();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzku) obj).zza((i << 3) | 3, (Object) (zzku) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, zzgr zzgr) {
        ((zzku) obj).zza((i << 3) | 2, (Object) zzgr);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzku) obj).zza((i << 3) | 1, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, int i2) {
        ((zzku) obj).zza((i << 3) | 5, (Object) Integer.valueOf(i2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzku) obj).zza(i << 3, (Object) Long.valueOf(j));
    }
}
