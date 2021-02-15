package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.vision.L;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzs<T> {
    private final Object lock = new Object();
    private final String tag;
    private final String zzdm;
    private final String zzdn;
    private boolean zzdo = false;
    private boolean zzdp = false;
    private T zzdq;
    private final Context zzg;

    public zzs(Context context, String str, String str2) {
        this.zzg = context;
        this.tag = str;
        String valueOf = String.valueOf(str2);
        this.zzdm = valueOf.length() != 0 ? "com.google.android.gms.vision.dynamite.".concat(valueOf) : new String("com.google.android.gms.vision.dynamite.");
        this.zzdn = str2;
    }

    /* access modifiers changed from: protected */
    public abstract T zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException;

    /* access modifiers changed from: protected */
    public abstract void zzp() throws RemoteException;

    public final boolean isOperational() {
        return zzr() != null;
    }

    public final void zzq() {
        synchronized (this.lock) {
            if (this.zzdq != null) {
                try {
                    zzp();
                } catch (RemoteException e) {
                    Log.e(this.tag, "Could not finalize native handle", e);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final T zzr() {
        synchronized (this.lock) {
            if (this.zzdq != null) {
                T t = this.zzdq;
                return t;
            }
            DynamiteModule dynamiteModule = null;
            try {
                dynamiteModule = DynamiteModule.load(this.zzg, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, this.zzdm);
            } catch (DynamiteModule.LoadingException unused) {
                String format = String.format("%s.%s", new Object[]{"com.google.android.gms.vision", this.zzdn});
                L.d("Cannot load thick client module, fall back to load optional module %s", format);
                try {
                    dynamiteModule = DynamiteModule.load(this.zzg, DynamiteModule.PREFER_REMOTE, format);
                } catch (DynamiteModule.LoadingException e) {
                    L.e(e, "Error loading optional module %s", format);
                    if (!this.zzdo) {
                        L.d("Broadcasting download intent for dependency %s", this.zzdn);
                        String str = this.zzdn;
                        Intent intent = new Intent();
                        intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
                        intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", str);
                        intent.setAction("com.google.android.gms.vision.DEPENDENCY");
                        this.zzg.sendBroadcast(intent);
                        this.zzdo = true;
                    }
                }
            }
            if (dynamiteModule != null) {
                try {
                    this.zzdq = zza(dynamiteModule, this.zzg);
                } catch (RemoteException | DynamiteModule.LoadingException e2) {
                    Log.e(this.tag, "Error creating remote native handle", e2);
                }
            }
            if (!this.zzdp && this.zzdq == null) {
                Log.w(this.tag, "Native handle not yet available. Reverting to no-op handle.");
                this.zzdp = true;
            } else if (this.zzdp && this.zzdq != null) {
                Log.w(this.tag, "Native handle is now available.");
            }
            T t2 = this.zzdq;
            return t2;
        }
    }
}
