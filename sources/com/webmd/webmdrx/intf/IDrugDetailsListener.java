package com.webmd.webmdrx.intf;

import org.json.JSONObject;

public interface IDrugDetailsListener {
    void onDrugDetailsFailed(String str);

    void onDrugDetailsFetch(JSONObject jSONObject);
}
