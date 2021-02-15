package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.TextureView;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzbdf extends TextureView implements zzbdy {
    protected final zzbdp zzege = new zzbdp();
    protected final zzbdz zzegf;

    public zzbdf(Context context) {
        super(context);
        this.zzegf = new zzbdz(context, this);
    }

    public abstract int getCurrentPosition();

    public abstract int getDuration();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract void pause();

    public abstract void play();

    public abstract void seekTo(int i);

    public abstract void setVideoPath(String str);

    public abstract void stop();

    public abstract void zza(float f, float f2);

    public abstract void zza(zzbdc zzbdc);

    public void zzdl(int i) {
    }

    public void zzdm(int i) {
    }

    public void zzdn(int i) {
    }

    public void zzdo(int i) {
    }

    public void zzdp(int i) {
    }

    public abstract String zzyx();

    public abstract void zzzb();

    public void zzb(String str, String[] strArr) {
        setVideoPath(str);
    }
}
