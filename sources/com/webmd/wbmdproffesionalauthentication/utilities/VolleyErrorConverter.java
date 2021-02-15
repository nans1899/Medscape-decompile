package com.webmd.wbmdproffesionalauthentication.utilities;

import android.content.Context;
import com.android.volley.VolleyError;
import com.webmd.wbmdproffesionalauthentication.R;

public class VolleyErrorConverter {
    public static String exceptionToMessage(Context context, Object obj) {
        String message;
        if (obj instanceof VolleyError) {
            VolleyError volleyError = (VolleyError) obj;
            if (volleyError.networkResponse == null || volleyError.networkResponse.data == null) {
                return "";
            }
            return new String(volleyError.networkResponse.data);
        } else if (!(obj instanceof Exception) || context == null || (message = ((Exception) obj).getMessage()) == null) {
            return "";
        } else {
            if (message.toLowerCase().contains("authfailureerror")) {
                return context.getResources().getString(R.string.error_api_username);
            }
            if (message.toLowerCase().contains("timeout")) {
                return context.getResources().getString(R.string.error_api_timeout);
            }
            if (message.toLowerCase().contains("noconnection")) {
                return context.getResources().getString(R.string.error_api_no_connection);
            }
            return context.getResources().getString(R.string.error_api_unknown);
        }
    }
}
