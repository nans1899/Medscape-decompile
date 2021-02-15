package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzamt {
    private static zzamt zzdjg;
    private AtomicBoolean zzdjh = new AtomicBoolean(false);

    public static zzamt zztr() {
        if (zzdjg == null) {
            zzdjg = new zzamt();
        }
        return zzdjg;
    }

    zzamt() {
    }

    public final Thread zzd(Context context, String str) {
        if (!this.zzdjh.compareAndSet(false, true)) {
            return null;
        }
        Thread thread = new Thread(new zzamw(this, context, str));
        thread.start();
        return thread;
    }

    public final Thread zzm(Context context) {
        if (!this.zzdjh.compareAndSet(false, true)) {
            return null;
        }
        Thread thread = new Thread(new zzamv(this, context));
        thread.start();
        return thread;
    }

    private static void zza(Context context, AppMeasurementSdk appMeasurementSdk) {
        try {
            ((zzbis) zzbbt.zza(context, "com.google.android.gms.ads.measurement.DynamiteMeasurementManager", zzamy.zzbxx)).zza(ObjectWrapper.wrap(context), new zzamu(appMeasurementSdk));
        } catch (RemoteException | zzbbv | NullPointerException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    private static boolean zzn(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    static /* synthetic */ void zzo(Context context) {
        zzabb.initialize(context);
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcnx)).booleanValue() && zzn(context)) {
            zza(context, AppMeasurementSdk.getInstance(context));
        }
    }

    static /* synthetic */ void zze(Context context, String str) {
        zzabb.initialize(context);
        Bundle bundle = new Bundle();
        bundle.putBoolean("measurementEnabled", ((Boolean) zzwm.zzpx().zzd(zzabb.zzcns)).booleanValue());
        zza(context, AppMeasurementSdk.getInstance(context, "FA-Ads", "am", str, bundle));
    }
}
