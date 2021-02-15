package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbpw extends zzboq {
    private final Executor zzfoc;
    private final zzafy zzfqz;
    private final Runnable zzfra;

    public zzbpw(zzbqn zzbqn, zzafy zzafy, Runnable runnable, Executor executor) {
        super(zzbqn);
        this.zzfqz = zzafy;
        this.zzfra = runnable;
        this.zzfoc = executor;
    }

    public final zzyo getVideoController() {
        return null;
    }

    public final void zza(ViewGroup viewGroup, zzvn zzvn) {
    }

    public final zzdnu zzahw() {
        return null;
    }

    public final View zzahx() {
        return null;
    }

    public final zzdnu zzaif() {
        return null;
    }

    public final int zzaig() {
        return 0;
    }

    public final void zzkf() {
    }

    public final void zzaih() {
        this.zzfoc.execute(new zzbpy(this, new zzbpv(new AtomicReference(this.zzfra))));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zze(Runnable runnable) {
        try {
            if (!this.zzfqz.zzm(ObjectWrapper.wrap(runnable))) {
                runnable.run();
            }
        } catch (RemoteException unused) {
            runnable.run();
        }
    }
}
