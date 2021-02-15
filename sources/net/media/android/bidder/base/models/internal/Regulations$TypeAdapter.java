package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.v;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;
import mnetinternal.p;

public final class Regulations$TypeAdapter extends v<c> {
    public static final g<c> TYPE_TOKEN = g.b(c.class);
    private final e mGson;

    public Regulations$TypeAdapter(e eVar) {
        this.mGson = eVar;
    }

    public void write(j jVar, c cVar) {
        if (cVar == null) {
            jVar.f();
            return;
        }
        jVar.d();
        jVar.a("coppa");
        jVar.a((long) cVar.a);
        jVar.e();
    }

    public c read(h hVar) {
        i f = hVar.f();
        if (i.NULL == f) {
            hVar.j();
            return null;
        } else if (i.BEGIN_OBJECT != f) {
            hVar.n();
            return null;
        } else {
            hVar.c();
            c cVar = new c();
            while (hVar.e()) {
                String g = hVar.g();
                char c = 65535;
                if (g.hashCode() == 94846581 && g.equals("coppa")) {
                    c = 0;
                }
                if (c != 0) {
                    hVar.n();
                } else {
                    cVar.a = p.j.a(hVar, cVar.a);
                }
            }
            hVar.d();
            return cVar;
        }
    }
}
