package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzdo implements zzcx {
    private static final Map<String, zzdo> zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzc = new zzdr(this);
    private final Object zzd = new Object();
    private volatile Map<String, ?> zze;
    private final List<zzcu> zzf = new ArrayList();

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.measurement.zzdo, java.lang.Object, java.lang.String] */
    static zzdo zza(Context context, String str) {
        zzdo zzdo;
        ? r0 = 0;
        if (!((!zzcr.zza() || r0.startsWith("direct_boot:")) ? true : zzcr.zza(context))) {
            return r0;
        }
        synchronized (zzdo.class) {
            zzdo = zza.get(r0);
            if (zzdo == null) {
                zzdo = new zzdo(zzb(context, r0));
                zza.put(r0, zzdo);
            }
        }
        return zzdo;
    }

    private static SharedPreferences zzb(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzcr.zza()) {
                    context = context.createDeviceProtectedStorageContext();
                }
                return context.getSharedPreferences(str.substring(12), 0);
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return sharedPreferences;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private zzdo(SharedPreferences sharedPreferences) {
        this.zzb = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this.zzc);
    }

    /* JADX INFO: finally extract failed */
    public final Object zza(String str) {
        Map<String, ?> map = this.zze;
        if (map == null) {
            synchronized (this.zzd) {
                map = this.zze;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.zzb.getAll();
                        this.zze = all;
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        map = all;
                    } catch (Throwable th) {
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        throw th;
                    }
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    static synchronized void zza() {
        synchronized (zzdo.class) {
            for (zzdo next : zza.values()) {
                next.zzb.unregisterOnSharedPreferenceChangeListener(next.zzc);
            }
            zza.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzd) {
            this.zze = null;
            zzdh.zza();
        }
        synchronized (this) {
            for (zzcu zza2 : this.zzf) {
                zza2.zza();
            }
        }
    }
}
