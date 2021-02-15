package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zztu;
import com.google.android.gms.internal.ads.zzua;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbgz extends WebView implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzbgj {
    private int maxHeight = -1;
    private int maxWidth = -1;
    private String zzacy;
    private final zzbbx zzbpe;
    private final WindowManager zzbrj;
    private int zzdoc = -1;
    private int zzdod = -1;
    private boolean zzdpo;
    private String zzdrb = "";
    private Boolean zzdzd;
    private zzabq zzeja;
    private final zzts zzemr;
    private final zzbhz zzeol;
    private final zzeg zzeom;
    private final zzacb zzeon;
    private final zzk zzeoo;
    private final zzb zzeop;
    private final float zzeoq;
    private final zzsu zzeor;
    private final boolean zzeos;
    private zzdnv zzeot;
    private zzdnw zzeou;
    private boolean zzeov = false;
    private boolean zzeow = false;
    private zzbgi zzeox;
    private zze zzeoy;
    private IObjectWrapper zzeoz;
    private zzbhy zzepa;
    private boolean zzepb;
    private boolean zzepc;
    private boolean zzepd;
    private int zzepe;
    private boolean zzepf = true;
    private boolean zzepg = false;
    private zzbhd zzeph;
    private boolean zzepi;
    private boolean zzepj;
    private zzads zzepk;
    private zzadn zzepl;
    private zzsc zzepm;
    private int zzepn;
    /* access modifiers changed from: private */
    public int zzepo;
    private zzabq zzepp;
    private zzabq zzepq;
    private zzabp zzepr;
    private WeakReference<View.OnClickListener> zzeps;
    private zze zzept;
    private boolean zzepu;
    private zzbbh zzepv;
    private Map<String, zzbfl> zzepw;
    private final DisplayMetrics zzxd;

    static zzbgz zzb(Context context, zzbhy zzbhy, String str, boolean z, boolean z2, zzeg zzeg, zzacb zzacb, zzbbx zzbbx, zzabs zzabs, zzk zzk, zzb zzb, zzts zzts, zzsu zzsu, boolean z3, zzdnv zzdnv, zzdnw zzdnw) {
        zzbhz zzbhz = r0;
        zzbhz zzbhz2 = new zzbhz(context);
        return new zzbgz(zzbhz, zzbhy, str, z, z2, zzeg, zzacb, zzbbx, zzabs, zzk, zzb, zzts, zzsu, z3, zzdnv, zzdnw);
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this;
    }

    public final boolean zzacl() {
        return false;
    }

    public final zzbdn zzzr() {
        return null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzbgz(zzbhz zzbhz, zzbhy zzbhy, String str, boolean z, boolean z2, zzeg zzeg, zzacb zzacb, zzbbx zzbbx, zzabs zzabs, zzk zzk, zzb zzb, zzts zzts, zzsu zzsu, boolean z3, zzdnv zzdnv, zzdnw zzdnw) {
        super(zzbhz);
        zzbbx zzbbx2 = zzbbx;
        this.zzeol = zzbhz;
        this.zzepa = zzbhy;
        this.zzacy = str;
        this.zzepc = z;
        this.zzepe = -1;
        this.zzeom = zzeg;
        this.zzeon = zzacb;
        this.zzbpe = zzbbx2;
        this.zzeoo = zzk;
        this.zzeop = zzb;
        this.zzbrj = (WindowManager) getContext().getSystemService("window");
        zzp.zzkr();
        DisplayMetrics zza = zzayu.zza(this.zzbrj);
        this.zzxd = zza;
        this.zzeoq = zza.density;
        this.zzemr = zzts;
        this.zzeor = zzsu;
        this.zzeos = z3;
        this.zzeot = zzdnv;
        this.zzeou = zzdnw;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            zzayp.zzc("Unable to enable Javascript.", e);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzp.zzkr().zza((Context) zzbhz, zzbbx2.zzbre, settings);
        zzp.zzkt().zza(getContext(), settings);
        setDownloadListener(this);
        zzacr();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzbhe.zzc(this), "googleAdsJsInterface");
        }
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        this.zzepv = new zzbbh(this.zzeol.zzzu(), this, this, (ViewTreeObserver.OnScrollChangedListener) null);
        zzacv();
        zzabp zzabp = new zzabp(new zzabs(true, "make_wv", this.zzacy));
        this.zzepr = zzabp;
        zzabp.zzrp().zzc(zzabs);
        zzabq zzb2 = zzabj.zzb(this.zzepr.zzrp());
        this.zzeja = zzb2;
        this.zzepr.zza("native:view_create", zzb2);
        this.zzepq = null;
        this.zzepp = null;
        zzp.zzkt().zzbg(zzbhz);
        zzp.zzkv().zzwq();
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzbgi) {
            this.zzeox = (zzbgi) webViewClient;
        }
    }

    public final zzb zzzv() {
        return this.zzeop;
    }

    private final boolean zzaco() {
        int i;
        int i2;
        boolean z = false;
        if (!this.zzeox.zzabc() && !this.zzeox.zzabd()) {
            return false;
        }
        zzwm.zzpt();
        DisplayMetrics displayMetrics = this.zzxd;
        int zzb = zzbbg.zzb(displayMetrics, displayMetrics.widthPixels);
        zzwm.zzpt();
        DisplayMetrics displayMetrics2 = this.zzxd;
        int zzb2 = zzbbg.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity zzzu = this.zzeol.zzzu();
        if (zzzu == null || zzzu.getWindow() == null) {
            i2 = zzb;
            i = zzb2;
        } else {
            zzp.zzkr();
            int[] zzd = zzayu.zzd(zzzu);
            zzwm.zzpt();
            int zzb3 = zzbbg.zzb(this.zzxd, zzd[0]);
            zzwm.zzpt();
            i = zzbbg.zzb(this.zzxd, zzd[1]);
            i2 = zzb3;
        }
        if (this.zzdoc == zzb && this.zzdod == zzb2 && this.maxWidth == i2 && this.maxHeight == i) {
            return false;
        }
        if (!(this.zzdoc == zzb && this.zzdod == zzb2)) {
            z = true;
        }
        this.zzdoc = zzb;
        this.zzdod = zzb2;
        this.maxWidth = i2;
        this.maxHeight = i;
        new zzaqn(this).zza(zzb, zzb2, i2, i, this.zzxd.density, this.zzbrj.getDefaultDisplay().getRotation());
        return z;
    }

    public final void zza(String str, Map<String, ?> map) {
        try {
            zza(str, zzp.zzkr().zzj(map));
        } catch (JSONException unused) {
            zzayp.zzfe("Could not convert parameters to JSON.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void evaluateJavascript(java.lang.String r2, android.webkit.ValueCallback<java.lang.String> r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isDestroyed()     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0014
            java.lang.String r2 = "#004 The webview is destroyed. Ignoring action."
            com.google.android.gms.internal.ads.zzayp.zzfg(r2)     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0012
            r2 = 0
            r3.onReceiveValue(r2)     // Catch:{ all -> 0x0019 }
        L_0x0012:
            monitor-exit(r1)
            return
        L_0x0014:
            super.evaluateJavascript(r2, r3)     // Catch:{ all -> 0x0019 }
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbgz.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    private final synchronized void zzfr(String str) {
        if (!isDestroyed()) {
            loadUrl(str);
        } else {
            zzayp.zzfe("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadUrl(String str) {
        if (!isDestroyed()) {
            try {
                super.loadUrl(str);
            } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e) {
                zzp.zzkv().zza(e, "AdWebViewImpl.loadUrl");
                zzayp.zzd("Could not call loadUrl. ", e);
            }
        } else {
            zzayp.zzfe("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final synchronized void zzfs(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError | UnsatisfiedLinkError e) {
            zzp.zzkv().zza(e, "AdWebViewImpl.loadUrlUnsafe");
            zzayp.zzd("Could not call loadUrl. ", e);
        }
    }

    public final synchronized void loadData(String str, String str2, String str3) {
        if (!isDestroyed()) {
            super.loadData(str, str2, str3);
        } else {
            zzayp.zzfe("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!isDestroyed()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            zzayp.zzfe("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void zzb(String str, String str2, String str3) {
        if (!isDestroyed()) {
            String str4 = str;
            super.loadDataWithBaseURL(str4, zzbho.zzf(str2, zzbho.zzacx()), "text/html", "UTF-8", str3);
            return;
        }
        zzayp.zzfe("#004 The webview is destroyed. Ignoring action.");
    }

    private final synchronized void zza(String str, ValueCallback<String> valueCallback) {
        if (!isDestroyed()) {
            evaluateJavascript(str, (ValueCallback<String>) null);
        } else {
            zzayp.zzfe("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final void zzft(String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            if (zzwo() == null) {
                zzacp();
            }
            if (zzwo().booleanValue()) {
                zza(str, (ValueCallback<String>) null);
                return;
            }
            String valueOf = String.valueOf(str);
            zzfr(valueOf.length() != 0 ? InAppMessageWebViewClient.JAVASCRIPT_PREFIX.concat(valueOf) : new String(InAppMessageWebViewClient.JAVASCRIPT_PREFIX));
            return;
        }
        String valueOf2 = String.valueOf(str);
        zzfr(valueOf2.length() != 0 ? InAppMessageWebViewClient.JAVASCRIPT_PREFIX.concat(valueOf2) : new String(InAppMessageWebViewClient.JAVASCRIPT_PREFIX));
    }

    public final void zzdc(String str) {
        zzft(str);
    }

    private final synchronized void zzacp() {
        Boolean zzwo = zzp.zzkv().zzwo();
        this.zzdzd = zzwo;
        if (zzwo == null) {
            try {
                evaluateJavascript("(function(){})()", (ValueCallback<String>) null);
                zza((Boolean) true);
            } catch (IllegalStateException unused) {
                zza((Boolean) false);
            }
        }
    }

    private final void zza(Boolean bool) {
        synchronized (this) {
            this.zzdzd = bool;
        }
        zzp.zzkv().zza(bool);
    }

    private final synchronized Boolean zzwo() {
        return this.zzdzd;
    }

    public final void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(jSONObject2).length());
        sb.append(str);
        sb.append("(");
        sb.append(jSONObject2);
        sb.append(");");
        zzft(sb.toString());
    }

    public final void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String valueOf = String.valueOf(sb.toString());
        zzayp.zzef(valueOf.length() != 0 ? "Dispatching AFMA event: ".concat(valueOf) : new String("Dispatching AFMA event: "));
        zzft(sb.toString());
    }

    public final void zzabp() {
        zzacq();
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzbpe.zzbre);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public final void zzdu(int i) {
        if (i == 0) {
            zzabj.zza(this.zzepr.zzrp(), this.zzeja, "aebb2");
        }
        zzacq();
        if (this.zzepr.zzrp() != null) {
            this.zzepr.zzrp().zzh("close_type", String.valueOf(i));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzbpe.zzbre);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    private final void zzacq() {
        zzabj.zza(this.zzepr.zzrp(), this.zzeja, "aeh2");
    }

    public final void zzuy() {
        if (this.zzepp == null) {
            zzabj.zza(this.zzepr.zzrp(), this.zzeja, "aes2");
            zzabq zzb = zzabj.zzb(this.zzepr.zzrp());
            this.zzepp = zzb;
            this.zzepr.zza("native:view_show", zzb);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzbpe.zzbre);
        zza("onshow", (Map<String, ?>) hashMap);
    }

    public final void zzabq() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzp.zzkw().zzqh()));
        hashMap.put("app_volume", String.valueOf(zzp.zzkw().zzqg()));
        hashMap.put("device_volume", String.valueOf(zzazm.zzbi(getContext())));
        zza("volume", (Map<String, ?>) hashMap);
    }

    public final void zza(boolean z, long j) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("success", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap.put("duration", Long.toString(j));
        zza("onCacheAccessComplete", (Map<String, ?>) hashMap);
    }

    public final synchronized zze zzabs() {
        return this.zzeoy;
    }

    public final synchronized IObjectWrapper zzaca() {
        return this.zzeoz;
    }

    public final synchronized zze zzabt() {
        return this.zzept;
    }

    public final synchronized zzbhy zzabu() {
        return this.zzepa;
    }

    public final synchronized String zzabv() {
        return this.zzacy;
    }

    public final WebViewClient zzabx() {
        return this.zzeox;
    }

    public final synchronized boolean zzaby() {
        return this.zzdpo;
    }

    public final zzeg zzabz() {
        return this.zzeom;
    }

    public final zzbbx zzzx() {
        return this.zzbpe;
    }

    public final synchronized boolean zzacb() {
        return this.zzepc;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzp.zzkr();
            zzayu.zza(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(str4).length());
            sb.append("Couldn't find an Activity to view url/mimetype: ");
            sb.append(str);
            sb.append(" / ");
            sb.append(str4);
            zzayp.zzef(sb.toString());
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.zzeox.zzabd() || this.zzeox.zzabe()) {
            zzeg zzeg = this.zzeom;
            if (zzeg != null) {
                zzeg.zza(motionEvent);
            }
            zzacb zzacb = this.zzeon;
            if (zzacb != null) {
                zzacb.zza(motionEvent);
            }
        } else {
            synchronized (this) {
                if (this.zzepk != null) {
                    this.zzepk.zzc(motionEvent);
                }
            }
        }
        if (isDestroyed()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float axisValue = motionEvent.getAxisValue(9);
        float axisValue2 = motionEvent.getAxisValue(10);
        if (motionEvent.getActionMasked() == 8) {
            if (axisValue > 0.0f && !canScrollVertically(-1)) {
                return false;
            }
            if (axisValue < 0.0f && !canScrollVertically(1)) {
                return false;
            }
            if (axisValue2 > 0.0f && !canScrollHorizontally(-1)) {
                return false;
            }
            if (axisValue2 < 0.0f && !canScrollHorizontally(1)) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01f8, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x01d6 A[SYNTHETIC, Splitter:B:111:0x01d6] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0141  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:121:0x01f9=Splitter:B:121:0x01f9, B:64:0x00de=Splitter:B:64:0x00de} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.isDestroyed()     // Catch:{ all -> 0x01fe }
            r1 = 0
            if (r0 == 0) goto L_0x000d
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x000d:
            boolean r0 = r7.isInEditMode()     // Catch:{ all -> 0x01fe }
            if (r0 != 0) goto L_0x01f9
            boolean r0 = r7.zzepc     // Catch:{ all -> 0x01fe }
            if (r0 != 0) goto L_0x01f9
            com.google.android.gms.internal.ads.zzbhy r0 = r7.zzepa     // Catch:{ all -> 0x01fe }
            boolean r0 = r0.zzadc()     // Catch:{ all -> 0x01fe }
            if (r0 == 0) goto L_0x0021
            goto L_0x01f9
        L_0x0021:
            com.google.android.gms.internal.ads.zzbhy r0 = r7.zzepa     // Catch:{ all -> 0x01fe }
            boolean r0 = r0.zzade()     // Catch:{ all -> 0x01fe }
            if (r0 == 0) goto L_0x002e
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x002e:
            com.google.android.gms.internal.ads.zzbhy r0 = r7.zzepa     // Catch:{ all -> 0x01fe }
            boolean r0 = r0.zzadd()     // Catch:{ all -> 0x01fe }
            if (r0 == 0) goto L_0x0091
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcsg     // Catch:{ all -> 0x01fe }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x01fe }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x01fe }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01fe }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01fe }
            if (r0 == 0) goto L_0x004d
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x004d:
            com.google.android.gms.internal.ads.zzbhd r0 = r7.zzzs()     // Catch:{ all -> 0x01fe }
            r1 = 0
            if (r0 == 0) goto L_0x0059
            float r0 = r0.getAspectRatio()     // Catch:{ all -> 0x01fe }
            goto L_0x005a
        L_0x0059:
            r0 = 0
        L_0x005a:
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0063
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x0063:
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01fe }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01fe }
            float r1 = (float) r9     // Catch:{ all -> 0x01fe }
            float r1 = r1 * r0
            int r1 = (int) r1     // Catch:{ all -> 0x01fe }
            float r2 = (float) r8     // Catch:{ all -> 0x01fe }
            float r2 = r2 / r0
            int r2 = (int) r2     // Catch:{ all -> 0x01fe }
            if (r9 != 0) goto L_0x007c
            if (r2 == 0) goto L_0x007c
            float r9 = (float) r2     // Catch:{ all -> 0x01fe }
            float r9 = r9 * r0
            int r1 = (int) r9     // Catch:{ all -> 0x01fe }
            r9 = r2
            goto L_0x0084
        L_0x007c:
            if (r8 != 0) goto L_0x0084
            if (r1 == 0) goto L_0x0084
            float r8 = (float) r1     // Catch:{ all -> 0x01fe }
            float r8 = r8 / r0
            int r2 = (int) r8     // Catch:{ all -> 0x01fe }
            r8 = r1
        L_0x0084:
            int r8 = java.lang.Math.min(r1, r8)     // Catch:{ all -> 0x01fe }
            int r9 = java.lang.Math.min(r2, r9)     // Catch:{ all -> 0x01fe }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x0091:
            com.google.android.gms.internal.ads.zzbhy r0 = r7.zzepa     // Catch:{ all -> 0x01fe }
            boolean r0 = r0.isFluid()     // Catch:{ all -> 0x01fe }
            if (r0 == 0) goto L_0x00e3
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcsj     // Catch:{ all -> 0x01fe }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x01fe }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x01fe }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01fe }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01fe }
            if (r0 != 0) goto L_0x00de
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR1()     // Catch:{ all -> 0x01fe }
            if (r0 != 0) goto L_0x00b2
            goto L_0x00de
        L_0x00b2:
            java.lang.String r0 = "/contentHeight"
            com.google.android.gms.internal.ads.zzbhb r1 = new com.google.android.gms.internal.ads.zzbhb     // Catch:{ all -> 0x01fe }
            r1.<init>(r7)     // Catch:{ all -> 0x01fe }
            r7.zza((java.lang.String) r0, (com.google.android.gms.internal.ads.zzahq<? super com.google.android.gms.internal.ads.zzbgj>) r1)     // Catch:{ all -> 0x01fe }
            java.lang.String r0 = "(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();"
            r7.zzft(r0)     // Catch:{ all -> 0x01fe }
            android.util.DisplayMetrics r0 = r7.zzxd     // Catch:{ all -> 0x01fe }
            float r0 = r0.density     // Catch:{ all -> 0x01fe }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01fe }
            int r1 = r7.zzepo     // Catch:{ all -> 0x01fe }
            r2 = -1
            if (r1 == r2) goto L_0x00d5
            int r9 = r7.zzepo     // Catch:{ all -> 0x01fe }
            float r9 = (float) r9     // Catch:{ all -> 0x01fe }
            float r9 = r9 * r0
            int r9 = (int) r9     // Catch:{ all -> 0x01fe }
            goto L_0x00d9
        L_0x00d5:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01fe }
        L_0x00d9:
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x00de:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x00e3:
            com.google.android.gms.internal.ads.zzbhy r0 = r7.zzepa     // Catch:{ all -> 0x01fe }
            boolean r0 = r0.zzadb()     // Catch:{ all -> 0x01fe }
            if (r0 == 0) goto L_0x00f8
            android.util.DisplayMetrics r8 = r7.zzxd     // Catch:{ all -> 0x01fe }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01fe }
            android.util.DisplayMetrics r9 = r7.zzxd     // Catch:{ all -> 0x01fe }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01fe }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x00f8:
            int r0 = android.view.View.MeasureSpec.getMode(r8)     // Catch:{ all -> 0x01fe }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01fe }
            int r2 = android.view.View.MeasureSpec.getMode(r9)     // Catch:{ all -> 0x01fe }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01fe }
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r4) goto L_0x0118
            if (r0 != r3) goto L_0x0114
            goto L_0x0118
        L_0x0114:
            r0 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0119
        L_0x0118:
            r0 = r8
        L_0x0119:
            if (r2 == r4) goto L_0x011d
            if (r2 != r3) goto L_0x011e
        L_0x011d:
            r5 = r9
        L_0x011e:
            com.google.android.gms.internal.ads.zzbhy r2 = r7.zzepa     // Catch:{ all -> 0x01fe }
            int r2 = r2.widthPixels     // Catch:{ all -> 0x01fe }
            r3 = 1
            if (r2 > r0) goto L_0x012e
            com.google.android.gms.internal.ads.zzbhy r2 = r7.zzepa     // Catch:{ all -> 0x01fe }
            int r2 = r2.heightPixels     // Catch:{ all -> 0x01fe }
            if (r2 <= r5) goto L_0x012c
            goto L_0x012e
        L_0x012c:
            r2 = 0
            goto L_0x012f
        L_0x012e:
            r2 = 1
        L_0x012f:
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.zzabb.zzcuu     // Catch:{ all -> 0x01fe }
            com.google.android.gms.internal.ads.zzaax r6 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x01fe }
            java.lang.Object r4 = r6.zzd(r4)     // Catch:{ all -> 0x01fe }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x01fe }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01fe }
            if (r4 == 0) goto L_0x0167
            com.google.android.gms.internal.ads.zzbhy r4 = r7.zzepa     // Catch:{ all -> 0x01fe }
            int r4 = r4.widthPixels     // Catch:{ all -> 0x01fe }
            float r4 = (float) r4     // Catch:{ all -> 0x01fe }
            float r6 = r7.zzeoq     // Catch:{ all -> 0x01fe }
            float r4 = r4 / r6
            float r0 = (float) r0     // Catch:{ all -> 0x01fe }
            float r6 = r7.zzeoq     // Catch:{ all -> 0x01fe }
            float r0 = r0 / r6
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0163
            com.google.android.gms.internal.ads.zzbhy r0 = r7.zzepa     // Catch:{ all -> 0x01fe }
            int r0 = r0.heightPixels     // Catch:{ all -> 0x01fe }
            float r0 = (float) r0     // Catch:{ all -> 0x01fe }
            float r4 = r7.zzeoq     // Catch:{ all -> 0x01fe }
            float r0 = r0 / r4
            float r4 = (float) r5     // Catch:{ all -> 0x01fe }
            float r5 = r7.zzeoq     // Catch:{ all -> 0x01fe }
            float r4 = r4 / r5
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x0163
            r0 = 1
            goto L_0x0164
        L_0x0163:
            r0 = 0
        L_0x0164:
            if (r2 == 0) goto L_0x0167
            r2 = r0
        L_0x0167:
            r0 = 8
            if (r2 == 0) goto L_0x01d6
            com.google.android.gms.internal.ads.zzbhy r2 = r7.zzepa     // Catch:{ all -> 0x01fe }
            int r2 = r2.widthPixels     // Catch:{ all -> 0x01fe }
            float r2 = (float) r2     // Catch:{ all -> 0x01fe }
            float r4 = r7.zzeoq     // Catch:{ all -> 0x01fe }
            float r2 = r2 / r4
            int r2 = (int) r2     // Catch:{ all -> 0x01fe }
            com.google.android.gms.internal.ads.zzbhy r4 = r7.zzepa     // Catch:{ all -> 0x01fe }
            int r4 = r4.heightPixels     // Catch:{ all -> 0x01fe }
            float r4 = (float) r4     // Catch:{ all -> 0x01fe }
            float r5 = r7.zzeoq     // Catch:{ all -> 0x01fe }
            float r4 = r4 / r5
            int r4 = (int) r4     // Catch:{ all -> 0x01fe }
            float r8 = (float) r8     // Catch:{ all -> 0x01fe }
            float r5 = r7.zzeoq     // Catch:{ all -> 0x01fe }
            float r8 = r8 / r5
            int r8 = (int) r8     // Catch:{ all -> 0x01fe }
            float r9 = (float) r9     // Catch:{ all -> 0x01fe }
            float r5 = r7.zzeoq     // Catch:{ all -> 0x01fe }
            float r9 = r9 / r5
            int r9 = (int) r9     // Catch:{ all -> 0x01fe }
            r5 = 103(0x67, float:1.44E-43)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fe }
            r6.<init>(r5)     // Catch:{ all -> 0x01fe }
            java.lang.String r5 = "Not enough space to show ad. Needs "
            r6.append(r5)     // Catch:{ all -> 0x01fe }
            r6.append(r2)     // Catch:{ all -> 0x01fe }
            java.lang.String r2 = "x"
            r6.append(r2)     // Catch:{ all -> 0x01fe }
            r6.append(r4)     // Catch:{ all -> 0x01fe }
            java.lang.String r2 = " dp, but only has "
            r6.append(r2)     // Catch:{ all -> 0x01fe }
            r6.append(r8)     // Catch:{ all -> 0x01fe }
            java.lang.String r8 = "x"
            r6.append(r8)     // Catch:{ all -> 0x01fe }
            r6.append(r9)     // Catch:{ all -> 0x01fe }
            java.lang.String r8 = " dp."
            r6.append(r8)     // Catch:{ all -> 0x01fe }
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x01fe }
            com.google.android.gms.internal.ads.zzayp.zzfe(r8)     // Catch:{ all -> 0x01fe }
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01fe }
            if (r8 == r0) goto L_0x01c4
            r8 = 4
            r7.setVisibility(r8)     // Catch:{ all -> 0x01fe }
        L_0x01c4:
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01fe }
            boolean r8 = r7.zzeov     // Catch:{ all -> 0x01fe }
            if (r8 != 0) goto L_0x01f7
            com.google.android.gms.internal.ads.zzts r8 = r7.zzemr     // Catch:{ all -> 0x01fe }
            com.google.android.gms.internal.ads.zztu$zza$zzb r9 = com.google.android.gms.internal.ads.zztu.zza.zzb.BANNER_SIZE_INVALID     // Catch:{ all -> 0x01fe }
            r8.zza((com.google.android.gms.internal.ads.zztu.zza.zzb) r9)     // Catch:{ all -> 0x01fe }
            r7.zzeov = r3     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x01d6:
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01fe }
            if (r8 == r0) goto L_0x01df
            r7.setVisibility(r1)     // Catch:{ all -> 0x01fe }
        L_0x01df:
            boolean r8 = r7.zzeow     // Catch:{ all -> 0x01fe }
            if (r8 != 0) goto L_0x01ec
            com.google.android.gms.internal.ads.zzts r8 = r7.zzemr     // Catch:{ all -> 0x01fe }
            com.google.android.gms.internal.ads.zztu$zza$zzb r9 = com.google.android.gms.internal.ads.zztu.zza.zzb.BANNER_SIZE_VALID     // Catch:{ all -> 0x01fe }
            r8.zza((com.google.android.gms.internal.ads.zztu.zza.zzb) r9)     // Catch:{ all -> 0x01fe }
            r7.zzeow = r3     // Catch:{ all -> 0x01fe }
        L_0x01ec:
            com.google.android.gms.internal.ads.zzbhy r8 = r7.zzepa     // Catch:{ all -> 0x01fe }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01fe }
            com.google.android.gms.internal.ads.zzbhy r9 = r7.zzepa     // Catch:{ all -> 0x01fe }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01fe }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01fe }
        L_0x01f7:
            monitor-exit(r7)
            return
        L_0x01f9:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01fe }
            monitor-exit(r7)
            return
        L_0x01fe:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbgz.onMeasure(int, int):void");
    }

    public final void onGlobalLayout() {
        boolean zzaco = zzaco();
        zze zzabs = zzabs();
        if (zzabs != null && zzaco) {
            zzabs.zzux();
        }
    }

    public final synchronized void zza(zze zze) {
        this.zzeoy = zze;
    }

    public final synchronized void zzap(IObjectWrapper iObjectWrapper) {
        this.zzeoz = iObjectWrapper;
    }

    public final synchronized void zzb(zze zze) {
        this.zzept = zze;
    }

    public final synchronized void zza(zzbhy zzbhy) {
        this.zzepa = zzbhy;
        requestLayout();
    }

    public final synchronized void zzba(boolean z) {
        boolean z2 = z != this.zzepc;
        this.zzepc = z;
        zzacr();
        if (z2) {
            if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcnb)).booleanValue() || !this.zzepa.zzadb()) {
                new zzaqn(this).zzdz(z ? "expanded" : "default");
            }
        }
    }

    public final void zzacf() {
        this.zzepv.zzyp();
    }

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzepv.onAttachedToWindow();
        }
        boolean z = this.zzepi;
        if (this.zzeox != null && this.zzeox.zzabd()) {
            if (!this.zzepj) {
                this.zzeox.zzabf();
                this.zzeox.zzabg();
                this.zzepj = true;
            }
            zzaco();
            z = true;
        }
        zzbd(z);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        synchronized (this) {
            if (!isDestroyed()) {
                this.zzepv.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzepj && this.zzeox != null && this.zzeox.zzabd() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                this.zzeox.zzabf();
                this.zzeox.zzabg();
                this.zzepj = false;
            }
        }
        zzbd(false);
    }

    public final void zzbw(Context context) {
        this.zzeol.setBaseContext(context);
        this.zzepv.zzh(this.zzeol.zzzu());
    }

    public final synchronized void zzal(boolean z) {
        if (this.zzeoy != null) {
            this.zzeoy.zza(this.zzeox.zzabc(), z);
        } else {
            this.zzdpo = z;
        }
    }

    public final synchronized void setRequestedOrientation(int i) {
        this.zzepe = i;
        if (this.zzeoy != null) {
            this.zzeoy.setRequestedOrientation(i);
        }
    }

    public final Activity zzzu() {
        return this.zzeol.zzzu();
    }

    public final Context zzabr() {
        return this.zzeol.zzabr();
    }

    private final synchronized void zzacr() {
        if (!this.zzepc) {
            if (!this.zzepa.zzadb()) {
                if (Build.VERSION.SDK_INT < 18) {
                    zzayp.zzef("Disabling hardware acceleration on an AdView.");
                    zzacs();
                    return;
                }
                zzayp.zzef("Enabling hardware acceleration on an AdView.");
                zzact();
                return;
            }
        }
        zzayp.zzef("Enabling hardware acceleration on an overlay.");
        zzact();
    }

    private final synchronized void zzacs() {
        if (!this.zzepd) {
            zzp.zzkt();
            setLayerType(1, (Paint) null);
        }
        this.zzepd = true;
    }

    private final synchronized void zzact() {
        if (this.zzepd) {
            zzp.zzkt();
            setLayerType(0, (Paint) null);
        }
        this.zzepd = false;
    }

    public final synchronized void destroy() {
        zzacv();
        this.zzepv.zzyq();
        if (this.zzeoy != null) {
            this.zzeoy.close();
            this.zzeoy.onDestroy();
            this.zzeoy = null;
        }
        this.zzeoz = null;
        this.zzeox.reset();
        if (!this.zzepb) {
            zzp.zzln();
            zzbfi.zzc(this);
            zzacu();
            this.zzepb = true;
            zzayp.zzei("Initiating WebView self destruct sequence in 3...");
            zzayp.zzei("Loading blank page in WebView, 2...");
            zzfs("about:blank");
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.zzepb) {
                    this.zzeox.reset();
                    zzp.zzln();
                    zzbfi.zzc(this);
                    zzacu();
                    zzwr();
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public final synchronized void zzacc() {
        zzayp.zzei("Destroying WebView!");
        zzwr();
        zzayu.zzeba.post(new zzbha(this));
    }

    private final synchronized void zzwr() {
        if (!this.zzepu) {
            this.zzepu = true;
            zzp.zzkv().zzwr();
        }
    }

    public final synchronized boolean isDestroyed() {
        return this.zzepb;
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (Build.VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                zzbgi zzbgi = this.zzeox;
            }
        }
    }

    public final void zzacg() {
        if (this.zzepq == null) {
            zzabq zzb = zzabj.zzb(this.zzepr.zzrp());
            this.zzepq = zzb;
            this.zzepr.zza("native:view_load", zzb);
        }
    }

    public final void onPause() {
        if (!isDestroyed()) {
            try {
                super.onPause();
            } catch (Exception e) {
                zzayp.zzc("Could not pause webview.", e);
            }
        }
    }

    public final void onResume() {
        if (!isDestroyed()) {
            try {
                super.onResume();
            } catch (Exception e) {
                zzayp.zzc("Could not resume webview.", e);
            }
        }
    }

    public final void zzacj() {
        zzayp.zzei("Cannot add text view to inner AdWebView");
    }

    public final void zzax(boolean z) {
        this.zzeox.zzax(z);
    }

    public final void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Exception e) {
                zzayp.zzc("Could not stop loading webview.", e);
            }
        }
    }

    public final synchronized void zzbb(boolean z) {
        this.zzepf = z;
    }

    public final synchronized boolean zzacd() {
        return this.zzepf;
    }

    public final synchronized void zzkn() {
        this.zzepg = true;
        if (this.zzeoo != null) {
            this.zzeoo.zzkn();
        }
    }

    public final synchronized void zzko() {
        this.zzepg = false;
        if (this.zzeoo != null) {
            this.zzeoo.zzko();
        }
    }

    private final synchronized void zzacu() {
        if (this.zzepw != null) {
            for (zzbfl release : this.zzepw.values()) {
                release.release();
            }
        }
        this.zzepw = null;
    }

    public final synchronized void zza(String str, zzbfl zzbfl) {
        if (this.zzepw == null) {
            this.zzepw = new HashMap();
        }
        this.zzepw.put(str, zzbfl);
    }

    public final synchronized zzbfl zzfj(String str) {
        if (this.zzepw == null) {
            return null;
        }
        return this.zzepw.get(str);
    }

    public final synchronized String getRequestId() {
        return this.zzdrb;
    }

    public final synchronized void zzaaa() {
        if (this.zzepl != null) {
            this.zzepl.zzsf();
        }
    }

    public final synchronized void zza(zzadn zzadn) {
        this.zzepl = zzadn;
    }

    public final synchronized void zza(zzsc zzsc) {
        this.zzepm = zzsc;
    }

    public final synchronized zzsc zzack() {
        return this.zzepm;
    }

    public final zzabq zzzt() {
        return this.zzeja;
    }

    public final zzabp zzzw() {
        return this.zzepr;
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzeps = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public final synchronized void zza(zzads zzads) {
        this.zzepk = zzads;
    }

    public final synchronized zzads zzach() {
        return this.zzepk;
    }

    public final synchronized zzbhd zzzs() {
        return this.zzeph;
    }

    public final synchronized void zza(zzbhd zzbhd) {
        if (this.zzeph != null) {
            zzayp.zzfc("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzeph = zzbhd;
        }
    }

    public final synchronized boolean zzace() {
        return this.zzepn > 0;
    }

    public final synchronized void zzbc(boolean z) {
        int i = this.zzepn + (z ? 1 : -1);
        this.zzepn = i;
        if (i <= 0 && this.zzeoy != null) {
            this.zzeoy.zzva();
        }
    }

    private final void zzacv() {
        zzabs zzrp;
        zzabp zzabp = this.zzepr;
        if (zzabp != null && (zzrp = zzabp.zzrp()) != null && zzp.zzkv().zzwn() != null) {
            zzp.zzkv().zzwn().zza(zzrp);
        }
    }

    public final void zzaci() {
        setBackgroundColor(0);
    }

    public final void zzav(boolean z) {
        this.zzeox.zzav(z);
    }

    public final void zzuz() {
        zze zzabs = zzabs();
        if (zzabs != null) {
            zzabs.zzuz();
        }
    }

    public final int zzzy() {
        return getMeasuredHeight();
    }

    public final int zzzz() {
        return getMeasuredWidth();
    }

    public final void zza(com.google.android.gms.ads.internal.overlay.zzb zzb) {
        this.zzeox.zza(zzb);
    }

    public final void zzb(boolean z, int i) {
        this.zzeox.zzb(z, i);
    }

    public final void zza(boolean z, int i, String str) {
        this.zzeox.zza(z, i, str);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        this.zzeox.zza(z, i, str, str2);
    }

    public final void zza(zzqr zzqr) {
        synchronized (this) {
            this.zzepi = zzqr.zzbro;
        }
        zzbd(zzqr.zzbro);
    }

    private final void zzbd(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        zza("onAdVisibilityChanged", (Map<String, ?>) hashMap);
    }

    public final void zza(String str, zzahq<? super zzbgj> zzahq) {
        zzbgi zzbgi = this.zzeox;
        if (zzbgi != null) {
            zzbgi.zza(str, zzahq);
        }
    }

    public final void zzb(String str, zzahq<? super zzbgj> zzahq) {
        zzbgi zzbgi = this.zzeox;
        if (zzbgi != null) {
            zzbgi.zzb(str, zzahq);
        }
    }

    public final void zza(String str, Predicate<zzahq<? super zzbgj>> predicate) {
        zzbgi zzbgi = this.zzeox;
        if (zzbgi != null) {
            zzbgi.zza(str, predicate);
        }
    }

    public final boolean zzc(boolean z, int i) {
        destroy();
        this.zzemr.zza((zztr) new zzbgy(z, i));
        this.zzemr.zza(zztu.zza.zzb.ANDROID_WEBVIEW_CRASH);
        return true;
    }

    public final void zza(ViewGroup viewGroup, Activity activity, String str, String str2) {
        if (!zzacn()) {
            zzayp.zzei("AR ad is not enabled or the ad from the server is not an AR ad.");
            return;
        }
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }
        zzayp.zzei("Initializing ArWebView object.");
        this.zzeor.zza(activity, this);
        this.zzeor.zzc(str, str2);
        if (viewGroup != null) {
            viewGroup.addView(this.zzeor.getView());
        } else {
            zzayp.zzfc("The FrameLayout object cannot be null.");
        }
    }

    public final zzsu zzacm() {
        return this.zzeor;
    }

    public final boolean zzacn() {
        return ((Boolean) zzwm.zzpx().zzd(zzabb.zzcwp)).booleanValue() && this.zzeor != null && this.zzeos;
    }

    public final void zza(zzdnv zzdnv, zzdnw zzdnw) {
        this.zzeot = zzdnv;
        this.zzeou = zzdnw;
    }

    public final /* synthetic */ zzbhv zzabw() {
        return this.zzeox;
    }

    static final /* synthetic */ void zza(boolean z, int i, zzua.zzi.zza zza) {
        zzua.zzae.zza zzpf = zzua.zzae.zzpf();
        if (zzpf.zzpe() != z) {
            zzpf.zzx(z);
        }
        zza.zza((zzua.zzae) ((zzejz) zzpf.zzcy(i).zzbgt()));
    }
}
