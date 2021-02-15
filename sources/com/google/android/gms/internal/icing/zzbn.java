package com.google.android.gms.internal.icing;

import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzbn {
    private static final ArrayMap<String, Uri> zzcv = new ArrayMap<>();

    public static synchronized Uri zzl(String str) {
        Uri uri;
        synchronized (zzbn.class) {
            uri = zzcv.get(str);
            if (uri == null) {
                String valueOf = String.valueOf(Uri.encode(str));
                uri = Uri.parse(valueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(valueOf) : new String("content://com.google.android.gms.phenotype/"));
                zzcv.put(str, uri);
            }
        }
        return uri;
    }
}
