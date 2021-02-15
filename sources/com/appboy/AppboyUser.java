package com.appboy;

import bo.app.bq;
import bo.app.bt;
import bo.app.bz;
import bo.app.cf;
import bo.app.cg;
import bo.app.dq;
import bo.app.dt;
import bo.app.du;
import com.appboy.enums.Gender;
import com.appboy.enums.Month;
import com.appboy.enums.NotificationSubscriptionType;
import com.appboy.models.outgoing.AttributionData;
import com.appboy.models.outgoing.FacebookUser;
import com.appboy.models.outgoing.TwitterUser;
import com.appboy.support.AppboyLogger;
import com.appboy.support.CustomAttributeValidationUtils;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;

public class AppboyUser {
    private static final String a = AppboyLogger.getAppboyLogTag(AppboyUser.class);
    private final dt b;
    private final dq c;
    private final Object d = new Object();
    private final bt e;
    private volatile String f;
    private final bq g;

    AppboyUser(dt dtVar, bq bqVar, String str, bt btVar, dq dqVar) {
        this.f = str;
        this.b = dtVar;
        this.e = btVar;
        this.c = dqVar;
        this.g = bqVar;
    }

    public boolean addAlias(String str, String str2) {
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.w(a, "Invalid alias parameter: alias is required to be non-null and non-empty. Not adding alias.");
            return false;
        } else if (StringUtils.isNullOrBlank(str2)) {
            AppboyLogger.w(a, "Invalid label parameter: label is required to be non-null and non-empty. Not adding alias.");
            return false;
        } else {
            try {
                return this.g.a((bz) cf.a(str, str2));
            } catch (Exception e2) {
                String str3 = a;
                AppboyLogger.e(str3, "Failed to set alias: " + str, e2);
                return false;
            }
        }
    }

    public boolean setFirstName(String str) {
        try {
            this.b.b(str);
            return true;
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set first name to: " + str, e2);
            return false;
        }
    }

    public boolean setLastName(String str) {
        try {
            this.b.c(str);
            return true;
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set last name to: " + str, e2);
            return false;
        }
    }

    public boolean setEmail(String str) {
        try {
            return this.b.d(str);
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set email to: " + str, e2);
            return false;
        }
    }

    public boolean setGender(Gender gender) {
        try {
            this.b.a(gender);
            return true;
        } catch (Exception e2) {
            String str = a;
            AppboyLogger.w(str, "Failed to set gender to: " + gender, e2);
            return false;
        }
    }

    public boolean setDateOfBirth(int i, Month month, int i2) {
        try {
            return this.b.a(i, month, i2);
        } catch (Exception e2) {
            String str = a;
            AppboyLogger.w(str, "Failed to set date of birth to: " + i + "-" + month.getValue() + "-" + i2, e2);
            return false;
        }
    }

    public boolean setCountry(String str) {
        try {
            this.b.e(str);
            return true;
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set country to: " + str, e2);
            return false;
        }
    }

    public boolean setHomeCity(String str) {
        try {
            this.b.f(str);
            return true;
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set home city to: " + str, e2);
            return false;
        }
    }

    public boolean setLanguage(String str) {
        try {
            this.b.g(str);
            return true;
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set language to: " + str, e2);
            return false;
        }
    }

    public boolean setEmailNotificationSubscriptionType(NotificationSubscriptionType notificationSubscriptionType) {
        try {
            this.b.a(notificationSubscriptionType);
            return true;
        } catch (Exception e2) {
            String str = a;
            AppboyLogger.w(str, "Failed to set email notification subscription to: " + notificationSubscriptionType, e2);
            return false;
        }
    }

    public boolean setPushNotificationSubscriptionType(NotificationSubscriptionType notificationSubscriptionType) {
        try {
            this.b.b(notificationSubscriptionType);
            return true;
        } catch (Exception e2) {
            String str = a;
            AppboyLogger.w(str, "Failed to set push notification subscription to: " + notificationSubscriptionType, e2);
            return false;
        }
    }

    public boolean setPhoneNumber(String str) {
        try {
            return this.b.h(str);
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set phone number to: " + str, e2);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, boolean z) {
        try {
            return this.b.a(str, (Object) Boolean.valueOf(z));
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set custom boolean attribute " + str + ".", e2);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, int i) {
        try {
            return this.b.a(str, (Object) Integer.valueOf(i));
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set custom integer attribute " + str + ".", e2);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, float f2) {
        try {
            return this.b.a(str, (Object) Float.valueOf(f2));
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set custom float attribute " + str + ".", e2);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, long j) {
        try {
            return this.b.a(str, (Object) Long.valueOf(j));
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set custom long attribute " + str + ".", e2);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, String str2) {
        try {
            return this.b.a(str, (Object) str2);
        } catch (Exception e2) {
            String str3 = a;
            AppboyLogger.w(str3, "Failed to set custom string attribute " + str + ".", e2);
            return false;
        }
    }

    public boolean setCustomUserAttribute(String str, double d2) {
        try {
            return this.b.a(str, (Object) Double.valueOf(d2));
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set custom double attribute " + str + ".", e2);
            return false;
        }
    }

    public boolean addToCustomAttributeArray(String str, String str2) {
        try {
            if (!CustomAttributeValidationUtils.isValidCustomAttributeKey(str, this.c.i())) {
                AppboyLogger.w(a, "Custom attribute key was invalid. Not adding to attribute array.");
                return false;
            } else if (!CustomAttributeValidationUtils.isValidCustomAttributeValue(str2)) {
                return false;
            } else {
                return this.g.a((bz) cf.e(ValidationUtils.ensureAppboyFieldLength(str), ValidationUtils.ensureAppboyFieldLength(str2)));
            }
        } catch (Exception e2) {
            String str3 = a;
            AppboyLogger.w(str3, "Failed to add custom attribute with key '" + str + "'.", e2);
            return false;
        }
    }

    public boolean removeFromCustomAttributeArray(String str, String str2) {
        try {
            if (!CustomAttributeValidationUtils.isValidCustomAttributeKey(str, this.c.i())) {
                AppboyLogger.w(a, "Custom attribute key was invalid. Not removing from attribute array.");
                return false;
            } else if (!CustomAttributeValidationUtils.isValidCustomAttributeValue(str2)) {
                return false;
            } else {
                return this.g.a((bz) cf.f(ValidationUtils.ensureAppboyFieldLength(str), ValidationUtils.ensureAppboyFieldLength(str2)));
            }
        } catch (Exception e2) {
            String str3 = a;
            AppboyLogger.w(str3, "Failed to remove custom attribute with key '" + str + "'.", e2);
            return false;
        }
    }

    public boolean setCustomAttributeArray(String str, String[] strArr) {
        try {
            if (!CustomAttributeValidationUtils.isValidCustomAttributeKey(str, this.c.i())) {
                return false;
            }
            String ensureAppboyFieldLength = ValidationUtils.ensureAppboyFieldLength(str);
            if (strArr != null) {
                strArr = CustomAttributeValidationUtils.ensureCustomAttributeArrayValues(strArr);
            }
            return this.g.a((bz) cf.a(ensureAppboyFieldLength, strArr));
        } catch (Exception unused) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set custom attribute array with key: '" + str + "'.");
            return false;
        }
    }

    public boolean setCustomUserAttributeToNow(String str) {
        try {
            return this.b.a(str, du.a());
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set custom attribute " + str + " to now.", e2);
            return false;
        }
    }

    public boolean setCustomUserAttributeToSecondsFromEpoch(String str, long j) {
        try {
            return this.b.a(str, j);
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to set custom attribute " + str + " to " + j + " seconds from epoch.", e2);
            return false;
        }
    }

    public boolean incrementCustomUserAttribute(String str) {
        return incrementCustomUserAttribute(str, 1);
    }

    public boolean incrementCustomUserAttribute(String str, int i) {
        try {
            if (!CustomAttributeValidationUtils.isValidCustomAttributeKey(str, this.c.i())) {
                return false;
            }
            return this.g.a((bz) cf.a(ValidationUtils.ensureAppboyFieldLength(str), i));
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to increment custom attribute " + str + " by " + i + ".", e2);
            return false;
        }
    }

    public boolean unsetCustomUserAttribute(String str) {
        try {
            return this.b.j(str);
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to unset custom attribute " + str + ".", e2);
            return false;
        }
    }

    public boolean setFacebookData(FacebookUser facebookUser) {
        try {
            this.b.a(facebookUser);
            return true;
        } catch (Exception e2) {
            AppboyLogger.w(a, "Failed to set Facebook user data.", e2);
            return false;
        }
    }

    public boolean setTwitterData(TwitterUser twitterUser) {
        try {
            this.b.a(twitterUser);
            return true;
        } catch (Exception e2) {
            AppboyLogger.w(a, "Failed to set Twitter user data.", e2);
            return false;
        }
    }

    public boolean setAttributionData(AttributionData attributionData) {
        try {
            this.b.a(attributionData);
            return true;
        } catch (Exception e2) {
            AppboyLogger.w(a, "Failed to set attribution data.", e2);
            return false;
        }
    }

    public boolean setAvatarImageUrl(String str) {
        try {
            this.b.i(str);
            return true;
        } catch (Exception e2) {
            AppboyLogger.w(a, "Failed to set the avatar image URL.", e2);
            return false;
        }
    }

    public String getUserId() {
        String str;
        synchronized (this.d) {
            str = this.f;
        }
        return str;
    }

    public void setLastKnownLocation(double d2, double d3, Double d4, Double d5) {
        try {
            this.e.a(new cg(d2, d3, d4, d5));
        } catch (Exception e2) {
            AppboyLogger.w(a, "Failed to manually set location.", e2);
        }
    }

    public void setLocationCustomAttribute(String str, double d2, double d3) {
        try {
            if (!CustomAttributeValidationUtils.isValidCustomAttributeKey(str, this.c.i())) {
                AppboyLogger.w(a, "Custom location attribute key was invalid. Not setting attribute.");
            } else if (!ValidationUtils.isValidLocation(d2, d3)) {
                String str2 = a;
                AppboyLogger.w(str2, "Cannot set custom location attribute due with invalid latitude '" + d2 + " and longitude '" + d3 + "'");
            } else {
                this.g.a((bz) cf.a(ValidationUtils.ensureAppboyFieldLength(str), d2, d3));
            }
        } catch (Exception e2) {
            String str3 = a;
            AppboyLogger.w(str3, "Failed to set custom location attribute with key '" + str + "' and latitude '" + d2 + "' and longitude '" + d3 + "'", e2);
        }
    }

    public void unsetLocationCustomAttribute(String str) {
        try {
            if (!CustomAttributeValidationUtils.isValidCustomAttributeKey(str, this.c.i())) {
                AppboyLogger.w(a, "Custom location attribute key was invalid. Not setting attribute.");
                return;
            }
            this.g.a((bz) cf.k(ValidationUtils.ensureAppboyFieldLength(str)));
        } catch (Exception e2) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to unset custom location attribute with key '" + str + "'", e2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        synchronized (this.d) {
            if (!this.f.equals("")) {
                if (!this.f.equals(str)) {
                    throw new IllegalArgumentException("setExternalId can not be used to change the external ID of a UserCache from a non-empty value to a new value. Was: [" + this.f + "], tried to change to: [" + str + "]");
                }
            }
            this.f = str;
            this.b.a(str);
        }
    }
}
