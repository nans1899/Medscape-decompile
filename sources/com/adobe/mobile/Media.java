package com.adobe.mobile;

import java.util.HashMap;
import java.util.Map;

public final class Media {
    private static final String NO_ANALYTICS_MESSAGE = "Analytics - ADBMobile is not configured correctly to use Analytics.";

    public interface MediaCallback<T> {
        void call(T t);
    }

    public static MediaSettings settingsWith(String str, double d, String str2, String str3) {
        return MediaSettings.settingsWith(str, d, str2, str3);
    }

    public static MediaSettings adSettingsWith(String str, double d, String str2, String str3, String str4, double d2, String str5) {
        return MediaSettings.adSettingsWith(str, d, str2, str3, str4, d2, str5);
    }

    public static void open(final MediaSettings mediaSettings, final MediaCallback mediaCallback) {
        StaticMethods.getMediaExecutor().execute(new Runnable() {
            public void run() {
                MediaAnalytics.sharedInstance().open(mediaSettings, mediaCallback);
            }
        });
    }

    public static void close(final String str) {
        StaticMethods.getMediaExecutor().execute(new Runnable() {
            public void run() {
                MediaAnalytics.sharedInstance().close(str);
            }
        });
    }

    public static void play(final String str, final double d) {
        StaticMethods.getMediaExecutor().execute(new Runnable() {
            public void run() {
                MediaAnalytics.sharedInstance().play(str, d);
            }
        });
    }

    public static void complete(final String str, final double d) {
        StaticMethods.getMediaExecutor().execute(new Runnable() {
            public void run() {
                MediaAnalytics.sharedInstance().complete(str, d);
            }
        });
    }

    public static void stop(final String str, final double d) {
        StaticMethods.getMediaExecutor().execute(new Runnable() {
            public void run() {
                MediaAnalytics.sharedInstance().stop(str, d);
            }
        });
    }

    public static void click(final String str, final double d) {
        StaticMethods.getMediaExecutor().execute(new Runnable() {
            public void run() {
                MediaAnalytics.sharedInstance().click(str, d);
            }
        });
    }

    public static void track(final String str, Map<String, Object> map) {
        MediaAnalytics.sharedInstance().setTrackCalledOnItem(str);
        final HashMap hashMap = map != null ? new HashMap(map) : null;
        StaticMethods.getMediaExecutor().execute(new Runnable() {
            public void run() {
                MediaAnalytics.sharedInstance().track(str, hashMap);
            }
        });
    }
}
