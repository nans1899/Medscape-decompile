package bo.app;

public class aw extends Exception {
    public aw(String str, Throwable th) {
        super(str, th);
    }

    public aw(String str) {
        super(str);
    }
}
