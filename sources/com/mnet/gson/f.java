package com.mnet.gson;

import com.mnet.gson.internal.Excluder;
import com.mnet.gson.internal.a;
import com.mnet.gson.internal.bind.TreeTypeAdapter;
import com.mnet.gson.internal.bind.i;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mnetinternal.g;

public final class f {
    private Excluder a = Excluder.a;
    private u b = u.a;
    private d c = c.IDENTITY;
    private final Map<Type, g<?>> d = new HashMap();
    private final List<w> e = new ArrayList();
    private final List<w> f = new ArrayList();
    private boolean g = false;
    private String h;
    private int i = 2;
    private int j = 2;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;

    public f a() {
        this.g = true;
        return this;
    }

    public f a(Type type, Object obj) {
        boolean z = obj instanceof s;
        a.a(z || (obj instanceof j) || (obj instanceof g) || (obj instanceof v));
        if (obj instanceof g) {
            this.d.put(type, (g) obj);
        }
        if (z || (obj instanceof j)) {
            this.e.add(TreeTypeAdapter.b(g.a(type), obj));
        }
        if (obj instanceof v) {
            this.e.add(i.a(g.a(type), (v) obj));
        }
        return this;
    }

    public f a(w wVar) {
        this.e.add(wVar);
        return this;
    }

    public e b() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        arrayList.addAll(this.f);
        a(this.h, this.i, this.j, arrayList);
        return new e(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, arrayList);
    }

    private void a(String str, int i2, int i3, List<w> list) {
        DefaultDateTypeAdapter defaultDateTypeAdapter;
        if (str != null && !"".equals(str.trim())) {
            defaultDateTypeAdapter = new DefaultDateTypeAdapter(str);
        } else if (i2 != 2 && i3 != 2) {
            defaultDateTypeAdapter = new DefaultDateTypeAdapter(i2, i3);
        } else {
            return;
        }
        list.add(TreeTypeAdapter.a(g.b(Date.class), defaultDateTypeAdapter));
        list.add(TreeTypeAdapter.a(g.b(Timestamp.class), defaultDateTypeAdapter));
        list.add(TreeTypeAdapter.a(g.b(java.sql.Date.class), defaultDateTypeAdapter));
    }
}
