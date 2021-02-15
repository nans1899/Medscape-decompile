package mnetinternal;

public final class s {
    private static s b;
    private r a = new t(new v());

    private s() {
    }

    public static r a() {
        if (b == null) {
            b = new s();
        }
        return b.a;
    }
}
