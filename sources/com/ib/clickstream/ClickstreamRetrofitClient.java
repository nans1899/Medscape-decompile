package com.ib.clickstream;

import androidx.core.app.NotificationCompat;
import com.appboy.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J$\u0010\u000e\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0016J$\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/ib/clickstream/ClickstreamRetrofitClient;", "Lretrofit2/Callback;", "Lokhttp3/ResponseBody;", "()V", "BASE_URL", "", "getBASE_URL", "()Ljava/lang/String;", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "sendMetrics", "metricBody", "Lorg/json/JSONObject;", "partyId", "callback", "clickstream_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClickstreamRetrofitClient.kt */
public final class ClickstreamRetrofitClient implements Callback<ResponseBody> {
    private final String BASE_URL = "https://ibclick.stream/";

    public void onFailure(Call<ResponseBody> call, Throwable th) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(th, Constants.APPBOY_PUSH_TITLE_KEY);
    }

    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(response, "response");
    }

    public final String getBASE_URL() {
        return this.BASE_URL;
    }

    public final void sendMetrics(JSONObject jSONObject, String str, Callback<ResponseBody> callback) {
        Intrinsics.checkNotNullParameter(jSONObject, "metricBody");
        Intrinsics.checkNotNullParameter(str, "partyId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        RequestBody create = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jSONObject.toString());
        Intrinsics.checkNotNullExpressionValue(create, "body");
        ((ClickstreamMetricService) new Retrofit.Builder().baseUrl(this.BASE_URL).build().create(ClickstreamMetricService.class)).sendIBEvent(create, str).enqueue(callback);
    }
}
