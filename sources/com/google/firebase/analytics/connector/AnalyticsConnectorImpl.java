package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.analytics.connector.internal.zza;
import com.google.firebase.analytics.connector.internal.zzb;
import com.google.firebase.analytics.connector.internal.zze;
import com.google.firebase.analytics.connector.internal.zzg;
import com.google.firebase.events.Event;
import com.google.firebase.events.Subscriber;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.6.0 */
public class AnalyticsConnectorImpl implements AnalyticsConnector {
    private static volatile AnalyticsConnector zzb;
    final Map<String, zza> zza = new ConcurrentHashMap();
    private final AppMeasurementSdk zzc;

    private AnalyticsConnectorImpl(AppMeasurementSdk appMeasurementSdk) {
        Preconditions.checkNotNull(appMeasurementSdk);
        this.zzc = appMeasurementSdk;
    }

    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp, Context context, Subscriber subscriber) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(subscriber);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (AnalyticsConnectorImpl.class) {
                if (zzb == null) {
                    Bundle bundle = new Bundle(1);
                    if (firebaseApp.isDefaultApp()) {
                        subscriber.subscribe(DataCollectionDefaultChange.class, zza.zza, zzb.zza);
                        bundle.putBoolean("dataCollectionDefaultEnabled", firebaseApp.isDataCollectionDefaultEnabled());
                    }
                    zzb = new AnalyticsConnectorImpl(zzag.zza(context, (String) null, (String) null, (String) null, bundle).zza());
                }
            }
        }
        return zzb;
    }

    public static AnalyticsConnector getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp) {
        return (AnalyticsConnector) firebaseApp.get(AnalyticsConnector.class);
    }

    public void logEvent(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (zzb.zza(str) && zzb.zza(str2, bundle) && zzb.zza(str, str2, bundle)) {
            zzb.zzb(str, str2, bundle);
            this.zzc.logEvent(str, str2, bundle);
        }
    }

    public void setUserProperty(String str, String str2, Object obj) {
        if (zzb.zza(str) && zzb.zza(str, str2)) {
            this.zzc.setUserProperty(str, str2, obj);
        }
    }

    public Map<String, Object> getUserProperties(boolean z) {
        return this.zzc.getUserProperties((String) null, (String) null, z);
    }

    public AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener(final String str, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        Object obj;
        Preconditions.checkNotNull(analyticsConnectorListener);
        if (!zzb.zza(str) || zza(str)) {
            return null;
        }
        AppMeasurementSdk appMeasurementSdk = this.zzc;
        if ("fiam".equals(str)) {
            obj = new zze(appMeasurementSdk, analyticsConnectorListener);
        } else if ("crash".equals(str) || "clx".equals(str)) {
            obj = new zzg(appMeasurementSdk, analyticsConnectorListener);
        } else {
            obj = null;
        }
        if (obj == null) {
            return null;
        }
        this.zza.put(str, obj);
        return new AnalyticsConnector.AnalyticsConnectorHandle() {
            public void unregister() {
                if (AnalyticsConnectorImpl.this.zza(str)) {
                    AnalyticsConnector.AnalyticsConnectorListener zza2 = AnalyticsConnectorImpl.this.zza.get(str).zza();
                    if (zza2 != null) {
                        zza2.onMessageTriggered(0, (Bundle) null);
                    }
                    AnalyticsConnectorImpl.this.zza.remove(str);
                }
            }

            public void registerEventNames(Set<String> set) {
                if (AnalyticsConnectorImpl.this.zza(str) && str.equals("fiam") && set != null && !set.isEmpty()) {
                    AnalyticsConnectorImpl.this.zza.get(str).zza(set);
                }
            }

            public void unregisterEventNames() {
                if (AnalyticsConnectorImpl.this.zza(str) && str.equals("fiam")) {
                    AnalyticsConnectorImpl.this.zza.get(str).zzb();
                }
            }
        };
    }

    public void setConditionalUserProperty(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        if (zzb.zza(conditionalUserProperty)) {
            this.zzc.setConditionalUserProperty(zzb.zzb(conditionalUserProperty));
        }
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        if (str2 == null || zzb.zza(str2, bundle)) {
            this.zzc.clearConditionalUserProperty(str, str2, bundle);
        }
    }

    public List<AnalyticsConnector.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        for (Bundle zza2 : this.zzc.getConditionalUserProperties(str, str2)) {
            arrayList.add(zzb.zza(zza2));
        }
        return arrayList;
    }

    public int getMaxUserProperties(String str) {
        return this.zzc.getMaxUserProperties(str);
    }

    /* access modifiers changed from: private */
    public final boolean zza(String str) {
        return !str.isEmpty() && this.zza.containsKey(str) && this.zza.get(str) != null;
    }

    static final /* synthetic */ void zza(Event event) {
        boolean z = ((DataCollectionDefaultChange) event.getPayload()).enabled;
        synchronized (AnalyticsConnectorImpl.class) {
            ((AnalyticsConnectorImpl) zzb).zzc.zza(z);
        }
    }
}
