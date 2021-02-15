package com.webmd.wbmdproffesionalauthentication.parser;

import android.content.Context;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdproffesionalauthentication.R;
import org.json.JSONObject;

public class LoginErrorParser {
    public static String parseLoginError(Context context, JSONObject jSONObject) {
        if (context == null) {
            return "";
        }
        if (jSONObject == null) {
            return context.getString(R.string.error_api_unknown);
        }
        if (jSONObject.optInt("status") != 0) {
            return "";
        }
        String optString = jSONObject.optString("errorMessage");
        return !StringExtensions.isNotEmpty(optString) ? context.getString(R.string.error_api_unknown) : optString;
    }
}
