package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.internal.ads.zztu;
import com.google.common.net.HttpHeaders;
import com.tapstream.sdk.http.RequestBuilders;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbgi extends WebViewClient implements zzbhv {
    private final Object lock;
    private boolean zzbrf;
    private zzux zzcgv;
    private zzagt zzdep;
    private zzagv zzdeq;
    private zza zzdfm;
    private zzaqd zzdfn;
    private zzp zzdpm;
    private zzv zzdpq;
    private boolean zzdtp;
    protected zzbgj zzemq;
    private final zzts zzemr;
    private final HashMap<String, List<zzahq<? super zzbgj>>> zzems;
    private zzbhu zzemt;
    private zzbhx zzemu;
    private zzbhw zzemv;
    private boolean zzemw;
    private boolean zzemx;
    private boolean zzemy;
    private boolean zzemz;
    private final zzaqo zzena;
    protected zzawl zzenb;
    private boolean zzenc;
    private boolean zzend;
    private int zzene;
    private View.OnAttachStateChangeListener zzenf;

    public zzbgi(zzbgj zzbgj, zzts zzts, boolean z) {
        this(zzbgj, zzts, z, new zzaqo(zzbgj, zzbgj.zzabr(), new zzaam(zzbgj.getContext())), (zzaqd) null);
    }

    private zzbgi(zzbgj zzbgj, zzts zzts, boolean z, zzaqo zzaqo, zzaqd zzaqd) {
        this.zzems = new HashMap<>();
        this.lock = new Object();
        this.zzemw = false;
        this.zzemr = zzts;
        this.zzemq = zzbgj;
        this.zzbrf = z;
        this.zzena = zzaqo;
        this.zzdfn = null;
    }

    public final void zza(int i, int i2, boolean z) {
        this.zzena.zzj(i, i2);
        zzaqd zzaqd = this.zzdfn;
        if (zzaqd != null) {
            zzaqd.zza(i, i2, false);
        }
    }

    public final void zza(zzux zzux, zzagt zzagt, zzp zzp, zzagv zzagv, zzv zzv, boolean z, zzahp zzahp, zza zza, zzaqq zzaqq, zzawl zzawl) {
        if (zza == null) {
            zza = new zza(this.zzemq.getContext(), zzawl, (zzasr) null);
        }
        this.zzdfn = new zzaqd(this.zzemq, zzaqq);
        this.zzenb = zzawl;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcom)).booleanValue()) {
            zza("/adMetadata", (zzahq<? super zzbgj>) new zzagu(zzagt));
        }
        zza("/appEvent", (zzahq<? super zzbgj>) new zzagw(zzagv));
        zza("/backButton", (zzahq<? super zzbgj>) zzagx.zzdfb);
        zza("/refresh", (zzahq<? super zzbgj>) zzagx.zzdfc);
        zza("/canOpenApp", (zzahq<? super zzbgj>) zzagx.zzdes);
        zza("/canOpenURLs", (zzahq<? super zzbgj>) zzagx.zzder);
        zza("/canOpenIntents", (zzahq<? super zzbgj>) zzagx.zzdet);
        zza("/click", (zzahq<? super zzbgj>) zzagx.zzdeu);
        zza("/close", (zzahq<? super zzbgj>) zzagx.zzdev);
        zza("/customClose", (zzahq<? super zzbgj>) zzagx.zzdew);
        zza("/instrument", (zzahq<? super zzbgj>) zzagx.zzdff);
        zza("/delayPageLoaded", (zzahq<? super zzbgj>) zzagx.zzdfh);
        zza("/delayPageClosed", (zzahq<? super zzbgj>) zzagx.zzdfi);
        zza("/getLocationInfo", (zzahq<? super zzbgj>) zzagx.zzdfj);
        zza("/httpTrack", (zzahq<? super zzbgj>) zzagx.zzdex);
        zza("/log", (zzahq<? super zzbgj>) zzagx.zzdey);
        zza("/mraid", (zzahq<? super zzbgj>) new zzahr(zza, this.zzdfn, zzaqq));
        zza("/mraidLoaded", (zzahq<? super zzbgj>) this.zzena);
        zza("/open", (zzahq<? super zzbgj>) new zzahu(zza, this.zzdfn));
        zza("/precache", (zzahq<? super zzbgj>) new zzbfq());
        zza("/touch", (zzahq<? super zzbgj>) zzagx.zzdfa);
        zza("/video", (zzahq<? super zzbgj>) zzagx.zzdfd);
        zza("/videoMeta", (zzahq<? super zzbgj>) zzagx.zzdfe);
        if (com.google.android.gms.ads.internal.zzp.zzlp().zzac(this.zzemq.getContext())) {
            zza("/logScionEvent", (zzahq<? super zzbgj>) new zzahs(this.zzemq.getContext()));
        }
        this.zzcgv = zzux;
        this.zzdpm = zzp;
        this.zzdep = zzagt;
        this.zzdeq = zzagv;
        this.zzdpq = zzv;
        this.zzdfm = zza;
        this.zzemw = z;
    }

    public final zza zzabb() {
        return this.zzdfm;
    }

    public final boolean zzabc() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzbrf;
        }
        return z;
    }

    public final boolean zzabd() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzemx;
        }
        return z;
    }

    public final boolean zzabe() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzemy;
        }
        return z;
    }

    public final ViewTreeObserver.OnGlobalLayoutListener zzabf() {
        synchronized (this.lock) {
        }
        return null;
    }

    public final ViewTreeObserver.OnScrollChangedListener zzabg() {
        synchronized (this.lock) {
        }
        return null;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        zzsu zzacm = this.zzemq.zzacm();
        if (zzacm != null && webView == zzacm.getWebView()) {
            zzacm.onPageStarted(webView, str, bitmap);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r1 == null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        r1.zztj();
        r0.zzemu = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        zzabm();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r0.zzenc = true;
        r1 = r0.zzemu;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onPageFinished(android.webkit.WebView r1, java.lang.String r2) {
        /*
            r0 = this;
            java.lang.Object r1 = r0.lock
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzbgj r2 = r0.zzemq     // Catch:{ all -> 0x0029 }
            boolean r2 = r2.isDestroyed()     // Catch:{ all -> 0x0029 }
            if (r2 == 0) goto L_0x0017
            java.lang.String r2 = "Blank page loaded, 1..."
            com.google.android.gms.internal.ads.zzayp.zzei(r2)     // Catch:{ all -> 0x0029 }
            com.google.android.gms.internal.ads.zzbgj r2 = r0.zzemq     // Catch:{ all -> 0x0029 }
            r2.zzacc()     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            return
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            r1 = 1
            r0.zzenc = r1
            com.google.android.gms.internal.ads.zzbhx r1 = r0.zzemu
            if (r1 == 0) goto L_0x0025
            r1.zztj()
            r1 = 0
            r0.zzemu = r1
        L_0x0025:
            r0.zzabm()
            return
        L_0x0029:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbgi.onPageFinished(android.webkit.WebView, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public final void zza(View view, zzawl zzawl, int i) {
        if (zzawl.zzvr() && i > 0) {
            zzawl.zzl(view);
            if (zzawl.zzvr()) {
                zzayu.zzeba.postDelayed(new zzbgn(this, view, zzawl, i), 100);
            }
        }
    }

    private final void zzabh() {
        if (this.zzenf != null) {
            this.zzemq.getView().removeOnAttachStateChangeListener(this.zzenf);
        }
    }

    public final void zzabi() {
        zzawl zzawl = this.zzenb;
        if (zzawl != null) {
            WebView webView = this.zzemq.getWebView();
            if (ViewCompat.isAttachedToWindow(webView)) {
                zza((View) webView, zzawl, 10);
                return;
            }
            zzabh();
            this.zzenf = new zzbgm(this, zzawl);
            this.zzemq.getView().addOnAttachStateChangeListener(this.zzenf);
        }
    }

    public final void zzabj() {
        synchronized (this.lock) {
            this.zzemz = true;
        }
        this.zzene++;
        zzabm();
    }

    public final void zzabk() {
        this.zzene--;
        zzabm();
    }

    public final void zzabl() {
        zzts zzts = this.zzemr;
        if (zzts != null) {
            zzts.zza(zztu.zza.zzb.DELAY_PAGE_LOAD_CANCELLED_AD);
        }
        this.zzend = true;
        zzabm();
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvj)).booleanValue()) {
            this.zzemq.destroy();
        }
    }

    private final void zzabm() {
        if (this.zzemt != null && ((this.zzenc && this.zzene <= 0) || this.zzend)) {
            this.zzemt.zzai(!this.zzend);
            this.zzemt = null;
        }
        this.zzemq.zzacg();
    }

    public final void zza(zzb zzb) {
        zzp zzp;
        boolean zzacb = this.zzemq.zzacb();
        zzux zzux = (!zzacb || this.zzemq.zzabu().zzadb()) ? this.zzcgv : null;
        if (zzacb) {
            zzp = null;
        } else {
            zzp = this.zzdpm;
        }
        zza(new AdOverlayInfoParcel(zzb, zzux, zzp, this.zzdpq, this.zzemq.zzzx()));
    }

    public final void zzb(boolean z, int i) {
        zzux zzux = (!this.zzemq.zzacb() || this.zzemq.zzabu().zzadb()) ? this.zzcgv : null;
        zzp zzp = this.zzdpm;
        zzv zzv = this.zzdpq;
        zzbgj zzbgj = this.zzemq;
        zza(new AdOverlayInfoParcel(zzux, zzp, zzv, zzbgj, z, i, zzbgj.zzzx()));
    }

    public final void zza(boolean z, int i, String str) {
        zzbgo zzbgo;
        boolean zzacb = this.zzemq.zzacb();
        zzux zzux = (!zzacb || this.zzemq.zzabu().zzadb()) ? this.zzcgv : null;
        if (zzacb) {
            zzbgo = null;
        } else {
            zzbgo = new zzbgo(this.zzemq, this.zzdpm);
        }
        zzagt zzagt = this.zzdep;
        zzagv zzagv = this.zzdeq;
        zzv zzv = this.zzdpq;
        zzbgj zzbgj = this.zzemq;
        zza(new AdOverlayInfoParcel(zzux, (zzp) zzbgo, zzagt, zzagv, zzv, zzbgj, z, i, str, zzbgj.zzzx()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        zzbgo zzbgo;
        boolean zzacb = this.zzemq.zzacb();
        zzux zzux = (!zzacb || this.zzemq.zzabu().zzadb()) ? this.zzcgv : null;
        if (zzacb) {
            zzbgo = null;
        } else {
            zzbgo = new zzbgo(this.zzemq, this.zzdpm);
        }
        zzagt zzagt = this.zzdep;
        zzagv zzagv = this.zzdeq;
        zzv zzv = this.zzdpq;
        zzbgj zzbgj = this.zzemq;
        zza(new AdOverlayInfoParcel(zzux, zzbgo, zzagt, zzagv, zzv, zzbgj, z, i, str, str2, zzbgj.zzzx()));
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        zzaqd zzaqd = this.zzdfn;
        boolean zzuo = zzaqd != null ? zzaqd.zzuo() : false;
        com.google.android.gms.ads.internal.zzp.zzkq();
        zzl.zza(this.zzemq.getContext(), adOverlayInfoParcel, !zzuo);
        if (this.zzenb != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzdpl != null) {
                str = adOverlayInfoParcel.zzdpl.url;
            }
            this.zzenb.zzea(str);
        }
    }

    public final void zza(String str, zzahq<? super zzbgj> zzahq) {
        synchronized (this.lock) {
            List list = this.zzems.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzems.put(str, list);
            }
            list.add(zzahq);
        }
    }

    public final void zzb(String str, zzahq<? super zzbgj> zzahq) {
        synchronized (this.lock) {
            List list = this.zzems.get(str);
            if (list != null) {
                list.remove(zzahq);
            }
        }
    }

    public final void zza(String str, Predicate<zzahq<? super zzbgj>> predicate) {
        synchronized (this.lock) {
            List<zzahq> list = this.zzems.get(str);
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (zzahq zzahq : list) {
                    if (predicate.apply(zzahq)) {
                        arrayList.add(zzahq);
                    }
                }
                list.removeAll(arrayList);
            }
        }
    }

    public final void reset() {
        zzawl zzawl = this.zzenb;
        if (zzawl != null) {
            zzawl.zzvt();
            this.zzenb = null;
        }
        zzabh();
        synchronized (this.lock) {
            this.zzems.clear();
            this.zzcgv = null;
            this.zzdpm = null;
            this.zzemt = null;
            this.zzemu = null;
            this.zzdep = null;
            this.zzdeq = null;
            this.zzemw = false;
            this.zzbrf = false;
            this.zzemx = false;
            this.zzemz = false;
            this.zzdpq = null;
            this.zzemv = null;
            if (this.zzdfn != null) {
                this.zzdfn.zzac(true);
                this.zzdfn = null;
            }
        }
    }

    public final void zza(zzbhu zzbhu) {
        this.zzemt = zzbhu;
    }

    public final void zza(zzbhx zzbhx) {
        this.zzemu = zzbhx;
    }

    public final void onLoadResource(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzayp.zzei(valueOf.length() != 0 ? "Loading resource: ".concat(valueOf) : new String("Loading resource: "));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzh(parse);
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzayp.zzei(valueOf.length() != 0 ? "AdWebView shouldOverrideUrlLoading: ".concat(valueOf) : new String("AdWebView shouldOverrideUrlLoading: "));
        Uri parse = Uri.parse(str);
        if (!"gmsg".equalsIgnoreCase(parse.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            if (this.zzemw && webView == this.zzemq.getWebView()) {
                String scheme = parse.getScheme();
                if ("http".equalsIgnoreCase(scheme) || RequestBuilders.DEFAULT_SCHEME.equalsIgnoreCase(scheme)) {
                    zzux zzux = this.zzcgv;
                    if (zzux != null) {
                        zzux.onAdClicked();
                        zzawl zzawl = this.zzenb;
                        if (zzawl != null) {
                            zzawl.zzea(str);
                        }
                        this.zzcgv = null;
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            }
            if (!this.zzemq.getWebView().willNotDraw()) {
                try {
                    zzeg zzabz = this.zzemq.zzabz();
                    if (zzabz != null && zzabz.zzb(parse)) {
                        parse = zzabz.zza(parse, this.zzemq.getContext(), this.zzemq.getView(), this.zzemq.zzzu());
                    }
                } catch (zzef unused) {
                    String valueOf2 = String.valueOf(str);
                    zzayp.zzfe(valueOf2.length() != 0 ? "Unable to append parameter to URL: ".concat(valueOf2) : new String("Unable to append parameter to URL: "));
                }
                zza zza = this.zzdfm;
                if (zza == null || zza.zzjz()) {
                    zza(new zzb("android.intent.action.VIEW", parse.toString(), (String) null, (String) null, (String) null, (String) null, (String) null));
                } else {
                    this.zzdfm.zzbo(str);
                }
            } else {
                String valueOf3 = String.valueOf(str);
                zzayp.zzfe(valueOf3.length() != 0 ? "AdWebView unable to handle URL: ".concat(valueOf3) : new String("AdWebView unable to handle URL: "));
            }
        } else {
            zzh(parse);
        }
        return true;
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zzd(str, Collections.emptyMap());
    }

    /* access modifiers changed from: protected */
    public final WebResourceResponse zzd(String str, Map<String, String> map) {
        zzsz zza;
        try {
            String zzc = zzaxh.zzc(str, this.zzemq.getContext(), this.zzdtp);
            if (!zzc.equals(str)) {
                return zze(zzc, map);
            }
            zzte zzbw = zzte.zzbw(str);
            if (zzbw != null && (zza = com.google.android.gms.ads.internal.zzp.zzkx().zza(zzbw)) != null && zza.zzmw()) {
                return new WebResourceResponse("", "", zza.zzmx());
            }
            if (!zzbbk.isEnabled() || !zzacr.zzdax.get().booleanValue()) {
                return null;
            }
            return zze(str, map);
        } catch (Exception | NoClassDefFoundError e) {
            com.google.android.gms.ads.internal.zzp.zzkv().zza(e, "AdWebViewClient.interceptRequest");
            return zzabn();
        }
    }

    private static WebResourceResponse zzabn() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcob)).booleanValue()) {
            return new WebResourceResponse("", "", new ByteArrayInputStream(new byte[0]));
        }
        return null;
    }

    private final WebResourceResponse zze(String str, Map<String, String> map) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        int i = 0;
        while (true) {
            i++;
            if (i <= 20) {
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(10000);
                openConnection.setReadTimeout(10000);
                for (Map.Entry next : map.entrySet()) {
                    openConnection.addRequestProperty((String) next.getKey(), (String) next.getValue());
                }
                if (openConnection instanceof HttpURLConnection) {
                    httpURLConnection = (HttpURLConnection) openConnection;
                    com.google.android.gms.ads.internal.zzp.zzkr().zza(this.zzemq.getContext(), this.zzemq.zzzx().zzbre, false, httpURLConnection);
                    zzbbk zzbbk = new zzbbk();
                    zzbbk.zza(httpURLConnection, (byte[]) null);
                    int responseCode = httpURLConnection.getResponseCode();
                    zzbbk.zza(httpURLConnection, responseCode);
                    if (responseCode < 300 || responseCode >= 400) {
                        com.google.android.gms.ads.internal.zzp.zzkr();
                    } else {
                        String headerField = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
                        if (headerField == null) {
                            throw new IOException("Missing Location header in redirect");
                        } else if (headerField.startsWith("tel:")) {
                            return null;
                        } else {
                            URL url2 = new URL(url, headerField);
                            String protocol = url2.getProtocol();
                            if (protocol == null) {
                                zzayp.zzfe("Protocol is null");
                                return zzabn();
                            } else if (protocol.equals("http") || protocol.equals(RequestBuilders.DEFAULT_SCHEME)) {
                                String valueOf = String.valueOf(headerField);
                                zzayp.zzef(valueOf.length() != 0 ? "Redirecting to ".concat(valueOf) : new String("Redirecting to "));
                                httpURLConnection.disconnect();
                                url = url2;
                            } else {
                                String valueOf2 = String.valueOf(protocol);
                                zzayp.zzfe(valueOf2.length() != 0 ? "Unsupported scheme: ".concat(valueOf2) : new String("Unsupported scheme: "));
                                return zzabn();
                            }
                        }
                    }
                } else {
                    throw new IOException("Invalid protocol.");
                }
            } else {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Too many redirects (20)");
                throw new IOException(sb.toString());
            }
        }
        com.google.android.gms.ads.internal.zzp.zzkr();
        return zzayu.zzd(httpURLConnection);
    }

    public final void zzav(boolean z) {
        this.zzemw = z;
    }

    public final zzawl zzabo() {
        return this.zzenb;
    }

    public final void zzuu() {
        synchronized (this.lock) {
            this.zzemw = false;
            this.zzbrf = true;
            zzbbz.zzeet.execute(new zzbgl(this));
        }
    }

    public final void zzax(boolean z) {
        this.zzdtp = z;
    }

    public final void zzi(int i, int i2) {
        zzaqd zzaqd = this.zzdfn;
        if (zzaqd != null) {
            zzaqd.zzi(i, i2);
        }
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 79 || keyCode == 222) {
            return true;
        }
        switch (keyCode) {
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
                return true;
            default:
                switch (keyCode) {
                    case 126:
                    case 127:
                    case 128:
                    case 129:
                    case 130:
                        return true;
                    default:
                        return false;
                }
        }
    }

    public final void zzh(Uri uri) {
        String path = uri.getPath();
        List list = this.zzems.get(path);
        if (list != null) {
            if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcvf)).booleanValue()) {
                com.google.android.gms.ads.internal.zzp.zzkr();
                zza(zzayu.zzj(uri), (List<zzahq<? super zzbgj>>) list, path);
                return;
            }
            zzdyq.zza(com.google.android.gms.ads.internal.zzp.zzkr().zzi(uri), new zzbgp(this, list, path), zzbbz.zzeeu);
            return;
        }
        String valueOf = String.valueOf(uri);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32);
        sb.append("No GMSG handler found for GMSG: ");
        sb.append(valueOf);
        zzayp.zzei(sb.toString());
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcxk)).booleanValue() && com.google.android.gms.ads.internal.zzp.zzkv().zzwn() != null) {
            zzbbz.zzeep.execute(new zzbgk(path));
        }
    }

    /* access modifiers changed from: private */
    public final void zza(Map<String, String> map, List<zzahq<? super zzbgj>> list, String str) {
        if (zzayp.isLoggable(2)) {
            String valueOf = String.valueOf(str);
            zzayp.zzei(valueOf.length() != 0 ? "Received GMSG: ".concat(valueOf) : new String("Received GMSG: "));
            for (String next : map.keySet()) {
                String str2 = map.get(next);
                StringBuilder sb = new StringBuilder(String.valueOf(next).length() + 4 + String.valueOf(str2).length());
                sb.append("  ");
                sb.append(next);
                sb.append(": ");
                sb.append(str2);
                zzayp.zzei(sb.toString());
            }
        }
        for (zzahq<? super zzbgj> zza : list) {
            zza.zza(this.zzemq, map);
        }
    }

    public final void zzay(boolean z) {
        synchronized (this.lock) {
            this.zzemx = true;
        }
    }

    public final void zzaz(boolean z) {
        synchronized (this.lock) {
            this.zzemy = z;
        }
    }

    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return this.zzemq.zzc(renderProcessGoneDetail.didCrash(), renderProcessGoneDetail.rendererPriorityAtExit());
    }
}
