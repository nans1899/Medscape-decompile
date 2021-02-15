package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zze extends GmsClient<zzr> {
    static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.ClientKey<zze> CLIENT_KEY = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zze, Api.ApiOptions.NoOptions> zzev;

    public zze(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 113, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    public final int getMinApkVersion() {
        return 12600000;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.firebase.appindexing.internal.IAppIndexingService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.icing.APP_INDEXING_SERVICE";
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.appindexing.internal.IAppIndexingService");
        if (queryLocalInterface instanceof zzr) {
            return (zzr) queryLocalInterface;
        }
        return new zzu(iBinder);
    }

    static {
        zzd zzd = new zzd();
        zzev = zzd;
        API = new Api<>("AppIndexing.API", zzd, CLIENT_KEY);
    }
}
