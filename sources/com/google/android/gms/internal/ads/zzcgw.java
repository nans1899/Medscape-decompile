package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzp;
import com.wbmd.wbmddrugscommons.constants.Constants;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcgw {
    private final Executor executor;
    private final zzbbx zzbpe;
    private final zzadu zzdly;
    private final zzts zzemr;
    private final zzeg zzeom;
    private final ScheduledExecutorService zzfmv;
    private final zzcgn zzgdl;
    private final zzb zzgdm;
    private final zzchj zzgdn;
    private final Context zzvr;

    public zzcgw(Context context, zzcgn zzcgn, zzeg zzeg, zzbbx zzbbx, zzb zzb, zzts zzts, Executor executor2, zzdok zzdok, zzchj zzchj, ScheduledExecutorService scheduledExecutorService) {
        this.zzvr = context;
        this.zzgdl = zzcgn;
        this.zzeom = zzeg;
        this.zzbpe = zzbbx;
        this.zzgdm = zzb;
        this.zzemr = zzts;
        this.executor = executor2;
        this.zzdly = zzdok.zzdly;
        this.zzgdn = zzchj;
        this.zzfmv = scheduledExecutorService;
    }

    public static List<zzzk> zzj(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("mute");
        if (optJSONObject == null) {
            return zzdwp.zzaxo();
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray("reasons");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return zzdwp.zzaxo();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            zzzk zzl = zzl(optJSONArray.optJSONObject(i));
            if (zzl != null) {
                arrayList.add(zzl);
            }
        }
        return zzdwp.zzb(arrayList);
    }

    public static zzzk zzk(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("mute");
        if (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("default_reason")) == null) {
            return null;
        }
        return zzl(optJSONObject);
    }

    private static zzzk zzl(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("reason");
        String optString2 = jSONObject.optString("ping_url");
        if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
            return null;
        }
        return new zzzk(optString, optString2);
    }

    public final zzdzc<zzadq> zzc(JSONObject jSONObject, String str) {
        return zza(jSONObject.optJSONObject(str), this.zzdly.zzddm);
    }

    public final zzdzc<List<zzadq>> zzd(JSONObject jSONObject, String str) {
        return zza(jSONObject.optJSONArray(str), this.zzdly.zzddm, this.zzdly.zzbnp);
    }

    private final zzdzc<List<zzadq>> zza(JSONArray jSONArray, boolean z, boolean z2) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return zzdyq.zzaf(Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        int length = z2 ? jSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            arrayList.add(zza(jSONArray.optJSONObject(i), z));
        }
        return zzdyq.zzb(zzdyq.zzi(arrayList), zzcgv.zzdwz, this.executor);
    }

    private final zzdzc<zzadq> zza(JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return zzdyq.zzaf(null);
        }
        String optString = jSONObject.optString("url");
        if (TextUtils.isEmpty(optString)) {
            return zzdyq.zzaf(null);
        }
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        int optInt = jSONObject.optInt(GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH, -1);
        int optInt2 = jSONObject.optInt(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, -1);
        if (z) {
            return zzdyq.zzaf(new zzadq((Drawable) null, Uri.parse(optString), optDouble, optInt, optInt2));
        }
        return zza(jSONObject.optBoolean("require"), zzdyq.zzb(this.zzgdl.zza(optString, optDouble, optBoolean), new zzcgy(optString, optDouble, optInt, optInt2), this.executor), (Object) null);
    }

    public final zzdzc<zzadl> zze(JSONObject jSONObject, String str) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return zzdyq.zzaf(null);
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray(Constants.WBMDDrugResponseKeyDrugImages);
        JSONObject optJSONObject2 = optJSONObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE);
        if (optJSONArray == null && optJSONObject2 != null) {
            optJSONArray = new JSONArray();
            optJSONArray.put(optJSONObject2);
        }
        return zza(optJSONObject.optBoolean("require"), zzdyq.zzb(zza(optJSONArray, false, true), new zzcgx(this, optJSONObject), this.executor), (Object) null);
    }

    private static Integer zzf(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException unused) {
            return null;
        }
    }

    public final zzdzc<zzbgj> zzm(JSONObject jSONObject) {
        JSONObject zza = zzbao.zza(jSONObject, "html_containers", "instream");
        if (zza == null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("video");
            if (optJSONObject == null) {
                return zzdyq.zzaf(null);
            }
            if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
                zzayp.zzfe("Required field 'vast_xml' is missing");
                return zzdyq.zzaf(null);
            }
            return zza(zzdyq.zza(this.zzgdn.zzn(optJSONObject), (long) ((Integer) zzwm.zzpx().zzd(zzabb.zzcry)).intValue(), TimeUnit.SECONDS, this.zzfmv), (Object) null);
        }
        zzdzc<zzbgj> zzo = this.zzgdn.zzo(zza.optString("base_url"), zza.optString("html"));
        return zzdyq.zzb(zzo, new zzcgz(zzo), (Executor) zzbbz.zzeeu);
    }

    private static <T> zzdzc<T> zza(zzdzc<T> zzdzc, T t) {
        return zzdyq.zzb(zzdzc, Exception.class, new zzchc((Object) null), zzbbz.zzeeu);
    }

    private static <T> zzdzc<T> zza(boolean z, zzdzc<T> zzdzc, T t) {
        if (z) {
            return zzdyq.zzb(zzdzc, new zzchb(zzdzc), (Executor) zzbbz.zzeeu);
        }
        return zza(zzdzc, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzb(String str, Object obj) throws Exception {
        zzp.zzks();
        zzbgj zza = zzbgr.zza(this.zzvr, zzbhy.zzacy(), "native-omid", false, false, this.zzeom, (zzacb) null, this.zzbpe, (zzabs) null, (zzk) null, this.zzgdm, this.zzemr, (zzsu) null, false, (zzdnv) null, (zzdnw) null);
        zzbch zzl = zzbch.zzl(zza);
        zza.zzabw().zza((zzbhu) new zzche(zzl));
        zza.loadData(str, "text/html", "UTF-8");
        return zzl;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzadl zza(JSONObject jSONObject, List list) {
        Integer num = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        String optString = jSONObject.optString("text");
        Integer zzf = zzf(jSONObject, "bg_color");
        Integer zzf2 = zzf(jSONObject, "text_color");
        int optInt = jSONObject.optInt("text_size", -1);
        boolean optBoolean = jSONObject.optBoolean("allow_pub_rendering");
        int optInt2 = jSONObject.optInt("animation_ms", 1000);
        int optInt3 = jSONObject.optInt("presentation_ms", 4000);
        if (optInt > 0) {
            num = Integer.valueOf(optInt);
        }
        return new zzadl(optString, list, zzf, zzf2, num, optInt3 + optInt2, this.zzdly.zzbnq, optBoolean);
    }
}
