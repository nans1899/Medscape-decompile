package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzfv;
import com.google.android.gms.measurement.internal.zzgs;
import com.google.android.gms.measurement.internal.zzgt;
import com.google.android.gms.measurement.internal.zzgu;
import com.google.android.gms.measurement.internal.zzgv;
import com.google.android.gms.measurement.internal.zzgw;
import com.google.android.gms.measurement.internal.zzgx;
import com.google.android.gms.measurement.internal.zzib;
import com.google.android.gms.measurement.internal.zzih;
import com.google.android.gms.measurement.internal.zzkr;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Deprecated
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public class AppMeasurement {
    public static final String CRASH_ORIGIN = "crash";
    public static final String FCM_ORIGIN = "fcm";
    public static final String FIAM_ORIGIN = "fiam";
    private static volatile AppMeasurement zza;
    private final zzfv zzb;
    private final zzib zzc;
    private final boolean zzd;

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
    public static class ConditionalUserProperty {
        public boolean mActive;
        public String mAppId;
        public long mCreationTimestamp;
        public String mExpiredEventName;
        public Bundle mExpiredEventParams;
        public String mName;
        public String mOrigin;
        public long mTimeToLive;
        public String mTimedOutEventName;
        public Bundle mTimedOutEventParams;
        public String mTriggerEventName;
        public long mTriggerTimeout;
        public String mTriggeredEventName;
        public Bundle mTriggeredEventParams;
        public long mTriggeredTimestamp;
        public Object mValue;

        public ConditionalUserProperty() {
        }

        public ConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
            Preconditions.checkNotNull(conditionalUserProperty);
            this.mAppId = conditionalUserProperty.mAppId;
            this.mOrigin = conditionalUserProperty.mOrigin;
            this.mCreationTimestamp = conditionalUserProperty.mCreationTimestamp;
            this.mName = conditionalUserProperty.mName;
            Object obj = conditionalUserProperty.mValue;
            if (obj != null) {
                Object zza = zzih.zza(obj);
                this.mValue = zza;
                if (zza == null) {
                    this.mValue = conditionalUserProperty.mValue;
                }
            }
            this.mActive = conditionalUserProperty.mActive;
            this.mTriggerEventName = conditionalUserProperty.mTriggerEventName;
            this.mTriggerTimeout = conditionalUserProperty.mTriggerTimeout;
            this.mTimedOutEventName = conditionalUserProperty.mTimedOutEventName;
            if (conditionalUserProperty.mTimedOutEventParams != null) {
                this.mTimedOutEventParams = new Bundle(conditionalUserProperty.mTimedOutEventParams);
            }
            this.mTriggeredEventName = conditionalUserProperty.mTriggeredEventName;
            if (conditionalUserProperty.mTriggeredEventParams != null) {
                this.mTriggeredEventParams = new Bundle(conditionalUserProperty.mTriggeredEventParams);
            }
            this.mTriggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
            this.mTimeToLive = conditionalUserProperty.mTimeToLive;
            this.mExpiredEventName = conditionalUserProperty.mExpiredEventName;
            if (conditionalUserProperty.mExpiredEventParams != null) {
                this.mExpiredEventParams = new Bundle(conditionalUserProperty.mExpiredEventParams);
            }
        }

        ConditionalUserProperty(Bundle bundle) {
            Preconditions.checkNotNull(bundle);
            this.mAppId = (String) zzgt.zza(bundle, "app_id", String.class, null);
            this.mOrigin = (String) zzgt.zza(bundle, "origin", String.class, null);
            this.mName = (String) zzgt.zza(bundle, "name", String.class, null);
            this.mValue = zzgt.zza(bundle, "value", Object.class, null);
            this.mTriggerEventName = (String) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
            this.mTriggerTimeout = ((Long) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L)).longValue();
            this.mTimedOutEventName = (String) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
            this.mTimedOutEventParams = (Bundle) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
            this.mTriggeredEventName = (String) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
            this.mTriggeredEventParams = (Bundle) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
            this.mTimeToLive = ((Long) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L)).longValue();
            this.mExpiredEventName = (String) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
            this.mExpiredEventParams = (Bundle) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
            this.mActive = ((Boolean) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.class, false)).booleanValue();
            this.mCreationTimestamp = ((Long) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.class, 0L)).longValue();
            this.mTriggeredTimestamp = ((Long) zzgt.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.class, 0L)).longValue();
        }

        /* access modifiers changed from: package-private */
        public final Bundle zza() {
            Bundle bundle = new Bundle();
            String str = this.mAppId;
            if (str != null) {
                bundle.putString("app_id", str);
            }
            String str2 = this.mOrigin;
            if (str2 != null) {
                bundle.putString("origin", str2);
            }
            String str3 = this.mName;
            if (str3 != null) {
                bundle.putString("name", str3);
            }
            Object obj = this.mValue;
            if (obj != null) {
                zzgt.zza(bundle, obj);
            }
            String str4 = this.mTriggerEventName;
            if (str4 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str4);
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, this.mTriggerTimeout);
            String str5 = this.mTimedOutEventName;
            if (str5 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, str5);
            }
            Bundle bundle2 = this.mTimedOutEventParams;
            if (bundle2 != null) {
                bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, bundle2);
            }
            String str6 = this.mTriggeredEventName;
            if (str6 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, str6);
            }
            Bundle bundle3 = this.mTriggeredEventParams;
            if (bundle3 != null) {
                bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, bundle3);
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, this.mTimeToLive);
            String str7 = this.mExpiredEventName;
            if (str7 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str7);
            }
            Bundle bundle4 = this.mExpiredEventParams;
            if (bundle4 != null) {
                bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle4);
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, this.mCreationTimestamp);
            bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, this.mActive);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, this.mTriggeredTimestamp);
            return bundle;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
    public static final class Event extends zzgs {
        public static final String AD_REWARD = "_ar";
        public static final String APP_EXCEPTION = "_ae";

        private Event() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
    public interface EventInterceptor extends zzgx {
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
    public interface OnEventListener extends zzgw {
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
    public static final class Param extends zzgv {
        public static final String FATAL = "fatal";
        public static final String TIMESTAMP = "timestamp";
        public static final String TYPE = "type";

        private Param() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
    public static final class UserProperty extends zzgu {
        public static final String FIREBASE_LAST_NOTIFICATION = "_ln";

        private UserProperty() {
        }
    }

    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return zza(context, (String) null, (String) null);
    }

    private static AppMeasurement zza(Context context, String str, String str2) {
        if (zza == null) {
            synchronized (AppMeasurement.class) {
                if (zza == null) {
                    zzib zza2 = zza(context, (Bundle) null);
                    if (zza2 != null) {
                        zza = new AppMeasurement(zza2);
                    } else {
                        zza = new AppMeasurement(zzfv.zza(context, new zzae(0, 0, true, (String) null, (String) null, (String) null, (Bundle) null), (Long) null));
                    }
                }
            }
        }
        return zza;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.measurement.internal.zzib zza(android.content.Context r7, android.os.Bundle r8) {
        /*
            r8 = 0
            java.lang.String r0 = "com.google.firebase.analytics.FirebaseAnalytics"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0027 }
            java.lang.String r1 = "getScionFrontendApiImplementation"
            r2 = 2
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{  }
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            r5 = 0
            r3[r5] = r4     // Catch:{  }
            java.lang.Class<android.os.Bundle> r4 = android.os.Bundle.class
            r6 = 1
            r3[r6] = r4     // Catch:{  }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r1, r3)     // Catch:{  }
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{  }
            r1[r5] = r7     // Catch:{  }
            r1[r6] = r8     // Catch:{  }
            java.lang.Object r7 = r0.invoke(r8, r1)     // Catch:{  }
            com.google.android.gms.measurement.internal.zzib r7 = (com.google.android.gms.measurement.internal.zzib) r7     // Catch:{  }
            return r7
        L_0x0027:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.AppMeasurement.zza(android.content.Context, android.os.Bundle):com.google.android.gms.measurement.internal.zzib");
    }

    @Deprecated
    public void setMeasurementEnabled(boolean z) {
        if (this.zzd) {
            this.zzc.zza(Boolean.valueOf(z));
        } else {
            this.zzb.zzg().zza(Boolean.valueOf(z));
        }
    }

    private AppMeasurement(zzfv zzfv) {
        Preconditions.checkNotNull(zzfv);
        this.zzb = zzfv;
        this.zzc = null;
        this.zzd = false;
    }

    private AppMeasurement(zzib zzib) {
        Preconditions.checkNotNull(zzib);
        this.zzc = zzib;
        this.zzb = null;
        this.zzd = true;
    }

    public void logEventInternal(String str, String str2, Bundle bundle) {
        if (this.zzd) {
            this.zzc.zza(str, str2, bundle);
        } else {
            this.zzb.zzg().zza(str, str2, bundle);
        }
    }

    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        if (this.zzd) {
            this.zzc.zza(str, str2, bundle, j);
        } else {
            this.zzb.zzg().zza(str, str2, bundle, true, false, j);
        }
    }

    public void setUserPropertyInternal(String str, String str2, Object obj) {
        Preconditions.checkNotEmpty(str);
        if (this.zzd) {
            this.zzc.zza(str, str2, obj);
        } else {
            this.zzb.zzg().zza(str, str2, obj, true);
        }
    }

    public Map<String, Object> getUserProperties(boolean z) {
        if (this.zzd) {
            return this.zzc.zza((String) null, (String) null, z);
        }
        List<zzkr> zza2 = this.zzb.zzg().zza(z);
        ArrayMap arrayMap = new ArrayMap(zza2.size());
        for (zzkr next : zza2) {
            arrayMap.put(next.zza, next.zza());
        }
        return arrayMap;
    }

    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        if (this.zzd) {
            this.zzc.zza((zzgx) eventInterceptor);
        } else {
            this.zzb.zzg().zza((zzgx) eventInterceptor);
        }
    }

    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        if (this.zzd) {
            this.zzc.zza((zzgw) onEventListener);
        } else {
            this.zzb.zzg().zza((zzgw) onEventListener);
        }
    }

    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        if (this.zzd) {
            this.zzc.zzb((zzgw) onEventListener);
        } else {
            this.zzb.zzg().zzb((zzgw) onEventListener);
        }
    }

    public String getCurrentScreenName() {
        if (this.zzd) {
            return this.zzc.zza();
        }
        return this.zzb.zzg().zzai();
    }

    public String getCurrentScreenClass() {
        if (this.zzd) {
            return this.zzc.zzb();
        }
        return this.zzb.zzg().zzaj();
    }

    public String getAppInstanceId() {
        if (this.zzd) {
            return this.zzc.zzc();
        }
        return this.zzb.zzg().zzag();
    }

    public String getGmpAppId() {
        if (this.zzd) {
            return this.zzc.zzd();
        }
        return this.zzb.zzg().zzak();
    }

    public long generateEventId() {
        if (this.zzd) {
            return this.zzc.zze();
        }
        return this.zzb.zzh().zzf();
    }

    public void beginAdUnitExposure(String str) {
        if (this.zzd) {
            this.zzc.zza(str);
        } else {
            this.zzb.zzy().zza(str, this.zzb.zzl().elapsedRealtime());
        }
    }

    public void endAdUnitExposure(String str) {
        if (this.zzd) {
            this.zzc.zzb(str);
        } else {
            this.zzb.zzy().zzb(str, this.zzb.zzl().elapsedRealtime());
        }
    }

    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        if (this.zzd) {
            this.zzc.zza(conditionalUserProperty.zza());
        } else {
            this.zzb.zzg().zza(conditionalUserProperty.zza());
        }
    }

    /* access modifiers changed from: protected */
    public void setConditionalUserPropertyAs(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        if (!this.zzd) {
            this.zzb.zzg().zzb(conditionalUserProperty.zza());
            return;
        }
        throw new IllegalStateException("Unexpected call on client side");
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        if (this.zzd) {
            this.zzc.zzb(str, str2, bundle);
        } else {
            this.zzb.zzg().zzc(str, str2, bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        if (!this.zzd) {
            this.zzb.zzg().zza(str, str2, str3, bundle);
            return;
        }
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        if (this.zzd) {
            return this.zzc.zza(str, str2, z);
        }
        return this.zzb.zzg().zza(str, str2, z);
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        if (!this.zzd) {
            return this.zzb.zzg().zza(str, str2, str3, z);
        }
        throw new IllegalStateException("Unexpected call on client side");
    }

    public List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        List<Bundle> list;
        int i;
        if (this.zzd) {
            list = this.zzc.zza(str, str2);
        } else {
            list = this.zzb.zzg().zza(str, str2);
        }
        if (list == null) {
            i = 0;
        } else {
            i = list.size();
        }
        ArrayList arrayList = new ArrayList(i);
        for (Bundle conditionalUserProperty : list) {
            arrayList.add(new ConditionalUserProperty(conditionalUserProperty));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        int i;
        if (!this.zzd) {
            ArrayList<Bundle> zza2 = this.zzb.zzg().zza(str, str2, str3);
            int i2 = 0;
            if (zza2 == null) {
                i = 0;
            } else {
                i = zza2.size();
            }
            ArrayList arrayList = new ArrayList(i);
            ArrayList arrayList2 = zza2;
            int size = arrayList2.size();
            while (i2 < size) {
                Object obj = arrayList2.get(i2);
                i2++;
                arrayList.add(new ConditionalUserProperty((Bundle) obj));
            }
            return arrayList;
        }
        throw new IllegalStateException("Unexpected call on client side");
    }

    public int getMaxUserProperties(String str) {
        if (this.zzd) {
            return this.zzc.zzc(str);
        }
        this.zzb.zzg();
        Preconditions.checkNotEmpty(str);
        return 25;
    }

    public Boolean getBoolean() {
        if (this.zzd) {
            return (Boolean) this.zzc.zza(4);
        }
        return this.zzb.zzg().zzab();
    }

    public String getString() {
        if (this.zzd) {
            return (String) this.zzc.zza(0);
        }
        return this.zzb.zzg().zzac();
    }

    public Long getLong() {
        if (this.zzd) {
            return (Long) this.zzc.zza(1);
        }
        return this.zzb.zzg().zzad();
    }

    public Integer getInteger() {
        if (this.zzd) {
            return (Integer) this.zzc.zza(3);
        }
        return this.zzb.zzg().zzae();
    }

    public Double getDouble() {
        if (this.zzd) {
            return (Double) this.zzc.zza(2);
        }
        return this.zzb.zzg().zzaf();
    }
}
