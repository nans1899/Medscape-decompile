package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public abstract class zzct implements Serializable, Iterable<Byte> {
    public static final zzct zzgi = new zzdd(zzeb.zzla);
    private static final zzcz zzgj = (zzcs.zzal() ? new zzdg((zzcw) null) : new zzcx((zzcw) null));
    private static final Comparator<zzct> zzgk = new zzcv();
    private int zzef = 0;

    zzct() {
    }

    /* access modifiers changed from: private */
    public static int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    /* access modifiers changed from: protected */
    public abstract int zza(int i, int i2, int i3);

    public abstract zzct zza(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract String zza(Charset charset);

    /* access modifiers changed from: package-private */
    public abstract void zza(zzcu zzcu) throws IOException;

    public abstract boolean zzao();

    public abstract byte zzk(int i);

    /* access modifiers changed from: package-private */
    public abstract byte zzl(int i);

    public static zzct zzp(String str) {
        return new zzdd(str.getBytes(zzeb.UTF_8));
    }

    public final String zzan() {
        return size() == 0 ? "" : zza(zzeb.UTF_8);
    }

    public final int hashCode() {
        int i = this.zzef;
        if (i == 0) {
            int size = size();
            i = zza(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzef = i;
        }
        return i;
    }

    static zzdb zzm(int i) {
        return new zzdb(i, (zzcw) null);
    }

    /* access modifiers changed from: protected */
    public final int zzap() {
        return this.zzef;
    }

    static int zzb(int i, int i2, int i3) {
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
        objArr[2] = size() <= 50 ? zzgi.zzd(this) : String.valueOf(zzgi.zzd(zza(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public /* synthetic */ Iterator iterator() {
        return new zzcw(this);
    }
}
