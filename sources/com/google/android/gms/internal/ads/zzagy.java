package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import com.google.firebase.messaging.Constants;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzagy implements zzahq<zzbgj> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgj zzbgj = (zzbgj) obj;
        String str = (String) map.get("action");
        if ("tick".equals(str)) {
            String str2 = (String) map.get(Constants.ScionAnalytics.PARAM_LABEL);
            String str3 = (String) map.get("start_label");
            String str4 = (String) map.get("timestamp");
            if (TextUtils.isEmpty(str2)) {
                zzayp.zzfe("No label given for CSI tick.");
            } else if (TextUtils.isEmpty(str4)) {
                zzayp.zzfe("No timestamp given for CSI tick.");
            } else {
                try {
                    long elapsedRealtime = zzp.zzky().elapsedRealtime() + (Long.parseLong(str4) - zzp.zzky().currentTimeMillis());
                    if (TextUtils.isEmpty(str3)) {
                        str3 = "native:view_load";
                    }
                    zzbgj.zzzw().zzb(str2, str3, elapsedRealtime);
                } catch (NumberFormatException e) {
                    zzayp.zzd("Malformed timestamp for CSI tick.", e);
                }
            }
        } else if ("experiment".equals(str)) {
            String str5 = (String) map.get("value");
            if (TextUtils.isEmpty(str5)) {
                zzayp.zzfe("No value given for CSI experiment.");
                return;
            }
            zzabs zzrp = zzbgj.zzzw().zzrp();
            if (zzrp == null) {
                zzayp.zzfe("No ticker for WebView, dropping experiment ID.");
            } else {
                zzrp.zzh("e", str5);
            }
        } else if (com.appboy.Constants.APPBOY_PUSH_EXTRAS_KEY.equals(str)) {
            String str6 = (String) map.get("name");
            String str7 = (String) map.get("value");
            if (TextUtils.isEmpty(str7)) {
                zzayp.zzfe("No value given for CSI extra.");
            } else if (TextUtils.isEmpty(str6)) {
                zzayp.zzfe("No name given for CSI extra.");
            } else {
                zzabs zzrp2 = zzbgj.zzzw().zzrp();
                if (zzrp2 == null) {
                    zzayp.zzfe("No ticker for WebView, dropping extra parameter.");
                } else {
                    zzrp2.zzh(str6, str7);
                }
            }
        }
    }
}
