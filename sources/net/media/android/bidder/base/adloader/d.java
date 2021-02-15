package net.media.android.bidder.base.adloader;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import mnetinternal.aa;
import mnetinternal.ac;
import mnetinternal.cs;
import mnetinternal.x;
import mnetinternal.z;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.internal.AdResponse;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;
import net.media.android.bidder.base.models.internal.BidResponse;
import net.media.android.bidder.base.models.internal.DefaultBid;
import net.media.android.bidder.base.models.internal.PublisherConfig;

final class d {
    /* access modifiers changed from: private */
    public Map<String, Map<String, List<DefaultBid>>> a = new HashMap();
    private Future b;
    private b c;
    /* access modifiers changed from: private */
    public ReadWriteLock d = new ReentrantReadWriteLock();

    public d(List<cs> list) {
        this.c = new b(list);
        a();
        b();
    }

    /* access modifiers changed from: private */
    public void a() {
        Future future = this.b;
        if (future != null && !future.isDone()) {
            this.b.cancel(false);
        }
        this.b = aa.a((Runnable) new ac() {
            public void a() {
                List<DefaultBid> defaultBids;
                Logger.debug("##MNetDefaultAdLoader##", "initializing default bidders");
                HashMap hashMap = new HashMap();
                PublisherConfig publisherConfig = AdUnitConfig.getInstance().getPublisherConfig();
                if (publisherConfig != null && (defaultBids = publisherConfig.getDefaultBids()) != null) {
                    for (DefaultBid next : defaultBids) {
                        String bidderId = next.getBidderId();
                        String creativeId = next.getCreativeId();
                        Map map = (Map) hashMap.get(bidderId);
                        if (map == null && (map = (Map) hashMap.get(bidderId)) == null) {
                            map = new HashMap();
                            hashMap.put(bidderId, map);
                        }
                        List list = (List) map.get(creativeId);
                        if (list == null) {
                            map.put(creativeId, d.this.a(next));
                        } else {
                            list.add(next);
                        }
                    }
                    d.this.d.writeLock().lock();
                    d.this.a.clear();
                    d.this.a.putAll(hashMap);
                    d.this.d.writeLock().unlock();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public List<DefaultBid> a(DefaultBid defaultBid) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(defaultBid);
        return arrayList;
    }

    private void b() {
        x.a().a(AdTrackerEvent.EVENT_CONFIG_FETCH_COMPLETE, new z<AdTrackerEvent>() {
            public void a(AdTrackerEvent adTrackerEvent) {
                Logger.debug("##MNetDefaultAdLoader##", "configs changed");
                d.this.a();
            }
        });
    }

    public AdResponse a(AdRequest adRequest, long j) {
        this.d.readLock().lock();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Map<String, List<DefaultBid>>> value : this.a.entrySet()) {
            BidResponse a2 = a(adRequest, (Map<String, List<DefaultBid>>) (Map) value.getValue());
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        AdResponse a3 = this.c.a(adRequest, arrayList, j);
        if (a3 != null) {
            a3.setIsDefaultResponse(true);
        }
        this.d.readLock().unlock();
        return a3;
    }

    private BidResponse a(AdRequest adRequest, Map<String, List<DefaultBid>> map) {
        String crawlerLink = adRequest.getHostAppContext().getCrawlerLink();
        ArrayList arrayList = new ArrayList();
        if (map.containsKey(adRequest.getAdUnitId())) {
            arrayList.addAll(map.get(adRequest.getAdUnitId()));
        }
        if (map.containsKey("*")) {
            arrayList.addAll(map.get("*"));
        }
        if (!TextUtils.isEmpty(crawlerLink)) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (!crawlerLink.matches(((DefaultBid) it.next()).getRequestUrlRegex())) {
                    it.remove();
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        Collections.sort(arrayList, new Comparator<DefaultBid>() {
            /* renamed from: a */
            public int compare(DefaultBid defaultBid, DefaultBid defaultBid2) {
                return Double.compare(defaultBid2.getBid(), defaultBid.getBid());
            }
        });
        return ((DefaultBid) arrayList.get(0)).getBidResponse();
    }
}
