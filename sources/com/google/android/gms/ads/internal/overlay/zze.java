package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzabs;
import com.google.android.gms.internal.ads.zzacb;
import com.google.android.gms.internal.ads.zzahp;
import com.google.android.gms.internal.ads.zzaqq;
import com.google.android.gms.internal.ads.zzarb;
import com.google.android.gms.internal.ads.zzawl;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzayu;
import com.google.android.gms.internal.ads.zzazd;
import com.google.android.gms.internal.ads.zzbgj;
import com.google.android.gms.internal.ads.zzbgr;
import com.google.android.gms.internal.ads.zzbhu;
import com.google.android.gms.internal.ads.zzbhv;
import com.google.android.gms.internal.ads.zzdnv;
import com.google.android.gms.internal.ads.zzdnw;
import com.google.android.gms.internal.ads.zzeg;
import com.google.android.gms.internal.ads.zzsu;
import com.google.android.gms.internal.ads.zzts;
import com.google.android.gms.internal.ads.zzux;
import com.google.android.gms.internal.ads.zzwm;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zze extends zzarb implements zzw {
    private static final int zzdon = Color.argb(0, 0, 0, 0);
    protected final Activity zzaas;
    private boolean zzbov = false;
    zzbgj zzdgy;
    AdOverlayInfoParcel zzdoo;
    private zzk zzdop;
    private zzo zzdoq;
    private boolean zzdor = false;
    private FrameLayout zzdos;
    private WebChromeClient.CustomViewCallback zzdot;
    private boolean zzdou = false;
    private zzh zzdov;
    private boolean zzdow = false;
    int zzdox = 0;
    private final Object zzdoy = new Object();
    private Runnable zzdoz;
    private boolean zzdpa;
    private boolean zzdpb;
    private boolean zzdpc = false;
    private boolean zzdpd = false;
    private boolean zzdpe = true;

    public zze(Activity activity) {
        this.zzaas = activity;
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
    }

    public final void onRestart() {
    }

    public final void close() {
        this.zzdox = 2;
        this.zzaas.finish();
    }

    public final void zzur() {
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzdoo;
        if (adOverlayInfoParcel != null && this.zzdor) {
            setRequestedOrientation(adOverlayInfoParcel.orientation);
        }
        if (this.zzdos != null) {
            this.zzaas.setContentView(this.zzdov);
            this.zzdpb = true;
            this.zzdos.removeAllViews();
            this.zzdos = null;
        }
        WebChromeClient.CustomViewCallback customViewCallback = this.zzdot;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
            this.zzdot = null;
        }
        this.zzdor = false;
    }

    public final void zzus() {
        this.zzdox = 1;
        this.zzaas.finish();
    }

    public final void onBackPressed() {
        this.zzdox = 0;
    }

    public final boolean zzut() {
        this.zzdox = 0;
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj == null) {
            return true;
        }
        boolean zzacd = zzbgj.zzacd();
        if (!zzacd) {
            this.zzdgy.zza("onbackblocked", (Map<String, ?>) Collections.emptyMap());
        }
        return zzacd;
    }

    public void onCreate(Bundle bundle) {
        this.zzaas.requestWindowFeature(1);
        this.zzdou = bundle != null && bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        try {
            AdOverlayInfoParcel zzd = AdOverlayInfoParcel.zzd(this.zzaas.getIntent());
            this.zzdoo = zzd;
            if (zzd != null) {
                if (zzd.zzbpe.zzeem > 7500000) {
                    this.zzdox = 3;
                }
                if (this.zzaas.getIntent() != null) {
                    this.zzdpe = this.zzaas.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
                }
                if (this.zzdoo.zzdpt != null) {
                    this.zzbov = this.zzdoo.zzdpt.zzbov;
                } else {
                    this.zzbov = false;
                }
                if (this.zzbov && this.zzdoo.zzdpt.zzbpa != -1) {
                    new zzj(this).zzwz();
                }
                if (bundle == null) {
                    if (this.zzdoo.zzdpm != null && this.zzdpe) {
                        this.zzdoo.zzdpm.zzun();
                    }
                    if (!(this.zzdoo.zzdpr == 1 || this.zzdoo.zzcgv == null)) {
                        this.zzdoo.zzcgv.onAdClicked();
                    }
                }
                zzh zzh = new zzh(this.zzaas, this.zzdoo.zzdps, this.zzdoo.zzbpe.zzbre);
                this.zzdov = zzh;
                zzh.setId(1000);
                zzp.zzkt().zzg(this.zzaas);
                int i = this.zzdoo.zzdpr;
                if (i == 1) {
                    zzak(false);
                } else if (i == 2) {
                    this.zzdop = new zzk(this.zzdoo.zzdgy);
                    zzak(false);
                } else if (i == 3) {
                    zzak(true);
                } else {
                    throw new zzi("Could not determine ad overlay type.");
                }
            } else {
                throw new zzi("Could not get info for ad overlay.");
            }
        } catch (zzi e) {
            zzayp.zzfe(e.getMessage());
            this.zzdox = 3;
            this.zzaas.finish();
        }
    }

    public final void onStart() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzctt)).booleanValue()) {
            zzbgj zzbgj = this.zzdgy;
            if (zzbgj == null || zzbgj.isDestroyed()) {
                zzayp.zzfe("The webview does not exist. Ignoring action.");
                return;
            }
            zzp.zzkt();
            zzazd.zzb(this.zzdgy);
        }
    }

    public final void onResume() {
        if (this.zzdoo.zzdpm != null) {
            this.zzdoo.zzdpm.onResume();
        }
        zza(this.zzaas.getResources().getConfiguration());
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzctt)).booleanValue()) {
            zzbgj zzbgj = this.zzdgy;
            if (zzbgj == null || zzbgj.isDestroyed()) {
                zzayp.zzfe("The webview does not exist. Ignoring action.");
                return;
            }
            zzp.zzkt();
            zzazd.zzb(this.zzdgy);
        }
    }

    public final void onPause() {
        zzur();
        if (this.zzdoo.zzdpm != null) {
            this.zzdoo.zzdpm.onPause();
        }
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzctt)).booleanValue() && this.zzdgy != null && (!this.zzaas.isFinishing() || this.zzdop == null)) {
            zzp.zzkt();
            zzazd.zza(this.zzdgy);
        }
        zzuv();
    }

    public final void zzad(IObjectWrapper iObjectWrapper) {
        zza((Configuration) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzdou);
    }

    public final void onStop() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzctt)).booleanValue() && this.zzdgy != null && (!this.zzaas.isFinishing() || this.zzdop == null)) {
            zzp.zzkt();
            zzazd.zza(this.zzdgy);
        }
        zzuv();
    }

    public final void onDestroy() {
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj != null) {
            try {
                this.zzdov.removeView(zzbgj.getView());
            } catch (NullPointerException unused) {
            }
        }
        zzuv();
    }

    private final void zzaj(boolean z) {
        int intValue = ((Integer) zzwm.zzpx().zzd(zzabb.zzctv)).intValue();
        zzr zzr = new zzr();
        zzr.size = 50;
        zzr.paddingLeft = z ? intValue : 0;
        zzr.paddingRight = z ? 0 : intValue;
        zzr.paddingTop = 0;
        zzr.paddingBottom = intValue;
        this.zzdoq = new zzo(this.zzaas, zzr, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        zza(z, this.zzdoo.zzdpo);
        this.zzdov.addView(this.zzdoq, layoutParams);
    }

    public final void zzdq() {
        this.zzdpb = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0014, code lost:
        r0 = r6.zzdoo;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(boolean r7, boolean r8) {
        /*
            r6 = this;
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcou
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r6.zzdoo
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.zzi r0 = r0.zzdpt
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = r6.zzdoo
            com.google.android.gms.ads.internal.zzi r0 = r0.zzdpt
            boolean r0 = r0.zzbpc
            if (r0 == 0) goto L_0x0026
            r0 = 1
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zzabb.zzcov
            com.google.android.gms.internal.ads.zzaax r4 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r3 = r4.zzd(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r6.zzdoo
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.ads.internal.zzi r3 = r3.zzdpt
            if (r3 == 0) goto L_0x004b
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r3 = r6.zzdoo
            com.google.android.gms.ads.internal.zzi r3 = r3.zzdpt
            boolean r3 = r3.zzbpd
            if (r3 == 0) goto L_0x004b
            r3 = 1
            goto L_0x004c
        L_0x004b:
            r3 = 0
        L_0x004c:
            if (r7 == 0) goto L_0x0062
            if (r8 == 0) goto L_0x0062
            if (r0 == 0) goto L_0x0062
            if (r3 != 0) goto L_0x0062
            com.google.android.gms.internal.ads.zzaqn r7 = new com.google.android.gms.internal.ads.zzaqn
            com.google.android.gms.internal.ads.zzbgj r4 = r6.zzdgy
            java.lang.String r5 = "useCustomClose"
            r7.<init>(r4, r5)
            java.lang.String r4 = "Custom close has been disabled for interstitial ads in this ad slot."
            r7.zzdx(r4)
        L_0x0062:
            com.google.android.gms.ads.internal.overlay.zzo r7 = r6.zzdoq
            if (r7 == 0) goto L_0x0071
            if (r3 != 0) goto L_0x006e
            if (r8 == 0) goto L_0x006d
            if (r0 != 0) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r1 = 0
        L_0x006e:
            r7.zzal(r1)
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zze.zza(boolean, boolean):void");
    }

    public final void zzuu() {
        this.zzdov.removeView(this.zzdoq);
        zzaj(true);
    }

    public final void setRequestedOrientation(int i) {
        if (this.zzaas.getApplicationInfo().targetSdkVersion >= ((Integer) zzwm.zzpx().zzd(zzabb.zzcvy)).intValue()) {
            if (this.zzaas.getApplicationInfo().targetSdkVersion <= ((Integer) zzwm.zzpx().zzd(zzabb.zzcvz)).intValue()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzwm.zzpx().zzd(zzabb.zzcwa)).intValue()) {
                    if (Build.VERSION.SDK_INT <= ((Integer) zzwm.zzpx().zzd(zzabb.zzcwb)).intValue()) {
                        return;
                    }
                }
            }
        }
        try {
            this.zzaas.setRequestedOrientation(i);
        } catch (Throwable th) {
            zzp.zzkv().zzb(th, "AdOverlay.setRequestedOrientation");
        }
    }

    public final void zza(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        FrameLayout frameLayout = new FrameLayout(this.zzaas);
        this.zzdos = frameLayout;
        frameLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzdos.addView(view, -1, -1);
        this.zzaas.setContentView(this.zzdos);
        this.zzdpb = true;
        this.zzdot = customViewCallback;
        this.zzdor = true;
    }

    private final void zzak(boolean z) throws zzi {
        if (!this.zzdpb) {
            this.zzaas.requestWindowFeature(1);
        }
        Window window = this.zzaas.getWindow();
        if (window != null) {
            zzbhv zzabw = this.zzdoo.zzdgy != null ? this.zzdoo.zzdgy.zzabw() : null;
            boolean z2 = false;
            boolean z3 = zzabw != null && zzabw.zzabc();
            this.zzdow = false;
            if (z3) {
                int i = this.zzdoo.orientation;
                zzp.zzkt();
                if (i == 6) {
                    if (this.zzaas.getResources().getConfiguration().orientation == 1) {
                        z2 = true;
                    }
                    this.zzdow = z2;
                } else {
                    int i2 = this.zzdoo.orientation;
                    zzp.zzkt();
                    if (i2 == 7) {
                        if (this.zzaas.getResources().getConfiguration().orientation == 2) {
                            z2 = true;
                        }
                        this.zzdow = z2;
                    }
                }
            }
            boolean z4 = this.zzdow;
            StringBuilder sb = new StringBuilder(46);
            sb.append("Delay onShow to next orientation change: ");
            sb.append(z4);
            zzayp.zzef(sb.toString());
            setRequestedOrientation(this.zzdoo.orientation);
            zzp.zzkt();
            window.setFlags(16777216, 16777216);
            zzayp.zzef("Hardware acceleration on the AdActivity window enabled.");
            if (!this.zzbov) {
                this.zzdov.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            } else {
                this.zzdov.setBackgroundColor(zzdon);
            }
            this.zzaas.setContentView(this.zzdov);
            this.zzdpb = true;
            if (z) {
                try {
                    zzp.zzks();
                    zzbgj zza = zzbgr.zza(this.zzaas, this.zzdoo.zzdgy != null ? this.zzdoo.zzdgy.zzabu() : null, this.zzdoo.zzdgy != null ? this.zzdoo.zzdgy.zzabv() : null, true, z3, (zzeg) null, (zzacb) null, this.zzdoo.zzbpe, (zzabs) null, (zzk) null, this.zzdoo.zzdgy != null ? this.zzdoo.zzdgy.zzzv() : null, zzts.zzna(), (zzsu) null, false, (zzdnv) null, (zzdnw) null);
                    this.zzdgy = zza;
                    zza.zzabw().zza((zzux) null, this.zzdoo.zzdep, (zzp) null, this.zzdoo.zzdeq, this.zzdoo.zzdpq, true, (zzahp) null, this.zzdoo.zzdgy != null ? this.zzdoo.zzdgy.zzabw().zzabb() : null, (zzaqq) null, (zzawl) null);
                    this.zzdgy.zzabw().zza((zzbhu) new zzd(this));
                    if (this.zzdoo.url != null) {
                        this.zzdgy.loadUrl(this.zzdoo.url);
                    } else if (this.zzdoo.zzdpp != null) {
                        this.zzdgy.loadDataWithBaseURL(this.zzdoo.zzdpn, this.zzdoo.zzdpp, "text/html", "UTF-8", (String) null);
                    } else {
                        throw new zzi("No URL or HTML to display in ad overlay.");
                    }
                    if (this.zzdoo.zzdgy != null) {
                        this.zzdoo.zzdgy.zzb(this);
                    }
                } catch (Exception e) {
                    zzayp.zzc("Error obtaining webview.", e);
                    throw new zzi("Could not obtain webview for the overlay.");
                }
            } else {
                zzbgj zzbgj = this.zzdoo.zzdgy;
                this.zzdgy = zzbgj;
                zzbgj.zzbw(this.zzaas);
            }
            this.zzdgy.zza(this);
            if (this.zzdoo.zzdgy != null) {
                zzc(this.zzdoo.zzdgy.zzaca(), this.zzdov);
            }
            ViewParent parent = this.zzdgy.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this.zzdgy.getView());
            }
            if (this.zzbov) {
                this.zzdgy.zzaci();
            }
            this.zzdgy.zza((ViewGroup) null, this.zzaas, this.zzdoo.zzdpn, this.zzdoo.zzdpp);
            this.zzdov.addView(this.zzdgy.getView(), -1, -1);
            if (!z && !this.zzdow) {
                zzuy();
            }
            zzaj(z3);
            if (this.zzdgy.zzaby()) {
                zza(z3, true);
                return;
            }
            return;
        }
        throw new zzi("Invalid activity, no window available.");
    }

    private final void zzuv() {
        if (this.zzaas.isFinishing() && !this.zzdpc) {
            this.zzdpc = true;
            zzbgj zzbgj = this.zzdgy;
            if (zzbgj != null) {
                zzbgj.zzdu(this.zzdox);
                synchronized (this.zzdoy) {
                    if (!this.zzdpa && this.zzdgy.zzace()) {
                        this.zzdoz = new zzg(this);
                        zzayu.zzeba.postDelayed(this.zzdoz, ((Long) zzwm.zzpx().zzd(zzabb.zzcot)).longValue());
                        return;
                    }
                }
            }
            zzuw();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzuw() {
        if (!this.zzdpd) {
            this.zzdpd = true;
            zzbgj zzbgj = this.zzdgy;
            if (zzbgj != null) {
                this.zzdov.removeView(zzbgj.getView());
                zzk zzk = this.zzdop;
                if (zzk != null) {
                    this.zzdgy.zzbw(zzk.zzvr);
                    this.zzdgy.zzba(false);
                    this.zzdop.parent.addView(this.zzdgy.getView(), this.zzdop.index, this.zzdop.zzdpi);
                    this.zzdop = null;
                } else if (this.zzaas.getApplicationContext() != null) {
                    this.zzdgy.zzbw(this.zzaas.getApplicationContext());
                }
                this.zzdgy = null;
            }
            AdOverlayInfoParcel adOverlayInfoParcel = this.zzdoo;
            if (!(adOverlayInfoParcel == null || adOverlayInfoParcel.zzdpm == null)) {
                this.zzdoo.zzdpm.zzum();
            }
            AdOverlayInfoParcel adOverlayInfoParcel2 = this.zzdoo;
            if (adOverlayInfoParcel2 != null && adOverlayInfoParcel2.zzdgy != null) {
                zzc(this.zzdoo.zzdgy.zzaca(), this.zzdoo.zzdgy.getView());
            }
        }
    }

    private static void zzc(IObjectWrapper iObjectWrapper, View view) {
        if (iObjectWrapper != null && view != null) {
            zzp.zzlg().zza(iObjectWrapper, view);
        }
    }

    public final void zzux() {
        if (this.zzdow) {
            this.zzdow = false;
            zzuy();
        }
    }

    private final void zzuy() {
        this.zzdgy.zzuy();
    }

    public final void zzuz() {
        this.zzdov.zzdpg = true;
    }

    public final void zzva() {
        synchronized (this.zzdoy) {
            this.zzdpa = true;
            if (this.zzdoz != null) {
                zzayu.zzeba.removeCallbacks(this.zzdoz);
                zzayu.zzeba.post(this.zzdoz);
            }
        }
    }

    private final void zza(Configuration configuration) {
        AdOverlayInfoParcel adOverlayInfoParcel;
        AdOverlayInfoParcel adOverlayInfoParcel2 = this.zzdoo;
        boolean z = true;
        boolean z2 = false;
        boolean z3 = (adOverlayInfoParcel2 == null || adOverlayInfoParcel2.zzdpt == null || !this.zzdoo.zzdpt.zzbow) ? false : true;
        boolean zza = zzp.zzkt().zza(this.zzaas, configuration);
        if ((this.zzbov && !z3) || zza) {
            z = false;
        } else if (Build.VERSION.SDK_INT >= 19 && (adOverlayInfoParcel = this.zzdoo) != null && adOverlayInfoParcel.zzdpt != null && this.zzdoo.zzdpt.zzbpb) {
            z2 = true;
        }
        Window window = this.zzaas.getWindow();
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcow)).booleanValue() && Build.VERSION.SDK_INT >= 19) {
            View decorView = window.getDecorView();
            int i = 256;
            if (z) {
                i = 5380;
                if (z2) {
                    i = 5894;
                }
            }
            decorView.setSystemUiVisibility(i);
        } else if (z) {
            window.addFlags(1024);
            window.clearFlags(2048);
            if (Build.VERSION.SDK_INT >= 19 && z2) {
                window.getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
            }
        } else {
            window.addFlags(2048);
            window.clearFlags(1024);
        }
    }
}
