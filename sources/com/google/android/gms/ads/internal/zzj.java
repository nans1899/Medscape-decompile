package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaak;
import com.google.android.gms.internal.ads.zzaby;
import com.google.android.gms.internal.ads.zzacm;
import com.google.android.gms.internal.ads.zzari;
import com.google.android.gms.internal.ads.zzaro;
import com.google.android.gms.internal.ads.zzaug;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzbbg;
import com.google.android.gms.internal.ads.zzbbx;
import com.google.android.gms.internal.ads.zzbbz;
import com.google.android.gms.internal.ads.zzef;
import com.google.android.gms.internal.ads.zzeg;
import com.google.android.gms.internal.ads.zzsh;
import com.google.android.gms.internal.ads.zzvg;
import com.google.android.gms.internal.ads.zzvn;
import com.google.android.gms.internal.ads.zzvs;
import com.google.android.gms.internal.ads.zzwm;
import com.google.android.gms.internal.ads.zzwo;
import com.google.android.gms.internal.ads.zzwt;
import com.google.android.gms.internal.ads.zzxf;
import com.google.android.gms.internal.ads.zzxj;
import com.google.android.gms.internal.ads.zzxk;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzyi;
import com.google.android.gms.internal.ads.zzyn;
import com.google.android.gms.internal.ads.zzyo;
import com.google.android.gms.internal.ads.zzyu;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.util.Map;
import java.util.concurrent.Future;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzj extends zzxf {
    /* access modifiers changed from: private */
    public final zzbbx zzbpe;
    private final zzvn zzbpf;
    /* access modifiers changed from: private */
    public final Future<zzeg> zzbpg = zzbbz.zzeep.zze(new zzo(this));
    private final zzq zzbph;
    /* access modifiers changed from: private */
    public WebView zzbpi = new WebView(this.zzvr);
    /* access modifiers changed from: private */
    public zzwt zzbpj;
    /* access modifiers changed from: private */
    public zzeg zzbpk;
    private AsyncTask<Void, Void, String> zzbpl;
    /* access modifiers changed from: private */
    public final Context zzvr;

    public zzj(Context context, zzvn zzvn, String str, zzbbx zzbbx) {
        this.zzvr = context;
        this.zzbpe = zzbbx;
        this.zzbpf = zzvn;
        this.zzbph = new zzq(context, str);
        zzbt(0);
        this.zzbpi.setVerticalScrollBarEnabled(false);
        this.zzbpi.getSettings().setJavaScriptEnabled(true);
        this.zzbpi.setWebViewClient(new zzm(this));
        this.zzbpi.setOnTouchListener(new zzl(this));
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final zzyo getVideoController() {
        return null;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final boolean isReady() throws RemoteException {
        return false;
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    public final void stopLoading() throws RemoteException {
    }

    public final void zza(zzyi zzyi) {
    }

    public final String zzkh() throws RemoteException {
        return null;
    }

    public final zzyn zzki() {
        return null;
    }

    public final IObjectWrapper zzke() throws RemoteException {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap(this.zzbpi);
    }

    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzbpl.cancel(true);
        this.zzbpg.cancel(true);
        this.zzbpi.destroy();
        this.zzbpi = null;
    }

    public final boolean zza(zzvg zzvg) throws RemoteException {
        Preconditions.checkNotNull(this.zzbpi, "This Search Ad has already been torn down");
        this.zzbph.zza(zzvg, this.zzbpe);
        this.zzbpl = new zzn(this, (zzm) null).execute(new Void[0]);
        return true;
    }

    public final void pause() throws RemoteException {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }

    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }

    public final void zza(zzwt zzwt) throws RemoteException {
        this.zzbpj = zzwt;
    }

    public final void zza(zzxk zzxk) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzxj zzxj) {
        throw new IllegalStateException("Unused method");
    }

    public final Bundle getAdMetadata() {
        throw new IllegalStateException("Unused method");
    }

    public final void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zzkf() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final zzvn zzkg() throws RemoteException {
        return this.zzbpf;
    }

    public final void zza(zzvn zzvn) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public final void zza(zzari zzari) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzaro zzaro, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    public final zzxk zzkj() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    public final zzwt zzkk() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    public final void zza(zzaby zzaby) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzwo zzwo) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzxq zzxq) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzaug zzaug) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void setUserId(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void zzbp(String str) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzaak zzaak) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzyu zzyu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzvs zzvs) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzsh zzsh) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public final void setImmersiveMode(boolean z) {
        throw new IllegalStateException("Unused method");
    }

    /* access modifiers changed from: package-private */
    public final int zzbq(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT);
        if (TextUtils.isEmpty(queryParameter)) {
            return 0;
        }
        try {
            zzwm.zzpt();
            return zzbbg.zzc(this.zzvr, Integer.parseInt(queryParameter));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzbt(int i) {
        if (this.zzbpi != null) {
            this.zzbpi.setLayoutParams(new ViewGroup.LayoutParams(-1, i));
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzkl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https://").appendEncodedPath(zzacm.zzdag.get());
        builder.appendQueryParameter(SearchIntents.EXTRA_QUERY, this.zzbph.getQuery());
        builder.appendQueryParameter("pubId", this.zzbph.zzlr());
        Map<String, String> zzls = this.zzbph.zzls();
        for (String next : zzls.keySet()) {
            builder.appendQueryParameter(next, zzls.get(next));
        }
        Uri build = builder.build();
        zzeg zzeg = this.zzbpk;
        if (zzeg != null) {
            try {
                build = zzeg.zza(build, this.zzvr);
            } catch (zzef e) {
                zzayp.zzd("Unable to process ad data", e);
            }
        }
        String zzkm = zzkm();
        String encodedQuery = build.getEncodedQuery();
        StringBuilder sb = new StringBuilder(String.valueOf(zzkm).length() + 1 + String.valueOf(encodedQuery).length());
        sb.append(zzkm);
        sb.append("#");
        sb.append(encodedQuery);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zzkm() {
        String zzlq = this.zzbph.zzlq();
        if (TextUtils.isEmpty(zzlq)) {
            zzlq = "www.google.com";
        }
        String str = zzacm.zzdag.get();
        StringBuilder sb = new StringBuilder(String.valueOf(zzlq).length() + 8 + String.valueOf(str).length());
        sb.append("https://");
        sb.append(zzlq);
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public final String zzbr(String str) {
        if (this.zzbpk == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzbpk.zza(parse, this.zzvr, (View) null, (Activity) null);
        } catch (zzef e) {
            zzayp.zzd("Unable to process ad data", e);
        }
        return parse.toString();
    }

    /* access modifiers changed from: private */
    public final void zzbs(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.zzvr.startActivity(intent);
    }
}
