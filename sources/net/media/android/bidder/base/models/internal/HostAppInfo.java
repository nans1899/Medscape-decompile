package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import java.util.HashMap;
import java.util.Map;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;
import net.media.android.bidder.base.models.internal.HostAppContext;
import net.media.android.bidder.base.models.internal.Publisher;

public final class HostAppInfo {
    protected static final String VERSION_CODE_KEY = "ver_code";
    @c(a = "ver")
    protected String mAppVersionName;
    @c(a = "ext")
    protected Map<String, Object> mExtensions = new HashMap();
    @c(a = "content")
    protected HostAppContext mHostAppContext;
    @c(a = "foreground")
    protected boolean mIsForeground;
    @c(a = "bundle")
    protected String mPackageName;
    @c(a = "publisher")
    protected Publisher mPublisher;

    protected HostAppInfo() {
    }

    public final class TypeAdapter extends v<HostAppInfo> {
        public static final g<HostAppInfo> TYPE_TOKEN = g.b(HostAppInfo.class);
        private final e mGson;
        private final v<Publisher> mTypeAdapter0;
        private final v<HostAppContext> mTypeAdapter1;
        private final v<Object> mTypeAdapter2;
        private final v<Map<String, Object>> mTypeAdapter3 = new p.f(i.A, this.mTypeAdapter2, new p.e());

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            g<Object> b = g.b(Object.class);
            this.mTypeAdapter0 = eVar.a(Publisher.TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter1 = eVar.a(HostAppContext.TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter2 = eVar.a(b);
        }

        public void write(j jVar, HostAppInfo hostAppInfo) {
            if (hostAppInfo == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("bundle");
            if (hostAppInfo.mPackageName != null) {
                i.A.write(jVar, hostAppInfo.mPackageName);
            } else {
                jVar.f();
            }
            jVar.a("ver");
            if (hostAppInfo.mAppVersionName != null) {
                i.A.write(jVar, hostAppInfo.mAppVersionName);
            } else {
                jVar.f();
            }
            jVar.a(Logger.PUBLISHER);
            if (hostAppInfo.mPublisher != null) {
                this.mTypeAdapter0.write(jVar, hostAppInfo.mPublisher);
            } else {
                jVar.f();
            }
            jVar.a("content");
            if (hostAppInfo.mHostAppContext != null) {
                this.mTypeAdapter1.write(jVar, hostAppInfo.mHostAppContext);
            } else {
                jVar.f();
            }
            jVar.a("foreground");
            jVar.a(hostAppInfo.mIsForeground);
            jVar.a("ext");
            if (hostAppInfo.mExtensions != null) {
                this.mTypeAdapter3.write(jVar, hostAppInfo.mExtensions);
            } else {
                jVar.f();
            }
            jVar.e();
        }

        public HostAppInfo read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                HostAppInfo hostAppInfo = new HostAppInfo();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -1377881982:
                            if (g.equals("bundle")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 100897:
                            if (g.equals("ext")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 116643:
                            if (g.equals("ver")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 951530617:
                            if (g.equals("content")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 1447404028:
                            if (g.equals(Logger.PUBLISHER)) {
                                c = 2;
                                break;
                            }
                            break;
                        case 1984457027:
                            if (g.equals("foreground")) {
                                c = 4;
                                break;
                            }
                            break;
                    }
                    if (c == 0) {
                        hostAppInfo.mPackageName = i.A.read(hVar);
                    } else if (c == 1) {
                        hostAppInfo.mAppVersionName = i.A.read(hVar);
                    } else if (c == 2) {
                        hostAppInfo.mPublisher = this.mTypeAdapter0.read(hVar);
                    } else if (c == 3) {
                        hostAppInfo.mHostAppContext = this.mTypeAdapter1.read(hVar);
                    } else if (c == 4) {
                        hostAppInfo.mIsForeground = p.g.a(hVar, hostAppInfo.mIsForeground);
                    } else if (c != 5) {
                        hVar.n();
                    } else {
                        hostAppInfo.mExtensions = this.mTypeAdapter3.read(hVar);
                    }
                }
                hVar.d();
                return hostAppInfo;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    /* access modifiers changed from: protected */
    public void setAppVersionName(String str) {
        this.mAppVersionName = str;
    }

    /* access modifiers changed from: protected */
    public void setAppVersionCode(int i) {
        this.mExtensions.put(VERSION_CODE_KEY, String.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void setPublisher(Publisher publisher) {
        this.mPublisher = publisher;
    }

    /* access modifiers changed from: protected */
    public void setHostAppContext(HostAppContext hostAppContext) {
        this.mHostAppContext = hostAppContext;
    }

    /* access modifiers changed from: protected */
    public void setIsForeground(boolean z) {
        this.mIsForeground = z;
    }

    public static class Builder {
        protected HostAppInfo mHostAppInfo = new HostAppInfo();

        /* access modifiers changed from: package-private */
        public Builder setPackageName(String str) {
            this.mHostAppInfo.setPackageName(str);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAppVersionName(String str) {
            this.mHostAppInfo.setAppVersionName(str);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAppVersionCode(int i) {
            this.mHostAppInfo.setAppVersionCode(i);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setPublisher(Publisher publisher) {
            this.mHostAppInfo.setPublisher(publisher);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setHostAppContext(HostAppContext hostAppContext) {
            this.mHostAppInfo.setHostAppContext(hostAppContext);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setIsForeground(boolean z) {
            this.mHostAppInfo.setIsForeground(z);
            return this;
        }

        public HostAppInfo build() {
            return this.mHostAppInfo;
        }
    }
}
