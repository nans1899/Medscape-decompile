package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzeju;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzejs<T extends zzeju<T>> {
    private static final zzejs zzigk = new zzejs(true);
    final zzemk<T, Object> zzigh;
    private boolean zzigi;
    private boolean zzigj;

    private zzejs() {
        this.zzigh = zzemk.zzho(16);
    }

    private zzejs(boolean z) {
        this(zzemk.zzho(0));
        zzbec();
    }

    private zzejs(zzemk<T, Object> zzemk) {
        this.zzigh = zzemk;
        zzbec();
    }

    public static <T extends zzeju<T>> zzejs<T> zzbfy() {
        return zzigk;
    }

    public final void zzbec() {
        if (!this.zzigi) {
            this.zzigh.zzbec();
            this.zzigi = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzigi;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzejs)) {
            return false;
        }
        return this.zzigh.equals(((zzejs) obj).zzigh);
    }

    public final int hashCode() {
        return this.zzigh.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> iterator() {
        if (this.zzigj) {
            return new zzekp(this.zzigh.entrySet().iterator());
        }
        return this.zzigh.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> descendingIterator() {
        if (this.zzigj) {
            return new zzekp(this.zzigh.zzbio().iterator());
        }
        return this.zzigh.zzbio().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zzigh.get(t);
        if (!(obj instanceof zzeko)) {
            return obj;
        }
        zzeko zzeko = (zzeko) obj;
        return zzeko.zzbhh();
    }

    private final void zza(T t, Object obj) {
        if (!t.zzbgc()) {
            zza(t.zzbga(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(t.zzbga(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzeko) {
            this.zzigj = true;
        }
        this.zzigh.put(t, obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        if ((r3 instanceof com.google.android.gms.internal.ads.zzeke) == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001c, code lost:
        if ((r3 instanceof com.google.android.gms.internal.ads.zzeko) == false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.ads.zzeno r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.ads.zzekb.checkNotNull(r3)
            int[] r0 = com.google.android.gms.internal.ads.zzejr.zzigg
            com.google.android.gms.internal.ads.zzenr r2 = r2.zzbje()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L_0x0040;
                case 2: goto L_0x003d;
                case 3: goto L_0x003a;
                case 4: goto L_0x0037;
                case 5: goto L_0x0034;
                case 6: goto L_0x0031;
                case 7: goto L_0x0028;
                case 8: goto L_0x001f;
                case 9: goto L_0x0016;
                default: goto L_0x0014;
            }
        L_0x0014:
            r0 = 0
            goto L_0x0042
        L_0x0016:
            boolean r2 = r3 instanceof com.google.android.gms.internal.ads.zzelj
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof com.google.android.gms.internal.ads.zzeko
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x001f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof com.google.android.gms.internal.ads.zzeke
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.ads.zzeip
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x0031:
            boolean r0 = r3 instanceof java.lang.String
            goto L_0x0042
        L_0x0034:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L_0x0042
        L_0x0037:
            boolean r0 = r3 instanceof java.lang.Double
            goto L_0x0042
        L_0x003a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L_0x0042
        L_0x003d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L_0x0042
        L_0x0040:
            boolean r0 = r3 instanceof java.lang.Integer
        L_0x0042:
            if (r0 == 0) goto L_0x0045
            return
        L_0x0045:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzejs.zza(com.google.android.gms.internal.ads.zzeno, java.lang.Object):void");
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzigh.zzbim(); i++) {
            if (!zzb(this.zzigh.zzhp(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> zzb : this.zzigh.zzbin()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzeju<T>> boolean zzb(Map.Entry<T, Object> entry) {
        zzeju zzeju = (zzeju) entry.getKey();
        if (zzeju.zzbgb() == zzenr.MESSAGE) {
            if (zzeju.zzbgc()) {
                for (zzelj isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzelj) {
                    if (!((zzelj) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzeko) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzejs<T> zzejs) {
        for (int i = 0; i < zzejs.zzigh.zzbim(); i++) {
            zzc(zzejs.zzigh.zzhp(i));
        }
        for (Map.Entry<T, Object> zzc : zzejs.zzigh.zzbin()) {
            zzc(zzc);
        }
    }

    private static Object zzak(Object obj) {
        if (obj instanceof zzels) {
            return ((zzels) obj).zzbdy();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzc(Map.Entry<T, Object> entry) {
        Object obj;
        zzeju zzeju = (zzeju) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzeko) {
            zzeko zzeko = (zzeko) value;
            value = zzeko.zzbhh();
        }
        if (zzeju.zzbgc()) {
            Object zza = zza(zzeju);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzak : (List) value) {
                ((List) zza).add(zzak(zzak));
            }
            this.zzigh.put(zzeju, zza);
        } else if (zzeju.zzbgb() == zzenr.MESSAGE) {
            Object zza2 = zza(zzeju);
            if (zza2 == null) {
                this.zzigh.put(zzeju, zzak(value));
                return;
            }
            if (zza2 instanceof zzels) {
                obj = zzeju.zza((zzels) zza2, (zzels) value);
            } else {
                obj = zzeju.zza(((zzelj) zza2).zzbgl(), (zzelj) value).zzbgt();
            }
            this.zzigh.put(zzeju, obj);
        } else {
            this.zzigh.put(zzeju, zzak(value));
        }
    }

    static void zza(zzejj zzejj, zzeno zzeno, int i, Object obj) throws IOException {
        if (zzeno == zzeno.GROUP) {
            zzelj zzelj = (zzelj) obj;
            zzekb.zzk(zzelj);
            zzejj.writeTag(i, 3);
            zzelj.zzb(zzejj);
            zzejj.writeTag(i, 4);
            return;
        }
        zzejj.writeTag(i, zzeno.zzbjf());
        switch (zzejr.zzifq[zzeno.ordinal()]) {
            case 1:
                zzejj.zzc(((Double) obj).doubleValue());
                return;
            case 2:
                zzejj.zzf(((Float) obj).floatValue());
                return;
            case 3:
                zzejj.zzfi(((Long) obj).longValue());
                return;
            case 4:
                zzejj.zzfi(((Long) obj).longValue());
                return;
            case 5:
                zzejj.zzgm(((Integer) obj).intValue());
                return;
            case 6:
                zzejj.zzfk(((Long) obj).longValue());
                return;
            case 7:
                zzejj.zzgp(((Integer) obj).intValue());
                return;
            case 8:
                zzejj.zzbs(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzelj) obj).zzb(zzejj);
                return;
            case 10:
                zzejj.zzg((zzelj) obj);
                return;
            case 11:
                if (obj instanceof zzeip) {
                    zzejj.zzah((zzeip) obj);
                    return;
                } else {
                    zzejj.zzhv((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzeip) {
                    zzejj.zzah((zzeip) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzejj.zzk(bArr, 0, bArr.length);
                return;
            case 13:
                zzejj.zzgn(((Integer) obj).intValue());
                return;
            case 14:
                zzejj.zzgp(((Integer) obj).intValue());
                return;
            case 15:
                zzejj.zzfk(((Long) obj).longValue());
                return;
            case 16:
                zzejj.zzgo(((Integer) obj).intValue());
                return;
            case 17:
                zzejj.zzfj(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzeke) {
                    zzejj.zzgm(((zzeke) obj).zzv());
                    return;
                } else {
                    zzejj.zzgm(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzbfz() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzigh.zzbim(); i2++) {
            i += zzd(this.zzigh.zzhp(i2));
        }
        for (Map.Entry<T, Object> zzd : this.zzigh.zzbin()) {
            i += zzd(zzd);
        }
        return i;
    }

    private static int zzd(Map.Entry<T, Object> entry) {
        zzeju zzeju = (zzeju) entry.getKey();
        Object value = entry.getValue();
        if (zzeju.zzbgb() != zzenr.MESSAGE || zzeju.zzbgc() || zzeju.zzbgd()) {
            return zzb((zzeju<?>) zzeju, value);
        }
        if (value instanceof zzeko) {
            return zzejj.zzb(((zzeju) entry.getKey()).zzv(), (zzeks) (zzeko) value);
        }
        return zzejj.zzb(((zzeju) entry.getKey()).zzv(), (zzelj) value);
    }

    static int zza(zzeno zzeno, int i, Object obj) {
        int zzgq = zzejj.zzgq(i);
        if (zzeno == zzeno.GROUP) {
            zzekb.zzk((zzelj) obj);
            zzgq <<= 1;
        }
        return zzgq + zzb(zzeno, obj);
    }

    private static int zzb(zzeno zzeno, Object obj) {
        switch (zzejr.zzifq[zzeno.ordinal()]) {
            case 1:
                return zzejj.zzd(((Double) obj).doubleValue());
            case 2:
                return zzejj.zzg(((Float) obj).floatValue());
            case 3:
                return zzejj.zzfl(((Long) obj).longValue());
            case 4:
                return zzejj.zzfm(((Long) obj).longValue());
            case 5:
                return zzejj.zzgr(((Integer) obj).intValue());
            case 6:
                return zzejj.zzfo(((Long) obj).longValue());
            case 7:
                return zzejj.zzgu(((Integer) obj).intValue());
            case 8:
                return zzejj.zzbt(((Boolean) obj).booleanValue());
            case 9:
                return zzejj.zzi((zzelj) obj);
            case 10:
                if (obj instanceof zzeko) {
                    return zzejj.zza((zzeko) obj);
                }
                return zzejj.zzh((zzelj) obj);
            case 11:
                if (obj instanceof zzeip) {
                    return zzejj.zzai((zzeip) obj);
                }
                return zzejj.zzhw((String) obj);
            case 12:
                if (obj instanceof zzeip) {
                    return zzejj.zzai((zzeip) obj);
                }
                return zzejj.zzx((byte[]) obj);
            case 13:
                return zzejj.zzgs(((Integer) obj).intValue());
            case 14:
                return zzejj.zzgv(((Integer) obj).intValue());
            case 15:
                return zzejj.zzfp(((Long) obj).longValue());
            case 16:
                return zzejj.zzgt(((Integer) obj).intValue());
            case 17:
                return zzejj.zzfn(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzeke) {
                    return zzejj.zzgw(((zzeke) obj).zzv());
                }
                return zzejj.zzgw(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzb(zzeju<?> zzeju, Object obj) {
        zzeno zzbga = zzeju.zzbga();
        int zzv = zzeju.zzv();
        if (!zzeju.zzbgc()) {
            return zza(zzbga, zzv, obj);
        }
        int i = 0;
        if (zzeju.zzbgd()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzbga, zzb);
            }
            return zzejj.zzgq(zzv) + i + zzejj.zzgy(i);
        }
        for (Object zza : (List) obj) {
            i += zza(zzbga, zzv, zza);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzejs zzejs = new zzejs();
        for (int i = 0; i < this.zzigh.zzbim(); i++) {
            Map.Entry<T, Object> zzhp = this.zzigh.zzhp(i);
            zzejs.zza((zzeju) zzhp.getKey(), zzhp.getValue());
        }
        for (Map.Entry next : this.zzigh.zzbin()) {
            zzejs.zza((zzeju) next.getKey(), next.getValue());
        }
        zzejs.zzigj = this.zzigj;
        return zzejs;
    }
}
