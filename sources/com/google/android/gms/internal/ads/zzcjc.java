package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcjc extends VideoController.VideoLifecycleCallbacks {
    private final zzcei zzfxu;

    public zzcjc(zzcei zzcei) {
        this.zzfxu = zzcei;
    }

    public final void onVideoStart() {
        zzyt zza = zza(this.zzfxu);
        if (zza != null) {
            try {
                zza.onVideoStart();
            } catch (RemoteException e) {
                zzayp.zzd("Unable to call onVideoEnd()", e);
            }
        }
    }

    public final void onVideoPause() {
        zzyt zza = zza(this.zzfxu);
        if (zza != null) {
            try {
                zza.onVideoPause();
            } catch (RemoteException e) {
                zzayp.zzd("Unable to call onVideoEnd()", e);
            }
        }
    }

    public final void onVideoEnd() {
        zzyt zza = zza(this.zzfxu);
        if (zza != null) {
            try {
                zza.onVideoEnd();
            } catch (RemoteException e) {
                zzayp.zzd("Unable to call onVideoEnd()", e);
            }
        }
    }

    private static zzyt zza(zzcei zzcei) {
        zzyo videoController = zzcei.getVideoController();
        if (videoController == null) {
            return null;
        }
        try {
            return videoController.zzqm();
        } catch (RemoteException unused) {
            return null;
        }
    }
}
