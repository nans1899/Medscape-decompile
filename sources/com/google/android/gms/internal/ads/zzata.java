package com.google.android.gms.internal.ads;

import java.io.OutputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzata implements Runnable {
    private final OutputStream zzdtt;
    private final byte[] zzdtu;

    zzata(OutputStream outputStream, byte[] bArr) {
        this.zzdtt = outputStream;
        this.zzdtu = bArr;
    }

    public final void run() {
        zzatb.zza(this.zzdtt, this.zzdtu);
    }
}
