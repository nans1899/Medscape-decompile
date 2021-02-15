package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.rewarded.RewardItem;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzavu extends zzauw {
    private final String type;
    private final int zzdvs;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzavu(zzauv zzauv) {
        this(zzauv != null ? zzauv.type : "", zzauv != null ? zzauv.zzdvs : 1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzavu(RewardItem rewardItem) {
        this(rewardItem != null ? rewardItem.getType() : "", rewardItem != null ? rewardItem.getAmount() : 1);
    }

    public zzavu(String str, int i) {
        this.type = str;
        this.zzdvs = i;
    }

    public final String getType() throws RemoteException {
        return this.type;
    }

    public final int getAmount() throws RemoteException {
        return this.zzdvs;
    }
}
