package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
class zzeiz extends zzeiw {
    protected final byte[] zzifc;

    zzeiz(byte[] bArr) {
        if (bArr != null) {
            this.zzifc = bArr;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: protected */
    public int zzbem() {
        return 0;
    }

    public byte zzfu(int i) {
        return this.zzifc[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzfv(int i) {
        return this.zzifc[i];
    }

    public int size() {
        return this.zzifc.length;
    }

    public final zzeip zzz(int i, int i2) {
        int zzi = zzi(i, i2, size());
        if (zzi == 0) {
            return zzeip.zzier;
        }
        return new zzeis(this.zzifc, zzbem() + i, zzi);
    }

    /* access modifiers changed from: protected */
    public void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzifc, i, bArr, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzeim zzeim) throws IOException {
        zzeim.zzh(this.zzifc, zzbem(), size());
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzifc, zzbem(), size(), charset);
    }

    public final boolean zzbeg() {
        int zzbem = zzbem();
        return zzeng.zzm(this.zzifc, zzbem, size() + zzbem);
    }

    /* access modifiers changed from: protected */
    public final int zzg(int i, int i2, int i3) {
        int zzbem = zzbem() + i2;
        return zzeng.zzb(i, this.zzifc, zzbem, i3 + zzbem);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeip) || size() != ((zzeip) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzeiz)) {
            return obj.equals(this);
        }
        zzeiz zzeiz = (zzeiz) obj;
        int zzbel = zzbel();
        int zzbel2 = zzeiz.zzbel();
        if (zzbel == 0 || zzbel2 == 0 || zzbel == zzbel2) {
            return zza(zzeiz, 0, size());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzeip zzeip, int i, int i2) {
        if (i2 <= zzeip.size()) {
            int i3 = i + i2;
            if (i3 > zzeip.size()) {
                int size = zzeip.size();
                StringBuilder sb = new StringBuilder(59);
                sb.append("Ran off end of other: ");
                sb.append(i);
                sb.append(", ");
                sb.append(i2);
                sb.append(", ");
                sb.append(size);
                throw new IllegalArgumentException(sb.toString());
            } else if (!(zzeip instanceof zzeiz)) {
                return zzeip.zzz(i, i3).equals(zzz(0, i2));
            } else {
                zzeiz zzeiz = (zzeiz) zzeip;
                byte[] bArr = this.zzifc;
                byte[] bArr2 = zzeiz.zzifc;
                int zzbem = zzbem() + i2;
                int zzbem2 = zzbem();
                int zzbem3 = zzeiz.zzbem() + i;
                while (zzbem2 < zzbem) {
                    if (bArr[zzbem2] != bArr2[zzbem3]) {
                        return false;
                    }
                    zzbem2++;
                    zzbem3++;
                }
                return true;
            }
        } else {
            int size2 = size();
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Length too large: ");
            sb2.append(i2);
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    /* access modifiers changed from: protected */
    public final int zzh(int i, int i2, int i3) {
        return zzekb.zza(i, this.zzifc, zzbem() + i2, i3);
    }

    public final zzeja zzbeh() {
        return zzeja.zzb(this.zzifc, zzbem(), size(), true);
    }
}
