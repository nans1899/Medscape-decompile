package mnetinternal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class bk {
    private String a;
    private int b;
    private int c;

    public bk(bm bmVar) {
        this.b = bmVar.d();
        this.c = bmVar.a();
        this.a = bmVar.b();
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String a(int i) {
        return "s[" + i + "].r";
    }

    public String b(int i) {
        return "s[" + i + "].id";
    }

    public String c(int i) {
        return "s[" + i + "].of";
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bk bkVar = (bk) obj;
        if (this.b == bkVar.b && this.c == bkVar.c) {
            return bkVar.a.equals(this.a);
        }
        return false;
    }

    public static void a(List<bk> list) {
        Collections.sort(list, new Comparator<bk>() {
            /* renamed from: a */
            public int compare(bk bkVar, bk bkVar2) {
                if (bkVar2.c() == bkVar.c()) {
                    return 0;
                }
                return bkVar2.c() > bkVar.c() ? 1 : -1;
            }
        });
    }
}
