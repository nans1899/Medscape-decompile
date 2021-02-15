package com.comscore.util.setup;

import com.comscore.android.util.jni.AndroidJniHelper;
import com.comscore.android.util.log.AndroidLogger;
import com.comscore.util.jni.JniComScoreHelper;
import com.comscore.util.log.LogHelper;

public class CustomPlatformSetup extends PlatformSetup {
    private static final String a = "6.5.3.201009";

    public JniComScoreHelper createApplicationInfoHelper() {
        return new AndroidJniHelper();
    }

    public LogHelper createLogger() {
        return new AndroidLogger();
    }

    public String getJavaCodeVersion() {
        return a;
    }
}
