package com.google.android.gms.internal.icing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.tapstream.sdk.http.RequestBuilders;
import java.util.List;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzaj implements AppIndexApi {
    private static final String TAG = zzaj.class.getSimpleName();

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static abstract class zzd<T extends Result> extends zza<Status> {
        public zzd(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    static abstract class zza<T extends Result> extends BaseImplementation.ApiMethodImpl<T, zzah> {
        public zza(GoogleApiClient googleApiClient) {
            super((Api<?>) zzf.zzg, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public abstract void zza(zzaa zzaa) throws RemoteException;

        /* access modifiers changed from: protected */
        public /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
            zza((zzaa) ((zzah) anyClient).getService());
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static final class zzc extends zzae<Status> {
        public zzc(BaseImplementation.ResultHolder<Status> resultHolder) {
            super(resultHolder);
        }

        public final void zza(Status status) {
            this.zzas.setResult(status);
        }
    }

    public final PendingResult<Status> zza(GoogleApiClient googleApiClient, zzw... zzwArr) {
        return googleApiClient.enqueue(new zzai(this, googleApiClient, zzwArr));
    }

    public final PendingResult<Status> view(GoogleApiClient googleApiClient, Activity activity, Intent intent, String str, Uri uri, List<AppIndexApi.AppIndexingLink> list) {
        String packageName = googleApiClient.getContext().getPackageName();
        if (list != null) {
            for (AppIndexApi.AppIndexingLink appIndexingLink : list) {
                zzb((String) null, appIndexingLink.appIndexingUrl);
            }
        }
        zzw[] zzwArr = {new zzw(packageName, intent, str, uri, (String) null, list, 1)};
        GoogleApiClient googleApiClient2 = googleApiClient;
        return zza(googleApiClient, zzwArr);
    }

    @Deprecated
    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    static final class zzb implements AppIndexApi.ActionResult {
        private zzaj zzau;
        private PendingResult<Status> zzav;
        private Action zzaw;

        zzb(zzaj zzaj, PendingResult<Status> pendingResult, Action action) {
            this.zzau = zzaj;
            this.zzav = pendingResult;
            this.zzaw = action;
        }

        public final PendingResult<Status> end(GoogleApiClient googleApiClient) {
            String packageName = googleApiClient.getContext().getPackageName();
            zzw zza = zzag.zza(this.zzaw, System.currentTimeMillis(), packageName, 2);
            return this.zzau.zza(googleApiClient, zza);
        }

        public final PendingResult<Status> getPendingResult() {
            return this.zzav;
        }
    }

    public final PendingResult<Status> viewEnd(GoogleApiClient googleApiClient, Activity activity, Intent intent) {
        return zza(googleApiClient, new zzz().zza(zzw.zza(googleApiClient.getContext().getPackageName(), intent)).zza(System.currentTimeMillis()).zzb(0).zzc(2).zzd());
    }

    public static Intent zza(String str, Uri uri) {
        zzb(str, uri);
        if (zza(uri)) {
            return new Intent("android.intent.action.VIEW", uri);
        }
        if (zzb(uri)) {
            List<String> pathSegments = uri.getPathSegments();
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(pathSegments.get(0));
            if (pathSegments.size() > 1) {
                builder.authority(pathSegments.get(1));
                for (int i = 2; i < pathSegments.size(); i++) {
                    builder.appendPath(pathSegments.get(i));
                }
            } else {
                String str2 = TAG;
                String valueOf = String.valueOf(uri);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 88);
                sb.append("The app URI must have the format: android-app://<package_name>/<scheme>/<path>. But got ");
                sb.append(valueOf);
                Log.e(str2, sb.toString());
            }
            builder.encodedQuery(uri.getEncodedQuery());
            builder.encodedFragment(uri.getEncodedFragment());
            return new Intent("android.intent.action.VIEW", builder.build());
        }
        String valueOf2 = String.valueOf(uri);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 70);
        sb2.append("appIndexingUri is neither an HTTP(S) URL nor an \"android-app://\" URL: ");
        sb2.append(valueOf2);
        throw new RuntimeException(sb2.toString());
    }

    private static void zzb(String str, Uri uri) {
        if (zza(uri)) {
            if (uri.getHost().isEmpty()) {
                String valueOf = String.valueOf(uri);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 98);
                sb.append("AppIndex: The web URL must have a host (follow the format http(s)://<host>/<path>). Provided URI: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        } else if (!zzb(uri)) {
            String valueOf2 = String.valueOf(uri);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 176);
            sb2.append("AppIndex: The URI scheme must either be 'http(s)' or 'android-app'. If the latter, it must follow the format 'android-app://<package_name>/<scheme>/<host_path>'. Provided URI: ");
            sb2.append(valueOf2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (str == null || str.equals(uri.getHost())) {
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.isEmpty() || pathSegments.get(0).isEmpty()) {
                String valueOf3 = String.valueOf(uri);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 128);
                sb3.append("AppIndex: The app URI scheme must exist and follow the format android-app://<package_name>/<scheme>/<host_path>). Provided URI: ");
                sb3.append(valueOf3);
                throw new IllegalArgumentException(sb3.toString());
            }
        } else {
            String valueOf4 = String.valueOf(uri);
            StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf4).length() + 150);
            sb4.append("AppIndex: The android-app URI host must match the package name and follow the format android-app://<package_name>/<scheme>/<host_path>. Provided URI: ");
            sb4.append(valueOf4);
            throw new IllegalArgumentException(sb4.toString());
        }
    }

    private static boolean zza(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equals(scheme) || RequestBuilders.DEFAULT_SCHEME.equals(scheme);
    }

    private static boolean zzb(Uri uri) {
        return "android-app".equals(uri.getScheme());
    }

    public final PendingResult<Status> view(GoogleApiClient googleApiClient, Activity activity, Uri uri, String str, Uri uri2, List<AppIndexApi.AppIndexingLink> list) {
        String packageName = googleApiClient.getContext().getPackageName();
        zzb(packageName, uri);
        return view(googleApiClient, activity, zza(packageName, uri), str, uri2, list);
    }

    public final PendingResult<Status> viewEnd(GoogleApiClient googleApiClient, Activity activity, Uri uri) {
        return viewEnd(googleApiClient, activity, zza(googleApiClient.getContext().getPackageName(), uri));
    }

    public final AppIndexApi.ActionResult action(GoogleApiClient googleApiClient, Action action) {
        return new zzb(this, zza(googleApiClient, action, 1), action);
    }

    public final PendingResult<Status> start(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 1);
    }

    public final PendingResult<Status> end(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 2);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, Action action, int i) {
        return zza(googleApiClient, zzag.zza(action, System.currentTimeMillis(), googleApiClient.getContext().getPackageName(), i));
    }
}
