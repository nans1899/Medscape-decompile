package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public interface zzbgj extends zzk, zzajr, zzaks, zzbdu, zzbfy, zzbhi, zzbhl, zzbhp, zzbhq, zzbhs, zzbht, zzqu {
    void destroy();

    Context getContext();

    int getHeight();

    ViewGroup.LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] iArr);

    ViewParent getParent();

    View getView();

    WebView getWebView();

    int getWidth();

    boolean isDestroyed();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5);

    void loadUrl(String str);

    void measure(int i, int i2);

    void onPause();

    void onResume();

    void setBackgroundColor(int i);

    void setOnClickListener(View.OnClickListener onClickListener);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void setRequestedOrientation(int i);

    void setWebChromeClient(WebChromeClient webChromeClient);

    void setWebViewClient(WebViewClient webViewClient);

    void zza(ViewGroup viewGroup, Activity activity, String str, String str2);

    void zza(zze zze);

    void zza(zzadn zzadn);

    void zza(zzads zzads);

    void zza(zzbhd zzbhd);

    void zza(zzbhy zzbhy);

    void zza(zzdnv zzdnv, zzdnw zzdnw);

    void zza(zzsc zzsc);

    void zza(String str, Predicate<zzahq<? super zzbgj>> predicate);

    void zza(String str, zzahq<? super zzbgj> zzahq);

    void zza(String str, zzbfl zzbfl);

    void zzabp();

    void zzabq();

    Context zzabr();

    zze zzabs();

    zze zzabt();

    zzbhy zzabu();

    String zzabv();

    zzbhv zzabw();

    WebViewClient zzabx();

    boolean zzaby();

    zzeg zzabz();

    IObjectWrapper zzaca();

    boolean zzacb();

    void zzacc();

    boolean zzacd();

    boolean zzace();

    void zzacf();

    void zzacg();

    zzads zzach();

    void zzaci();

    void zzacj();

    zzsc zzack();

    boolean zzacl();

    zzsu zzacm();

    boolean zzacn();

    void zzal(boolean z);

    void zzap(IObjectWrapper iObjectWrapper);

    void zzax(boolean z);

    void zzb(zze zze);

    void zzb(String str, zzahq<? super zzbgj> zzahq);

    void zzb(String str, String str2, String str3);

    void zzba(boolean z);

    void zzbb(boolean z);

    void zzbc(boolean z);

    void zzbw(Context context);

    boolean zzc(boolean z, int i);

    void zzdu(int i);

    void zzuy();

    zzbhd zzzs();

    Activity zzzu();

    zzb zzzv();

    zzabp zzzw();

    zzbbx zzzx();
}
