package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcef extends zzyr {
    private final Object lock = new Object();
    @Nullable
    private zzyo zzfzo;
    @Nullable
    private final zzanu zzfzp;

    public zzcef(@Nullable zzyo zzyo, @Nullable zzanu zzanu) {
        this.zzfzo = zzyo;
        this.zzfzp = zzanu;
    }

    public final void play() throws RemoteException {
        throw new RemoteException();
    }

    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    public final void stop() throws RemoteException {
        throw new RemoteException();
    }

    public final void mute(boolean z) throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isMuted() throws RemoteException {
        throw new RemoteException();
    }

    public final int getPlaybackState() throws RemoteException {
        throw new RemoteException();
    }

    public final float getDuration() throws RemoteException {
        zzanu zzanu = this.zzfzp;
        if (zzanu != null) {
            return zzanu.getVideoDuration();
        }
        return 0.0f;
    }

    public final float getCurrentTime() throws RemoteException {
        zzanu zzanu = this.zzfzp;
        if (zzanu != null) {
            return zzanu.getVideoCurrentTime();
        }
        return 0.0f;
    }

    public final void zza(zzyt zzyt) throws RemoteException {
        synchronized (this.lock) {
            if (this.zzfzo != null) {
                this.zzfzo.zza(zzyt);
            }
        }
    }

    public final zzyt zzqm() throws RemoteException {
        synchronized (this.lock) {
            if (this.zzfzo == null) {
                return null;
            }
            zzyt zzqm = this.zzfzo.zzqm();
            return zzqm;
        }
    }

    public final float getAspectRatio() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isCustomControlsEnabled() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isClickToExpandEnabled() throws RemoteException {
        throw new RemoteException();
    }
}
