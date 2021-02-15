package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.CollectionUtils;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbhd extends zzyr {
    private final Object lock = new Object();
    private boolean zzadw;
    private boolean zzadx;
    private int zzaez;
    private zzyt zzdle;
    private final zzbdu zzegh;
    private final boolean zzepz;
    private final boolean zzeqa;
    private boolean zzeqb;
    private boolean zzeqc = true;
    private float zzeqd;
    private float zzeqe;
    private float zzeqf;
    private zzafq zzeqg;

    public zzbhd(zzbdu zzbdu, float f, boolean z, boolean z2) {
        this.zzegh = zzbdu;
        this.zzeqd = f;
        this.zzepz = z;
        this.zzeqa = z2;
    }

    public final void play() {
        zzf(OmnitureData.LINK_NAME_VIDEO_PLAY, (Map<String, String>) null);
    }

    public final void pause() {
        zzf(OmnitureData.LINK_NAME_VIDEO_PAUSE, (Map<String, String>) null);
    }

    public final void stop() {
        zzf("stop", (Map<String, String>) null);
    }

    public final void mute(boolean z) {
        zzf(z ? "mute" : "unmute", (Map<String, String>) null);
    }

    public final void zzb(zzaak zzaak) {
        boolean z = zzaak.zzadv;
        boolean z2 = zzaak.zzadw;
        boolean z3 = zzaak.zzadx;
        synchronized (this.lock) {
            this.zzadw = z2;
            this.zzadx = z3;
        }
        zzf("initialState", CollectionUtils.mapOf("muteStart", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO, "customControlsRequested", z2 ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO, "clickToExpandRequested", z3 ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    private final void zzf(String str, Map<String, String> map) {
        HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put("action", str);
        zzbbz.zzeet.execute(new zzbhc(this, hashMap));
    }

    public final boolean isMuted() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzeqc;
        }
        return z;
    }

    public final int getPlaybackState() {
        int i;
        synchronized (this.lock) {
            i = this.zzaez;
        }
        return i;
    }

    public final float getAspectRatio() {
        float f;
        synchronized (this.lock) {
            f = this.zzeqf;
        }
        return f;
    }

    public final float getDuration() {
        float f;
        synchronized (this.lock) {
            f = this.zzeqd;
        }
        return f;
    }

    public final float getCurrentTime() {
        float f;
        synchronized (this.lock) {
            f = this.zzeqe;
        }
        return f;
    }

    public final void zza(zzyt zzyt) {
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

    public final boolean isCustomControlsEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzepz && this.zzadw;
        }
        return z;
    }

    public final boolean isClickToExpandEnabled() {
        boolean z;
        boolean isCustomControlsEnabled = isCustomControlsEnabled();
        synchronized (this.lock) {
            if (!isCustomControlsEnabled) {
                try {
                    if (this.zzadx && this.zzeqa) {
                        z = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            z = false;
        }
        return z;
    }

    public final void zze(float f) {
        synchronized (this.lock) {
            this.zzeqe = f;
        }
    }

    public final void zzacw() {
        boolean z;
        int i;
        synchronized (this.lock) {
            z = this.zzeqc;
            i = this.zzaez;
            this.zzaez = 3;
        }
        zza(i, 3, z, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(float r4, float r5, int r6, boolean r7, float r8) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            float r1 = r3.zzeqd     // Catch:{ all -> 0x004f }
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0012
            float r1 = r3.zzeqf     // Catch:{ all -> 0x004f }
            int r1 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0010
            goto L_0x0012
        L_0x0010:
            r1 = 0
            goto L_0x0013
        L_0x0012:
            r1 = 1
        L_0x0013:
            r3.zzeqd = r5     // Catch:{ all -> 0x004f }
            r3.zzeqe = r4     // Catch:{ all -> 0x004f }
            boolean r4 = r3.zzeqc     // Catch:{ all -> 0x004f }
            r3.zzeqc = r7     // Catch:{ all -> 0x004f }
            int r5 = r3.zzaez     // Catch:{ all -> 0x004f }
            r3.zzaez = r6     // Catch:{ all -> 0x004f }
            float r2 = r3.zzeqf     // Catch:{ all -> 0x004f }
            r3.zzeqf = r8     // Catch:{ all -> 0x004f }
            float r8 = r8 - r2
            float r8 = java.lang.Math.abs(r8)     // Catch:{ all -> 0x004f }
            r2 = 953267991(0x38d1b717, float:1.0E-4)
            int r8 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r8 <= 0) goto L_0x0038
            com.google.android.gms.internal.ads.zzbdu r8 = r3.zzegh     // Catch:{ all -> 0x004f }
            android.view.View r8 = r8.getView()     // Catch:{ all -> 0x004f }
            r8.invalidate()     // Catch:{ all -> 0x004f }
        L_0x0038:
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            if (r1 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.zzafq r8 = r3.zzeqg     // Catch:{ RemoteException -> 0x0045 }
            if (r8 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.zzafq r8 = r3.zzeqg     // Catch:{ RemoteException -> 0x0045 }
            r8.zzsu()     // Catch:{ RemoteException -> 0x0045 }
            goto L_0x004b
        L_0x0045:
            r8 = move-exception
            java.lang.String r0 = "#007 Could not call remote method."
            com.google.android.gms.internal.ads.zzbbq.zze(r0, r8)
        L_0x004b:
            r3.zza(r5, r6, r4, r7)
            return
        L_0x004f:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbhd.zza(float, float, int, boolean, float):void");
    }

    public final void zza(zzafq zzafq) {
        synchronized (this.lock) {
            this.zzeqg = zzafq;
        }
    }

    private final void zza(int i, int i2, boolean z, boolean z2) {
        zzbbz.zzeet.execute(new zzbhf(this, i, i2, z, z2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(int i, int i2, boolean z, boolean z2) {
        synchronized (this.lock) {
            boolean z3 = false;
            boolean z4 = i != i2;
            boolean z5 = !this.zzeqb && i2 == 1;
            boolean z6 = z4 && i2 == 1;
            boolean z7 = z4 && i2 == 2;
            boolean z8 = z4 && i2 == 3;
            boolean z9 = z != z2;
            if (this.zzeqb || z5) {
                z3 = true;
            }
            this.zzeqb = z3;
            if (z5) {
                try {
                    if (this.zzdle != null) {
                        this.zzdle.onVideoStart();
                    }
                } catch (RemoteException e) {
                    zzbbq.zze("#007 Could not call remote method.", e);
                }
            }
            if (z6 && this.zzdle != null) {
                this.zzdle.onVideoPlay();
            }
            if (z7 && this.zzdle != null) {
                this.zzdle.onVideoPause();
            }
            if (z8) {
                if (this.zzdle != null) {
                    this.zzdle.onVideoEnd();
                }
                this.zzegh.zzaaa();
            }
            if (z9 && this.zzdle != null) {
                this.zzdle.onVideoMute(z2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzk(Map map) {
        this.zzegh.zza("pubVideoCmd", (Map<String, ?>) map);
    }
}
