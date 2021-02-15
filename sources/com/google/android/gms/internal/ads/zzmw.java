package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzmw implements zznm {
    /* access modifiers changed from: private */
    public final int track;
    private final /* synthetic */ zzmn zzbek;

    public zzmw(zzmn zzmn, int i) {
        this.zzbek = zzmn;
        this.track = i;
    }

    public final boolean isReady() {
        return this.zzbek.zzbb(this.track);
    }

    public final void zzhs() throws IOException {
        this.zzbek.zzhs();
    }

    public final int zzb(zzhq zzhq, zzjk zzjk, boolean z) {
        return this.zzbek.zza(this.track, zzhq, zzjk, z);
    }

    public final void zzeh(long j) {
        this.zzbek.zzd(this.track, j);
    }
}
