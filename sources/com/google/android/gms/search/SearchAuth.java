package com.google.android.gms.search;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.icing.zzap;
import com.google.android.gms.internal.icing.zzas;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public class SearchAuth {
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.ClientKey<zzap> CLIENT_KEY;
    public static final SearchAuthApi SearchAuthApi = new zzas();
    private static final Api.AbstractClientBuilder<zzap, Api.ApiOptions.NoOptions> zzbm = new zzb();

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static class StatusCodes {
        public static final int AUTH_DISABLED = 10000;
        public static final int AUTH_THROTTLED = 10001;
        public static final int DEVELOPER_ERROR = 10;
        public static final int INTERNAL_ERROR = 8;
        public static final int SUCCESS = 0;
    }

    private SearchAuth() {
    }

    static {
        Api.ClientKey<zzap> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        API = new Api<>("SearchAuth.API", zzbm, clientKey);
    }
}
