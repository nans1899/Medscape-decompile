package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzzm implements MediaContent {
    private final VideoController zzcjt = new VideoController();
    private final zzadz zzckt;

    public zzzm(zzadz zzadz) {
        this.zzckt = zzadz;
    }

    public final float getAspectRatio() {
        try {
            return this.zzckt.getAspectRatio();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return 0.0f;
        }
    }

    public final float getDuration() {
        try {
            return this.zzckt.getDuration();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return 0.0f;
        }
    }

    public final float getCurrentTime() {
        try {
            return this.zzckt.getCurrentTime();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return 0.0f;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzckt.getVideoController() != null) {
                this.zzcjt.zza(this.zzckt.getVideoController());
            }
        } catch (RemoteException e) {
            zzbbq.zzc("Exception occurred while getting video controller", e);
        }
        return this.zzcjt;
    }

    public final boolean hasVideoContent() {
        try {
            return this.zzckt.hasVideoContent();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return false;
        }
    }

    public final void setMainImage(Drawable drawable) {
        try {
            this.zzckt.zzo(ObjectWrapper.wrap(drawable));
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final Drawable getMainImage() {
        try {
            IObjectWrapper zzsj = this.zzckt.zzsj();
            if (zzsj != null) {
                return (Drawable) ObjectWrapper.unwrap(zzsj);
            }
            return null;
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final zzadz zzqx() {
        return this.zzckt;
    }
}
