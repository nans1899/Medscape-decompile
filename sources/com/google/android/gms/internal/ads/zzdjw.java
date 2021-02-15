package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzbnw;
import com.google.android.gms.internal.ads.zzbqo;
import com.google.android.gms.internal.ads.zzbtp;
import com.google.android.gms.internal.ads.zzbtq;
import com.google.android.gms.internal.ads.zzbys;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzdjw<AppOpenAd extends zzbqo, AppOpenRequestComponent extends zzbnw<AppOpenAd>, AppOpenRequestComponentBuilder extends zzbtq<AppOpenRequestComponent>> implements zzdae<AppOpenAd> {
    /* access modifiers changed from: private */
    public final Executor zzfoc;
    private final ViewGroup zzfpw;
    private final zzdom zzgsm;
    /* access modifiers changed from: private */
    @Nullable
    public zzdzc<AppOpenAd> zzgsx;
    protected final zzbix zzguj;
    private final Context zzhah;
    /* access modifiers changed from: private */
    public final zzdkc zzhai;
    /* access modifiers changed from: private */
    public final zzdlv<AppOpenRequestComponent, AppOpenAd> zzhaj;

    protected zzdjw(Context context, Executor executor, zzbix zzbix, zzdlv<AppOpenRequestComponent, AppOpenAd> zzdlv, zzdkc zzdkc, zzdom zzdom) {
        this.zzhah = context;
        this.zzfoc = executor;
        this.zzguj = zzbix;
        this.zzhaj = zzdlv;
        this.zzhai = zzdkc;
        this.zzgsm = zzdom;
        this.zzfpw = new FrameLayout(context);
    }

    /* access modifiers changed from: protected */
    public abstract AppOpenRequestComponentBuilder zza(zzbol zzbol, zzbtp zzbtp, zzbys zzbys);

    public final boolean isLoading() {
        zzdzc<AppOpenAd> zzdzc = this.zzgsx;
        return zzdzc != null && !zzdzc.isDone();
    }

    public final synchronized boolean zza(zzvg zzvg, String str, zzdad zzdad, zzdag<? super AppOpenAd> zzdag) throws RemoteException {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (str == null) {
            zzayp.zzfc("Ad unit ID should not be null for app open ad.");
            this.zzfoc.execute(new zzdjz(this));
            return false;
        } else if (this.zzgsx != null) {
            return false;
        } else {
            zzdox.zze(this.zzhah, zzvg.zzche);
            zzdok zzatn = this.zzgsm.zzgt(str).zze(zzvn.zzpm()).zzh(zzvg).zzatn();
            zzdkd zzdkd = new zzdkd((zzdkb) null);
            zzdkd.zzfsk = zzatn;
            zzdzc<AppOpenAd> zza = this.zzhaj.zza(new zzdlw(zzdkd), new zzdjy(this));
            this.zzgsx = zza;
            zzdyq.zza(zza, new zzdkb(this, zzdag, zzdkd), this.zzfoc);
            return true;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final synchronized AppOpenRequestComponentBuilder zzb(zzdlu zzdlu) {
        zzdkd zzdkd = (zzdkd) zzdlu;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyg)).booleanValue()) {
            return zza(new zzbol(this.zzfpw), new zzbtp.zza().zzce(this.zzhah).zza(zzdkd.zzfsk).zzajv(), new zzbys.zza().zzakr());
        }
        zzdkc zzb = zzdkc.zzb(this.zzhai);
        zzbys.zza zza = new zzbys.zza();
        zza.zza((zzbui) zzb, this.zzfoc);
        zza.zza((zzbwb) zzb, this.zzfoc);
        zza.zza((zzp) zzb, this.zzfoc);
        zza.zza((zzdls) zzb);
        return zza(new zzbol(this.zzfpw), new zzbtp.zza().zzce(this.zzhah).zza(zzdkd.zzfsk).zzajv(), zza.zzakr());
    }

    public final void zza(zzvs zzvs) {
        this.zzgsm.zzb(zzvs);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzass() {
        this.zzhai.zzk(zzdpe.zza(zzdpg.INVALID_AD_UNIT_ID, (String) null, (zzva) null));
    }
}
