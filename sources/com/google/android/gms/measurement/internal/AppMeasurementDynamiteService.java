package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzab;
import com.google.android.gms.internal.measurement.zzac;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.internal.measurement.zzmj;
import com.google.android.gms.internal.measurement.zzu;
import com.google.android.gms.internal.measurement.zzw;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@17.6.0 */
public class AppMeasurementDynamiteService extends zzu {
    zzfv zza = null;
    private Map<Integer, zzgw> zzb = new ArrayMap();

    /* compiled from: com.google.android.gms:play-services-measurement-sdk@@17.6.0 */
    class zza implements zzgw {
        private zzab zza;

        zza(zzab zzab) {
            this.zza = zzab;
        }

        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zza.zza(str, str2, bundle, j);
            } catch (RemoteException e) {
                AppMeasurementDynamiteService.this.zza.zzq().zzh().zza("Event listener threw exception", e);
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk@@17.6.0 */
    class zzb implements zzgx {
        private zzab zza;

        zzb(zzab zzab) {
            this.zza = zzab;
        }

        public final void interceptEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zza.zza(str, str2, bundle, j);
            } catch (RemoteException e) {
                AppMeasurementDynamiteService.this.zza.zzq().zzh().zza("Event interceptor threw exception", e);
            }
        }
    }

    private final void zza() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    public void initialize(IObjectWrapper iObjectWrapper, zzae zzae, long j) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzfv zzfv = this.zza;
        if (zzfv == null) {
            this.zza = zzfv.zza(context, zzae, Long.valueOf(j));
        } else {
            zzfv.zzq().zzh().zza("Attempting to initialize multiple times");
        }
    }

    public void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        zza();
        this.zza.zzg().zza(str, str2, bundle, z, z2, j);
    }

    public void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        zza();
        this.zza.zzg().zza(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j);
    }

    public void setUserId(String str, long j) throws RemoteException {
        zza();
        this.zza.zzg().zza((String) null, "_id", (Object) str, true, j);
    }

    public void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        zza();
        this.zza.zzu().zza((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    public void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        zza();
        this.zza.zzg().zza(Boolean.valueOf(z));
    }

    public void clearMeasurementEnabled(long j) throws RemoteException {
        zza();
        this.zza.zzg().zza((Boolean) null);
    }

    public void setConsent(Bundle bundle, long j) throws RemoteException {
        zza();
        zzgy zzg = this.zza.zzg();
        if (zzmj.zzb() && zzg.zzs().zzd((String) null, zzat.zzcg)) {
            zzg.zza(bundle, 30, j);
        }
    }

    public void setConsentThirdParty(Bundle bundle, long j) throws RemoteException {
        zza();
        zzgy zzg = this.zza.zzg();
        if (zzmj.zzb() && zzg.zzs().zzd((String) null, zzat.zzch)) {
            zzg.zza(bundle, 10, j);
        }
    }

    public void resetAnalyticsData(long j) throws RemoteException {
        zza();
        zzgy zzg = this.zza.zzg();
        zzg.zza((String) null);
        zzg.zzp().zza((Runnable) new zzhi(zzg, j));
    }

    public void setMinimumSessionDuration(long j) throws RemoteException {
        zza();
        zzgy zzg = this.zza.zzg();
        zzg.zzp().zza((Runnable) new zzhf(zzg, j));
    }

    public void setSessionTimeoutDuration(long j) throws RemoteException {
        zza();
        zzgy zzg = this.zza.zzg();
        zzg.zzp().zza((Runnable) new zzhe(zzg, j));
    }

    public void getMaxUserProperties(String str, zzw zzw) throws RemoteException {
        zza();
        this.zza.zzg();
        Preconditions.checkNotEmpty(str);
        this.zza.zzh().zza(zzw, 25);
    }

    public void getCurrentScreenName(zzw zzw) throws RemoteException {
        zza();
        zza(zzw, this.zza.zzg().zzai());
    }

    public void getCurrentScreenClass(zzw zzw) throws RemoteException {
        zza();
        zza(zzw, this.zza.zzg().zzaj());
    }

    public void getCachedAppInstanceId(zzw zzw) throws RemoteException {
        zza();
        zza(zzw, this.zza.zzg().zzag());
    }

    public void getAppInstanceId(zzw zzw) throws RemoteException {
        zza();
        this.zza.zzp().zza((Runnable) new zzh(this, zzw));
    }

    public void getGmpAppId(zzw zzw) throws RemoteException {
        zza();
        zza(zzw, this.zza.zzg().zzak());
    }

    public void generateEventId(zzw zzw) throws RemoteException {
        zza();
        this.zza.zzh().zza(zzw, this.zza.zzh().zzf());
    }

    public void beginAdUnitExposure(String str, long j) throws RemoteException {
        zza();
        this.zza.zzy().zza(str, j);
    }

    public void endAdUnitExposure(String str, long j) throws RemoteException {
        zza();
        this.zza.zzy().zzb(str, j);
    }

    public void initForTests(Map map) throws RemoteException {
        zza();
    }

    public void logEventAndBundle(String str, String str2, Bundle bundle, zzw zzw, long j) throws RemoteException {
        Bundle bundle2;
        zza();
        Preconditions.checkNotEmpty(str2);
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("_o", AdParameterKeys.SECTION_ID);
        this.zza.zzp().zza((Runnable) new zzj(this, zzw, new zzar(str2, new zzam(bundle), AdParameterKeys.SECTION_ID, j), str));
    }

    public void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhz zzhz = this.zza.zzg().zza;
        if (zzhz != null) {
            this.zza.zzg().zzaa();
            zzhz.onActivityStarted((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhz zzhz = this.zza.zzg().zza;
        if (zzhz != null) {
            this.zza.zzg().zzaa();
            zzhz.onActivityStopped((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        zza();
        zzhz zzhz = this.zza.zzg().zza;
        if (zzhz != null) {
            this.zza.zzg().zzaa();
            zzhz.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    public void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhz zzhz = this.zza.zzg().zza;
        if (zzhz != null) {
            this.zza.zzg().zzaa();
            zzhz.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhz zzhz = this.zza.zzg().zza;
        if (zzhz != null) {
            this.zza.zzg().zzaa();
            zzhz.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        zzhz zzhz = this.zza.zzg().zza;
        if (zzhz != null) {
            this.zza.zzg().zzaa();
            zzhz.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzw zzw, long j) throws RemoteException {
        zza();
        zzhz zzhz = this.zza.zzg().zza;
        Bundle bundle = new Bundle();
        if (zzhz != null) {
            this.zza.zzg().zzaa();
            zzhz.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzw.zza(bundle);
        } catch (RemoteException e) {
            this.zza.zzq().zzh().zza("Error returning bundle value to wrapper", e);
        }
    }

    public void performAction(Bundle bundle, zzw zzw, long j) throws RemoteException {
        zza();
        zzw.zza((Bundle) null);
    }

    public void getUserProperties(String str, String str2, boolean z, zzw zzw) throws RemoteException {
        zza();
        this.zza.zzp().zza((Runnable) new zzi(this, zzw, str, str2, z));
    }

    public void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Object obj;
        Object obj2;
        zza();
        Object obj3 = null;
        if (iObjectWrapper == null) {
            obj = null;
        } else {
            obj = ObjectWrapper.unwrap(iObjectWrapper);
        }
        if (iObjectWrapper2 == null) {
            obj2 = null;
        } else {
            obj2 = ObjectWrapper.unwrap(iObjectWrapper2);
        }
        if (iObjectWrapper3 != null) {
            obj3 = ObjectWrapper.unwrap(iObjectWrapper3);
        }
        this.zza.zzq().zza(i, true, false, str, obj, obj2, obj3);
    }

    public void setEventInterceptor(zzab zzab) throws RemoteException {
        zza();
        zzgy zzg = this.zza.zzg();
        zzb zzb2 = new zzb(zzab);
        zzg.zzv();
        zzg.zzp().zza((Runnable) new zzhl(zzg, zzb2));
    }

    public void registerOnMeasurementEventListener(zzab zzab) throws RemoteException {
        zza();
        zzgw zzgw = this.zzb.get(Integer.valueOf(zzab.zza()));
        if (zzgw == null) {
            zzgw = new zza(zzab);
            this.zzb.put(Integer.valueOf(zzab.zza()), zzgw);
        }
        this.zza.zzg().zza(zzgw);
    }

    public void unregisterOnMeasurementEventListener(zzab zzab) throws RemoteException {
        zza();
        zzgw remove = this.zzb.remove(Integer.valueOf(zzab.zza()));
        if (remove == null) {
            remove = new zza(zzab);
        }
        this.zza.zzg().zzb(remove);
    }

    public void setInstanceIdProvider(zzac zzac) throws RemoteException {
        zza();
    }

    public void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        zza();
        if (bundle == null) {
            this.zza.zzq().zze().zza("Conditional user property must not be null");
        } else {
            this.zza.zzg().zza(bundle, j);
        }
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        zza();
        this.zza.zzg().zzc(str, str2, bundle);
    }

    public void getConditionalUserProperties(String str, String str2, zzw zzw) throws RemoteException {
        zza();
        this.zza.zzp().zza((Runnable) new zzl(this, zzw, str, str2));
    }

    public void getTestFlag(zzw zzw, int i) throws RemoteException {
        zza();
        if (i == 0) {
            this.zza.zzh().zza(zzw, this.zza.zzg().zzac());
        } else if (i == 1) {
            this.zza.zzh().zza(zzw, this.zza.zzg().zzad().longValue());
        } else if (i == 2) {
            zzkw zzh = this.zza.zzh();
            double doubleValue = this.zza.zzg().zzaf().doubleValue();
            Bundle bundle = new Bundle();
            bundle.putDouble("r", doubleValue);
            try {
                zzw.zza(bundle);
            } catch (RemoteException e) {
                zzh.zzy.zzq().zzh().zza("Error returning double value to wrapper", e);
            }
        } else if (i == 3) {
            this.zza.zzh().zza(zzw, this.zza.zzg().zzae().intValue());
        } else if (i == 4) {
            this.zza.zzh().zza(zzw, this.zza.zzg().zzab().booleanValue());
        }
    }

    private final void zza(zzw zzw, String str) {
        this.zza.zzh().zza(zzw, str);
    }

    public void setDataCollectionEnabled(boolean z) throws RemoteException {
        zza();
        zzgy zzg = this.zza.zzg();
        zzg.zzv();
        zzg.zzp().zza((Runnable) new zzhw(zzg, z));
    }

    public void isDataCollectionEnabled(zzw zzw) throws RemoteException {
        zza();
        this.zza.zzp().zza((Runnable) new zzk(this, zzw));
    }

    public void setDefaultEventParameters(Bundle bundle) {
        Bundle bundle2;
        zza();
        zzgy zzg = this.zza.zzg();
        if (bundle == null) {
            bundle2 = null;
        } else {
            bundle2 = new Bundle(bundle);
        }
        zzg.zzp().zza((Runnable) new zzhb(zzg, bundle2));
    }
}
