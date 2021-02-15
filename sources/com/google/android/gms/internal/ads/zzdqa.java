package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.LinkedList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdqa {
    private final int maxEntries;
    private final LinkedList<zzdqk<?>> zzhha = new LinkedList<>();
    private final int zzhhb;
    private final zzdqx zzhhc;

    public zzdqa(int i, int i2) {
        this.maxEntries = i;
        this.zzhhb = i2;
        this.zzhhc = new zzdqx();
    }

    public final boolean zzb(zzdqk<?> zzdqk) {
        this.zzhhc.zzauu();
        zzaue();
        if (this.zzhha.size() == this.maxEntries) {
            return false;
        }
        this.zzhha.add(zzdqk);
        return true;
    }

    public final zzdqk<?> zzatz() {
        this.zzhhc.zzauu();
        zzaue();
        if (this.zzhha.isEmpty()) {
            return null;
        }
        zzdqk<?> remove = this.zzhha.remove();
        if (remove != null) {
            this.zzhhc.zzauv();
        }
        return remove;
    }

    public final int size() {
        zzaue();
        return this.zzhha.size();
    }

    public final long getCreationTimeMillis() {
        return this.zzhhc.getCreationTimeMillis();
    }

    public final long zzaua() {
        return this.zzhhc.zzaua();
    }

    public final int zzaub() {
        return this.zzhhc.zzaub();
    }

    public final String zzauc() {
        return this.zzhhc.zzaum();
    }

    public final zzdra zzaud() {
        return this.zzhhc.zzaux();
    }

    private final void zzaue() {
        while (!this.zzhha.isEmpty()) {
            if (zzp.zzky().currentTimeMillis() - this.zzhha.getFirst().zzhis >= ((long) this.zzhhb)) {
                this.zzhhc.zzauw();
                this.zzhha.remove();
            } else {
                return;
            }
        }
    }
}
