package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzmt {
    private final zzjx zzarj;
    private final zzjv[] zzben;
    private zzjv zzbeo;

    public zzmt(zzjv[] zzjvArr, zzjx zzjx) {
        this.zzben = zzjvArr;
        this.zzarj = zzjx;
    }

    public final zzjv zza(zzjy zzjy, Uri uri) throws IOException, InterruptedException {
        zzjv zzjv = this.zzbeo;
        if (zzjv != null) {
            return zzjv;
        }
        zzjv[] zzjvArr = this.zzben;
        int length = zzjvArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            zzjv zzjv2 = zzjvArr[i];
            try {
                if (zzjv2.zza(zzjy)) {
                    this.zzbeo = zzjv2;
                    zzjy.zzgq();
                    break;
                }
                i++;
            } catch (EOFException unused) {
            } finally {
                zzjy.zzgq();
            }
        }
        zzjv zzjv3 = this.zzbeo;
        if (zzjv3 != null) {
            zzjv3.zza(this.zzarj);
            return this.zzbeo;
        }
        String zza = zzpq.zza((Object[]) this.zzben);
        StringBuilder sb = new StringBuilder(String.valueOf(zza).length() + 58);
        sb.append("None of the available extractors (");
        sb.append(zza);
        sb.append(") could read the stream.");
        throw new zzns(sb.toString(), uri);
    }

    public final void release() {
        zzjv zzjv = this.zzbeo;
        if (zzjv != null) {
            zzjv.release();
            this.zzbeo = null;
        }
    }
}
