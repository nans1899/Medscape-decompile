package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.appboy.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzp;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmz {
    private final Executor executor;
    private final String packageName;
    private final String zzbre;
    private final String zzcyu = zzacp.zzdao.get();
    private final zzbbu zzedh;
    private final Map<String, String> zzghi = new HashMap();
    private final boolean zzghp;
    private final Context zzvr;

    public zzcmz(Executor executor2, zzbbu zzbbu, Context context, zzbbx zzbbx) {
        this.executor = executor2;
        this.zzedh = zzbbu;
        this.zzvr = context;
        this.packageName = context.getPackageName();
        this.zzghp = ((double) zzwm.zzqa().nextFloat()) <= zzacp.zzdan.get().doubleValue();
        this.zzbre = zzbbx.zzbre;
        this.zzghi.put(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY, "gmob_sdk");
        this.zzghi.put("v", ExifInterface.GPS_MEASUREMENT_3D);
        this.zzghi.put(AdParameterKeys.OS, Build.VERSION.RELEASE);
        this.zzghi.put("api_v", Build.VERSION.SDK);
        Map<String, String> map = this.zzghi;
        zzp.zzkr();
        map.put("device", zzayu.zzxt());
        this.zzghi.put(AdParameterKeys.SECTION_ID, this.packageName);
        Map<String, String> map2 = this.zzghi;
        zzp.zzkr();
        map2.put("is_lite_sdk", zzayu.zzaz(this.zzvr) ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.zzghi.put("e", TextUtils.join(",", zzabb.zzrh()));
        this.zzghi.put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, this.zzbre);
    }

    /* access modifiers changed from: package-private */
    public final void zzn(Map<String, String> map) {
        String zzo = zzo(map);
        if (this.zzghp) {
            this.executor.execute(new zzcnc(this, zzo));
        }
        zzayp.zzei(zzo);
    }

    public final Map<String, String> zzapg() {
        return new HashMap(this.zzghi);
    }

    public final ConcurrentHashMap<String, String> zzaph() {
        return new ConcurrentHashMap<>(this.zzghi);
    }

    /* access modifiers changed from: package-private */
    public final String zzo(Map<String, String> map) {
        Uri.Builder buildUpon = Uri.parse(this.zzcyu).buildUpon();
        for (Map.Entry next : map.entrySet()) {
            buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
        }
        return buildUpon.build().toString();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzgi(String str) {
        this.zzedh.zzes(str);
    }
}
