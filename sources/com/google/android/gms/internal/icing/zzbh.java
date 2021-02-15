package com.google.android.gms.internal.icing;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzbh implements zzbg {
    private static zzbh zzcr;
    @Nullable
    private final ContentObserver zzcl;
    @Nullable
    private final Context zzcs;

    static zzbh zzc(Context context) {
        zzbh zzbh;
        synchronized (zzbh.class) {
            if (zzcr == null) {
                zzcr = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzbh(context) : new zzbh();
            }
            zzbh = zzcr;
        }
        return zzbh;
    }

    private zzbh(Context context) {
        this.zzcs = context;
        this.zzcl = new zzbj(this, (Handler) null);
        context.getContentResolver().registerContentObserver(zzax.CONTENT_URI, true, this.zzcl);
    }

    private zzbh() {
        this.zzcs = null;
        this.zzcl = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzj */
    public final String zzi(String str) {
        if (this.zzcs == null) {
            return null;
        }
        try {
            return (String) zzbf.zza(new zzbk(this, str));
        } catch (IllegalStateException | SecurityException e) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? "Unable to read GServices for: ".concat(valueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    static synchronized void zzs() {
        synchronized (zzbh.class) {
            if (!(zzcr == null || zzcr.zzcs == null || zzcr.zzcl == null)) {
                zzcr.zzcs.getContentResolver().unregisterContentObserver(zzcr.zzcl);
            }
            zzcr = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzk(String str) {
        return zzax.zza(this.zzcs.getContentResolver(), str, (String) null);
    }
}
