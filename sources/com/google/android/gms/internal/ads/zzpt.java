package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzpt extends Surface {
    private static boolean zzbks;
    private static boolean zzbkt;
    private final boolean zzbbf;
    private final zzpv zzbku;
    private boolean zzbkv;

    public static synchronized boolean zzc(Context context) {
        boolean z;
        synchronized (zzpt.class) {
            if (!zzbkt) {
                if (zzpq.SDK_INT >= 17) {
                    boolean z2 = false;
                    String eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
                    if (eglQueryString != null && eglQueryString.contains("EGL_EXT_protected_content")) {
                        if (!(zzpq.SDK_INT == 24 && (zzpq.MODEL.startsWith("SM-G950") || zzpq.MODEL.startsWith("SM-G955")) && !context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance"))) {
                            z2 = true;
                        }
                    }
                    zzbks = z2;
                }
                zzbkt = true;
            }
            z = zzbks;
        }
        return z;
    }

    public static zzpt zzc(Context context, boolean z) {
        if (zzpq.SDK_INT >= 17) {
            zzoz.checkState(!z || zzc(context));
            return new zzpv().zzm(z);
        }
        throw new UnsupportedOperationException("Unsupported prior to API level 17");
    }

    private zzpt(zzpv zzpv, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.zzbku = zzpv;
        this.zzbbf = z;
    }

    public final void release() {
        super.release();
        synchronized (this.zzbku) {
            if (!this.zzbkv) {
                this.zzbku.release();
                this.zzbkv = true;
            }
        }
    }
}
