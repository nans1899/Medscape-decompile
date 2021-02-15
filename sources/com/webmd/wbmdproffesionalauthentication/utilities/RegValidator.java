package com.webmd.wbmdproffesionalauthentication.utilities;

import android.content.Context;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdhttpclient.VolleyRestClient;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.model.EnvironmentData;
import com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.SpecialCountriesRegistrationData;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

public class RegValidator {
    static String TAG = RegValidator.class.getSimpleName();

    public int validateName(String str) {
        if (!StringExtensions.isNotEmpty(str)) {
            return R.string.registration_page2_valuerequired;
        }
        if (str.length() > 35) {
            return R.string.registration_page2_namemaxlength;
        }
        if (!IsMatch(str, "^[a-zA-Z.áÄäÉéíóÖöúÜüñÑß`' ''^-]*$")) {
            return R.string.registration_page2_nameinvalidcharacters;
        }
        return -1;
    }

    public String validateZIP(Context context, String str, String str2) {
        if (context != null) {
            if (!StringExtensions.isNotEmpty(str)) {
                return context.getString(R.string.error_zip_no_value);
            }
            if (str2 == null || !str2.equalsIgnoreCase("us")) {
                if (str.length() > 15) {
                    return context.getString(R.string.error_zip_limit_length);
                }
            } else if (!IsMatch(str, "^[0-9]+$") || str.length() > 5 || str.length() > 5) {
                return context.getString(R.string.error_zip_no_valid);
            }
        }
        return "";
    }

    public int validatePassword(String str) {
        if (!StringExtensions.isNotEmpty(str)) {
            return R.string.registration_page2_valuerequired;
        }
        if (!IsMatch(str, "^[A-Za-záÄäÉéíóÖöúÜüñÑß`0-9]+$")) {
            return R.string.registration_page2_passwordinvalid;
        }
        if (str.length() < 8) {
            return R.string.signup_password_hint;
        }
        if (str.length() > 40) {
            return R.string.signup_password_too_long;
        }
        return -1;
    }

    public void validateEmail(String str, final ICallbackEvent<Boolean, Integer> iCallbackEvent, Context context) {
        if (iCallbackEvent != null || context != null) {
            if (!StringExtensions.isNotEmpty(str)) {
                iCallbackEvent.onError(Integer.valueOf(R.string.registration_page2_valuerequired));
            } else if (Util.isOnline(context)) {
                try {
                    str = URLEncoder.encode(str, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                VolleyRestClient.getInstance(context).get(EnvironmentUtil.Companion.getUrl(context, EnvironmentData.EMAIL_VALIDATION_URL) + str + "&src=" + Util.getAppNameForUrl(context), 0, new ICallbackEvent() {
                    public void onError(Object obj) {
                        iCallbackEvent.onError(Integer.valueOf(R.string.error_service_unavailable));
                    }

                    public void onCompleted(Object obj) {
                        String str = (String) obj;
                        if (!StringExtensions.isNotEmpty(str)) {
                            iCallbackEvent.onCompleted(true);
                            return;
                        }
                        iCallbackEvent.onError(Integer.valueOf(RegValidator.this.getErrorMessage(str.replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "_").replace(IOUtils.LINE_SEPARATOR_UNIX, ""))));
                    }
                });
            } else {
                iCallbackEvent.onError(Integer.valueOf(R.string.error_connection_required));
            }
        }
    }

    /* access modifiers changed from: private */
    public int getErrorMessage(String str) {
        if (str.equalsIgnoreCase("errors_regemailaddress_uniqueemail")) {
            return R.string.registration_page2_uniqueemail;
        }
        if (str.equalsIgnoreCase("errors_regemailaddress_restrictedemaildomain")) {
            return R.string.registration_page2_restrictedemaildomain;
        }
        if (str.equalsIgnoreCase("errors_regemailaddress_restrictedemailcheck")) {
            return R.string.registration_page2_restrictedemaildomaincheck;
        }
        if (str.equalsIgnoreCase("errors_regemailaddress_restrictedemaildomainext")) {
            return R.string.registration_page2_restrictedemaildomainnext;
        }
        if (str.equalsIgnoreCase("error_regemailaddress_invalid")) {
            return R.string.registration_page2_invalidemail;
        }
        return R.string.registration_page2_genericemail;
    }

    private boolean IsMatch(String str, String str2) {
        try {
            return Pattern.compile(str2).matcher(str).matches();
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public String validatePhoneNumber(String str, String str2) {
        SpecialCountriesRegistrationData specialCountriesRegistrationData = new SpecialCountriesRegistrationData();
        if (StringExtensions.isNullOrEmpty(str) || StringExtensions.isNullOrEmpty(str2)) {
            return "Please provide a valid value";
        }
        int intValue = specialCountriesRegistrationData.getPhoneLengthRules().get(str).intValue();
        if (str.equals("Mexico") || str.equals("Brazil")) {
            if (str2.length() <= intValue) {
                return "";
            }
            return "Phone number should have maximum " + intValue + " digits";
        } else if (str2.length() == intValue) {
            return "";
        } else {
            return "Phone number should be " + intValue + " digits";
        }
    }
}
