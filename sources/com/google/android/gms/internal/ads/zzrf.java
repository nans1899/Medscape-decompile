package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzrf implements Application.ActivityLifecycleCallbacks {
    /* access modifiers changed from: private */
    public boolean foreground = true;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private Activity zzaas;
    /* access modifiers changed from: private */
    public boolean zzbsu = false;
    /* access modifiers changed from: private */
    public final List<zzrh> zzbsv = new ArrayList();
    private final List<zzrw> zzbsw = new ArrayList();
    private Runnable zzbsx;
    private long zzbsy;
    private Context zzvr;
    private boolean zzzh = false;

    zzrf() {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void zza(Application application, Context context) {
        if (!this.zzzh) {
            application.registerActivityLifecycleCallbacks(this);
            if (context instanceof Activity) {
                setActivity((Activity) context);
            }
            this.zzvr = application;
            this.zzbsy = ((Long) zzwm.zzpx().zzd(zzabb.zzcoo)).longValue();
            this.zzzh = true;
        }
    }

    public final void zza(zzrh zzrh) {
        synchronized (this.lock) {
            this.zzbsv.add(zzrh);
        }
    }

    public final void zzb(zzrh zzrh) {
        synchronized (this.lock) {
            this.zzbsv.remove(zzrh);
        }
    }

    public final Activity getActivity() {
        return this.zzaas;
    }

    public final Context getContext() {
        return this.zzvr;
    }

    public final void onActivityStarted(Activity activity) {
        setActivity(activity);
    }

    public final void onActivityResumed(Activity activity) {
        setActivity(activity);
        this.zzbsu = false;
        boolean z = !this.foreground;
        this.foreground = true;
        if (this.zzbsx != null) {
            zzayu.zzeba.removeCallbacks(this.zzbsx);
        }
        synchronized (this.lock) {
            for (zzrw onActivityResumed : this.zzbsw) {
                try {
                    onActivityResumed.onActivityResumed(activity);
                } catch (Exception e) {
                    zzp.zzkv().zza(e, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzbbq.zzc("", e);
                }
            }
            if (z) {
                for (zzrh zzp : this.zzbsv) {
                    try {
                        zzp.zzp(true);
                    } catch (Exception e2) {
                        zzbbq.zzc("", e2);
                    }
                }
            } else {
                zzayp.zzef("App is still foreground.");
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        setActivity(activity);
        synchronized (this.lock) {
            for (zzrw onActivityPaused : this.zzbsw) {
                try {
                    onActivityPaused.onActivityPaused(activity);
                } catch (Exception e) {
                    zzp.zzkv().zza(e, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzbbq.zzc("", e);
                }
            }
        }
        this.zzbsu = true;
        if (this.zzbsx != null) {
            zzayu.zzeba.removeCallbacks(this.zzbsx);
        }
        zzdvo zzdvo = zzayu.zzeba;
        zzri zzri = new zzri(this);
        this.zzbsx = zzri;
        zzdvo.postDelayed(zzri, this.zzbsy);
    }

    public final void onActivityDestroyed(Activity activity) {
        synchronized (this.lock) {
            if (this.zzaas != null) {
                if (this.zzaas.equals(activity)) {
                    this.zzaas = null;
                }
                Iterator<zzrw> it = this.zzbsw.iterator();
                while (it.hasNext()) {
                    try {
                        if (it.next().zza(activity)) {
                            it.remove();
                        }
                    } catch (Exception e) {
                        zzp.zzkv().zza(e, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                        zzbbq.zzc("", e);
                    }
                }
            }
        }
    }

    private final void setActivity(Activity activity) {
        synchronized (this.lock) {
            if (!activity.getClass().getName().startsWith(MobileAds.ERROR_DOMAIN)) {
                this.zzaas = activity;
            }
        }
    }
}
