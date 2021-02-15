package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcdv extends zzaec {
    private final zzcei zzfxu;
    private IObjectWrapper zzfyr;

    public zzcdv(zzcei zzcei) {
        this.zzfxu = zzcei;
    }

    public final float getAspectRatio() throws RemoteException {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcwn)).booleanValue()) {
            return 0.0f;
        }
        if (this.zzfxu.getMediaContentAspectRatio() != 0.0f) {
            return this.zzfxu.getMediaContentAspectRatio();
        }
        if (this.zzfxu.getVideoController() != null) {
            return zzalu();
        }
        IObjectWrapper iObjectWrapper = this.zzfyr;
        if (iObjectWrapper != null) {
            return zzar(iObjectWrapper);
        }
        zzaee zzamc = this.zzfxu.zzamc();
        if (zzamc == null) {
            return 0.0f;
        }
        float width = (zzamc == null || zzamc.getWidth() == -1 || zzamc.getHeight() == -1) ? 0.0f : ((float) zzamc.getWidth()) / ((float) zzamc.getHeight());
        if (width != 0.0f) {
            return width;
        }
        return zzar(zzamc.zzsg());
    }

    public final float getDuration() throws RemoteException {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwo)).booleanValue() && this.zzfxu.getVideoController() != null) {
            return this.zzfxu.getVideoController().getDuration();
        }
        return 0.0f;
    }

    public final float getCurrentTime() throws RemoteException {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwo)).booleanValue() && this.zzfxu.getVideoController() != null) {
            return this.zzfxu.getVideoController().getCurrentTime();
        }
        return 0.0f;
    }

    public final zzyo getVideoController() throws RemoteException {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcwo)).booleanValue()) {
            return null;
        }
        return this.zzfxu.getVideoController();
    }

    public final boolean hasVideoContent() throws RemoteException {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwo)).booleanValue() && this.zzfxu.getVideoController() != null) {
            return true;
        }
        return false;
    }

    public final void zza(zzafq zzafq) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwo)).booleanValue() && (this.zzfxu.getVideoController() instanceof zzbhd)) {
            ((zzbhd) this.zzfxu.getVideoController()).zza(zzafq);
        }
    }

    public final void zzo(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcsc)).booleanValue()) {
            this.zzfyr = iObjectWrapper;
        }
    }

    public final IObjectWrapper zzsj() throws RemoteException {
        IObjectWrapper iObjectWrapper = this.zzfyr;
        if (iObjectWrapper != null) {
            return iObjectWrapper;
        }
        zzaee zzamc = this.zzfxu.zzamc();
        if (zzamc == null) {
            return null;
        }
        return zzamc.zzsg();
    }

    private final float zzalu() {
        try {
            return this.zzfxu.getVideoController().getAspectRatio();
        } catch (RemoteException e) {
            zzayp.zzc("Remote exception getting video controller aspect ratio.", e);
            return 0.0f;
        }
    }

    private static float zzar(IObjectWrapper iObjectWrapper) {
        Drawable drawable;
        if (iObjectWrapper == null || (drawable = (Drawable) ObjectWrapper.unwrap(iObjectWrapper)) == null || drawable.getIntrinsicWidth() == -1 || drawable.getIntrinsicHeight() == -1) {
            return 0.0f;
        }
        return ((float) drawable.getIntrinsicWidth()) / ((float) drawable.getIntrinsicHeight());
    }
}
