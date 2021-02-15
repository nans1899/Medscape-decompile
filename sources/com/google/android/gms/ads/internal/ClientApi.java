package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.overlay.zzs;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.overlay.zzy;
import com.google.android.gms.ads.internal.overlay.zzz;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzaei;
import com.google.android.gms.internal.ads.zzael;
import com.google.android.gms.internal.ads.zzanb;
import com.google.android.gms.internal.ads.zzaqp;
import com.google.android.gms.internal.ads.zzaqy;
import com.google.android.gms.internal.ads.zzarn;
import com.google.android.gms.internal.ads.zzaud;
import com.google.android.gms.internal.ads.zzauy;
import com.google.android.gms.internal.ads.zzaxl;
import com.google.android.gms.internal.ads.zzbbx;
import com.google.android.gms.internal.ads.zzbix;
import com.google.android.gms.internal.ads.zzcez;
import com.google.android.gms.internal.ads.zzcfc;
import com.google.android.gms.internal.ads.zzczh;
import com.google.android.gms.internal.ads.zzczj;
import com.google.android.gms.internal.ads.zzczu;
import com.google.android.gms.internal.ads.zzdju;
import com.google.android.gms.internal.ads.zzvn;
import com.google.android.gms.internal.ads.zzwm;
import com.google.android.gms.internal.ads.zzwz;
import com.google.android.gms.internal.ads.zzxc;
import com.google.android.gms.internal.ads.zzxo;
import com.google.android.gms.internal.ads.zzxs;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class ClientApi extends zzxo {
    public final zzxs zzc(IObjectWrapper iObjectWrapper) {
        return null;
    }

    public final zzarn zzd(IObjectWrapper iObjectWrapper) {
        return null;
    }

    public final zzxc zza(IObjectWrapper iObjectWrapper, zzvn zzvn, String str, zzanb zzanb, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return new zzczj(zzbix.zza(context, zzanb, i), context, zzvn, str);
    }

    public final zzxc zzb(IObjectWrapper iObjectWrapper, zzvn zzvn, String str, zzanb zzanb, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return new zzczu(zzbix.zza(context, zzanb, i), context, zzvn, str);
    }

    public final zzwz zza(IObjectWrapper iObjectWrapper, String str, zzanb zzanb, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return new zzczh(zzbix.zza(context, zzanb, i), context, str);
    }

    public final zzaei zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return new zzcfc((FrameLayout) ObjectWrapper.unwrap(iObjectWrapper), (FrameLayout) ObjectWrapper.unwrap(iObjectWrapper2), 202510000);
    }

    public final zzaud zza(IObjectWrapper iObjectWrapper, zzanb zzanb, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return zzbix.zza(context, zzanb, i).zzadw().zzbz(context).zzagi().zzagm();
    }

    public final zzaqy zzb(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
        AdOverlayInfoParcel zzd = AdOverlayInfoParcel.zzd(activity.getIntent());
        if (zzd == null) {
            return new zzt(activity);
        }
        int i = zzd.zzdpr;
        if (i == 1) {
            return new zzq(activity);
        }
        if (i == 2) {
            return new zzz(activity);
        }
        if (i == 3) {
            return new zzy(activity);
        }
        if (i != 4) {
            return new zzt(activity);
        }
        return new zzs(activity, zzd);
    }

    public final zzxs zza(IObjectWrapper iObjectWrapper, int i) {
        return zzbix.zzf((Context) ObjectWrapper.unwrap(iObjectWrapper), i).zzado();
    }

    public final zzxc zza(IObjectWrapper iObjectWrapper, zzvn zzvn, String str, int i) {
        return new zzj((Context) ObjectWrapper.unwrap(iObjectWrapper), zzvn, str, new zzbbx(202510000, i, true, false));
    }

    public final zzael zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return new zzcez((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final zzauy zzb(IObjectWrapper iObjectWrapper, String str, zzanb zzanb, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        return zzbix.zza(context, zzanb, i).zzadw().zzbz(context).zzfw(str).zzagi().zzagn();
    }

    public final zzxc zzc(IObjectWrapper iObjectWrapper, zzvn zzvn, String str, zzanb zzanb, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzdju zzafu = zzbix.zza(context, zzanb, i).zzads().zzfv(str).zzby(context).zzafu();
        if (i >= ((Integer) zzwm.zzpx().zzd(zzabb.zzcul)).intValue()) {
            return zzafu.zzafy();
        }
        return zzafu.zzafx();
    }

    public final zzaxl zzb(IObjectWrapper iObjectWrapper, zzanb zzanb, int i) {
        return zzbix.zza((Context) ObjectWrapper.unwrap(iObjectWrapper), zzanb, i).zzady();
    }

    public final zzaqp zzc(IObjectWrapper iObjectWrapper, zzanb zzanb, int i) {
        return zzbix.zza((Context) ObjectWrapper.unwrap(iObjectWrapper), zzanb, i).zzadz();
    }
}
