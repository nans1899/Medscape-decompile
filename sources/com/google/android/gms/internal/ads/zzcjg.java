package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzp;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcjg extends zzbqo {
    private final zzaux zzdwc;
    private final WeakReference<zzbgj> zzfwr;
    private final zzbzp zzfws;
    private final zzbrh zzfwt;
    private final zzdtn zzfwu;
    private final zzbuk zzfwv;
    private final zzccm zzfww;
    private boolean zzgeb = false;
    private final zzbvv zzges;
    private final Context zzvr;

    zzcjg(zzbqn zzbqn, Context context, zzbgj zzbgj, zzccm zzccm, zzbzp zzbzp, zzbuk zzbuk, zzbvv zzbvv, zzbrh zzbrh, zzdnv zzdnv, zzdtn zzdtn) {
        super(zzbqn);
        this.zzvr = context;
        this.zzfww = zzccm;
        this.zzfwr = new WeakReference<>(zzbgj);
        this.zzfws = zzbzp;
        this.zzfwv = zzbuk;
        this.zzges = zzbvv;
        this.zzfwt = zzbrh;
        this.zzfwu = zzdtn;
        this.zzdwc = new zzavu(zzdnv.zzdtc);
    }

    public final boolean zzb(boolean z, Activity activity) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcnz)).booleanValue()) {
            zzp.zzkr();
            if (zzayu.zzav(this.zzvr)) {
                zzayp.zzfe("Rewarded ads that show when your app is in the background are a violation of AdMob policies and may lead to blocked ad serving. To learn more, visit https://googlemobileadssdk.page.link/admob-interstitial-policies");
                this.zzfwv.zzajw();
                if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcoa)).booleanValue()) {
                    this.zzfwu.zzhc(this.zzfmw.zzhew.zzeou.zzdtb);
                }
                return false;
            }
        }
        if (this.zzgeb) {
            zzayp.zzfe("The rewarded ad have been showed.");
            this.zzfwv.zzl(zzdpe.zza(zzdpg.AD_REUSED, (String) null, (zzva) null));
            return false;
        }
        this.zzgeb = true;
        this.zzfws.zzajl();
        Context context = activity;
        if (activity == null) {
            context = this.zzvr;
        }
        try {
            this.zzfww.zza(z, context);
            this.zzfws.zzajj();
            return true;
        } catch (zzccl e) {
            this.zzfwv.zza(e);
            return false;
        }
    }

    public final boolean zzanu() {
        return this.zzgeb;
    }

    public final zzaux zzqz() {
        return this.zzdwc;
    }

    public final boolean isClosed() {
        return this.zzfwt.isClosed();
    }

    public final boolean zzra() {
        zzbgj zzbgj = (zzbgj) this.zzfwr.get();
        return zzbgj != null && !zzbgj.zzacl();
    }

    public final Bundle getAdMetadata() {
        return this.zzges.getAdMetadata();
    }

    public final void finalize() throws Throwable {
        try {
            zzbgj zzbgj = (zzbgj) this.zzfwr.get();
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcxj)).booleanValue()) {
                if (!this.zzgeb && zzbgj != null) {
                    zzdzb zzdzb = zzbbz.zzeet;
                    zzbgj.getClass();
                    zzdzb.execute(zzcjf.zzh(zzbgj));
                }
            } else if (zzbgj != null) {
                zzbgj.destroy();
            }
        } finally {
            super.finalize();
        }
    }
}
