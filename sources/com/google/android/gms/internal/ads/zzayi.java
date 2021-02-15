package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.Clock;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzayi implements zzrh {
    private final Object lock = new Object();
    private final zzayr zzdzr;
    private final zzayg zzdzz;
    private final zzaye zzeaa;
    private final HashSet<zzaxw> zzeab = new HashSet<>();
    private final HashSet<zzayj> zzeac = new HashSet<>();

    public zzayi(String str, zzayr zzayr) {
        this.zzeaa = new zzaye(str, zzayr);
        this.zzdzr = zzayr;
        this.zzdzz = new zzayg();
    }

    public final void zzb(zzaxw zzaxw) {
        synchronized (this.lock) {
            this.zzeab.add(zzaxw);
        }
    }

    public final void zzb(HashSet<zzaxw> hashSet) {
        synchronized (this.lock) {
            this.zzeab.addAll(hashSet);
        }
    }

    public final Bundle zza(Context context, zzayh zzayh) {
        HashSet hashSet = new HashSet();
        synchronized (this.lock) {
            hashSet.addAll(this.zzeab);
            this.zzeab.clear();
        }
        Bundle bundle = new Bundle();
        bundle.putBundle(AdParameterKeys.SECTION_ID, this.zzeaa.zzp(context, this.zzdzz.zzwy()));
        Bundle bundle2 = new Bundle();
        Iterator<zzayj> it = this.zzeac.iterator();
        if (!it.hasNext()) {
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                arrayList.add(((zzaxw) it2.next()).toBundle());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            zzayh.zza(hashSet);
            return bundle;
        }
        zzayj next = it.next();
        throw new NoSuchMethodError();
    }

    public final void zzp(boolean z) {
        long currentTimeMillis = zzp.zzky().currentTimeMillis();
        if (z) {
            if (currentTimeMillis - this.zzdzr.zzxl() > ((Long) zzwm.zzpx().zzd(zzabb.zzcop)).longValue()) {
                this.zzeaa.zzdzo = -1;
                return;
            }
            this.zzeaa.zzdzo = this.zzdzr.zzxm();
            return;
        }
        this.zzdzr.zzez(currentTimeMillis);
        this.zzdzr.zzdg(this.zzeaa.zzdzo);
    }

    public final void zzwb() {
        synchronized (this.lock) {
            this.zzeaa.zzwb();
        }
    }

    public final void zzwa() {
        synchronized (this.lock) {
            this.zzeaa.zzwa();
        }
    }

    public final void zza(zzvg zzvg, long j) {
        synchronized (this.lock) {
            this.zzeaa.zza(zzvg, j);
        }
    }

    public final zzaxw zza(Clock clock, String str) {
        return new zzaxw(clock, this, this.zzdzz.zzwx(), str);
    }
}
