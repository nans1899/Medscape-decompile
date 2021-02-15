package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public interface zzie {
    public static final ByteBuffer zzaiy = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());

    void flush();

    boolean isActive();

    void reset();

    boolean zzb(int i, int i2, int i3) throws zzih;

    boolean zzfe();

    int zzfj();

    int zzfk();

    void zzfl();

    ByteBuffer zzfm();

    void zzn(ByteBuffer byteBuffer);
}
