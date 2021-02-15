package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzeoe implements zzbp {
    private static zzeoq zzdc = zzeoq.zzn(zzeoe.class);
    private String type;
    private long zzawn;
    private zzbs zzitn;
    boolean zzito;
    private boolean zzitp;
    private ByteBuffer zzitq;
    private long zzitr;
    private long zzits = -1;
    private zzeok zzitt;
    private ByteBuffer zzitu = null;

    private final synchronized void zzbke() {
        if (!this.zzitp) {
            try {
                zzeoq zzeoq = zzdc;
                String valueOf = String.valueOf(this.type);
                zzeoq.zzik(valueOf.length() != 0 ? "mem mapping ".concat(valueOf) : new String("mem mapping "));
                this.zzitq = this.zzitt.zzh(this.zzitr, this.zzits);
                this.zzitp = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzl(ByteBuffer byteBuffer);

    protected zzeoe(String str) {
        this.type = str;
        this.zzitp = true;
        this.zzito = true;
    }

    public final void zza(zzeok zzeok, ByteBuffer byteBuffer, long j, zzbo zzbo) throws IOException {
        long position = zzeok.position();
        this.zzitr = position;
        this.zzawn = position - ((long) byteBuffer.remaining());
        this.zzits = j;
        this.zzitt = zzeok;
        zzeok.zzfc(zzeok.position() + j);
        this.zzitp = false;
        this.zzito = false;
        zzbkf();
    }

    public final synchronized void zzbkf() {
        zzbke();
        zzeoq zzeoq = zzdc;
        String valueOf = String.valueOf(this.type);
        zzeoq.zzik(valueOf.length() != 0 ? "parsing details of ".concat(valueOf) : new String("parsing details of "));
        if (this.zzitq != null) {
            ByteBuffer byteBuffer = this.zzitq;
            this.zzito = true;
            byteBuffer.rewind();
            zzl(byteBuffer);
            if (byteBuffer.remaining() > 0) {
                this.zzitu = byteBuffer.slice();
            }
            this.zzitq = null;
        }
    }

    public final String getType() {
        return this.type;
    }

    public final void zza(zzbs zzbs) {
        this.zzitn = zzbs;
    }
}
