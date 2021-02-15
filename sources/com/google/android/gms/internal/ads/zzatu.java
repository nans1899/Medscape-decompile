package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzatu extends zzatz {
    private final String type;
    private final int zzdvs;

    public zzatu(String str, int i) {
        this.type = str;
        this.zzdvs = i;
    }

    public final String getType() {
        return this.type;
    }

    public final int getAmount() {
        return this.zzdvs;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzatu)) {
            zzatu zzatu = (zzatu) obj;
            if (!Objects.equal(this.type, zzatu.type) || !Objects.equal(Integer.valueOf(this.zzdvs), Integer.valueOf(zzatu.zzdvs))) {
                return false;
            }
            return true;
        }
        return false;
    }
}
