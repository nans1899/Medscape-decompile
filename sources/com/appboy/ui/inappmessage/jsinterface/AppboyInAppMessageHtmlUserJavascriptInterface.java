package com.appboy.ui.inappmessage.jsinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.appboy.Appboy;
import com.appboy.AppboyUser;
import com.appboy.enums.Gender;
import com.appboy.enums.Month;
import com.appboy.enums.NotificationSubscriptionType;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class AppboyInAppMessageHtmlUserJavascriptInterface {
    public static final String JS_BRIDGE_ATTRIBUTE_VALUE = "value";
    public static final String JS_BRIDGE_GENDER_FEMALE = Gender.FEMALE.forJsonPut();
    public static final String JS_BRIDGE_GENDER_MALE = Gender.MALE.forJsonPut();
    public static final String JS_BRIDGE_GENDER_NOT_APPLICABLE = Gender.NOT_APPLICABLE.forJsonPut();
    public static final String JS_BRIDGE_GENDER_OTHER = Gender.OTHER.forJsonPut();
    public static final String JS_BRIDGE_GENDER_PREFER_NOT_TO_SAY = Gender.PREFER_NOT_TO_SAY.forJsonPut();
    public static final String JS_BRIDGE_GENDER_UNKNOWN = Gender.UNKNOWN.forJsonPut();
    public static final String JS_BRIDGE_OPTED_IN = "opted_in";
    public static final String JS_BRIDGE_SUBSCRIBED = "subscribed";
    public static final String JS_BRIDGE_UNSUBSCRIBED = "unsubscribed";
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyInAppMessageHtmlUserJavascriptInterface.class);
    private Context mContext;

    public AppboyInAppMessageHtmlUserJavascriptInterface(Context context) {
        this.mContext = context;
    }

    @JavascriptInterface
    public void setFirstName(String str) {
        Appboy.getInstance(this.mContext).getCurrentUser().setFirstName(str);
    }

    @JavascriptInterface
    public void setLastName(String str) {
        Appboy.getInstance(this.mContext).getCurrentUser().setLastName(str);
    }

    @JavascriptInterface
    public void setEmail(String str) {
        Appboy.getInstance(this.mContext).getCurrentUser().setEmail(str);
    }

    @JavascriptInterface
    public void setGender(String str) {
        Gender parseGender = parseGender(str);
        if (parseGender == null) {
            String str2 = TAG;
            AppboyLogger.e(str2, "Failed to parse gender in Braze HTML in-app message javascript interface with gender: " + str);
            return;
        }
        Appboy.getInstance(this.mContext).getCurrentUser().setGender(parseGender);
    }

    /* access modifiers changed from: package-private */
    public Gender parseGender(String str) {
        if (str == null) {
            return null;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        if (lowerCase.equals(JS_BRIDGE_GENDER_MALE)) {
            return Gender.MALE;
        }
        if (lowerCase.equals(JS_BRIDGE_GENDER_FEMALE)) {
            return Gender.FEMALE;
        }
        if (lowerCase.equals(JS_BRIDGE_GENDER_OTHER)) {
            return Gender.OTHER;
        }
        if (lowerCase.equals(JS_BRIDGE_GENDER_UNKNOWN)) {
            return Gender.UNKNOWN;
        }
        if (lowerCase.equals(JS_BRIDGE_GENDER_NOT_APPLICABLE)) {
            return Gender.NOT_APPLICABLE;
        }
        if (lowerCase.equals(JS_BRIDGE_GENDER_PREFER_NOT_TO_SAY)) {
            return Gender.PREFER_NOT_TO_SAY;
        }
        return null;
    }

    @JavascriptInterface
    public void setDateOfBirth(int i, int i2, int i3) {
        Month monthFromInt = monthFromInt(i2);
        if (monthFromInt == null) {
            String str = TAG;
            AppboyLogger.e(str, "Failed to parse month for value " + i2);
            return;
        }
        Appboy.getInstance(this.mContext).getCurrentUser().setDateOfBirth(i, monthFromInt, i3);
    }

    /* access modifiers changed from: package-private */
    public Month monthFromInt(int i) {
        if (i < 1 || i > 12) {
            return null;
        }
        return Month.getMonth(i - 1);
    }

    @JavascriptInterface
    public void setCountry(String str) {
        Appboy.getInstance(this.mContext).getCurrentUser().setCountry(str);
    }

    @JavascriptInterface
    public void setLanguage(String str) {
        Appboy.getInstance(this.mContext).getCurrentUser().setLanguage(str);
    }

    @JavascriptInterface
    public void setHomeCity(String str) {
        Appboy.getInstance(this.mContext).getCurrentUser().setHomeCity(str);
    }

    @JavascriptInterface
    public void setEmailNotificationSubscriptionType(String str) {
        NotificationSubscriptionType subscriptionTypeFromJavascriptString = subscriptionTypeFromJavascriptString(str);
        if (subscriptionTypeFromJavascriptString == null) {
            String str2 = TAG;
            AppboyLogger.e(str2, "Failed to parse email subscription type in Braze HTML in-app message javascript interface with subscription " + str);
            return;
        }
        Appboy.getInstance(this.mContext).getCurrentUser().setEmailNotificationSubscriptionType(subscriptionTypeFromJavascriptString);
    }

    @JavascriptInterface
    public void setPushNotificationSubscriptionType(String str) {
        NotificationSubscriptionType subscriptionTypeFromJavascriptString = subscriptionTypeFromJavascriptString(str);
        if (subscriptionTypeFromJavascriptString == null) {
            String str2 = TAG;
            AppboyLogger.e(str2, "Failed to parse push subscription type in Braze HTML in-app message javascript interface with subscription: " + str);
            return;
        }
        Appboy.getInstance(this.mContext).getCurrentUser().setPushNotificationSubscriptionType(subscriptionTypeFromJavascriptString);
    }

    /* access modifiers changed from: package-private */
    public NotificationSubscriptionType subscriptionTypeFromJavascriptString(String str) {
        String lowerCase = str.toLowerCase(Locale.US);
        if (lowerCase.equals(JS_BRIDGE_SUBSCRIBED)) {
            return NotificationSubscriptionType.SUBSCRIBED;
        }
        if (lowerCase.equals(JS_BRIDGE_UNSUBSCRIBED)) {
            return NotificationSubscriptionType.UNSUBSCRIBED;
        }
        if (lowerCase.equals(JS_BRIDGE_OPTED_IN)) {
            return NotificationSubscriptionType.OPTED_IN;
        }
        return null;
    }

    @JavascriptInterface
    public void setPhoneNumber(String str) {
        Appboy.getInstance(this.mContext).getCurrentUser().setPhoneNumber(str);
    }

    @JavascriptInterface
    public void setCustomUserAttributeJSON(String str, String str2) {
        setCustomAttribute(Appboy.getInstance(this.mContext).getCurrentUser(), str, str2);
    }

    /* access modifiers changed from: package-private */
    public void setCustomAttribute(AppboyUser appboyUser, String str, String str2) {
        try {
            Object obj = new JSONObject(str2).get("value");
            if (obj instanceof String) {
                appboyUser.setCustomUserAttribute(str, (String) obj);
            } else if (obj instanceof Boolean) {
                appboyUser.setCustomUserAttribute(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Integer) {
                appboyUser.setCustomUserAttribute(str, ((Integer) obj).intValue());
            } else if (obj instanceof Double) {
                appboyUser.setCustomUserAttribute(str, ((Double) obj).floatValue());
            } else {
                String str3 = TAG;
                AppboyLogger.e(str3, "Failed to parse custom attribute type for key: " + str);
            }
        } catch (Exception e) {
            String str4 = TAG;
            AppboyLogger.e(str4, "Failed to parse custom attribute type for key: " + str, e);
        }
    }

    @JavascriptInterface
    public void setCustomUserAttributeArray(String str, String str2) {
        String[] parseStringArrayFromJsonString = parseStringArrayFromJsonString(str2);
        if (parseStringArrayFromJsonString == null) {
            String str3 = TAG;
            AppboyLogger.e(str3, "Failed to set custom attribute array for key " + str);
            return;
        }
        Appboy.getInstance(this.mContext).getCurrentUser().setCustomAttributeArray(str, parseStringArrayFromJsonString);
    }

    /* access modifiers changed from: package-private */
    public String[] parseStringArrayFromJsonString(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return (String[]) arrayList.toArray(new String[0]);
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Failed to parse custom attribute array", e);
            return null;
        }
    }

    @JavascriptInterface
    public void addToCustomAttributeArray(String str, String str2) {
        Appboy.getInstance(this.mContext).getCurrentUser().addToCustomAttributeArray(str, str2);
    }

    @JavascriptInterface
    public void removeFromCustomAttributeArray(String str, String str2) {
        Appboy.getInstance(this.mContext).getCurrentUser().removeFromCustomAttributeArray(str, str2);
    }

    @JavascriptInterface
    public void incrementCustomUserAttribute(String str) {
        Appboy.getInstance(this.mContext).getCurrentUser().incrementCustomUserAttribute(str);
    }

    @JavascriptInterface
    public void setLocationCustomUserAttribute(String str, double d, double d2) {
        Appboy.getInstance(this.mContext).getCurrentUser().setLocationCustomAttribute(str, d, d2);
    }
}
