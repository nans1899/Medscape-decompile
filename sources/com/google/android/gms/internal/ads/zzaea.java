package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaea implements NativeCustomTemplateAd.DisplayOpenMeasurement {
    private final zzafa zzddp;

    public zzaea(zzafa zzafa) {
        this.zzddp = zzafa;
        try {
            zzafa.zzss();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final boolean start() {
        try {
            return this.zzddp.zzsr();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return false;
        }
    }

    public final void setView(View view) {
        try {
            this.zzddp.zzq(ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
