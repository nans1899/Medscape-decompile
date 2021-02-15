package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.instream.InstreamAd;
import com.google.android.gms.ads.instream.InstreamAdView;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzajl extends InstreamAd {
    private MediaContent zzbng = zzte();
    private VideoController zzcjt = zztd();
    private final zzaje zzdgh;

    public zzajl(zzaje zzaje) {
        this.zzdgh = zzaje;
    }

    public final void zza(InstreamAdView instreamAdView) {
        if (instreamAdView == null) {
            zzbbq.zzfc("showInView: parameter view must not be null.");
            return;
        }
        try {
            this.zzdgh.zzr(ObjectWrapper.wrap(instreamAdView));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final MediaContent getMediaContent() {
        return this.zzbng;
    }

    public final VideoController getVideoController() {
        return this.zzcjt;
    }

    public final float getVideoDuration() {
        VideoController videoController = this.zzcjt;
        if (videoController == null) {
            return 0.0f;
        }
        return videoController.getVideoDuration();
    }

    public final float getVideoCurrentTime() {
        VideoController videoController = this.zzcjt;
        if (videoController == null) {
            return 0.0f;
        }
        return videoController.getVideoCurrentTime();
    }

    public final float getAspectRatio() {
        VideoController videoController = this.zzcjt;
        if (videoController == null) {
            return 0.0f;
        }
        return videoController.getAspectRatio();
    }

    public final void destroy() {
        try {
            this.zzdgh.destroy();
            this.zzcjt = null;
            this.zzbng = null;
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    private final VideoController zztd() {
        VideoController videoController = new VideoController();
        try {
            videoController.zza(this.zzdgh.getVideoController());
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
        return videoController;
    }

    private final MediaContent zzte() {
        try {
            if (this.zzdgh.zzsw() != null) {
                return new zzzm(this.zzdgh.zzsw());
            }
            return null;
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return null;
        }
    }
}
