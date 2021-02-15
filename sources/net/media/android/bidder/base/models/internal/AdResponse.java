package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.v;
import java.util.List;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;
import mnetinternal.p;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class AdResponse {
    protected AdRequest mAdRequest;
    protected List<BidResponse> mBidResponses;
    protected int mClientAuctionTime;
    protected boolean mDefaultResponse;
    protected boolean mIsClientAuction;
    protected boolean mIsInterstitial;
    protected BidResponse mWinningBid;

    protected AdResponse() {
    }

    public AdResponse(List<BidResponse> list, AdRequest adRequest) {
        this(list, adRequest, false);
    }

    public final class TypeAdapter extends v<AdResponse> {
        public static final g<AdResponse> TYPE_TOKEN = g.b(AdResponse.class);
        private final e mGson;
        private final v<BidResponse> mTypeAdapter0;
        private final v<List<BidResponse>> mTypeAdapter1;
        private final v<AdRequest> mTypeAdapter2;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            g<AdRequest> b = g.b(AdRequest.class);
            v<BidResponse> a = eVar.a(BidResponse.TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter0 = a;
            this.mTypeAdapter1 = new p.d(a, new p.c());
            this.mTypeAdapter2 = eVar.a(b);
        }

        public void write(j jVar, AdResponse adResponse) {
            if (adResponse == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("mBidResponses");
            if (adResponse.mBidResponses != null) {
                this.mTypeAdapter1.write(jVar, adResponse.mBidResponses);
            } else {
                jVar.f();
            }
            jVar.a("mWinningBid");
            if (adResponse.mWinningBid != null) {
                this.mTypeAdapter0.write(jVar, adResponse.mWinningBid);
            } else {
                jVar.f();
            }
            jVar.a("mIsInterstitial");
            jVar.a(adResponse.mIsInterstitial);
            jVar.a("mIsClientAuction");
            jVar.a(adResponse.mIsClientAuction);
            jVar.a("mClientAuctionTime");
            jVar.a((long) adResponse.mClientAuctionTime);
            jVar.a("mAdRequest");
            if (adResponse.mAdRequest != null) {
                this.mTypeAdapter2.write(jVar, adResponse.mAdRequest);
            } else {
                jVar.f();
            }
            jVar.a("mDefaultResponse");
            jVar.a(adResponse.mDefaultResponse);
            jVar.e();
        }

        public AdResponse read(h hVar) {
            i f = hVar.f();
            if (i.NULL == f) {
                hVar.j();
                return null;
            } else if (i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                AdResponse adResponse = new AdResponse();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -517838081:
                            if (g.equals("mAdRequest")) {
                                c = 5;
                                break;
                            }
                            break;
                        case -44211389:
                            if (g.equals("mIsInterstitial")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 179404002:
                            if (g.equals("mBidResponses")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 847097205:
                            if (g.equals("mDefaultResponse")) {
                                c = 6;
                                break;
                            }
                            break;
                        case 982927864:
                            if (g.equals("mClientAuctionTime")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 1116159681:
                            if (g.equals("mIsClientAuction")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 1367383194:
                            if (g.equals("mWinningBid")) {
                                c = 1;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            adResponse.mBidResponses = this.mTypeAdapter1.read(hVar);
                            break;
                        case 1:
                            adResponse.mWinningBid = this.mTypeAdapter0.read(hVar);
                            break;
                        case 2:
                            adResponse.mIsInterstitial = p.g.a(hVar, adResponse.mIsInterstitial);
                            break;
                        case 3:
                            adResponse.mIsClientAuction = p.g.a(hVar, adResponse.mIsClientAuction);
                            break;
                        case 4:
                            adResponse.mClientAuctionTime = p.j.a(hVar, adResponse.mClientAuctionTime);
                            break;
                        case 5:
                            adResponse.mAdRequest = this.mTypeAdapter2.read(hVar);
                            break;
                        case 6:
                            adResponse.mDefaultResponse = p.g.a(hVar, adResponse.mDefaultResponse);
                            break;
                        default:
                            hVar.n();
                            break;
                    }
                }
                hVar.d();
                return adResponse;
            }
        }
    }

    public AdResponse(List<BidResponse> list, AdRequest adRequest, boolean z) {
        this.mBidResponses = list;
        this.mIsClientAuction = z;
        this.mAdRequest = adRequest;
    }

    public BidResponse getWinningBid() {
        return this.mWinningBid;
    }

    public void setWinningBid(BidResponse bidResponse) {
        this.mWinningBid = bidResponse;
    }

    public void setIsInterstitial(boolean z) {
        this.mIsInterstitial = z;
    }

    public boolean isIsInterstitial() {
        return this.mIsInterstitial;
    }

    public void setBidResponses(List<BidResponse> list) {
        this.mBidResponses = list;
    }

    public List<BidResponse> getBidResponses() {
        return this.mBidResponses;
    }

    public boolean isClientAuction() {
        return this.mIsClientAuction;
    }

    public void setClientAuctionTime(int i) {
        this.mClientAuctionTime = i;
    }

    public int getClientAuctionTime() {
        return this.mClientAuctionTime;
    }

    public AdRequest getAdRequest() {
        return this.mAdRequest;
    }

    public void setIsDefaultResponse(boolean z) {
        this.mDefaultResponse = z;
    }

    public boolean isDefaultResponse() {
        return this.mDefaultResponse;
    }
}
