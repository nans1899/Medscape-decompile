package com.google.android.gms.internal.vision;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzbd implements zzay {
    private static zzbd zzgb;
    @Nullable
    private final ContentObserver zzfr;
    @Nullable
    private final Context zzg;

    static zzbd zze(Context context) {
        zzbd zzbd;
        synchronized (zzbd.class) {
            if (zzgb == null) {
                zzgb = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzbd(context) : new zzbd();
            }
            zzbd = zzgb;
        }
        return zzbd;
    }

    private zzbd(Context context) {
        this.zzg = context;
        this.zzfr = new zzbf(this, (Handler) null);
        context.getContentResolver().registerContentObserver(zzaq.CONTENT_URI, true, this.zzfr);
    }

    private zzbd() {
        this.zzg = null;
        this.zzfr = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final String zzb(String str) {
        if (this.zzg == null) {
            return null;
        }
        try {
            return (String) zzbb.zza(new zzbc(this, str));
        } catch (IllegalStateException | SecurityException e) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? "Unable to read GServices for: ".concat(valueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    static synchronized void zzae() {
        synchronized (zzbd.class) {
            if (!(zzgb == null || zzgb.zzg == null || zzgb.zzfr == null)) {
                zzgb.zzg.getContentResolver().unregisterContentObserver(zzgb.zzfr);
            }
            zzgb = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzd(String str) {
        return zzaq.zza(this.zzg.getContentResolver(), str, (String) null);
    }
}
