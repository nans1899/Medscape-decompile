package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzanv extends zzyr {
    private final Object lock = new Object();
    private volatile zzyt zzdle;

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
        throw new RemoteException();
    }

    public final float getCurrentTime() throws RemoteException {
        throw new RemoteException();
    }

    public final void zza(zzyt zzyt) throws RemoteException {
        synchronized (this.lock) {
            this.zzdle = zzyt;
        }
    }

    public final zzyt zzqm() throws RemoteException {
        zzyt zzyt;
        synchronized (this.lock) {
            zzyt = this.zzdle;
        }
        return zzyt;
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
