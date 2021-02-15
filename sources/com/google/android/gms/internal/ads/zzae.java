package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzae {
    private final zzx zzal;
    private final AtomicInteger zzbg;
    private final Set<zzaa<?>> zzbh;
    private final PriorityBlockingQueue<zzaa<?>> zzbi;
    private final PriorityBlockingQueue<zzaa<?>> zzbj;
    private final zzw[] zzbk;
    private final List<zzag> zzbl;
    private final List<zzah> zzbm;
    private final zzk zzn;
    private final zzak zzo;
    private zzm zzz;

    private zzae(zzk zzk, zzx zzx, int i, zzak zzak) {
        this.zzbg = new AtomicInteger();
        this.zzbh = new HashSet();
        this.zzbi = new PriorityBlockingQueue<>();
        this.zzbj = new PriorityBlockingQueue<>();
        this.zzbl = new ArrayList();
        this.zzbm = new ArrayList();
        this.zzn = zzk;
        this.zzal = zzx;
        this.zzbk = new zzw[4];
        this.zzo = zzak;
    }

    private zzae(zzk zzk, zzx zzx, int i) {
        this(zzk, zzx, 4, new zzt(new Handler(Looper.getMainLooper())));
    }

    public zzae(zzk zzk, zzx zzx) {
        this(zzk, zzx, 4);
    }

    public final void start() {
        zzm zzm = this.zzz;
        if (zzm != null) {
            zzm.quit();
        }
        for (zzw zzw : this.zzbk) {
            if (zzw != null) {
                zzw.quit();
            }
        }
        zzm zzm2 = new zzm(this.zzbi, this.zzbj, this.zzn, this.zzo);
        this.zzz = zzm2;
        zzm2.start();
        for (int i = 0; i < this.zzbk.length; i++) {
            zzw zzw2 = new zzw(this.zzbj, this.zzal, this.zzn, this.zzo);
            this.zzbk[i] = zzw2;
            zzw2.start();
        }
    }

    public final <T> zzaa<T> zze(zzaa<T> zzaa) {
        zzaa.zza(this);
        synchronized (this.zzbh) {
            this.zzbh.add(zzaa);
        }
        zzaa.zze(this.zzbg.incrementAndGet());
        zzaa.zzc("add-to-queue");
        zza(zzaa, 0);
        if (!zzaa.zzh()) {
            this.zzbj.add(zzaa);
            return zzaa;
        }
        this.zzbi.add(zzaa);
        return zzaa;
    }

    /* access modifiers changed from: package-private */
    public final <T> void zzf(zzaa<T> zzaa) {
        synchronized (this.zzbh) {
            this.zzbh.remove(zzaa);
        }
        synchronized (this.zzbl) {
            for (zzag zzg : this.zzbl) {
                zzg.zzg(zzaa);
            }
        }
        zza(zzaa, 5);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzaa<?> zzaa, int i) {
        synchronized (this.zzbm) {
            for (zzah zzb : this.zzbm) {
                zzb.zzb(zzaa, i);
            }
        }
    }
}
