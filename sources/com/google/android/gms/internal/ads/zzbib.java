package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.common.util.Predicate;
import com.google.common.net.HttpHeaders;
import com.tapstream.sdk.http.RequestBuilders;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbib extends zzbik implements zzbhv {
    private final Object lock = new Object();
    private volatile boolean zzbrf;
    private zzux zzcgv;
    private zzagt zzdep;
    private zzagv zzdeq;
    private zza zzdfm;
    private zzaqd zzdfn;
    private zzp zzdpm;
    private zzv zzdpq;
    private boolean zzdtp;
    protected zzbgj zzemq;
    private zzbhu zzemt;
    private zzbhx zzemu;
    private zzbhw zzemv;
    private boolean zzemw = false;
    private boolean zzemx;
    private boolean zzemy;
    private boolean zzemz;
    private zzaqo zzena;
    private zzawl zzenb;
    private boolean zzenc;
    private boolean zzend;
    private int zzene;
    private View.OnAttachStateChangeListener zzenf;
    private final zzakd<zzbgj> zzeqp = new zzakd<>();

    /* access modifiers changed from: package-private */
    public final void zza(zzbgj zzbgj, boolean z) {
        zzaqo zzaqo = new zzaqo(zzbgj, zzbgj.zzabr(), new zzaam(zzbgj.getContext()));
        this.zzemq = zzbgj;
        this.zzbrf = z;
        this.zzena = zzaqo;
        this.zzdfn = null;
        this.zzeqp.zzg(zzbgj);
    }

    public final void zza(int i, int i2, boolean z) {
        this.zzena.zzj(i, i2);
        zzaqd zzaqd = this.zzdfn;
        if (zzaqd != null) {
            zzaqd.zza(i, i2, false);
        }
    }

    public final void zza(String str, zzahq<? super zzbgj> zzahq) {
        this.zzeqp.zza(str, zzahq);
    }

    public final void zzb(String str, zzahq<? super zzbgj> zzahq) {
        this.zzeqp.zzb(str, zzahq);
    }

    public final void zza(String str, Predicate<zzahq<? super zzbgj>> predicate) {
        this.zzeqp.zza(str, predicate);
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
        return this.zzbrf;
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

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        zzsu zzacm = this.zzemq.zzacm();
        if (zzacm != null && webView == zzacm.getWebView()) {
            zzacm.onPageStarted(webView, str, bitmap);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    public final void zza(zzbin zzbin) {
        this.zzenc = true;
        zzbhx zzbhx = this.zzemu;
        if (zzbhx != null) {
            zzbhx.zztj();
            this.zzemu = null;
        }
        zzabm();
    }

    /* access modifiers changed from: private */
    public final void zza(View view, zzawl zzawl, int i) {
        if (zzawl.zzvr() && i > 0) {
            zzawl.zzl(view);
            if (zzawl.zzvr()) {
                zzayu.zzeba.postDelayed(new zzbid(this, view, zzawl, i), 100);
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
            this.zzenf = new zzbic(this, zzawl);
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
        this.zzend = true;
        zzabm();
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
        zzbif zzbif;
        boolean zzacb = this.zzemq.zzacb();
        zzux zzux = (!zzacb || this.zzemq.zzabu().zzadb()) ? this.zzcgv : null;
        if (zzacb) {
            zzbif = null;
        } else {
            zzbif = new zzbif(this.zzemq, this.zzdpm);
        }
        zzagt zzagt = this.zzdep;
        zzagv zzagv = this.zzdeq;
        zzv zzv = this.zzdpq;
        zzbgj zzbgj = this.zzemq;
        zza(new AdOverlayInfoParcel(zzux, (zzp) zzbif, zzagt, zzagv, zzv, zzbgj, z, i, str, zzbgj.zzzx()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        zzbif zzbif;
        boolean zzacb = this.zzemq.zzacb();
        zzux zzux = (!zzacb || this.zzemq.zzabu().zzadb()) ? this.zzcgv : null;
        if (zzacb) {
            zzbif = null;
        } else {
            zzbif = new zzbif(this.zzemq, this.zzdpm);
        }
        zzagt zzagt = this.zzdep;
        zzagv zzagv = this.zzdeq;
        zzv zzv = this.zzdpq;
        zzbgj zzbgj = this.zzemq;
        zza(new AdOverlayInfoParcel(zzux, zzbif, zzagt, zzagv, zzv, zzbgj, z, i, str, str2, zzbgj.zzzx()));
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

    public final void destroy() {
        zzawl zzawl = this.zzenb;
        if (zzawl != null) {
            zzawl.zzvt();
            this.zzenb = null;
        }
        zzabh();
        this.zzeqp.reset();
        this.zzeqp.zzg(null);
        synchronized (this.lock) {
            this.zzcgv = null;
            this.zzdpm = null;
            this.zzemt = null;
            this.zzemu = null;
            this.zzdep = null;
            this.zzdeq = null;
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

    public final void zzb(zzbin zzbin) {
        this.zzeqp.zzg(zzbin.uri);
    }

    public final boolean zzc(zzbin zzbin) {
        String valueOf = String.valueOf(zzbin.url);
        zzayp.zzei(valueOf.length() != 0 ? "AdWebView shouldOverrideUrlLoading: ".concat(valueOf) : new String("AdWebView shouldOverrideUrlLoading: "));
        Uri uri = zzbin.uri;
        if (this.zzeqp.zzg(uri)) {
            return true;
        }
        if (this.zzemw) {
            String scheme = uri.getScheme();
            if ("http".equalsIgnoreCase(scheme) || RequestBuilders.DEFAULT_SCHEME.equalsIgnoreCase(scheme)) {
                zzux zzux = this.zzcgv;
                if (zzux != null) {
                    zzux.onAdClicked();
                    zzawl zzawl = this.zzenb;
                    if (zzawl != null) {
                        zzawl.zzea(zzbin.url);
                    }
                    this.zzcgv = null;
                }
                return false;
            }
        }
        if (!this.zzemq.getWebView().willNotDraw()) {
            try {
                zzeg zzabz = this.zzemq.zzabz();
                if (zzabz != null && zzabz.zzb(uri)) {
                    uri = zzabz.zza(uri, this.zzemq.getContext(), this.zzemq.getView(), this.zzemq.zzzu());
                }
            } catch (zzef unused) {
                String valueOf2 = String.valueOf(zzbin.url);
                zzayp.zzfe(valueOf2.length() != 0 ? "Unable to append parameter to URL: ".concat(valueOf2) : new String("Unable to append parameter to URL: "));
            }
            zza zza = this.zzdfm;
            if (zza == null || zza.zzjz()) {
                zza(new zzb("android.intent.action.VIEW", uri.toString(), (String) null, (String) null, (String) null, (String) null, (String) null));
            } else {
                this.zzdfm.zzbo(zzbin.url);
            }
        } else {
            String valueOf3 = String.valueOf(zzbin.url);
            zzayp.zzfe(valueOf3.length() != 0 ? "AdWebView unable to handle URL: ".concat(valueOf3) : new String("AdWebView unable to handle URL: "));
        }
        return true;
    }

    public final WebResourceResponse zzd(zzbin zzbin) {
        WebResourceResponse webResourceResponse;
        zzsz zza;
        String str;
        zzawl zzawl = this.zzenb;
        if (zzawl != null) {
            zzawl.zza(zzbin.url, zzbin.zzam, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(zzbin.url).getName())) {
            webResourceResponse = null;
        } else {
            zzuu();
            if (this.zzemq.zzabu().zzadb()) {
                str = (String) zzwm.zzpx().zzd(zzabb.zzcmz);
            } else if (this.zzemq.zzacb()) {
                str = (String) zzwm.zzpx().zzd(zzabb.zzcmy);
            } else {
                str = (String) zzwm.zzpx().zzd(zzabb.zzcmx);
            }
            com.google.android.gms.ads.internal.zzp.zzkr();
            webResourceResponse = zzayu.zzd(this.zzemq.getContext(), this.zzemq.zzzx().zzbre, str);
        }
        if (webResourceResponse != null) {
            return webResourceResponse;
        }
        try {
            if (!zzaxh.zzc(zzbin.url, this.zzemq.getContext(), this.zzdtp).equals(zzbin.url)) {
                return zze(zzbin);
            }
            zzte zzbw = zzte.zzbw(zzbin.url);
            if (zzbw != null && (zza = com.google.android.gms.ads.internal.zzp.zzkx().zza(zzbw)) != null && zza.zzmw()) {
                return new WebResourceResponse("", "", zza.zzmx());
            }
            if (!zzbbk.isEnabled() || !zzacr.zzdax.get().booleanValue()) {
                return null;
            }
            return zze(zzbin);
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

    private final WebResourceResponse zze(zzbin zzbin) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(zzbin.url);
        int i = 0;
        while (true) {
            i++;
            if (i <= 20) {
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(10000);
                openConnection.setReadTimeout(10000);
                for (Map.Entry next : zzbin.zzam.entrySet()) {
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
            zzbbz.zzeet.execute(new zzbia(this));
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

    public final void zzh(Uri uri) {
        this.zzeqp.zzh(uri);
    }

    public final boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return this.zzemq.zzc(renderProcessGoneDetail.didCrash(), renderProcessGoneDetail.rendererPriorityAtExit());
    }
}
