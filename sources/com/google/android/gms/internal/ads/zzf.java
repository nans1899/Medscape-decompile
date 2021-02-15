package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzf implements zzc {
    private final FileChannel zzg;
    private final long zzh;
    private final long zzi;

    public zzf(FileChannel fileChannel, long j, long j2) {
        this.zzg = fileChannel;
        this.zzh = j;
        this.zzi = j2;
    }

    public final long size() {
        return this.zzi;
    }

    public final void zza(MessageDigest[] messageDigestArr, long j, int i) throws IOException {
        MappedByteBuffer map = this.zzg.map(FileChannel.MapMode.READ_ONLY, this.zzh + j, (long) i);
        map.load();
        for (MessageDigest update : messageDigestArr) {
            map.position(0);
            update.update(map);
        }
    }
}
