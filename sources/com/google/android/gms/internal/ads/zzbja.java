package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzf;
import com.google.android.gms.ads.internal.zzp;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbja {
    private final Context zzaai;
    private final zzbbx zzbot;
    private final WeakReference<Context> zzesm;

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static class zza {
        /* access modifiers changed from: private */
        public Context zzaai;
        /* access modifiers changed from: private */
        public zzbbx zzbot;
        /* access modifiers changed from: private */
        public WeakReference<Context> zzesm;

        public final zza zza(zzbbx zzbbx) {
            this.zzbot = zzbbx;
            return this;
        }

        public final zza zzbx(Context context) {
            this.zzesm = new WeakReference<>(context);
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.zzaai = context;
            return this;
        }
    }

    private zzbja(zza zza2) {
        this.zzbot = zza2.zzbot;
        this.zzaai = zza2.zzaai;
        this.zzesm = zza2.zzesm;
    }

    /* access modifiers changed from: package-private */
    public final Context zzaeb() {
        return this.zzaai;
    }

    /* access modifiers changed from: package-private */
    public final WeakReference<Context> zzaec() {
        return this.zzesm;
    }

    /* access modifiers changed from: package-private */
    public final zzbbx zzaed() {
        return this.zzbot;
    }

    /* access modifiers changed from: package-private */
    public final String zzaee() {
        return zzp.zzkr().zzs(this.zzaai, this.zzbot.zzbre);
    }

    public final zzeg zzaef() {
        return new zzeg(new zzf(this.zzaai, this.zzbot));
    }
}
