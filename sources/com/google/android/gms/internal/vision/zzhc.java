package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
class zzhc extends zzhd {
    protected final byte[] zzua;

    zzhc(byte[] bArr) {
        if (bArr != null) {
            this.zzua = bArr;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: protected */
    public int zzfo() {
        return 0;
    }

    public byte zzau(int i) {
        return this.zzua[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzav(int i) {
        return this.zzua[i];
    }

    public int size() {
        return this.zzua.length;
    }

    public final zzgs zzi(int i, int i2) {
        int zze = zze(0, i2, size());
        if (zze == 0) {
            return zzgs.zztt;
        }
        return new zzgz(this.zzua, zzfo(), zze);
    }

    /* access modifiers changed from: protected */
    public void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzua, 0, bArr, 0, i3);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzgt zzgt) throws IOException {
        zzgt.zzc(this.zzua, zzfo(), size());
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzua, zzfo(), size(), charset);
    }

    public final boolean zzfm() {
        int zzfo = zzfo();
        return zzlf.zzf(this.zzua, zzfo, size() + zzfo);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgs) || size() != ((zzgs) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzhc)) {
            return obj.equals(this);
        }
        zzhc zzhc = (zzhc) obj;
        int zzfn = zzfn();
        int zzfn2 = zzhc.zzfn();
        if (zzfn == 0 || zzfn2 == 0 || zzfn == zzfn2) {
            return zza(zzhc, 0, size());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzgs zzgs, int i, int i2) {
        if (i2 > zzgs.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzgs.size()) {
            int size2 = zzgs.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzgs instanceof zzhc)) {
            return zzgs.zzi(0, i2).equals(zzi(0, i2));
        } else {
            zzhc zzhc = (zzhc) zzgs;
            byte[] bArr = this.zzua;
            byte[] bArr2 = zzhc.zzua;
            int zzfo = zzfo() + i2;
            int zzfo2 = zzfo();
            int zzfo3 = zzhc.zzfo();
            while (zzfo2 < zzfo) {
                if (bArr[zzfo2] != bArr2[zzfo3]) {
                    return false;
                }
                zzfo2++;
                zzfo3++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final int zzd(int i, int i2, int i3) {
        return zzie.zza(i, this.zzua, zzfo(), i3);
    }
}
