package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzo<V> implements Runnable {
    @NullableDecl
    private zzdzm<V> zzhtw;

    zzdzo(zzdzm<V> zzdzm) {
        this.zzhtw = zzdzm;
    }

    public final void run() {
        zzdzc zza;
        String str;
        zzdzm<V> zzdzm = this.zzhtw;
        if (zzdzm != null && (zza = zzdzm.zzhtu) != null) {
            this.zzhtw = null;
            if (zza.isDone()) {
                zzdzm.setFuture(zza);
                return;
            }
            try {
                ScheduledFuture zzb = zzdzm.zzhtv;
                ScheduledFuture unused = zzdzm.zzhtv = null;
                str = "Timed out";
                if (zzb != null) {
                    long abs = Math.abs(zzb.getDelay(TimeUnit.MILLISECONDS));
                    if (abs > 10) {
                        StringBuilder sb = new StringBuilder(str.length() + 66);
                        sb.append(str);
                        sb.append(" (timeout delayed by ");
                        sb.append(abs);
                        sb.append(" ms after scheduled time)");
                        str = sb.toString();
                    }
                }
                String valueOf = String.valueOf(str);
                String valueOf2 = String.valueOf(zza);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 2 + String.valueOf(valueOf2).length());
                sb2.append(valueOf);
                sb2.append(": ");
                sb2.append(valueOf2);
                zzdzm.setException(new zzdzn(sb2.toString()));
                zza.cancel(true);
            } catch (Throwable th) {
                zza.cancel(true);
                throw th;
            }
        }
    }
}
