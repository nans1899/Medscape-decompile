package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbtp;
import com.google.android.gms.internal.ads.zzbys;
import com.google.android.gms.internal.ads.zzdbd;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdaq extends zzaxk {
    private static final List<String> zzguf = new ArrayList(Arrays.asList(new String[]{"/aclk", "/pcs/click"}));
    private static final List<String> zzgug = new ArrayList(Arrays.asList(new String[]{".doubleclick.net", ".googleadservices.com"}));
    private static final List<String> zzguh = new ArrayList(Arrays.asList(new String[]{"/pagead/adview", "/pcs/view", "/pagead/conversion"}));
    private static final List<String> zzgui = new ArrayList(Arrays.asList(new String[]{".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"}));
    /* access modifiers changed from: private */
    public zzbbx zzbot;
    private final ScheduledExecutorService zzfmv;
    private zzeg zzfna;
    private Point zzfye = new Point();
    private Point zzfyf = new Point();
    private final zzdzb zzgcz;
    private zzdoz<zzcil> zzgpl;
    private zzbix zzguj;
    private zzasa zzguk;
    private Context zzvr;

    public zzdaq(zzbix zzbix, Context context, zzeg zzeg, zzbbx zzbbx, zzdoz<zzcil> zzdoz, zzdzb zzdzb, ScheduledExecutorService scheduledExecutorService) {
        this.zzguj = zzbix;
        this.zzvr = context;
        this.zzfna = zzeg;
        this.zzbot = zzbbx;
        this.zzgpl = zzdoz;
        this.zzgcz = zzdzb;
        this.zzfmv = scheduledExecutorService;
    }

    public final IObjectWrapper zzao(IObjectWrapper iObjectWrapper) {
        return null;
    }

    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return null;
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaxr zzaxr, zzaxg zzaxg) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        this.zzvr = context;
        String str = zzaxr.zzbuu;
        String str2 = zzaxr.zzbrb;
        zzvn zzvn = zzaxr.zzdye;
        zzvg zzvg = zzaxr.zzdyf;
        zzdan zzadx = this.zzguj.zzadx();
        zzbtp.zza zzce = new zzbtp.zza().zzce(context);
        zzdom zzdom = new zzdom();
        if (str == null) {
            str = "adUnitId";
        }
        zzdom zzgt = zzdom.zzgt(str);
        if (zzvg == null) {
            zzvg = new zzvj().zzpj();
        }
        zzdom zzh = zzgt.zzh(zzvg);
        if (zzvn == null) {
            zzvn = new zzvn();
        }
        zzdyq.zza(zzadx.zzg(zzce.zza(zzh.zze(zzvn).zzatn()).zzajv()).zza(new zzdbd(new zzdbd.zza().zzgs(str2))).zzg(new zzbys.zza().zzakr()).zzagt().zzags(), new zzdaz(this, zzaxg), this.zzguj.zzadi());
    }

    public final void zzan(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcxp)).booleanValue()) {
            MotionEvent motionEvent = (MotionEvent) ObjectWrapper.unwrap(iObjectWrapper);
            zzasa zzasa = this.zzguk;
            this.zzfye = zzbau.zza(motionEvent, zzasa == null ? null : zzasa.zzaat);
            if (motionEvent.getAction() == 0) {
                this.zzfyf = this.zzfye;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation((float) this.zzfye.x, (float) this.zzfye.y);
            this.zzfna.zza(obtain);
            obtain.recycle();
        }
    }

    public final void zza(zzasa zzasa) {
        this.zzguk = zzasa;
        this.zzgpl.zzed(1);
    }

    public final void zza(List<Uri> list, IObjectWrapper iObjectWrapper, zzart zzart) {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxp)).booleanValue()) {
            try {
                zzart.onError("The updating URL feature is not enabled.");
            } catch (RemoteException e) {
                zzbbq.zzc("", e);
            }
        } else {
            zzdzc zze = this.zzgcz.zze(new zzdap(this, list, iObjectWrapper));
            if (zzarf()) {
                zze = zzdyq.zzb(zze, new zzdas(this), (Executor) this.zzgcz);
            } else {
                zzayp.zzfd("Asset view map is empty.");
            }
            zzdyq.zza(zze, new zzdbc(this, zzart), this.zzguj.zzadi());
        }
    }

    public final void zzb(List<Uri> list, IObjectWrapper iObjectWrapper, zzart zzart) {
        try {
            if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxp)).booleanValue()) {
                zzart.onError("The updating URL feature is not enabled.");
            } else if (list.size() != 1) {
                zzart.onError("There should be only 1 click URL.");
            } else {
                Uri uri = list.get(0);
                if (!zza(uri, zzguf, zzgug)) {
                    String valueOf = String.valueOf(uri);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                    sb.append("Not a Google URL: ");
                    sb.append(valueOf);
                    zzayp.zzfe(sb.toString());
                    zzart.onSuccess(list);
                    return;
                }
                zzdzc zze = this.zzgcz.zze(new zzdar(this, uri, iObjectWrapper));
                if (zzarf()) {
                    zze = zzdyq.zzb(zze, new zzdau(this), (Executor) this.zzgcz);
                } else {
                    zzayp.zzfd("Asset view map is empty.");
                }
                zzdyq.zza(zze, new zzdbb(this, zzart), this.zzguj.zzadi());
            }
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    private static boolean zzk(Uri uri) {
        return zza(uri, zzguh, zzgui);
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final Uri zzb(Uri uri, IObjectWrapper iObjectWrapper) throws Exception {
        try {
            uri = this.zzfna.zza(uri, this.zzvr, (View) ObjectWrapper.unwrap(iObjectWrapper), (Activity) null);
        } catch (zzef e) {
            zzbbq.zzd("", e);
        }
        if (uri.getQueryParameter("ms") != null) {
            return uri;
        }
        throw new Exception("Failed to append spam signals to click url.");
    }

    private static boolean zza(Uri uri, List<String> list, List<String> list2) {
        String host = uri.getHost();
        String path = uri.getPath();
        if (!(host == null || path == null)) {
            for (String contains : list) {
                if (path.contains(contains)) {
                    for (String endsWith : list2) {
                        if (host.endsWith(endsWith)) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }

    private final zzdzc<String> zzgr(String str) {
        zzcil[] zzcilArr = new zzcil[1];
        zzdzc<O> zzb = zzdyq.zzb(this.zzgpl.zzatt(), new zzdax(this, zzcilArr, str), (Executor) this.zzgcz);
        zzb.addListener(new zzdba(this, zzcilArr), this.zzgcz);
        return zzdyl.zzg(zzb).zza((long) ((Integer) zzwm.zzpx().zzd(zzabb.zzcxq)).intValue(), TimeUnit.MILLISECONDS, this.zzfmv).zza(zzdav.zzdwz, this.zzgcz).zza(Exception.class, zzday.zzdwz, (Executor) this.zzgcz);
    }

    private static Uri zza(Uri uri, String str, String str2) {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf("&adurl=");
        if (indexOf == -1) {
            indexOf = uri2.indexOf("?adurl=");
        }
        if (indexOf == -1) {
            return uri.buildUpon().appendQueryParameter(str, str2).build();
        }
        int i = indexOf + 1;
        return Uri.parse(uri2.substring(0, i) + str + "=" + str2 + "&" + uri2.substring(i));
    }

    private final boolean zzarf() {
        zzasa zzasa = this.zzguk;
        return (zzasa == null || zzasa.zzdqc == null || this.zzguk.zzdqc.isEmpty()) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzcil[] zzcilArr) {
        if (zzcilArr[0] != null) {
            this.zzgpl.zzd(zzdyq.zzaf(zzcilArr[0]));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzcil[] zzcilArr, String str, zzcil zzcil) throws Exception {
        zzcilArr[0] = zzcil;
        JSONObject zza = zzbau.zza(this.zzvr, this.zzguk.zzdqc, this.zzguk.zzdqc, this.zzguk.zzaat);
        JSONObject zza2 = zzbau.zza(this.zzvr, this.zzguk.zzaat);
        JSONObject zzt = zzbau.zzt(this.zzguk.zzaat);
        JSONObject zzb = zzbau.zzb(this.zzvr, this.zzguk.zzaat);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("asset_view_signal", zza);
        jSONObject.put("ad_view_signal", zza2);
        jSONObject.put("scroll_view_signal", zzt);
        jSONObject.put("lock_screen_signal", zzb);
        if (str == "google.afma.nativeAds.getPublisherCustomRenderedClickSignals") {
            jSONObject.put("click_signal", zzbau.zza((String) null, this.zzvr, this.zzfyf, this.zzfye));
        }
        return zzcil.zzc(str, jSONObject);
    }

    static /* synthetic */ Uri zzc(Uri uri, String str) {
        return !TextUtils.isEmpty(str) ? zza(uri, "nas", str) : uri;
    }

    static /* synthetic */ ArrayList zza(List list, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Uri uri = (Uri) it.next();
            if (!zzk(uri) || TextUtils.isEmpty(str)) {
                arrayList.add(uri);
            } else {
                arrayList.add(zza(uri, "nas", str));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzl(Uri uri) throws Exception {
        return zzdyq.zzb(zzgr("google.afma.nativeAds.getPublisherCustomRenderedClickSignals"), new zzdaw(this, uri), (Executor) this.zzgcz);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzb(ArrayList arrayList) throws Exception {
        return zzdyq.zzb(zzgr("google.afma.nativeAds.getPublisherCustomRenderedImpressionSignals"), new zzdat(this, arrayList), (Executor) this.zzgcz);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ArrayList zza(List list, IObjectWrapper iObjectWrapper) throws Exception {
        String zza = this.zzfna.zzca() != null ? this.zzfna.zzca().zza(this.zzvr, (View) ObjectWrapper.unwrap(iObjectWrapper), (Activity) null) : "";
        if (!TextUtils.isEmpty(zza)) {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Uri uri = (Uri) it.next();
                if (!zzk(uri)) {
                    String valueOf = String.valueOf(uri);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                    sb.append("Not a Google URL: ");
                    sb.append(valueOf);
                    zzayp.zzfe(sb.toString());
                    arrayList.add(uri);
                } else {
                    arrayList.add(zza(uri, "ms", zza));
                }
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
            throw new Exception("Empty impression URLs result.");
        }
        throw new Exception("Failed to get view signals.");
    }
}
