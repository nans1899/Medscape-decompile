package net.media.android.bidder.base.macro;

import java.util.ArrayList;
import java.util.List;
import mnetinternal.da;

public final class c {
    private List<String> a;

    public interface b {
        Object a(String str);
    }

    private c() {
        this.a = new ArrayList();
    }

    /* access modifiers changed from: private */
    public void a(List<String> list) {
        this.a.addAll(list);
    }

    public String a(String str, b bVar) {
        if (str == null) {
            return null;
        }
        for (String next : this.a) {
            if (str.contains(next)) {
                str = str.replaceAll(da.b(next), String.valueOf(bVar.a(next)));
            }
        }
        return str;
    }

    public static class a {
        private c a = new c();

        public a a(List<String> list) {
            this.a.a(list);
            return this;
        }

        public c a() {
            return this.a;
        }
    }
}
