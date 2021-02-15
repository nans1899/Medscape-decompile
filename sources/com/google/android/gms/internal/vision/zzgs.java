package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzgs implements Serializable, Iterable<Byte> {
    public static final zzgs zztt = new zzhc(zzie.zzys);
    private static final zzgy zztu = (zzgl.zzel() ? new zzhf((zzgv) null) : new zzgw((zzgv) null));
    private static final Comparator<zzgs> zztv = new zzgu();
    private int zzoc = 0;

    zzgs() {
    }

    /* access modifiers changed from: private */
    public static int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    /* access modifiers changed from: protected */
    public abstract String zza(Charset charset);

    /* access modifiers changed from: package-private */
    public abstract void zza(zzgt zzgt) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void zza(byte[] bArr, int i, int i2, int i3);

    public abstract byte zzau(int i);

    /* access modifiers changed from: package-private */
    public abstract byte zzav(int i);

    /* access modifiers changed from: protected */
    public abstract int zzd(int i, int i2, int i3);

    public abstract boolean zzfm();

    public abstract zzgs zzi(int i, int i2);

    public static zzgs zza(byte[] bArr, int i, int i2) {
        zze(i, i + i2, bArr.length);
        return new zzhc(zztu.zzd(bArr, i, i2));
    }

    static zzgs zzd(byte[] bArr) {
        return new zzhc(bArr);
    }

    static zzgs zzb(byte[] bArr, int i, int i2) {
        return new zzgz(bArr, i, i2);
    }

    public static zzgs zzv(String str) {
        return new zzhc(str.getBytes(zzie.UTF_8));
    }

    public final String zzfl() {
        return size() == 0 ? "" : zza(zzie.UTF_8);
    }

    public final int hashCode() {
        int i = this.zzoc;
        if (i == 0) {
            int size = size();
            i = zzd(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzoc = i;
        }
        return i;
    }

    static zzha zzaw(int i) {
        return new zzha(i, (zzgv) null);
    }

    /* access modifiers changed from: protected */
    public final int zzfn() {
        return this.zzoc;
    }

    static int zze(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else {
            StringBuilder sb3 = new StringBuilder(37);
            sb3.append("End index: ");
            sb3.append(i2);
            sb3.append(" >= ");
            sb3.append(i3);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(size());
        objArr[2] = size() <= 50 ? zzkt.zzd(this) : String.valueOf(zzkt.zzd(zzi(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public /* synthetic */ Iterator iterator() {
        return new zzgv(this);
    }
}
