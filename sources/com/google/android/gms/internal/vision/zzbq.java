package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzbq implements zzay {
    private static final Map<String, zzbq> zzhc = new ArrayMap();
    private final Object zzfs = new Object();
    private volatile Map<String, ?> zzft;
    private final List<zzaz> zzfu = new ArrayList();
    private final SharedPreferences zzhd;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzhe = new zzbt(this);

    static zzbq zzb(Context context, String str) {
        zzbq zzbq;
        if (!((!zzas.zzu() || str.startsWith("direct_boot:")) ? true : zzas.isUserUnlocked(context))) {
            return null;
        }
        synchronized (zzbq.class) {
            zzbq = zzhc.get(str);
            if (zzbq == null) {
                zzbq = new zzbq(zzc(context, str));
                zzhc.put(str, zzbq);
            }
        }
        return zzbq;
    }

    private static SharedPreferences zzc(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzas.zzu()) {
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

    private zzbq(SharedPreferences sharedPreferences) {
        this.zzhd = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this.zzhe);
    }

    /* JADX INFO: finally extract failed */
    public final Object zzb(String str) {
        Map<String, ?> map = this.zzft;
        if (map == null) {
            synchronized (this.zzfs) {
                map = this.zzft;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.zzhd.getAll();
                        this.zzft = all;
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

    static synchronized void zzy() {
        synchronized (zzbq.class) {
            for (zzbq next : zzhc.values()) {
                next.zzhd.unregisterOnSharedPreferenceChangeListener(next.zzhe);
            }
            zzhc.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzfs) {
            this.zzft = null;
            zzbi.zzaf();
        }
        synchronized (this) {
            for (zzaz zzad : this.zzfu) {
                zzad.zzad();
            }
        }
    }
}
