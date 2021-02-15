package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
abstract class zzwn<T> {
    private static final zzxp zzcjh = zzqc();

    zzwn() {
    }

    /* access modifiers changed from: protected */
    public abstract T zza(zzxp zzxp) throws RemoteException;

    /* access modifiers changed from: protected */
    public abstract T zzpp();

    /* access modifiers changed from: protected */
    public abstract T zzpq() throws RemoteException;

    private static zzxp zzqc() {
        try {
            Object newInstance = zzvx.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            if (!(newInstance instanceof IBinder)) {
                zzbbq.zzfe("ClientApi class is not an instance of IBinder.");
                return null;
            }
            IBinder iBinder = (IBinder) newInstance;
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
            if (queryLocalInterface instanceof zzxp) {
                return (zzxp) queryLocalInterface;
            }
            return new zzxr(iBinder);
        } catch (Exception unused) {
            zzbbq.zzfe("Failed to instantiate ClientApi class.");
            return null;
        }
    }

    private final T zzqd() {
        zzxp zzxp = zzcjh;
        if (zzxp == null) {
            zzbbq.zzfe("ClientApi class cannot be loaded.");
            return null;
        }
        try {
            return zza(zzxp);
        } catch (RemoteException e) {
            zzbbq.zzd("Cannot invoke local loader using ClientApi class.", e);
            return null;
        }
    }

    private final T zzqe() {
        try {
            return zzpq();
        } catch (RemoteException e) {
            zzbbq.zzd("Cannot invoke remote loader.", e);
            return null;
        }
    }

    public final T zzd(Context context, boolean z) {
        T t;
        boolean z2 = false;
        boolean z3 = z;
        if (!z3) {
            zzwm.zzpt();
            if (!zzbbg.zzd(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                zzbbq.zzef("Google Play Services is not available.");
                z3 = true;
            }
        }
        if (DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) > DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID)) {
            z3 = true;
        }
        zzabb.initialize(context);
        if (zzacs.zzday.get().booleanValue()) {
            z3 = false;
        }
        if (z3) {
            t = zzqd();
            if (t == null) {
                t = zzqe();
            }
        } else {
            T zzqe = zzqe();
            int i = zzqe == null ? 1 : 0;
            if (i != 0) {
                if (zzwm.zzqa().nextInt(zzadb.zzdcb.get().intValue()) == 0) {
                    z2 = true;
                }
                if (z2) {
                    Bundle bundle = new Bundle();
                    bundle.putString("action", "dynamite_load");
                    bundle.putInt("is_missing", i);
                    zzwm.zzpt().zza(context, zzwm.zzpz().zzbre, "gmob-apps", bundle, true);
                }
            }
            t = zzqe == null ? zzqd() : zzqe;
        }
        return t == null ? zzpp() : t;
    }
}
