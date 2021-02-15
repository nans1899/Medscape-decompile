package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.query.QueryInfoGenerationCallback;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class zzaru {
    private static zzaxl zzdqb;
    private final zzyw zzacu;
    private final AdFormat zzdqa;
    private final Context zzvr;

    public static zzaxl zzs(Context context) {
        zzaxl zzaxl;
        synchronized (zzaru.class) {
            if (zzdqb == null) {
                zzdqb = zzwm.zzpu().zza(context, (zzanb) new zzanc());
            }
            zzaxl = zzdqb;
        }
        return zzaxl;
    }

    public zzaru(Context context, AdFormat adFormat, zzyw zzyw) {
        this.zzvr = context;
        this.zzdqa = adFormat;
        this.zzacu = zzyw;
    }

    public final void zza(QueryInfoGenerationCallback queryInfoGenerationCallback) {
        zzvg zzvg;
        zzaxl zzs = zzs(this.zzvr);
        if (zzs == null) {
            queryInfoGenerationCallback.onFailure("Internal Error, query info generator is null.");
            return;
        }
        IObjectWrapper wrap = ObjectWrapper.wrap(this.zzvr);
        zzyw zzyw = this.zzacu;
        if (zzyw == null) {
            zzvg = new zzvj().zzpj();
        } else {
            zzvg = zzvl.zza(this.zzvr, zzyw);
        }
        try {
            zzs.zza(wrap, new zzaxr((String) null, this.zzdqa.name(), (zzvn) null, zzvg), (zzaxg) new zzarx(this, queryInfoGenerationCallback));
        } catch (RemoteException unused) {
            queryInfoGenerationCallback.onFailure("Internal Error.");
        }
    }
}
