package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.medscape.android.cache.Cache;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbfe implements zzahq<zzbdu> {
    private boolean zzeli;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            zzwm.zzpt();
            return zzbbg.zzc(context, Integer.parseInt(str2));
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(str2).length());
            sb.append("Could not parse ");
            sb.append(str);
            sb.append(" in a video GMSG: ");
            sb.append(str2);
            zzayp.zzfe(sb.toString());
            return i;
        }
    }

    private static void zza(zzbdh zzbdh, Map<String, String> map) {
        String str = map.get("minBufferMs");
        String str2 = map.get("maxBufferMs");
        String str3 = map.get("bufferForPlaybackMs");
        String str4 = map.get("bufferForPlaybackAfterRebufferMs");
        String str5 = map.get("socketReceiveBufferSize");
        if (str != null) {
            try {
                zzbdh.zzdl(Integer.parseInt(str));
            } catch (NumberFormatException unused) {
                zzayp.zzfe(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", new Object[]{str, str2}));
                return;
            }
        }
        if (str2 != null) {
            zzbdh.zzdm(Integer.parseInt(str2));
        }
        if (str3 != null) {
            zzbdh.zzdn(Integer.parseInt(str3));
        }
        if (str4 != null) {
            zzbdh.zzdo(Integer.parseInt(str4));
        }
        if (str5 != null) {
            zzbdh.zzdp(Integer.parseInt(str5));
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        int i;
        zzbdu zzbdu = (zzbdu) obj;
        String str = (String) map.get("action");
        if (str == null) {
            zzayp.zzfe("Action missing from video GMSG.");
            return;
        }
        if (zzayp.isLoggable(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String jSONObject2 = jSONObject.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(jSONObject2).length());
            sb.append("Video GMSG: ");
            sb.append(str);
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(jSONObject2);
            zzayp.zzef(sb.toString());
        }
        if ("background".equals(str)) {
            String str2 = (String) map.get("color");
            if (TextUtils.isEmpty(str2)) {
                zzayp.zzfe("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzbdu.setBackgroundColor(Color.parseColor(str2));
            } catch (IllegalArgumentException unused) {
                zzayp.zzfe("Invalid color parameter in video GMSG.");
            }
        } else {
            if ("decoderProps".equals(str)) {
                String str3 = (String) map.get("mimeTypes");
                if (str3 == null) {
                    zzayp.zzfe("No MIME types specified for decoder properties inspection.");
                    zzbdh.zza(zzbdu, "missingMimeTypes");
                    return;
                }
                HashMap hashMap = new HashMap();
                for (String str4 : str3.split(",")) {
                    hashMap.put(str4, zzbbe.zzew(str4.trim()));
                }
                zzbdh.zza(zzbdu, (Map<String, List<Map<String, Object>>>) hashMap);
                return;
            }
            zzbdn zzzr = zzbdu.zzzr();
            if (zzzr == null) {
                zzayp.zzfe("Could not get underlay container for a video GMSG.");
                return;
            }
            boolean equals = AppSettingsData.STATUS_NEW.equals(str);
            boolean equals2 = "position".equals(str);
            if (equals || equals2) {
                Context context = zzbdu.getContext();
                int zza = zza(context, map, "x", 0);
                int zza2 = zza(context, map, "y", 0);
                int zza3 = zza(context, map, "w", -1);
                int zza4 = zza(context, map, "h", -1);
                int min = Math.min(zza3, zzbdu.zzzz() - zza);
                int min2 = Math.min(zza4, zzbdu.zzzy() - zza2);
                try {
                    i = Integer.parseInt((String) map.get(OmnitureData.MODULE_NAME_VIDEO));
                } catch (NumberFormatException unused2) {
                    i = 0;
                }
                boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                if (!equals || zzzr.zzzn() != null) {
                    zzzr.zze(zza, zza2, min, min2);
                    return;
                }
                zzzr.zza(zza, zza2, min, min2, i, parseBoolean, new zzbdv((String) map.get("flags")));
                zzbdh zzzn = zzzr.zzzn();
                if (zzzn != null) {
                    zza(zzzn, (Map<String, String>) map);
                    return;
                }
                return;
            }
            zzbhd zzzs = zzbdu.zzzs();
            if (zzzs != null) {
                if ("timeupdate".equals(str)) {
                    String str5 = (String) map.get("currentTime");
                    if (str5 == null) {
                        zzayp.zzfe("currentTime parameter missing from timeupdate video GMSG.");
                        return;
                    }
                    try {
                        zzzs.zze(Float.parseFloat(str5));
                        return;
                    } catch (NumberFormatException unused3) {
                        String valueOf = String.valueOf(str5);
                        zzayp.zzfe(valueOf.length() != 0 ? "Could not parse currentTime parameter from timeupdate video GMSG: ".concat(valueOf) : new String("Could not parse currentTime parameter from timeupdate video GMSG: "));
                        return;
                    }
                } else if ("skip".equals(str)) {
                    zzzs.zzacw();
                    return;
                }
            }
            zzbdh zzzn2 = zzzr.zzzn();
            if (zzzn2 == null) {
                zzbdh.zzb(zzbdu);
            } else if ("click".equals(str)) {
                Context context2 = zzbdu.getContext();
                int zza5 = zza(context2, map, "x", 0);
                int zza6 = zza(context2, map, "y", 0);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) zza5, (float) zza6, 0);
                zzzn2.zze(obtain);
                obtain.recycle();
            } else if ("currentTime".equals(str)) {
                String str6 = (String) map.get(Cache.Caches.TIME);
                if (str6 == null) {
                    zzayp.zzfe("Time parameter missing from currentTime video GMSG.");
                    return;
                }
                try {
                    zzzn2.seekTo((int) (Float.parseFloat(str6) * 1000.0f));
                } catch (NumberFormatException unused4) {
                    String valueOf2 = String.valueOf(str6);
                    zzayp.zzfe(valueOf2.length() != 0 ? "Could not parse time parameter from currentTime video GMSG: ".concat(valueOf2) : new String("Could not parse time parameter from currentTime video GMSG: "));
                }
            } else if (MessengerShareContentUtility.SHARE_BUTTON_HIDE.equals(str)) {
                zzzn2.setVisibility(4);
            } else if ("load".equals(str)) {
                zzzn2.zzhy();
            } else if ("loadControl".equals(str)) {
                zza(zzzn2, (Map<String, String>) map);
            } else if ("muted".equals(str)) {
                if (Boolean.parseBoolean((String) map.get("muted"))) {
                    zzzn2.zzzh();
                } else {
                    zzzn2.zzzi();
                }
            } else if (OmnitureData.LINK_NAME_VIDEO_PAUSE.equals(str)) {
                zzzn2.pause();
            } else if (OmnitureData.LINK_NAME_VIDEO_PLAY.equals(str)) {
                zzzn2.play();
            } else if ("show".equals(str)) {
                zzzn2.setVisibility(0);
            } else if ("src".equals(str)) {
                String str7 = (String) map.get("src");
                String[] strArr = {str7};
                String str8 = (String) map.get("demuxed");
                if (str8 != null) {
                    try {
                        JSONArray jSONArray = new JSONArray(str8);
                        String[] strArr2 = new String[jSONArray.length()];
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            strArr2[i2] = jSONArray.getString(i2);
                        }
                        strArr = strArr2;
                    } catch (JSONException unused5) {
                        String valueOf3 = String.valueOf(str8);
                        zzayp.zzfe(valueOf3.length() != 0 ? "Malformed demuxed URL list for playback: ".concat(valueOf3) : new String("Malformed demuxed URL list for playback: "));
                        strArr = new String[]{str7};
                    }
                }
                zzzn2.zzc(str7, strArr);
            } else if ("touchMove".equals(str)) {
                Context context3 = zzbdu.getContext();
                zzzn2.zza((float) zza(context3, map, "dx", 0), (float) zza(context3, map, "dy", 0));
                if (!this.zzeli) {
                    zzbdu.zzuz();
                    this.zzeli = true;
                }
            } else if ("volume".equals(str)) {
                String str9 = (String) map.get("volume");
                if (str9 == null) {
                    zzayp.zzfe("Level parameter missing from volume video GMSG.");
                    return;
                }
                try {
                    zzzn2.setVolume(Float.parseFloat(str9));
                } catch (NumberFormatException unused6) {
                    String valueOf4 = String.valueOf(str9);
                    zzayp.zzfe(valueOf4.length() != 0 ? "Could not parse volume parameter from volume video GMSG: ".concat(valueOf4) : new String("Could not parse volume parameter from volume video GMSG: "));
                }
            } else if ("watermark".equals(str)) {
                zzzn2.zzzj();
            } else {
                String valueOf5 = String.valueOf(str);
                zzayp.zzfe(valueOf5.length() != 0 ? "Unknown video action: ".concat(valueOf5) : new String("Unknown video action: "));
            }
        }
    }
}
