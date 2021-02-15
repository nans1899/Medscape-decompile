package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.v;
import java.util.List;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;
import mnetinternal.p;

public final class BannerImpression$TypeAdapter extends v<b> {
    public static final g<b> TYPE_TOKEN = g.b(b.class);
    private final e mGson;
    private final v<a> mTypeAdapter0;
    private final v<List<a>> mTypeAdapter1;

    public BannerImpression$TypeAdapter(e eVar) {
        this.mGson = eVar;
        v<a> a = eVar.a(AdFormat$TypeAdapter.TYPE_TOKEN);
        this.mTypeAdapter0 = a;
        this.mTypeAdapter1 = new p.d(a, new p.c());
    }

    public void write(j jVar, b bVar) {
        if (bVar == null) {
            jVar.f();
            return;
        }
        jVar.d();
        jVar.a("format");
        if (bVar.a != null) {
            this.mTypeAdapter1.write(jVar, bVar.a);
        } else {
            jVar.f();
        }
        jVar.e();
    }

    public b read(h hVar) {
        i f = hVar.f();
        if (i.NULL == f) {
            hVar.j();
            return null;
        } else if (i.BEGIN_OBJECT != f) {
            hVar.n();
            return null;
        } else {
            hVar.c();
            b bVar = new b();
            while (hVar.e()) {
                String g = hVar.g();
                char c = 65535;
                if (g.hashCode() == -1268779017 && g.equals("format")) {
                    c = 0;
                }
                if (c != 0) {
                    hVar.n();
                } else {
                    bVar.a = this.mTypeAdapter1.read(hVar);
                }
            }
            hVar.d();
            return bVar;
        }
    }
}
