package com.medscape.android.CP;

import kotlin.Metadata;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H'¨\u0006\b"}, d2 = {"Lcom/medscape/android/CP/CPEventAdService;", "", "sendCpEvent", "Lretrofit2/Call;", "Lokhttp3/ResponseBody;", "cookies", "", "eventUrl", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CPEventAdService.kt */
public interface CPEventAdService {
    @GET("events.json")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ResponseBody> sendCpEvent(@Header("Cookies") String str, @Query("event") String str2);
}
