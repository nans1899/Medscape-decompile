package com.wbmd.wbmddatacompliance.parsers;

import android.content.Context;
import com.google.gson.Gson;
import com.wbmd.wbmddatacompliance.R;
import com.wbmd.wbmddatacompliance.callbacks.IGDPRFailLoggingCallback;
import com.wbmd.wbmddatacompliance.model.Country;
import com.wbmd.wbmddatacompliance.model.GeoResponse;
import com.wbmd.wbmddatacompliance.model.GeoResponseData;
import com.wbmd.wbmddatacompliance.model.GeoResponseLegacy;
import com.wbmd.wbmddatacompliance.utils.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeoResponseParser {
    private List<Country> mEuCountries = new ArrayList();
    private IGDPRFailLoggingCallback mFailLogCallback;
    private GeoResponse mGeoResponse;
    private GeoResponseLegacy mGeoResponseLegacy;

    public GeoResponseParser(GeoResponse geoResponse, Context context, IGDPRFailLoggingCallback iGDPRFailLoggingCallback) throws Exception {
        this.mGeoResponse = geoResponse;
        this.mFailLogCallback = iGDPRFailLoggingCallback;
        init(context);
    }

    public GeoResponseParser(GeoResponseLegacy geoResponseLegacy, Context context, IGDPRFailLoggingCallback iGDPRFailLoggingCallback) throws Exception {
        this.mGeoResponseLegacy = geoResponseLegacy;
        this.mFailLogCallback = iGDPRFailLoggingCallback;
        init(context);
    }

    private boolean parseNewIsEu() {
        Gson gson = new Gson();
        GeoResponseLegacy geoResponseLegacy = this.mGeoResponseLegacy;
        if (geoResponseLegacy != null) {
            try {
                return checkForEUCountries((GeoResponseData) gson.fromJson(geoResponseLegacy.getData(), GeoResponseData.class));
            } catch (Exception e) {
                IGDPRFailLoggingCallback iGDPRFailLoggingCallback = this.mFailLogCallback;
                if (iGDPRFailLoggingCallback != null) {
                    iGDPRFailLoggingCallback.sendErrorLog((String) null, "parseNewIsEu_exception", e);
                }
            }
        }
        return false;
    }

    private boolean checkForEUCountries(GeoResponseData geoResponseData) {
        if (geoResponseData == null || geoResponseData.getEdgetwolettercountry() == null) {
            return false;
        }
        for (Country edgetwolettercountry : this.mEuCountries) {
            if (edgetwolettercountry.getEdgetwolettercountry().equalsIgnoreCase(geoResponseData.getEdgetwolettercountry())) {
                return true;
            }
        }
        return false;
    }

    public boolean parseIsEu() {
        GeoResponse geoResponse = this.mGeoResponse;
        if (geoResponse != null) {
            return checkForEUCountries(geoResponse.getData());
        }
        return parseNewIsEu();
    }

    private void init(Context context) throws Exception {
        this.mEuCountries = Arrays.asList((Country[]) new Gson().fromJson(Util.readResource(R.raw.eu, context), Country[].class));
    }

    public boolean isValidResponse() {
        GeoResponseLegacy geoResponseLegacy = this.mGeoResponseLegacy;
        if (geoResponseLegacy == null || geoResponseLegacy.getCode().longValue() != 1 || !this.mGeoResponseLegacy.getStatus().equalsIgnoreCase("ok") || !this.mGeoResponseLegacy.getData().contains("edgetwolettercountry")) {
            return false;
        }
        return true;
    }
}
