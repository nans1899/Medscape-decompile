package net.media.android.bidder.base.models.internal;

import android.location.Location;
import android.text.TextUtils;
import bolts.Bolts;
import com.dd.plist.ASCIIPropertyListParser;
import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mnetinternal.c;
import mnetinternal.cb;
import mnetinternal.ck;
import mnetinternal.cu;
import mnetinternal.cv;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;
import mnetinternal.s;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.common.b;
import net.media.android.bidder.base.gson.a;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.AdSize;
import net.media.android.bidder.base.models.MNetUser;
import net.media.android.bidder.base.models.internal.AdImpression;
import net.media.android.bidder.base.models.internal.Bidder;
import net.media.android.bidder.base.models.internal.DeviceInfo;
import net.media.android.bidder.base.models.internal.FireLogsInfo;
import net.media.android.bidder.base.models.internal.HostAppInfo;

public final class BidRequest {
    @c(a = "activity_name")
    protected String mActivityName;
    @c(a = "ad_cycle_id")
    protected String mAdCycleId;
    @c(a = "imp")
    protected List<AdImpression> mAdImpressions = new ArrayList();
    @c(a = "acttime")
    protected int mAuctionTime;
    @c(a = "cbidinfo")
    protected Map<String, Map<String, Integer>> mBidderCountInfo;
    @c(a = "bidders")
    protected List<Bidder> mBidders;
    @c(a = "capabilities")
    protected Map<String, Boolean> mCapabilities;
    @c(a = "device")
    protected DeviceInfo mDeviceInfo;
    @c(a = "ext")
    protected Map<String, Object> mExt = new HashMap();
    @c(a = "external_ver_code")
    protected int mExternalVerCode = 0;
    @c(a = "external_ver_name")
    protected String mExternalVerName = "";
    @c(a = "f_logs")
    protected FireLogsInfo mFireLogsInfo;
    @c(a = "gdprconsent")
    protected int mGdprConsent = b.a().i().value();
    @c(a = "gdprstring")
    protected String mGdprConsentString = b.a().g();
    @c(a = "view_height")
    protected int mHeight;
    @c(a = "app")
    protected HostAppInfo mHostAppInfo;
    @c(a = "internal_ver_code")
    protected int mInternalVerCode = 24;
    @c(a = "internal_ver_name")
    protected String mInternalVerName = Bolts.VERSION;
    @c(a = "gdpr")
    protected int mIsGdpr;
    @c(a = "launcher_activity")
    protected boolean mIsLauncherActivity;
    @c(a = "mnet_user")
    protected MNetUser mMNetUser;
    @c(a = "regs")
    protected c mRegulations;
    @c(a = "visit_id")
    protected String mVisitId;
    @c(a = "view_width")
    protected int mWidth;

    public final class TypeAdapter extends v<BidRequest> {
        public static final g<BidRequest> TYPE_TOKEN = g.b(BidRequest.class);
        private final e mGson;
        private final v<HostAppInfo> mTypeAdapter0;
        private final v<DeviceInfo> mTypeAdapter1;
        private final v<Map<String, Map<String, Integer>>> mTypeAdapter10;
        private final v<c> mTypeAdapter11;
        private final v<Object> mTypeAdapter12;
        private final v<Map<String, Object>> mTypeAdapter13;
        private final v<AdImpression> mTypeAdapter2;
        private final v<List<AdImpression>> mTypeAdapter3;
        private final v<MNetUser> mTypeAdapter4;
        private final v<Map<String, Boolean>> mTypeAdapter5 = new p.f(i.A, i.e, new p.e());
        private final v<Bidder> mTypeAdapter6;
        private final v<List<Bidder>> mTypeAdapter7;
        private final v<FireLogsInfo> mTypeAdapter8;
        private final v<Map<String, Integer>> mTypeAdapter9;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            g<Object> b = g.b(Object.class);
            this.mTypeAdapter0 = eVar.a(HostAppInfo.TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter1 = eVar.a(DeviceInfo.TypeAdapter.TYPE_TOKEN);
            v<AdImpression> a = eVar.a(AdImpression.TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter2 = a;
            this.mTypeAdapter3 = new p.d(a, new p.c());
            this.mTypeAdapter4 = eVar.a(MNetUser.TypeAdapter.TYPE_TOKEN);
            v<Bidder> a2 = eVar.a(Bidder.TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter6 = a2;
            this.mTypeAdapter7 = new p.d(a2, new p.c());
            this.mTypeAdapter8 = eVar.a(FireLogsInfo.TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter9 = new p.f(i.A, p.c, new p.e());
            this.mTypeAdapter10 = new p.f(i.A, this.mTypeAdapter9, new p.e());
            this.mTypeAdapter11 = eVar.a(Regulations$TypeAdapter.TYPE_TOKEN);
            this.mTypeAdapter12 = eVar.a(b);
            this.mTypeAdapter13 = new p.f(i.A, this.mTypeAdapter12, new p.e());
        }

        public void write(j jVar, BidRequest bidRequest) {
            if (bidRequest == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a(AdParameterKeys.SECTION_ID);
            if (bidRequest.mHostAppInfo != null) {
                this.mTypeAdapter0.write(jVar, bidRequest.mHostAppInfo);
            } else {
                jVar.f();
            }
            jVar.a("device");
            if (bidRequest.mDeviceInfo != null) {
                this.mTypeAdapter1.write(jVar, bidRequest.mDeviceInfo);
            } else {
                jVar.f();
            }
            jVar.a("imp");
            if (bidRequest.mAdImpressions != null) {
                this.mTypeAdapter3.write(jVar, bidRequest.mAdImpressions);
            } else {
                jVar.f();
            }
            jVar.a("view_height");
            jVar.a((long) bidRequest.mHeight);
            jVar.a("view_width");
            jVar.a((long) bidRequest.mWidth);
            jVar.a("visit_id");
            if (bidRequest.mVisitId != null) {
                i.A.write(jVar, bidRequest.mVisitId);
            } else {
                jVar.f();
            }
            jVar.a(Constants.HB.AD_CYCLE_ID);
            if (bidRequest.mAdCycleId != null) {
                i.A.write(jVar, bidRequest.mAdCycleId);
            } else {
                jVar.f();
            }
            jVar.a("external_ver_name");
            if (bidRequest.mExternalVerName != null) {
                i.A.write(jVar, bidRequest.mExternalVerName);
            } else {
                jVar.f();
            }
            jVar.a("external_ver_code");
            jVar.a((long) bidRequest.mExternalVerCode);
            jVar.a("internal_ver_name");
            if (bidRequest.mInternalVerName != null) {
                i.A.write(jVar, bidRequest.mInternalVerName);
            } else {
                jVar.f();
            }
            jVar.a("internal_ver_code");
            jVar.a((long) bidRequest.mInternalVerCode);
            jVar.a("mnet_user");
            if (bidRequest.mMNetUser != null) {
                this.mTypeAdapter4.write(jVar, bidRequest.mMNetUser);
            } else {
                jVar.f();
            }
            jVar.a(com.medscape.android.Constants.CAPABILITIES_INNER_CAPABILITIES_KEY);
            if (bidRequest.mCapabilities != null) {
                this.mTypeAdapter5.write(jVar, bidRequest.mCapabilities);
            } else {
                jVar.f();
            }
            jVar.a("bidders");
            if (bidRequest.mBidders != null) {
                this.mTypeAdapter7.write(jVar, bidRequest.mBidders);
            } else {
                jVar.f();
            }
            jVar.a("activity_name");
            if (bidRequest.mActivityName != null) {
                i.A.write(jVar, bidRequest.mActivityName);
            } else {
                jVar.f();
            }
            jVar.a("launcher_activity");
            jVar.a(bidRequest.mIsLauncherActivity);
            jVar.a("f_logs");
            if (bidRequest.mFireLogsInfo != null) {
                this.mTypeAdapter8.write(jVar, bidRequest.mFireLogsInfo);
            } else {
                jVar.f();
            }
            jVar.a("acttime");
            jVar.a((long) bidRequest.mAuctionTime);
            jVar.a("cbidinfo");
            if (bidRequest.mBidderCountInfo != null) {
                this.mTypeAdapter10.write(jVar, bidRequest.mBidderCountInfo);
            } else {
                jVar.f();
            }
            jVar.a("regs");
            if (bidRequest.mRegulations != null) {
                this.mTypeAdapter11.write(jVar, bidRequest.mRegulations);
            } else {
                jVar.f();
            }
            jVar.a("gdpr");
            jVar.a((long) bidRequest.mIsGdpr);
            jVar.a("gdprconsent");
            jVar.a((long) bidRequest.mGdprConsent);
            jVar.a("gdprstring");
            if (bidRequest.mGdprConsentString != null) {
                i.A.write(jVar, bidRequest.mGdprConsentString);
            } else {
                jVar.f();
            }
            jVar.a("ext");
            if (bidRequest.mExt != null) {
                this.mTypeAdapter13.write(jVar, bidRequest.mExt);
            } else {
                jVar.f();
            }
            jVar.e();
        }

        public BidRequest read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                BidRequest bidRequest = new BidRequest();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -1516078164:
                            if (g.equals("view_width")) {
                                c = 4;
                                break;
                            }
                            break;
                        case -1487597642:
                            if (g.equals(com.medscape.android.Constants.CAPABILITIES_INNER_CAPABILITIES_KEY)) {
                                c = 12;
                                break;
                            }
                            break;
                        case -1381931109:
                            if (g.equals("gdprconsent")) {
                                c = 21;
                                break;
                            }
                            break;
                        case -1335157162:
                            if (g.equals("device")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -1283731992:
                            if (g.equals("f_logs")) {
                                c = 16;
                                break;
                            }
                            break;
                        case -1196129360:
                            if (g.equals(Constants.HB.AD_CYCLE_ID)) {
                                c = 6;
                                break;
                            }
                            break;
                        case -1161481633:
                            if (g.equals("acttime")) {
                                c = 17;
                                break;
                            }
                            break;
                        case -1036332613:
                            if (g.equals("activity_name")) {
                                c = 14;
                                break;
                            }
                            break;
                        case -275132675:
                            if (g.equals("external_ver_code")) {
                                c = 8;
                                break;
                            }
                            break;
                        case -274818149:
                            if (g.equals("external_ver_name")) {
                                c = 7;
                                break;
                            }
                            break;
                        case -186777599:
                            if (g.equals("view_height")) {
                                c = 3;
                                break;
                            }
                            break;
                        case -117459713:
                            if (g.equals("bidders")) {
                                c = ASCIIPropertyListParser.WHITESPACE_CARRIAGE_RETURN;
                                break;
                            }
                            break;
                        case 96801:
                            if (g.equals(AdParameterKeys.SECTION_ID)) {
                                c = 0;
                                break;
                            }
                            break;
                        case 100897:
                            if (g.equals("ext")) {
                                c = 23;
                                break;
                            }
                            break;
                        case 104396:
                            if (g.equals("imp")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 2573424:
                            if (g.equals("gdprstring")) {
                                c = 22;
                                break;
                            }
                            break;
                        case 3168159:
                            if (g.equals("gdpr")) {
                                c = 20;
                                break;
                            }
                            break;
                        case 3496543:
                            if (g.equals("regs")) {
                                c = 19;
                                break;
                            }
                            break;
                        case 620341454:
                            if (g.equals("launcher_activity")) {
                                c = 15;
                                break;
                            }
                            break;
                        case 622228840:
                            if (g.equals("cbidinfo")) {
                                c = 18;
                                break;
                            }
                            break;
                        case 1584667791:
                            if (g.equals("visit_id")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 1653513291:
                            if (g.equals("internal_ver_code")) {
                                c = 10;
                                break;
                            }
                            break;
                        case 1653827817:
                            if (g.equals("internal_ver_name")) {
                                c = 9;
                                break;
                            }
                            break;
                        case 1847441562:
                            if (g.equals("mnet_user")) {
                                c = 11;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            bidRequest.mHostAppInfo = this.mTypeAdapter0.read(hVar);
                            break;
                        case 1:
                            bidRequest.mDeviceInfo = this.mTypeAdapter1.read(hVar);
                            break;
                        case 2:
                            bidRequest.mAdImpressions = this.mTypeAdapter3.read(hVar);
                            break;
                        case 3:
                            bidRequest.mHeight = p.j.a(hVar, bidRequest.mHeight);
                            break;
                        case 4:
                            bidRequest.mWidth = p.j.a(hVar, bidRequest.mWidth);
                            break;
                        case 5:
                            bidRequest.mVisitId = i.A.read(hVar);
                            break;
                        case 6:
                            bidRequest.mAdCycleId = i.A.read(hVar);
                            break;
                        case 7:
                            bidRequest.mExternalVerName = i.A.read(hVar);
                            break;
                        case 8:
                            bidRequest.mExternalVerCode = p.j.a(hVar, bidRequest.mExternalVerCode);
                            break;
                        case 9:
                            bidRequest.mInternalVerName = i.A.read(hVar);
                            break;
                        case 10:
                            bidRequest.mInternalVerCode = p.j.a(hVar, bidRequest.mInternalVerCode);
                            break;
                        case 11:
                            bidRequest.mMNetUser = this.mTypeAdapter4.read(hVar);
                            break;
                        case 12:
                            bidRequest.mCapabilities = this.mTypeAdapter5.read(hVar);
                            break;
                        case 13:
                            bidRequest.mBidders = this.mTypeAdapter7.read(hVar);
                            break;
                        case 14:
                            bidRequest.mActivityName = i.A.read(hVar);
                            break;
                        case 15:
                            bidRequest.mIsLauncherActivity = p.g.a(hVar, bidRequest.mIsLauncherActivity);
                            break;
                        case 16:
                            bidRequest.mFireLogsInfo = this.mTypeAdapter8.read(hVar);
                            break;
                        case 17:
                            bidRequest.mAuctionTime = p.j.a(hVar, bidRequest.mAuctionTime);
                            break;
                        case 18:
                            bidRequest.mBidderCountInfo = this.mTypeAdapter10.read(hVar);
                            break;
                        case 19:
                            bidRequest.mRegulations = this.mTypeAdapter11.read(hVar);
                            break;
                        case 20:
                            bidRequest.mIsGdpr = p.j.a(hVar, bidRequest.mIsGdpr);
                            break;
                        case 21:
                            bidRequest.mGdprConsent = p.j.a(hVar, bidRequest.mGdprConsent);
                            break;
                        case 22:
                            bidRequest.mGdprConsentString = i.A.read(hVar);
                            break;
                        case 23:
                            bidRequest.mExt = this.mTypeAdapter13.read(hVar);
                            break;
                        default:
                            hVar.n();
                            break;
                    }
                }
                hVar.d();
                return bidRequest;
            }
        }
    }

    protected static BidRequest setCommonValues(BidRequest bidRequest, HostAppContext hostAppContext, AdRequest adRequest) {
        bidRequest.setMNetUser(MNet.b());
        bidRequest.setVisitId(cb.a().a("__mn__visit_id"));
        bidRequest.setCapabilities(cu.a().b());
        bidRequest.setRegulations(new c(b.a().f()));
        bidRequest.setIsGdpr(b.a().c().value());
        bidRequest.setHostAppInfo(new HostAppInfo.Builder().setPackageName(ck.a().e()).setAppVersionCode(ck.a().c()).setAppVersionName(ck.a().d()).setIsForeground(cv.b(MNet.getContext())).setPublisher(new Publisher(MNet.getCustomerId())).setHostAppContext(hostAppContext).build());
        if (adRequest != null) {
            bidRequest.mExt.put("source_fd", Integer.valueOf(adRequest.isInternal() ^ true ? 1 : 0));
        }
        return bidRequest;
    }

    public static BidRequest createForAdRequest(AdRequest adRequest, List<BidResponse> list) {
        BidRequest bidRequest = new BidRequest();
        bidRequest.setAdCycleId(adRequest.getAdCycleId());
        bidRequest.addImpressions(new AdImpression.Builder().setAdUnitId(adRequest.getAdUnitId()).setIsInterstitial(adRequest.isInterstitial() ? 1 : 0).setAdSource(new AdSource(adRequest.isInternal())).setImpressionType(adRequest.getAdSizes()).build());
        HostAppContext hostAppContext = adRequest.getHostAppContext();
        if (hostAppContext != null) {
            hostAppContext.setKeywords(adRequest.getKeywords());
        }
        boolean isChildDirectedContent = adRequest.isChildDirectedContent();
        if (b.a().e()) {
            isChildDirectedContent = true;
        }
        bidRequest.setDeviceInfo(ck.a().a(isChildDirectedContent, adRequest.getLocation()));
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BidResponse createNew : list) {
                arrayList.add(Bidder.createNew(createNew));
            }
        }
        bidRequest.setBidders(arrayList);
        return setCommonValues(bidRequest, hostAppContext, adRequest);
    }

    public static BidRequest createForFireLogs(AdRequest adRequest, BidResponse bidResponse, List<BidResponse> list, FireLogsInfo fireLogsInfo, int i) {
        BidRequest bidRequest = new BidRequest();
        bidRequest.setAdCycleId(adRequest.getAdCycleId());
        boolean z = true;
        if (bidResponse == null) {
            for (BidResponse next : list) {
                bidRequest.addImpressions(new AdImpression.Builder().setAdUnitId(adRequest.getAdUnitId()).setImpressionType(new AdSize(next.getWidth(), next.getHeight())).setAdSource(new AdSource(adRequest.isInternal())).build());
            }
        } else {
            bidRequest.addImpressions(new AdImpression.Builder().setAdUnitId(adRequest.getAdUnitId()).setImpressionType(new AdSize(bidResponse.getWidth(), bidResponse.getHeight())).setAdSource(new AdSource(adRequest.isInternal())).build());
        }
        boolean isChildDirectedContent = adRequest.isChildDirectedContent();
        if (!b.a().e()) {
            z = isChildDirectedContent;
        }
        bidRequest.setDeviceInfo(ck.a().a(z, adRequest.getLocation()));
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BidResponse createNew : list) {
                arrayList.add(Bidder.createNew(createNew));
            }
        }
        bidRequest.setBidders(arrayList);
        bidRequest.setFireLogsInfo(fireLogsInfo);
        bidRequest.setAuctionTime(i);
        return setCommonValues(bidRequest, adRequest.getHostAppContext(), adRequest);
    }

    public static BidRequest createForBidPrefetch(String str, String str2, HostAppContext hostAppContext) {
        BidRequest bidRequest = new BidRequest();
        bidRequest.addImpressions(new AdImpression.Builder().setAdSource(new AdSource(false)).setAdUnitId(str2).build());
        bidRequest.setDeviceInfo(ck.a().a(b.a().e(), (Location) null));
        bidRequest.setActivityName(str);
        bidRequest.setIsLauncherActivity(MNet.a(str));
        if (TextUtils.isEmpty(str2)) {
            bidRequest.setBidderCountInfo(s.a().a());
        } else {
            bidRequest.setBidderCountInfo(Collections.singletonMap(str2, s.a().b(str2)));
        }
        return setCommonValues(bidRequest, hostAppContext, (AdRequest) null);
    }

    protected BidRequest() {
    }

    /* access modifiers changed from: protected */
    public void setHostAppInfo(HostAppInfo hostAppInfo) {
        this.mHostAppInfo = hostAppInfo;
    }

    /* access modifiers changed from: protected */
    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.mDeviceInfo = deviceInfo;
    }

    /* access modifiers changed from: protected */
    public void addImpressions(AdImpression adImpression) {
        this.mAdImpressions.add(adImpression);
    }

    /* access modifiers changed from: protected */
    public void setAdCycleId(String str) {
        this.mAdCycleId = str;
    }

    /* access modifiers changed from: protected */
    public void setVisitId(String str) {
        this.mVisitId = str;
    }

    /* access modifiers changed from: protected */
    public void setWidth(int i) {
        this.mWidth = i;
    }

    /* access modifiers changed from: protected */
    public void setHeight(int i) {
        this.mHeight = i;
    }

    /* access modifiers changed from: protected */
    public void setIsGdpr(int i) {
        this.mIsGdpr = i;
    }

    /* access modifiers changed from: protected */
    public void setMNetUser(MNetUser mNetUser) {
        this.mMNetUser = mNetUser;
    }

    /* access modifiers changed from: protected */
    public void setCapabilities(Map<String, Boolean> map) {
        this.mCapabilities = map;
    }

    /* access modifiers changed from: protected */
    public void setBidders(List<Bidder> list) {
        this.mBidders = list;
    }

    /* access modifiers changed from: protected */
    public void setActivityName(String str) {
        this.mActivityName = str;
    }

    /* access modifiers changed from: protected */
    public void setIsLauncherActivity(boolean z) {
        this.mIsLauncherActivity = z;
    }

    /* access modifiers changed from: protected */
    public void setFireLogsInfo(FireLogsInfo fireLogsInfo) {
        this.mFireLogsInfo = fireLogsInfo;
    }

    /* access modifiers changed from: protected */
    public void setAuctionTime(int i) {
        this.mAuctionTime = i;
    }

    /* access modifiers changed from: protected */
    public void setBidderCountInfo(Map<String, Map<String, Integer>> map) {
        this.mBidderCountInfo = map;
    }

    /* access modifiers changed from: protected */
    public void setRegulations(c cVar) {
        this.mRegulations = cVar;
    }

    public String toJson() {
        return a.b().b((Object) this);
    }
}
