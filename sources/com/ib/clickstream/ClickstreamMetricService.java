package com.ib.clickstream;

import kotlin.Metadata;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b`\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH'¨\u0006\t"}, d2 = {"Lcom/ib/clickstream/ClickstreamMetricService;", "", "sendIBEvent", "Lretrofit2/Call;", "Lokhttp3/ResponseBody;", "requestBody", "Lokhttp3/RequestBody;", "partyId", "", "clickstream_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClickstreamMetricService.kt */
public interface ClickstreamMetricService {
    @POST("event")
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> sendIBEvent(@Body RequestBody requestBody, @Query("p") String str);
}
