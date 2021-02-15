package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import com.comscore.streaming.ContentDeliveryAdvertisementCapability;
import com.comscore.streaming.ContentDeliveryComposition;
import com.comscore.streaming.ContentDistributionModel;
import com.google.android.gms.ads.internal.zzp;
import com.google.logging.type.LogSeverity;
import io.branch.referral.BranchError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbcs extends zzbdf implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final Map<Integer, String> zzefh = new HashMap();
    private final zzbdx zzefi;
    private final boolean zzefj;
    private int zzefk = 0;
    private int zzefl = 0;
    private MediaPlayer zzefm;
    private Uri zzefn;
    private int zzefo;
    private int zzefp;
    private int zzefq;
    private int zzefr;
    private int zzefs;
    private zzbds zzeft;
    private boolean zzefu;
    private int zzefv;
    /* access modifiers changed from: private */
    public zzbdc zzefw;

    public zzbcs(Context context, boolean z, boolean z2, zzbdv zzbdv, zzbdx zzbdx) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzefi = zzbdx;
        this.zzefu = z;
        this.zzefj = z2;
        zzbdx.zzb(this);
    }

    public final String zzyx() {
        String str = this.zzefu ? " spherical" : "";
        return str.length() != 0 ? "MediaPlayer".concat(str) : new String("MediaPlayer");
    }

    public final void zza(zzbdc zzbdc) {
        this.zzefw = zzbdc;
    }

    public final void setVideoPath(String str) {
        Uri parse = Uri.parse(str);
        zzte zzd = zzte.zzd(parse);
        if (zzd == null || zzd.url != null) {
            if (zzd != null) {
                parse = Uri.parse(zzd.url);
            }
            this.zzefn = parse;
            this.zzefv = 0;
            zzyy();
            requestLayout();
            invalidate();
        }
    }

    public final void stop() {
        zzayp.zzei("AdMediaPlayerView stop");
        MediaPlayer mediaPlayer = this.zzefm;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.zzefm.release();
            this.zzefm = null;
            zzdj(0);
            this.zzefl = 0;
        }
        this.zzefi.onStop();
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdMediaPlayerView size changed: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        zzayp.zzei(sb.toString());
        this.zzefo = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.zzefp = videoHeight;
        if (this.zzefo != 0 && videoHeight != 0) {
            requestLayout();
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzayp.zzei("AdMediaPlayerView prepared");
        zzdj(2);
        this.zzefi.zzfb();
        zzayu.zzeba.post(new zzbcu(this));
        this.zzefo = mediaPlayer.getVideoWidth();
        this.zzefp = mediaPlayer.getVideoHeight();
        int i = this.zzefv;
        if (i != 0) {
            seekTo(i);
        }
        zzyz();
        int i2 = this.zzefo;
        int i3 = this.zzefp;
        StringBuilder sb = new StringBuilder(62);
        sb.append("AdMediaPlayerView stream dimensions: ");
        sb.append(i2);
        sb.append(" x ");
        sb.append(i3);
        zzayp.zzfd(sb.toString());
        if (this.zzefl == 3) {
            play();
        }
        zzzb();
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzayp.zzei("AdMediaPlayerView completion");
        zzdj(5);
        this.zzefl = 5;
        zzayu.zzeba.post(new zzbcx(this));
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzefh.get(Integer.valueOf(i));
        String str2 = zzefh.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer info: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzayp.zzei(sb.toString());
        return true;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzefh.get(Integer.valueOf(i));
        String str2 = zzefh.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 38 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer error: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzayp.zzfe(sb.toString());
        zzdj(-1);
        this.zzefl = -1;
        zzayu.zzeba.post(new zzbcw(this, str, str2));
        return true;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzefq = i;
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzayp.zzei("AdMediaPlayerView surface created");
        zzyy();
        zzayu.zzeba.post(new zzbcz(this));
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zzayp.zzei("AdMediaPlayerView surface changed");
        boolean z = true;
        boolean z2 = this.zzefl == 3;
        if (!(this.zzefo == i && this.zzefp == i2)) {
            z = false;
        }
        if (this.zzefm != null && z2 && z) {
            int i3 = this.zzefv;
            if (i3 != 0) {
                seekTo(i3);
            }
            play();
        }
        zzbds zzbds = this.zzeft;
        if (zzbds != null) {
            zzbds.zzm(i, i2);
        }
        zzayu.zzeba.post(new zzbcy(this, i, i2));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzayp.zzei("AdMediaPlayerView surface destroyed");
        MediaPlayer mediaPlayer = this.zzefm;
        if (mediaPlayer != null && this.zzefv == 0) {
            this.zzefv = mediaPlayer.getCurrentPosition();
        }
        zzbds zzbds = this.zzeft;
        if (zzbds != null) {
            zzbds.zzzo();
        }
        zzayu.zzeba.post(new zzbdb(this));
        zzat(true);
        return true;
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzefi.zzc(this);
        this.zzege.zza(surfaceTexture, this.zzefw);
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        StringBuilder sb = new StringBuilder(58);
        sb.append("AdMediaPlayerView window visibility changed to ");
        sb.append(i);
        zzayp.zzei(sb.toString());
        zzayu.zzeba.post(new zzbcv(this, i));
        super.onWindowVisibilityChanged(i);
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int defaultSize = getDefaultSize(this.zzefo, i);
        int defaultSize2 = getDefaultSize(this.zzefp, i2);
        if (this.zzefo > 0 && this.zzefp > 0 && this.zzeft == null) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i5 = this.zzefo;
                int i6 = i5 * size2;
                int i7 = this.zzefp;
                if (i6 < size * i7) {
                    defaultSize = (i5 * size2) / i7;
                    defaultSize2 = size2;
                } else {
                    if (i5 * size2 > size * i7) {
                        i4 = (i7 * size) / i5;
                    }
                    defaultSize = size;
                    defaultSize2 = size2;
                }
            } else if (mode == 1073741824) {
                int i8 = (this.zzefp * size) / this.zzefo;
                if (mode2 != Integer.MIN_VALUE || i8 <= size2) {
                    i4 = i8;
                }
                defaultSize = size;
                defaultSize2 = size2;
            } else if (mode2 == 1073741824) {
                int i9 = (this.zzefo * size2) / this.zzefp;
                if (mode != Integer.MIN_VALUE || i9 <= size) {
                    defaultSize = i9;
                    defaultSize2 = size2;
                }
                defaultSize = size;
                defaultSize2 = size2;
            } else {
                int i10 = this.zzefo;
                int i11 = this.zzefp;
                if (mode2 != Integer.MIN_VALUE || i11 <= size2) {
                    defaultSize2 = i11;
                } else {
                    i10 = (i10 * size2) / i11;
                    defaultSize2 = size2;
                }
                if (mode != Integer.MIN_VALUE || i10 <= size) {
                    defaultSize = i10;
                } else {
                    i4 = (this.zzefp * size) / this.zzefo;
                }
            }
            defaultSize = size;
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        zzbds zzbds = this.zzeft;
        if (zzbds != null) {
            zzbds.zzm(defaultSize, defaultSize2);
        }
        if (Build.VERSION.SDK_INT == 16) {
            int i12 = this.zzefr;
            if ((i12 > 0 && i12 != defaultSize) || ((i3 = this.zzefs) > 0 && i3 != defaultSize2)) {
                zzyz();
            }
            this.zzefr = defaultSize;
            this.zzefs = defaultSize2;
        }
    }

    public final String toString() {
        String name = getClass().getName();
        String hexString = Integer.toHexString(hashCode());
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
        sb.append(name);
        sb.append("@");
        sb.append(hexString);
        return sb.toString();
    }

    private final void zzyy() {
        zzayp.zzei("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzefn != null && surfaceTexture != null) {
            zzat(false);
            try {
                zzp.zzlh();
                MediaPlayer mediaPlayer = new MediaPlayer();
                this.zzefm = mediaPlayer;
                mediaPlayer.setOnBufferingUpdateListener(this);
                this.zzefm.setOnCompletionListener(this);
                this.zzefm.setOnErrorListener(this);
                this.zzefm.setOnInfoListener(this);
                this.zzefm.setOnPreparedListener(this);
                this.zzefm.setOnVideoSizeChangedListener(this);
                this.zzefq = 0;
                if (this.zzefu) {
                    zzbds zzbds = new zzbds(getContext());
                    this.zzeft = zzbds;
                    zzbds.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzeft.start();
                    SurfaceTexture zzzp = this.zzeft.zzzp();
                    if (zzzp != null) {
                        surfaceTexture = zzzp;
                    } else {
                        this.zzeft.zzzo();
                        this.zzeft = null;
                    }
                }
                this.zzefm.setDataSource(getContext(), this.zzefn);
                zzp.zzli();
                this.zzefm.setSurface(new Surface(surfaceTexture));
                this.zzefm.setAudioStreamType(3);
                this.zzefm.setScreenOnWhilePlaying(true);
                this.zzefm.prepareAsync();
                zzdj(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzefn);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
                sb.append("Failed to initialize MediaPlayer at ");
                sb.append(valueOf);
                zzayp.zzd(sb.toString(), e);
                onError(this.zzefm, 1, 0);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034 A[LOOP:0: B:10:0x0034->B:15:0x004f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzyz() {
        /*
            r8 = this;
            boolean r0 = r8.zzefj
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r8.zzza()
            if (r0 == 0) goto L_0x0059
            android.media.MediaPlayer r0 = r8.zzefm
            int r0 = r0.getCurrentPosition()
            if (r0 <= 0) goto L_0x0059
            int r0 = r8.zzefl
            r1 = 3
            if (r0 == r1) goto L_0x0059
            java.lang.String r0 = "AdMediaPlayerView nudging MediaPlayer"
            com.google.android.gms.internal.ads.zzayp.zzei(r0)
            r0 = 0
            r8.zzd(r0)
            android.media.MediaPlayer r0 = r8.zzefm
            r0.start()
            android.media.MediaPlayer r0 = r8.zzefm
            int r0 = r0.getCurrentPosition()
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzp.zzky()
            long r1 = r1.currentTimeMillis()
        L_0x0034:
            boolean r3 = r8.zzza()
            if (r3 == 0) goto L_0x0051
            android.media.MediaPlayer r3 = r8.zzefm
            int r3 = r3.getCurrentPosition()
            if (r3 != r0) goto L_0x0051
            com.google.android.gms.common.util.Clock r3 = com.google.android.gms.ads.internal.zzp.zzky()
            long r3 = r3.currentTimeMillis()
            long r3 = r3 - r1
            r5 = 250(0xfa, double:1.235E-321)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0034
        L_0x0051:
            android.media.MediaPlayer r0 = r8.zzefm
            r0.pause()
            r8.zzzb()
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcs.zzyz():void");
    }

    private final void zzat(boolean z) {
        zzayp.zzei("AdMediaPlayerView release");
        zzbds zzbds = this.zzeft;
        if (zzbds != null) {
            zzbds.zzzo();
            this.zzeft = null;
        }
        MediaPlayer mediaPlayer = this.zzefm;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.zzefm.release();
            this.zzefm = null;
            zzdj(0);
            if (z) {
                this.zzefl = 0;
                this.zzefl = 0;
            }
        }
    }

    public final void play() {
        zzayp.zzei("AdMediaPlayerView play");
        if (zzza()) {
            this.zzefm.start();
            zzdj(3);
            this.zzege.zzzd();
            zzayu.zzeba.post(new zzbda(this));
        }
        this.zzefl = 3;
    }

    public final void pause() {
        zzayp.zzei("AdMediaPlayerView pause");
        if (zzza() && this.zzefm.isPlaying()) {
            this.zzefm.pause();
            zzdj(4);
            zzayu.zzeba.post(new zzbdd(this));
        }
        this.zzefl = 4;
    }

    public final int getDuration() {
        if (zzza()) {
            return this.zzefm.getDuration();
        }
        return -1;
    }

    public final int getCurrentPosition() {
        if (zzza()) {
            return this.zzefm.getCurrentPosition();
        }
        return 0;
    }

    public final void seekTo(int i) {
        StringBuilder sb = new StringBuilder(34);
        sb.append("AdMediaPlayerView seek ");
        sb.append(i);
        zzayp.zzei(sb.toString());
        if (zzza()) {
            this.zzefm.seekTo(i);
            this.zzefv = 0;
            return;
        }
        this.zzefv = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r2.zzefk;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzza() {
        /*
            r2 = this;
            android.media.MediaPlayer r0 = r2.zzefm
            if (r0 == 0) goto L_0x000f
            int r0 = r2.zzefk
            r1 = -1
            if (r0 == r1) goto L_0x000f
            if (r0 == 0) goto L_0x000f
            r1 = 1
            if (r0 == r1) goto L_0x000f
            return r1
        L_0x000f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcs.zzza():boolean");
    }

    public final void zza(float f, float f2) {
        zzbds zzbds = this.zzeft;
        if (zzbds != null) {
            zzbds.zzb(f, f2);
        }
    }

    public final int getVideoWidth() {
        MediaPlayer mediaPlayer = this.zzefm;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoWidth();
        }
        return 0;
    }

    public final int getVideoHeight() {
        MediaPlayer mediaPlayer = this.zzefm;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoHeight();
        }
        return 0;
    }

    public final void zzzb() {
        zzd(this.zzegf.getVolume());
    }

    private final void zzd(float f) {
        MediaPlayer mediaPlayer = this.zzefm;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setVolume(f, f);
            } catch (IllegalStateException unused) {
            }
        } else {
            zzayp.zzfe("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    private final void zzdj(int i) {
        if (i == 3) {
            this.zzefi.zzaac();
            this.zzegf.zzaac();
        } else if (this.zzefk == 3) {
            this.zzefi.zzaad();
            this.zzegf.zzaad();
        }
        this.zzefk = i;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzdk(int i) {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.onWindowVisibilityChanged(i);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            zzefh.put(-1004, "MEDIA_ERROR_IO");
            zzefh.put(-1007, "MEDIA_ERROR_MALFORMED");
            zzefh.put(-1010, "MEDIA_ERROR_UNSUPPORTED");
            zzefh.put(Integer.valueOf(BranchError.ERR_BRANCH_NO_SHARE_OPTION), "MEDIA_ERROR_TIMED_OUT");
            zzefh.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzefh.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzefh.put(1, "MEDIA_ERROR_UNKNOWN");
        zzefh.put(1, "MEDIA_INFO_UNKNOWN");
        zzefh.put(Integer.valueOf(LogSeverity.ALERT_VALUE), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzefh.put(Integer.valueOf(ContentDeliveryComposition.CLEAN), "MEDIA_INFO_BUFFERING_START");
        zzefh.put(Integer.valueOf(ContentDeliveryComposition.EMBED), "MEDIA_INFO_BUFFERING_END");
        zzefh.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        zzefh.put(Integer.valueOf(ContentDeliveryAdvertisementCapability.NONE), "MEDIA_INFO_NOT_SEEKABLE");
        zzefh.put(Integer.valueOf(ContentDeliveryAdvertisementCapability.DYNAMIC_LOAD), "MEDIA_INFO_METADATA_UPDATE");
        if (Build.VERSION.SDK_INT >= 19) {
            zzefh.put(Integer.valueOf(ContentDistributionModel.TV_AND_ONLINE), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzefh.put(Integer.valueOf(ContentDistributionModel.EXCLUSIVELY_ONLINE), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }
}
