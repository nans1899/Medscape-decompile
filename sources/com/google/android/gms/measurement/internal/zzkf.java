package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.medscape.android.Constants;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzkf {
    private final Clock zza;
    private long zzb;

    public zzkf(Clock clock) {
        Preconditions.checkNotNull(clock);
        this.zza = clock;
    }

    public final void zza() {
        this.zzb = this.zza.elapsedRealtime();
    }

    public final void zzb() {
        this.zzb = 0;
    }

    public final boolean zza(long j) {
        if (this.zzb != 0 && this.zza.elapsedRealtime() - this.zzb < Constants.HOUR_IN_MILLIS) {
            return false;
        }
        return true;
    }
}
