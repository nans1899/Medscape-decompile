package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzhb implements zzhw, zzhx {
    private int index;
    private int state;
    private final int zzaee;
    private zzhz zzaef;
    private zznm zzaeg;
    private long zzaeh;
    private boolean zzaei = true;
    private boolean zzaej;

    public zzhb(int i) {
        this.zzaee = i;
    }

    /* access modifiers changed from: protected */
    public void onStarted() throws zzhd {
    }

    /* access modifiers changed from: protected */
    public void onStopped() throws zzhd {
    }

    public void zza(int i, Object obj) throws zzhd {
    }

    /* access modifiers changed from: protected */
    public void zza(long j, boolean z) throws zzhd {
    }

    /* access modifiers changed from: protected */
    public void zza(zzho[] zzhoArr, long j) throws zzhd {
    }

    public final zzhw zzdz() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void zze(boolean z) throws zzhd {
    }

    public zzpd zzea() {
        return null;
    }

    public int zzeg() throws zzhd {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void zzeh() {
    }

    public final int getTrackType() {
        return this.zzaee;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final int getState() {
        return this.state;
    }

    public final void zza(zzhz zzhz, zzho[] zzhoArr, zznm zznm, long j, boolean z, long j2) throws zzhd {
        zzoz.checkState(this.state == 0);
        this.zzaef = zzhz;
        this.state = 1;
        zze(z);
        zza(zzhoArr, zznm, j2);
        zza(j, z);
    }

    public final void start() throws zzhd {
        boolean z = true;
        if (this.state != 1) {
            z = false;
        }
        zzoz.checkState(z);
        this.state = 2;
        onStarted();
    }

    public final void zza(zzho[] zzhoArr, zznm zznm, long j) throws zzhd {
        zzoz.checkState(!this.zzaej);
        this.zzaeg = zznm;
        this.zzaei = false;
        this.zzaeh = j;
        zza(zzhoArr, j);
    }

    public final zznm zzeb() {
        return this.zzaeg;
    }

    public final boolean zzec() {
        return this.zzaei;
    }

    public final void zzed() {
        this.zzaej = true;
    }

    public final boolean zzee() {
        return this.zzaej;
    }

    public final void zzef() throws IOException {
        this.zzaeg.zzhs();
    }

    public final void zzdo(long j) throws zzhd {
        this.zzaej = false;
        this.zzaei = false;
        zza(j, false);
    }

    public final void stop() throws zzhd {
        zzoz.checkState(this.state == 2);
        this.state = 1;
        onStopped();
    }

    public final void disable() {
        boolean z = true;
        if (this.state != 1) {
            z = false;
        }
        zzoz.checkState(z);
        this.state = 0;
        this.zzaeg = null;
        this.zzaej = false;
        zzeh();
    }

    /* access modifiers changed from: protected */
    public final zzhz zzei() {
        return this.zzaef;
    }

    /* access modifiers changed from: protected */
    public final int getIndex() {
        return this.index;
    }

    /* access modifiers changed from: protected */
    public final int zza(zzhq zzhq, zzjk zzjk, boolean z) {
        int zzb = this.zzaeg.zzb(zzhq, zzjk, z);
        if (zzb == -4) {
            if (zzjk.zzgl()) {
                this.zzaei = true;
                if (this.zzaej) {
                    return -4;
                }
                return -3;
            }
            zzjk.zzaob += this.zzaeh;
        } else if (zzb == -5) {
            zzho zzho = zzhq.zzahx;
            if (zzho.zzahr != Long.MAX_VALUE) {
                zzhq.zzahx = zzho.zzds(zzho.zzahr + this.zzaeh);
            }
        }
        return zzb;
    }

    /* access modifiers changed from: protected */
    public final void zzdp(long j) {
        this.zzaeg.zzeh(j - this.zzaeh);
    }

    /* access modifiers changed from: protected */
    public final boolean zzej() {
        return this.zzaei ? this.zzaej : this.zzaeg.isReady();
    }
}
