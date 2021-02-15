package com.google.firebase.appindexing.internal;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.firebase.iid.MessengerIpcClient;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzz extends zzaa {
    private final Context zzcs;
    private final ContentResolver zzfu;

    public zzz(Context context) {
        this.zzcs = context;
        this.zzfu = context.getContentResolver();
    }

    public final void grantSlicePermission(String str, Uri uri) {
        ContentProviderClient acquireUnstableContentProviderClient = this.zzfu.acquireUnstableContentProviderClient(uri);
        try {
            Bundle bundle = new Bundle();
            bundle.putParcelable("slice_uri", uri);
            bundle.putString("provider_pkg", this.zzcs.getPackageName());
            bundle.putString(MessengerIpcClient.KEY_PACKAGE, str);
            acquireUnstableContentProviderClient.call("grant_perms", (String) null, bundle);
        } catch (RemoteException e) {
            Log.e("ContentValues", "Unable to get slice descendants", e);
        } finally {
            acquireUnstableContentProviderClient.release();
        }
    }
}
