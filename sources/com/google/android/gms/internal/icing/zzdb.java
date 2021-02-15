package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzdb {
    private final byte[] buffer;
    private final zzdk zzgo;

    private zzdb(int i) {
        byte[] bArr = new byte[i];
        this.buffer = bArr;
        this.zzgo = zzdk.zzb(bArr);
    }

    public final zzct zzar() {
        this.zzgo.zzav();
        return new zzdd(this.buffer);
    }

    public final zzdk zzas() {
        return this.zzgo;
    }

    /* synthetic */ zzdb(int i, zzcw zzcw) {
        this(i);
    }
}
