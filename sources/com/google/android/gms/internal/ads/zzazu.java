package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzp;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzazu {
    private final Object lock = new Object();
    private String zzebw = "";
    private String zzebx = "";
    private boolean zzeby = false;
    private String zzebz = "";

    public final void zze(Context context, String str, String str2) {
        if (!zzf(context, str, str2)) {
            zza(context, "In-app preview failed to load because of a system error. Please try again later.", true, true);
        } else if (ExifInterface.GPS_MEASUREMENT_2D.equals(this.zzebz)) {
            zzayp.zzef("Creative is not pushed for this device.");
            zza(context, "There was no creative pushed from DFP to the device.", false, false);
        } else if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzebz)) {
            zzayp.zzef("The app is not linked for creative preview.");
            zzi(context, str, str2);
        } else if (AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(this.zzebz)) {
            zzayp.zzef("Device is linked for in app preview.");
            zza(context, "The device is successfully linked for creative preview.", false, true);
        }
    }

    public final void zza(Context context, String str, String str2, String str3) {
        boolean zzyg = zzyg();
        if (zzg(context, str, str2)) {
            if (!zzyg && !TextUtils.isEmpty(str3)) {
                zzc(context, str2, str3, str);
            }
            zzayp.zzef("Device is linked for debug signals.");
            zza(context, "The device is successfully linked for troubleshooting.", false, true);
            return;
        }
        zzi(context, str, str2);
    }

    private final boolean zzf(Context context, String str, String str2) {
        String zzh = zzh(context, zzd(context, (String) zzwm.zzpx().zzd(zzabb.zzctn), str, str2).toString(), str2);
        if (TextUtils.isEmpty(zzh)) {
            zzayp.zzef("Not linked for in app preview.");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(zzh.trim());
            String optString = jSONObject.optString("gct");
            this.zzebz = jSONObject.optString("status");
            synchronized (this.lock) {
                this.zzebx = optString;
            }
            return true;
        } catch (JSONException e) {
            zzayp.zzd("Fail to get in app preview response json.", e);
            return false;
        }
    }

    private final boolean zzg(Context context, String str, String str2) {
        String zzh = zzh(context, zzd(context, (String) zzwm.zzpx().zzd(zzabb.zzcto), str, str2).toString(), str2);
        if (TextUtils.isEmpty(zzh)) {
            zzayp.zzef("Not linked for debug signals.");
            return false;
        }
        try {
            boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(new JSONObject(zzh.trim()).optString("debug_mode"));
            synchronized (this.lock) {
                this.zzeby = equals;
            }
            return equals;
        } catch (JSONException e) {
            zzayp.zzd("Fail to get debug mode response json.", e);
            return false;
        }
    }

    private static String zzh(Context context, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", zzp.zzkr().zzs(context, str2));
        zzdzc<String> zzc = new zzbag(context).zzc(str, hashMap);
        try {
            return (String) zzc.get((long) ((Integer) zzwm.zzpx().zzd(zzabb.zzctq)).intValue(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            String valueOf = String.valueOf(str);
            zzayp.zzc(valueOf.length() != 0 ? "Timeout while retriving a response from: ".concat(valueOf) : new String("Timeout while retriving a response from: "), e);
            zzc.cancel(true);
            return null;
        } catch (InterruptedException e2) {
            String valueOf2 = String.valueOf(str);
            zzayp.zzc(valueOf2.length() != 0 ? "Interrupted while retriving a response from: ".concat(valueOf2) : new String("Interrupted while retriving a response from: "), e2);
            zzc.cancel(true);
            return null;
        } catch (Exception e3) {
            String valueOf3 = String.valueOf(str);
            zzayp.zzc(valueOf3.length() != 0 ? "Error retriving a response from: ".concat(valueOf3) : new String("Error retriving a response from: "), e3);
            return null;
        }
    }

    private final void zzi(Context context, String str, String str2) {
        zzp.zzkr();
        zzayu.zza(context, zzd(context, (String) zzwm.zzpx().zzd(zzabb.zzctm), str, str2));
    }

    public final boolean zzb(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || !zzp.zzlb().zzyg()) {
            return false;
        }
        zzayp.zzef("Sending troubleshooting signals to the server.");
        zzc(context, str, str2, str3);
        return true;
    }

    private final void zzc(Context context, String str, String str2, String str3) {
        Uri.Builder buildUpon = zzd(context, (String) zzwm.zzpx().zzd(zzabb.zzctp), str3, str).buildUpon();
        buildUpon.appendQueryParameter("debugData", str2);
        zzp.zzkr();
        zzayu.zzb(context, str, buildUpon.build().toString());
    }

    private final Uri zzd(Context context, String str, String str2, String str3) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("linkedDeviceId", zzbj(context));
        buildUpon.appendQueryParameter("adSlotPath", str2);
        buildUpon.appendQueryParameter("afmaVersion", str3);
        return buildUpon.build();
    }

    private final String zzbj(Context context) {
        String str;
        synchronized (this.lock) {
            if (TextUtils.isEmpty(this.zzebw)) {
                zzp.zzkr();
                String zzt = zzayu.zzt(context, "debug_signals_id.txt");
                this.zzebw = zzt;
                if (TextUtils.isEmpty(zzt)) {
                    zzp.zzkr();
                    this.zzebw = zzayu.zzxs();
                    zzp.zzkr();
                    zzayu.zzc(context, "debug_signals_id.txt", this.zzebw);
                }
            }
            str = this.zzebw;
        }
        return str;
    }

    public final String zzyf() {
        String str;
        synchronized (this.lock) {
            str = this.zzebx;
        }
        return str;
    }

    public final boolean zzyg() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzeby;
        }
        return z;
    }

    private final void zza(Context context, String str, boolean z, boolean z2) {
        if (!(context instanceof Activity)) {
            zzayp.zzfd("Can not create dialog without Activity Context");
        } else {
            zzayu.zzeba.post(new zzazx(this, context, str, z, z2));
        }
    }
}
