package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzej<E> extends zzeb<E> implements Set<E> {
    @NullableDecl
    private transient zzee<E> zznf;

    static int zzy(int i) {
        int max = Math.max(i, 2);
        boolean z = true;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) max)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (max >= 1073741824) {
            z = false;
        }
        zzde.checkArgument(z, "collection too large");
        return 1073741824;
    }

    /* access modifiers changed from: package-private */
    public boolean zzcz() {
        return false;
    }

    zzej() {
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzej) || !zzcz() || !((zzej) obj).zzcz() || hashCode() == obj.hashCode()) {
            return zzey.zza(this, obj);
        }
        return false;
    }

    public int hashCode() {
        return zzey.zza(this);
    }

    public zzee<E> zzct() {
        zzee<E> zzee = this.zznf;
        if (zzee != null) {
            return zzee;
        }
        zzee<E> zzda = zzda();
        this.zznf = zzda;
        return zzda;
    }

    /* access modifiers changed from: package-private */
    public zzee<E> zzda() {
        return zzee.zza(toArray());
    }

    public /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
