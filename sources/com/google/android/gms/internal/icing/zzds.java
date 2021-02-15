package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzds<T extends zzdu<T>> {
    private static final zzds zzhn = new zzds(true);
    final zzfz<T, Object> zzhk;
    private boolean zzhl;
    private boolean zzhm;

    private zzds() {
        this.zzhk = zzfz.zzai(16);
    }

    private zzds(boolean z) {
        this(zzfz.zzai(0));
        zzai();
    }

    private zzds(zzfz<T, Object> zzfz) {
        this.zzhk = zzfz;
        zzai();
    }

    public static <T extends zzdu<T>> zzds<T> zzbd() {
        return zzhn;
    }

    public final void zzai() {
        if (!this.zzhl) {
            this.zzhk.zzai();
            this.zzhl = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzhl;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzds)) {
            return false;
        }
        return this.zzhk.equals(((zzds) obj).zzhk);
    }

    public final int hashCode() {
        return this.zzhk.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> iterator() {
        if (this.zzhm) {
            return new zzen(this.zzhk.entrySet().iterator());
        }
        return this.zzhk.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> descendingIterator() {
        if (this.zzhm) {
            return new zzen(this.zzhk.zzdf().iterator());
        }
        return this.zzhk.zzdf().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zzhk.get(t);
        if (!(obj instanceof zzei)) {
            return obj;
        }
        zzei zzei = (zzei) obj;
        return zzei.zzca();
    }

    private final void zza(T t, Object obj) {
        if (!t.zzbi()) {
            zza(t.zzbg(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(t.zzbg(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzei) {
            this.zzhm = true;
        }
        this.zzhk.put(t, obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        if ((r3 instanceof com.google.android.gms.internal.icing.zzec) == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001c, code lost:
        if ((r3 instanceof com.google.android.gms.internal.icing.zzei) == false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.icing.zzha r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.icing.zzeb.checkNotNull(r3)
            int[] r0 = com.google.android.gms.internal.icing.zzdr.zzhi
            com.google.android.gms.internal.icing.zzhh r2 = r2.zzdt()
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
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.zzfh
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.zzei
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x001f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x0042
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.zzec
            if (r2 == 0) goto L_0x0014
            goto L_0x0042
        L_0x0028:
            boolean r2 = r3 instanceof com.google.android.gms.internal.icing.zzct
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzds.zza(com.google.android.gms.internal.icing.zzha, java.lang.Object):void");
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.zzhk.zzdd(); i++) {
            if (!zzb(this.zzhk.zzaj(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> zzb : this.zzhk.zzde()) {
            if (!zzb(zzb)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzdu<T>> boolean zzb(Map.Entry<T, Object> entry) {
        zzdu zzdu = (zzdu) entry.getKey();
        if (zzdu.zzbh() == zzhh.MESSAGE) {
            if (zzdu.zzbi()) {
                for (zzfh isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzfh) {
                    if (!((zzfh) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzei) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzds<T> zzds) {
        for (int i = 0; i < zzds.zzhk.zzdd(); i++) {
            zzc(zzds.zzhk.zzaj(i));
        }
        for (Map.Entry<T, Object> zzc : zzds.zzhk.zzde()) {
            zzc(zzc);
        }
    }

    private static Object zzg(Object obj) {
        if (obj instanceof zzfn) {
            return ((zzfn) obj).zzaf();
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
        zzdu zzdu = (zzdu) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzei) {
            zzei zzei = (zzei) value;
            value = zzei.zzca();
        }
        if (zzdu.zzbi()) {
            Object zza = zza(zzdu);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object zzg : (List) value) {
                ((List) zza).add(zzg(zzg));
            }
            this.zzhk.put(zzdu, zza);
        } else if (zzdu.zzbh() == zzhh.MESSAGE) {
            Object zza2 = zza(zzdu);
            if (zza2 == null) {
                this.zzhk.put(zzdu, zzg(value));
                return;
            }
            if (zza2 instanceof zzfn) {
                obj = zzdu.zza((zzfn) zza2, (zzfn) value);
            } else {
                obj = zzdu.zza(((zzfh) zza2).zzbq(), (zzfh) value).zzbx();
            }
            this.zzhk.put(zzdu, obj);
        } else {
            this.zzhk.put(zzdu, zzg(value));
        }
    }

    static void zza(zzdk zzdk, zzha zzha, int i, Object obj) throws IOException {
        if (zzha == zzha.GROUP) {
            zzfh zzfh = (zzfh) obj;
            zzeb.zzf(zzfh);
            zzdk.zzb(i, 3);
            zzfh.zzb(zzdk);
            zzdk.zzb(i, 4);
            return;
        }
        zzdk.zzb(i, zzha.zzdu());
        switch (zzdr.zzhj[zzha.ordinal()]) {
            case 1:
                zzdk.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzdk.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzdk.zzb(((Long) obj).longValue());
                return;
            case 4:
                zzdk.zzb(((Long) obj).longValue());
                return;
            case 5:
                zzdk.zzo(((Integer) obj).intValue());
                return;
            case 6:
                zzdk.zzd(((Long) obj).longValue());
                return;
            case 7:
                zzdk.zzr(((Integer) obj).intValue());
                return;
            case 8:
                zzdk.zze(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzfh) obj).zzb(zzdk);
                return;
            case 10:
                zzdk.zzb((zzfh) obj);
                return;
            case 11:
                if (obj instanceof zzct) {
                    zzdk.zza((zzct) obj);
                    return;
                } else {
                    zzdk.zzq((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzct) {
                    zzdk.zza((zzct) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzdk.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzdk.zzp(((Integer) obj).intValue());
                return;
            case 14:
                zzdk.zzr(((Integer) obj).intValue());
                return;
            case 15:
                zzdk.zzd(((Long) obj).longValue());
                return;
            case 16:
                zzdk.zzq(((Integer) obj).intValue());
                return;
            case 17:
                zzdk.zzc(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzec) {
                    zzdk.zzo(((zzec) obj).zzbf());
                    return;
                } else {
                    zzdk.zzo(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzbe() {
        int i = 0;
        for (int i2 = 0; i2 < this.zzhk.zzdd(); i2++) {
            i += zzd(this.zzhk.zzaj(i2));
        }
        for (Map.Entry<T, Object> zzd : this.zzhk.zzde()) {
            i += zzd(zzd);
        }
        return i;
    }

    private static int zzd(Map.Entry<T, Object> entry) {
        zzdu zzdu = (zzdu) entry.getKey();
        Object value = entry.getValue();
        if (zzdu.zzbh() != zzhh.MESSAGE || zzdu.zzbi() || zzdu.zzbj()) {
            return zzb((zzdu<?>) zzdu, value);
        }
        if (value instanceof zzei) {
            return zzdk.zzb(((zzdu) entry.getKey()).zzbf(), (zzem) (zzei) value);
        }
        return zzdk.zzb(((zzdu) entry.getKey()).zzbf(), (zzfh) value);
    }

    static int zza(zzha zzha, int i, Object obj) {
        int zzs = zzdk.zzs(i);
        if (zzha == zzha.GROUP) {
            zzeb.zzf((zzfh) obj);
            zzs <<= 1;
        }
        return zzs + zzb(zzha, obj);
    }

    private static int zzb(zzha zzha, Object obj) {
        switch (zzdr.zzhj[zzha.ordinal()]) {
            case 1:
                return zzdk.zzb(((Double) obj).doubleValue());
            case 2:
                return zzdk.zzb(((Float) obj).floatValue());
            case 3:
                return zzdk.zze(((Long) obj).longValue());
            case 4:
                return zzdk.zzf(((Long) obj).longValue());
            case 5:
                return zzdk.zzt(((Integer) obj).intValue());
            case 6:
                return zzdk.zzh(((Long) obj).longValue());
            case 7:
                return zzdk.zzw(((Integer) obj).intValue());
            case 8:
                return zzdk.zzf(((Boolean) obj).booleanValue());
            case 9:
                return zzdk.zzd((zzfh) obj);
            case 10:
                if (obj instanceof zzei) {
                    return zzdk.zza((zzem) (zzei) obj);
                }
                return zzdk.zzc((zzfh) obj);
            case 11:
                if (obj instanceof zzct) {
                    return zzdk.zzb((zzct) obj);
                }
                return zzdk.zzr((String) obj);
            case 12:
                if (obj instanceof zzct) {
                    return zzdk.zzb((zzct) obj);
                }
                return zzdk.zzc((byte[]) obj);
            case 13:
                return zzdk.zzu(((Integer) obj).intValue());
            case 14:
                return zzdk.zzx(((Integer) obj).intValue());
            case 15:
                return zzdk.zzi(((Long) obj).longValue());
            case 16:
                return zzdk.zzv(((Integer) obj).intValue());
            case 17:
                return zzdk.zzg(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzec) {
                    return zzdk.zzy(((zzec) obj).zzbf());
                }
                return zzdk.zzy(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zzb(zzdu<?> zzdu, Object obj) {
        zzha zzbg = zzdu.zzbg();
        int zzbf = zzdu.zzbf();
        if (!zzdu.zzbi()) {
            return zza(zzbg, zzbf, obj);
        }
        int i = 0;
        if (zzdu.zzbj()) {
            for (Object zzb : (List) obj) {
                i += zzb(zzbg, zzb);
            }
            return zzdk.zzs(zzbf) + i + zzdk.zzaa(i);
        }
        for (Object zza : (List) obj) {
            i += zza(zzbg, zzbf, zza);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzds zzds = new zzds();
        for (int i = 0; i < this.zzhk.zzdd(); i++) {
            Map.Entry<T, Object> zzaj = this.zzhk.zzaj(i);
            zzds.zza((zzdu) zzaj.getKey(), zzaj.getValue());
        }
        for (Map.Entry next : this.zzhk.zzde()) {
            zzds.zza((zzdu) next.getKey(), next.getValue());
        }
        zzds.zzhm = this.zzhm;
        return zzds;
    }
}
