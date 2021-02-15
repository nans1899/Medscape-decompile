package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaou {
    public static int zza(AdRequest.ErrorCode errorCode) {
        int i = zzaot.zzdme[errorCode.ordinal()];
        if (i == 2) {
            return 1;
        }
        if (i != 3) {
            return i != 4 ? 0 : 3;
        }
        return 2;
    }

    public static MediationAdRequest zza(zzvg zzvg, boolean z) {
        AdRequest.Gender gender;
        HashSet hashSet = zzvg.zzchd != null ? new HashSet(zzvg.zzchd) : null;
        Date date = new Date(zzvg.zzchb);
        int i = zzvg.zzchc;
        if (i == 1) {
            gender = AdRequest.Gender.MALE;
        } else if (i != 2) {
            gender = AdRequest.Gender.UNKNOWN;
        } else {
            gender = AdRequest.Gender.FEMALE;
        }
        return new MediationAdRequest(date, gender, hashSet, z, zzvg.zznb);
    }
}
