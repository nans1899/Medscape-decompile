package com.webmd.webmdrx.viewmodels;

import android.app.Application;
import android.util.Patterns;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import com.facebook.places.model.PlaceFields;
import com.google.gson.JsonObject;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.webmdrx.manager.ApiManager;
import com.webmd.webmdrx.tasks.TaskRequestHelper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import webmd.com.environmentswitcher.EnvironmentManager;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0010j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006`\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0002J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0016J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006J\u0016\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/webmd/webmdrx/viewmodels/SendSmsMailViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "PHONE", "", "RECIPIENT_EMAIL", "isError", "Landroidx/databinding/ObservableBoolean;", "()Landroidx/databinding/ObservableBoolean;", "setError", "(Landroidx/databinding/ObservableBoolean;)V", "isSending", "setSending", "getRequestHeader", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "cardCookie", "isValidEmail", "", "email", "", "isValidPhone", "phone", "sendMail", "", "text", "sendSMS", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SendSmsMailViewModel.kt */
public final class SendSmsMailViewModel extends AndroidViewModel {
    private final String PHONE = PlaceFields.PHONE;
    private final String RECIPIENT_EMAIL = "recipientEmail";
    private ObservableBoolean isError = new ObservableBoolean(false);
    private ObservableBoolean isSending = new ObservableBoolean(false);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SendSmsMailViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
    }

    public final ObservableBoolean isError() {
        return this.isError;
    }

    public final void setError(ObservableBoolean observableBoolean) {
        Intrinsics.checkNotNullParameter(observableBoolean, "<set-?>");
        this.isError = observableBoolean;
    }

    public final ObservableBoolean isSending() {
        return this.isSending;
    }

    public final void setSending(ObservableBoolean observableBoolean) {
        this.isSending = observableBoolean;
    }

    public final boolean isValidEmail(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "email");
        return !StringExtensions.isNullOrEmpty(charSequence.toString()) && Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    public final boolean isValidPhone(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, PlaceFields.PHONE);
        return !StringExtensions.isNullOrEmpty(charSequence.toString()) && StringsKt.trim(charSequence).length() > 10 && Patterns.PHONE.matcher(charSequence).matches();
    }

    public final void sendSMS(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "text");
        Intrinsics.checkNotNullParameter(str2, "cardCookie");
        ApiManager.RetrofitServices provideRetrofitService = ApiManager.getInstance().provideRetrofitService(getApplication());
        if (provideRetrofitService != null) {
            ObservableBoolean observableBoolean = this.isSending;
            Intrinsics.checkNotNull(observableBoolean);
            observableBoolean.set(true);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(this.PHONE, str);
            provideRetrofitService.rxSendSms(getRequestHeader(str2), jsonObject).enqueue(new SendSmsMailViewModel$sendSMS$1(this));
        }
    }

    public final void sendMail(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "text");
        Intrinsics.checkNotNullParameter(str2, "cardCookie");
        ApiManager.RetrofitServices provideRetrofitService = ApiManager.getInstance().provideRetrofitService(getApplication());
        if (provideRetrofitService != null) {
            ObservableBoolean observableBoolean = this.isSending;
            Intrinsics.checkNotNull(observableBoolean);
            observableBoolean.set(true);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(this.RECIPIENT_EMAIL, str);
            provideRetrofitService.rxSendMail(getRequestHeader(str2), jsonObject).enqueue(new SendSmsMailViewModel$sendMail$1(this));
        }
    }

    private final HashMap<String, String> getRequestHeader(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        EnvironmentManager instance = EnvironmentManager.getInstance(getApplication());
        long currentTimeMillis = System.currentTimeMillis();
        Intrinsics.checkNotNullExpressionValue(instance, "environmentManager");
        String searchClientId = instance.getSearchClientId();
        String clientSecretHashForTimestamp = TaskRequestHelper.getClientSecretHashForTimestamp(currentTimeMillis, instance.getSearchSecretId(), searchClientId);
        Map map = hashMap;
        Intrinsics.checkNotNullExpressionValue(clientSecretHashForTimestamp, "secretHash");
        CharSequence charSequence = clientSecretHashForTimestamp;
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) charSequence.charAt(!z ? i : length), 32) <= 0;
            if (!z) {
                if (!z2) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!z2) {
                break;
            } else {
                length--;
            }
        }
        map.put("enc_data", charSequence.subSequence(i, length + 1).toString());
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s", Arrays.copyOf(new Object[]{Long.valueOf(currentTimeMillis)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
        map.put("timestamp", format);
        Intrinsics.checkNotNullExpressionValue(searchClientId, "clientId");
        map.put("client_id", searchClientId);
        map.put("Content-Type", "application/json; charset=utf-8");
        String replace$default = StringsKt.replace$default(StringsKt.replace$default(str, "+", "%20", false, 4, (Object) null), ",", "", false, 4, (Object) null);
        Trace.d("Cookie", "cookieString " + replace$default);
        map.put("cookie", replace$default);
        return hashMap;
    }
}
