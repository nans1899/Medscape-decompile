package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.v;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;
import mnetinternal.p;

public final class AdFormat$TypeAdapter extends v<a> {
    public static final g<a> TYPE_TOKEN = g.b(a.class);
    private final e mGson;

    public AdFormat$TypeAdapter(e eVar) {
        this.mGson = eVar;
    }

    public void write(j jVar, a aVar) {
        if (aVar == null) {
            jVar.f();
            return;
        }
        jVar.d();
        jVar.a("w");
        jVar.a((long) aVar.a);
        jVar.a("h");
        jVar.a((long) aVar.b);
        jVar.e();
    }

    public a read(h hVar) {
        i f = hVar.f();
        if (i.NULL == f) {
            hVar.j();
            return null;
        } else if (i.BEGIN_OBJECT != f) {
            hVar.n();
            return null;
        } else {
            hVar.c();
            a aVar = new a();
            while (hVar.e()) {
                String g = hVar.g();
                char c = 65535;
                int hashCode = g.hashCode();
                if (hashCode != 104) {
                    if (hashCode == 119 && g.equals("w")) {
                        c = 0;
                    }
                } else if (g.equals("h")) {
                    c = 1;
                }
                if (c == 0) {
                    aVar.a = p.j.a(hVar, aVar.a);
                } else if (c != 1) {
                    hVar.n();
                } else {
                    aVar.b = p.j.a(hVar, aVar.b);
                }
            }
            hVar.d();
            return aVar;
        }
    }
}
