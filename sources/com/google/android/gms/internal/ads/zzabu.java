package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzabu implements CustomRenderedAd {
    private final zzabt zzczq;

    public zzabu(zzabt zzabt) {
        this.zzczq = zzabt;
    }

    public final String getBaseUrl() {
        try {
            return this.zzczq.zzrs();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final String getContent() {
        try {
            return this.zzczq.getContent();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final void onAdRendered(View view) {
        try {
            this.zzczq.zzn(view != null ? ObjectWrapper.wrap(view) : null);
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void recordClick() {
        try {
            this.zzczq.recordClick();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzczq.recordImpression();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }
}
