package com.google.android.gms.internal.ads;

import android.provider.Settings;
import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzfm extends zzgm {
    public zzfm(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2) {
        super(zzex, str, str2, zza, i, 49);
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        this.zzabc.zzb(zzcn.ENUM_FAILURE);
        try {
            this.zzabc.zzb(((Boolean) this.zzabm.invoke((Object) null, new Object[]{this.zzwg.getContext()})).booleanValue() ? zzcn.ENUM_TRUE : zzcn.ENUM_FALSE);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof Settings.SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
