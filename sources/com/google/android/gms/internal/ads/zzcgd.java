package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcgd implements zzceq {
    private final zzbbx zzbpe;
    private final zzdnv zzeot;
    private final zzdok zzfsk;
    private final zzbuu zzfxv;
    private final zzbuc zzfxw;
    private boolean zzfya = false;
    private boolean zzfyd = false;
    private final zzano zzgcm;
    private final zzanp zzgcn;
    private final zzanu zzgco;
    private final Context zzvr;

    public zzcgd(zzano zzano, zzanp zzanp, zzanu zzanu, zzbuu zzbuu, zzbuc zzbuc, Context context, zzdnv zzdnv, zzbbx zzbbx, zzdok zzdok) {
        this.zzgcm = zzano;
        this.zzgcn = zzanp;
        this.zzgco = zzanu;
        this.zzfxv = zzbuu;
        this.zzfxw = zzbuc;
        this.zzvr = context;
        this.zzeot = zzdnv;
        this.zzbpe = zzbbx;
        this.zzfsk = zzdok;
    }

    public final void cancelUnconfirmedClick() {
    }

    public final void destroy() {
    }

    public final void setClickConfirmingView(View view) {
    }

    public final JSONObject zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2) {
        return null;
    }

    public final void zza(View view, MotionEvent motionEvent, View view2) {
    }

    public final void zza(zzafz zzafz) {
    }

    public final void zzalp() {
    }

    public final void zzalr() {
    }

    public final void zzfz(String str) {
    }

    public final void zzg(Bundle bundle) {
    }

    public final void zzh(Bundle bundle) {
    }

    public final boolean zzi(Bundle bundle) {
        return false;
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        try {
            IObjectWrapper wrap = ObjectWrapper.wrap(view);
            HashMap<String, View> zzb = zzb(map);
            HashMap<String, View> zzb2 = zzb(map2);
            if (this.zzgco != null) {
                this.zzgco.zzc(wrap, ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
            } else if (this.zzgcm != null) {
                this.zzgcm.zzc(wrap, ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                this.zzgcm.zzv(wrap);
            } else if (this.zzgcn != null) {
                this.zzgcn.zzc(wrap, ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                this.zzgcn.zzv(wrap);
            }
        } catch (RemoteException e) {
            zzayp.zzd("Failed to call trackView", e);
        }
    }

    private static HashMap<String, View> zzb(Map<String, WeakReference<View>> map) {
        HashMap<String, View> hashMap = new HashMap<>();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Map.Entry next : map.entrySet()) {
                View view = (View) ((WeakReference) next.getValue()).get();
                if (view != null) {
                    hashMap.put((String) next.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    public final void zza(View view, Map<String, WeakReference<View>> map) {
        try {
            IObjectWrapper wrap = ObjectWrapper.wrap(view);
            if (this.zzgco != null) {
                this.zzgco.zzw(wrap);
            } else if (this.zzgcm != null) {
                this.zzgcm.zzw(wrap);
            } else if (this.zzgcn != null) {
                this.zzgcn.zzw(wrap);
            }
        } catch (RemoteException e) {
            zzayp.zzd("Failed to call untrackView", e);
        }
    }

    public final void zza(View view, View view2, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, boolean z) {
        if (!this.zzfyd || !this.zzeot.zzdky) {
            zzad(view);
        }
    }

    private final void zzad(View view) {
        try {
            if (this.zzgco != null && !this.zzgco.getOverrideClickHandling()) {
                this.zzgco.zzu(ObjectWrapper.wrap(view));
                this.zzfxw.onAdClicked();
            } else if (this.zzgcm != null && !this.zzgcm.getOverrideClickHandling()) {
                this.zzgcm.zzu(ObjectWrapper.wrap(view));
                this.zzfxw.onAdClicked();
            } else if (this.zzgcn != null && !this.zzgcn.getOverrideClickHandling()) {
                this.zzgcn.zzu(ObjectWrapper.wrap(view));
                this.zzfxw.onAdClicked();
            }
        } catch (RemoteException e) {
            zzayp.zzd("Failed to call handleClick", e);
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2, boolean z) {
        if (!this.zzfyd) {
            zzayp.zzfe("Custom click reporting for 3p ads failed. enableCustomClickGesture is not set.");
        } else if (!this.zzeot.zzdky) {
            zzayp.zzfe("Custom click reporting for 3p ads failed. Ad unit id not whitelisted.");
        } else {
            zzad(view);
        }
    }

    public final void zzsv() {
        this.zzfyd = true;
    }

    public final boolean isCustomClickGestureEnabled() {
        return this.zzeot.zzdky;
    }

    public final void zzb(View view, Map<String, WeakReference<View>> map, Map<String, WeakReference<View>> map2) {
        try {
            if (!this.zzfya && this.zzeot.zzhdw != null) {
                this.zzfya |= zzp.zzlb().zzb(this.zzvr, this.zzbpe.zzbre, this.zzeot.zzhdw.toString(), this.zzfsk.zzhfb);
            }
            if (this.zzgco != null && !this.zzgco.getOverrideImpressionRecording()) {
                this.zzgco.recordImpression();
                this.zzfxv.onAdImpression();
            } else if (this.zzgcm != null && !this.zzgcm.getOverrideImpressionRecording()) {
                this.zzgcm.recordImpression();
                this.zzfxv.onAdImpression();
            } else if (this.zzgcn != null && !this.zzgcn.getOverrideImpressionRecording()) {
                this.zzgcn.recordImpression();
                this.zzfxv.onAdImpression();
            }
        } catch (RemoteException e) {
            zzayp.zzd("Failed to call recordImpression", e);
        }
    }

    public final void zza(zzyd zzyd) {
        zzayp.zzfe("Mute This Ad is not supported for 3rd party ads");
    }

    public final void zza(zzxz zzxz) {
        zzayp.zzfe("Mute This Ad is not supported for 3rd party ads");
    }

    public final void zzalq() {
        zzayp.zzfe("Mute This Ad is not supported for 3rd party ads");
    }
}
