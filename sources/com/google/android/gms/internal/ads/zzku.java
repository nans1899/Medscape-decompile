package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzku extends zzkr {
    public final long zzawj;
    public final List<zzkt> zzawk = new ArrayList();
    public final List<zzku> zzawl = new ArrayList();

    public zzku(int i, long j) {
        super(i);
        this.zzawj = j;
    }

    public final void zza(zzkt zzkt) {
        this.zzawk.add(zzkt);
    }

    public final void zza(zzku zzku) {
        this.zzawl.add(zzku);
    }

    public final zzkt zzau(int i) {
        int size = this.zzawk.size();
        for (int i2 = 0; i2 < size; i2++) {
            zzkt zzkt = this.zzawk.get(i2);
            if (zzkt.type == i) {
                return zzkt;
            }
        }
        return null;
    }

    public final zzku zzav(int i) {
        int size = this.zzawl.size();
        for (int i2 = 0; i2 < size; i2++) {
            zzku zzku = this.zzawl.get(i2);
            if (zzku.type == i) {
                return zzku;
            }
        }
        return null;
    }

    public final String toString() {
        String zzas = zzas(this.type);
        String arrays = Arrays.toString(this.zzawk.toArray());
        String arrays2 = Arrays.toString(this.zzawl.toArray());
        StringBuilder sb = new StringBuilder(String.valueOf(zzas).length() + 22 + String.valueOf(arrays).length() + String.valueOf(arrays2).length());
        sb.append(zzas);
        sb.append(" leaves: ");
        sb.append(arrays);
        sb.append(" containers: ");
        sb.append(arrays2);
        return sb.toString();
    }
}
