package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import com.appboy.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzp;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzabf {
    private String zzbre = null;
    private String zzcyu;
    private Map<String, String> zzcyv;
    private Context zzvr = null;

    public zzabf(Context context, String str) {
        String str2;
        this.zzvr = context;
        this.zzbre = str;
        this.zzcyu = zzacp.zzdao.get();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.zzcyv = linkedHashMap;
        linkedHashMap.put(Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY, "gmob_sdk");
        this.zzcyv.put("v", ExifInterface.GPS_MEASUREMENT_3D);
        this.zzcyv.put(AdParameterKeys.OS, Build.VERSION.RELEASE);
        this.zzcyv.put("api_v", Build.VERSION.SDK);
        Map<String, String> map = this.zzcyv;
        zzp.zzkr();
        map.put("device", zzayu.zzxt());
        Map<String, String> map2 = this.zzcyv;
        if (context.getApplicationContext() != null) {
            str2 = context.getApplicationContext().getPackageName();
        } else {
            str2 = context.getPackageName();
        }
        map2.put(AdParameterKeys.SECTION_ID, str2);
        Map<String, String> map3 = this.zzcyv;
        zzp.zzkr();
        map3.put("is_lite_sdk", zzayu.zzaz(context) ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Future<zzatl> zzy = zzp.zzlc().zzy(this.zzvr);
        try {
            this.zzcyv.put("network_coarse", Integer.toString(zzy.get().zzdus));
            this.zzcyv.put("network_fine", Integer.toString(zzy.get().zzdut));
        } catch (Exception e) {
            zzp.zzkv().zza(e, "CsiConfiguration.CsiConfiguration");
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzrk() {
        return this.zzcyu;
    }

    /* access modifiers changed from: package-private */
    public final Context getContext() {
        return this.zzvr;
    }

    /* access modifiers changed from: package-private */
    public final String zzlu() {
        return this.zzbre;
    }

    /* access modifiers changed from: package-private */
    public final Map<String, String> zzrl() {
        return this.zzcyv;
    }
}
