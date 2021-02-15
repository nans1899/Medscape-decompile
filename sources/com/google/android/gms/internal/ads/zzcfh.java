package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.util.Clock;
import com.google.firebase.messaging.Constants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcfh implements View.OnClickListener {
    private final Clock zzbqa;
    private final zzcil zzgbo;
    private zzafz zzgbp;
    private zzahq<Object> zzgbq;
    String zzgbr;
    Long zzgbs;
    WeakReference<View> zzgbt;

    public zzcfh(zzcil zzcil, Clock clock) {
        this.zzgbo = zzcil;
        this.zzbqa = clock;
    }

    public final void zza(zzafz zzafz) {
        this.zzgbp = zzafz;
        zzahq<Object> zzahq = this.zzgbq;
        if (zzahq != null) {
            this.zzgbo.zzb("/unconfirmedClick", zzahq);
        }
        zzcfk zzcfk = new zzcfk(this, zzafz);
        this.zzgbq = zzcfk;
        this.zzgbo.zza("/unconfirmedClick", (zzahq<Object>) zzcfk);
    }

    public final zzafz zzanj() {
        return this.zzgbp;
    }

    public final void cancelUnconfirmedClick() {
        if (this.zzgbp != null && this.zzgbs != null) {
            zzank();
            try {
                this.zzgbp.onUnconfirmedClickCancelled();
            } catch (RemoteException e) {
                zzbbq.zze("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onClick(View view) {
        WeakReference<View> weakReference = this.zzgbt;
        if (weakReference != null && weakReference.get() == view) {
            if (!(this.zzgbr == null || this.zzgbs == null)) {
                HashMap hashMap = new HashMap();
                hashMap.put("id", this.zzgbr);
                hashMap.put("time_interval", String.valueOf(this.zzbqa.currentTimeMillis() - this.zzgbs.longValue()));
                hashMap.put(Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE, "onePointFiveClick");
                this.zzgbo.zza("sendMessageToNativeJs", (Map<String, ?>) hashMap);
            }
            zzank();
        }
    }

    private final void zzank() {
        View view;
        this.zzgbr = null;
        this.zzgbs = null;
        WeakReference<View> weakReference = this.zzgbt;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            view.setClickable(false);
            view.setOnClickListener((View.OnClickListener) null);
            this.zzgbt = null;
        }
    }
}
