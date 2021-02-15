package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseUserActions;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzn extends FirebaseUserActions {
    private zzp zzfj;

    public zzn(Context context) {
        this.zzfj = new zzp(context);
    }

    public final Task<Void> start(Action action) {
        return zza(1, action);
    }

    public final Task<Void> end(Action action) {
        return zza(2, action);
    }

    private final Task<Void> zza(int i, Action action) {
        zza[] zzaArr = new zza[1];
        if (action != null) {
            if (!(action instanceof zza)) {
                return Tasks.forException(new FirebaseAppIndexingInvalidArgumentException("Custom Action objects are not allowed. Please use the 'Actions' or 'ActionBuilder' class for creating Action objects."));
            }
            zzaArr[0] = (zza) action;
            zzaArr[0].zzab().zzf(i);
        }
        return this.zzfj.doWrite(new zzq(this, zzaArr));
    }
}
