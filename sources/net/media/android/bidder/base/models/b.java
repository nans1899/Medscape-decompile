package net.media.android.bidder.base.models;

import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import com.webmd.wbmdcmepulse.models.articles.HtmlTableRow;
import java.util.Map;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;
import net.media.android.bidder.base.models.internal.AdDetails;
import net.media.android.bidder.base.models.internal.ServerDelays;

public final class b extends v<a> {
    public static final g<a> a = g.b(a.class);
    private final e b;
    private final v<AdDetails> c;
    private final v<Map<String, AdDetails>> d = new p.f(i.A, this.c, new p.e());
    private final v<Object> e;
    private final v<Map<String, Object>> f;
    private final v<ServerDelays> g;

    public b(e eVar) {
        this.b = eVar;
        g<Object> b2 = g.b(Object.class);
        g<ServerDelays> b3 = g.b(ServerDelays.class);
        this.c = eVar.a(AdDetails.TypeAdapter.TYPE_TOKEN);
        this.e = eVar.a(b2);
        this.f = new p.f(i.A, this.e, new p.e());
        this.g = eVar.a(b3);
    }

    /* renamed from: a */
    public void write(j jVar, a aVar) {
        if (aVar == null) {
            jVar.f();
            return;
        }
        jVar.d();
        jVar.a("ads");
        if (aVar.a != null) {
            this.d.write(jVar, aVar.a);
        } else {
            jVar.f();
        }
        jVar.a("ext");
        if (aVar.b != null) {
            this.f.write(jVar, aVar.b);
        } else {
            jVar.f();
        }
        jVar.a(HtmlTableRow.Row_TYPE_TABLE_DATA);
        if (aVar.c != null) {
            this.g.write(jVar, aVar.c);
        } else {
            jVar.f();
        }
        jVar.e();
    }

    /* renamed from: a */
    public a read(h hVar) {
        mnetinternal.i f2 = hVar.f();
        if (mnetinternal.i.NULL == f2) {
            hVar.j();
            return null;
        } else if (mnetinternal.i.BEGIN_OBJECT != f2) {
            hVar.n();
            return null;
        } else {
            hVar.c();
            a aVar = new a();
            while (hVar.e()) {
                String g2 = hVar.g();
                char c2 = 65535;
                int hashCode = g2.hashCode();
                if (hashCode != 3696) {
                    if (hashCode != 96432) {
                        if (hashCode == 100897 && g2.equals("ext")) {
                            c2 = 1;
                        }
                    } else if (g2.equals("ads")) {
                        c2 = 0;
                    }
                } else if (g2.equals(HtmlTableRow.Row_TYPE_TABLE_DATA)) {
                    c2 = 2;
                }
                if (c2 == 0) {
                    aVar.a = this.d.read(hVar);
                } else if (c2 == 1) {
                    aVar.b = this.f.read(hVar);
                } else if (c2 != 2) {
                    hVar.n();
                } else {
                    aVar.c = this.g.read(hVar);
                }
            }
            hVar.d();
            return aVar;
        }
    }
}
