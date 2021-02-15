package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdnp extends zzauc {
    private final zzdoj zzfuj;
    private boolean zzgtm = false;
    private final zzdnb zzhcx;
    private final zzdmc zzhcy;
    /* access modifiers changed from: private */
    public zzcjg zzhcz;

    public zzdnp(zzdnb zzdnb, zzdmc zzdmc, zzdoj zzdoj) {
        this.zzhcx = zzdnb;
        this.zzhcy = zzdmc;
        this.zzfuj = zzdoj;
    }

    public final void setAppPackageName(String str) throws RemoteException {
    }

    public final synchronized void zza(zzaum zzaum) throws RemoteException {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (!zzabd.zzct(zzaum.zzbuu)) {
            if (zzaqu()) {
                if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcuv)).booleanValue()) {
                    return;
                }
            }
            zzdmy zzdmy = new zzdmy((String) null);
            this.zzhcz = null;
            this.zzhcx.zzea(zzdoc.zzhep);
            this.zzhcx.zza(zzaum.zzdqr, zzaum.zzbuu, zzdmy, new zzdno(this));
        }
    }

    public final void destroy() throws RemoteException {
        zzl((IObjectWrapper) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.content.Context} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzl(com.google.android.gms.dynamic.IObjectWrapper r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "destroy must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.ads.zzdmc r0 = r2.zzhcy     // Catch:{ all -> 0x0025 }
            r1 = 0
            r0.zza(r1)     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.ads.zzcjg r0 = r2.zzhcz     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0023
            if (r3 != 0) goto L_0x0013
            goto L_0x001a
        L_0x0013:
            java.lang.Object r3 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r3)     // Catch:{ all -> 0x0025 }
            r1 = r3
            android.content.Context r1 = (android.content.Context) r1     // Catch:{ all -> 0x0025 }
        L_0x001a:
            com.google.android.gms.internal.ads.zzcjg r3 = r2.zzhcz     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.ads.zzbvb r3 = r3.zzaiw()     // Catch:{ all -> 0x0025 }
            r3.zzcc(r1)     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r2)
            return
        L_0x0025:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdnp.zzl(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    public final void pause() {
        zzj((IObjectWrapper) null);
    }

    public final synchronized void zzj(IObjectWrapper iObjectWrapper) {
        Context context;
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (this.zzhcz != null) {
            if (iObjectWrapper == null) {
                context = null;
            } else {
                context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            }
            this.zzhcz.zzaiw().zzca(context);
        }
    }

    public final void resume() {
        zzk((IObjectWrapper) null);
    }

    public final synchronized void zzk(IObjectWrapper iObjectWrapper) {
        Context context;
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        if (this.zzhcz != null) {
            if (iObjectWrapper == null) {
                context = null;
            } else {
                context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            }
            this.zzhcz.zzaiw().zzcb(context);
        }
    }

    public final synchronized void show() throws RemoteException {
        zzi((IObjectWrapper) null);
    }

    public final synchronized void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
        Activity activity;
        Preconditions.checkMainThread("showAd must be called on the main UI thread.");
        if (this.zzhcz != null) {
            if (iObjectWrapper != null) {
                Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
                if (unwrap instanceof Activity) {
                    activity = (Activity) unwrap;
                    this.zzhcz.zzb(this.zzgtm, activity);
                }
            }
            activity = null;
            this.zzhcz.zzb(this.zzgtm, activity);
        }
    }

    public final boolean zzra() {
        zzcjg zzcjg = this.zzhcz;
        return zzcjg != null && zzcjg.zzra();
    }

    public final synchronized String getMediationAdapterClassName() throws RemoteException {
        if (this.zzhcz == null || this.zzhcz.zzaix() == null) {
            return null;
        }
        return this.zzhcz.zzaix().getMediationAdapterClassName();
    }

    public final boolean isLoaded() throws RemoteException {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return zzaqu();
    }

    public final void zza(zzaug zzaug) throws RemoteException {
        Preconditions.checkMainThread("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzhcy.zzb(zzaug);
    }

    public final void zza(zzaub zzaub) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzhcy.zzb(zzaub);
    }

    public final void zza(zzxj zzxj) {
        Preconditions.checkMainThread("setAdMetadataListener can only be called from the UI thread.");
        if (zzxj == null) {
            this.zzhcy.zza((AdMetadataListener) null);
        } else {
            this.zzhcy.zza(new zzdnr(this, zzxj));
        }
    }

    public final Bundle getAdMetadata() {
        Preconditions.checkMainThread("getAdMetadata can only be called from the UI thread.");
        zzcjg zzcjg = this.zzhcz;
        return zzcjg != null ? zzcjg.getAdMetadata() : new Bundle();
    }

    public final synchronized void setUserId(String str) throws RemoteException {
        Preconditions.checkMainThread("setUserId must be called on the main UI thread.");
        this.zzfuj.zzdvz = str;
    }

    public final synchronized void setCustomData(String str) throws RemoteException {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcon)).booleanValue()) {
            Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCustomData");
            this.zzfuj.zzdwa = str;
        }
    }

    public final synchronized void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzgtm = z;
    }

    public final synchronized zzyn zzki() throws RemoteException {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxl)).booleanValue()) {
            return null;
        }
        if (this.zzhcz == null) {
            return null;
        }
        return this.zzhcz.zzaix();
    }

    private final synchronized boolean zzaqu() {
        return this.zzhcz != null && !this.zzhcz.isClosed();
    }
}
