package com.webmd.medscape.live.explorelivevents.network;

import com.google.common.net.HttpHeaders;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/network/BasicAuthInterceptor;", "Lokhttp3/Interceptor;", "username", "", "password", "(Ljava/lang/String;Ljava/lang/String;)V", "credentials", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BasicAuthInterceptor.kt */
public final class BasicAuthInterceptor implements Interceptor {
    private String credentials;

    public BasicAuthInterceptor(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "username");
        Intrinsics.checkNotNullParameter(str2, "password");
        this.credentials = Credentials.basic$default(str, str2, (Charset) null, 4, (Object) null);
    }

    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(chain.request().newBuilder().header(HttpHeaders.AUTHORIZATION, this.credentials).build());
    }
}
