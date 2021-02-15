package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzafb implements NativeCustomTemplateAd {
    private final VideoController zzcjt = new VideoController();
    private final zzafa zzddy;
    private final MediaView zzddz;
    private NativeCustomTemplateAd.DisplayOpenMeasurement zzdea;

    public zzafb(zzafa zzafa) {
        Context context;
        this.zzddy = zzafa;
        MediaView mediaView = null;
        try {
            context = (Context) ObjectWrapper.unwrap(zzafa.zzsp());
        } catch (RemoteException | NullPointerException e) {
            zzbbq.zzc("", e);
            context = null;
        }
        if (context != null) {
            MediaView mediaView2 = new MediaView(context);
            try {
                if (this.zzddy.zzp(ObjectWrapper.wrap(mediaView2))) {
                    mediaView = mediaView2;
                }
            } catch (RemoteException e2) {
                zzbbq.zzc("", e2);
            }
        }
        this.zzddz = mediaView;
    }

    public final zzafa zzst() {
        return this.zzddy;
    }

    public final CharSequence getText(String str) {
        try {
            return this.zzddy.zzcx(str);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final NativeAd.Image getImage(String str) {
        try {
            zzaee zzcy = this.zzddy.zzcy(str);
            if (zzcy != null) {
                return new zzaef(zzcy);
            }
            return null;
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            zzyo videoController = this.zzddy.getVideoController();
            if (videoController != null) {
                this.zzcjt.zza(videoController);
            }
        } catch (RemoteException e) {
            zzbbq.zzc("Exception occurred while getting video controller", e);
        }
        return this.zzcjt;
    }

    public final MediaView getVideoMediaView() {
        return this.zzddz;
    }

    public final List<String> getAvailableAssetNames() {
        try {
            return this.zzddy.getAvailableAssetNames();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final String getCustomTemplateId() {
        try {
            return this.zzddy.getCustomTemplateId();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final void performClick(String str) {
        try {
            this.zzddy.performClick(str);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void recordImpression() {
        try {
            this.zzddy.recordImpression();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final NativeCustomTemplateAd.DisplayOpenMeasurement getDisplayOpenMeasurement() {
        try {
            if (this.zzdea == null && this.zzddy.zzsq()) {
                this.zzdea = new zzaea(this.zzddy);
            }
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
        return this.zzdea;
    }

    public final void destroy() {
        try {
            this.zzddy.destroy();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
