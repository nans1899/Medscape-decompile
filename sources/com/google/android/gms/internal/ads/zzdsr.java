package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdsr {
    private final Clock zzbqa;
    private final String zzbre;
    private final String zzcja;
    private final String zzdqu;
    private final zzeg zzfna;
    private final zzdoj zzfuj;
    private final zzcwj zzfwe;
    private final Context zzvr;

    public zzdsr(zzcwj zzcwj, zzbbx zzbbx, String str, String str2, Context context, zzdoj zzdoj, Clock clock, zzeg zzeg) {
        this.zzfwe = zzcwj;
        this.zzbre = zzbbx.zzbre;
        this.zzdqu = str;
        this.zzcja = str2;
        this.zzvr = context;
        this.zzfuj = zzdoj;
        this.zzbqa = clock;
        this.zzfna = zzeg;
    }

    public final List<String> zza(zzdog zzdog, zzdnv zzdnv, List<String> list) {
        return zza(zzdog, zzdnv, false, "", "", list);
    }

    public final List<String> zza(zzdog zzdog, zzdnv zzdnv, boolean z, String str, String str2, List<String> list) {
        ArrayList arrayList = new ArrayList();
        String str3 = z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
        for (String zzc : list) {
            String zzc2 = zzc(zzc(zzc(zzc, "@gw_adlocid@", zzdog.zzhev.zzfsk.zzhfb), "@gw_adnetrefresh@", str3), "@gw_sdkver@", this.zzbre);
            if (zzdnv != null) {
                zzc2 = zzaxh.zzc(zzc(zzc(zzc(zzc2, "@gw_qdata@", zzdnv.zzdkp), "@gw_adnetid@", zzdnv.zzagx), "@gw_allocid@", zzdnv.zzdjo), this.zzvr, zzdnv.zzdtp);
            }
            String zzc3 = zzc(zzc(zzc(zzc2, "@gw_adnetstatus@", this.zzfwe.zzaqm()), "@gw_seqnum@", this.zzdqu), "@gw_sessid@", this.zzcja);
            boolean z2 = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcro)).booleanValue() && !TextUtils.isEmpty(str);
            boolean isEmpty = true ^ TextUtils.isEmpty(str2);
            if (z2 || isEmpty) {
                if (this.zzfna.zzb(Uri.parse(zzc3))) {
                    Uri.Builder buildUpon = Uri.parse(zzc3).buildUpon();
                    if (z2) {
                        buildUpon = buildUpon.appendQueryParameter("ms", str);
                    }
                    if (isEmpty) {
                        buildUpon = buildUpon.appendQueryParameter("attok", str2);
                    }
                    zzc3 = buildUpon.build().toString();
                }
            }
            arrayList.add(zzc3);
        }
        return arrayList;
    }

    public final List<String> zza(zzdnv zzdnv, List<String> list, zzatw zzatw) {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = this.zzbqa.currentTimeMillis();
        try {
            String type = zzatw.getType();
            String num = Integer.toString(zzatw.getAmount());
            zzdoj zzdoj = this.zzfuj;
            String str = "";
            String zzgy = zzdoj == null ? str : zzgy(zzdoj.zzdvz);
            zzdoj zzdoj2 = this.zzfuj;
            if (zzdoj2 != null) {
                str = zzgy(zzdoj2.zzdwa);
            }
            for (String zzc : list) {
                arrayList.add(zzaxh.zzc(zzc(zzc(zzc(zzc(zzc(zzc(zzc, "@gw_rwd_userid@", Uri.encode(zzgy)), "@gw_rwd_custom_data@", Uri.encode(str)), "@gw_tmstmp@", Long.toString(currentTimeMillis)), "@gw_rwd_itm@", Uri.encode(type)), "@gw_rwd_amt@", num), "@gw_sdkver@", this.zzbre), this.zzvr, zzdnv.zzdtp));
            }
            return arrayList;
        } catch (RemoteException e) {
            zzayp.zzc("Unable to determine award type and amount.", e);
            return arrayList;
        }
    }

    private static String zzc(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        return str.replaceAll(str2, str3);
    }

    private static String zzgy(String str) {
        return (TextUtils.isEmpty(str) || !zzbbk.isEnabled()) ? str : "fakeForAdDebugLog";
    }
}
