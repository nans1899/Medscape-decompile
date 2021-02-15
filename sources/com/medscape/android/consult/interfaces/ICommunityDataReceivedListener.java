package com.medscape.android.consult.interfaces;

import com.medscape.android.util.MedscapeException;
import org.json.JSONObject;

public interface ICommunityDataReceivedListener {
    void onCommunityDataReceived(JSONObject jSONObject);

    void onFailedToReceiveCommunityData(MedscapeException medscapeException);
}
