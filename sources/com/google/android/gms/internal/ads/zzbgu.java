package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbgu extends FrameLayout implements zzbgj {
    /* access modifiers changed from: private */
    public final zzbgj zzeof;
    private final zzbdn zzeog;
    private final AtomicBoolean zzeoh = new AtomicBoolean();

    public zzbgu(zzbgj zzbgj) {
        super(zzbgj.getContext());
        this.zzeof = zzbgj;
        this.zzeog = new zzbdn(zzbgj.zzabr(), this, this);
        if (!zzacn()) {
            addView(this.zzeof.getView());
        }
    }

    public final View getView() {
        return this;
    }

    public final zzbdn zzzr() {
        return this.zzeog;
    }

    public final void onPause() {
        this.zzeog.onPause();
        this.zzeof.onPause();
    }

    public final void zzacc() {
        this.zzeog.onDestroy();
        this.zzeof.zzacc();
    }

    public final void zzaci() {
        setBackgroundColor(0);
        this.zzeof.setBackgroundColor(0);
    }

    public final int zzzy() {
        return getMeasuredHeight();
    }

    public final int zzzz() {
        return getMeasuredWidth();
    }

    public final void zzaaa() {
        this.zzeof.zzaaa();
    }

    public final WebView getWebView() {
        return this.zzeof.getWebView();
    }

    public final void zza(String str, Map<String, ?> map) {
        this.zzeof.zza(str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        this.zzeof.zza(str, jSONObject);
    }

    public final void zza(String str, zzahq<? super zzbgj> zzahq) {
        this.zzeof.zza(str, zzahq);
    }

    public final void zzb(String str, zzahq<? super zzbgj> zzahq) {
        this.zzeof.zzb(str, zzahq);
    }

    public final void zza(String str, Predicate<zzahq<? super zzbgj>> predicate) {
        this.zzeof.zza(str, predicate);
    }

    public final void zzabp() {
        this.zzeof.zzabp();
    }

    public final void zzdu(int i) {
        this.zzeof.zzdu(i);
    }

    public final void zzuy() {
        this.zzeof.zzuy();
    }

    public final void zzabq() {
        this.zzeof.zzabq();
    }

    public final void zza(boolean z, long j) {
        this.zzeof.zza(z, j);
    }

    public final void zzdc(String str) {
        this.zzeof.zzdc(str);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        this.zzeof.zzb(str, jSONObject);
    }

    public final Activity zzzu() {
        return this.zzeof.zzzu();
    }

    public final Context zzabr() {
        return this.zzeof.zzabr();
    }

    public final zzb zzzv() {
        return this.zzeof.zzzv();
    }

    public final zze zzabs() {
        return this.zzeof.zzabs();
    }

    public final IObjectWrapper zzaca() {
        return this.zzeof.zzaca();
    }

    public final zze zzabt() {
        return this.zzeof.zzabt();
    }

    public final zzbhy zzabu() {
        return this.zzeof.zzabu();
    }

    public final String zzabv() {
        return this.zzeof.zzabv();
    }

    public final zzbhv zzabw() {
        return this.zzeof.zzabw();
    }

    public final WebViewClient zzabx() {
        return this.zzeof.zzabx();
    }

    public final boolean zzaby() {
        return this.zzeof.zzaby();
    }

    public final zzeg zzabz() {
        return this.zzeof.zzabz();
    }

    public final zzbbx zzzx() {
        return this.zzeof.zzzx();
    }

    public final boolean zzacb() {
        return this.zzeof.zzacb();
    }

    public final boolean isDestroyed() {
        return this.zzeof.isDestroyed();
    }

    public final boolean zzacd() {
        return this.zzeof.zzacd();
    }

    public final void zzko() {
        this.zzeof.zzko();
    }

    public final void zzkn() {
        this.zzeof.zzkn();
    }

    public final String getRequestId() {
        return this.zzeof.getRequestId();
    }

    public final zzabq zzzt() {
        return this.zzeof.zzzt();
    }

    public final zzabp zzzw() {
        return this.zzeof.zzzw();
    }

    public final zzbhd zzzs() {
        return this.zzeof.zzzs();
    }

    public final void zza(zze zze) {
        this.zzeof.zza(zze);
    }

    public final void zzap(IObjectWrapper iObjectWrapper) {
        this.zzeof.zzap(iObjectWrapper);
    }

    public final void zza(zzbhy zzbhy) {
        this.zzeof.zza(zzbhy);
    }

    public final void zzba(boolean z) {
        this.zzeof.zzba(z);
    }

    public final void zzacf() {
        this.zzeof.zzacf();
    }

    public final void zzbw(Context context) {
        this.zzeof.zzbw(context);
    }

    public final void zzal(boolean z) {
        this.zzeof.zzal(z);
    }

    public final void setRequestedOrientation(int i) {
        this.zzeof.setRequestedOrientation(i);
    }

    public final void zzb(zze zze) {
        this.zzeof.zzb(zze);
    }

    public final void zzbb(boolean z) {
        this.zzeof.zzbb(z);
    }

    public final void zza(String str, zzbfl zzbfl) {
        this.zzeof.zza(str, zzbfl);
    }

    public final zzbfl zzfj(String str) {
        return this.zzeof.zzfj(str);
    }

    public final void zzacg() {
        this.zzeof.zzacg();
    }

    public final void destroy() {
        IObjectWrapper zzaca = zzaca();
        if (zzaca != null) {
            zzayu.zzeba.post(new zzbgx(zzaca));
            zzayu.zzeba.postDelayed(new zzbgw(this), (long) ((Integer) zzwm.zzpx().zzd(zzabb.zzcud)).intValue());
            return;
        }
        this.zzeof.destroy();
    }

    public final void loadData(String str, String str2, String str3) {
        this.zzeof.loadData(str, str2, str3);
    }

    public final void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zzeof.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public final void loadUrl(String str) {
        this.zzeof.loadUrl(str);
    }

    public final void zzb(String str, String str2, String str3) {
        this.zzeof.zzb(str, str2, str3);
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzeof.setOnClickListener(onClickListener);
    }

    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.zzeof.setOnTouchListener(onTouchListener);
    }

    public final void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zzeof.setWebChromeClient(webChromeClient);
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        this.zzeof.setWebViewClient(webViewClient);
    }

    public final void onResume() {
        this.zzeof.onResume();
    }

    public final void zzacj() {
        TextView textView = new TextView(getContext());
        Resources resources = zzp.zzkv().getResources();
        textView.setText(resources != null ? resources.getString(R.string.s7) : "Test Ad");
        textView.setTextSize(15.0f);
        textView.setTextColor(-1);
        textView.setPadding(5, 0, 5, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-12303292);
        gradientDrawable.setCornerRadius(8.0f);
        textView.setBackground(gradientDrawable);
        addView(textView, new FrameLayout.LayoutParams(-2, -2, 49));
        bringChildToFront(textView);
    }

    public final void zzax(boolean z) {
        this.zzeof.zzax(z);
    }

    public final void zza(zzadn zzadn) {
        this.zzeof.zza(zzadn);
    }

    public final void zza(zzsc zzsc) {
        this.zzeof.zza(zzsc);
    }

    public final zzsc zzack() {
        return this.zzeof.zzack();
    }

    public final void zza(zzqr zzqr) {
        this.zzeof.zza(zzqr);
    }

    public final void zza(zzads zzads) {
        this.zzeof.zza(zzads);
    }

    public final zzads zzach() {
        return this.zzeof.zzach();
    }

    public final void zza(zzbhd zzbhd) {
        this.zzeof.zza(zzbhd);
    }

    public final boolean zzace() {
        return this.zzeof.zzace();
    }

    public final void zzbc(boolean z) {
        this.zzeof.zzbc(z);
    }

    public final void zzav(boolean z) {
        this.zzeof.zzav(z);
    }

    public final void zzuz() {
        this.zzeof.zzuz();
    }

    public final void zza(com.google.android.gms.ads.internal.overlay.zzb zzb) {
        this.zzeof.zza(zzb);
    }

    public final void zzb(boolean z, int i) {
        this.zzeof.zzb(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzeof.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzeof.zza(z, i, str, str2);
    }

    public final boolean zzc(boolean z, int i) {
        if (!this.zzeoh.compareAndSet(false, true)) {
            return true;
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcoe)).booleanValue()) {
            return false;
        }
        if (this.zzeof.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.zzeof.getParent()).removeView(this.zzeof.getView());
        }
        return this.zzeof.zzc(z, i);
    }

    public final boolean zzacl() {
        return this.zzeoh.get();
    }

    public final void zza(ViewGroup viewGroup, Activity activity, String str, String str2) {
        this.zzeof.zza(this, activity, str, str2);
    }

    public final zzsu zzacm() {
        return this.zzeof.zzacm();
    }

    public final boolean zzacn() {
        return this.zzeof.zzacn();
    }

    public final void zza(zzdnv zzdnv, zzdnw zzdnw) {
        this.zzeof.zza(zzdnv, zzdnw);
    }
}
