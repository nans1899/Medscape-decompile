package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcpy extends zzasz {
    private final /* synthetic */ zzcpv zzgkj;

    protected zzcpy(zzcpv zzcpv) {
        this.zzgkj = zzcpv;
    }

    public final void zzb(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzgkj.zzdja.set(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor));
    }

    public final void zza(zzazy zzazy) {
        this.zzgkj.zzdja.setException(new zzbab(zzazy.zzacm, zzazy.errorCode));
    }
}
