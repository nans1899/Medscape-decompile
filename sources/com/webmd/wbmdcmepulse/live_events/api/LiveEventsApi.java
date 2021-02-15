package com.webmd.wbmdcmepulse.live_events.api;

import android.content.Context;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000 \t2\u00020\u0001:\u0001\tJ(\u0010\u0002\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00060\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH'¨\u0006\n"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/api/LiveEventsApi;", "", "getLiveEvents", "Lretrofit2/Call;", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Lkotlin/collections/ArrayList;", "specialty", "", "Companion", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsApi.kt */
public interface LiveEventsApi {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String QA01_URL = "https://www.qa01.medscape.org/ws/getLiveEventFragments/";

    @GET("{specialty}")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    Call<ArrayList<LiveEventItem>> getLiveEvents(@Path("specialty") String str);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/api/LiveEventsApi$Companion;", "", "()V", "DEFAULT_URL", "", "QA01_URL", "create", "Lcom/webmd/wbmdcmepulse/live_events/api/LiveEventsApi;", "url", "context", "Landroid/content/Context;", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: LiveEventsApi.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String DEFAULT_URL = "https://www.medscape.org/ws/getLiveEventFragments/";
        public static final String QA01_URL = "https://www.qa01.medscape.org/ws/getLiveEventFragments/";

        private Companion() {
        }

        public final LiveEventsApi create(String str, Context context) {
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(context, "context");
            if (str.length() == 0) {
                str = SharedPreferenceProvider.get().getString(Constants.LIVE_EVENT_REGISTER_DEFAULT_URL, DEFAULT_URL, context);
                Intrinsics.checkNotNullExpressionValue(str, "SharedPreferenceProvider…RL, DEFAULT_URL, context)");
            }
            HttpUrl parse = HttpUrl.Companion.parse(str);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(LiveEventsApi$Companion$create$logger$1.INSTANCE);
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
            Object create = new Retrofit.Builder().baseUrl(parse).client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).readTimeout(5, TimeUnit.SECONDS).build()).addConverterFactory(GsonConverterFactory.create()).build().create(LiveEventsApi.class);
            Intrinsics.checkNotNullExpressionValue(create, "Retrofit.Builder()\n     …iveEventsApi::class.java)");
            return (LiveEventsApi) create;
        }
    }
}
