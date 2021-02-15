package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
class zzdd extends zzde {
    protected final byte[] zzgp;

    zzdd(byte[] bArr) {
        if (bArr != null) {
            this.zzgp = bArr;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: protected */
    public int zzaq() {
        return 0;
    }

    public byte zzk(int i) {
        return this.zzgp[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzl(int i) {
        return this.zzgp[i];
    }

    public int size() {
        return this.zzgp.length;
    }

    public final zzct zza(int i, int i2) {
        int zzb = zzb(0, i2, size());
        if (zzb == 0) {
            return zzct.zzgi;
        }
        return new zzda(this.zzgp, zzaq(), zzb);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzcu zzcu) throws IOException {
        zzcu.zza(this.zzgp, zzaq(), size());
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzgp, zzaq(), size(), charset);
    }

    public final boolean zzao() {
        int zzaq = zzaq();
        return zzgv.zzc(this.zzgp, zzaq, size() + zzaq);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzct) || size() != ((zzct) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzdd)) {
            return obj.equals(this);
        }
        zzdd zzdd = (zzdd) obj;
        int zzap = zzap();
        int zzap2 = zzdd.zzap();
        if (zzap == 0 || zzap2 == 0 || zzap == zzap2) {
            return zza(zzdd, 0, size());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzct zzct, int i, int i2) {
        if (i2 > zzct.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzct.size()) {
            int size2 = zzct.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzct instanceof zzdd)) {
            return zzct.zza(0, i2).equals(zza(0, i2));
        } else {
            zzdd zzdd = (zzdd) zzct;
            byte[] bArr = this.zzgp;
            byte[] bArr2 = zzdd.zzgp;
            int zzaq = zzaq() + i2;
            int zzaq2 = zzaq();
            int zzaq3 = zzdd.zzaq();
            while (zzaq2 < zzaq) {
                if (bArr[zzaq2] != bArr2[zzaq3]) {
                    return false;
                }
                zzaq2++;
                zzaq3++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final int zza(int i, int i2, int i3) {
        return zzeb.zza(i, this.zzgp, zzaq(), i3);
    }
}
