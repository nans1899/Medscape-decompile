package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzagq extends zzafr {
    /* access modifiers changed from: private */
    public final OnPublisherAdViewLoadedListener zzdem;

    public zzagq(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener) {
        this.zzdem = onPublisherAdViewLoadedListener;
    }

    public final void zza(zzxc zzxc, IObjectWrapper iObjectWrapper) {
        if (zzxc != null && iObjectWrapper != null) {
            PublisherAdView publisherAdView = new PublisherAdView((Context) ObjectWrapper.unwrap(iObjectWrapper));
            AppEventListener appEventListener = null;
            try {
                if (zzxc.zzkk() instanceof zzvc) {
                    zzvc zzvc = (zzvc) zzxc.zzkk();
                    publisherAdView.setAdListener(zzvc != null ? zzvc.getAdListener() : null);
                }
            } catch (RemoteException e) {
                zzbbq.zzc("", e);
            }
            try {
                if (zzxc.zzkj() instanceof zzvt) {
                    zzvt zzvt = (zzvt) zzxc.zzkj();
                    if (zzvt != null) {
                        appEventListener = zzvt.getAppEventListener();
                    }
                    publisherAdView.setAppEventListener(appEventListener);
                }
            } catch (RemoteException e2) {
                zzbbq.zzc("", e2);
            }
            zzbbg.zzaah.post(new zzagp(this, publisherAdView, zzxc));
        }
    }
}
