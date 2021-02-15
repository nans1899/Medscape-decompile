package com.google.android.gms.internal.vision;

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

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzau implements zzay {
    private static final Map<Uri, zzau> zzfp = new ArrayMap();
    private static final String[] zzfv = {"key", "value"};
    private final Uri uri;
    private final ContentResolver zzfq;
    private final ContentObserver zzfr = new zzaw(this, (Handler) null);
    private final Object zzfs = new Object();
    private volatile Map<String, String> zzft;
    private final List<zzaz> zzfu = new ArrayList();

    private zzau(ContentResolver contentResolver, Uri uri2) {
        this.zzfq = contentResolver;
        this.uri = uri2;
        contentResolver.registerContentObserver(uri2, false, this.zzfr);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(5:5|6|7|8|9)|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.vision.zzau zza(android.content.ContentResolver r3, android.net.Uri r4) {
        /*
            java.lang.Class<com.google.android.gms.internal.vision.zzau> r0 = com.google.android.gms.internal.vision.zzau.class
            monitor-enter(r0)
            java.util.Map<android.net.Uri, com.google.android.gms.internal.vision.zzau> r1 = zzfp     // Catch:{ all -> 0x001a }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.vision.zzau r1 = (com.google.android.gms.internal.vision.zzau) r1     // Catch:{ all -> 0x001a }
            if (r1 != 0) goto L_0x0018
            com.google.android.gms.internal.vision.zzau r2 = new com.google.android.gms.internal.vision.zzau     // Catch:{ SecurityException -> 0x0018 }
            r2.<init>(r3, r4)     // Catch:{ SecurityException -> 0x0018 }
            java.util.Map<android.net.Uri, com.google.android.gms.internal.vision.zzau> r3 = zzfp     // Catch:{ SecurityException -> 0x0017 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzau.zza(android.content.ContentResolver, android.net.Uri):com.google.android.gms.internal.vision.zzau");
    }

    private final Map<String, String> zzv() {
        Map<String, String> map = this.zzft;
        if (map == null) {
            synchronized (this.zzfs) {
                map = this.zzft;
                if (map == null) {
                    map = zzx();
                    this.zzft = map;
                }
            }
        }
        if (map != null) {
            return map;
        }
        return Collections.emptyMap();
    }

    public final void zzw() {
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

    /* JADX INFO: finally extract failed */
    private final Map<String, String> zzx() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Map<String, String> map = (Map) zzbb.zza(new zzax(this));
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

    static synchronized void zzy() {
        synchronized (zzau.class) {
            for (zzau next : zzfp.values()) {
                next.zzfq.unregisterContentObserver(next.zzfr);
            }
            zzfp.clear();
        }
    }

    public final /* synthetic */ Object zzb(String str) {
        return zzv().get(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map zzz() {
        Map map;
        Cursor query = this.zzfq.query(this.uri, zzfv, (String) null, (String[]) null, (String) null);
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
