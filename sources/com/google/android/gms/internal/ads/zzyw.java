package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.mediation.MediationExtrasReceiver;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.query.AdInfo;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzyw {
    private final int zzadm;
    private final int zzadn;
    private final String zzado;
    private final boolean zzbny;
    private final int zzchc;
    private final String zzchf;
    private final String zzchh;
    private final Bundle zzchj;
    private final String zzchl;
    private final boolean zzchn;
    private final List<String> zzchp;
    private final Bundle zzcjm;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzcjn;
    private final SearchAdRequest zzcjo;
    private final Set<String> zzcjp;
    private final Set<String> zzcjq;
    private final AdInfo zzcjr;
    private final Date zzmx;
    private final Set<String> zzmz;
    private final Location zznb;

    public zzyw(zzyz zzyz) {
        this(zzyz, (SearchAdRequest) null);
    }

    public zzyw(zzyz zzyz, SearchAdRequest searchAdRequest) {
        this.zzmx = zzyz.zzmx;
        this.zzchh = zzyz.zzchh;
        this.zzchp = zzyz.zzchp;
        this.zzchc = zzyz.zzchc;
        this.zzmz = Collections.unmodifiableSet(zzyz.zzcjz);
        this.zznb = zzyz.zznb;
        this.zzbny = zzyz.zzbny;
        this.zzcjm = zzyz.zzcjm;
        this.zzcjn = Collections.unmodifiableMap(zzyz.zzcka);
        this.zzchf = zzyz.zzchf;
        this.zzchl = zzyz.zzchl;
        this.zzcjo = searchAdRequest;
        this.zzadm = zzyz.zzadm;
        this.zzcjp = Collections.unmodifiableSet(zzyz.zzckb);
        this.zzchj = zzyz.zzchj;
        this.zzcjq = Collections.unmodifiableSet(zzyz.zzckc);
        this.zzchn = zzyz.zzchn;
        this.zzcjr = zzyz.zzcjr;
        this.zzadn = zzyz.zzadn;
        this.zzado = zzyz.zzado;
    }

    @Deprecated
    public final Date getBirthday() {
        return this.zzmx;
    }

    public final String getContentUrl() {
        return this.zzchh;
    }

    public final List<String> zzqn() {
        return new ArrayList(this.zzchp);
    }

    @Deprecated
    public final int getGender() {
        return this.zzchc;
    }

    public final Set<String> getKeywords() {
        return this.zzmz;
    }

    public final Location getLocation() {
        return this.zznb;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzbny;
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return (NetworkExtras) this.zzcjn.get(cls);
    }

    public final Bundle getNetworkExtrasBundle(Class<? extends MediationExtrasReceiver> cls) {
        return this.zzcjm.getBundle(cls.getName());
    }

    public final Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> cls) {
        Bundle bundle = this.zzcjm.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null) {
            return bundle.getBundle(cls.getName());
        }
        return null;
    }

    public final String getPublisherProvidedId() {
        return this.zzchf;
    }

    public final String zzqo() {
        return this.zzchl;
    }

    public final SearchAdRequest zzqp() {
        return this.zzcjo;
    }

    public final boolean isTestDevice(Context context) {
        RequestConfiguration requestConfiguration = zzzd.zzqw().getRequestConfiguration();
        zzwm.zzpt();
        String zzbn = zzbbg.zzbn(context);
        return this.zzcjp.contains(zzbn) || requestConfiguration.getTestDeviceIds().contains(zzbn);
    }

    public final Map<Class<? extends NetworkExtras>, NetworkExtras> zzqq() {
        return this.zzcjn;
    }

    public final Bundle zzqr() {
        return this.zzcjm;
    }

    public final int zzqs() {
        return this.zzadm;
    }

    public final Bundle getCustomTargeting() {
        return this.zzchj;
    }

    public final Set<String> zzqt() {
        return this.zzcjq;
    }

    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzchn;
    }

    public final AdInfo zzqu() {
        return this.zzcjr;
    }

    public final int zzqv() {
        return this.zzadn;
    }

    public final String getMaxAdContentRating() {
        return this.zzado;
    }
}
