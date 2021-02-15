package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.v;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;
import mnetinternal.p;

public final class AdSource {
    @c(a = "source_fd")
    protected int mSource;

    protected AdSource() {
    }

    public AdSource(boolean z) {
        this.mSource = z ^ true ? 1 : 0;
    }

    public final class TypeAdapter extends v<AdSource> {
        public static final g<AdSource> TYPE_TOKEN = g.b(AdSource.class);
        private final e mGson;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
        }

        public void write(j jVar, AdSource adSource) {
            if (adSource == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("source_fd");
            jVar.a((long) adSource.mSource);
            jVar.e();
        }

        public AdSource read(h hVar) {
            i f = hVar.f();
            if (i.NULL == f) {
                hVar.j();
                return null;
            } else if (i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                AdSource adSource = new AdSource();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    if (g.hashCode() == -1698410654 && g.equals("source_fd")) {
                        c = 0;
                    }
                    if (c != 0) {
                        hVar.n();
                    } else {
                        adSource.mSource = p.j.a(hVar, adSource.mSource);
                    }
                }
                hVar.d();
                return adSource;
            }
        }
    }
}
