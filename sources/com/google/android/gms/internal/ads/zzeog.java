package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzeog extends zzeoi implements zzbp {
    private String type;
    private long zzawn;
    private zzbs zzitn;
    private boolean zzitv;

    public zzeog(String str) {
        this.type = str;
    }

    public final void zza(zzbs zzbs) {
        this.zzitn = zzbs;
    }

    public final String getType() {
        return this.type;
    }

    public final void zza(zzeok zzeok, ByteBuffer byteBuffer, long j, zzbo zzbo) throws IOException {
        this.zzawn = zzeok.position() - ((long) byteBuffer.remaining());
        this.zzitv = byteBuffer.remaining() == 16;
        zza(zzeok, j, zzbo);
    }

    public final void zza(zzeok zzeok, long j, zzbo zzbo) throws IOException {
        this.zzitt = zzeok;
        this.zzitz = zzeok.position();
        this.zzbgl = this.zzitz - ((long) ((this.zzitv || 8 + j >= 4294967296L) ? 16 : 8));
        zzeok.zzfc(zzeok.position() + j);
        this.zzawj = zzeok.position();
        this.zzitx = zzbo;
    }
}
