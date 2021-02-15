package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import java.util.Map;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;
import net.media.android.bidder.base.models.internal.BidDetails;

public final class Bidder {
    @c(a = "bid_details")
    protected BidDetails mBidDetails;
    @c(a = "bid_info")
    protected Map<String, Object> mBidInfo;
    @c(a = "bidder_id")
    protected int mBidderId;
    @c(a = "id")
    protected String mId;
    @c(a = "m_bid")
    protected double mMainBid;

    public final class TypeAdapter extends v<Bidder> {
        public static final g<Bidder> TYPE_TOKEN = g.b(Bidder.class);
        private final e mGson;
        private final v<Object> mTypeAdapter0;
        private final v<Map<String, Object>> mTypeAdapter1 = new p.f(i.A, this.mTypeAdapter0, new p.e());
        private final v<BidDetails> mTypeAdapter2;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            this.mTypeAdapter0 = eVar.a(g.b(Object.class));
            this.mTypeAdapter2 = eVar.a(BidDetails.TypeAdapter.TYPE_TOKEN);
        }

        public void write(j jVar, Bidder bidder) {
            if (bidder == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("id");
            if (bidder.mId != null) {
                i.A.write(jVar, bidder.mId);
            } else {
                jVar.f();
            }
            jVar.a("bidder_id");
            jVar.a((long) bidder.mBidderId);
            jVar.a("m_bid");
            jVar.a(bidder.mMainBid);
            jVar.a("bid_info");
            if (bidder.mBidInfo != null) {
                this.mTypeAdapter1.write(jVar, bidder.mBidInfo);
            } else {
                jVar.f();
            }
            jVar.a("bid_details");
            if (bidder.mBidDetails != null) {
                this.mTypeAdapter2.write(jVar, bidder.mBidDetails);
            } else {
                jVar.f();
            }
            jVar.e();
        }

        public Bidder read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                Bidder bidder = new Bidder();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -1209650362:
                            if (g.equals("bidder_id")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 3355:
                            if (g.equals("id")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 97503488:
                            if (g.equals("bid_details")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 103591467:
                            if (g.equals("m_bid")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 649213616:
                            if (g.equals("bid_info")) {
                                c = 3;
                                break;
                            }
                            break;
                    }
                    if (c == 0) {
                        bidder.mId = i.A.read(hVar);
                    } else if (c == 1) {
                        bidder.mBidderId = p.j.a(hVar, bidder.mBidderId);
                    } else if (c == 2) {
                        bidder.mMainBid = p.h.a(hVar, bidder.mMainBid);
                    } else if (c == 3) {
                        bidder.mBidInfo = this.mTypeAdapter1.read(hVar);
                    } else if (c != 4) {
                        hVar.n();
                    } else {
                        bidder.mBidDetails = this.mTypeAdapter2.read(hVar);
                    }
                }
                hVar.d();
                return bidder;
            }
        }
    }

    protected Bidder() {
    }

    public Bidder(String str, String str2, double d, Map<String, Object> map, BidDetails bidDetails) {
        this.mId = str;
        try {
            this.mBidderId = Integer.parseInt(str2);
        } catch (NumberFormatException unused) {
        }
        this.mMainBid = d;
        this.mBidInfo = map;
        this.mBidDetails = bidDetails;
    }

    public static Bidder createNew(BidResponse bidResponse) {
        return new Bidder(bidResponse.getAdCycleId(), bidResponse.getBidderId(), bidResponse.getBidMultiplier(), bidResponse.getBidInfo(), BidDetails.createNew(bidResponse));
    }
}
