package com.google.android.gms.internal.ads;

import java.io.FilterInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbg extends FilterInputStream {
    private final HttpURLConnection zzcx;

    zzbg(HttpURLConnection httpURLConnection) {
        super(zzbd.zza(httpURLConnection));
        this.zzcx = httpURLConnection;
    }

    public final void close() throws IOException {
        super.close();
        this.zzcx.disconnect();
    }
}
