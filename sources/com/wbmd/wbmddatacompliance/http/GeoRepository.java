package com.wbmd.wbmddatacompliance.http;

import android.content.Context;
import com.wbmd.wbmddatacompliance.callbacks.IEuCallback;
import com.wbmd.wbmddatacompliance.callbacks.IGDPRFailLoggingCallback;
import com.wbmd.wbmddatacompliance.model.GeoResponse;
import com.wbmd.wbmddatacompliance.model.GeoResponseLegacy;
import com.wbmd.wbmddatacompliance.parsers.GeoResponseParser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoRepository {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public IGDPRFailLoggingCallback mFailLogCallback;
    private IGeoService mGeoService;

    public GeoRepository(IGeoService iGeoService, Context context, IGDPRFailLoggingCallback iGDPRFailLoggingCallback) {
        this.mGeoService = iGeoService;
        this.mContext = context;
        this.mFailLogCallback = iGDPRFailLoggingCallback;
    }

    public void makeNewGeoApiRequest(final IEuCallback iEuCallback) {
        try {
            this.mGeoService.makeVisitorEURequest().enqueue(new Callback<GeoResponse>() {
                public void onResponse(Call<GeoResponse> call, Response<GeoResponse> response) {
                    if (response == null || response.body() == null || response.body().getData() == null || response.body().getData().getEdgecountrycode() == null) {
                        if (GeoRepository.this.mFailLogCallback != null) {
                            GeoRepository.this.mFailLogCallback.sendErrorLog("Empty Response/Bad Data", "makeNewGeoApiRequest_response_fail", (Throwable) null);
                        }
                        iEuCallback.onError();
                        return;
                    }
                    try {
                        iEuCallback.onIsEuResponse(new GeoResponseParser(response.body(), GeoRepository.this.mContext, GeoRepository.this.mFailLogCallback).parseIsEu());
                    } catch (Exception e) {
                        if (GeoRepository.this.mFailLogCallback != null) {
                            GeoRepository.this.mFailLogCallback.sendErrorLog((String) null, "makeNewGeoApiRequest_resp_exception", e);
                        }
                        iEuCallback.onError();
                    }
                }

                public void onFailure(Call<GeoResponse> call, Throwable th) {
                    if (GeoRepository.this.mFailLogCallback != null) {
                        GeoRepository.this.mFailLogCallback.sendErrorLog((String) null, "makeNewGeoApiRequest_call_fail", th);
                    }
                    iEuCallback.onError();
                }
            });
        } catch (Exception e) {
            IGDPRFailLoggingCallback iGDPRFailLoggingCallback = this.mFailLogCallback;
            if (iGDPRFailLoggingCallback != null) {
                iGDPRFailLoggingCallback.sendErrorLog((String) null, "makeNewGeoApiRequest_exception", e);
            }
            iEuCallback.onError();
        }
    }

    public void makeGeoApiRequest(final IEuCallback iEuCallback) {
        try {
            this.mGeoService.makeVisitorEULegacyRequest().enqueue(new Callback<GeoResponseLegacy>() {
                public void onResponse(Call<GeoResponseLegacy> call, Response<GeoResponseLegacy> response) {
                    try {
                        GeoResponseParser geoResponseParser = new GeoResponseParser(response.body(), GeoRepository.this.mContext, GeoRepository.this.mFailLogCallback);
                        if (geoResponseParser.isValidResponse()) {
                            iEuCallback.onIsEuResponse(geoResponseParser.parseIsEu());
                        } else {
                            iEuCallback.onError();
                        }
                    } catch (Exception e) {
                        if (GeoRepository.this.mFailLogCallback != null) {
                            GeoRepository.this.mFailLogCallback.sendErrorLog((String) null, "makeGeoApiRequest_resp_exception", e);
                        }
                        GeoRepository.this.makeNewGeoApiRequest(iEuCallback);
                    }
                }

                public void onFailure(Call<GeoResponseLegacy> call, Throwable th) {
                    if (GeoRepository.this.mFailLogCallback != null) {
                        GeoRepository.this.mFailLogCallback.sendErrorLog((String) null, "makeGeoApiRequest_call_fail", th);
                    }
                    GeoRepository.this.makeNewGeoApiRequest(iEuCallback);
                }
            });
        } catch (Exception e) {
            IGDPRFailLoggingCallback iGDPRFailLoggingCallback = this.mFailLogCallback;
            if (iGDPRFailLoggingCallback != null) {
                iGDPRFailLoggingCallback.sendErrorLog((String) null, "makeGeoApiRequest_exception", e);
            }
            makeNewGeoApiRequest(iEuCallback);
        }
    }
}
