package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationExtrasReceiver;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.query.AdInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzyz {
    /* access modifiers changed from: private */
    public int zzadm = -1;
    /* access modifiers changed from: private */
    public int zzadn = -1;
    /* access modifiers changed from: private */
    public String zzado;
    /* access modifiers changed from: private */
    public boolean zzbny = false;
    /* access modifiers changed from: private */
    public int zzchc = -1;
    /* access modifiers changed from: private */
    public String zzchf;
    /* access modifiers changed from: private */
    public String zzchh;
    /* access modifiers changed from: private */
    public final Bundle zzchj = new Bundle();
    /* access modifiers changed from: private */
    public String zzchl;
    /* access modifiers changed from: private */
    public boolean zzchn;
    /* access modifiers changed from: private */
    public final List<String> zzchp = new ArrayList();
    /* access modifiers changed from: private */
    public final Bundle zzcjm = new Bundle();
    /* access modifiers changed from: private */
    public AdInfo zzcjr;
    /* access modifiers changed from: private */
    public final HashSet<String> zzcjz = new HashSet<>();
    /* access modifiers changed from: private */
    public final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzcka = new HashMap<>();
    /* access modifiers changed from: private */
    public final HashSet<String> zzckb = new HashSet<>();
    /* access modifiers changed from: private */
    public final HashSet<String> zzckc = new HashSet<>();
    /* access modifiers changed from: private */
    public Date zzmx;
    /* access modifiers changed from: private */
    public Location zznb;

    public final void zzcj(String str) {
        this.zzcjz.add(str);
    }

    @Deprecated
    public final void zza(NetworkExtras networkExtras) {
        if (networkExtras instanceof AdMobExtras) {
            zza(AdMobAdapter.class, ((AdMobExtras) networkExtras).getExtras());
        } else {
            this.zzcka.put(networkExtras.getClass(), networkExtras);
        }
    }

    public final void zza(Class<? extends MediationExtrasReceiver> cls, Bundle bundle) {
        this.zzcjm.putBundle(cls.getName(), bundle);
    }

    public final void zzb(Class<? extends CustomEvent> cls, Bundle bundle) {
        if (this.zzcjm.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            this.zzcjm.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
        }
        this.zzcjm.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(cls.getName(), bundle);
    }

    public final void zzck(String str) {
        this.zzckb.add(str);
    }

    public final void zzcl(String str) {
        this.zzckb.remove(str);
    }

    @Deprecated
    public final void zza(Date date) {
        this.zzmx = date;
    }

    public final void zzcm(String str) {
        this.zzchh = str;
    }

    public final void zzc(List<String> list) {
        this.zzchp.clear();
        for (String next : list) {
            if (TextUtils.isEmpty(next)) {
                zzbbq.zzfe("neighboring content URL should not be null or empty");
            } else {
                this.zzchp.add(next);
            }
        }
    }

    @Deprecated
    public final void zzda(int i) {
        this.zzchc = i;
    }

    public final void zza(Location location) {
        this.zznb = location;
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzbny = z;
    }

    public final void zzcn(String str) {
        this.zzchf = str;
    }

    public final void zzco(String str) {
        this.zzchl = str;
    }

    @Deprecated
    public final void zzz(boolean z) {
        this.zzadm = z ? 1 : 0;
    }

    public final void zze(String str, String str2) {
        this.zzchj.putString(str, str2);
    }

    public final void zzcp(String str) {
        this.zzckc.add(str);
    }

    @Deprecated
    public final void zzaa(boolean z) {
        this.zzchn = z;
    }

    public final void zza(AdInfo adInfo) {
        this.zzcjr = adInfo;
    }

    @Deprecated
    public final void zzdb(int i) {
        if (i == -1 || i == 0 || i == 1) {
            this.zzadn = i;
        }
    }

    @Deprecated
    public final void zzcq(String str) {
        if ("G".equals(str) || "PG".equals(str) || "T".equals(str) || "MA".equals(str)) {
            this.zzado = str;
        }
    }
}
