package com.appboy.configuration;

import android.content.Context;
import bo.app.m;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PackageUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CachedConfigurationProvider {
    private static final String a = AppboyLogger.getAppboyLogTag(CachedConfigurationProvider.class);
    private final Context b;
    protected final Map<String, Object> mConfigurationCache = Collections.synchronizedMap(new HashMap());
    protected final m mRuntimeAppConfigurationProvider;

    public CachedConfigurationProvider(Context context) {
        this.b = context;
        this.mRuntimeAppConfigurationProvider = new m(context);
    }

    /* access modifiers changed from: protected */
    public boolean getBooleanValue(String str, boolean z) {
        if (this.mConfigurationCache.containsKey(str)) {
            return ((Boolean) this.mConfigurationCache.get(str)).booleanValue();
        }
        if (this.mRuntimeAppConfigurationProvider.a(str)) {
            boolean a2 = this.mRuntimeAppConfigurationProvider.a(str, z);
            this.mConfigurationCache.put(str, Boolean.valueOf(a2));
            String str2 = a;
            AppboyLogger.d(str2, "Using runtime override value for key: " + str + " and value: " + a2);
            return a2;
        }
        boolean readBooleanResourceValue = readBooleanResourceValue(str, z);
        this.mConfigurationCache.put(str, Boolean.valueOf(readBooleanResourceValue));
        String str3 = a;
        AppboyLogger.d(str3, "Defaulting to using xml value for key: " + str + " and value: " + readBooleanResourceValue);
        return readBooleanResourceValue;
    }

    /* access modifiers changed from: protected */
    public String getStringValue(String str, String str2) {
        if (this.mConfigurationCache.containsKey(str)) {
            return (String) this.mConfigurationCache.get(str);
        }
        if (this.mRuntimeAppConfigurationProvider.a(str)) {
            String a2 = this.mRuntimeAppConfigurationProvider.a(str, str2);
            this.mConfigurationCache.put(str, a2);
            String str3 = a;
            AppboyLogger.d(str3, "Using runtime override value for key: " + str + " and value: " + a2);
            return a2;
        }
        String readStringResourceValue = readStringResourceValue(str, str2);
        this.mConfigurationCache.put(str, readStringResourceValue);
        String str4 = a;
        AppboyLogger.d(str4, "Defaulting to using xml value for key: " + str + " and value: " + readStringResourceValue);
        return readStringResourceValue;
    }

    /* access modifiers changed from: protected */
    public int getIntValue(String str, int i) {
        if (this.mConfigurationCache.containsKey(str)) {
            return ((Integer) this.mConfigurationCache.get(str)).intValue();
        }
        if (this.mRuntimeAppConfigurationProvider.a(str)) {
            int a2 = this.mRuntimeAppConfigurationProvider.a(str, i);
            this.mConfigurationCache.put(str, Integer.valueOf(a2));
            String str2 = a;
            AppboyLogger.d(str2, "Using runtime override value for key: " + str + " and value: " + a2);
            return a2;
        }
        int readIntegerResourceValue = readIntegerResourceValue(str, i);
        this.mConfigurationCache.put(str, Integer.valueOf(readIntegerResourceValue));
        String str3 = a;
        AppboyLogger.d(str3, "Defaulting to using xml value for key: " + str + " and value: " + readIntegerResourceValue);
        return readIntegerResourceValue;
    }

    /* access modifiers changed from: protected */
    public Set<String> getStringSetValue(String str, Set<String> set) {
        if (this.mConfigurationCache.containsKey(str)) {
            return (Set) this.mConfigurationCache.get(str);
        }
        if (this.mRuntimeAppConfigurationProvider.a(str)) {
            Set<String> a2 = this.mRuntimeAppConfigurationProvider.a(str, set);
            this.mConfigurationCache.put(str, a2);
            String str2 = a;
            AppboyLogger.d(str2, "Using runtime override value for key: " + str + " and value: " + a2);
            return a2;
        }
        String[] readStringArrayResourceValue = readStringArrayResourceValue(str, new String[0]);
        if (readStringArrayResourceValue.length != 0) {
            set = new HashSet<>(Arrays.asList(readStringArrayResourceValue));
        }
        this.mConfigurationCache.put(str, set);
        String str3 = a;
        AppboyLogger.d(str3, "Defaulting to using xml value for key: " + str + " and value: " + set);
        return set;
    }

    /* access modifiers changed from: protected */
    public int readIntegerResourceValue(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            int identifier = this.b.getResources().getIdentifier(str, "integer", PackageUtils.getResourcePackageName(this.b));
            if (identifier != 0) {
                return this.b.getResources().getInteger(identifier);
            }
            String str2 = a;
            AppboyLogger.d(str2, "Unable to find the xml integer configuration value with key " + str + ". Using default value '" + i + "'.");
            return i;
        } catch (Exception unused) {
            String str3 = a;
            AppboyLogger.d(str3, "Unexpected exception retrieving the xml integer configuration value with key " + str + ". Using default value " + i + "'.");
            return i;
        }
    }

    /* access modifiers changed from: protected */
    public boolean readBooleanResourceValue(String str, boolean z) {
        if (str == null) {
            return z;
        }
        try {
            int identifier = this.b.getResources().getIdentifier(str, "bool", PackageUtils.getResourcePackageName(this.b));
            if (identifier != 0) {
                return this.b.getResources().getBoolean(identifier);
            }
            String str2 = a;
            AppboyLogger.d(str2, "Unable to find the xml boolean configuration value with key " + str + ". Using default value '" + z + "'.");
            return z;
        } catch (Exception unused) {
            String str3 = a;
            AppboyLogger.d(str3, "Unexpected exception retrieving the xml boolean configuration value with key " + str + ". Using default value " + z + "'.");
            return z;
        }
    }

    /* access modifiers changed from: protected */
    public String readStringResourceValue(String str, String str2) {
        if (str == null) {
            return str2;
        }
        try {
            int identifier = this.b.getResources().getIdentifier(str, "string", PackageUtils.getResourcePackageName(this.b));
            if (identifier != 0) {
                return this.b.getResources().getString(identifier);
            }
            String str3 = a;
            AppboyLogger.d(str3, "Unable to find the xml string configuration value with key " + str + ". Using default value '" + str2 + "'.");
            return str2;
        } catch (Exception unused) {
            String str4 = a;
            AppboyLogger.d(str4, "Unexpected exception retrieving the xml string configuration value with key " + str + ". Using default value " + str2 + "'.");
            return str2;
        }
    }

    /* access modifiers changed from: protected */
    public String[] readStringArrayResourceValue(String str, String[] strArr) {
        if (str == null) {
            return strArr;
        }
        try {
            int identifier = this.b.getResources().getIdentifier(str, "array", PackageUtils.getResourcePackageName(this.b));
            if (identifier != 0) {
                return this.b.getResources().getStringArray(identifier);
            }
            String str2 = a;
            AppboyLogger.d(str2, "Unable to find the xml string array configuration value with key " + str + ". Using default value '" + Arrays.toString(strArr) + "'.");
            return strArr;
        } catch (Exception unused) {
            String str3 = a;
            AppboyLogger.d(str3, "Unexpected exception retrieving the xml string array configuration value with key " + str + ". Using default value " + Arrays.toString(strArr) + "'.");
            return strArr;
        }
    }
}
