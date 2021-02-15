package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbfq implements zzahq<zzbdu> {
    private static Integer zzb(Map<String, String> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(map.get(str)));
        } catch (NumberFormatException unused) {
            String str2 = map.get(str);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39 + String.valueOf(str2).length());
            sb.append("Precache invalid numeric parameter '");
            sb.append(str);
            sb.append("': ");
            sb.append(str2);
            zzayp.zzfe(sb.toString());
            return null;
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbfl zzbfl;
        zzbdu zzbdu = (zzbdu) obj;
        if (zzayp.isLoggable(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String valueOf = String.valueOf(jSONObject);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 15);
            sb.append("Precache GMSG: ");
            sb.append(valueOf);
            zzayp.zzef(sb.toString());
        }
        zzp.zzln();
        if (!map.containsKey("abort")) {
            String str = (String) map.get("src");
            if (str != null) {
                String[] strArr = {str};
                String str2 = (String) map.get("demuxed");
                if (str2 != null) {
                    try {
                        JSONArray jSONArray = new JSONArray(str2);
                        String[] strArr2 = new String[jSONArray.length()];
                        for (int i = 0; i < jSONArray.length(); i++) {
                            strArr2[i] = jSONArray.getString(i);
                        }
                        strArr = strArr2;
                    } catch (JSONException unused) {
                        String valueOf2 = String.valueOf(str2);
                        zzayp.zzfe(valueOf2.length() != 0 ? "Malformed demuxed URL list for precache: ".concat(valueOf2) : new String("Malformed demuxed URL list for precache: "));
                        strArr = null;
                    }
                }
                if (strArr == null) {
                    strArr = new String[]{str};
                }
                if (zzbfi.zzd(zzbdu) != null) {
                    zzayp.zzfe("Precache task is already running.");
                    return;
                } else if (zzbdu.zzzv() == null) {
                    zzayp.zzfe("Precache requires a dependency provider.");
                    return;
                } else {
                    zzbdv zzbdv = new zzbdv((String) map.get("flags"));
                    Integer zzb = zzb(map, OmnitureData.MODULE_NAME_VIDEO);
                    if (zzb == null) {
                        zzb = 0;
                    }
                    zzbfl = zzbdu.zzzv().zzboi.zza(zzbdu, zzb.intValue(), (String) null, zzbdv);
                    new zzbfg(zzbdu, zzbfl, str, strArr).zzwz();
                }
            } else {
                zzbfg zzd = zzbfi.zzd(zzbdu);
                if (zzd != null) {
                    zzbfl = zzd.zzelk;
                } else {
                    zzayp.zzfe("Precache must specify a source.");
                    return;
                }
            }
            Integer zzb2 = zzb(map, "minBufferMs");
            if (zzb2 != null) {
                zzbfl.zzdl(zzb2.intValue());
            }
            Integer zzb3 = zzb(map, "maxBufferMs");
            if (zzb3 != null) {
                zzbfl.zzdm(zzb3.intValue());
            }
            Integer zzb4 = zzb(map, "bufferForPlaybackMs");
            if (zzb4 != null) {
                zzbfl.zzdn(zzb4.intValue());
            }
            Integer zzb5 = zzb(map, "bufferForPlaybackAfterRebufferMs");
            if (zzb5 != null) {
                zzbfl.zzdo(zzb5.intValue());
            }
        } else if (!zzbfi.zzc(zzbdu)) {
            zzayp.zzfe("Precache abort but no precache task running.");
        }
    }
}
