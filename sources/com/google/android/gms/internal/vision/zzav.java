package com.google.android.gms.internal.vision;

import android.content.Context;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzav extends zzbr {
    private final zzdf<zzcy<zzbe>> zzfw;
    private final Context zzg;

    zzav(Context context, @Nullable zzdf<zzcy<zzbe>> zzdf) {
        if (context != null) {
            this.zzg = context;
            this.zzfw = zzdf;
            return;
        }
        throw new NullPointerException("Null context");
    }

    /* access modifiers changed from: package-private */
    public final Context zzaa() {
        return this.zzg;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final zzdf<zzcy<zzbe>> zzab() {
        return this.zzfw;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzg);
        String valueOf2 = String.valueOf(this.zzfw);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46 + String.valueOf(valueOf2).length());
        sb.append("FlagsContext{context=");
        sb.append(valueOf);
        sb.append(", hermeticFileOverrides=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r1 = r4.zzfw;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.gms.internal.vision.zzbr
            r2 = 0
            if (r1 == 0) goto L_0x002d
            com.google.android.gms.internal.vision.zzbr r5 = (com.google.android.gms.internal.vision.zzbr) r5
            android.content.Context r1 = r4.zzg
            android.content.Context r3 = r5.zzaa()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x002d
            com.google.android.gms.internal.vision.zzdf<com.google.android.gms.internal.vision.zzcy<com.google.android.gms.internal.vision.zzbe>> r1 = r4.zzfw
            if (r1 != 0) goto L_0x0022
            com.google.android.gms.internal.vision.zzdf r5 = r5.zzab()
            if (r5 != 0) goto L_0x002d
            goto L_0x002c
        L_0x0022:
            com.google.android.gms.internal.vision.zzdf r5 = r5.zzab()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x002d
        L_0x002c:
            return r0
        L_0x002d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzav.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int hashCode = (this.zzg.hashCode() ^ 1000003) * 1000003;
        zzdf<zzcy<zzbe>> zzdf = this.zzfw;
        return hashCode ^ (zzdf == null ? 0 : zzdf.hashCode());
    }
}
