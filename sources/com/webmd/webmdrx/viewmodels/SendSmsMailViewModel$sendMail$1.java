package com.webmd.webmdrx.viewmodels;

import androidx.core.app.NotificationCompat;
import androidx.databinding.ObservableBoolean;
import com.appboy.Constants;
import com.webmd.webmdrx.models.SmsResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J$\u0010\t\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"com/webmd/webmdrx/viewmodels/SendSmsMailViewModel$sendMail$1", "Lretrofit2/Callback;", "Lcom/webmd/webmdrx/models/SmsResponse;", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SendSmsMailViewModel.kt */
public final class SendSmsMailViewModel$sendMail$1 implements Callback<SmsResponse> {
    final /* synthetic */ SendSmsMailViewModel this$0;

    SendSmsMailViewModel$sendMail$1(SendSmsMailViewModel sendSmsMailViewModel) {
        this.this$0 = sendSmsMailViewModel;
    }

    public void onFailure(Call<SmsResponse> call, Throwable th) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(th, Constants.APPBOY_PUSH_TITLE_KEY);
        th.printStackTrace();
        ObservableBoolean isSending = this.this$0.isSending();
        Intrinsics.checkNotNull(isSending);
        isSending.set(false);
    }

    public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.isSuccessful() && response.body() != null) {
            SmsResponse body = response.body();
            Intrinsics.checkNotNull(body);
            Intrinsics.checkNotNullExpressionValue(body, "response.body()!!");
            if (body.getStatus().equals("ok")) {
                ObservableBoolean isSending = this.this$0.isSending();
                Intrinsics.checkNotNull(isSending);
                isSending.set(false);
            }
        }
    }
}
