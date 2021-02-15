package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdwv<E> extends zzdwn<E> {
    private int zzahv;
    @NullableDecl
    private Object[] zzhqw;

    public zzdwv() {
        super(4);
    }

    zzdwv(int i) {
        super(i);
        this.zzhqw = new Object[zzdws.zzen(i)];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.google.android.gms.internal.ads.zzdxf} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: com.google.android.gms.internal.ads.zzdws} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: com.google.android.gms.internal.ads.zzdxf} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: com.google.android.gms.internal.ads.zzdxf} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzdws<E> zzaxu() {
        /*
            r8 = this;
            int r0 = r8.size
            if (r0 == 0) goto L_0x005b
            r1 = 1
            if (r0 == r1) goto L_0x0051
            java.lang.Object[] r0 = r8.zzhqw
            if (r0 == 0) goto L_0x003d
            int r0 = r8.size
            int r0 = com.google.android.gms.internal.ads.zzdws.zzen(r0)
            java.lang.Object[] r2 = r8.zzhqw
            int r2 = r2.length
            if (r0 != r2) goto L_0x003d
            int r0 = r8.size
            java.lang.Object[] r2 = r8.zzhqk
            int r2 = r2.length
            boolean r0 = com.google.android.gms.internal.ads.zzdws.zzv(r0, r2)
            if (r0 == 0) goto L_0x002a
            java.lang.Object[] r0 = r8.zzhqk
            int r2 = r8.size
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r2)
            goto L_0x002c
        L_0x002a:
            java.lang.Object[] r0 = r8.zzhqk
        L_0x002c:
            r3 = r0
            com.google.android.gms.internal.ads.zzdxf r0 = new com.google.android.gms.internal.ads.zzdxf
            int r4 = r8.zzahv
            java.lang.Object[] r5 = r8.zzhqw
            int r2 = r5.length
            int r6 = r2 + -1
            int r7 = r8.size
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7)
            goto L_0x004b
        L_0x003d:
            int r0 = r8.size
            java.lang.Object[] r2 = r8.zzhqk
            com.google.android.gms.internal.ads.zzdws r0 = com.google.android.gms.internal.ads.zzdws.zza(r0, r2)
            int r2 = r0.size()
            r8.size = r2
        L_0x004b:
            r8.zzhql = r1
            r1 = 0
            r8.zzhqw = r1
            return r0
        L_0x0051:
            java.lang.Object[] r0 = r8.zzhqk
            r1 = 0
            r0 = r0[r1]
            com.google.android.gms.internal.ads.zzdws r0 = com.google.android.gms.internal.ads.zzdws.zzad(r0)
            return r0
        L_0x005b:
            com.google.android.gms.internal.ads.zzdxf<java.lang.Object> r0 = com.google.android.gms.internal.ads.zzdxf.zzhrf
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdwv.zzaxu():com.google.android.gms.internal.ads.zzdws");
    }

    public final /* synthetic */ zzdwm zzg(Iterable iterable) {
        zzdwd.checkNotNull(iterable);
        if (this.zzhqw != null) {
            for (Object zzaa : iterable) {
                zzaa(zzaa);
            }
        } else {
            super.zzg(iterable);
        }
        return this;
    }

    public final /* synthetic */ zzdwn zzab(Object obj) {
        return (zzdwv) zzaa(obj);
    }

    public final /* synthetic */ zzdwm zza(Iterator it) {
        zzdwd.checkNotNull(it);
        while (it.hasNext()) {
            zzaa(it.next());
        }
        return this;
    }

    public final /* synthetic */ zzdwm zzaa(Object obj) {
        zzdwd.checkNotNull(obj);
        if (this.zzhqw != null) {
            int zzen = zzdws.zzen(this.size);
            Object[] objArr = this.zzhqw;
            if (zzen <= objArr.length) {
                int length = objArr.length - 1;
                int hashCode = obj.hashCode();
                int zzel = zzdwl.zzel(hashCode);
                while (true) {
                    int i = zzel & length;
                    Object[] objArr2 = this.zzhqw;
                    Object obj2 = objArr2[i];
                    if (obj2 != null) {
                        if (obj2.equals(obj)) {
                            break;
                        }
                        zzel = i + 1;
                    } else {
                        objArr2[i] = obj;
                        this.zzahv += hashCode;
                        super.zzaa(obj);
                        break;
                    }
                }
                return this;
            }
        }
        this.zzhqw = null;
        super.zzaa(obj);
        return this;
    }
}
