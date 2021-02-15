package bo.app;

public enum u {
    SHORT("yyyy-MM-dd"),
    LONG("yyyy-MM-dd kk:mm:ss"),
    ANDROID_LOGCAT("MM-dd kk:mm:ss.SSS");
    
    private final String d;

    private u(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }
}
