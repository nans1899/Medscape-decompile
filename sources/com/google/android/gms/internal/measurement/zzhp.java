package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhr;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzhp<T extends zzhr<T>> {
    private static final zzhp zzd = new zzhp(true);
    final zzke<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    private zzhp() {
        this.zza = zzke.zza(16);
    }

    private zzhp(boolean z) {
        this(zzke.zza(0));
        zzb();
    }

    private zzhp(zzke<T, Object> zzke) {
        this.zza = zzke;
        zzb();
    }

    public static <T extends zzhr<T>> zzhp<T> zza() {
        return zzd;
    }

    public final void zzb() {
        if (!this.zzb) {
            this.zza.zza();
            this.zzb = true;
        }
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhp)) {
            return false;
        }
        return this.zza.equals(((zzhp) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zzc) {
            return new zzin(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> zze() {
        if (this.zzc) {
            return new zzin(this.zza.zze().iterator());
        }
        return this.zza.zze().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (!(obj instanceof zzim)) {
            return obj;
        }
        zzim zzim = (zzim) obj;
        return zzim.zza();
    }

    private final void zzb(T t, Object obj) {
        if (!t.zzd()) {
            zza(t.zzb(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(t.zzb(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzim) {
            this.zzc = true;
        }
        this.zza.put(t, obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        if ((r3 instanceof com.google.android.gms.internal.measurement.zzib) == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001c, code lost:
        if ((r3 instanceof com.google.android.gms.internal.measurement.zzim) == false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.measurement.zzli r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.measurement.zzic.zza(r3)
            int[] r0 = com.google.android.gms.internal.measurement.zzhs.zza
            com.google.android.gms.internal.measurement.zzll r2 = r2.zza()
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
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzjh
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzim
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x001f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzib
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzgr
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhp.zza(com.google.android.gms.internal.measurement.zzli, java.lang.Object):void");
    }

    public final boolean zzf() {
        for (int i = 0; i < this.zza.zzc(); i++) {
            if (!zza(this.zza.zzb(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> zza2 : this.zza.zzd()) {
            if (!zza(zza2)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzhr<T>> boolean zza(Map.Entry<T, Object> entry) {
        zzhr zzhr = (zzhr) entry.getKey();
        if (zzhr.zzc() == zzll.MESSAGE) {
            if (zzhr.zzd()) {
                for (zzjh i_ : (List) entry.getValue()) {
                    if (!i_.i_()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzjh) {
                    if (!((zzjh) value).i_()) {
                        return false;
                    }
                } else if (value instanceof zzim) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzhp<T> zzhp) {
        for (int i = 0; i < zzhp.zza.zzc(); i++) {
            zzb(zzhp.zza.zzb(i));
        }
        for (Map.Entry<T, Object> zzb2 : zzhp.zza.zzd()) {
            zzb(zzb2);
        }
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzjq) {
            return ((zzjq) obj).zza();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        Object obj;
        zzhr zzhr = (zzhr) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzim) {
            zzim zzim = (zzim) value;
            value = zzim.zza();
        }
        if (zzhr.zzd()) {
            Object zza2 = zza(zzhr);
            if (zza2 == null) {
                zza2 = new ArrayList();
            }
            for (Object zza3 : (List) value) {
                ((List) zza2).add(zza(zza3));
            }
            this.zza.put(zzhr, zza2);
        } else if (zzhr.zzc() == zzll.MESSAGE) {
            Object zza4 = zza(zzhr);
            if (zza4 == null) {
                this.zza.put(zzhr, zza(value));
                return;
            }
            if (zza4 instanceof zzjq) {
                obj = zzhr.zza((zzjq) zza4, (zzjq) value);
            } else {
                obj = zzhr.zza(((zzjh) zza4).zzbs(), (zzjh) value).zzz();
            }
            this.zza.put(zzhr, obj);
        } else {
            this.zza.put(zzhr, zza(value));
        }
    }

    static void zza(zzhg zzhg, zzli zzli, int i, Object obj) throws IOException {
        if (zzli == zzli.GROUP) {
            zzjh zzjh = (zzjh) obj;
            zzic.zza(zzjh);
            zzhg.zza(i, 3);
            zzjh.zza(zzhg);
            zzhg.zza(i, 4);
            return;
        }
        zzhg.zza(i, zzli.zzb());
        switch (zzhs.zzb[zzli.ordinal()]) {
            case 1:
                zzhg.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzhg.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzhg.zza(((Long) obj).longValue());
                return;
            case 4:
                zzhg.zza(((Long) obj).longValue());
                return;
            case 5:
                zzhg.zza(((Integer) obj).intValue());
                return;
            case 6:
                zzhg.zzc(((Long) obj).longValue());
                return;
            case 7:
                zzhg.zzd(((Integer) obj).intValue());
                return;
            case 8:
                zzhg.zza(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzjh) obj).zza(zzhg);
                return;
            case 10:
                zzhg.zza((zzjh) obj);
                return;
            case 11:
                if (obj instanceof zzgr) {
                    zzhg.zza((zzgr) obj);
                    return;
                } else {
                    zzhg.zza((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzgr) {
                    zzhg.zza((zzgr) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzhg.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzhg.zzb(((Integer) obj).intValue());
                return;
            case 14:
                zzhg.zzd(((Integer) obj).intValue());
                return;
            case 15:
                zzhg.zzc(((Long) obj).longValue());
                return;
            case 16:
                zzhg.zzc(((Integer) obj).intValue());
                return;
            case 17:
                zzhg.zzb(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzib) {
                    zzhg.zza(((zzib) obj).zza());
                    return;
                } else {
                    zzhg.zza(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzg() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzc(); i2++) {
            i += zzc(this.zza.zzb(i2));
        }
        for (Map.Entry<T, Object> zzc2 : this.zza.zzd()) {
            i += zzc(zzc2);
        }
        return i;
    }

    private static int zzc(Map.Entry<T, Object> entry) {
        zzhr zzhr = (zzhr) entry.getKey();
        Object value = entry.getValue();
        if (zzhr.zzc() != zzll.MESSAGE || zzhr.zzd() || zzhr.zze()) {
            return zza((zzhr<?>) zzhr, value);
        }
        if (value instanceof zzim) {
            return zzhg.zzb(((zzhr) entry.getKey()).zza(), (zziq) (zzim) value);
        }
        return zzhg.zzb(((zzhr) entry.getKey()).zza(), (zzjh) value);
    }

    static int zza(zzli zzli, int i, Object obj) {
        int zze = zzhg.zze(i);
        if (zzli == zzli.GROUP) {
            zzic.zza((zzjh) obj);
            zze <<= 1;
        }
        return zze + zzb(zzli, obj);
    }

    private static int zzb(zzli zzli, Object obj) {
        switch (zzhs.zzb[zzli.ordinal()]) {
            case 1:
                return zzhg.zzb(((Double) obj).doubleValue());
            case 2:
                return zzhg.zzb(((Float) obj).floatValue());
            case 3:
                return zzhg.zzd(((Long) obj).longValue());
            case 4:
                return zzhg.zze(((Long) obj).longValue());
            case 5:
                return zzhg.zzf(((Integer) obj).intValue());
            case 6:
                return zzhg.zzg(((Long) obj).longValue());
            case 7:
                return zzhg.zzi(((Integer) obj).intValue());
            case 8:
                return zzhg.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzhg.zzc((zzjh) obj);
            case 10:
                if (obj instanceof zzim) {
                    return zzhg.zza((zziq) (zzim) obj);
                }
                return zzhg.zzb((zzjh) obj);
            case 11:
                if (obj instanceof zzgr) {
                    return zzhg.zzb((zzgr) obj);
                }
                return zzhg.zzb((String) obj);
            case 12:
                if (obj instanceof zzgr) {
                    return zzhg.zzb((zzgr) obj);
                }
                return zzhg.zzb((byte[]) obj);
            case 13:
                return zzhg.zzg(((Integer) obj).intValue());
            case 14:
                return zzhg.zzj(((Integer) obj).intValue());
            case 15:
                return zzhg.zzh(((Long) obj).longValue());
            case 16:
                return zzhg.zzh(((Integer) obj).intValue());
            case 17:
                return zzhg.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzib) {
                    return zzhg.zzk(((zzib) obj).zza());
                }
                return zzhg.zzk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzhr<?> zzhr, Object obj) {
        zzli zzb2 = zzhr.zzb();
        int zza2 = zzhr.zza();
        if (!zzhr.zzd()) {
            return zza(zzb2, zza2, obj);
        }
        int i = 0;
        if (zzhr.zze()) {
            for (Object zzb3 : (List) obj) {
                i += zzb(zzb2, zzb3);
            }
            return zzhg.zze(zza2) + i + zzhg.zzl(i);
        }
        for (Object zza3 : (List) obj) {
            i += zza(zzb2, zza2, zza3);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzhp zzhp = new zzhp();
        for (int i = 0; i < this.zza.zzc(); i++) {
            Map.Entry<T, Object> zzb2 = this.zza.zzb(i);
            zzhp.zzb((zzhr) zzb2.getKey(), zzb2.getValue());
        }
        for (Map.Entry next : this.zza.zzd()) {
            zzhp.zzb((zzhr) next.getKey(), next.getValue());
        }
        zzhp.zzc = this.zzc;
        return zzhp;
    }
}
