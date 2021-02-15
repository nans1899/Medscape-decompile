package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import java.util.List;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;

public final class BidDetails {
    @c(a = "adcode")
    protected String mAdCode;
    @c(a = "adurl")
    protected String mAdUrl;
    @c(a = "creative_type")
    protected String mCreativeType;
    @c(a = "winner")
    protected boolean mIsWinner;
    @c(a = "logging_pixels")
    protected List<String> mLoggingPixels;
    @c(a = "size")
    protected String mSize;

    public final class TypeAdapter extends v<BidDetails> {
        public static final g<BidDetails> TYPE_TOKEN = g.b(BidDetails.class);
        private final e mGson;
        private final v<List<String>> mTypeAdapter0 = new p.d(i.A, new p.c());

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
        }

        public void write(j jVar, BidDetails bidDetails) {
            if (bidDetails == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("creative_type");
            if (bidDetails.mCreativeType != null) {
                i.A.write(jVar, bidDetails.mCreativeType);
            } else {
                jVar.f();
            }
            jVar.a("adcode");
            if (bidDetails.mAdCode != null) {
                i.A.write(jVar, bidDetails.mAdCode);
            } else {
                jVar.f();
            }
            jVar.a("adurl");
            if (bidDetails.mAdUrl != null) {
                i.A.write(jVar, bidDetails.mAdUrl);
            } else {
                jVar.f();
            }
            jVar.a("winner");
            jVar.a(bidDetails.mIsWinner);
            jVar.a("size");
            if (bidDetails.mSize != null) {
                i.A.write(jVar, bidDetails.mSize);
            } else {
                jVar.f();
            }
            jVar.a("logging_pixels");
            if (bidDetails.mLoggingPixels != null) {
                this.mTypeAdapter0.write(jVar, bidDetails.mLoggingPixels);
            } else {
                jVar.f();
            }
            jVar.e();
        }

        public BidDetails read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                BidDetails bidDetails = new BidDetails();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -1422528368:
                            if (g.equals("adcode")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -829622387:
                            if (g.equals("logging_pixels")) {
                                c = 5;
                                break;
                            }
                            break;
                        case -787742657:
                            if (g.equals("winner")) {
                                c = 3;
                                break;
                            }
                            break;
                        case -702722614:
                            if (g.equals("creative_type")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 3530753:
                            if (g.equals("size")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 92676716:
                            if (g.equals("adurl")) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    if (c == 0) {
                        bidDetails.mCreativeType = i.A.read(hVar);
                    } else if (c == 1) {
                        bidDetails.mAdCode = i.A.read(hVar);
                    } else if (c == 2) {
                        bidDetails.mAdUrl = i.A.read(hVar);
                    } else if (c == 3) {
                        bidDetails.mIsWinner = p.g.a(hVar, bidDetails.mIsWinner);
                    } else if (c == 4) {
                        bidDetails.mSize = i.A.read(hVar);
                    } else if (c != 5) {
                        hVar.n();
                    } else {
                        bidDetails.mLoggingPixels = this.mTypeAdapter0.read(hVar);
                    }
                }
                hVar.d();
                return bidDetails;
            }
        }
    }

    protected BidDetails() {
    }

    public BidDetails(String str, String str2, String str3, boolean z, List<String> list, String str4) {
        this.mCreativeType = str;
        this.mAdCode = str2;
        this.mAdUrl = str3;
        this.mIsWinner = z;
        this.mLoggingPixels = list;
        this.mSize = str4;
    }

    public static BidDetails createNew(BidResponse bidResponse) {
        return new BidDetails(bidResponse.getCreativeType(), bidResponse.getRawAdCode(), bidResponse.getAdUrl(), bidResponse.isWinner(), bidResponse.getLoggingBeacons(), bidResponse.getSize());
    }
}
