package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbt extends zzeoe {
    private ByteBuffer zzdd;

    public zzbt(String str) {
        super(str);
    }

    public final void zzl(ByteBuffer byteBuffer) {
        this.zzdd = byteBuffer;
        byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
    }
}
