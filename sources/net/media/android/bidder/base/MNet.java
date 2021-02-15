package net.media.android.bidder.base;

import android.app.Application;
import android.content.Context;
import java.util.Map;
import net.media.android.bidder.base.common.b;
import net.media.android.bidder.base.logging.MNetLog;
import net.media.android.bidder.base.models.MNetUser;

public final class MNet {
    private static volatile b a;

    public static void init(Application application, String str, boolean z) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(application, str, z);
                }
            }
            return;
        }
        MNetLog.error("MNet SDK is already initialized");
    }

    public static void init(Application application, String str) {
        init(application, str, false);
    }

    public static String getCustomerId() {
        if (a != null) {
            return a.a();
        }
        MNetLog.error("Initialize SDK with MNet.init()");
        return "";
    }

    public static Context getContext() {
        if (a == null) {
            return null;
        }
        return a.b();
    }

    public static boolean a() {
        if (a == null) {
            return false;
        }
        return a.c();
    }

    public static boolean a(String str) {
        if (a == null) {
            return false;
        }
        return a.a(str);
    }

    public static void setMNetUser(String str, String str2, int i, Map<String, Object> map, String str3) {
        if (a == null) {
            MNetLog.error("Initialize SDK with MNet.init()");
        } else {
            a.a(str, str2, i, map, str3);
        }
    }

    public static MNetUser b() {
        if (a == null) {
            MNetLog.error("Initialize SDK with MNet.init()");
            return null;
        } else if (b.a().e()) {
            return null;
        } else {
            return a.d();
        }
    }

    public static void updateGdprConsent(SubjectToGDPR subjectToGDPR, ConsentStatus consentStatus, String str) {
        if (a == null) {
            MNetLog.error("Initialize SDK with MNet.init()");
        } else {
            a.a(subjectToGDPR, consentStatus, str);
        }
    }
}
