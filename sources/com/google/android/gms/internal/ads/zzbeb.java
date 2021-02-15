package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView;
import com.google.android.gms.ads.internal.zzp;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbeb extends zzbdf implements TextureView.SurfaceTextureListener, zzbey {
    private Surface zzblk;
    private final zzbdx zzefi;
    private final boolean zzefj;
    private int zzefo;
    private int zzefp;
    private int zzefr;
    private int zzefs;
    private zzbds zzeft;
    private final boolean zzefu;
    private zzbdc zzefw;
    private final zzbdu zzegh;
    private String[] zzegu;
    private final zzbdv zzejr;
    private zzber zzejs;
    private String zzejt;
    private boolean zzeju;
    private int zzejv = 1;
    private boolean zzejw;
    private boolean zzejx;
    private float zzejy;

    public zzbeb(Context context, zzbdx zzbdx, zzbdu zzbdu, boolean z, boolean z2, zzbdv zzbdv) {
        super(context);
        this.zzefj = z2;
        this.zzegh = zzbdu;
        this.zzefi = zzbdx;
        this.zzefu = z;
        this.zzejr = zzbdv;
        setSurfaceTextureListener(this);
        this.zzefi.zzb(this);
    }

    private final zzber zzaaf() {
        return new zzber(this.zzegh.getContext(), this.zzejr);
    }

    private final String zzaag() {
        return zzp.zzkr().zzs(this.zzegh.getContext(), this.zzegh.zzzx().zzbre);
    }

    private final boolean zzaah() {
        zzber zzber = this.zzejs;
        return (zzber == null || zzber.zzaau() == null || this.zzeju) ? false : true;
    }

    private final boolean zzaai() {
        return zzaah() && this.zzejv != 1;
    }

    private final void zzaaj() {
        String str;
        if (this.zzejs == null && (str = this.zzejt) != null && this.zzblk != null) {
            if (str.startsWith("cache:")) {
                zzbfl zzfj = this.zzegh.zzfj(this.zzejt);
                if (zzfj instanceof zzbfw) {
                    zzber zzaaz = ((zzbfw) zzfj).zzaaz();
                    this.zzejs = zzaaz;
                    if (zzaaz.zzaau() == null) {
                        zzayp.zzfe("Precached video player has been released.");
                        return;
                    }
                } else if (zzfj instanceof zzbfx) {
                    zzbfx zzbfx = (zzbfx) zzfj;
                    String zzaag = zzaag();
                    ByteBuffer byteBuffer = zzbfx.getByteBuffer();
                    boolean zzaba = zzbfx.zzaba();
                    String url = zzbfx.getUrl();
                    if (url == null) {
                        zzayp.zzfe("Stream cache URL is null.");
                        return;
                    }
                    zzber zzaaf = zzaaf();
                    this.zzejs = zzaaf;
                    zzaaf.zza(new Uri[]{Uri.parse(url)}, zzaag, byteBuffer, zzaba);
                } else {
                    String valueOf = String.valueOf(this.zzejt);
                    zzayp.zzfe(valueOf.length() != 0 ? "Stream cache miss: ".concat(valueOf) : new String("Stream cache miss: "));
                    return;
                }
            } else {
                this.zzejs = zzaaf();
                String zzaag2 = zzaag();
                Uri[] uriArr = new Uri[this.zzegu.length];
                int i = 0;
                while (true) {
                    String[] strArr = this.zzegu;
                    if (i >= strArr.length) {
                        break;
                    }
                    uriArr[i] = Uri.parse(strArr[i]);
                    i++;
                }
                this.zzejs.zza(uriArr, zzaag2);
            }
            this.zzejs.zza((zzbey) this);
            zza(this.zzblk, false);
            if (this.zzejs.zzaau() != null) {
                int playbackState = this.zzejs.zzaau().getPlaybackState();
                this.zzejv = playbackState;
                if (playbackState == 3) {
                    zzaak();
                }
            }
        }
    }

    private final void zza(Surface surface, boolean z) {
        zzber zzber = this.zzejs;
        if (zzber != null) {
            zzber.zza(surface, z);
        } else {
            zzayp.zzfe("Trying to set surface before player is initalized.");
        }
    }

    private final void zza(float f, boolean z) {
        zzber zzber = this.zzejs;
        if (zzber != null) {
            zzber.zzb(f, z);
        } else {
            zzayp.zzfe("Trying to set volume before player is initalized.");
        }
    }

    public final void zzzb() {
        zza(this.zzegf.getVolume(), false);
    }

    private final void zzaak() {
        if (!this.zzejw) {
            this.zzejw = true;
            zzayu.zzeba.post(new zzbea(this));
            zzzb();
            this.zzefi.zzfb();
            if (this.zzejx) {
                play();
            }
        }
    }

    public final String zzyx() {
        String str = this.zzefu ? " spherical" : "";
        return str.length() != 0 ? "ExoPlayer/3".concat(str) : new String("ExoPlayer/3");
    }

    public final void zza(zzbdc zzbdc) {
        this.zzefw = zzbdc;
    }

    public final void setVideoPath(String str) {
        if (str != null) {
            this.zzejt = str;
            this.zzegu = new String[]{str};
            zzaaj();
        }
    }

    public final void zzb(String str, String[] strArr) {
        if (str != null) {
            if (strArr == null) {
                setVideoPath(str);
            }
            this.zzejt = str;
            this.zzegu = (String[]) Arrays.copyOf(strArr, strArr.length);
            zzaaj();
        }
    }

    public final void play() {
        if (zzaai()) {
            if (this.zzejr.zzeip) {
                zzaam();
            }
            this.zzejs.zzaau().zzf(true);
            this.zzefi.zzaac();
            this.zzegf.zzaac();
            this.zzege.zzzd();
            zzayu.zzeba.post(new zzbef(this));
            return;
        }
        this.zzejx = true;
    }

    public final void stop() {
        if (zzaah()) {
            this.zzejs.zzaau().stop();
            if (this.zzejs != null) {
                zza((Surface) null, true);
                zzber zzber = this.zzejs;
                if (zzber != null) {
                    zzber.zza((zzbey) null);
                    this.zzejs.release();
                    this.zzejs = null;
                }
                this.zzejv = 1;
                this.zzeju = false;
                this.zzejw = false;
                this.zzejx = false;
            }
        }
        this.zzefi.zzaad();
        this.zzegf.zzaad();
        this.zzefi.onStop();
    }

    public final void pause() {
        if (zzaai()) {
            if (this.zzejr.zzeip) {
                zzaan();
            }
            this.zzejs.zzaau().zzf(false);
            this.zzefi.zzaad();
            this.zzegf.zzaad();
            zzayu.zzeba.post(new zzbee(this));
        }
    }

    public final void seekTo(int i) {
        if (zzaai()) {
            this.zzejs.zzaau().seekTo((long) i);
        }
    }

    public final void zzdl(int i) {
        zzber zzber = this.zzejs;
        if (zzber != null) {
            zzber.zzaax().zzds(i);
        }
    }

    public final void zzdm(int i) {
        zzber zzber = this.zzejs;
        if (zzber != null) {
            zzber.zzaax().zzdt(i);
        }
    }

    public final void zzdn(int i) {
        zzber zzber = this.zzejs;
        if (zzber != null) {
            zzber.zzaax().zzdn(i);
        }
    }

    public final void zzdo(int i) {
        zzber zzber = this.zzejs;
        if (zzber != null) {
            zzber.zzaax().zzdo(i);
        }
    }

    public final void zzdp(int i) {
        zzber zzber = this.zzejs;
        if (zzber != null) {
            zzber.zzdp(i);
        }
    }

    public final void zza(float f, float f2) {
        zzbds zzbds = this.zzeft;
        if (zzbds != null) {
            zzbds.zzb(f, f2);
        }
    }

    public final int getCurrentPosition() {
        if (zzaai()) {
            return (int) this.zzejs.zzaau().zzem();
        }
        return 0;
    }

    public final int getDuration() {
        if (zzaai()) {
            return (int) this.zzejs.zzaau().getDuration();
        }
        return 0;
    }

    public final int getVideoWidth() {
        return this.zzefo;
    }

    public final int getVideoHeight() {
        return this.zzefp;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007a A[LOOP:0: B:30:0x007a->B:35:0x0095, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r11, int r12) {
        /*
            r10 = this;
            super.onMeasure(r11, r12)
            int r11 = r10.getMeasuredWidth()
            int r12 = r10.getMeasuredHeight()
            float r0 = r10.zzejy
            r1 = 0
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 == 0) goto L_0x002a
            com.google.android.gms.internal.ads.zzbds r2 = r10.zzeft
            if (r2 != 0) goto L_0x002a
            float r2 = (float) r11
            float r3 = (float) r12
            float r3 = r2 / r3
            int r4 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r4 <= 0) goto L_0x0020
            float r2 = r2 / r0
            int r12 = (int) r2
        L_0x0020:
            float r0 = r10.zzejy
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x002a
            float r11 = (float) r12
            float r11 = r11 * r0
            int r11 = (int) r11
        L_0x002a:
            r10.setMeasuredDimension(r11, r12)
            com.google.android.gms.internal.ads.zzbds r0 = r10.zzeft
            if (r0 == 0) goto L_0x0034
            r0.zzm(r11, r12)
        L_0x0034:
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 16
            if (r0 != r2) goto L_0x00a2
            int r0 = r10.zzefr
            if (r0 <= 0) goto L_0x0040
            if (r0 != r11) goto L_0x0046
        L_0x0040:
            int r0 = r10.zzefs
            if (r0 <= 0) goto L_0x009e
            if (r0 == r12) goto L_0x009e
        L_0x0046:
            boolean r0 = r10.zzefj
            if (r0 == 0) goto L_0x009e
            boolean r0 = r10.zzaah()
            if (r0 == 0) goto L_0x009e
            com.google.android.gms.internal.ads.zzber r0 = r10.zzejs
            com.google.android.gms.internal.ads.zzhc r0 = r0.zzaau()
            long r2 = r0.zzem()
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x009e
            boolean r2 = r0.zzek()
            if (r2 == 0) goto L_0x0067
            goto L_0x009e
        L_0x0067:
            r2 = 1
            r10.zza((float) r1, (boolean) r2)
            r0.zzf(r2)
            long r1 = r0.zzem()
            com.google.android.gms.common.util.Clock r3 = com.google.android.gms.ads.internal.zzp.zzky()
            long r3 = r3.currentTimeMillis()
        L_0x007a:
            boolean r5 = r10.zzaah()
            if (r5 == 0) goto L_0x0097
            long r5 = r0.zzem()
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 != 0) goto L_0x0097
            com.google.android.gms.common.util.Clock r5 = com.google.android.gms.ads.internal.zzp.zzky()
            long r5 = r5.currentTimeMillis()
            long r5 = r5 - r3
            r7 = 250(0xfa, double:1.235E-321)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x007a
        L_0x0097:
            r1 = 0
            r0.zzf(r1)
            r10.zzzb()
        L_0x009e:
            r10.zzefr = r11
            r10.zzefs = r12
        L_0x00a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbeb.onMeasure(int, int):void");
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.zzefu) {
            zzbds zzbds = new zzbds(getContext());
            this.zzeft = zzbds;
            zzbds.zza(surfaceTexture, i, i2);
            this.zzeft.start();
            SurfaceTexture zzzp = this.zzeft.zzzp();
            if (zzzp != null) {
                surfaceTexture = zzzp;
            } else {
                this.zzeft.zzzo();
                this.zzeft = null;
            }
        }
        Surface surface = new Surface(surfaceTexture);
        this.zzblk = surface;
        if (this.zzejs == null) {
            zzaaj();
        } else {
            zza(surface, true);
            if (!this.zzejr.zzeip) {
                zzaam();
            }
        }
        if (this.zzefo == 0 || this.zzefp == 0) {
            zzo(i, i2);
        } else {
            zzaal();
        }
        zzayu.zzeba.post(new zzbeh(this));
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zzbds zzbds = this.zzeft;
        if (zzbds != null) {
            zzbds.zzm(i, i2);
        }
        zzayu.zzeba.post(new zzbeg(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzefi.zzc(this);
        this.zzege.zza(surfaceTexture, this.zzefw);
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        pause();
        zzbds zzbds = this.zzeft;
        if (zzbds != null) {
            zzbds.zzzo();
            this.zzeft = null;
        }
        if (this.zzejs != null) {
            zzaan();
            Surface surface = this.zzblk;
            if (surface != null) {
                surface.release();
            }
            this.zzblk = null;
            zza((Surface) null, true);
        }
        zzayu.zzeba.post(new zzbej(this));
        return true;
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdExoPlayerView3 window visibility changed to ");
        sb.append(i);
        zzayp.zzei(sb.toString());
        zzayu.zzeba.post(new zzbei(this, i));
        super.onWindowVisibilityChanged(i);
    }

    public final void zzb(boolean z, long j) {
        if (this.zzegh != null) {
            zzbbz.zzeet.execute(new zzbel(this, z, j));
        }
    }

    public final void zzdq(int i) {
        if (this.zzejv != i) {
            this.zzejv = i;
            if (i == 3) {
                zzaak();
            } else if (i == 4) {
                if (this.zzejr.zzeip) {
                    zzaan();
                }
                this.zzefi.zzaad();
                this.zzegf.zzaad();
                zzayu.zzeba.post(new zzbed(this));
            }
        }
    }

    public final void zzn(int i, int i2) {
        this.zzefo = i;
        this.zzefp = i2;
        zzaal();
    }

    public final void zza(String str, Exception exc) {
        String canonicalName = exc.getClass().getCanonicalName();
        String message = exc.getMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(canonicalName).length() + String.valueOf(message).length());
        sb.append(str);
        sb.append("/");
        sb.append(canonicalName);
        sb.append(":");
        sb.append(message);
        String sb2 = sb.toString();
        String valueOf = String.valueOf(sb2);
        zzayp.zzfe(valueOf.length() != 0 ? "ExoPlayerAdapter error: ".concat(valueOf) : new String("ExoPlayerAdapter error: "));
        this.zzeju = true;
        if (this.zzejr.zzeip) {
            zzaan();
        }
        zzayu.zzeba.post(new zzbec(this, sb2));
    }

    private final void zzaal() {
        zzo(this.zzefo, this.zzefp);
    }

    private final void zzo(int i, int i2) {
        float f = i2 > 0 ? ((float) i) / ((float) i2) : 1.0f;
        if (this.zzejy != f) {
            this.zzejy = f;
            requestLayout();
        }
    }

    private final void zzaam() {
        zzber zzber = this.zzejs;
        if (zzber != null) {
            zzber.zzaw(true);
        }
    }

    private final void zzaan() {
        zzber zzber = this.zzejs;
        if (zzber != null) {
            zzber.zzaw(false);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(boolean z, long j) {
        this.zzegh.zza(z, j);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzdr(int i) {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.onWindowVisibilityChanged(i);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaao() {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.zzzf();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzp(int i, int i2) {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.zzk(i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaap() {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.zzzc();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaaq() {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.onPaused();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaar() {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.zzzd();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzfk(String str) {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.zzm("ExoPlayerAdapter error", str);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaas() {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.zzze();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaat() {
        zzbdc zzbdc = this.zzefw;
        if (zzbdc != null) {
            zzbdc.zzfb();
        }
    }
}
