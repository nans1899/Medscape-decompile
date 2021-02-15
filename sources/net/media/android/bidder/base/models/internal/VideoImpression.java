package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.v;
import java.util.ArrayList;
import java.util.List;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;
import mnetinternal.p;

public class VideoImpression {
    @c(a = "format")
    protected List<a> mAdFormats;

    VideoImpression() {
        this.mAdFormats = new ArrayList();
    }

    VideoImpression(List<a> list) {
        this.mAdFormats = list;
    }

    public final class TypeAdapter extends v<VideoImpression> {
        public static final g<VideoImpression> TYPE_TOKEN = g.b(VideoImpression.class);
        private final e mGson;
        private final v<a> mTypeAdapter0;
        private final v<List<a>> mTypeAdapter1;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            v<a> a = eVar.a(AdFormat$TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter0 = a;
            this.mTypeAdapter1 = new p.d(a, new p.c());
        }

        public void write(j jVar, VideoImpression videoImpression) {
            if (videoImpression == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("format");
            if (videoImpression.mAdFormats != null) {
                this.mTypeAdapter1.write(jVar, videoImpression.mAdFormats);
            } else {
                jVar.f();
            }
            jVar.e();
        }

        public VideoImpression read(h hVar) {
            i f = hVar.f();
            if (i.NULL == f) {
                hVar.j();
                return null;
            } else if (i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                VideoImpression videoImpression = new VideoImpression();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    if (g.hashCode() == -1268779017 && g.equals("format")) {
                        c = 0;
                    }
                    if (c != 0) {
                        hVar.n();
                    } else {
                        videoImpression.mAdFormats = this.mTypeAdapter1.read(hVar);
                    }
                }
                hVar.d();
                return videoImpression;
            }
        }
    }
}
