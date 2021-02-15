package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzeg;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzn extends AsyncTask<Void, Void, String> {
    private final /* synthetic */ zzj zzbpm;

    private zzn(zzj zzj) {
        this.zzbpm = zzj;
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final String doInBackground(Void... voidArr) {
        try {
            zzeg unused = this.zzbpm.zzbpk = (zzeg) this.zzbpm.zzbpg.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzbbq.zzd("", e);
        }
        return this.zzbpm.zzkl();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (this.zzbpm.zzbpi != null && str != null) {
            this.zzbpm.zzbpi.loadUrl(str);
        }
    }

    /* synthetic */ zzn(zzj zzj, zzm zzm) {
        this(zzj);
    }
}
