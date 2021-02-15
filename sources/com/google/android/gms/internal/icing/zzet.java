package com.google.android.gms.internal.icing;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzet extends zzer {
    private static final Class<?> zzmc = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzet() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        Object obj2;
        List list = (List) zzgs.zzo(obj, j);
        if (list instanceof zzeo) {
            obj2 = ((zzeo) list).zzce();
        } else if (!zzmc.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzfq) || !(list instanceof zzee)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzee zzee = (zzee) list;
                if (zzee.zzah()) {
                    zzee.zzai();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzgs.zza(obj, j, obj2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.google.android.gms.internal.icing.zzep} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: com.google.android.gms.internal.icing.zzep} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: com.google.android.gms.internal.icing.zzep} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <E> void zza(java.lang.Object r5, java.lang.Object r6, long r7) {
        /*
            r4 = this;
            java.util.List r6 = zzc(r6, r7)
            int r0 = r6.size()
            java.util.List r1 = zzc(r5, r7)
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x0035
            boolean r2 = r1 instanceof com.google.android.gms.internal.icing.zzeo
            if (r2 == 0) goto L_0x001c
            com.google.android.gms.internal.icing.zzep r1 = new com.google.android.gms.internal.icing.zzep
            r1.<init>((int) r0)
            goto L_0x0031
        L_0x001c:
            boolean r2 = r1 instanceof com.google.android.gms.internal.icing.zzfq
            if (r2 == 0) goto L_0x002c
            boolean r2 = r1 instanceof com.google.android.gms.internal.icing.zzee
            if (r2 == 0) goto L_0x002c
            com.google.android.gms.internal.icing.zzee r1 = (com.google.android.gms.internal.icing.zzee) r1
            com.google.android.gms.internal.icing.zzee r0 = r1.zzj(r0)
            r1 = r0
            goto L_0x0031
        L_0x002c:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
        L_0x0031:
            com.google.android.gms.internal.icing.zzgs.zza((java.lang.Object) r5, (long) r7, (java.lang.Object) r1)
            goto L_0x0087
        L_0x0035:
            java.lang.Class<?> r2 = zzmc
            java.lang.Class r3 = r1.getClass()
            boolean r2 = r2.isAssignableFrom(r3)
            if (r2 == 0) goto L_0x0053
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = r1.size()
            int r3 = r3 + r0
            r2.<init>(r3)
            r2.addAll(r1)
            com.google.android.gms.internal.icing.zzgs.zza((java.lang.Object) r5, (long) r7, (java.lang.Object) r2)
        L_0x0051:
            r1 = r2
            goto L_0x0087
        L_0x0053:
            boolean r2 = r1 instanceof com.google.android.gms.internal.icing.zzgr
            if (r2 == 0) goto L_0x006a
            com.google.android.gms.internal.icing.zzep r2 = new com.google.android.gms.internal.icing.zzep
            int r3 = r1.size()
            int r3 = r3 + r0
            r2.<init>((int) r3)
            com.google.android.gms.internal.icing.zzgr r1 = (com.google.android.gms.internal.icing.zzgr) r1
            r2.addAll(r1)
            com.google.android.gms.internal.icing.zzgs.zza((java.lang.Object) r5, (long) r7, (java.lang.Object) r2)
            goto L_0x0051
        L_0x006a:
            boolean r2 = r1 instanceof com.google.android.gms.internal.icing.zzfq
            if (r2 == 0) goto L_0x0087
            boolean r2 = r1 instanceof com.google.android.gms.internal.icing.zzee
            if (r2 == 0) goto L_0x0087
            r2 = r1
            com.google.android.gms.internal.icing.zzee r2 = (com.google.android.gms.internal.icing.zzee) r2
            boolean r3 = r2.zzah()
            if (r3 != 0) goto L_0x0087
            int r1 = r1.size()
            int r1 = r1 + r0
            com.google.android.gms.internal.icing.zzee r1 = r2.zzj(r1)
            com.google.android.gms.internal.icing.zzgs.zza((java.lang.Object) r5, (long) r7, (java.lang.Object) r1)
        L_0x0087:
            int r0 = r1.size()
            int r2 = r6.size()
            if (r0 <= 0) goto L_0x0096
            if (r2 <= 0) goto L_0x0096
            r1.addAll(r6)
        L_0x0096:
            if (r0 <= 0) goto L_0x0099
            r6 = r1
        L_0x0099:
            com.google.android.gms.internal.icing.zzgs.zza((java.lang.Object) r5, (long) r7, (java.lang.Object) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzet.zza(java.lang.Object, java.lang.Object, long):void");
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzgs.zzo(obj, j);
    }
}
