package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class zzzd {
    private static zzzd zzckj;
    private final Object lock = new Object();
    /* access modifiers changed from: private */
    public ArrayList<OnInitializationCompleteListener> zzcki = new ArrayList<>();
    private zzxs zzckk;
    /* access modifiers changed from: private */
    public boolean zzckl = false;
    private RewardedVideoAd zzckm;
    private RequestConfiguration zzckn = new RequestConfiguration.Builder().build();
    private InitializationStatus zzcko;
    /* access modifiers changed from: private */
    public boolean zzzh = false;

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    class zza extends zzaiw {
        private zza() {
        }

        public final void zze(List<zzaiq> list) throws RemoteException {
            int i = 0;
            boolean unused = zzzd.this.zzckl = false;
            boolean unused2 = zzzd.this.zzzh = true;
            InitializationStatus zza = zzzd.zzd(list);
            ArrayList zza2 = zzzd.zzqw().zzcki;
            int size = zza2.size();
            while (i < size) {
                Object obj = zza2.get(i);
                i++;
                ((OnInitializationCompleteListener) obj).onInitializationComplete(zza);
            }
            zzzd.zzqw().zzcki.clear();
        }

        /* synthetic */ zza(zzzd zzzd, zzzg zzzg) {
            this();
        }
    }

    private zzzd() {
    }

    public static zzzd zzqw() {
        zzzd zzzd;
        synchronized (zzzd.class) {
            if (zzckj == null) {
                zzckj = new zzzd();
            }
            zzzd = zzckj;
        }
        return zzzd;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.content.Context r5, java.lang.String r6, com.google.android.gms.ads.initialization.OnInitializationCompleteListener r7) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.lock
            monitor-enter(r0)
            boolean r1 = r4.zzckl     // Catch:{ all -> 0x00c7 }
            if (r1 == 0) goto L_0x0014
            if (r7 == 0) goto L_0x0012
            com.google.android.gms.internal.ads.zzzd r5 = zzqw()     // Catch:{ all -> 0x00c7 }
            java.util.ArrayList<com.google.android.gms.ads.initialization.OnInitializationCompleteListener> r5 = r5.zzcki     // Catch:{ all -> 0x00c7 }
            r5.add(r7)     // Catch:{ all -> 0x00c7 }
        L_0x0012:
            monitor-exit(r0)     // Catch:{ all -> 0x00c7 }
            return
        L_0x0014:
            boolean r1 = r4.zzzh     // Catch:{ all -> 0x00c7 }
            if (r1 == 0) goto L_0x0023
            if (r7 == 0) goto L_0x0021
            com.google.android.gms.ads.initialization.InitializationStatus r5 = r4.getInitializationStatus()     // Catch:{ all -> 0x00c7 }
            r7.onInitializationComplete(r5)     // Catch:{ all -> 0x00c7 }
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x00c7 }
            return
        L_0x0023:
            r1 = 1
            r4.zzckl = r1     // Catch:{ all -> 0x00c7 }
            if (r7 == 0) goto L_0x0031
            com.google.android.gms.internal.ads.zzzd r1 = zzqw()     // Catch:{ all -> 0x00c7 }
            java.util.ArrayList<com.google.android.gms.ads.initialization.OnInitializationCompleteListener> r1 = r1.zzcki     // Catch:{ all -> 0x00c7 }
            r1.add(r7)     // Catch:{ all -> 0x00c7 }
        L_0x0031:
            if (r5 == 0) goto L_0x00bf
            com.google.android.gms.internal.ads.zzamt r1 = com.google.android.gms.internal.ads.zzamt.zztr()     // Catch:{ RemoteException -> 0x00b7 }
            r1.zzd(r5, r6)     // Catch:{ RemoteException -> 0x00b7 }
            r4.zzg(r5)     // Catch:{ RemoteException -> 0x00b7 }
            if (r7 == 0) goto L_0x004a
            com.google.android.gms.internal.ads.zzxs r1 = r4.zzckk     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.internal.ads.zzzd$zza r2 = new com.google.android.gms.internal.ads.zzzd$zza     // Catch:{ RemoteException -> 0x00b7 }
            r3 = 0
            r2.<init>(r4, r3)     // Catch:{ RemoteException -> 0x00b7 }
            r1.zza((com.google.android.gms.internal.ads.zzait) r2)     // Catch:{ RemoteException -> 0x00b7 }
        L_0x004a:
            com.google.android.gms.internal.ads.zzxs r1 = r4.zzckk     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.internal.ads.zzanc r2 = new com.google.android.gms.internal.ads.zzanc     // Catch:{ RemoteException -> 0x00b7 }
            r2.<init>()     // Catch:{ RemoteException -> 0x00b7 }
            r1.zza((com.google.android.gms.internal.ads.zzanb) r2)     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.internal.ads.zzxs r1 = r4.zzckk     // Catch:{ RemoteException -> 0x00b7 }
            r1.initialize()     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.internal.ads.zzxs r1 = r4.zzckk     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.internal.ads.zzzc r2 = new com.google.android.gms.internal.ads.zzzc     // Catch:{ RemoteException -> 0x00b7 }
            r2.<init>(r4, r5)     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r2)     // Catch:{ RemoteException -> 0x00b7 }
            r1.zza(r6, r2)     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.ads.RequestConfiguration r6 = r4.zzckn     // Catch:{ RemoteException -> 0x00b7 }
            int r6 = r6.getTagForChildDirectedTreatment()     // Catch:{ RemoteException -> 0x00b7 }
            r1 = -1
            if (r6 != r1) goto L_0x0078
            com.google.android.gms.ads.RequestConfiguration r6 = r4.zzckn     // Catch:{ RemoteException -> 0x00b7 }
            int r6 = r6.getTagForUnderAgeOfConsent()     // Catch:{ RemoteException -> 0x00b7 }
            if (r6 == r1) goto L_0x007d
        L_0x0078:
            com.google.android.gms.ads.RequestConfiguration r6 = r4.zzckn     // Catch:{ RemoteException -> 0x00b7 }
            r4.zza((com.google.android.gms.ads.RequestConfiguration) r6)     // Catch:{ RemoteException -> 0x00b7 }
        L_0x007d:
            com.google.android.gms.internal.ads.zzabb.initialize(r5)     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r5 = com.google.android.gms.internal.ads.zzabb.zzcuq     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.internal.ads.zzaax r6 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ RemoteException -> 0x00b7 }
            java.lang.Object r5 = r6.zzd(r5)     // Catch:{ RemoteException -> 0x00b7 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ RemoteException -> 0x00b7 }
            boolean r5 = r5.booleanValue()     // Catch:{ RemoteException -> 0x00b7 }
            if (r5 != 0) goto L_0x00bd
            java.lang.String r5 = r4.getVersionString()     // Catch:{ RemoteException -> 0x00b7 }
            java.lang.String r6 = "0"
            boolean r5 = r5.endsWith(r6)     // Catch:{ RemoteException -> 0x00b7 }
            if (r5 != 0) goto L_0x00bd
            java.lang.String r5 = "Google Mobile Ads SDK initialization functionality unavailable for this session. Ad requests can be made at any time."
            com.google.android.gms.internal.ads.zzbbq.zzfc(r5)     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.internal.ads.zzze r5 = new com.google.android.gms.internal.ads.zzze     // Catch:{ RemoteException -> 0x00b7 }
            r5.<init>(r4)     // Catch:{ RemoteException -> 0x00b7 }
            r4.zzcko = r5     // Catch:{ RemoteException -> 0x00b7 }
            if (r7 == 0) goto L_0x00bd
            android.os.Handler r5 = com.google.android.gms.internal.ads.zzbbg.zzaah     // Catch:{ RemoteException -> 0x00b7 }
            com.google.android.gms.internal.ads.zzzf r6 = new com.google.android.gms.internal.ads.zzzf     // Catch:{ RemoteException -> 0x00b7 }
            r6.<init>(r4, r7)     // Catch:{ RemoteException -> 0x00b7 }
            r5.post(r6)     // Catch:{ RemoteException -> 0x00b7 }
            goto L_0x00bd
        L_0x00b7:
            r5 = move-exception
            java.lang.String r6 = "MobileAdsSettingManager initialization failed"
            com.google.android.gms.internal.ads.zzbbq.zzd(r6, r5)     // Catch:{ all -> 0x00c7 }
        L_0x00bd:
            monitor-exit(r0)     // Catch:{ all -> 0x00c7 }
            return
        L_0x00bf:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00c7 }
            java.lang.String r6 = "Context cannot be null."
            r5.<init>(r6)     // Catch:{ all -> 0x00c7 }
            throw r5     // Catch:{ all -> 0x00c7 }
        L_0x00c7:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c7 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzzd.zza(android.content.Context, java.lang.String, com.google.android.gms.ads.initialization.OnInitializationCompleteListener):void");
    }

    public final RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        synchronized (this.lock) {
            if (this.zzckm != null) {
                RewardedVideoAd rewardedVideoAd = this.zzckm;
                return rewardedVideoAd;
            }
            zzauo zzauo = new zzauo(context, (zzaud) new zzwk(zzwm.zzpu(), context, new zzanc()).zzd(context, false));
            this.zzckm = zzauo;
            return zzauo;
        }
    }

    public final void setAppVolume(float f) {
        boolean z = true;
        Preconditions.checkArgument(0.0f <= f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        synchronized (this.lock) {
            if (this.zzckk == null) {
                z = false;
            }
            Preconditions.checkState(z, "MobileAds.initialize() must be called prior to setting the app volume.");
            try {
                this.zzckk.setAppVolume(f);
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to set app volume.", e);
            }
        }
    }

    public final float zzqg() {
        float f;
        synchronized (this.lock) {
            f = 1.0f;
            if (this.zzckk == null) {
                return 1.0f;
            }
            try {
                f = this.zzckk.zzqg();
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to get app volume.", e);
            }
        }
        return f;
    }

    public final void setAppMuted(boolean z) {
        synchronized (this.lock) {
            Preconditions.checkState(this.zzckk != null, "MobileAds.initialize() must be called prior to setting app muted state.");
            try {
                this.zzckk.setAppMuted(z);
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to set app mute state.", e);
            }
        }
    }

    public final boolean zzqh() {
        boolean z;
        synchronized (this.lock) {
            z = false;
            if (this.zzckk == null) {
                return false;
            }
            try {
                z = this.zzckk.zzqh();
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to get app mute state.", e);
            }
        }
        return z;
    }

    public final void openDebugMenu(Context context, String str) {
        synchronized (this.lock) {
            Preconditions.checkState(this.zzckk != null, "MobileAds.initialize() must be called prior to opening debug menu.");
            try {
                this.zzckk.zzb(ObjectWrapper.wrap(context), str);
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to open debug menu.", e);
            }
        }
    }

    public final String getVersionString() {
        String zzhi;
        synchronized (this.lock) {
            Preconditions.checkState(this.zzckk != null, "MobileAds.initialize() must be called prior to getting version string.");
            try {
                zzhi = zzdwf.zzhi(this.zzckk.getVersionString());
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to get version string.", e);
                return "";
            }
        }
        return zzhi;
    }

    public final void registerRtbAdapter(Class<? extends RtbAdapter> cls) {
        synchronized (this.lock) {
            try {
                this.zzckk.zzci(cls.getCanonicalName());
            } catch (RemoteException e) {
                zzbbq.zzc("Unable to register RtbAdapter", e);
            }
        }
    }

    public final InitializationStatus getInitializationStatus() {
        synchronized (this.lock) {
            Preconditions.checkState(this.zzckk != null, "MobileAds.initialize() must be called prior to getting initialization status.");
            try {
                if (this.zzcko != null) {
                    InitializationStatus initializationStatus = this.zzcko;
                    return initializationStatus;
                }
                InitializationStatus zzd = zzd(this.zzckk.zzqi());
                return zzd;
            } catch (RemoteException unused) {
                zzbbq.zzfc("Unable to get Initialization status.");
                return null;
            }
        }
    }

    public final void disableMediationAdapterInitialization(Context context) {
        synchronized (this.lock) {
            zzg(context);
            try {
                this.zzckk.zzqj();
            } catch (RemoteException unused) {
                zzbbq.zzfc("Unable to disable mediation adapter initialization.");
            }
        }
    }

    /* access modifiers changed from: private */
    public static InitializationStatus zzd(List<zzaiq> list) {
        HashMap hashMap = new HashMap();
        for (zzaiq next : list) {
            hashMap.put(next.zzdfz, new zzaiy(next.zzdga ? AdapterStatus.State.READY : AdapterStatus.State.NOT_READY, next.description, next.zzdgb));
        }
        return new zzaix(hashMap);
    }

    public final RequestConfiguration getRequestConfiguration() {
        return this.zzckn;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setRequestConfiguration(com.google.android.gms.ads.RequestConfiguration r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0004
            r0 = 1
            goto L_0x0005
        L_0x0004:
            r0 = 0
        L_0x0005:
            java.lang.String r1 = "Null passed to setRequestConfiguration."
            com.google.android.gms.common.internal.Preconditions.checkArgument(r0, r1)
            java.lang.Object r0 = r4.lock
            monitor-enter(r0)
            com.google.android.gms.ads.RequestConfiguration r1 = r4.zzckn     // Catch:{ all -> 0x0030 }
            r4.zzckn = r5     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.zzxs r2 = r4.zzckk     // Catch:{ all -> 0x0030 }
            if (r2 != 0) goto L_0x0017
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x0017:
            int r2 = r1.getTagForChildDirectedTreatment()     // Catch:{ all -> 0x0030 }
            int r3 = r5.getTagForChildDirectedTreatment()     // Catch:{ all -> 0x0030 }
            if (r2 != r3) goto L_0x002b
            int r1 = r1.getTagForUnderAgeOfConsent()     // Catch:{ all -> 0x0030 }
            int r2 = r5.getTagForUnderAgeOfConsent()     // Catch:{ all -> 0x0030 }
            if (r1 == r2) goto L_0x002e
        L_0x002b:
            r4.zza((com.google.android.gms.ads.RequestConfiguration) r5)     // Catch:{ all -> 0x0030 }
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x0030:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzzd.setRequestConfiguration(com.google.android.gms.ads.RequestConfiguration):void");
    }

    private final void zza(RequestConfiguration requestConfiguration) {
        try {
            this.zzckk.zza(new zzaae(requestConfiguration));
        } catch (RemoteException e) {
            zzbbq.zzc("Unable to set request configuration parcel.", e);
        }
    }

    private final void zzg(Context context) {
        if (this.zzckk == null) {
            this.zzckk = (zzxs) new zzwj(zzwm.zzpu(), context).zzd(context, false);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(OnInitializationCompleteListener onInitializationCompleteListener) {
        onInitializationCompleteListener.onInitializationComplete(this.zzcko);
    }
}
