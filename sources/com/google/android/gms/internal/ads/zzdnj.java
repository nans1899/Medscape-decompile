package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdnj extends zzavb {
    private final String zzbuu;
    private final zzdoj zzfuj;
    private final Context zzgsl;
    private final zzdnb zzhcx;
    private final zzdmc zzhcy;
    /* access modifiers changed from: private */
    public zzcjg zzhcz;

    public zzdnj(String str, zzdnb zzdnb, Context context, zzdmc zzdmc, zzdoj zzdoj) {
        this.zzbuu = str;
        this.zzhcx = zzdnb;
        this.zzhcy = zzdmc;
        this.zzfuj = zzdoj;
        this.zzgsl = context;
    }

    public final synchronized void zza(zzvg zzvg, zzavg zzavg) throws RemoteException {
        zza(zzvg, zzavg, zzdoc.zzheq);
    }

    public final synchronized void zzb(zzvg zzvg, zzavg zzavg) throws RemoteException {
        zza(zzvg, zzavg, zzdoc.zzher);
    }

    public final synchronized void zzh(IObjectWrapper iObjectWrapper) throws RemoteException {
        zza(iObjectWrapper, false);
    }

    public final synchronized void zza(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if (this.zzhcz == null) {
            zzayp.zzfe("Rewarded can not be shown before loaded");
            this.zzhcy.zzj(zzdpe.zza(zzdpg.NOT_READY, (String) null, (zzva) null));
            return;
        }
        this.zzhcz.zzb(z, (Activity) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final synchronized String getMediationAdapterClassName() throws RemoteException {
        if (this.zzhcz == null || this.zzhcz.zzaix() == null) {
            return null;
        }
        return this.zzhcz.zzaix().getMediationAdapterClassName();
    }

    public final boolean isLoaded() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzcjg zzcjg = this.zzhcz;
        return zzcjg != null && !zzcjg.zzanu();
    }

    public final void zza(zzavd zzavd) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzhcy.zzb(zzavd);
    }

    public final void zza(zzavl zzavl) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzhcy.zzb(zzavl);
    }

    public final void zza(zzyh zzyh) {
        if (zzyh == null) {
            this.zzhcy.zza((AdMetadataListener) null);
        } else {
            this.zzhcy.zza(new zzdni(this, zzyh));
        }
    }

    public final Bundle getAdMetadata() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzcjg zzcjg = this.zzhcz;
        return zzcjg != null ? zzcjg.getAdMetadata() : new Bundle();
    }

    public final zzaux zzqz() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzcjg zzcjg = this.zzhcz;
        if (zzcjg != null) {
            return zzcjg.zzqz();
        }
        return null;
    }

    public final synchronized void zza(zzavt zzavt) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzdoj zzdoj = this.zzfuj;
        zzdoj.zzdvz = zzavt.zzdvz;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcon)).booleanValue()) {
            zzdoj.zzdwa = zzavt.zzdwa;
        }
    }

    public final zzyn zzki() {
        zzcjg zzcjg;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcxl)).booleanValue() && (zzcjg = this.zzhcz) != null) {
            return zzcjg.zzaix();
        }
        return null;
    }

    public final void zza(zzyi zzyi) {
        Preconditions.checkMainThread("setOnPaidEventListener must be called on the main UI thread.");
        this.zzhcy.zzc(zzyi);
    }

    private final synchronized void zza(zzvg zzvg, zzavg zzavg, int i) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzhcy.zzb(zzavg);
        zzp.zzkr();
        if (zzayu.zzbe(this.zzgsl) && zzvg.zzcho == null) {
            zzayp.zzfc("Failed to load the ad because app ID is missing.");
            this.zzhcy.zzk(zzdpe.zza(zzdpg.APP_ID_MISSING, (String) null, (zzva) null));
        } else if (this.zzhcz == null) {
            zzdmy zzdmy = new zzdmy((String) null);
            this.zzhcx.zzea(i);
            this.zzhcx.zza(zzvg, this.zzbuu, zzdmy, new zzdnl(this));
        }
    }
}
