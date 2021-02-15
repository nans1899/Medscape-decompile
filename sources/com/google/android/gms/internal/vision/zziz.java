package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zziz extends zzix {
    private static final Class<?> zzzw = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zziz() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzld.zzp(obj, j);
        if (list instanceof zziu) {
            obj2 = ((zziu) list).zzht();
        } else if (!zzzw.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzjz) || !(list instanceof zzik)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzik zzik = (zzik) list;
                if (zzik.zzei()) {
                    zzik.zzej();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzld.zza(obj, j, obj2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.google.android.gms.internal.vision.zziv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: com.google.android.gms.internal.vision.zziv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: com.google.android.gms.internal.vision.zziv} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <L> java.util.List<L> zza(java.lang.Object r3, long r4, int r6) {
        /*
            java.util.List r0 = zzd(r3, r4)
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x002d
            boolean r1 = r0 instanceof com.google.android.gms.internal.vision.zziu
            if (r1 == 0) goto L_0x0014
            com.google.android.gms.internal.vision.zziv r0 = new com.google.android.gms.internal.vision.zziv
            r0.<init>((int) r6)
            goto L_0x0029
        L_0x0014:
            boolean r1 = r0 instanceof com.google.android.gms.internal.vision.zzjz
            if (r1 == 0) goto L_0x0024
            boolean r1 = r0 instanceof com.google.android.gms.internal.vision.zzik
            if (r1 == 0) goto L_0x0024
            com.google.android.gms.internal.vision.zzik r0 = (com.google.android.gms.internal.vision.zzik) r0
            com.google.android.gms.internal.vision.zzik r6 = r0.zzan(r6)
            r0 = r6
            goto L_0x0029
        L_0x0024:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r6)
        L_0x0029:
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r0)
            goto L_0x007f
        L_0x002d:
            java.lang.Class<?> r1 = zzzw
            java.lang.Class r2 = r0.getClass()
            boolean r1 = r1.isAssignableFrom(r2)
            if (r1 == 0) goto L_0x004b
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.size()
            int r2 = r2 + r6
            r1.<init>(r2)
            r1.addAll(r0)
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r1)
        L_0x0049:
            r0 = r1
            goto L_0x007f
        L_0x004b:
            boolean r1 = r0 instanceof com.google.android.gms.internal.vision.zzky
            if (r1 == 0) goto L_0x0062
            com.google.android.gms.internal.vision.zziv r1 = new com.google.android.gms.internal.vision.zziv
            int r2 = r0.size()
            int r2 = r2 + r6
            r1.<init>((int) r2)
            com.google.android.gms.internal.vision.zzky r0 = (com.google.android.gms.internal.vision.zzky) r0
            r1.addAll(r0)
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r1)
            goto L_0x0049
        L_0x0062:
            boolean r1 = r0 instanceof com.google.android.gms.internal.vision.zzjz
            if (r1 == 0) goto L_0x007f
            boolean r1 = r0 instanceof com.google.android.gms.internal.vision.zzik
            if (r1 == 0) goto L_0x007f
            r1 = r0
            com.google.android.gms.internal.vision.zzik r1 = (com.google.android.gms.internal.vision.zzik) r1
            boolean r2 = r1.zzei()
            if (r2 != 0) goto L_0x007f
            int r0 = r0.size()
            int r0 = r0 + r6
            com.google.android.gms.internal.vision.zzik r0 = r1.zzan(r0)
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r0)
        L_0x007f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zziz.zza(java.lang.Object, long, int):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzd = zzd(obj2, j);
        List zza = zza(obj, j, zzd.size());
        int size = zza.size();
        int size2 = zzd.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzd);
        }
        if (size > 0) {
            zzd = zza;
        }
        zzld.zza(obj, j, (Object) zzd);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzld.zzp(obj, j);
    }
}
