package com.google.android.gms.internal.ads;

import com.comscore.streaming.AdvertisementType;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzemc extends zzeip {
    static final int[] zzimo = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, AdvertisementType.BRANDED_ON_DEMAND_POST_ROLL, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private final int zzimp;
    /* access modifiers changed from: private */
    public final zzeip zzimq;
    /* access modifiers changed from: private */
    public final zzeip zzimr;
    private final int zzims;
    private final int zzimt;

    private zzemc(zzeip zzeip, zzeip zzeip2) {
        this.zzimq = zzeip;
        this.zzimr = zzeip2;
        int size = zzeip.size();
        this.zzims = size;
        this.zzimp = size + zzeip2.size();
        this.zzimt = Math.max(zzeip.zzbej(), zzeip2.zzbej()) + 1;
    }

    static zzeip zza(zzeip zzeip, zzeip zzeip2) {
        if (zzeip2.size() == 0) {
            return zzeip;
        }
        if (zzeip.size() == 0) {
            return zzeip2;
        }
        int size = zzeip.size() + zzeip2.size();
        if (size < 128) {
            return zzb(zzeip, zzeip2);
        }
        if (zzeip instanceof zzemc) {
            zzemc zzemc = (zzemc) zzeip;
            if (zzemc.zzimr.size() + zzeip2.size() < 128) {
                return new zzemc(zzemc.zzimq, zzb(zzemc.zzimr, zzeip2));
            } else if (zzemc.zzimq.zzbej() > zzemc.zzimr.zzbej() && zzemc.zzbej() > zzeip2.zzbej()) {
                return new zzemc(zzemc.zzimq, new zzemc(zzemc.zzimr, zzeip2));
            }
        }
        if (size >= zzhm(Math.max(zzeip.zzbej(), zzeip2.zzbej()) + 1)) {
            return new zzemc(zzeip, zzeip2);
        }
        return new zzeme((zzemb) null).zzc(zzeip, zzeip2);
    }

    private static zzeip zzb(zzeip zzeip, zzeip zzeip2) {
        int size = zzeip.size();
        int size2 = zzeip2.size();
        byte[] bArr = new byte[(size + size2)];
        zzeip.zza(bArr, 0, 0, size);
        zzeip2.zza(bArr, 0, size, size2);
        return zzeip.zzv(bArr);
    }

    static int zzhm(int i) {
        int[] iArr = zzimo;
        if (i >= iArr.length) {
            return Integer.MAX_VALUE;
        }
        return iArr[i];
    }

    public final byte zzfu(int i) {
        zzaa(i, this.zzimp);
        return zzfv(i);
    }

    /* access modifiers changed from: package-private */
    public final byte zzfv(int i) {
        int i2 = this.zzims;
        if (i < i2) {
            return this.zzimq.zzfv(i);
        }
        return this.zzimr.zzfv(i - i2);
    }

    public final int size() {
        return this.zzimp;
    }

    public final zzeiu zzbee() {
        return new zzemb(this);
    }

    /* access modifiers changed from: protected */
    public final int zzbej() {
        return this.zzimt;
    }

    /* access modifiers changed from: protected */
    public final boolean zzbek() {
        return this.zzimp >= zzhm(this.zzimt);
    }

    public final zzeip zzz(int i, int i2) {
        int zzi = zzi(i, i2, this.zzimp);
        if (zzi == 0) {
            return zzeip.zzier;
        }
        if (zzi == this.zzimp) {
            return this;
        }
        int i3 = this.zzims;
        if (i2 <= i3) {
            return this.zzimq.zzz(i, i2);
        }
        if (i >= i3) {
            return this.zzimr.zzz(i - i3, i2 - i3);
        }
        zzeip zzeip = this.zzimq;
        return new zzemc(zzeip.zzz(i, zzeip.size()), this.zzimr.zzz(0, i2 - this.zzims));
    }

    /* access modifiers changed from: protected */
    public final void zzb(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.zzims;
        if (i4 <= i5) {
            this.zzimq.zzb(bArr, i, i2, i3);
        } else if (i >= i5) {
            this.zzimr.zzb(bArr, i - i5, i2, i3);
        } else {
            int i6 = i5 - i;
            this.zzimq.zzb(bArr, i, i2, i6);
            this.zzimr.zzb(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzeim zzeim) throws IOException {
        this.zzimq.zza(zzeim);
        this.zzimr.zza(zzeim);
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(toByteArray(), charset);
    }

    public final boolean zzbeg() {
        int zzg = this.zzimq.zzg(0, 0, this.zzims);
        zzeip zzeip = this.zzimr;
        if (zzeip.zzg(zzg, 0, zzeip.size()) == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final int zzg(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzims;
        if (i4 <= i5) {
            return this.zzimq.zzg(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zzimr.zzg(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zzimr.zzg(this.zzimq.zzg(i, i2, i6), 0, i3 - i6);
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeip)) {
            return false;
        }
        zzeip zzeip = (zzeip) obj;
        if (this.zzimp != zzeip.size()) {
            return false;
        }
        if (this.zzimp == 0) {
            return true;
        }
        int zzbel = zzbel();
        int zzbel2 = zzeip.zzbel();
        if (zzbel != 0 && zzbel2 != 0 && zzbel != zzbel2) {
            return false;
        }
        zzemd zzemd = new zzemd(this, (zzemb) null);
        zzeiw zzeiw = (zzeiw) zzemd.next();
        zzemd zzemd2 = new zzemd(zzeip, (zzemb) null);
        zzeiw zzeiw2 = (zzeiw) zzemd2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int size = zzeiw.size() - i;
            int size2 = zzeiw2.size() - i2;
            int min = Math.min(size, size2);
            if (i == 0) {
                z = zzeiw.zza(zzeiw2, i2, min);
            } else {
                z = zzeiw2.zza(zzeiw, i, min);
            }
            if (!z) {
                return false;
            }
            i3 += min;
            int i4 = this.zzimp;
            if (i3 < i4) {
                if (min == size) {
                    zzeiw = (zzeiw) zzemd.next();
                    i = 0;
                } else {
                    i += min;
                }
                if (min == size2) {
                    zzeiw2 = (zzeiw) zzemd2.next();
                    i2 = 0;
                } else {
                    i2 += min;
                }
            } else if (i3 == i4) {
                return true;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final int zzh(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzims;
        if (i4 <= i5) {
            return this.zzimq.zzh(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zzimr.zzh(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zzimr.zzh(this.zzimq.zzh(i, i2, i6), 0, i3 - i6);
    }

    public final zzeja zzbeh() {
        return new zzejf(new zzemg(this));
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    /* synthetic */ zzemc(zzeip zzeip, zzeip zzeip2, zzemb zzemb) {
        this(zzeip, zzeip2);
    }
}
