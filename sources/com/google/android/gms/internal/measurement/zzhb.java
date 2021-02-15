package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
class zzhb extends zzgy {
    protected final byte[] zzb;

    zzhb(byte[] bArr) {
        if (bArr != null) {
            this.zzb = bArr;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: protected */
    public int zze() {
        return 0;
    }

    public byte zza(int i) {
        return this.zzb[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzb(int i) {
        return this.zzb[i];
    }

    public int zza() {
        return this.zzb.length;
    }

    public final zzgr zza(int i, int i2) {
        int zzb2 = zzb(0, i2, zza());
        if (zzb2 == 0) {
            return zzgr.zza;
        }
        return new zzgu(this.zzb, zze(), zzb2);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzgo zzgo) throws IOException {
        zzgo.zza(this.zzb, zze(), zza());
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzb, zze(), zza(), charset);
    }

    public final boolean zzc() {
        int zze = zze();
        return zzla.zza(this.zzb, zze, zza() + zze);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgr) || zza() != ((zzgr) obj).zza()) {
            return false;
        }
        if (zza() == 0) {
            return true;
        }
        if (!(obj instanceof zzhb)) {
            return obj.equals(this);
        }
        zzhb zzhb = (zzhb) obj;
        int zzd = zzd();
        int zzd2 = zzhb.zzd();
        if (zzd == 0 || zzd2 == 0 || zzd == zzd2) {
            return zza(zzhb, 0, zza());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzgr zzgr, int i, int i2) {
        if (i2 > zzgr.zza()) {
            int zza = zza();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(zza);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzgr.zza()) {
            int zza2 = zzgr.zza();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(zza2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzgr instanceof zzhb)) {
            return zzgr.zza(0, i2).equals(zza(0, i2));
        } else {
            zzhb zzhb = (zzhb) zzgr;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzhb.zzb;
            int zze = zze() + i2;
            int zze2 = zze();
            int zze3 = zzhb.zze();
            while (zze2 < zze) {
                if (bArr[zze2] != bArr2[zze3]) {
                    return false;
                }
                zze2++;
                zze3++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final int zza(int i, int i2, int i3) {
        return zzic.zza(i, this.zzb, zze(), i3);
    }
}
