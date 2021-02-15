package com.medscape.android.consult.interfaces;

import com.medscape.android.util.MedscapeException;
import org.json.JSONArray;

public interface ICommunityArrayReceivedListener {
    void onCommunityDataReceived(JSONArray jSONArray);

    void onFailedToReceiveCommunityData(MedscapeException medscapeException);
}
