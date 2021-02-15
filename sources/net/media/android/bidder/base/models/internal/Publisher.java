package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;

public final class Publisher {
    @c(a = "id")
    protected String mId;

    protected Publisher() {
    }

    public Publisher(String str) {
        this.mId = str;
    }

    public final class TypeAdapter extends v<Publisher> {
        public static final g<Publisher> TYPE_TOKEN = g.b(Publisher.class);
        private final e mGson;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
        }

        public void write(j jVar, Publisher publisher) {
            if (publisher == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("id");
            if (publisher.mId != null) {
                i.A.write(jVar, publisher.mId);
            } else {
                jVar.f();
            }
            jVar.e();
        }

        public Publisher read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                Publisher publisher = new Publisher();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    if (g.hashCode() == 3355 && g.equals("id")) {
                        c = 0;
                    }
                    if (c != 0) {
                        hVar.n();
                    } else {
                        publisher.mId = i.A.read(hVar);
                    }
                }
                hVar.d();
                return publisher;
            }
        }
    }
}
