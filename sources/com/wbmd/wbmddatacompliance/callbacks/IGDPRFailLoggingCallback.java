package com.wbmd.wbmddatacompliance.callbacks;

public interface IGDPRFailLoggingCallback {
    void sendErrorLog(String str, String str2, Throwable th);
}
