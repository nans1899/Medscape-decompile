package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class zzapn {
    public static zzaph zzdt(String str) throws RemoteException {
        try {
            return new zzapq((RtbAdapter) Class.forName(str, false, zzapn.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (Throwable unused) {
            throw new RemoteException();
        }
    }
}
