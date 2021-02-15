package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaul implements RewardItem {
    private final zzatw zzdvw;

    public zzaul(zzatw zzatw) {
        this.zzdvw = zzatw;
    }

    public final String getType() {
        zzatw zzatw = this.zzdvw;
        if (zzatw == null) {
            return null;
        }
        try {
            return zzatw.getType();
        } catch (RemoteException e) {
            zzbbq.zzd("Could not forward getType to RewardItem", e);
            return null;
        }
    }

    public final int getAmount() {
        zzatw zzatw = this.zzdvw;
        if (zzatw == null) {
            return 0;
        }
        try {
            return zzatw.getAmount();
        } catch (RemoteException e) {
            zzbbq.zzd("Could not forward getAmount to RewardItem", e);
            return 0;
        }
    }
}
