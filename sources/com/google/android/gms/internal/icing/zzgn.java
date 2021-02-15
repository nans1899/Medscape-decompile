package com.google.android.gms.internal.icing;

import java.util.List;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzgn extends RuntimeException {
    private final List<String> zzoe = null;

    public zzgn(zzfh zzfh) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }
}
