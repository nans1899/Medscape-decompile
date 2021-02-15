package com.webmd.wbmdproffesionalauthentication.license;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;
import com.appboy.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u001e\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J$\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001aH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/webmd/wbmdproffesionalauthentication/license/LicencesValidator;", "Lretrofit2/Callback;", "Lcom/webmd/wbmdproffesionalauthentication/license/LicenseResponse;", "()V", "BASE_URL", "", "getBASE_URL", "()Ljava/lang/String;", "validationResult", "Landroidx/lifecycle/MutableLiveData;", "getValidationResult", "()Landroidx/lifecycle/MutableLiveData;", "setValidationResult", "(Landroidx/lifecycle/MutableLiveData;)V", "makeValidationRequest", "", "environment", "countryCode", "userInput", "onFailure", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "wbmdprofessionalauthenticaion_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LicencesValidator.kt */
public final class LicencesValidator implements Callback<LicenseResponse> {
    private final String BASE_URL = "https://profreg%s.medscape.com/px/";
    private MutableLiveData<LicenseResponse> validationResult = new MutableLiveData<>();

    public final String getBASE_URL() {
        return this.BASE_URL;
    }

    public final MutableLiveData<LicenseResponse> getValidationResult() {
        return this.validationResult;
    }

    public final void setValidationResult(MutableLiveData<LicenseResponse> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.validationResult = mutableLiveData;
    }

    public final void makeValidationRequest(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "environment");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE);
        Intrinsics.checkNotNullParameter(str3, "userInput");
        Gson create = new GsonBuilder().create();
        Retrofit.Builder builder = new Retrofit.Builder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(this.BASE_URL, Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
        ((LicenceService) builder.baseUrl(format).addConverterFactory(GsonConverterFactory.create(create)).build().create(LicenceService.class)).validateLicense(str2, str3).enqueue(this);
    }

    public void onFailure(Call<LicenseResponse> call, Throwable th) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(th, Constants.APPBOY_PUSH_TITLE_KEY);
        this.validationResult.postValue(new LicenseResponse(101, "Connection Error. Please try again", 1, false));
    }

    public void onResponse(Call<LicenseResponse> call, Response<LicenseResponse> response) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.isSuccessful()) {
            this.validationResult.postValue(response.body());
        } else {
            this.validationResult.postValue(new LicenseResponse(response.code(), "Unexpected error occurred. Please try again", 1, false));
        }
    }
}
