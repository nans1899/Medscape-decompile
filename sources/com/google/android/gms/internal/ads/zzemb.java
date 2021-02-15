package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzemb extends zzeiq {
    private final zzemd zziml = new zzemd(this.zzimn, (zzemb) null);
    private zzeiu zzimm = zzbif();
    private final /* synthetic */ zzemc zzimn;

    zzemb(zzemc zzemc) {
        this.zzimn = zzemc;
    }

    private final zzeiu zzbif() {
        if (this.zziml.hasNext()) {
            return (zzeiu) ((zzeiw) this.zziml.next()).iterator();
        }
        return null;
    }

    public final boolean hasNext() {
        return this.zzimm != null;
    }

    public final byte nextByte() {
        zzeiu zzeiu = this.zzimm;
        if (zzeiu != null) {
            byte nextByte = zzeiu.nextByte();
            if (!this.zzimm.hasNext()) {
                this.zzimm = zzbif();
            }
            return nextByte;
        }
        throw new NoSuchElementException();
    }
}
