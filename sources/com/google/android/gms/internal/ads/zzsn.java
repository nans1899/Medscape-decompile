package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzsn extends AppOpenAd {
    private final zzsg zzbus;

    public zzsn(zzsg zzsg) {
        this.zzbus = zzsg;
    }

    public final void show(Activity activity, FullScreenContentCallback fullScreenContentCallback) {
        try {
            this.zzbus.zza(ObjectWrapper.wrap(activity), new zzsd(fullScreenContentCallback));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final ResponseInfo getResponseInfo() {
        zzyn zzyn;
        try {
            zzyn = this.zzbus.zzki();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            zzyn = null;
        }
        return ResponseInfo.zza(zzyn);
    }

    /* access modifiers changed from: protected */
    public final zzxc zzdw() {
        try {
            return this.zzbus.zzdw();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzsm zzsm) {
        try {
            this.zzbus.zza(zzsm);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
