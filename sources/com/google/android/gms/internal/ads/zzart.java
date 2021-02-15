package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzart extends IInterface {
    void onError(String str) throws RemoteException;

    void onSuccess(List<Uri> list) throws RemoteException;
}
