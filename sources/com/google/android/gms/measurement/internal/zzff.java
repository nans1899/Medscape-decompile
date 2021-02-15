package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzff {
    private final String zza;
    private final boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private final /* synthetic */ zzfd zze;

    public zzff(zzfd zzfd, String str, boolean z) {
        this.zze = zzfd;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = z;
    }

    public final boolean zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.zze.zzf().getBoolean(this.zza, this.zzb);
        }
        return this.zzd;
    }

    public final void zza(boolean z) {
        SharedPreferences.Editor edit = this.zze.zzf().edit();
        edit.putBoolean(this.zza, z);
        edit.apply();
        this.zzd = z;
    }
}
