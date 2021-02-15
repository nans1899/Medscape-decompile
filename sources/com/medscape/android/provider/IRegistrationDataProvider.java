package com.medscape.android.provider;

import com.medscape.android.interfaces.ICallbackEvent;
import org.json.JSONObject;

public interface IRegistrationDataProvider {
    void sendPasswordResetRequest(String str, String str2, ICallbackEvent<JSONObject, String> iCallbackEvent);
}
