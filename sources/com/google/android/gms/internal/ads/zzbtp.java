package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbtp {
    private final zzdok zzfsk;
    private Bundle zzfuh;
    private final String zzfui;
    private final zzdoj zzfuj;
    private final Context zzvr;

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static class zza {
        /* access modifiers changed from: private */
        public zzdok zzfsk;
        /* access modifiers changed from: private */
        public Bundle zzfuh;
        /* access modifiers changed from: private */
        public String zzfui;
        /* access modifiers changed from: private */
        public zzdoj zzfuj;
        /* access modifiers changed from: private */
        public Context zzvr;

        public final zza zzce(Context context) {
            this.zzvr = context;
            return this;
        }

        public final zza zza(zzdok zzdok) {
            this.zzfsk = zzdok;
            return this;
        }

        public final zza zzf(Bundle bundle) {
            this.zzfuh = bundle;
            return this;
        }

        public final zza zzfx(String str) {
            this.zzfui = str;
            return this;
        }

        public final zzbtp zzajv() {
            return new zzbtp(this);
        }

        public final zza zza(zzdoj zzdoj) {
            this.zzfuj = zzdoj;
            return this;
        }
    }

    private zzbtp(zza zza2) {
        this.zzvr = zza2.zzvr;
        this.zzfsk = zza2.zzfsk;
        this.zzfuh = zza2.zzfuh;
        this.zzfui = zza2.zzfui;
        this.zzfuj = zza2.zzfuj;
    }

    /* access modifiers changed from: package-private */
    public final zza zzajq() {
        return new zza().zzce(this.zzvr).zza(this.zzfsk).zzfx(this.zzfui).zzf(this.zzfuh);
    }

    /* access modifiers changed from: package-private */
    public final zzdok zzajr() {
        return this.zzfsk;
    }

    /* access modifiers changed from: package-private */
    public final zzdoj zzajs() {
        return this.zzfuj;
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzajt() {
        return this.zzfuh;
    }

    /* access modifiers changed from: package-private */
    public final String zzaju() {
        return this.zzfui;
    }

    /* access modifiers changed from: package-private */
    public final Context zzcd(Context context) {
        if (this.zzfui != null) {
            return context;
        }
        return this.zzvr;
    }
}
