package net.media.android.bidder.base.models.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.dd.plist.ASCIIPropertyListParser;
import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mnetinternal.af;
import mnetinternal.ai;
import mnetinternal.ak;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.macro.a;
import net.media.android.bidder.base.models.AdSize;

public final class BidResponse implements Parcelable {
    private static final String ADX = "adxd";
    public static final Parcelable.Creator<BidResponse> CREATOR = new Parcelable.Creator<BidResponse>() {
        /* renamed from: a */
        public BidResponse createFromParcel(Parcel parcel) {
            return new BidResponse(parcel);
        }

        /* renamed from: a */
        public BidResponse[] newArray(int i) {
            return new BidResponse[i];
        }
    };
    private static final String FPD = "fpd";
    private static final String TPD = "tpd";
    @c(a = "adcode")
    protected String mAdCode;
    protected transient String mAdCycleId;
    @c(a = "adtype")
    protected String mAdType;
    @c(a = "adurl")
    protected String mAdUrl;
    @c(a = "a_bid")
    protected double mAuctionBid;
    @c(a = "bid")
    protected double mBid;
    @c(a = "bid_info")
    protected Map<String, Object> mBidInfo;
    @c(a = "m_bid")
    protected double mBidMultiplier;
    @c(a = "bid_type")
    protected String mBidType;
    @c(a = "bidder_id")
    protected String mBidderId;
    @c(a = "bidder_name")
    protected String mBidderName;
    @c(a = "cbdp")
    protected double mCbdp;
    @c(a = "clsprc")
    protected double mClosingPrice;
    @c(a = "creative_id")
    protected String mCreativeId;
    @c(a = "creative_type")
    protected String mCreativeType;
    @c(a = "dfpbd")
    protected double mDfpBid;
    @c(a = "dfpbdm1")
    protected double mDfpBidMultiplierOne;
    @c(a = "dfpbdm2")
    protected double mDfpBidMultiplierTwo;
    @c(a = "expiry")
    protected long mExpiry;
    @c(a = "elogs")
    protected List<String> mExpiryLogs;
    @c(a = "ext")
    protected HashMap<String, Object> mExt;
    @c(a = "h")
    protected int mHeight;
    @c(a = "id")
    protected String mId;
    protected boolean mIsCached;
    protected boolean mIsWinner;
    protected transient String mKeywords;
    @c(a = "logging_pixels")
    protected List<String> mLoggingBeacons;
    @c(a = "obidm1")
    protected double mOgBidMultiplierOne;
    @c(a = "obidm2")
    protected double mOgBidMultiplierTwo;
    @c(a = "og_bid")
    protected double mOriginalBid;
    protected transient String mPredictionId;
    @c(a = "publisher_id")
    protected String mPublisherId;
    @c(a = "server_extras")
    protected Map<String, Object> mServerExtras;
    @c(a = "size")
    protected String mSize;
    @c(a = "skippable")
    protected boolean mSkippable;
    @c(a = "tp")
    protected String mTrackingPixel;
    @c(a = "variant")
    protected int mVariant;
    protected transient String mViewContextLink;
    @c(a = "w")
    protected int mWidth;
    @c(a = "preload")
    protected boolean preload;

    public interface AdCodeCallback {
        void failed();

        void success(String str);
    }

    public int describeContents() {
        return 0;
    }

    public final class TypeAdapter extends v<BidResponse> {
        public static final g<BidResponse> TYPE_TOKEN = g.b(BidResponse.class);
        private final e mGson;
        private final v<List<String>> mTypeAdapter0 = new p.d(i.A, new p.c());
        private final v<Object> mTypeAdapter1;
        private final v<Map<String, Object>> mTypeAdapter2;
        private final v<HashMap<String, Object>> mTypeAdapter3;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            g<Object> b = g.b(Object.class);
            this.mTypeAdapter1 = eVar.a(b);
            this.mTypeAdapter2 = new p.f(i.A, this.mTypeAdapter1, new p.e());
            this.mTypeAdapter3 = new p.f(i.A, this.mTypeAdapter1, new p.b());
        }

        public void write(j jVar, BidResponse bidResponse) {
            if (bidResponse == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("id");
            if (bidResponse.mId != null) {
                i.A.write(jVar, bidResponse.mId);
            } else {
                jVar.f();
            }
            jVar.a("bidder_id");
            if (bidResponse.mBidderId != null) {
                i.A.write(jVar, bidResponse.mBidderId);
            } else {
                jVar.f();
            }
            jVar.a("bidder_name");
            if (bidResponse.mBidderName != null) {
                i.A.write(jVar, bidResponse.mBidderName);
            } else {
                jVar.f();
            }
            jVar.a("creative_id");
            if (bidResponse.mCreativeId != null) {
                i.A.write(jVar, bidResponse.mCreativeId);
            } else {
                jVar.f();
            }
            jVar.a("adtype");
            if (bidResponse.mAdType != null) {
                i.A.write(jVar, bidResponse.mAdType);
            } else {
                jVar.f();
            }
            jVar.a("creative_type");
            if (bidResponse.mCreativeType != null) {
                i.A.write(jVar, bidResponse.mCreativeType);
            } else {
                jVar.f();
            }
            jVar.a("adurl");
            if (bidResponse.mAdUrl != null) {
                i.A.write(jVar, bidResponse.mAdUrl);
            } else {
                jVar.f();
            }
            jVar.a("adcode");
            if (bidResponse.mAdCode != null) {
                i.A.write(jVar, bidResponse.mAdCode);
            } else {
                jVar.f();
            }
            jVar.a(Constants.PUBLISHER_ID);
            if (bidResponse.mPublisherId != null) {
                i.A.write(jVar, bidResponse.mPublisherId);
            } else {
                jVar.f();
            }
            jVar.a("variant");
            jVar.a((long) bidResponse.mVariant);
            jVar.a("tp");
            if (bidResponse.mTrackingPixel != null) {
                i.A.write(jVar, bidResponse.mTrackingPixel);
            } else {
                jVar.f();
            }
            jVar.a("size");
            if (bidResponse.mSize != null) {
                i.A.write(jVar, bidResponse.mSize);
            } else {
                jVar.f();
            }
            jVar.a("h");
            jVar.a((long) bidResponse.mHeight);
            jVar.a("w");
            jVar.a((long) bidResponse.mWidth);
            jVar.a("logging_pixels");
            if (bidResponse.mLoggingBeacons != null) {
                this.mTypeAdapter0.write(jVar, bidResponse.mLoggingBeacons);
            } else {
                jVar.f();
            }
            jVar.a("elogs");
            if (bidResponse.mExpiryLogs != null) {
                this.mTypeAdapter0.write(jVar, bidResponse.mExpiryLogs);
            } else {
                jVar.f();
            }
            jVar.a("server_extras");
            if (bidResponse.mServerExtras != null) {
                this.mTypeAdapter2.write(jVar, bidResponse.mServerExtras);
            } else {
                jVar.f();
            }
            jVar.a("skippable");
            jVar.a(bidResponse.mSkippable);
            jVar.a("bid_type");
            if (bidResponse.mBidType != null) {
                i.A.write(jVar, bidResponse.mBidType);
            } else {
                jVar.f();
            }
            jVar.a("ext");
            if (bidResponse.mExt != null) {
                this.mTypeAdapter3.write(jVar, bidResponse.mExt);
            } else {
                jVar.f();
            }
            jVar.a("bid");
            jVar.a(bidResponse.mBid);
            jVar.a("og_bid");
            jVar.a(bidResponse.mOriginalBid);
            jVar.a("cbdp");
            jVar.a(bidResponse.mCbdp);
            jVar.a("clsprc");
            jVar.a(bidResponse.mClosingPrice);
            jVar.a("expiry");
            jVar.a(bidResponse.mExpiry);
            jVar.a("bid_info");
            if (bidResponse.mBidInfo != null) {
                this.mTypeAdapter2.write(jVar, bidResponse.mBidInfo);
            } else {
                jVar.f();
            }
            jVar.a("dfpbd");
            jVar.a(bidResponse.mDfpBid);
            jVar.a("a_bid");
            jVar.a(bidResponse.mAuctionBid);
            jVar.a("obidm1");
            jVar.a(bidResponse.mOgBidMultiplierOne);
            jVar.a("obidm2");
            jVar.a(bidResponse.mOgBidMultiplierTwo);
            jVar.a("m_bid");
            jVar.a(bidResponse.mBidMultiplier);
            jVar.a("dfpbdm1");
            jVar.a(bidResponse.mDfpBidMultiplierOne);
            jVar.a("dfpbdm2");
            jVar.a(bidResponse.mDfpBidMultiplierTwo);
            jVar.a("preload");
            jVar.a(bidResponse.preload);
            jVar.a("mIsCached");
            jVar.a(bidResponse.mIsCached);
            jVar.a("mIsWinner");
            jVar.a(bidResponse.mIsWinner);
            jVar.e();
        }

        public BidResponse read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                BidResponse bidResponse = new BidResponse();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -1858159042:
                            if (g.equals(Constants.PUBLISHER_ID)) {
                                c = 8;
                                break;
                            }
                            break;
                        case -1422528368:
                            if (g.equals("adcode")) {
                                c = 7;
                                break;
                            }
                            break;
                        case -1422011939:
                            if (g.equals("adtype")) {
                                c = 4;
                                break;
                            }
                            break;
                        case -1357403849:
                            if (g.equals("clsprc")) {
                                c = 23;
                                break;
                            }
                            break;
                        case -1289159373:
                            if (g.equals("expiry")) {
                                c = 24;
                                break;
                            }
                            break;
                        case -1209650362:
                            if (g.equals("bidder_id")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -1023398894:
                            if (g.equals("obidm1")) {
                                c = 28;
                                break;
                            }
                            break;
                        case -1023398893:
                            if (g.equals("obidm2")) {
                                c = 29;
                                break;
                            }
                            break;
                        case -1019081194:
                            if (g.equals("og_bid")) {
                                c = 21;
                                break;
                            }
                            break;
                        case -829622387:
                            if (g.equals("logging_pixels")) {
                                c = 14;
                                break;
                            }
                            break;
                        case -702722614:
                            if (g.equals("creative_type")) {
                                c = 5;
                                break;
                            }
                            break;
                        case -358273109:
                            if (g.equals("creative_id")) {
                                c = 3;
                                break;
                            }
                            break;
                        case -318476791:
                            if (g.equals("preload")) {
                                c = '!';
                                break;
                            }
                            break;
                        case -73409927:
                            if (g.equals("mIsCached")) {
                                c = '\"';
                                break;
                            }
                            break;
                        case 104:
                            if (g.equals("h")) {
                                c = 12;
                                break;
                            }
                            break;
                        case 119:
                            if (g.equals("w")) {
                                c = ASCIIPropertyListParser.WHITESPACE_CARRIAGE_RETURN;
                                break;
                            }
                            break;
                        case 3355:
                            if (g.equals("id")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 3708:
                            if (g.equals("tp")) {
                                c = 10;
                                break;
                            }
                            break;
                        case 97533:
                            if (g.equals("bid")) {
                                c = 20;
                                break;
                            }
                            break;
                        case 100897:
                            if (g.equals("ext")) {
                                c = 19;
                                break;
                            }
                            break;
                        case 3046699:
                            if (g.equals("cbdp")) {
                                c = 22;
                                break;
                            }
                            break;
                        case 3530753:
                            if (g.equals("size")) {
                                c = 11;
                                break;
                            }
                            break;
                        case 92509215:
                            if (g.equals("a_bid")) {
                                c = 27;
                                break;
                            }
                            break;
                        case 92676716:
                            if (g.equals("adurl")) {
                                c = 6;
                                break;
                            }
                            break;
                        case 95501552:
                            if (g.equals("dfpbd")) {
                                c = 26;
                                break;
                            }
                            break;
                        case 96603028:
                            if (g.equals("elogs")) {
                                c = 15;
                                break;
                            }
                            break;
                        case 103591467:
                            if (g.equals("m_bid")) {
                                c = 30;
                                break;
                            }
                            break;
                        case 236785797:
                            if (g.equals("variant")) {
                                c = 9;
                                break;
                            }
                            break;
                        case 506894742:
                            if (g.equals("mIsWinner")) {
                                c = '#';
                                break;
                            }
                            break;
                        case 649213616:
                            if (g.equals("bid_info")) {
                                c = 25;
                                break;
                            }
                            break;
                        case 649552188:
                            if (g.equals("bid_type")) {
                                c = 18;
                                break;
                            }
                            break;
                        case 730110687:
                            if (g.equals("server_extras")) {
                                c = 16;
                                break;
                            }
                            break;
                        case 1462288886:
                            if (g.equals("bidder_name")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 1582681684:
                            if (g.equals("dfpbdm1")) {
                                c = 31;
                                break;
                            }
                            break;
                        case 1582681685:
                            if (g.equals("dfpbdm2")) {
                                c = ' ';
                                break;
                            }
                            break;
                        case 2109771691:
                            if (g.equals("skippable")) {
                                c = 17;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            bidResponse.mId = i.A.read(hVar);
                            break;
                        case 1:
                            bidResponse.mBidderId = i.A.read(hVar);
                            break;
                        case 2:
                            bidResponse.mBidderName = i.A.read(hVar);
                            break;
                        case 3:
                            bidResponse.mCreativeId = i.A.read(hVar);
                            break;
                        case 4:
                            bidResponse.mAdType = i.A.read(hVar);
                            break;
                        case 5:
                            bidResponse.mCreativeType = i.A.read(hVar);
                            break;
                        case 6:
                            bidResponse.mAdUrl = i.A.read(hVar);
                            break;
                        case 7:
                            bidResponse.mAdCode = i.A.read(hVar);
                            break;
                        case 8:
                            bidResponse.mPublisherId = i.A.read(hVar);
                            break;
                        case 9:
                            bidResponse.mVariant = p.j.a(hVar, bidResponse.mVariant);
                            break;
                        case 10:
                            bidResponse.mTrackingPixel = i.A.read(hVar);
                            break;
                        case 11:
                            bidResponse.mSize = i.A.read(hVar);
                            break;
                        case 12:
                            bidResponse.mHeight = p.j.a(hVar, bidResponse.mHeight);
                            break;
                        case 13:
                            bidResponse.mWidth = p.j.a(hVar, bidResponse.mWidth);
                            break;
                        case 14:
                            bidResponse.mLoggingBeacons = this.mTypeAdapter0.read(hVar);
                            break;
                        case 15:
                            bidResponse.mExpiryLogs = this.mTypeAdapter0.read(hVar);
                            break;
                        case 16:
                            bidResponse.mServerExtras = this.mTypeAdapter2.read(hVar);
                            break;
                        case 17:
                            bidResponse.mSkippable = p.g.a(hVar, bidResponse.mSkippable);
                            break;
                        case 18:
                            bidResponse.mBidType = i.A.read(hVar);
                            break;
                        case 19:
                            bidResponse.mExt = this.mTypeAdapter3.read(hVar);
                            break;
                        case 20:
                            bidResponse.mBid = p.h.a(hVar, bidResponse.mBid);
                            break;
                        case 21:
                            bidResponse.mOriginalBid = p.h.a(hVar, bidResponse.mOriginalBid);
                            break;
                        case 22:
                            bidResponse.mCbdp = p.h.a(hVar, bidResponse.mCbdp);
                            break;
                        case 23:
                            bidResponse.mClosingPrice = p.h.a(hVar, bidResponse.mClosingPrice);
                            break;
                        case 24:
                            bidResponse.mExpiry = p.k.a(hVar, bidResponse.mExpiry);
                            break;
                        case 25:
                            bidResponse.mBidInfo = this.mTypeAdapter2.read(hVar);
                            break;
                        case 26:
                            bidResponse.mDfpBid = p.h.a(hVar, bidResponse.mDfpBid);
                            break;
                        case 27:
                            bidResponse.mAuctionBid = p.h.a(hVar, bidResponse.mAuctionBid);
                            break;
                        case 28:
                            bidResponse.mOgBidMultiplierOne = p.h.a(hVar, bidResponse.mOgBidMultiplierOne);
                            break;
                        case 29:
                            bidResponse.mOgBidMultiplierTwo = p.h.a(hVar, bidResponse.mOgBidMultiplierTwo);
                            break;
                        case 30:
                            bidResponse.mBidMultiplier = p.h.a(hVar, bidResponse.mBidMultiplier);
                            break;
                        case 31:
                            bidResponse.mDfpBidMultiplierOne = p.h.a(hVar, bidResponse.mDfpBidMultiplierOne);
                            break;
                        case ' ':
                            bidResponse.mDfpBidMultiplierTwo = p.h.a(hVar, bidResponse.mDfpBidMultiplierTwo);
                            break;
                        case '!':
                            bidResponse.preload = p.g.a(hVar, bidResponse.preload);
                            break;
                        case '\"':
                            bidResponse.mIsCached = p.g.a(hVar, bidResponse.mIsCached);
                            break;
                        case '#':
                            bidResponse.mIsWinner = p.g.a(hVar, bidResponse.mIsWinner);
                            break;
                        default:
                            hVar.n();
                            break;
                    }
                }
                hVar.d();
                return bidResponse;
            }
        }
    }

    protected BidResponse() {
        this.mAdCode = "";
        this.preload = true;
    }

    public String getCreativeId() {
        return this.mCreativeId;
    }

    public void setCreativeId(String str) {
        this.mCreativeId = str;
    }

    public String getBidderId() {
        return this.mBidderId;
    }

    public String getBidderName() {
        return this.mBidderName;
    }

    private void setBidderId(String str) {
        this.mBidderId = str;
    }

    public String getAdType() {
        return this.mAdType;
    }

    public String getCreativeType() {
        return this.mCreativeType;
    }

    private void setAdType(String str) {
        this.mAdType = str;
    }

    public String getAdUrl() {
        return this.mAdUrl;
    }

    public void getAdCode(final AdCodeCallback adCodeCallback) {
        if (!TextUtils.isEmpty(this.mAdCode)) {
            adCodeCallback.success(a.a(getRawAdCode(), this));
        } else if (TextUtils.isEmpty(this.mAdUrl)) {
            adCodeCallback.failed();
        } else {
            af.a(new ai.a(this.mAdUrl).a(), new ak<String>() {
                public Class<String> a() {
                    return String.class;
                }

                public void a(String str) {
                    BidResponse.this.mAdCode = str;
                    adCodeCallback.success(a.a(str, BidResponse.this));
                }

                public void a(Throwable th) {
                    adCodeCallback.failed();
                }
            });
        }
    }

    public String getRawAdCode() {
        if (this.mAdCode == null) {
            this.mAdCode = "";
        }
        return this.mAdCode;
    }

    public String getPublisherId() {
        return this.mPublisherId;
    }

    private void setPublisherId(String str) {
        this.mPublisherId = str;
    }

    public int getVariant() {
        return this.mVariant;
    }

    private void setVariant(int i) {
        this.mVariant = i;
    }

    public String getTrackingPixel() {
        return this.mTrackingPixel;
    }

    private void setTRackingPixel(String str) {
        this.mTrackingPixel = str;
    }

    public String getSize() {
        return this.mSize;
    }

    public AdSize getAdSize() {
        return new AdSize(this.mWidth, this.mHeight);
    }

    private void setSize(String str) {
        this.mSize = str;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    private void setHeight(int i) {
        this.mHeight = i;
    }

    private void setWidth(int i) {
        this.mWidth = i;
    }

    public void setServerExtras(Map<String, Object> map) {
        this.mServerExtras = map;
    }

    public Map<String, Object> getServerExtras() {
        return this.mServerExtras;
    }

    public String getContextLink() {
        return this.mViewContextLink;
    }

    public void setContextLink(String str) {
        this.mViewContextLink = str;
    }

    public List<String> getLoggingBeacons() {
        return this.mLoggingBeacons;
    }

    public List<String> getExpiryLogs() {
        return this.mExpiryLogs;
    }

    private void setLoggingBeacons(ArrayList<String> arrayList) {
        this.mLoggingBeacons = arrayList;
    }

    public boolean isSkippable() {
        return this.mSkippable;
    }

    public String getBidType() {
        return this.mBidType;
    }

    public Map<String, Object> getExt() {
        return this.mExt;
    }

    public String getAdCycleId() {
        return this.mAdCycleId;
    }

    public String getKeywords() {
        return this.mKeywords;
    }

    public double getBid() {
        return this.mBid;
    }

    public double getOriginalBid() {
        return this.mOriginalBid;
    }

    public void setOriginalBid(double d) {
        this.mOriginalBid = d;
    }

    public double getCbdp() {
        return this.mCbdp;
    }

    public double getClosingPrice() {
        return this.mClosingPrice;
    }

    public long getExpiry() {
        return this.mExpiry;
    }

    public void appendExt(Map<String, Object> map) {
        if (map != null) {
            if (this.mExt == null) {
                this.mExt = new HashMap<>();
            }
            this.mExt.putAll(map);
        }
    }

    public Map<String, Object> getBidInfo() {
        return this.mBidInfo;
    }

    public void setAdCycleId(String str) {
        this.mAdCycleId = str;
    }

    public void setKeywords(String str) {
        this.mKeywords = str;
    }

    public boolean isFirstPartyBid() {
        return FPD.equals(this.mBidType);
    }

    public boolean isThirdPartyBid() {
        return TPD.equals(this.mBidType);
    }

    public boolean isAdx() {
        return ADX.equals(this.mBidType);
    }

    public boolean isYbnca() {
        return Constants.YBNCA_BIDDER_ID.equalsIgnoreCase(this.mBidderId);
    }

    public double getDfpBid() {
        return this.mDfpBid;
    }

    public void setDfpBid(double d) {
        this.mDfpBid = d;
    }

    public boolean isCached() {
        return this.mIsCached;
    }

    public void setCached(boolean z) {
        this.mIsCached = z;
    }

    public boolean isWinner() {
        return this.mIsWinner;
    }

    public void setIsWinner(boolean z) {
        this.mIsWinner = z;
    }

    public double getAuctionBid() {
        return this.mAuctionBid;
    }

    public double getOgBidMultiplierOne() {
        return this.mOgBidMultiplierOne;
    }

    public double getOgBidMultiplierTwo() {
        return this.mOgBidMultiplierTwo;
    }

    public double getBidMultiplier() {
        return this.mBidMultiplier;
    }

    public double getDfpBidMultiplierOne() {
        return this.mDfpBidMultiplierOne;
    }

    public double getDfpBidMultiplierTwo() {
        return this.mDfpBidMultiplierTwo;
    }

    public String getPredictionId() {
        return this.mPredictionId;
    }

    public void setPredictionId(String str) {
        this.mPredictionId = str;
    }

    public boolean canPreload() {
        return this.preload;
    }

    protected BidResponse(Parcel parcel) {
        this.mAdCode = "";
        boolean z = true;
        this.preload = true;
        this.mId = parcel.readString();
        this.mAdCycleId = parcel.readString();
        this.mBidderId = parcel.readString();
        this.mAdType = parcel.readString();
        this.mAdUrl = parcel.readString();
        this.mAdCode = parcel.readString();
        this.mPublisherId = parcel.readString();
        this.mVariant = parcel.readInt();
        this.mTrackingPixel = parcel.readString();
        this.mSize = parcel.readString();
        this.mLoggingBeacons = parcel.createStringArrayList();
        this.mCreativeId = parcel.readString();
        this.mServerExtras = parcel.readHashMap(getClass().getClassLoader());
        this.mHeight = parcel.readInt();
        this.mWidth = parcel.readInt();
        this.mCreativeType = parcel.readString();
        this.mBidType = parcel.readString();
        this.mBid = parcel.readDouble();
        this.mSkippable = parcel.readByte() != 0;
        this.mIsCached = parcel.readByte() != 0;
        this.mExpiry = parcel.readLong();
        this.mDfpBid = parcel.readDouble();
        this.mBidInfo = parcel.readHashMap(getClass().getClassLoader());
        this.mExpiryLogs = parcel.createStringArrayList();
        this.mBidderName = parcel.readString();
        this.mCbdp = parcel.readDouble();
        this.mClosingPrice = parcel.readDouble();
        this.mOriginalBid = parcel.readDouble();
        this.mAuctionBid = parcel.readDouble();
        this.mOgBidMultiplierOne = parcel.readDouble();
        this.mOgBidMultiplierTwo = parcel.readDouble();
        this.mBidMultiplier = parcel.readDouble();
        this.mDfpBidMultiplierOne = parcel.readDouble();
        this.mDfpBidMultiplierTwo = parcel.readDouble();
        this.mIsWinner = parcel.readByte() == 0 ? false : z;
        this.mPredictionId = parcel.readString();
        this.mKeywords = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mAdCycleId);
        parcel.writeString(this.mBidderId);
        parcel.writeString(this.mAdType);
        parcel.writeString(this.mAdUrl);
        parcel.writeString(this.mAdCode);
        parcel.writeString(this.mPublisherId);
        parcel.writeInt(this.mVariant);
        parcel.writeString(this.mTrackingPixel);
        parcel.writeString(this.mSize);
        parcel.writeStringList(this.mLoggingBeacons);
        parcel.writeString(this.mCreativeId);
        parcel.writeMap(this.mServerExtras);
        parcel.writeInt(this.mHeight);
        parcel.writeInt(this.mWidth);
        parcel.writeString(this.mCreativeType);
        parcel.writeString(this.mBidType);
        parcel.writeDouble(this.mBid);
        parcel.writeByte(this.mSkippable ? (byte) 1 : 0);
        parcel.writeByte(this.mIsCached ? (byte) 1 : 0);
        parcel.writeLong(this.mExpiry);
        parcel.writeDouble(this.mDfpBid);
        parcel.writeMap(this.mBidInfo);
        parcel.writeStringList(this.mExpiryLogs);
        parcel.writeString(this.mBidderName);
        parcel.writeDouble(this.mCbdp);
        parcel.writeDouble(this.mClosingPrice);
        parcel.writeDouble(this.mOriginalBid);
        parcel.writeDouble(this.mAuctionBid);
        parcel.writeDouble(this.mOgBidMultiplierOne);
        parcel.writeDouble(this.mOgBidMultiplierTwo);
        parcel.writeDouble(this.mBidMultiplier);
        parcel.writeDouble(this.mDfpBidMultiplierOne);
        parcel.writeDouble(this.mDfpBidMultiplierTwo);
        parcel.writeByte(this.mIsWinner ? (byte) 1 : 0);
        parcel.writeString(this.mPredictionId);
        parcel.writeString(this.mKeywords);
    }
}
