package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeaj {
    private static final CopyOnWriteArrayList<zzeak> zzhuj = new CopyOnWriteArrayList<>();

    public static zzeak zzhj(String str) throws GeneralSecurityException {
        Iterator<zzeak> it = zzhuj.iterator();
        while (it.hasNext()) {
            zzeak next = it.next();
            if (next.zzhk(str)) {
                return next;
            }
        }
        String valueOf = String.valueOf(str);
        throw new GeneralSecurityException(valueOf.length() != 0 ? "No KMS client does support: ".concat(valueOf) : new String("No KMS client does support: "));
    }
}
