package com.wbmd.wbmddatacompliance.http;

import com.wbmd.wbmddatacompliance.model.GeoResponse;
import com.wbmd.wbmddatacompliance.model.GeoResponseLegacy;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IGeoService {
    @GET("api/directories/service.svc/location/Europe")
    @Headers({"Accept: application/json"})
    Call<GeoResponseLegacy> makeVisitorEULegacyRequest();

    @GET("api/directories/service.svc/location/Europe")
    @Headers({"Accept: application/json"})
    Call<GeoResponse> makeVisitorEURequest();
}
