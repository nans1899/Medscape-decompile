package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.rewarded.RewardItem;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzavm implements RewardItem {
    private final zzaux zzdwc;

    public zzavm(zzaux zzaux) {
        this.zzdwc = zzaux;
    }

    public final String getType() {
        zzaux zzaux = this.zzdwc;
        if (zzaux == null) {
            return null;
        }
        try {
            return zzaux.getType();
        } catch (RemoteException e) {
            zzbbq.zzd("Could not forward getType to RewardItem", e);
            return null;
        }
    }

    public final int getAmount() {
        zzaux zzaux = this.zzdwc;
        if (zzaux == null) {
            return 0;
        }
        try {
            return zzaux.getAmount();
        } catch (RemoteException e) {
            zzbbq.zzd("Could not forward getAmount to RewardItem", e);
            return 0;
        }
    }
}
