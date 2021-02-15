package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbml {
    /* access modifiers changed from: private */
    public final Executor executor;
    private final String zzbrd;
    private final zzamk zzfni;
    /* access modifiers changed from: private */
    public zzbmu zzfnj;
    private final zzahq<Object> zzfnk = new zzbmo(this);
    private final zzahq<Object> zzfnl = new zzbmq(this);

    public zzbml(String str, zzamk zzamk, Executor executor2) {
        this.zzbrd = str;
        this.zzfni = zzamk;
        this.executor = executor2;
    }

    public final void zza(zzbmu zzbmu) {
        this.zzfni.zzc("/updateActiveView", this.zzfnk);
        this.zzfni.zzc("/untrackActiveViewUnit", this.zzfnl);
        this.zzfnj = zzbmu;
    }

    public final void zzd(zzbgj zzbgj) {
        zzbgj.zza("/updateActiveView", (zzahq<? super zzbgj>) this.zzfnk);
        zzbgj.zza("/untrackActiveViewUnit", (zzahq<? super zzbgj>) this.zzfnl);
    }

    public final void zze(zzbgj zzbgj) {
        zzbgj.zzb("/updateActiveView", this.zzfnk);
        zzbgj.zzb("/untrackActiveViewUnit", this.zzfnl);
    }

    public final void zzahe() {
        this.zzfni.zzd("/updateActiveView", this.zzfnk);
        this.zzfni.zzd("/untrackActiveViewUnit", this.zzfnl);
    }

    /* access modifiers changed from: private */
    public final boolean zzm(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        if (TextUtils.isEmpty(str) || !str.equals(this.zzbrd)) {
            return false;
        }
        return true;
    }
}
