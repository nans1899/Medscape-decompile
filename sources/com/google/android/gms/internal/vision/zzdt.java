package com.google.android.gms.internal.vision;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzdt extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzdp zzmo;

    zzdt(zzdp zzdp) {
        this.zzmo = zzdp;
    }

    public final int size() {
        return this.zzmo.size();
    }

    public final void clear() {
        this.zzmo.clear();
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return this.zzmo.zzck();
    }

    public final boolean contains(@NullableDecl Object obj) {
        Map zzcf = this.zzmo.zzcf();
        if (zzcf != null) {
            return zzcf.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            int zzb = this.zzmo.indexOf(entry.getKey());
            if (zzb == -1 || !zzcz.equal(this.zzmo.zzmj[zzb], entry.getValue())) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        r0 = r9.zzmo.zzcg();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean remove(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r10) {
        /*
            r9 = this;
            com.google.android.gms.internal.vision.zzdp r0 = r9.zzmo
            java.util.Map r0 = r0.zzcf()
            if (r0 == 0) goto L_0x0011
            java.util.Set r0 = r0.entrySet()
            boolean r10 = r0.remove(r10)
            return r10
        L_0x0011:
            boolean r0 = r10 instanceof java.util.Map.Entry
            r1 = 0
            if (r0 == 0) goto L_0x005b
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10
            com.google.android.gms.internal.vision.zzdp r0 = r9.zzmo
            boolean r0 = r0.zzce()
            if (r0 == 0) goto L_0x0021
            return r1
        L_0x0021:
            com.google.android.gms.internal.vision.zzdp r0 = r9.zzmo
            int r0 = r0.zzcg()
            java.lang.Object r2 = r10.getKey()
            java.lang.Object r3 = r10.getValue()
            com.google.android.gms.internal.vision.zzdp r10 = r9.zzmo
            java.lang.Object r5 = r10.zzmg
            com.google.android.gms.internal.vision.zzdp r10 = r9.zzmo
            int[] r6 = r10.zzmh
            com.google.android.gms.internal.vision.zzdp r10 = r9.zzmo
            java.lang.Object[] r7 = r10.zzmi
            com.google.android.gms.internal.vision.zzdp r10 = r9.zzmo
            java.lang.Object[] r8 = r10.zzmj
            r4 = r0
            int r10 = com.google.android.gms.internal.vision.zzea.zza(r2, r3, r4, r5, r6, r7, r8)
            r2 = -1
            if (r10 != r2) goto L_0x004a
            return r1
        L_0x004a:
            com.google.android.gms.internal.vision.zzdp r1 = r9.zzmo
            r1.zzf(r10, r0)
            com.google.android.gms.internal.vision.zzdp r10 = r9.zzmo
            com.google.android.gms.internal.vision.zzdp.zzd(r10)
            com.google.android.gms.internal.vision.zzdp r10 = r9.zzmo
            r10.zzch()
            r10 = 1
            return r10
        L_0x005b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzdt.remove(java.lang.Object):boolean");
    }
}
