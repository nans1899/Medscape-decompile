package com.google.firebase.appindexing;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appindexing.internal.zzn;
import java.lang.ref.WeakReference;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public abstract class FirebaseUserActions {
    public static final String APP_INDEXING_API_TAG = "FirebaseUserActions";
    private static WeakReference<FirebaseUserActions> zzen;

    public abstract Task<Void> end(Action action);

    public abstract Task<Void> start(Action action);

    public static synchronized FirebaseUserActions getInstance() {
        zzn zzn;
        synchronized (FirebaseUserActions.class) {
            zzn = zzen == null ? null : (FirebaseUserActions) zzen.get();
            if (zzn == null) {
                zzn zzn2 = new zzn(FirebaseApp.getInstance().getApplicationContext());
                zzen = new WeakReference<>(zzn2);
                zzn = zzn2;
            }
        }
        return zzn;
    }
}
