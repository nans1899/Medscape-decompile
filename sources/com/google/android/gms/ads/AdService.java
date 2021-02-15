package com.google.android.gms.ads;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzanb;
import com.google.android.gms.internal.ads.zzanc;
import com.google.android.gms.internal.ads.zzaqp;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzwm;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class AdService extends IntentService {
    private final zzaqp zzacw = zzwm.zzpu().zzb((Context) this, (zzanb) new zzanc());

    public AdService() {
        super("AdService");
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        try {
            this.zzacw.zzc(intent);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 50);
            sb.append("RemoteException calling handleNotificationIntent: ");
            sb.append(valueOf);
            zzbbq.zzfc(sb.toString());
        }
    }
}
