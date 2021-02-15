package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.appopen.AppOpenAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzsr {
    private final int orientation;
    private final zzvl zzacs;
    private final zzyw zzacu;
    private zzxc zzbut;
    private final String zzbuu;
    private final AppOpenAd.AppOpenAdLoadCallback zzbuv;
    private final zzanc zzbuw = new zzanc();
    private final Context zzvr;

    public zzsr(Context context, String str, zzyw zzyw, int i, AppOpenAd.AppOpenAdLoadCallback appOpenAdLoadCallback) {
        this.zzvr = context;
        this.zzbuu = str;
        this.zzacu = zzyw;
        this.orientation = i;
        this.zzbuv = appOpenAdLoadCallback;
        this.zzacs = zzvl.zzchs;
    }

    public final void zzmu() {
        try {
            this.zzbut = zzwm.zzpu().zza(this.zzvr, zzvn.zzpm(), this.zzbuu, this.zzbuw);
            this.zzbut.zza(new zzvs(this.orientation));
            this.zzbut.zza((zzsh) new zzsb(this.zzbuv));
            this.zzbut.zza(zzvl.zza(this.zzvr, this.zzacu));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }
}
