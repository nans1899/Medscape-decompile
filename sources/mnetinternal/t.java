package mnetinternal;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.models.AdSize;
import net.media.android.bidder.base.models.internal.BidResponse;

final class t implements r {
    private Map<String, Map<String, List<BidResponse>>> a = new ConcurrentHashMap();
    private u b;
    private final Object c = new Object();

    t(u uVar) {
        this.b = uVar;
    }

    public List<BidResponse> a(String str, List<AdSize> list, String str2) {
        Map map;
        if (TextUtils.isEmpty(str) || (map = this.a.get(str)) == null) {
            return null;
        }
        synchronized (this.c) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : map.entrySet()) {
                List list2 = (List) entry.getValue();
                ArrayList arrayList2 = new ArrayList();
                if (Constants.YBNCA_BIDDER_ID.equalsIgnoreCase((String) entry.getKey()) && !TextUtils.isEmpty(str2)) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        BidResponse bidResponse = (BidResponse) it.next();
                        if (str2.equalsIgnoreCase(bidResponse.getContextLink())) {
                            if (this.b.a(bidResponse)) {
                                arrayList2.add(bidResponse);
                            } else {
                                it.remove();
                            }
                        }
                    }
                }
                if (arrayList2.isEmpty()) {
                    arrayList2.addAll(list2);
                }
                if (list != null) {
                    if (!list.isEmpty()) {
                        ArrayList arrayList3 = new ArrayList();
                        ArrayList arrayList4 = new ArrayList(list);
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            BidResponse bidResponse2 = (BidResponse) it2.next();
                            int indexOf = arrayList4.indexOf(bidResponse2.getAdSize());
                            if (indexOf != -1) {
                                if (this.b.a(bidResponse2)) {
                                    arrayList3.add(bidResponse2);
                                    arrayList4.remove(indexOf);
                                    it2.remove();
                                    list2.remove(bidResponse2);
                                } else {
                                    list2.remove(bidResponse2);
                                    it2.remove();
                                }
                            }
                        }
                        if (!arrayList3.isEmpty()) {
                            arrayList.addAll(arrayList3);
                        }
                    }
                }
                BidResponse a2 = a((List<BidResponse>) arrayList2, (List<BidResponse>) list2);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
    }

    private BidResponse a(List<BidResponse> list, List<BidResponse> list2) {
        if (list == null || list.size() == 0) {
            return null;
        }
        BidResponse bidResponse = list.get(0);
        list.remove(0);
        list2.remove(bidResponse);
        if (this.b.a(bidResponse)) {
            return bidResponse;
        }
        return a(list, list2);
    }

    public List<BidResponse> a(String str) {
        return a(str, (List<AdSize>) null, (String) null);
    }

    public void a(String str, BidResponse bidResponse) {
        Map map;
        if (!TextUtils.isEmpty(str) && bidResponse != null) {
            String a2 = a(bidResponse);
            synchronized (this.c) {
                Map map2 = this.a.get(str);
                if (map2 == null) {
                    synchronized (t.class) {
                        map = this.a.get(str);
                        if (map == null) {
                            map = new ConcurrentHashMap();
                            this.a.put(str, map);
                        }
                    }
                    map2 = map;
                }
                List list = (List) map2.get(a2);
                if (list == null) {
                    map2.put(a2, b(bidResponse));
                } else {
                    list.add(bidResponse);
                }
            }
        }
    }

    private String a(BidResponse bidResponse) {
        return bidResponse.getBidderId();
    }

    private List<BidResponse> b(BidResponse bidResponse) {
        List<BidResponse> synchronizedList = Collections.synchronizedList(new ArrayList());
        synchronizedList.add(bidResponse);
        return synchronizedList;
    }

    public void a(String str, List<BidResponse> list) {
        if (list != null && !list.isEmpty()) {
            for (BidResponse a2 : list) {
                a(str, a2);
            }
        }
    }

    public Map<String, Integer> b(String str) {
        Map map;
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str) || (map = this.a.get(str)) == null) {
            return hashMap;
        }
        synchronized (this.c) {
            for (Map.Entry entry : map.entrySet()) {
                hashMap.put(entry.getKey(), Integer.valueOf(((List) entry.getValue()).size()));
            }
        }
        return hashMap;
    }

    public Map<String, Map<String, Integer>> a() {
        HashMap hashMap = new HashMap();
        synchronized (this.c) {
            for (Map.Entry next : this.a.entrySet()) {
                Map map = (Map) next.getValue();
                if (map != null) {
                    HashMap hashMap2 = new HashMap();
                    for (Map.Entry entry : map.entrySet()) {
                        hashMap2.put(entry.getKey(), Integer.valueOf(((List) entry.getValue()).size()));
                    }
                    hashMap.put(next.getKey(), hashMap2);
                }
            }
        }
        return hashMap;
    }
}
