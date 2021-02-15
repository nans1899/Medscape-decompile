package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.applinks.AppLinkData;
import com.google.android.gms.common.internal.Preconditions;
import com.medscape.android.ads.AdsConstants;
import com.medscape.android.cache.Cache;
import com.medscape.android.settings.Settings;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbt implements zzdgu<Bundle> {
    private final zzdok zzfsk;

    public zzdbt(zzdok zzdok) {
        Preconditions.checkNotNull(zzdok, "the targeting must not be null");
        this.zzfsk = zzdok;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        zzvg zzvg = this.zzfsk.zzhfa;
        bundle.putString("slotname", this.zzfsk.zzhfb);
        boolean z = true;
        int i = zzdbs.zzgfd[this.zzfsk.zzhfh.zzheo - 1];
        if (i == 1) {
            bundle.putBoolean("is_new_rewarded", true);
        } else if (i == 2) {
            bundle.putBoolean("is_rewarded_interstitial", true);
        }
        zzdot.zza(bundle, "cust_age", new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date(zzvg.zzchb)), zzvg.zzchb != -1);
        zzdot.zza(bundle, AppLinkData.ARGUMENTS_EXTRAS_KEY, zzvg.extras);
        zzdot.zza(bundle, "cust_gender", Integer.valueOf(zzvg.zzchc), zzvg.zzchc != -1);
        zzdot.zza(bundle, AdsConstants.KW, zzvg.zzchd);
        zzdot.zza(bundle, "tag_for_child_directed_treatment", Integer.valueOf(zzvg.zzadm), zzvg.zzadm != -1);
        if (zzvg.zzche) {
            bundle.putBoolean("test_request", zzvg.zzche);
        }
        zzdot.zza(bundle, "d_imp_hdr", (Integer) 1, zzvg.versionCode >= 2 && zzvg.zzbny);
        zzdot.zza(bundle, "ppid", zzvg.zzchf, zzvg.versionCode >= 2 && !TextUtils.isEmpty(zzvg.zzchf));
        if (zzvg.zznb != null) {
            Location location = zzvg.zznb;
            Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
            Long valueOf2 = Long.valueOf(location.getTime() * 1000);
            Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
            Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
            Bundle bundle2 = new Bundle();
            bundle2.putFloat("radius", valueOf.floatValue());
            bundle2.putLong(Settings.LATITUDE_KEY, valueOf3.longValue());
            bundle2.putLong(Settings.LONGITUDE_KEY, valueOf4.longValue());
            bundle2.putLong(Cache.Caches.TIME, valueOf2.longValue());
            bundle.putBundle("uule", bundle2);
        }
        zzdot.zza(bundle, "url", zzvg.zzchh);
        zzdot.zza(bundle, "neighboring_content_urls", zzvg.zzchp);
        zzdot.zza(bundle, "custom_targeting", zzvg.zzchj);
        zzdot.zza(bundle, "category_exclusions", zzvg.zzchk);
        zzdot.zza(bundle, "request_agent", zzvg.zzchl);
        zzdot.zza(bundle, "request_pkg", zzvg.zzchm);
        zzdot.zza(bundle, "is_designed_for_families", Boolean.valueOf(zzvg.zzchn), zzvg.versionCode >= 7);
        if (zzvg.versionCode >= 8) {
            Integer valueOf5 = Integer.valueOf(zzvg.zzadn);
            if (zzvg.zzadn == -1) {
                z = false;
            }
            zzdot.zza(bundle, "tag_for_under_age_of_consent", valueOf5, z);
            zzdot.zza(bundle, "max_ad_content_rating", zzvg.zzado);
        }
    }
}
