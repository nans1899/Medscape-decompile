package com.google.android.gms.internal.ads;

import android.location.Location;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzany implements MediationAdRequest {
    private final String zzado;
    private final int zzchc;
    private final boolean zzchn;
    private final int zzdlk;
    private final int zzdll;
    private final Date zzmx;
    private final Set<String> zzmz;
    private final boolean zzna;
    private final Location zznb;

    public zzany(Date date, int i, Set<String> set, Location location, boolean z, int i2, boolean z2, int i3, String str) {
        this.zzmx = date;
        this.zzchc = i;
        this.zzmz = set;
        this.zznb = location;
        this.zzna = z;
        this.zzdlk = i2;
        this.zzchn = z2;
        this.zzdll = i3;
        this.zzado = str;
    }

    @Deprecated
    public final Date getBirthday() {
        return this.zzmx;
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

    public final boolean isTesting() {
        return this.zzna;
    }

    public final int taggedForChildDirectedTreatment() {
        return this.zzdlk;
    }

    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzchn;
    }
}
