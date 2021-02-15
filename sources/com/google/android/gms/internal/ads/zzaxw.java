package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaxw {
    private final Object lock = new Object();
    /* access modifiers changed from: private */
    public final Clock zzbqa;
    private final String zzdrd;
    private boolean zzdsn = false;
    private long zzdsr = -1;
    private final zzayi zzdyh;
    private final LinkedList<zzaxz> zzdyi;
    private final String zzdyj;
    private long zzdyk = -1;
    private long zzdyl = -1;
    private long zzdym = 0;
    private long zzdyn = -1;
    private long zzdyo = -1;

    zzaxw(Clock clock, zzayi zzayi, String str, String str2) {
        this.zzbqa = clock;
        this.zzdyh = zzayi;
        this.zzdyj = str;
        this.zzdrd = str2;
        this.zzdyi = new LinkedList<>();
    }

    public final void zze(zzvg zzvg) {
        synchronized (this.lock) {
            long elapsedRealtime = this.zzbqa.elapsedRealtime();
            this.zzdyn = elapsedRealtime;
            this.zzdyh.zza(zzvg, elapsedRealtime);
        }
    }

    public final void zzey(long j) {
        synchronized (this.lock) {
            this.zzdyo = j;
            if (j != -1) {
                this.zzdyh.zzb(this);
            }
        }
    }

    public final void zzwa() {
        synchronized (this.lock) {
            if (this.zzdyo != -1 && this.zzdyk == -1) {
                this.zzdyk = this.zzbqa.elapsedRealtime();
                this.zzdyh.zzb(this);
            }
            this.zzdyh.zzwa();
        }
    }

    public final void zzwb() {
        synchronized (this.lock) {
            if (this.zzdyo != -1) {
                zzaxz zzaxz = new zzaxz(this);
                zzaxz.zzwm();
                this.zzdyi.add(zzaxz);
                this.zzdym++;
                this.zzdyh.zzwb();
                this.zzdyh.zzb(this);
            }
        }
    }

    public final void zzwc() {
        synchronized (this.lock) {
            if (this.zzdyo != -1 && !this.zzdyi.isEmpty()) {
                zzaxz last = this.zzdyi.getLast();
                if (last.zzwk() == -1) {
                    last.zzwl();
                    this.zzdyh.zzb(this);
                }
            }
        }
    }

    public final void zzan(boolean z) {
        synchronized (this.lock) {
            if (this.zzdyo != -1) {
                this.zzdyl = this.zzbqa.elapsedRealtime();
            }
        }
    }

    public final Bundle toBundle() {
        Bundle bundle;
        synchronized (this.lock) {
            bundle = new Bundle();
            bundle.putString("seq_num", this.zzdyj);
            bundle.putString("slotid", this.zzdrd);
            bundle.putBoolean("ismediation", false);
            bundle.putLong("treq", this.zzdyn);
            bundle.putLong("tresponse", this.zzdyo);
            bundle.putLong("timp", this.zzdyk);
            bundle.putLong("tload", this.zzdyl);
            bundle.putLong("pcc", this.zzdym);
            bundle.putLong("tfetch", this.zzdsr);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.zzdyi.iterator();
            while (it.hasNext()) {
                arrayList.add(((zzaxz) it.next()).toBundle());
            }
            bundle.putParcelableArrayList("tclick", arrayList);
        }
        return bundle;
    }

    public final String zzwd() {
        return this.zzdyj;
    }
}
