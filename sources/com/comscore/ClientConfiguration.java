package com.comscore;

import com.comscore.util.cpp.CppJavaBinder;
import com.comscore.util.log.Logger;
import com.comscore.util.setup.Setup;
import java.util.HashMap;
import java.util.Map;

public abstract class ClientConfiguration extends CppJavaBinder {
    long b = 0;
    Builder c = null;

    public static abstract class Builder<T extends Builder, P extends ClientConfiguration> {
        protected String clientId;
        protected boolean httpRedirectCaching;
        protected boolean keepAliveMeasurement;
        protected Map<String, String> persistentLabels;
        protected boolean secureTransmission;
        protected Map<String, String> startLabels;

        protected Builder() {
            try {
                this.keepAliveMeasurement = ClientConfiguration.getDefaultKeepAliveMeasurementNative();
                this.secureTransmission = ClientConfiguration.getDefaultSecureTransmissionNative();
                this.httpRedirectCaching = ClientConfiguration.getDefaultHttpRedirectCachingEnabledNative();
            } catch (UnsatisfiedLinkError e) {
                Logger.e("Error using the native library: ", (Throwable) e);
            }
            this.persistentLabels = new HashMap();
            this.startLabels = new HashMap();
        }

        protected Builder(Builder builder) {
            this.persistentLabels = new HashMap(builder.persistentLabels);
            this.startLabels = new HashMap(builder.startLabels);
            this.keepAliveMeasurement = builder.keepAliveMeasurement;
            this.secureTransmission = builder.secureTransmission;
            this.httpRedirectCaching = builder.httpRedirectCaching;
            this.clientId = builder.clientId;
        }

        /* access modifiers changed from: package-private */
        public abstract T a();

        public abstract P build();

        public T httpRedirectCaching(boolean z) {
            this.httpRedirectCaching = z;
            return self();
        }

        public T keepAliveMeasurement(boolean z) {
            this.keepAliveMeasurement = z;
            return self();
        }

        public T persistentLabels(Map<String, String> map) {
            this.persistentLabels.clear();
            this.persistentLabels.putAll(map);
            return self();
        }

        public T secureTransmission(boolean z) {
            this.secureTransmission = z;
            return self();
        }

        /* access modifiers changed from: protected */
        public abstract T self();

        /* access modifiers changed from: protected */
        public void setClientId(String str) {
            this.clientId = str;
        }

        public T startLabels(Map<String, String> map) {
            this.startLabels.clear();
            this.startLabels.putAll(map);
            return self();
        }

        @Deprecated
        public T vce(boolean z) {
            return self();
        }
    }

    ClientConfiguration() {
    }

    ClientConfiguration(Builder builder) {
        this.c = builder.a();
    }

    private static native void addPersistentLabelsNative(long j, Map<String, String> map);

    private static native boolean containsPersistentLabelNative(long j, String str);

    private static native boolean containsStartLabelNative(long j, String str);

    /* access modifiers changed from: private */
    public static native boolean getDefaultHttpRedirectCachingEnabledNative();

    /* access modifiers changed from: private */
    public static native boolean getDefaultKeepAliveMeasurementNative();

    /* access modifiers changed from: private */
    public static native boolean getDefaultSecureTransmissionNative();

    private static native String getPersistentLabelNative(long j, String str);

    private static native Map<String, String> getPersistentLabelsNative(long j);

    private static native Map<String, String> getStartLabelsNative(long j);

    private static native boolean isHttpRedirectCachingEnabledNative(long j);

    private static native boolean isKeepAliveMeasurementEnabledNative(long j);

    private static native boolean isSecureTransmissionEnabledNative(long j);

    private static native void removeAllPersistentLabelsNative(long j);

    private static native void removePersistentLabelNative(long j, String str);

    private static native void setPersistentLabelNative(long j, String str, String str2);

    public void addPersistentLabels(Map<String, String> map) {
        try {
            addPersistentLabelsNative(this.b, map);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
        }
    }

    public boolean containsPersistentLabel(String str) {
        Builder builder;
        if (!Setup.isNativeLibrarySuccessfullyLoaded() && (builder = this.c) != null) {
            return builder.persistentLabels.containsKey(str);
        }
        try {
            return containsPersistentLabelNative(this.b, str);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return false;
        }
    }

    public boolean containsStartLabel(String str) {
        Builder builder;
        if (!Setup.isNativeLibrarySuccessfullyLoaded() && (builder = this.c) != null) {
            return builder.startLabels.containsKey(str);
        }
        try {
            return containsStartLabelNative(this.b, str);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public long d() {
        return this.b;
    }

    public String getPersistentLabel(String str) {
        Builder builder;
        if (!Setup.isNativeLibrarySuccessfullyLoaded() && (builder = this.c) != null) {
            return builder.persistentLabels.get(str);
        }
        try {
            return getPersistentLabelNative(this.b, str);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return null;
        }
    }

    public Map<String, String> getPersistentLabels() {
        Builder builder;
        if (!Setup.isNativeLibrarySuccessfullyLoaded() && (builder = this.c) != null) {
            return builder.persistentLabels;
        }
        try {
            return getPersistentLabelsNative(this.b);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return null;
        }
    }

    public Map<String, String> getStartLabels() {
        Builder builder;
        if (!Setup.isNativeLibrarySuccessfullyLoaded() && (builder = this.c) != null) {
            return builder.startLabels;
        }
        try {
            return getStartLabelsNative(this.b);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return null;
        }
    }

    public boolean isHttpRedirectCachingEnabled() {
        Builder builder;
        if (!Setup.isNativeLibrarySuccessfullyLoaded() && (builder = this.c) != null) {
            return builder.httpRedirectCaching;
        }
        try {
            return isHttpRedirectCachingEnabledNative(this.b);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return false;
        }
    }

    public boolean isKeepAliveMeasurementEnabled() {
        Builder builder;
        if (!Setup.isNativeLibrarySuccessfullyLoaded() && (builder = this.c) != null) {
            return builder.keepAliveMeasurement;
        }
        try {
            return isKeepAliveMeasurementEnabledNative(this.b);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return false;
        }
    }

    public boolean isSecureTransmissionEnabled() {
        Builder builder;
        if (!Setup.isNativeLibrarySuccessfullyLoaded() && (builder = this.c) != null) {
            return builder.secureTransmission;
        }
        try {
            return isSecureTransmissionEnabledNative(this.b);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
            return false;
        }
    }

    @Deprecated
    public boolean isVceEnabled() {
        return false;
    }

    public void removeAllPersistentLabels() {
        try {
            removeAllPersistentLabelsNative(this.b);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
        }
    }

    public void removePersistentLabel(String str) {
        try {
            removePersistentLabelNative(this.b, str);
        } catch (UnsatisfiedLinkError e) {
            printException(e);
        }
    }

    public void setPersistentLabel(String str, String str2) {
        if (str != null && str2 != null) {
            try {
                setPersistentLabelNative(this.b, str, str2);
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
        }
    }
}
