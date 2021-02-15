package com.medscape.android.landingfeed.api;

import com.medscape.android.landingfeed.model.FeedItems;
import com.medscape.android.landingfeed.model.LandingFeedApiRequest;
import kotlin.Metadata;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \n2\u00020\u0001:\u0001\nJ,\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\tH'¨\u0006\u000b"}, d2 = {"Lcom/medscape/android/landingfeed/api/LandingFeedApi;", "", "getLandingContent", "Lretrofit2/Call;", "Lcom/medscape/android/landingfeed/model/FeedItems;", "feedPath", "", "Cookie", "request", "Lcom/medscape/android/landingfeed/model/LandingFeedApiRequest;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LandingFeedApi.kt */
public interface LandingFeedApi {
    public static final String CME = "CME";
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String DEV01_URL = "http://contentrecommendationservice-app-dev01.prf.ma1.medscape.com:8080/";
    public static final String FEED_PATH = "/contentrecommendationservice/contentrecommendations";
    public static final String HOME = "splash";
    public static final String NEWS = "NEWS";
    public static final String PROD_URL = "https://api.medscape.com/";
    public static final String QA01_URL = "https://api.qa01.medscape.com/";

    @POST("{feedPath}")
    @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
    Call<FeedItems> getLandingContent(@Path(encoded = true, value = "feedPath") String str, @Header("Cookie") String str2, @Body LandingFeedApiRequest landingFeedApiRequest);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/medscape/android/landingfeed/api/LandingFeedApi$Companion;", "", "()V", "CME", "", "DEV01_URL", "FEED_PATH", "HOME", "NEWS", "PROD_URL", "QA01_URL", "create", "Lcom/medscape/android/landingfeed/api/LandingFeedApi;", "url", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: LandingFeedApi.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String CME = "CME";
        public static final String DEV01_URL = "http://contentrecommendationservice-app-dev01.prf.ma1.medscape.com:8080/";
        public static final String FEED_PATH = "/contentrecommendationservice/contentrecommendations";
        public static final String HOME = "splash";
        public static final String NEWS = "NEWS";
        public static final String PROD_URL = "https://api.medscape.com/";
        public static final String QA01_URL = "https://api.qa01.medscape.com/";

        private Companion() {
        }

        public final LandingFeedApi create(String str) {
            CharSequence charSequence = str;
            if (charSequence == null || charSequence.length() == 0) {
                return null;
            }
            HttpUrl parse = HttpUrl.Companion.parse(str);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(LandingFeedApi$Companion$create$logger$1.INSTANCE);
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
            return (LandingFeedApi) new Retrofit.Builder().baseUrl(parse).client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()).addConverterFactory(GsonConverterFactory.create()).build().create(LandingFeedApi.class);
        }
    }
}
