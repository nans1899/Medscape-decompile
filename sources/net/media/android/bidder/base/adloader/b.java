package net.media.android.bidder.base.adloader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import mnetinternal.aa;
import mnetinternal.ac;
import mnetinternal.cs;
import mnetinternal.da;
import mnetinternal.q;
import mnetinternal.s;
import net.media.android.bidder.base.common.a;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.a;
import net.media.android.bidder.base.models.internal.AdResponse;
import net.media.android.bidder.base.models.internal.AdUnit;
import net.media.android.bidder.base.models.internal.BidResponse;

final class b {
    private List<cs> a;

    b(List<cs> list) {
        this.a = list;
    }

    public AdResponse a(AdRequest adRequest, List<BidResponse> list, long j) {
        if (adRequest == null) {
            return null;
        }
        final AdRequest build = new AdRequest.Builder().setAdUnitId(adRequest.getAdUnitId()).setInternal(adRequest.isInternal()).setInterstitial(adRequest.isInterstitial()).setAdSizes(adRequest.getAdSizes()).setHostAppContext(adRequest.getHostAppContext()).setAdType(adRequest.getAdType()).setKeywords(adRequest.getKeywords()).build();
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (BidResponse next : list) {
            if (next.isFirstPartyBid()) {
                arrayList.add(next);
            } else {
                arrayList2.add(next);
            }
        }
        BidResponse a2 = a(arrayList);
        if (a2 != null) {
            arrayList2.add(a2);
            arrayList.remove(a2);
            Logger.debug("##ClientAuction##", "winbid " + a2.getBid() + " dfpbid" + a2.getDfpBid() + " multi1 " + a2.getDfpBidMultiplierOne() + " mult2" + a2.getDfpBidMultiplierTwo() + a2.getServerExtras().toString());
            StringBuilder sb = new StringBuilder();
            sb.append("winbid ");
            sb.append(a2.toString());
            Logger.debug("##ClientAuction##", sb.toString());
        } else {
            Logger.debug("##ClientAuction##", "No fpd winbid present");
        }
        if (arrayList2.size() == 0) {
            return null;
        }
        int currentTimeMillis = (int) (System.currentTimeMillis() - j);
        AdResponse adResponse = new AdResponse(a(build, (List<BidResponse>) arrayList2), build, true);
        adResponse.setClientAuctionTime(currentTimeMillis);
        adResponse.setWinningBid(a2);
        ArrayList arrayList3 = new ArrayList(arrayList2);
        arrayList3.addAll(arrayList);
        a(a2, arrayList3, currentTimeMillis, build);
        aa.a((Runnable) new ac() {
            public void a() {
                if (AdUnitConfig.getInstance().getPublisherConfig().shouldReuseBids()) {
                    s.a().a(build.getAdUnitId(), arrayList);
                }
                q.a(build.getAdUnitId(), build.getHostAppContext());
            }
        });
        return adResponse;
    }

    private BidResponse a(List<BidResponse> list) {
        BidResponse bidResponse = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        Collections.sort(list, new Comparator<BidResponse>() {
            /* renamed from: a */
            public int compare(BidResponse bidResponse, BidResponse bidResponse2) {
                return Double.compare(bidResponse2.getAuctionBid(), bidResponse.getAuctionBid());
            }
        });
        BidResponse bidResponse2 = list.get(0);
        if (list.size() > 1) {
            bidResponse = list.get(1);
        }
        double bidMultiplier = bidResponse2.getBidMultiplier();
        double bidMultiplier2 = (bidResponse == null ? bidResponse2.getBidMultiplier() : bidResponse.getBidMultiplier()) + 0.1d;
        bidResponse2.setOriginalBid(da.a((bidResponse2.getOgBidMultiplierOne() * bidMultiplier) + (bidResponse2.getOgBidMultiplierTwo() * bidMultiplier2), 2));
        bidResponse2.setDfpBid(da.a((bidMultiplier * bidResponse2.getDfpBidMultiplierOne()) + (bidMultiplier2 * bidResponse2.getDfpBidMultiplierTwo()), 2));
        bidResponse2.setIsWinner(true);
        return bidResponse2;
    }

    private List<BidResponse> a(AdRequest adRequest, List<BidResponse> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        for (cs a2 : this.a) {
            list = a2.a(list, (String) null, adRequest, (a) null);
        }
        return list;
    }

    private void a(BidResponse bidResponse, List<BidResponse> list, int i, AdRequest adRequest) {
        if (list != null) {
            new a.C0043a(adRequest, bidResponse, list).a().a(i).b().a();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, List<BidResponse> list) {
        boolean z;
        String next;
        Logger.debug("##ClientAuction##", "checking bidders for client auction");
        AdUnit adUnitForCreativeId = AdUnitConfig.getInstance().getAdUnitForCreativeId(str);
        if (adUnitForCreativeId == null || list == null || list.isEmpty() || adUnitForCreativeId.getBidders() == null || adUnitForCreativeId.getBidders().isEmpty()) {
            return false;
        }
        Iterator<String> it = adUnitForCreativeId.getBidders().iterator();
        do {
            z = true;
            if (it.hasNext()) {
                next = it.next();
                Iterator<BidResponse> it2 = list.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (it2.next().getBidderId().equalsIgnoreCase(next)) {
                            continue;
                            break;
                        }
                    } else {
                        z = false;
                        continue;
                        break;
                    }
                }
            } else {
                return true;
            }
        } while (z);
        Logger.debug("##ClientAuction##", "bidderid " + next + " not found in cached bids");
        return false;
    }
}
