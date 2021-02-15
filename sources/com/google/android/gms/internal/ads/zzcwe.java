package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzcwe<AdT> implements zzcta<AdT> {
    /* access modifiers changed from: protected */
    public abstract zzdzc<AdT> zza(zzdok zzdok, Bundle bundle);

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return !TextUtils.isEmpty(zzdnv.zzhdu.optString(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, ""));
    }

    public final zzdzc<AdT> zzb(zzdog zzdog, zzdnv zzdnv) {
        zzdnv zzdnv2 = zzdnv;
        String optString = zzdnv2.zzhdu.optString(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, "");
        zzdok zzdok = zzdog.zzhev.zzfsk;
        zzdom zzgt = new zzdom().zzc(zzdok).zzgt(optString);
        Bundle zzn = zzn(zzdok.zzhfa.zzchi);
        Bundle zzn2 = zzn(zzn.getBundle("com.google.ads.mediation.admob.AdMobAdapter"));
        zzn2.putInt("gw", 1);
        String optString2 = zzdnv2.zzhdu.optString("mad_hac", (String) null);
        if (optString2 != null) {
            zzn2.putString("mad_hac", optString2);
        }
        String optString3 = zzdnv2.zzhdu.optString("adJson", (String) null);
        if (optString3 != null) {
            zzn2.putString("_ad", optString3);
        }
        zzn2.putBoolean("_noRefresh", true);
        Iterator<String> keys = zzdnv2.zzhdx.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String optString4 = zzdnv2.zzhdx.optString(next, (String) null);
            if (next != null) {
                zzn2.putString(next, optString4);
            }
        }
        zzn.putBundle("com.google.ads.mediation.admob.AdMobAdapter", zzn2);
        zzvg zzvg = r5;
        zzvg zzvg2 = new zzvg(zzdok.zzhfa.versionCode, zzdok.zzhfa.zzchb, zzn2, zzdok.zzhfa.zzchc, zzdok.zzhfa.zzchd, zzdok.zzhfa.zzche, zzdok.zzhfa.zzadm, zzdok.zzhfa.zzbny, zzdok.zzhfa.zzchf, zzdok.zzhfa.zzchg, zzdok.zzhfa.zznb, zzdok.zzhfa.zzchh, zzn, zzdok.zzhfa.zzchj, zzdok.zzhfa.zzchk, zzdok.zzhfa.zzchl, zzdok.zzhfa.zzchm, zzdok.zzhfa.zzchn, zzdok.zzhfa.zzcho, zzdok.zzhfa.zzadn, zzdok.zzhfa.zzado, zzdok.zzhfa.zzchp);
        zzdok zzatn = zzgt.zzh(zzvg).zzatn();
        Bundle bundle = new Bundle();
        zzdog zzdog2 = zzdog;
        zzdnw zzdnw = zzdog2.zzhew.zzeou;
        Bundle bundle2 = new Bundle();
        bundle2.putStringArrayList("nofill_urls", new ArrayList(zzdnw.zzdkm));
        bundle2.putInt("refresh_interval", zzdnw.zzhel);
        bundle2.putString("gws_query_id", zzdnw.zzdtb);
        bundle.putBundle("parent_common_config", bundle2);
        String str = zzdog2.zzhev.zzfsk.zzhfb;
        Bundle bundle3 = new Bundle();
        bundle3.putString("initial_ad_unit_id", str);
        zzdnv zzdnv3 = zzdnv;
        bundle3.putString("allocation_id", zzdnv3.zzdjo);
        bundle3.putStringArrayList("click_urls", new ArrayList(zzdnv3.zzdkj));
        bundle3.putStringArrayList("imp_urls", new ArrayList(zzdnv3.zzdkk));
        bundle3.putStringArrayList("manual_tracking_urls", new ArrayList(zzdnv3.zzdsp));
        bundle3.putStringArrayList("fill_urls", new ArrayList(zzdnv3.zzhdn));
        bundle3.putStringArrayList("video_start_urls", new ArrayList(zzdnv3.zzdtd));
        bundle3.putStringArrayList("video_reward_urls", new ArrayList(zzdnv3.zzdte));
        bundle3.putStringArrayList("video_complete_urls", new ArrayList(zzdnv3.zzhdm));
        bundle3.putString(FirebaseAnalytics.Param.TRANSACTION_ID, zzdnv3.zzdjz);
        bundle3.putString("valid_from_timestamp", zzdnv3.zzdka);
        bundle3.putBoolean("is_closable_area_disabled", zzdnv3.zzbpd);
        if (zzdnv3.zzdtc != null) {
            Bundle bundle4 = new Bundle();
            bundle4.putInt("rb_amount", zzdnv3.zzdtc.zzdvs);
            bundle4.putString("rb_type", zzdnv3.zzdtc.type);
            bundle3.putParcelableArray("rewards", new Bundle[]{bundle4});
        }
        bundle.putBundle("parent_ad_config", bundle3);
        return zza(zzatn, bundle);
    }

    private static Bundle zzn(Bundle bundle) {
        return bundle == null ? new Bundle() : new Bundle(bundle);
    }
}
