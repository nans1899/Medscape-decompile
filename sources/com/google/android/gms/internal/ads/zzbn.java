package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbn extends zzeoi implements Closeable {
    private static zzeoq zzdc = zzeoq.zzn(zzbn.class);

    public zzbn(zzeok zzeok, zzbo zzbo) throws IOException {
        zza(zzeok, zzeok.size(), zzbo);
    }

    public void close() throws IOException {
        this.zzitt.close();
    }

    public String toString() {
        String obj = this.zzitt.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 7);
        sb.append("model(");
        sb.append(obj);
        sb.append(")");
        return sb.toString();
    }
}
