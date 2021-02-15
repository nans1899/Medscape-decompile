package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zztn extends PushbackInputStream {
    private final /* synthetic */ zztm zzbvt;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zztn(zztm zztm, InputStream inputStream, int i) {
        super(inputStream, 1);
        this.zzbvt = zztm;
    }

    public final synchronized void close() throws IOException {
        this.zzbvt.zzbvl.disconnect();
        super.close();
    }
}
