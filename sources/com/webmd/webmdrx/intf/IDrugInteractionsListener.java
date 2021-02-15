package com.webmd.webmdrx.intf;

import org.json.JSONObject;

public interface IDrugInteractionsListener {
    void onInteractionsFailed(String str);

    void onInteractionsReceived(JSONObject jSONObject);
}
