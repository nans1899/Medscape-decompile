package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzcf;
import com.medscape.android.Constants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdup implements zzdtf {
    private final Object zzhnt;
    private final zzdus zzhnu;
    private final zzdve zzhnv;
    private final zzdtc zzvw;

    zzdup(Object obj, zzdus zzdus, zzdve zzdve, zzdtc zzdtc) {
        this.zzhnt = obj;
        this.zzhnu = zzdus;
        this.zzhnv = zzdve;
        this.zzvw = zzdtc;
    }

    /* access modifiers changed from: package-private */
    public final zzdus zzawp() {
        return this.zzhnu;
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzawq() throws zzdvc {
        try {
        } catch (Exception e) {
            throw new zzdvc(2001, (Throwable) e);
        }
        return ((Boolean) this.zzhnt.getClass().getDeclaredMethod("init", new Class[0]).invoke(this.zzhnt, new Object[0])).booleanValue();
    }

    public final synchronized void close() throws zzdvc {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.zzhnt.getClass().getDeclaredMethod("close", new Class[0]).invoke(this.zzhnt, new Object[0]);
            this.zzvw.zzg((int) Constants.CONSULT_FEEDTYPE_FOLLOWED_POSTS, System.currentTimeMillis() - currentTimeMillis);
        } catch (Exception e) {
            throw new zzdvc((int) com.webmd.wbmdcmepulse.models.utils.Constants.TAG_SIGNUP_EDUCATION_FRAGMENT, (Throwable) e);
        }
    }

    public final synchronized String zzv(Context context, String str) {
        Map<String, Object> zzcd;
        zzcd = this.zzhnv.zzcd();
        zzcd.put("f", "q");
        zzcd.put("ctx", context);
        zzcd.put("aid", (Object) null);
        return zzk(zzb((Map<String, String>) null, zzcd));
    }

    public final synchronized String zzb(Context context, String str, View view, Activity activity) {
        Map<String, Object> zzce;
        zzce = this.zzhnv.zzce();
        zzce.put("f", "v");
        zzce.put("ctx", context);
        zzce.put("aid", (Object) null);
        zzce.put("view", view);
        zzce.put("act", activity);
        return zzk(zzb((Map<String, String>) null, zzce));
    }

    public final synchronized String zza(Context context, String str, String str2, View view, Activity activity) {
        Map<String, Object> zzcf;
        zzcf = this.zzhnv.zzcf();
        zzcf.put("f", "c");
        zzcf.put("ctx", context);
        zzcf.put("cs", str2);
        zzcf.put("aid", (Object) null);
        zzcf.put("view", view);
        zzcf.put("act", activity);
        return zzk(zzb((Map<String, String>) null, zzcf));
    }

    public final synchronized void zza(String str, MotionEvent motionEvent) throws zzdvc {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap hashMap = new HashMap();
            hashMap.put("aid", (Object) null);
            hashMap.put("evt", motionEvent);
            this.zzhnt.getClass().getDeclaredMethod("he", new Class[]{Map.class}).invoke(this.zzhnt, new Object[]{hashMap});
            this.zzvw.zzg((int) Constants.CONSULT_FEEDTYPE_TAG, System.currentTimeMillis() - currentTimeMillis);
        } catch (Exception e) {
            throw new zzdvc((int) com.webmd.wbmdcmepulse.models.utils.Constants.TAG_CONTINUE_FRAGMENT, (Throwable) e);
        }
    }

    public final synchronized int zzawr() throws zzdvc {
        try {
        } catch (Exception e) {
            throw new zzdvc((int) com.webmd.wbmdcmepulse.models.utils.Constants.TAG_SIGNUP_LIST_FRAGMENT, (Throwable) e);
        }
        return ((Integer) this.zzhnt.getClass().getDeclaredMethod("lcs", new Class[0]).invoke(this.zzhnt, new Object[0])).intValue();
    }

    private static String zzk(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return Base64.encodeToString(((zzcf.zzf) ((zzejz) zzcf.zzf.zzbl().zzb(zzcm.DG).zzj(zzeip.zzu(bArr)).zzbgt())).toByteArray(), 11);
    }

    private final synchronized byte[] zzb(Map<String, String> map, Map<String, Object> map2) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
        } catch (Exception e) {
            this.zzvw.zza((int) com.webmd.wbmdcmepulse.models.utils.Constants.TAG_DEBUG, System.currentTimeMillis() - currentTimeMillis, e);
            return null;
        }
        return (byte[]) this.zzhnt.getClass().getDeclaredMethod("xss", new Class[]{Map.class, Map.class}).invoke(this.zzhnt, new Object[]{null, map2});
    }
}
