package com.google.android.gms.internal.icing;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzbc implements zzbg {
    private static final Map<Uri, zzbc> zzcj = new ArrayMap();
    private static final String[] zzcp = {"key", "value"};
    private final Uri uri;
    private final ContentResolver zzck;
    private final ContentObserver zzcl = new zzbe(this, (Handler) null);
    private final Object zzcm = new Object();
    private volatile Map<String, String> zzcn;
    private final List<zzbd> zzco = new ArrayList();

    private zzbc(ContentResolver contentResolver, Uri uri2) {
        this.zzck = contentResolver;
        this.uri = uri2;
        contentResolver.registerContentObserver(uri2, false, this.zzcl);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(5:5|6|7|8|9)|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.icing.zzbc zza(android.content.ContentResolver r3, android.net.Uri r4) {
        /*
            java.lang.Class<com.google.android.gms.internal.icing.zzbc> r0 = com.google.android.gms.internal.icing.zzbc.class
            monitor-enter(r0)
            java.util.Map<android.net.Uri, com.google.android.gms.internal.icing.zzbc> r1 = zzcj     // Catch:{ all -> 0x001a }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.icing.zzbc r1 = (com.google.android.gms.internal.icing.zzbc) r1     // Catch:{ all -> 0x001a }
            if (r1 != 0) goto L_0x0018
            com.google.android.gms.internal.icing.zzbc r2 = new com.google.android.gms.internal.icing.zzbc     // Catch:{ SecurityException -> 0x0018 }
            r2.<init>(r3, r4)     // Catch:{ SecurityException -> 0x0018 }
            java.util.Map<android.net.Uri, com.google.android.gms.internal.icing.zzbc> r3 = zzcj     // Catch:{ SecurityException -> 0x0017 }
            r3.put(r4, r2)     // Catch:{ SecurityException -> 0x0017 }
        L_0x0017:
            r1 = r2
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r1
        L_0x001a:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzbc.zza(android.content.ContentResolver, android.net.Uri):com.google.android.gms.internal.icing.zzbc");
    }

    private final Map<String, String> zzm() {
        Map<String, String> map = this.zzcn;
        if (map == null) {
            synchronized (this.zzcm) {
                map = this.zzcn;
                if (map == null) {
                    map = zzo();
                    this.zzcn = map;
                }
            }
        }
        if (map != null) {
            return map;
        }
        return Collections.emptyMap();
    }

    public final void zzn() {
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

    /* JADX INFO: finally extract failed */
    private final Map<String, String> zzo() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Map<String, String> map = (Map) zzbf.zza(new zzbb(this));
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return map;
        } catch (SQLiteException | IllegalStateException | SecurityException unused) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return null;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
    }

    static synchronized void zzp() {
        synchronized (zzbc.class) {
            for (zzbc next : zzcj.values()) {
                next.zzck.unregisterContentObserver(next.zzcl);
            }
            zzcj.clear();
        }
    }

    public final /* synthetic */ Object zzi(String str) {
        return zzm().get(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map zzq() {
        Map map;
        Cursor query = this.zzck.query(this.uri, zzcp, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            query.close();
            return map;
        } finally {
            query.close();
        }
    }
}
