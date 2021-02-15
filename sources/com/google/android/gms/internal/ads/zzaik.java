package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.ads.internal.zzp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaik implements zzx {
    /* access modifiers changed from: private */
    public volatile zzahz zzdfx;
    private final Context zzvr;

    public zzaik(Context context) {
        this.zzvr = context;
    }

    public final zzy zzc(zzaa<?> zzaa) throws zzao {
        zzaic zzh = zzaic.zzh(zzaa);
        long elapsedRealtime = zzp.zzky().elapsedRealtime();
        try {
            zzbcg zzbcg = new zzbcg();
            this.zzdfx = new zzahz(this.zzvr, zzp.zzlf().zzyj(), new zzaio(this, zzbcg), new zzain(this, zzbcg));
            this.zzdfx.checkAvailabilityAndConnect();
            zzdzc zza = zzdyq.zza(zzdyq.zzb(zzbcg, new zzaij(this, zzh), (Executor) zzbbz.zzeep), (long) ((Integer) zzwm.zzpx().zzd(zzabb.zzcti)).intValue(), TimeUnit.MILLISECONDS, zzbbz.zzees);
            zza.addListener(new zzail(this), zzbbz.zzeep);
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) zza.get();
            long elapsedRealtime2 = zzp.zzky().elapsedRealtime() - elapsedRealtime;
            StringBuilder sb = new StringBuilder(52);
            sb.append("Http assets remote cache took ");
            sb.append(elapsedRealtime2);
            sb.append("ms");
            zzayp.zzei(sb.toString());
            zzaie zzaie = (zzaie) new zzatb(parcelFileDescriptor).zza(zzaie.CREATOR);
            if (zzaie == null) {
                return null;
            }
            if (zzaie.zzdfv) {
                throw new zzao(zzaie.zzcgw);
            } else if (zzaie.zzdft.length != zzaie.zzdfu.length) {
                return null;
            } else {
                HashMap hashMap = new HashMap();
                for (int i = 0; i < zzaie.zzdft.length; i++) {
                    hashMap.put(zzaie.zzdft[i], zzaie.zzdfu[i]);
                }
                return new zzy(zzaie.statusCode, zzaie.data, (Map<String, String>) hashMap, zzaie.zzan, zzaie.zzao);
            }
        } catch (InterruptedException | ExecutionException unused) {
            StringBuilder sb2 = new StringBuilder(52);
            sb2.append("Http assets remote cache took ");
            sb2.append(zzp.zzky().elapsedRealtime() - elapsedRealtime);
            sb2.append("ms");
            zzayp.zzei(sb2.toString());
            return null;
        } catch (Throwable th) {
            long elapsedRealtime3 = zzp.zzky().elapsedRealtime() - elapsedRealtime;
            StringBuilder sb3 = new StringBuilder(52);
            sb3.append("Http assets remote cache took ");
            sb3.append(elapsedRealtime3);
            sb3.append("ms");
            zzayp.zzei(sb3.toString());
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        if (this.zzdfx != null) {
            this.zzdfx.disconnect();
            Binder.flushPendingCommands();
        }
    }
}
