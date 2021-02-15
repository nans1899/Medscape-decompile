package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.webmd.wbmdcmepulse.models.articles.HtmlTableRow;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzagx {
    public static final zzahq<zzbgj> zzder = zzaha.zzdfl;
    public static final zzahq<zzbgj> zzdes = zzagz.zzdfl;
    public static final zzahq<zzbgj> zzdet = zzahc.zzdfl;
    public static final zzahq<zzbgj> zzdeu = zzahb.zzdfl;
    public static final zzahq<zzbgj> zzdev = new zzahg();
    public static final zzahq<zzbgj> zzdew = new zzahi();
    public static final zzahq<zzbgj> zzdex = zzahe.zzdfl;
    public static final zzahq<Object> zzdey = new zzahh();
    public static final zzahq<zzbgj> zzdez = new zzahk();
    public static final zzahq<zzbgj> zzdfa = zzahd.zzdfl;
    public static final zzahq<zzbgj> zzdfb = new zzahj();
    public static final zzahq<zzbgj> zzdfc = new zzahm();
    public static final zzahq<zzbdu> zzdfd = new zzbfe();
    public static final zzahq<zzbdu> zzdfe = new zzbfh();
    public static final zzahq<zzbgj> zzdff = new zzagy();
    public static final zzahw zzdfg = new zzahw();
    public static final zzahq<zzbgj> zzdfh = new zzahl();
    public static final zzahq<zzbgj> zzdfi = new zzaho();
    public static final zzahq<zzbgj> zzdfj = new zzahn();
    public static final zzahq<zzbgj> zzdfk = new zzahf();

    static final /* synthetic */ void zza(zzbhq zzbhq, Map map) {
        String str = (String) map.get("tx");
        String str2 = (String) map.get("ty");
        String str3 = (String) map.get(HtmlTableRow.Row_TYPE_TABLE_DATA);
        try {
            int parseInt = Integer.parseInt(str);
            int parseInt2 = Integer.parseInt(str2);
            int parseInt3 = Integer.parseInt(str3);
            zzeg zzabz = zzbhq.zzabz();
            if (zzabz != null) {
                zzabz.zzca().zza(parseInt, parseInt2, parseInt3);
            }
        } catch (NumberFormatException unused) {
            zzayp.zzfe("Could not parse touch parameters from gmsg.");
        }
    }

    static final /* synthetic */ void zza(zzbhi zzbhi, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzayp.zzfe("URL missing from httpTrack GMSG.");
        } else {
            new zzbax(zzbhi.getContext(), ((zzbht) zzbhi).zzzx().zzbre, str).zzwz();
        }
    }

    static final /* synthetic */ void zza(zzbfy zzbfy, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzayp.zzfe("URL missing from click GMSG.");
            return;
        }
        Uri parse = Uri.parse(str);
        try {
            zzeg zzabz = ((zzbhq) zzbfy).zzabz();
            if (zzabz != null && zzabz.zzb(parse)) {
                parse = zzabz.zza(parse, ((zzbhi) zzbfy).getContext(), ((zzbhs) zzbfy).getView(), ((zzbhi) zzbfy).zzzu());
            }
        } catch (zzef unused) {
            String valueOf = String.valueOf(str);
            zzayp.zzfe(valueOf.length() != 0 ? "Unable to append parameter to URL: ".concat(valueOf) : new String("Unable to append parameter to URL: "));
        }
        zzbhi zzbhi = (zzbhi) zzbfy;
        new zzbax(zzbhi.getContext(), ((zzbht) zzbfy).zzzx().zzbre, zzaxh.zzb(parse, zzbhi.getContext())).zzwz();
    }

    static final /* synthetic */ void zzb(zzbhi zzbhi, Map map) {
        PackageManager packageManager = zzbhi.getContext().getPackageManager();
        try {
            try {
                JSONArray jSONArray = new JSONObject((String) map.get("data")).getJSONArray("intents");
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        String optString = jSONObject2.optString("id");
                        String optString2 = jSONObject2.optString("u");
                        String optString3 = jSONObject2.optString("i");
                        String optString4 = jSONObject2.optString("m");
                        String optString5 = jSONObject2.optString("p");
                        String optString6 = jSONObject2.optString("c");
                        String optString7 = jSONObject2.optString("intent_url");
                        Intent intent = null;
                        if (!TextUtils.isEmpty(optString7)) {
                            try {
                                intent = Intent.parseUri(optString7, 0);
                            } catch (URISyntaxException e) {
                                URISyntaxException uRISyntaxException = e;
                                String valueOf = String.valueOf(optString7);
                                zzayp.zzc(valueOf.length() != 0 ? "Error parsing the url: ".concat(valueOf) : new String("Error parsing the url: "), uRISyntaxException);
                            }
                        }
                        boolean z = true;
                        if (intent == null) {
                            intent = new Intent();
                            if (!TextUtils.isEmpty(optString2)) {
                                intent.setData(Uri.parse(optString2));
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                intent.setAction(optString3);
                            }
                            if (!TextUtils.isEmpty(optString4)) {
                                intent.setType(optString4);
                            }
                            if (!TextUtils.isEmpty(optString5)) {
                                intent.setPackage(optString5);
                            }
                            if (!TextUtils.isEmpty(optString6)) {
                                String[] split = optString6.split("/", 2);
                                if (split.length == 2) {
                                    intent.setComponent(new ComponentName(split[0], split[1]));
                                }
                            }
                        }
                        if (packageManager.resolveActivity(intent, 65536) == null) {
                            z = false;
                        }
                        try {
                            jSONObject.put(optString, z);
                        } catch (JSONException e2) {
                            zzayp.zzc("Error constructing openable urls response.", e2);
                        }
                    } catch (JSONException e3) {
                        zzayp.zzc("Error parsing the intent data.", e3);
                    }
                }
                ((zzajr) zzbhi).zza("openableIntents", jSONObject);
            } catch (JSONException unused) {
                ((zzajr) zzbhi).zza("openableIntents", new JSONObject());
            }
        } catch (JSONException unused2) {
            ((zzajr) zzbhi).zza("openableIntents", new JSONObject());
        }
    }

    static final /* synthetic */ void zzc(zzbhi zzbhi, Map map) {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcyl)).booleanValue()) {
            zzayp.zzfe("canOpenAppGmsgHandler disabled.");
            return;
        }
        String str = (String) map.get("package_name");
        if (TextUtils.isEmpty(str)) {
            zzayp.zzfe("Package name missing in canOpenApp GMSG.");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(str, Boolean.valueOf(zzbhi.getContext().getPackageManager().getLaunchIntentForPackage(str) != null));
        ((zzajr) zzbhi).zza("openableApp", (Map<String, ?>) hashMap);
    }

    static final /* synthetic */ void zzd(zzbhi zzbhi, Map map) {
        String str = (String) map.get("urls");
        if (TextUtils.isEmpty(str)) {
            zzayp.zzfe("URLs missing in canOpenURLs GMSG.");
            return;
        }
        String[] split = str.split(",");
        HashMap hashMap = new HashMap();
        PackageManager packageManager = zzbhi.getContext().getPackageManager();
        for (String str2 : split) {
            String[] split2 = str2.split(";", 2);
            boolean z = true;
            if (packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) == null) {
                z = false;
            }
            hashMap.put(str2, Boolean.valueOf(z));
        }
        ((zzajr) zzbhi).zza("openableURLs", (Map<String, ?>) hashMap);
    }
}
