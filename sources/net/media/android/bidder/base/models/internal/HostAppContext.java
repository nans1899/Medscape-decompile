package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;

public class HostAppContext {
    @c(a = "url")
    protected String mAppLink;
    @c(a = "ext")
    protected CrawlerLink mCrawlerLink;
    @c(a = "keywords")
    protected String mKeywords;

    protected static class CrawlerLink {
        @c(a = "url")
        protected String mCrawlerLink;

        public final class TypeAdapter extends v<CrawlerLink> {
            public static final g<CrawlerLink> TYPE_TOKEN = g.b(CrawlerLink.class);
            private final e mGson;

            public TypeAdapter(e eVar) {
                this.mGson = eVar;
            }

            public void write(j jVar, CrawlerLink crawlerLink) {
                if (crawlerLink == null) {
                    jVar.f();
                    return;
                }
                jVar.d();
                jVar.a("url");
                if (crawlerLink.mCrawlerLink != null) {
                    i.A.write(jVar, crawlerLink.mCrawlerLink);
                } else {
                    jVar.f();
                }
                jVar.e();
            }

            public CrawlerLink read(h hVar) {
                mnetinternal.i f = hVar.f();
                if (mnetinternal.i.NULL == f) {
                    hVar.j();
                    return null;
                } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                    hVar.n();
                    return null;
                } else {
                    hVar.c();
                    CrawlerLink crawlerLink = new CrawlerLink();
                    while (hVar.e()) {
                        String g = hVar.g();
                        char c = 65535;
                        if (g.hashCode() == 116079 && g.equals("url")) {
                            c = 0;
                        }
                        if (c != 0) {
                            hVar.n();
                        } else {
                            crawlerLink.mCrawlerLink = i.A.read(hVar);
                        }
                    }
                    hVar.d();
                    return crawlerLink;
                }
            }
        }

        protected CrawlerLink() {
        }

        CrawlerLink(String str) {
            this.mCrawlerLink = str;
        }

        /* access modifiers changed from: package-private */
        public String getCrawlerLink() {
            return this.mCrawlerLink;
        }

        /* access modifiers changed from: package-private */
        public boolean isNull() {
            return this.mCrawlerLink == null;
        }
    }

    protected HostAppContext() {
    }

    public final class TypeAdapter extends v<HostAppContext> {
        public static final g<HostAppContext> TYPE_TOKEN = g.b(HostAppContext.class);
        private final e mGson;
        private final v<CrawlerLink> mTypeAdapter0;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            this.mTypeAdapter0 = eVar.a(CrawlerLink.TypeAdapter.TYPE_TOKEN);
        }

        public void write(j jVar, HostAppContext hostAppContext) {
            if (hostAppContext == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("url");
            if (hostAppContext.mAppLink != null) {
                i.A.write(jVar, hostAppContext.mAppLink);
            } else {
                jVar.f();
            }
            jVar.a("ext");
            if (hostAppContext.mCrawlerLink != null) {
                this.mTypeAdapter0.write(jVar, hostAppContext.mCrawlerLink);
            } else {
                jVar.f();
            }
            jVar.a("keywords");
            if (hostAppContext.mKeywords != null) {
                i.A.write(jVar, hostAppContext.mKeywords);
            } else {
                jVar.f();
            }
            jVar.e();
        }

        public HostAppContext read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                HostAppContext hostAppContext = new HostAppContext();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    int hashCode = g.hashCode();
                    if (hashCode != 100897) {
                        if (hashCode != 116079) {
                            if (hashCode == 523149226 && g.equals("keywords")) {
                                c = 2;
                            }
                        } else if (g.equals("url")) {
                            c = 0;
                        }
                    } else if (g.equals("ext")) {
                        c = 1;
                    }
                    if (c == 0) {
                        hostAppContext.mAppLink = i.A.read(hVar);
                    } else if (c == 1) {
                        hostAppContext.mCrawlerLink = this.mTypeAdapter0.read(hVar);
                    } else if (c != 2) {
                        hVar.n();
                    } else {
                        hostAppContext.mKeywords = i.A.read(hVar);
                    }
                }
                hVar.d();
                return hostAppContext;
            }
        }
    }

    public HostAppContext(String str, String str2) {
        this.mAppLink = str;
        this.mCrawlerLink = new CrawlerLink(str2);
    }

    public void setAppLink(String str) {
        this.mAppLink = str;
    }

    public void setCrawlerLink(String str) {
        this.mCrawlerLink = new CrawlerLink(str);
    }

    public void setKeywords(String str) {
        this.mKeywords = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.mCrawlerLink;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isNull() {
        /*
            r1 = this;
            java.lang.String r0 = r1.mAppLink
            if (r0 != 0) goto L_0x0010
            net.media.android.bidder.base.models.internal.HostAppContext$CrawlerLink r0 = r1.mCrawlerLink
            if (r0 == 0) goto L_0x000e
            boolean r0 = r0.isNull()
            if (r0 == 0) goto L_0x0010
        L_0x000e:
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.models.internal.HostAppContext.isNull():boolean");
    }

    public String getCrawlerLink() {
        CrawlerLink crawlerLink = this.mCrawlerLink;
        if (crawlerLink == null) {
            return null;
        }
        return crawlerLink.getCrawlerLink();
    }

    public String getAppLink() {
        return this.mAppLink;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("HostAppContext { app_link: ");
        sb.append(this.mAppLink);
        sb.append(", crawler_link: ");
        CrawlerLink crawlerLink = this.mCrawlerLink;
        if (crawlerLink == null) {
            str = "null";
        } else {
            str = crawlerLink.getCrawlerLink();
        }
        sb.append(str);
        sb.append(", keywords: ");
        sb.append(this.mKeywords);
        sb.append(" }");
        return sb.toString();
    }
}
