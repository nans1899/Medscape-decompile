package com.google.android.gms.internal.icing;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzbt implements zzbg {
    private static final Map<String, zzbt> zzdh = new ArrayMap();
    private final Object zzcm = new Object();
    private volatile Map<String, ?> zzcn;
    private final List<zzbd> zzco = new ArrayList();
    private final SharedPreferences zzdi;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzdj = new zzbw(this);

    static zzbt zza(Context context, String str) {
        zzbt zzbt;
        if (!((!zzaz.zzk() || str.startsWith("direct_boot:")) ? true : zzaz.isUserUnlocked(context))) {
            return null;
        }
        synchronized (zzbt.class) {
            zzbt = zzdh.get(str);
            if (zzbt == null) {
                zzbt = new zzbt(zzb(context, str));
                zzdh.put(str, zzbt);
            }
        }
        return zzbt;
    }

    private static SharedPreferences zzb(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzaz.zzk()) {
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

    private zzbt(SharedPreferences sharedPreferences) {
        this.zzdi = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this.zzdj);
    }

    /* JADX INFO: finally extract failed */
    public final Object zzi(String str) {
        Map<String, ?> map = this.zzcn;
        if (map == null) {
            synchronized (this.zzcm) {
                map = this.zzcn;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.zzdi.getAll();
                        this.zzcn = all;
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

    static synchronized void zzp() {
        synchronized (zzbt.class) {
            for (zzbt next : zzdh.values()) {
                next.zzdi.unregisterOnSharedPreferenceChangeListener(next.zzdj);
            }
            zzdh.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzcm) {
            this.zzcn = null;
            zzbq.zzt();
        }
        synchronized (this) {
            for (zzbd zzr : this.zzco) {
                zzr.zzr();
            }
        }
    }
}
