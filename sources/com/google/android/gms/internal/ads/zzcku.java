package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcku {
    private final zzckp zzgfy;
    private final AtomicReference<zzanb> zzgfz = new AtomicReference<>();

    zzcku(zzckp zzckp) {
        this.zzgfy = zzckp;
    }

    public final void zzb(zzanb zzanb) {
        this.zzgfz.compareAndSet((Object) null, zzanb);
    }

    public final zzdpa zzd(String str, JSONObject jSONObject) throws zzdos {
        zzang zzang;
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                zzang = new zzanx((MediationAdapter) new AdMobAdapter());
            } else if ("com.google.ads.mediation.AdUrlAdapter".equals(str)) {
                zzang = new zzanx((MediationAdapter) new AdUrlAdapter());
            } else if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(str)) {
                zzang = new zzanx((MediationAdapter) new zzapx());
            } else {
                zzang = zze(str, jSONObject);
            }
            zzdpa zzdpa = new zzdpa(zzang);
            this.zzgfy.zza(str, zzdpa);
            return zzdpa;
        } catch (Throwable th) {
            throw new zzdos(th);
        }
    }

    public final zzaph zzdk(String str) throws RemoteException {
        zzaph zzdk = zzaog().zzdk(str);
        this.zzgfy.zza(str, zzdk);
        return zzdk;
    }

    public final boolean zzaof() {
        return this.zzgfz.get() != null;
    }

    private final zzang zze(String str, JSONObject jSONObject) throws RemoteException {
        zzanb zzaog = zzaog();
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            try {
                if (zzaog.zzdj(jSONObject.getString("class_name"))) {
                    return zzaog.zzdi("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
                }
                return zzaog.zzdi("com.google.ads.mediation.customevent.CustomEventAdapter");
            } catch (JSONException e) {
                zzayp.zzc("Invalid custom event.", e);
            }
        }
        return zzaog.zzdi(str);
    }

    private final zzanb zzaog() throws RemoteException {
        zzanb zzanb = this.zzgfz.get();
        if (zzanb != null) {
            return zzanb;
        }
        zzayp.zzfe("Unexpected call to adapter creator.");
        throw new RemoteException();
    }
}
