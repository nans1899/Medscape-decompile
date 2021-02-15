package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzmv implements zzmz, zznc {
    private final Uri uri;
    private final Handler zzaes;
    private final zzia zzaew;
    private zzhy zzafd;
    private final int zzbdj;
    private final zzmy zzbdk;
    private zznc zzbdl;
    private final String zzbdn = null;
    private final zzol zzbet;
    private final zzka zzbeu;
    private final int zzbev;
    private boolean zzbew;

    public zzmv(Uri uri2, zzol zzol, zzka zzka, int i, Handler handler, zzmy zzmy, String str, int i2) {
        this.uri = uri2;
        this.zzbet = zzol;
        this.zzbeu = zzka;
        this.zzbdj = i;
        this.zzaes = handler;
        this.zzbdk = zzmy;
        this.zzbev = i2;
        this.zzaew = new zzia();
    }

    public final void zzhz() throws IOException {
    }

    public final void zza(zzhc zzhc, boolean z, zznc zznc) {
        this.zzbdl = zznc;
        zznn zznn = new zznn(-9223372036854775807L, false);
        this.zzafd = zznn;
        zznc.zzb(zznn, (Object) null);
    }

    public final zzmx zza(int i, zzok zzok) {
        zzoz.checkArgument(i == 0);
        return new zzmn(this.uri, this.zzbet.zzip(), this.zzbeu.zzgt(), this.zzbdj, this.zzaes, this.zzbdk, this, zzok, (String) null, this.zzbev);
    }

    public final void zzb(zzmx zzmx) {
        ((zzmn) zzmx).release();
    }

    public final void zzia() {
        this.zzbdl = null;
    }

    public final void zzb(zzhy zzhy, Object obj) {
        boolean z = false;
        if (zzhy.zza(0, this.zzaew, false).zzaih != -9223372036854775807L) {
            z = true;
        }
        if (!this.zzbew || z) {
            this.zzafd = zzhy;
            this.zzbew = z;
            this.zzbdl.zzb(zzhy, (Object) null);
        }
    }
}
