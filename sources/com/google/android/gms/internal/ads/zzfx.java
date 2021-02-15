package com.google.android.gms.internal.ads;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzfx extends zzgm {
    private static final Object zzaba = new Object();
    private static volatile String zzabd;

    public zzfx(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2) {
        super(zzex, str, str2, zza, i, 1);
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        this.zzabc.zzu(ExifInterface.LONGITUDE_EAST);
        if (zzabd == null) {
            synchronized (zzaba) {
                if (zzabd == null) {
                    zzabd = (String) this.zzabm.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zzabc) {
            this.zzabc.zzu(zzabd);
        }
    }
}
