package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzepd<T> implements zzeoy<Set<T>> {
    private static final zzeoy<Set<Object>> zziut = zzeox.zzba(Collections.emptySet());
    private final List<zzeph<T>> zziuu;
    private final List<zzeph<Collection<T>>> zziuv;

    public static <T> zzepf<T> zzar(int i, int i2) {
        return new zzepf<>(i, i2);
    }

    private zzepd(List<zzeph<T>> list, List<zzeph<Collection<T>>> list2) {
        this.zziuu = list;
        this.zziuv = list2;
    }

    public final /* synthetic */ Object get() {
        int size = this.zziuu.size();
        ArrayList arrayList = new ArrayList(this.zziuv.size());
        int size2 = this.zziuv.size();
        for (int i = 0; i < size2; i++) {
            Collection collection = (Collection) this.zziuv.get(i).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet zzhz = zzeot.zzhz(size);
        int size3 = this.zziuu.size();
        for (int i2 = 0; i2 < size3; i2++) {
            zzhz.add(zzepe.checkNotNull(this.zziuu.get(i2).get()));
        }
        int size4 = arrayList.size();
        for (int i3 = 0; i3 < size4; i3++) {
            for (Object checkNotNull : (Collection) arrayList.get(i3)) {
                zzhz.add(zzepe.checkNotNull(checkNotNull));
            }
        }
        return Collections.unmodifiableSet(zzhz);
    }
}
