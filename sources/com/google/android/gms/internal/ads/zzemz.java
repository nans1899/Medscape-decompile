package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzemz extends zzemx<zzena, zzena> {
    zzemz() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzelz zzelz) {
        return false;
    }

    private static void zza(Object obj, zzena zzena) {
        ((zzejz) obj).zzijc = zzena;
    }

    /* access modifiers changed from: package-private */
    public final void zzaj(Object obj) {
        ((zzejz) obj).zzijc.zzbec();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzat(Object obj) {
        return ((zzena) obj).zzbgh();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzaz(Object obj) {
        return ((zzena) obj).zzbix();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzk(Object obj, Object obj2) {
        zzena zzena = (zzena) obj;
        zzena zzena2 = (zzena) obj2;
        if (zzena2.equals(zzena.zzbiv())) {
            return zzena;
        }
        return zzena.zza(zzena, zzena2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Object obj, zzenu zzenu) throws IOException {
        ((zzena) obj).zza(zzenu);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, zzenu zzenu) throws IOException {
        ((zzena) obj).zzb(zzenu);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzj(Object obj, Object obj2) {
        zza(obj, (zzena) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzay(Object obj) {
        zzena zzena = ((zzejz) obj).zzijc;
        if (zzena != zzena.zzbiv()) {
            return zzena;
        }
        zzena zzbiw = zzena.zzbiw();
        zza(obj, zzbiw);
        return zzbiw;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzax(Object obj) {
        return ((zzejz) obj).zzijc;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(Object obj, Object obj2) {
        zza(obj, (zzena) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzar(Object obj) {
        zzena zzena = (zzena) obj;
        zzena.zzbec();
        return zzena;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzbiu() {
        return zzena.zzbiw();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzena) obj).zzd((i << 3) | 3, (zzena) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, zzeip zzeip) {
        ((zzena) obj).zzd((i << 3) | 2, zzeip);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzena) obj).zzd((i << 3) | 1, Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Object obj, int i, int i2) {
        ((zzena) obj).zzd((i << 3) | 5, Integer.valueOf(i2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzena) obj).zzd(i << 3, Long.valueOf(j));
    }
}
