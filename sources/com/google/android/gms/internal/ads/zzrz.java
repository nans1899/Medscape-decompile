package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzrz implements Comparator<zzrn> {
    public zzrz(zzsa zzsa) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzrn zzrn = (zzrn) obj;
        zzrn zzrn2 = (zzrn) obj2;
        if (zzrn.zzmo() < zzrn2.zzmo()) {
            return -1;
        }
        if (zzrn.zzmo() > zzrn2.zzmo()) {
            return 1;
        }
        if (zzrn.zzmn() < zzrn2.zzmn()) {
            return -1;
        }
        if (zzrn.zzmn() > zzrn2.zzmn()) {
            return 1;
        }
        float zzmq = (zzrn.zzmq() - zzrn.zzmo()) * (zzrn.zzmp() - zzrn.zzmn());
        float zzmq2 = (zzrn2.zzmq() - zzrn2.zzmo()) * (zzrn2.zzmp() - zzrn2.zzmn());
        if (zzmq > zzmq2) {
            return -1;
        }
        if (zzmq < zzmq2) {
            return 1;
        }
        return 0;
    }
}
