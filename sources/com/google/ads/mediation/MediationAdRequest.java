package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class MediationAdRequest {
    private final Date zzmx;
    private final AdRequest.Gender zzmy;
    private final Set<String> zzmz;
    private final boolean zzna;
    private final Location zznb;

    public MediationAdRequest(Date date, AdRequest.Gender gender, Set<String> set, boolean z, Location location) {
        this.zzmx = date;
        this.zzmy = gender;
        this.zzmz = set;
        this.zzna = z;
        this.zznb = location;
    }

    public Integer getAgeInYears() {
        if (this.zzmx == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(this.zzmx);
        Integer valueOf = Integer.valueOf(instance2.get(1) - instance.get(1));
        return (instance2.get(2) < instance.get(2) || (instance2.get(2) == instance.get(2) && instance2.get(5) < instance.get(5))) ? Integer.valueOf(valueOf.intValue() - 1) : valueOf;
    }

    public Date getBirthday() {
        return this.zzmx;
    }

    public AdRequest.Gender getGender() {
        return this.zzmy;
    }

    public Set<String> getKeywords() {
        return this.zzmz;
    }

    public Location getLocation() {
        return this.zznb;
    }

    public boolean isTesting() {
        return this.zzna;
    }
}
