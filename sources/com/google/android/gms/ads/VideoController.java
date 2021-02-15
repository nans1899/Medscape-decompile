package com.google.android.gms.ads;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzaah;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzyo;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class VideoController {
    public static final int PLAYBACK_STATE_ENDED = 3;
    public static final int PLAYBACK_STATE_PAUSED = 2;
    public static final int PLAYBACK_STATE_PLAYING = 1;
    public static final int PLAYBACK_STATE_READY = 5;
    public static final int PLAYBACK_STATE_UNKNOWN = 0;
    private final Object lock = new Object();
    private zzyo zzadr;
    private VideoLifecycleCallbacks zzads;

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }

        public void onVideoMute(boolean z) {
        }

        public void onVideoPause() {
        }

        public void onVideoPlay() {
        }

        public void onVideoStart() {
        }
    }

    public final void zza(zzyo zzyo) {
        synchronized (this.lock) {
            this.zzadr = zzyo;
            if (this.zzads != null) {
                setVideoLifecycleCallbacks(this.zzads);
            }
        }
    }

    public final zzyo zzdv() {
        zzyo zzyo;
        synchronized (this.lock) {
            zzyo = this.zzadr;
        }
        return zzyo;
    }

    public final void play() {
        synchronized (this.lock) {
            if (this.zzadr != null) {
                try {
                    this.zzadr.play();
                } catch (RemoteException e) {
                    zzbbq.zzc("Unable to call play on video controller.", e);
                }
            }
        }
    }

    public final void pause() {
        synchronized (this.lock) {
            if (this.zzadr != null) {
                try {
                    this.zzadr.pause();
                } catch (RemoteException e) {
                    zzbbq.zzc("Unable to call pause on video controller.", e);
                }
            }
        }
    }

    public final void stop() {
        synchronized (this.lock) {
            if (this.zzadr != null) {
                try {
                    this.zzadr.stop();
                } catch (RemoteException e) {
                    zzbbq.zzc("Unable to call stop on video controller.", e);
                }
            }
        }
    }

    public final void mute(boolean z) {
        synchronized (this.lock) {
            if (this.zzadr != null) {
                try {
                    this.zzadr.mute(z);
                } catch (RemoteException e) {
                    zzbbq.zzc("Unable to call mute on video controller.", e);
                }
            }
        }
    }

    public final boolean isMuted() {
        synchronized (this.lock) {
            if (this.zzadr == null) {
                return true;
            }
            try {
                boolean isMuted = this.zzadr.isMuted();
                return isMuted;
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call isMuted on video controller.", e);
                return true;
            }
        }
    }

    public final int getPlaybackState() {
        synchronized (this.lock) {
            if (this.zzadr == null) {
                return 0;
            }
            try {
                int playbackState = this.zzadr.getPlaybackState();
                return playbackState;
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call getPlaybackState on video controller.", e);
                return 0;
            }
        }
    }

    public final boolean isCustomControlsEnabled() {
        synchronized (this.lock) {
            if (this.zzadr == null) {
                return false;
            }
            try {
                boolean isCustomControlsEnabled = this.zzadr.isCustomControlsEnabled();
                return isCustomControlsEnabled;
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call isUsingCustomPlayerControls.", e);
                return false;
            }
        }
    }

    public final boolean isClickToExpandEnabled() {
        synchronized (this.lock) {
            if (this.zzadr == null) {
                return false;
            }
            try {
                boolean isClickToExpandEnabled = this.zzadr.isClickToExpandEnabled();
                return isClickToExpandEnabled;
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call isClickToExpandEnabled.", e);
                return false;
            }
        }
    }

    public final float getVideoDuration() {
        synchronized (this.lock) {
            if (this.zzadr == null) {
                return 0.0f;
            }
            try {
                float duration = this.zzadr.getDuration();
                return duration;
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call getDuration on video controller.", e);
                return 0.0f;
            }
        }
    }

    public final float getVideoCurrentTime() {
        synchronized (this.lock) {
            if (this.zzadr == null) {
                return 0.0f;
            }
            try {
                float currentTime = this.zzadr.getCurrentTime();
                return currentTime;
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call getCurrentTime on video controller.", e);
                return 0.0f;
            }
        }
    }

    public final void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        Preconditions.checkNotNull(videoLifecycleCallbacks, "VideoLifecycleCallbacks may not be null.");
        synchronized (this.lock) {
            this.zzads = videoLifecycleCallbacks;
            if (this.zzadr != null) {
                try {
                    this.zzadr.zza(new zzaah(videoLifecycleCallbacks));
                } catch (RemoteException e) {
                    zzbbq.zzc("Unable to call setVideoLifecycleCallbacks on video controller.", e);
                }
            }
        }
    }

    public final VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.lock) {
            videoLifecycleCallbacks = this.zzads;
        }
        return videoLifecycleCallbacks;
    }

    public final boolean hasVideoContent() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzadr != null;
        }
        return z;
    }

    @Deprecated
    public final float getAspectRatio() {
        synchronized (this.lock) {
            if (this.zzadr == null) {
                return 0.0f;
            }
            try {
                float aspectRatio = this.zzadr.getAspectRatio();
                return aspectRatio;
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to call getAspectRatio on video controller.", e);
                return 0.0f;
            }
        }
    }
}
