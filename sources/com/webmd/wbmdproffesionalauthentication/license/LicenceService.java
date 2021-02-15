package com.webmd.wbmdproffesionalauthentication.license;

import kotlin.Metadata;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H'¨\u0006\b"}, d2 = {"Lcom/webmd/wbmdproffesionalauthentication/license/LicenceService;", "", "validateLicense", "Lretrofit2/Call;", "Lcom/webmd/wbmdproffesionalauthentication/license/LicenseResponse;", "country", "", "userInput", "wbmdprofessionalauthenticaion_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LicenceService.kt */
public interface LicenceService {
    @GET("validatelicense/license_{country}/{input}")
    @Headers({"Content-Type: application/json"})
    Call<LicenseResponse> validateLicense(@Path("country") String str, @Path("input") String str2);
}
