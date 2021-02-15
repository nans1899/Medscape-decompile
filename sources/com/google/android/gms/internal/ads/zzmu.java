package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzmu implements zzoy {
    private final Uri uri;
    private final zzom zzaos;
    private final zzmt zzbdq;
    private final zzpb zzbdr;
    private final /* synthetic */ zzmn zzbek;
    private final zzkb zzbep = new zzkb();
    private volatile boolean zzbeq;
    private boolean zzber = true;
    private long zzbes;
    /* access modifiers changed from: private */
    public long zzcp = -1;

    public zzmu(zzmn zzmn, Uri uri2, zzom zzom, zzmt zzmt, zzpb zzpb) {
        this.zzbek = zzmn;
        this.uri = (Uri) zzoz.checkNotNull(uri2);
        this.zzaos = (zzom) zzoz.checkNotNull(zzom);
        this.zzbdq = (zzmt) zzoz.checkNotNull(zzmt);
        this.zzbdr = zzpb;
    }

    public final void zze(long j, long j2) {
        this.zzbep.position = j;
        this.zzbes = j2;
        this.zzber = true;
    }

    public final void cancelLoad() {
        this.zzbeq = true;
    }

    public final boolean zzhx() {
        return this.zzbeq;
    }

    public final void zzhy() throws IOException, InterruptedException {
        int i = 0;
        while (i == 0 && !this.zzbeq) {
            zzjw zzjw = null;
            try {
                long j = this.zzbep.position;
                long zza = this.zzaos.zza(new zzon(this.uri, j, -1, this.zzbek.zzbdn));
                this.zzcp = zza;
                if (zza != -1) {
                    this.zzcp = zza + j;
                }
                zzjw zzjw2 = new zzjw(this.zzaos, j, this.zzcp);
                try {
                    zzjv zza2 = this.zzbdq.zza(zzjw2, this.zzaos.getUri());
                    if (this.zzber) {
                        zza2.zzc(j, this.zzbes);
                        this.zzber = false;
                    }
                    while (i == 0 && !this.zzbeq) {
                        this.zzbdr.block();
                        i = zza2.zza(zzjw2, this.zzbep);
                        if (zzjw2.getPosition() > this.zzbek.zzbdo + j) {
                            j = zzjw2.getPosition();
                            this.zzbdr.zziu();
                            this.zzbek.handler.post(this.zzbek.zzbdt);
                        }
                    }
                    if (i == 1) {
                        i = 0;
                    } else {
                        this.zzbep.position = zzjw2.getPosition();
                    }
                    zzpq.zza(this.zzaos);
                } catch (Throwable th) {
                    th = th;
                    zzjw = zzjw2;
                    this.zzbep.position = zzjw.getPosition();
                    zzpq.zza(this.zzaos);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (!(i == 1 || zzjw == null)) {
                    this.zzbep.position = zzjw.getPosition();
                }
                zzpq.zza(this.zzaos);
                throw th;
            }
        }
    }
}
