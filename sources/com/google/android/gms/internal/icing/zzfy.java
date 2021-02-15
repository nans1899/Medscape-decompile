package com.google.android.gms.internal.icing;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzfy extends zzfz<FieldDescriptorType, Object> {
    zzfy(int i) {
        super(i, (zzfy) null);
    }

    public final void zzai() {
        if (!isImmutable()) {
            for (int i = 0; i < zzdd(); i++) {
                Map.Entry zzaj = zzaj(i);
                if (((zzdu) zzaj.getKey()).zzbi()) {
                    zzaj.setValue(Collections.unmodifiableList((List) zzaj.getValue()));
                }
            }
            for (Map.Entry entry : zzde()) {
                if (((zzdu) entry.getKey()).zzbi()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzai();
    }
}
