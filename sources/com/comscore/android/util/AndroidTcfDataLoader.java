package com.comscore.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.comscore.util.TcfDataLoader;
import java.util.HashMap;
import java.util.Map;

public class AndroidTcfDataLoader implements TcfDataLoader, SharedPreferences.OnSharedPreferenceChangeListener {
    public static final int COMSCORE_VENDOR_INDEX = 77;
    public static final String IABTCF_CMP_SDK_ID = "IABTCF_CmpSdkID";
    public static final String IABTCF_GDPR_APPLIES = "IABTCF_gdprApplies";
    public static final String IABTCF_PUBLISHER_CC = "IABTCF_PublisherCC";
    public static final String IABTCF_PURPOSE_CONSENTS = "IABTCF_PurposeConsents";
    public static final String IABTCF_PURPOSE_ONE_TREATMENT = "IABTCF_PurposeOneTreatment";
    public static final String IABTCF_VENDOR_CONSENTS = "IABTCF_VendorConsents";
    public static final String IABTCF_VENDOR_LEGITIMATE_INTERESTS = "IABTCF_VendorLegitimateInterests";
    protected boolean _enabled = false;
    protected boolean _registered;
    private Map<Integer, Boolean> a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private String g;
    private SharedPreferences h;

    public AndroidTcfDataLoader() {
        HashMap hashMap = new HashMap();
        this.a = hashMap;
        hashMap.put(1, Boolean.FALSE);
        this.a.put(7, Boolean.FALSE);
        this.a.put(8, Boolean.FALSE);
        this.a.put(9, Boolean.FALSE);
        this.a.put(10, Boolean.FALSE);
    }

    private void a() {
        SharedPreferences sharedPreferences;
        if (!this._registered && (sharedPreferences = this.h) != null) {
            g(sharedPreferences);
            this.h.registerOnSharedPreferenceChangeListener(this);
            this._registered = true;
        }
    }

    private void a(SharedPreferences sharedPreferences) {
        this.b = sharedPreferences.contains(IABTCF_CMP_SDK_ID);
    }

    private void b() {
        SharedPreferences sharedPreferences;
        if (this._registered && (sharedPreferences = this.h) != null) {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
            this._registered = false;
        }
    }

    private void b(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString(IABTCF_PURPOSE_CONSENTS, (String) null);
        if (string != null && string.length() >= 10) {
            boolean z = true;
            this.a.put(1, Boolean.valueOf(string.charAt(0) == '1'));
            this.a.put(7, Boolean.valueOf(string.charAt(6) == '1'));
            this.a.put(8, Boolean.valueOf(string.charAt(7) == '1'));
            this.a.put(9, Boolean.valueOf(string.charAt(8) == '1'));
            Map<Integer, Boolean> map = this.a;
            if (string.charAt(9) != '1') {
                z = false;
            }
            map.put(10, Boolean.valueOf(z));
        }
    }

    private void c(SharedPreferences sharedPreferences) {
        boolean z = false;
        if (sharedPreferences.getInt(IABTCF_GDPR_APPLIES, 0) == 1) {
            z = true;
        }
        this.c = z;
    }

    private void d(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString(IABTCF_VENDOR_LEGITIMATE_INTERESTS, "");
        if (string.length() >= 76) {
            this.e = string.charAt(76) == '1';
        }
    }

    private void e(SharedPreferences sharedPreferences) {
        this.g = sharedPreferences.getString(IABTCF_PUBLISHER_CC, "AA");
    }

    private void f(SharedPreferences sharedPreferences) {
        boolean z = false;
        if (sharedPreferences.getInt(IABTCF_PURPOSE_ONE_TREATMENT, 0) == 1) {
            z = true;
        }
        this.f = z;
    }

    private void g(SharedPreferences sharedPreferences) {
        a(sharedPreferences);
        c(sharedPreferences);
        h(sharedPreferences);
        d(sharedPreferences);
        b(sharedPreferences);
        e(sharedPreferences);
        f(sharedPreferences);
    }

    private void h(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString(IABTCF_VENDOR_CONSENTS, "");
        if (string.length() >= 77) {
            this.d = string.charAt(76) == '1';
        }
    }

    public Map<Integer, Boolean> getConsents() {
        return this.a;
    }

    public String getPublisherCountryCode() {
        return this.g;
    }

    public boolean isCmpPresent() {
        return this.b;
    }

    public boolean isCmpReady() {
        return true;
    }

    public boolean isGdprApplicable() {
        return this.c;
    }

    public boolean isLegitimateInterestConsent() {
        return this.e;
    }

    public boolean isPurposeOneTreatmentEnabled() {
        return this.f;
    }

    public boolean isServiceSpecificEnabled() {
        return true;
    }

    public boolean isVendorConsentEnabled() {
        return this.d;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (IABTCF_CMP_SDK_ID.equals(str)) {
            a(sharedPreferences);
        } else if (IABTCF_GDPR_APPLIES.equals(str)) {
            c(sharedPreferences);
        } else if (IABTCF_VENDOR_CONSENTS.equals(str)) {
            h(sharedPreferences);
        } else if (IABTCF_VENDOR_LEGITIMATE_INTERESTS.equals(str)) {
            d(sharedPreferences);
        } else if (IABTCF_PURPOSE_CONSENTS.equals(str)) {
            b(sharedPreferences);
        } else if (IABTCF_PUBLISHER_CC.equals(str)) {
            e(sharedPreferences);
        } else if (IABTCF_PURPOSE_ONE_TREATMENT.equals(str)) {
            f(sharedPreferences);
        }
    }

    public void setContext(Context context) {
        if (this.h == null) {
            this.h = PreferenceManager.getDefaultSharedPreferences(context);
            if (this._enabled) {
                a();
            }
        }
    }

    public void setEnabled(boolean z) {
        if (this._enabled != z) {
            this._enabled = z;
            if (z) {
                a();
            } else {
                b();
            }
        }
    }
}
