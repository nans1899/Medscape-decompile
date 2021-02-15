package bo.app;

public class fm extends fr implements fk {
    private String a;
    private String b;

    public String b() {
        return "iam_click";
    }

    public fm(String str) {
        this.a = a(str);
    }

    public fm(String str, String str2) {
        this(str);
        this.b = str2;
    }

    public String a() {
        return this.a;
    }

    public String f() {
        return this.b;
    }
}
