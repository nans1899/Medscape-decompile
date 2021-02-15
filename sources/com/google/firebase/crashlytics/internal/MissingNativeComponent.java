package com.google.firebase.crashlytics.internal;

import java.io.File;

public final class MissingNativeComponent implements CrashlyticsNativeComponent {
    private static final NativeSessionFileProvider MISSING_NATIVE_SESSION_FILE_PROVIDER = new MissingNativeSessionFileProvider();

    public boolean finalizeSession(String str) {
        return true;
    }

    public boolean hasCrashDataForSession(String str) {
        return false;
    }

    public boolean openSession(String str) {
        return true;
    }

    public void writeBeginSession(String str, String str2, long j) {
    }

    public void writeSessionApp(String str, String str2, String str3, String str4, String str5, int i, String str6) {
    }

    public void writeSessionDevice(String str, int i, String str2, int i2, long j, long j2, boolean z, int i3, String str3, String str4) {
    }

    public void writeSessionOs(String str, String str2, String str3, boolean z) {
    }

    public NativeSessionFileProvider getSessionFileProvider(String str) {
        return MISSING_NATIVE_SESSION_FILE_PROVIDER;
    }

    private static final class MissingNativeSessionFileProvider implements NativeSessionFileProvider {
        public File getAppFile() {
            return null;
        }

        public File getBinaryImagesFile() {
            return null;
        }

        public File getDeviceFile() {
            return null;
        }

        public File getMetadataFile() {
            return null;
        }

        public File getMinidumpFile() {
            return null;
        }

        public File getOsFile() {
            return null;
        }

        public File getSessionFile() {
            return null;
        }

        private MissingNativeSessionFileProvider() {
        }
    }
}
