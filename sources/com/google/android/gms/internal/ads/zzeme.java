package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeme {
    private final ArrayDeque<zzeip> zzimw;

    private zzeme() {
        this.zzimw = new ArrayDeque<>();
    }

    /* access modifiers changed from: private */
    public final zzeip zzc(zzeip zzeip, zzeip zzeip2) {
        zzal(zzeip);
        zzal(zzeip2);
        zzeip pop = this.zzimw.pop();
        while (!this.zzimw.isEmpty()) {
            pop = new zzemc(this.zzimw.pop(), pop, (zzemb) null);
        }
        return pop;
    }

    private final void zzal(zzeip zzeip) {
        while (!zzeip.zzbek()) {
            if (zzeip instanceof zzemc) {
                zzemc zzemc = (zzemc) zzeip;
                zzal(zzemc.zzimq);
                zzeip = zzemc.zzimr;
            } else {
                String valueOf = String.valueOf(zzeip.getClass());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 49);
                sb.append("Has a new type of ByteString been created? Found ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        int zzhn = zzhn(zzeip.size());
        int zzhm = zzemc.zzhm(zzhn + 1);
        if (this.zzimw.isEmpty() || this.zzimw.peek().size() >= zzhm) {
            this.zzimw.push(zzeip);
            return;
        }
        int zzhm2 = zzemc.zzhm(zzhn);
        zzeip pop = this.zzimw.pop();
        while (!this.zzimw.isEmpty() && this.zzimw.peek().size() < zzhm2) {
            pop = new zzemc(this.zzimw.pop(), pop, (zzemb) null);
        }
        zzemc zzemc2 = new zzemc(pop, zzeip, (zzemb) null);
        while (!this.zzimw.isEmpty() && this.zzimw.peek().size() < zzemc.zzhm(zzhn(zzemc2.size()) + 1)) {
            zzemc2 = new zzemc(this.zzimw.pop(), zzemc2, (zzemb) null);
        }
        this.zzimw.push(zzemc2);
    }

    private static int zzhn(int i) {
        int binarySearch = Arrays.binarySearch(zzemc.zzimo, i);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }

    /* synthetic */ zzeme(zzemb zzemb) {
        this();
    }
}
