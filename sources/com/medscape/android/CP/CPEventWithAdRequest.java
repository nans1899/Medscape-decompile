package com.medscape.android.CP;

import androidx.core.app.NotificationCompat;
import com.appboy.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J$\u0010\u000e\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/medscape/android/CP/CPEventWithAdRequest;", "Lretrofit2/Callback;", "Lokhttp3/ResponseBody;", "()V", "BASE_URL", "", "getBASE_URL", "()Ljava/lang/String;", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "sendCpEventForAd", "cookies", "event", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CPEventWithAdRequest.kt */
public final class CPEventWithAdRequest implements Callback<ResponseBody> {
    private final String BASE_URL = "https://api.medscape.com/cp/";

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

    public final void sendCpEventForAd(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "cookies");
        CPEventAdService cPEventAdService = (CPEventAdService) new Retrofit.Builder().baseUrl(this.BASE_URL).build().create(CPEventAdService.class);
        if (str2 == null) {
            str2 = "";
        }
        cPEventAdService.sendCpEvent(str, str2).enqueue(this);
    }
}
