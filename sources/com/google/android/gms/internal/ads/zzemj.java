package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzemj extends zzemk<FieldDescriptorType, Object> {
    zzemj(int i) {
        super(i, (zzemj) null);
    }

    public final void zzbec() {
        if (!isImmutable()) {
            for (int i = 0; i < zzbim(); i++) {
                Map.Entry zzhp = zzhp(i);
                if (((zzeju) zzhp.getKey()).zzbgc()) {
                    zzhp.setValue(Collections.unmodifiableList((List) zzhp.getValue()));
                }
            }
            for (Map.Entry entry : zzbin()) {
                if (((zzeju) entry.getKey()).zzbgc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzbec();
    }
}
