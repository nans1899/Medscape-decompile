package mnetinternal;

import com.mnet.gson.e;
import java.io.Closeable;
import java.io.InputStream;
import net.media.android.bidder.base.logging.Logger;

public final class ag {
    private al a;
    /* access modifiers changed from: private */
    public ae b;

    public interface b {
        void a(InputStream inputStream);

        void a(Throwable th);
    }

    private ag() {
    }

    /* access modifiers changed from: private */
    public void a(al alVar) {
        this.a = alVar;
    }

    /* access modifiers changed from: private */
    public void a(ae aeVar) {
        this.b = aeVar;
    }

    /* access modifiers changed from: package-private */
    public <T> void a(int i, ai aiVar, final ak<T> akVar) {
        aiVar.b(i);
        if (!cb.a().b("__mn__config_expired") || aiVar.b().startsWith(an.c())) {
            final Class<T> a2 = akVar.a();
            this.a.a(aiVar, new b() {
                public void a(InputStream inputStream) {
                    try {
                        final Object cast = a2.cast(ag.this.b.a(inputStream, a2));
                        aa.a((Runnable) new ac() {
                            public void a() {
                                if (cast != null) {
                                    akVar.a(cast);
                                } else {
                                    akVar.a(new Throwable("Internal Error"));
                                }
                            }
                        });
                        Logger.debug("##HttpClient##", "response =>> " + new e().b(cast));
                    } catch (Exception e) {
                        da.a((Closeable) inputStream);
                        aa.a((Runnable) new ac() {
                            public void a() {
                                akVar.a((Throwable) e);
                            }
                        });
                    }
                }

                public void a(Throwable th) {
                    akVar.a(th);
                }
            });
            return;
        }
        akVar.a(new Throwable("Config Expired"));
        Logger.debug("##HttpClient##", "config expired, disabling request " + aiVar.b());
    }

    public static class a {
        private ag a = new ag();

        public a a(al alVar) {
            this.a.a(alVar);
            return this;
        }

        public a a(ae aeVar) {
            this.a.a(aeVar);
            return this;
        }

        public ag a() {
            return this.a;
        }
    }
}
