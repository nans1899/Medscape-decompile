package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.facebook.applinks.AppLinkData;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.settings.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdqm implements zzdqj {
    private final Object[] zzhit;

    public zzdqm(zzvg zzvg, String str, int i, String str2, zzvs zzvs) {
        HashSet hashSet = new HashSet(Arrays.asList(str2.split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        arrayList.add(str);
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains("birthday")) {
            arrayList.add(Long.valueOf(zzvg.zzchb));
        }
        if (hashSet.contains(AppLinkData.ARGUMENTS_EXTRAS_KEY)) {
            arrayList.add(zzr(zzvg.extras));
        } else if (hashSet.contains("npa")) {
            arrayList.add(zzvg.extras.getString("npa"));
        }
        if (hashSet.contains(Settings.GENDER)) {
            arrayList.add(Integer.valueOf(zzvg.zzchc));
        }
        if (hashSet.contains("keywords")) {
            if (zzvg.zzchd != null) {
                arrayList.add(zzvg.zzchd.toString());
            } else {
                arrayList.add((Object) null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(zzvg.zzche));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(zzvg.zzadm));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(zzvg.zzbny));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(zzvg.zzchf);
        }
        if (hashSet.contains("location")) {
            if (zzvg.zznb != null) {
                arrayList.add(zzvg.zznb.toString());
            } else {
                arrayList.add((Object) null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(zzvg.zzchh);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(zzr(zzvg.zzchi));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(zzr(zzvg.zzchj));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (zzvg.zzchk != null) {
                arrayList.add(zzvg.zzchk.toString());
            } else {
                arrayList.add((Object) null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(zzvg.zzchl);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(zzvg.zzchm);
        }
        if (hashSet.contains("isDesignedForFamilies")) {
            arrayList.add(Boolean.valueOf(zzvg.zzchn));
        }
        if (hashSet.contains("tagForUnderAgeOfConsent")) {
            arrayList.add(Integer.valueOf(zzvg.zzadn));
        }
        if (hashSet.contains("maxAdContentRating")) {
            arrayList.add(zzvg.zzado);
        }
        if (hashSet.contains("orientation")) {
            if (zzvs != null) {
                arrayList.add(Integer.valueOf(zzvs.orientation));
            } else {
                arrayList.add((Object) null);
            }
        }
        this.zzhit = arrayList.toArray();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzdqm)) {
            return false;
        }
        return Arrays.equals(this.zzhit, ((zzdqm) obj).zzhit);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzhit);
    }

    public final String toString() {
        int hashCode = hashCode();
        String arrays = Arrays.toString(this.zzhit);
        StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 22);
        sb.append("[PoolKey#");
        sb.append(hashCode);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(arrays);
        sb.append("]");
        return sb.toString();
    }

    private static String zzr(Bundle bundle) {
        String str;
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = new TreeSet(bundle.keySet()).iterator();
        while (it.hasNext()) {
            Object obj = bundle.get((String) it.next());
            if (obj == null) {
                str = "null";
            } else if (obj instanceof Bundle) {
                str = zzr((Bundle) obj);
            } else {
                str = obj.toString();
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
