package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcei {
    private Bundle extras;
    private zzadw zzddo;
    private List<zzzk> zzdec = Collections.emptyList();
    private List<?> zzerh;
    private double zzerk;
    private float zzerw;
    private IObjectWrapper zzfrd;
    private int zzfzr;
    private zzyo zzfzs;
    private View zzfzt;
    private zzzk zzfzu;
    private zzbgj zzfzv;
    private zzbgj zzfzw;
    private View zzfzx;
    private IObjectWrapper zzfzy;
    private zzaee zzfzz;
    private zzaee zzgaa;
    private String zzgab;
    private SimpleArrayMap<String, zzadq> zzgac = new SimpleArrayMap<>();
    private SimpleArrayMap<String, String> zzgad = new SimpleArrayMap<>();
    private String zzgae;

    public final synchronized void zzdy(int i) {
        this.zzfzr = i;
    }

    public final synchronized void zzb(zzyo zzyo) {
        this.zzfzs = zzyo;
    }

    public final synchronized void zza(zzadw zzadw) {
        this.zzddo = zzadw;
    }

    public final synchronized void setImages(List<zzadq> list) {
        this.zzerh = list;
    }

    public final synchronized void zzh(List<zzzk> list) {
        this.zzdec = list;
    }

    public final synchronized void zza(zzzk zzzk) {
        this.zzfzu = zzzk;
    }

    public final synchronized void zzac(View view) {
        this.zzfzx = view;
    }

    public final synchronized void setStarRating(double d) {
        this.zzerk = d;
    }

    public final synchronized void zza(zzaee zzaee) {
        this.zzfzz = zzaee;
    }

    public final synchronized void zzb(zzaee zzaee) {
        this.zzgaa = zzaee;
    }

    public final synchronized void zzga(String str) {
        this.zzgab = str;
    }

    public final synchronized void zzi(zzbgj zzbgj) {
        this.zzfzv = zzbgj;
    }

    public final synchronized void zzj(zzbgj zzbgj) {
        this.zzfzw = zzbgj;
    }

    public final synchronized void zzas(IObjectWrapper iObjectWrapper) {
        this.zzfrd = iObjectWrapper;
    }

    public final synchronized void zzn(String str, String str2) {
        if (str2 == null) {
            this.zzgad.remove(str);
        } else {
            this.zzgad.put(str, str2);
        }
    }

    public final synchronized void zza(String str, zzadq zzadq) {
        if (zzadq == null) {
            this.zzgac.remove(str);
        } else {
            this.zzgac.put(str, zzadq);
        }
    }

    private final synchronized void setMediaContentAspectRatio(float f) {
        this.zzerw = f;
    }

    public final synchronized void zzgb(String str) {
        this.zzgae = str;
    }

    private final synchronized String zzgc(String str) {
        return this.zzgad.get(str);
    }

    public final synchronized int zzama() {
        return this.zzfzr;
    }

    public final synchronized zzyo getVideoController() {
        return this.zzfzs;
    }

    public final synchronized zzadw zzsm() {
        return this.zzddo;
    }

    public final synchronized View zzamb() {
        return this.zzfzt;
    }

    public final synchronized String getHeadline() {
        return zzgc("headline");
    }

    public final synchronized List<?> getImages() {
        return this.zzerh;
    }

    public final zzaee zzamc() {
        List<?> list = this.zzerh;
        if (!(list == null || list.size() == 0)) {
            Object obj = this.zzerh.get(0);
            if (obj instanceof IBinder) {
                return zzaed.zzo((IBinder) obj);
            }
        }
        return null;
    }

    public final synchronized List<zzzk> getMuteThisAdReasons() {
        return this.zzdec;
    }

    public final synchronized zzzk zzamd() {
        return this.zzfzu;
    }

    public final synchronized String getBody() {
        return zzgc("body");
    }

    public final synchronized Bundle getExtras() {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        return this.extras;
    }

    public final synchronized String getCallToAction() {
        return zzgc("call_to_action");
    }

    public final synchronized View zzame() {
        return this.zzfzx;
    }

    public final synchronized IObjectWrapper zzsn() {
        return this.zzfzy;
    }

    public final synchronized String getStore() {
        return zzgc("store");
    }

    public final synchronized String getPrice() {
        return zzgc(FirebaseAnalytics.Param.PRICE);
    }

    public final synchronized double getStarRating() {
        return this.zzerk;
    }

    public final synchronized zzaee zzsl() {
        return this.zzfzz;
    }

    public final synchronized String getAdvertiser() {
        return zzgc("advertiser");
    }

    public final synchronized zzaee zzso() {
        return this.zzgaa;
    }

    public final synchronized String getCustomTemplateId() {
        return this.zzgab;
    }

    public final synchronized zzbgj zzamf() {
        return this.zzfzv;
    }

    public final synchronized zzbgj zzamg() {
        return this.zzfzw;
    }

    public final synchronized IObjectWrapper zzamh() {
        return this.zzfrd;
    }

    public final synchronized SimpleArrayMap<String, zzadq> zzami() {
        return this.zzgac;
    }

    public final synchronized float getMediaContentAspectRatio() {
        return this.zzerw;
    }

    public final synchronized String zzamj() {
        return this.zzgae;
    }

    public final synchronized SimpleArrayMap<String, String> zzamk() {
        return this.zzgad;
    }

    public final synchronized void destroy() {
        if (this.zzfzv != null) {
            this.zzfzv.destroy();
            this.zzfzv = null;
        }
        if (this.zzfzw != null) {
            this.zzfzw.destroy();
            this.zzfzw = null;
        }
        this.zzfrd = null;
        this.zzgac.clear();
        this.zzgad.clear();
        this.zzfzs = null;
        this.zzddo = null;
        this.zzfzt = null;
        this.zzerh = null;
        this.extras = null;
        this.zzfzx = null;
        this.zzfzy = null;
        this.zzfzz = null;
        this.zzgaa = null;
        this.zzgab = null;
    }

    public static zzcei zza(zzanp zzanp) {
        try {
            zzcef zza = zza(zzanp.getVideoController(), (zzanu) null);
            zzadw zzsm = zzanp.zzsm();
            String headline = zzanp.getHeadline();
            List<?> images = zzanp.getImages();
            String body = zzanp.getBody();
            Bundle extras2 = zzanp.getExtras();
            String callToAction = zzanp.getCallToAction();
            IObjectWrapper zzsn = zzanp.zzsn();
            String advertiser = zzanp.getAdvertiser();
            zzaee zzso = zzanp.zzso();
            zzcei zzcei = new zzcei();
            zzcei.zzfzr = 1;
            zzcei.zzfzs = zza;
            zzcei.zzddo = zzsm;
            zzcei.zzfzt = (View) zzat(zzanp.zzue());
            zzcei.zzn("headline", headline);
            zzcei.zzerh = images;
            zzcei.zzn("body", body);
            zzcei.extras = extras2;
            zzcei.zzn("call_to_action", callToAction);
            zzcei.zzfzx = (View) zzat(zzanp.zzuf());
            zzcei.zzfzy = zzsn;
            zzcei.zzn("advertiser", advertiser);
            zzcei.zzgaa = zzso;
            return zzcei;
        } catch (RemoteException e) {
            zzayp.zzd("Failed to get native ad from content ad mapper", e);
            return null;
        }
    }

    public static zzcei zza(zzano zzano) {
        try {
            zzcef zza = zza(zzano.getVideoController(), (zzanu) null);
            zzadw zzsm = zzano.zzsm();
            String headline = zzano.getHeadline();
            List<?> images = zzano.getImages();
            String body = zzano.getBody();
            Bundle extras2 = zzano.getExtras();
            String callToAction = zzano.getCallToAction();
            IObjectWrapper zzsn = zzano.zzsn();
            String store = zzano.getStore();
            String price = zzano.getPrice();
            double starRating = zzano.getStarRating();
            zzaee zzsl = zzano.zzsl();
            zzcei zzcei = new zzcei();
            zzcei.zzfzr = 2;
            zzcei.zzfzs = zza;
            zzcei.zzddo = zzsm;
            zzcei.zzfzt = (View) zzat(zzano.zzue());
            zzcei.zzn("headline", headline);
            zzcei.zzerh = images;
            zzcei.zzn("body", body);
            zzcei.extras = extras2;
            zzcei.zzn("call_to_action", callToAction);
            zzcei.zzfzx = (View) zzat(zzano.zzuf());
            zzcei.zzfzy = zzsn;
            zzcei.zzn("store", store);
            zzcei.zzn(FirebaseAnalytics.Param.PRICE, price);
            zzcei.zzerk = starRating;
            zzcei.zzfzz = zzsl;
            return zzcei;
        } catch (RemoteException e) {
            zzayp.zzd("Failed to get native ad from app install ad mapper", e);
            return null;
        }
    }

    public static zzcei zzb(zzanu zzanu) {
        try {
            return zza(zza(zzanu.getVideoController(), zzanu), zzanu.zzsm(), (View) zzat(zzanu.zzue()), zzanu.getHeadline(), zzanu.getImages(), zzanu.getBody(), zzanu.getExtras(), zzanu.getCallToAction(), (View) zzat(zzanu.zzuf()), zzanu.zzsn(), zzanu.getStore(), zzanu.getPrice(), zzanu.getStarRating(), zzanu.zzsl(), zzanu.getAdvertiser(), zzanu.getMediaContentAspectRatio());
        } catch (RemoteException e) {
            zzayp.zzd("Failed to get native ad assets from unified ad mapper", e);
            return null;
        }
    }

    public static zzcei zzb(zzano zzano) {
        try {
            return zza(zza(zzano.getVideoController(), (zzanu) null), zzano.zzsm(), (View) zzat(zzano.zzue()), zzano.getHeadline(), zzano.getImages(), zzano.getBody(), zzano.getExtras(), zzano.getCallToAction(), (View) zzat(zzano.zzuf()), zzano.zzsn(), zzano.getStore(), zzano.getPrice(), zzano.getStarRating(), zzano.zzsl(), (String) null, 0.0f);
        } catch (RemoteException e) {
            zzayp.zzd("Failed to get native ad assets from app install ad mapper", e);
            return null;
        }
    }

    public static zzcei zzb(zzanp zzanp) {
        try {
            return zza(zza(zzanp.getVideoController(), (zzanu) null), zzanp.zzsm(), (View) zzat(zzanp.zzue()), zzanp.getHeadline(), zzanp.getImages(), zzanp.getBody(), zzanp.getExtras(), zzanp.getCallToAction(), (View) zzat(zzanp.zzuf()), zzanp.zzsn(), (String) null, (String) null, -1.0d, zzanp.zzso(), zzanp.getAdvertiser(), 0.0f);
        } catch (RemoteException e) {
            zzayp.zzd("Failed to get native ad assets from content ad mapper", e);
            return null;
        }
    }

    private static zzcei zza(zzyo zzyo, zzadw zzadw, View view, String str, List list, String str2, Bundle bundle, String str3, View view2, IObjectWrapper iObjectWrapper, String str4, String str5, double d, zzaee zzaee, String str6, float f) {
        zzcei zzcei = new zzcei();
        zzcei.zzfzr = 6;
        zzcei.zzfzs = zzyo;
        zzcei.zzddo = zzadw;
        zzcei.zzfzt = view;
        String str7 = str;
        zzcei.zzn("headline", str);
        zzcei.zzerh = list;
        String str8 = str2;
        zzcei.zzn("body", str2);
        zzcei.extras = bundle;
        String str9 = str3;
        zzcei.zzn("call_to_action", str3);
        zzcei.zzfzx = view2;
        zzcei.zzfzy = iObjectWrapper;
        String str10 = str4;
        zzcei.zzn("store", str4);
        String str11 = str5;
        zzcei.zzn(FirebaseAnalytics.Param.PRICE, str5);
        zzcei.zzerk = d;
        zzcei.zzfzz = zzaee;
        zzcei.zzn("advertiser", str6);
        zzcei.setMediaContentAspectRatio(f);
        return zzcei;
    }

    private static <T> T zzat(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null) {
            return null;
        }
        return ObjectWrapper.unwrap(iObjectWrapper);
    }

    private static zzcef zza(zzyo zzyo, zzanu zzanu) {
        if (zzyo == null) {
            return null;
        }
        return new zzcef(zzyo, zzanu);
    }
}
