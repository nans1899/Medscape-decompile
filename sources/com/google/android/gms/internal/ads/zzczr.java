package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.AppEventListener;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzczr implements AppEventListener {
    private zzxk zzgtg;

    public final synchronized void zzb(zzxk zzxk) {
        this.zzgtg = zzxk;
    }

    public final synchronized zzxk zzaqs() {
        return this.zzgtg;
    }

    public final synchronized void onAppEvent(String str, String str2) {
        if (this.zzgtg != null) {
            try {
                this.zzgtg.onAppEvent(str, str2);
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception at onAppEvent.", e);
            }
        }
    }
}
