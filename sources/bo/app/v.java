package bo.app;

public enum v {
    ANDROID_VERSION("os_version"),
    CARRIER("carrier"),
    MODEL("model"),
    RESOLUTION("resolution"),
    LOCALE("locale"),
    TIMEZONE("time_zone"),
    NOTIFICATIONS_ENABLED("remote_notification_enabled");
    
    private String h;

    private v(String str) {
        this.h = str;
    }

    public String a() {
        return this.h;
    }
}
