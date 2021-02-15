package com.google.android.gms.internal.vision;

import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzbj {
    private static final ArrayMap<String, Uri> zzgp = new ArrayMap<>();

    public static synchronized Uri getContentProviderUri(String str) {
        Uri uri;
        synchronized (zzbj.class) {
            uri = zzgp.get(str);
            if (uri == null) {
                String valueOf = String.valueOf(Uri.encode(str));
                uri = Uri.parse(valueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(valueOf) : new String("content://com.google.android.gms.phenotype/"));
                zzgp.put(str, uri);
            }
        }
        return uri;
    }
}
