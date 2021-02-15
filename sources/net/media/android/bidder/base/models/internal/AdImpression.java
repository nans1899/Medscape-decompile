package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import java.util.ArrayList;
import mnetinternal.c;
import mnetinternal.cu;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.models.AdSize;
import net.media.android.bidder.base.models.internal.AdSource;
import net.media.android.bidder.base.models.internal.VideoImpression;

final class AdImpression {
    @c(a = "tagid")
    protected String a;
    @c(a = "instl")
    protected int b;
    @c(a = "banner")
    protected b c;
    @c(a = "video")
    protected VideoImpression d;
    @c(a = "ext")
    protected AdSource e;
    @c(a = "clickbrowser")
    protected int f;

    public final class TypeAdapter extends v<AdImpression> {
        public static final g<AdImpression> TYPE_TOKEN = g.b(AdImpression.class);
        private final e mGson;
        private final v<b> mTypeAdapter0;
        private final v<VideoImpression> mTypeAdapter1;
        private final v<AdSource> mTypeAdapter2;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            this.mTypeAdapter0 = eVar.a(BannerImpression$TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter1 = eVar.a(VideoImpression.TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter2 = eVar.a(AdSource.TypeAdapter.TYPE_TOKEN);
        }

        public void write(j jVar, AdImpression adImpression) {
            if (adImpression == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("tagid");
            if (adImpression.a != null) {
                i.A.write(jVar, adImpression.a);
            } else {
                jVar.f();
            }
            jVar.a("instl");
            jVar.a((long) adImpression.b);
            jVar.a(Constants.Capabilities.BANNER);
            if (adImpression.c != null) {
                this.mTypeAdapter0.write(jVar, adImpression.c);
            } else {
                jVar.f();
            }
            jVar.a("video");
            if (adImpression.d != null) {
                this.mTypeAdapter1.write(jVar, adImpression.d);
            } else {
                jVar.f();
            }
            jVar.a("ext");
            if (adImpression.e != null) {
                this.mTypeAdapter2.write(jVar, adImpression.e);
            } else {
                jVar.f();
            }
            jVar.a("clickbrowser");
            jVar.a((long) adImpression.f);
            jVar.e();
        }

        public AdImpression read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                AdImpression adImpression = new AdImpression();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -1396342996:
                            if (g.equals(Constants.Capabilities.BANNER)) {
                                c = 2;
                                break;
                            }
                            break;
                        case -958292832:
                            if (g.equals("clickbrowser")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 100897:
                            if (g.equals("ext")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 100360934:
                            if (g.equals("instl")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 110120501:
                            if (g.equals("tagid")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 112202875:
                            if (g.equals("video")) {
                                c = 3;
                                break;
                            }
                            break;
                    }
                    if (c == 0) {
                        adImpression.a = i.A.read(hVar);
                    } else if (c == 1) {
                        adImpression.b = p.j.a(hVar, adImpression.b);
                    } else if (c == 2) {
                        adImpression.c = this.mTypeAdapter0.read(hVar);
                    } else if (c == 3) {
                        adImpression.d = this.mTypeAdapter1.read(hVar);
                    } else if (c == 4) {
                        adImpression.e = this.mTypeAdapter2.read(hVar);
                    } else if (c != 5) {
                        hVar.n();
                    } else {
                        adImpression.f = p.j.a(hVar, adImpression.f);
                    }
                }
                hVar.d();
                return adImpression;
            }
        }
    }

    protected AdImpression() {
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        this.a = str;
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        this.b = i;
    }

    /* access modifiers changed from: private */
    public void a(b bVar) {
        this.c = bVar;
    }

    /* access modifiers changed from: private */
    public void a(VideoImpression videoImpression) {
        this.d = videoImpression;
    }

    /* access modifiers changed from: private */
    public void a(AdSource adSource) {
        this.e = adSource;
    }

    /* access modifiers changed from: private */
    public void b(int i) {
        this.f = i;
    }

    public static class Builder {
        private AdImpression mAdImpression = new AdImpression();

        public Builder() {
            if (AdUnitConfig.getInstance().getPublisherConfig().isInAppBrowsingEnabled()) {
                this.mAdImpression.b(0);
            } else {
                this.mAdImpression.b(1);
            }
        }

        public Builder setAdUnitId(String str) {
            this.mAdImpression.a(str);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setIsInterstitial(int i) {
            this.mAdImpression.a(i);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setImpressionType(AdSize... adSizeArr) {
            if (cu.a().a(Constants.Capabilities.BANNER)) {
                if (adSizeArr == null) {
                    this.mAdImpression.a(new b());
                } else {
                    ArrayList arrayList = new ArrayList();
                    for (AdSize adSize : adSizeArr) {
                        arrayList.add(new a(adSize.getWidth(), adSize.getHeight()));
                    }
                    this.mAdImpression.a(new b(arrayList));
                }
            }
            if (cu.a().a("video")) {
                if (adSizeArr == null) {
                    this.mAdImpression.a(new VideoImpression());
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    for (AdSize adSize2 : adSizeArr) {
                        arrayList2.add(new a(adSize2.getWidth(), adSize2.getHeight()));
                    }
                    this.mAdImpression.a(new VideoImpression(arrayList2));
                }
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAdSource(AdSource adSource) {
            this.mAdImpression.a(adSource);
            return this;
        }

        public AdImpression build() {
            return this.mAdImpression;
        }
    }
}
