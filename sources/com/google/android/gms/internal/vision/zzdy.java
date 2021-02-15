package com.google.android.gms.internal.vision;

import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzdy extends zzdl<K, V> {
    private final /* synthetic */ zzdp zzmo;
    @NullableDecl
    private final K zzmr;
    private int zzms;

    zzdy(zzdp zzdp, int i) {
        this.zzmo = zzdp;
        this.zzmr = zzdp.zzmi[i];
        this.zzms = i;
    }

    @NullableDecl
    public final K getKey() {
        return this.zzmr;
    }

    private final void zzco() {
        int i = this.zzms;
        if (i == -1 || i >= this.zzmo.size() || !zzcz.equal(this.zzmr, this.zzmo.zzmi[this.zzms])) {
            this.zzms = this.zzmo.indexOf(this.zzmr);
        }
    }

    @NullableDecl
    public final V getValue() {
        Map zzcf = this.zzmo.zzcf();
        if (zzcf != null) {
            return zzcf.get(this.zzmr);
        }
        zzco();
        if (this.zzms == -1) {
            return null;
        }
        return this.zzmo.zzmj[this.zzms];
    }

    public final V setValue(V v) {
        Map zzcf = this.zzmo.zzcf();
        if (zzcf != null) {
            return zzcf.put(this.zzmr, v);
        }
        zzco();
        if (this.zzms == -1) {
            this.zzmo.put(this.zzmr, v);
            return null;
        }
        V v2 = this.zzmo.zzmj[this.zzms];
        this.zzmo.zzmj[this.zzms] = v;
        return v2;
    }
}
