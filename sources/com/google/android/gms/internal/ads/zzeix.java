package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeix {
    private final byte[] buffer;
    private final zzejj zziew;

    private zzeix(int i) {
        byte[] bArr = new byte[i];
        this.buffer = bArr;
        this.zziew = zzejj.zzw(bArr);
    }

    public final zzeip zzben() {
        this.zziew.zzbfq();
        return new zzeiz(this.buffer);
    }

    public final zzejj zzbeo() {
        return this.zziew;
    }

    /* synthetic */ zzeix(int i, zzeio zzeio) {
        this(i);
    }
}
