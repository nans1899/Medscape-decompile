package bo.app;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class gh {
    private boolean a;
    private StringBuilder b;
    private String c;
    private Object d;
    private Object e;
    private final List<gd> f;
    private final List<gd> g;
    private final List<gd> h;

    public gh() {
        this(true, (String) null);
    }

    private gh(boolean z, String str) {
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.a = z;
        this.b = new StringBuilder(str == null ? "" : str);
    }

    public boolean a() {
        return this.a;
    }

    public boolean b() {
        return !this.a;
    }

    public String c() {
        return this.b.toString();
    }

    public void a(String str) {
        this.a = false;
        if (this.b.length() == 0) {
            this.b.append(str);
            return;
        }
        StringBuilder sb = this.b;
        sb.append(" ; ");
        sb.append(str);
    }

    public gh a(String str, Object obj, Object obj2) {
        this.f.add(new gd(str, obj, obj2));
        this.c = str;
        this.d = obj;
        this.e = obj2;
        a(b(str, obj, obj2));
        return this;
    }

    private String b(String str, Object obj, Object obj2) {
        return str + "\nExpected: " + a(obj) + "\n     got: " + a(obj2) + IOUtils.LINE_SEPARATOR_UNIX;
    }

    public gh a(String str, Object obj) {
        this.g.add(new gd(str, obj, (Object) null));
        a(c(str, obj));
        return this;
    }

    private String c(String str, Object obj) {
        return str + "\nExpected: " + a(obj) + "\n     but none found\n";
    }

    public gh b(String str, Object obj) {
        this.h.add(new gd(str, (Object) null, obj));
        a(d(str, obj));
        return this;
    }

    private String d(String str, Object obj) {
        return str + "\nUnexpected: " + a(obj) + IOUtils.LINE_SEPARATOR_UNIX;
    }

    private static String a(Object obj) {
        if (obj instanceof JSONArray) {
            return "a JSON array";
        }
        if (obj instanceof JSONObject) {
            return "a JSON object";
        }
        return obj.toString();
    }

    public String toString() {
        return this.b.toString();
    }
}
