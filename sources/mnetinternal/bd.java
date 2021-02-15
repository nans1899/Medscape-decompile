package mnetinternal;

import java.util.List;
import org.json.JSONObject;

public class bd {
    private String a;
    private String b;
    private bh c;
    private be d;
    private boolean e;
    private boolean f;

    public bd(bh bhVar, boolean z) {
        this.c = bhVar;
        this.e = z;
        this.d = new be();
    }

    public bd(bh bhVar, boolean z, boolean z2, String str, String str2, List<bk> list, List<bm> list2, boolean z3, JSONObject jSONObject) {
        this(bhVar, z);
        this.a = str;
        this.b = str2;
        this.f = z3;
    }

    public String a() {
        bh bhVar = this.c;
        if (bhVar == null) {
            return null;
        }
        try {
            return this.d.a(bhVar);
        } catch (Exception e2) {
            bi.a("##ActivityInfo##", (Throwable) e2);
            return null;
        }
    }

    public boolean b() {
        return this.e;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.b;
    }

    public bh e() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.d.a();
    }
}
