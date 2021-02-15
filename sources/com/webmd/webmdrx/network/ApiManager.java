package com.webmd.webmdrx.network;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import webmd.com.environmentswitcher.EnvironmentManager;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\f"}, d2 = {"Lcom/webmd/webmdrx/network/ApiManager;", "", "()V", "createRetrofit", "Lretrofit2/Retrofit;", "baseURL", "", "provideRetrofitService", "Lcom/webmd/webmdrx/network/ApiManager$RetrofitServices;", "mContext", "Landroid/content/Context;", "RetrofitServices", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ApiManager.kt */
public final class ApiManager {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/webmd/webmdrx/network/ApiManager$RetrofitServices;", "", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ApiManager.kt */
    public interface RetrofitServices {
    }

    private final Retrofit createRetrofit(String str) {
        Retrofit build = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Retrofit.Builder()\n     …\n                .build()");
        return build;
    }

    public final RetrofitServices provideRetrofitService(Context context) {
        if (context == null) {
            return null;
        }
        String requestLink = EnvironmentManager.getInstance(context).getRequestLink("WBMD_PILL_ID_SEARCH");
        Intrinsics.checkNotNullExpressionValue(requestLink, "baseURL");
        return (RetrofitServices) createRetrofit(requestLink).create(RetrofitServices.class);
    }
}
