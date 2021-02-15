package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzne implements zzmz {
    private final zzid zzaev = new zzid();
    private final zzmz[] zzbfe;
    private final ArrayList<zzmz> zzbff;
    private zznc zzbfg;
    private zzhy zzbfh;
    private Object zzbfi;
    private int zzbfj = -1;
    private zzng zzbfk;

    public zzne(zzmz... zzmzArr) {
        this.zzbfe = zzmzArr;
        this.zzbff = new ArrayList<>(Arrays.asList(zzmzArr));
    }

    public final void zza(zzhc zzhc, boolean z, zznc zznc) {
        this.zzbfg = zznc;
        int i = 0;
        while (true) {
            zzmz[] zzmzArr = this.zzbfe;
            if (i < zzmzArr.length) {
                zzmzArr[i].zza(zzhc, false, new zznd(this, i));
                i++;
            } else {
                return;
            }
        }
    }

    public final void zzhz() throws IOException {
        zzng zzng = this.zzbfk;
        if (zzng == null) {
            for (zzmz zzhz : this.zzbfe) {
                zzhz.zzhz();
            }
            return;
        }
        throw zzng;
    }

    public final zzmx zza(int i, zzok zzok) {
        int length = this.zzbfe.length;
        zzmx[] zzmxArr = new zzmx[length];
        for (int i2 = 0; i2 < length; i2++) {
            zzmxArr[i2] = this.zzbfe[i2].zza(i, zzok);
        }
        return new zznb(zzmxArr);
    }

    public final void zzb(zzmx zzmx) {
        zznb zznb = (zznb) zzmx;
        int i = 0;
        while (true) {
            zzmz[] zzmzArr = this.zzbfe;
            if (i < zzmzArr.length) {
                zzmzArr[i].zzb(zznb.zzbex[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public final void zzia() {
        for (zzmz zzia : this.zzbfe) {
            zzia.zzia();
        }
    }

    /* access modifiers changed from: private */
    public final void zza(int i, zzhy zzhy, Object obj) {
        zzng zzng;
        if (this.zzbfk == null) {
            int zzff = zzhy.zzff();
            int i2 = 0;
            while (true) {
                if (i2 >= zzff) {
                    if (this.zzbfj == -1) {
                        this.zzbfj = zzhy.zzfg();
                    } else if (zzhy.zzfg() != this.zzbfj) {
                        zzng = new zzng(1);
                    }
                    zzng = null;
                } else if (zzhy.zza(i2, this.zzaev, false).zzait) {
                    zzng = new zzng(0);
                    break;
                } else {
                    i2++;
                }
            }
            this.zzbfk = zzng;
        }
        if (this.zzbfk == null) {
            this.zzbff.remove(this.zzbfe[i]);
            if (i == 0) {
                this.zzbfh = zzhy;
                this.zzbfi = obj;
            }
            if (this.zzbff.isEmpty()) {
                this.zzbfg.zzb(this.zzbfh, this.zzbfi);
            }
        }
    }
}
