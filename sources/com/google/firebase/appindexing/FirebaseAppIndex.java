package com.google.firebase.appindexing;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appindexing.internal.zzi;
import java.lang.ref.WeakReference;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public abstract class FirebaseAppIndex {
    public static final String ACTION_UPDATE_INDEX = "com.google.firebase.appindexing.UPDATE_INDEX";
    public static final String APP_INDEXING_API_TAG = "FirebaseAppIndex";
    public static final String EXTRA_UPDATE_INDEX_REASON = "com.google.firebase.appindexing.extra.REASON";
    public static final int EXTRA_UPDATE_INDEX_REASON_REBUILD = 1;
    public static final int EXTRA_UPDATE_INDEX_REASON_REFRESH = 2;
    private static WeakReference<FirebaseAppIndex> zzen;

    public abstract Task<Void> remove(String... strArr);

    public abstract Task<Void> removeAll();

    public abstract Task<Void> update(Indexable... indexableArr);

    public static synchronized FirebaseAppIndex getInstance() {
        zzi zzi;
        synchronized (FirebaseAppIndex.class) {
            zzi = zzen == null ? null : (FirebaseAppIndex) zzen.get();
            if (zzi == null) {
                zzi zzi2 = new zzi(FirebaseApp.getInstance().getApplicationContext());
                zzen = new WeakReference<>(zzi2);
                zzi = zzi2;
            }
        }
        return zzi;
    }
}
