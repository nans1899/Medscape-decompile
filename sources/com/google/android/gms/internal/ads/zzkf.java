package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzkf {
    public final int zzaoz = 1;
    public final byte[] zzapa;

    public zzkf(int i, byte[] bArr) {
        this.zzapa = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzkf zzkf = (zzkf) obj;
            return this.zzaoz == zzkf.zzaoz && Arrays.equals(this.zzapa, zzkf.zzapa);
        }
    }

    public final int hashCode() {
        return (this.zzaoz * 31) + Arrays.hashCode(this.zzapa);
    }
}
