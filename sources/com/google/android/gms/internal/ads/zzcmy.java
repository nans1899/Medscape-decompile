package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmy implements zzdso {
    private final Clock zzbqa;
    private final Map<zzdsf, Long> zzghm = new HashMap();
    private final zzcms zzghn;
    private final Map<zzdsf, zzcmx> zzgho = new HashMap();

    public zzcmy(zzcms zzcms, Set<zzcmx> set, Clock clock) {
        this.zzghn = zzcms;
        for (zzcmx next : set) {
            this.zzgho.put(next.zzghl, next);
        }
        this.zzbqa = clock;
    }

    public final void zza(zzdsf zzdsf, String str) {
    }

    public final void zzb(zzdsf zzdsf, String str) {
        this.zzghm.put(zzdsf, Long.valueOf(this.zzbqa.elapsedRealtime()));
    }

    public final void zza(zzdsf zzdsf, String str, Throwable th) {
        if (this.zzghm.containsKey(zzdsf)) {
            long elapsedRealtime = this.zzbqa.elapsedRealtime() - this.zzghm.get(zzdsf).longValue();
            Map<String, String> zzru = this.zzghn.zzru();
            String valueOf = String.valueOf(str);
            String concat = valueOf.length() != 0 ? "task.".concat(valueOf) : new String("task.");
            String valueOf2 = String.valueOf(Long.toString(elapsedRealtime));
            zzru.put(concat, valueOf2.length() != 0 ? "f.".concat(valueOf2) : new String("f."));
        }
        if (this.zzgho.containsKey(zzdsf)) {
            zza(zzdsf, false);
        }
    }

    public final void zzc(zzdsf zzdsf, String str) {
        if (this.zzghm.containsKey(zzdsf)) {
            long elapsedRealtime = this.zzbqa.elapsedRealtime() - this.zzghm.get(zzdsf).longValue();
            Map<String, String> zzru = this.zzghn.zzru();
            String valueOf = String.valueOf(str);
            String concat = valueOf.length() != 0 ? "task.".concat(valueOf) : new String("task.");
            String valueOf2 = String.valueOf(Long.toString(elapsedRealtime));
            zzru.put(concat, valueOf2.length() != 0 ? "s.".concat(valueOf2) : new String("s."));
        }
        if (this.zzgho.containsKey(zzdsf)) {
            zza(zzdsf, true);
        }
    }

    private final void zza(zzdsf zzdsf, boolean z) {
        zzdsf zzb = this.zzgho.get(zzdsf).zzghk;
        String str = z ? "s." : "f.";
        if (this.zzghm.containsKey(zzb)) {
            long elapsedRealtime = this.zzbqa.elapsedRealtime() - this.zzghm.get(zzb).longValue();
            Map<String, String> zzru = this.zzghn.zzru();
            String valueOf = String.valueOf(this.zzgho.get(zzdsf).label);
            String concat = valueOf.length() != 0 ? "label.".concat(valueOf) : new String("label.");
            String valueOf2 = String.valueOf(Long.toString(elapsedRealtime));
            zzru.put(concat, valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        }
    }
}
