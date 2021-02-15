package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.AdError;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzcyc extends zzank {
    private final zzbvr zzfrr;
    private final zzbvh zzfsg;
    private final zzbuk zzfwv;
    private final zzbwa zzfwy;
    private final zzbuu zzfxv;
    private final zzbuc zzfxw;
    private final zzbyf zzget;
    private final zzbxy zzgqz;
    private final zzcau zzgrk;

    public zzcyc(zzbuc zzbuc, zzbuu zzbuu, zzbvh zzbvh, zzbvr zzbvr, zzbyf zzbyf, zzbwa zzbwa, zzcau zzcau, zzbxy zzbxy, zzbuk zzbuk) {
        this.zzfxw = zzbuc;
        this.zzfxv = zzbuu;
        this.zzfsg = zzbvh;
        this.zzfrr = zzbvr;
        this.zzget = zzbyf;
        this.zzfwy = zzbwa;
        this.zzgrk = zzcau;
        this.zzgqz = zzbxy;
        this.zzfwv = zzbuk;
    }

    public final void onAdFailedToLoad(int i) {
    }

    public final void zza(zzafa zzafa, String str) {
    }

    public final void zza(zzanm zzanm) {
    }

    public void zza(zzaux zzaux) throws RemoteException {
    }

    public final void zzb(Bundle bundle) throws RemoteException {
    }

    public void zzb(zzauv zzauv) {
    }

    public final void zzc(int i, String str) {
    }

    public final void zzc(zzva zzva) {
    }

    public final void zzdn(String str) {
    }

    public void zzud() throws RemoteException {
    }

    public final void onAdClicked() {
        this.zzfxw.onAdClicked();
    }

    public final void onAdClosed() {
        this.zzfwy.zzum();
    }

    public final void onAdLeftApplication() {
        this.zzfsg.onAdLeftApplication();
    }

    public final void onAdOpened() {
        this.zzfwy.zzun();
        this.zzgqz.zzajm();
    }

    public final void onAppEvent(String str, String str2) {
        this.zzget.onAppEvent(str, str2);
    }

    public final void onAdLoaded() {
        this.zzfrr.onAdLoaded();
    }

    public void onAdImpression() {
        this.zzfxv.onAdImpression();
        this.zzgqz.zzajk();
    }

    public final void onVideoPause() {
        this.zzgrk.onVideoPause();
    }

    public void zzuc() {
        this.zzgrk.onVideoStart();
    }

    public void onVideoEnd() {
        this.zzgrk.onVideoEnd();
    }

    public final void onVideoPlay() throws RemoteException {
        this.zzgrk.onVideoPlay();
    }

    @Deprecated
    public final void zzdd(int i) throws RemoteException {
        zze(new zzva(i, "", AdError.UNDEFINED_DOMAIN, (zzva) null, (IBinder) null));
    }

    public final void zzdo(String str) {
        zze(new zzva(0, str, AdError.UNDEFINED_DOMAIN, (zzva) null, (IBinder) null));
    }

    public final void zze(zzva zzva) {
        this.zzfwv.zzl(zzdpe.zza(zzdpg.MEDIATION_SHOW_ERROR, zzva));
    }
}
