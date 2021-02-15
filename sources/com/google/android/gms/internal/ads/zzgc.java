package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzgc extends zzgm {
    private List<Long> zzabg = null;

    public zzgc(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2) {
        super(zzex, str, str2, zza, i, 31);
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        this.zzabc.zzq(-1);
        this.zzabc.zzr(-1);
        if (this.zzabg == null) {
            this.zzabg = (List) this.zzabm.invoke((Object) null, new Object[]{this.zzwg.getContext()});
        }
        List<Long> list = this.zzabg;
        if (list != null && list.size() == 2) {
            synchronized (this.zzabc) {
                this.zzabc.zzq(this.zzabg.get(0).longValue());
                this.zzabc.zzr(this.zzabg.get(1).longValue());
            }
        }
    }
}
