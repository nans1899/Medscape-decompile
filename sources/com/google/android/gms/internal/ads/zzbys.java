package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.common.util.Clock;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbys {
    private final zzdls zzfrs;
    private final Set<zzcab<zzux>> zzfvs;
    private final Set<zzcab<zzbuh>> zzfvt;
    private final Set<zzcab<zzbuz>> zzfvu;
    private final Set<zzcab<zzbwb>> zzfvv;
    private final Set<zzcab<zzbvs>> zzfvw;
    private final Set<zzcab<zzbui>> zzfvx;
    private final Set<zzcab<zzbuv>> zzfvy;
    private final Set<zzcab<AdMetadataListener>> zzfvz;
    private final Set<zzcab<AppEventListener>> zzfwa;
    private final Set<zzcab<zzbwl>> zzfwb;
    private final Set<zzcab<zzp>> zzfwc;
    private zzbug zzfwd;
    private zzcwj zzfwe;

    private zzbys(zza zza2) {
        this.zzfvs = zza2.zzfvs;
        this.zzfvu = zza2.zzfvu;
        this.zzfvv = zza2.zzfvv;
        this.zzfvt = zza2.zzfvt;
        this.zzfvw = zza2.zzfvw;
        this.zzfvx = zza2.zzfvx;
        this.zzfvy = zza2.zzfvy;
        this.zzfvz = zza2.zzfvz;
        this.zzfwa = zza2.zzfwa;
        this.zzfwb = zza2.zzfwf;
        this.zzfrs = zza2.zzfrs;
        this.zzfwc = zza2.zzfwc;
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static class zza {
        /* access modifiers changed from: private */
        public zzdls zzfrs;
        /* access modifiers changed from: private */
        public Set<zzcab<zzux>> zzfvs = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<zzbuh>> zzfvt = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<zzbuz>> zzfvu = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<zzbwb>> zzfvv = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<zzbvs>> zzfvw = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<zzbui>> zzfvx = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<zzbuv>> zzfvy = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<AdMetadataListener>> zzfvz = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<AppEventListener>> zzfwa = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<zzp>> zzfwc = new HashSet();
        /* access modifiers changed from: private */
        public Set<zzcab<zzbwl>> zzfwf = new HashSet();

        public final zza zza(zzbuh zzbuh, Executor executor) {
            this.zzfvt.add(new zzcab(zzbuh, executor));
            return this;
        }

        public final zza zza(zzbvs zzbvs, Executor executor) {
            this.zzfvw.add(new zzcab(zzbvs, executor));
            return this;
        }

        public final zza zza(zzbui zzbui, Executor executor) {
            this.zzfvx.add(new zzcab(zzbui, executor));
            return this;
        }

        public final zza zza(zzbuv zzbuv, Executor executor) {
            this.zzfvy.add(new zzcab(zzbuv, executor));
            return this;
        }

        public final zza zza(AdMetadataListener adMetadataListener, Executor executor) {
            this.zzfvz.add(new zzcab(adMetadataListener, executor));
            return this;
        }

        public final zza zza(AppEventListener appEventListener, Executor executor) {
            this.zzfwa.add(new zzcab(appEventListener, executor));
            return this;
        }

        public final zza zza(zzxk zzxk, Executor executor) {
            if (this.zzfwa != null) {
                zzczr zzczr = new zzczr();
                zzczr.zzb(zzxk);
                this.zzfwa.add(new zzcab(zzczr, executor));
            }
            return this;
        }

        public final zza zza(zzux zzux, Executor executor) {
            this.zzfvs.add(new zzcab(zzux, executor));
            return this;
        }

        public final zza zza(zzbuz zzbuz, Executor executor) {
            this.zzfvu.add(new zzcab(zzbuz, executor));
            return this;
        }

        public final zza zza(zzbwb zzbwb, Executor executor) {
            this.zzfvv.add(new zzcab(zzbwb, executor));
            return this;
        }

        public final zza zza(zzp zzp, Executor executor) {
            this.zzfwc.add(new zzcab(zzp, executor));
            return this;
        }

        public final zza zza(zzbwl zzbwl, Executor executor) {
            this.zzfwf.add(new zzcab(zzbwl, executor));
            return this;
        }

        public final zza zza(zzdls zzdls) {
            this.zzfrs = zzdls;
            return this;
        }

        public final zzbys zzakr() {
            return new zzbys(this);
        }
    }

    public final Set<zzcab<zzbuh>> zzakf() {
        return this.zzfvt;
    }

    public final Set<zzcab<zzbvs>> zzakg() {
        return this.zzfvw;
    }

    public final Set<zzcab<zzbui>> zzakh() {
        return this.zzfvx;
    }

    public final Set<zzcab<zzbuv>> zzaki() {
        return this.zzfvy;
    }

    public final Set<zzcab<AdMetadataListener>> zzakj() {
        return this.zzfvz;
    }

    public final Set<zzcab<AppEventListener>> zzakk() {
        return this.zzfwa;
    }

    public final Set<zzcab<zzux>> zzakl() {
        return this.zzfvs;
    }

    public final Set<zzcab<zzbuz>> zzakm() {
        return this.zzfvu;
    }

    public final Set<zzcab<zzbwb>> zzakn() {
        return this.zzfvv;
    }

    public final Set<zzcab<zzbwl>> zzako() {
        return this.zzfwb;
    }

    public final Set<zzcab<zzp>> zzakp() {
        return this.zzfwc;
    }

    public final zzdls zzakq() {
        return this.zzfrs;
    }

    public final zzbug zzc(Set<zzcab<zzbui>> set) {
        if (this.zzfwd == null) {
            this.zzfwd = new zzbug(set);
        }
        return this.zzfwd;
    }

    public final zzcwj zza(Clock clock, zzcwl zzcwl, zzctd zzctd) {
        if (this.zzfwe == null) {
            this.zzfwe = new zzcwj(clock, zzcwl, zzctd);
        }
        return this.zzfwe;
    }
}
