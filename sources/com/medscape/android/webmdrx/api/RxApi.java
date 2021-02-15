package com.medscape.android.webmdrx.api;

import com.medscape.android.webmdrx.model.RxDrugData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Path;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000 \t2\u00020\u0001:\u0001\tJ.\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0001\u0010\b\u001a\u00020\u0007H'¨\u0006\n"}, d2 = {"Lcom/medscape/android/webmdrx/api/RxApi;", "", "findDrug", "Lretrofit2/Call;", "Lcom/medscape/android/webmdrx/model/RxDrugData;", "headers", "", "", "genericID", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: RxApi.kt */
public interface RxApi {
    public static final String BaseUrl = "https://www.webmd.com/";
    public static final String ClientID = "3454df96-c7a5-47bb-a74e-890fb3c30a0d";
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String SecretID = "8be2e6a4-8099-482c-8dac-ccd022581419";

    @GET("/search/2/api/rx/medscape/find/{genericID}?app=medscape&app_id=com.medscape.android")
    @Headers({"Accept: application/json"})
    Call<RxDrugData> findDrug(@HeaderMap Map<String, String> map, @Path("genericID") String str);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/medscape/android/webmdrx/api/RxApi$Companion;", "", "()V", "BaseUrl", "", "ClientID", "SecretID", "create", "Lcom/medscape/android/webmdrx/api/RxApi;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: RxApi.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String BaseUrl = "https://www.webmd.com/";
        public static final String ClientID = "3454df96-c7a5-47bb-a74e-890fb3c30a0d";
        public static final String SecretID = "8be2e6a4-8099-482c-8dac-ccd022581419";

        private Companion() {
        }

        public final RxApi create() {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(RxApi$Companion$create$logger$1.INSTANCE);
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
            Object create = new Retrofit.Builder().baseUrl("https://www.webmd.com/").client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()).addConverterFactory(GsonConverterFactory.create()).build().create(RxApi.class);
            Intrinsics.checkNotNullExpressionValue(create, "Retrofit.Builder()\n     …create(RxApi::class.java)");
            return (RxApi) create;
        }
    }
}
