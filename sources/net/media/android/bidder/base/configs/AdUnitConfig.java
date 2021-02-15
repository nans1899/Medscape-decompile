package net.media.android.bidder.base.configs;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.mnet.gson.k;
import com.mnet.gson.n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import mnetinternal.cc;
import mnetinternal.cd;
import mnetinternal.x;
import mnetinternal.z;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;
import net.media.android.bidder.base.models.internal.AdUnit;
import net.media.android.bidder.base.models.internal.PublisherConfig;

public final class AdUnitConfig {
    static final String ACTIVITY_KEY = "act";
    static final String AD_UNIT_KEY = "au";
    private static final String BIDDER_CONFIG_KEY = "hb_config";
    private static final String KEY = "key";
    static final String MAX_FIRST_VISIBLE_KEY = "max_first";
    static final String MAX_LAST_VISIBLE_KEY = "max_last";
    static final String MIN_FIRST_VISIBLE_KEY = "min_first";
    static final String MIN_LAST_VISIBLE_KEY = "min_last";
    static final String PARENT_ID_KEY = "pid";
    private static final String PREFIX = "prefix";
    private static final String PREFIX_IN_APP = "in-app";
    public static final String TAG = "##AdunitConfig##";
    private static final String VALUE = "value";
    static final String VIEW_ID_KEY = "id";
    private static volatile AdUnitConfig sInstance;
    private final List<a> mAdUnitFilters = new ArrayList();
    private final List<AdUnit> mAdUnits = Collections.synchronizedList(new ArrayList());
    private PublisherConfig mPublisherConfig;

    private interface a {
        List<AdUnit> a(List<AdUnit> list, String str, Map<String, Object> map, View view);
    }

    private AdUnitConfig() {
        registerAdUnitFilters();
        initConfigs();
        x.a().a(AdTrackerEvent.EVENT_CONFIG_FETCH_COMPLETE, new z() {
            public void a(Object obj) {
                AdUnitConfig.getInstance().initConfigs();
            }
        });
    }

    public static AdUnitConfig getInstance() {
        AdUnitConfig adUnitConfig = sInstance;
        if (adUnitConfig == null) {
            synchronized (AdUnitConfig.class) {
                adUnitConfig = sInstance;
                if (adUnitConfig == null) {
                    adUnitConfig = new AdUnitConfig();
                    sInstance = adUnitConfig;
                }
            }
        }
        return adUnitConfig;
    }

    public PublisherConfig getPublisherConfig() {
        synchronized (this) {
            if (this.mPublisherConfig == null) {
                PublisherConfig publisherConfig = new PublisherConfig();
                return publisherConfig;
            }
            PublisherConfig publisherConfig2 = this.mPublisherConfig;
            return publisherConfig2;
        }
    }

    /* access modifiers changed from: private */
    public void initConfigs() {
        n a2 = a.a().a(BIDDER_CONFIG_KEY);
        if (a2 != null) {
            synchronized (this) {
                this.mPublisherConfig = (PublisherConfig) net.media.android.bidder.base.gson.a.b().a((k) a2, PublisherConfig.class);
                this.mAdUnits.clear();
                if (this.mPublisherConfig.getAdUnits() != null) {
                    for (AdUnit next : this.mPublisherConfig.getAdUnits()) {
                        if (next != null) {
                            this.mAdUnits.add(next);
                        }
                    }
                }
            }
        }
    }

    private void registerAdUnitFilters() {
        if (this.mAdUnitFilters.isEmpty()) {
            this.mAdUnitFilters.add(new d());
            this.mAdUnitFilters.add(new c());
            this.mAdUnitFilters.add(new b());
        }
    }

    public boolean isBidderEnabled() {
        boolean z;
        synchronized (this) {
            z = this.mPublisherConfig != null && this.mPublisherConfig.isBidderEnabled();
        }
        return z;
    }

    public AdUnit getAdUnit(String str, Map<String, Object> map, View view) {
        ArrayList arrayList;
        if (this.mAdUnits == null) {
            return null;
        }
        List<AdUnit> arrayList2 = new ArrayList<>();
        synchronized (this) {
            arrayList = new ArrayList(this.mAdUnits);
        }
        for (a next : this.mAdUnitFilters) {
            if (arrayList2.isEmpty()) {
                arrayList2.addAll(arrayList);
            } else {
                arrayList.clear();
                arrayList.addAll(arrayList2);
            }
            arrayList2 = next.a(arrayList2, str, map, view);
        }
        if (arrayList2.isEmpty() || arrayList2.size() > 1) {
            return null;
        }
        return arrayList2.get(0);
    }

    public AdUnit getAdUnitForCreativeId(String str) {
        List<AdUnit> list = this.mAdUnits;
        if (list == null) {
            return null;
        }
        for (AdUnit next : list) {
            if (next.getCreativeId().equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    private static final class b implements a {
        b() {
        }

        public List<AdUnit> a(List<AdUnit> list, String str, Map<String, Object> map, View view) {
            if (TextUtils.isEmpty(str)) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (AdUnit next : list) {
                try {
                    if (next.getExtAdUnitId().equalsIgnoreCase("*")) {
                        arrayList2.add(next);
                    } else if (AdUnitConfig.safeMatches(str, next.getExtAdUnitId())) {
                        Logger.debug(AdUnitConfig.TAG, "matched " + next.getExtAdUnitId());
                        arrayList.add(next);
                    }
                } catch (Exception e) {
                    Logger.notify(AdUnitConfig.TAG, "error in matcher", e);
                }
            }
            if (arrayList.isEmpty() && arrayList2.size() == 1) {
                return arrayList2;
            }
            if (arrayList.size() == 1) {
                return arrayList;
            }
            arrayList.clear();
            return arrayList;
        }
    }

    /* access modifiers changed from: private */
    public static boolean safeMatches(String str, String str2) {
        try {
            if (str2.startsWith("*")) {
                str2 = "." + str2;
            }
            return Pattern.matches(str2, str);
        } catch (Exception e) {
            Logger.notify(TAG, "error in matcher", e);
            return false;
        }
    }

    private static final class d implements a {
        d() {
        }

        public List<AdUnit> a(List<AdUnit> list, String str, Map<String, Object> map, View view) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (AdUnit next : list) {
                if (!(map == null || map.isEmpty() || next.getTargets() == null)) {
                    arrayList2.clear();
                    for (Map next2 : next.getTargets()) {
                        if (next2 != null && !next2.isEmpty()) {
                            if (!next2.containsKey(AdUnitConfig.PREFIX) || !((String) next2.get(AdUnitConfig.PREFIX)).equalsIgnoreCase(AdUnitConfig.PREFIX_IN_APP)) {
                                arrayList2.add(next2);
                            }
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        boolean z = true;
                        Iterator it = arrayList2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Map map2 = (Map) it.next();
                            if (!map2.isEmpty() && (!map2.containsKey(AdUnitConfig.PREFIX) || !((String) map2.get(AdUnitConfig.PREFIX)).equalsIgnoreCase(AdUnitConfig.PREFIX_IN_APP))) {
                                String str2 = (String) map2.get(AdUnitConfig.KEY);
                                String str3 = (String) map2.get("value");
                                if (!map.containsKey(str2) || !str3.equals(map.get(str2))) {
                                    z = false;
                                }
                            }
                        }
                        z = false;
                        if (z) {
                            arrayList.add(next);
                        }
                    }
                }
            }
            return arrayList;
        }
    }

    private static final class c implements a {
        private int a = -1;
        private int b = -1;

        c() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:61:0x010a, code lost:
            if (r23.getId() != java.lang.Integer.parseInt((java.lang.String) r6.get("id"))) goto L_0x00fa;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x0153, code lost:
            r15 = false;
         */
        /* JADX WARNING: Removed duplicated region for block: B:105:0x019b  */
        /* JADX WARNING: Removed duplicated region for block: B:114:0x01b4  */
        /* JADX WARNING: Removed duplicated region for block: B:123:0x01cf  */
        /* JADX WARNING: Removed duplicated region for block: B:132:0x01e9  */
        /* JADX WARNING: Removed duplicated region for block: B:136:0x01f1  */
        /* JADX WARNING: Removed duplicated region for block: B:155:0x0153 A[EDGE_INSN: B:155:0x0153->B:85:0x0153 ?: BREAK  , SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x012d A[Catch:{ NumberFormatException -> 0x0158 }] */
        /* JADX WARNING: Removed duplicated region for block: B:87:0x0156  */
        /* JADX WARNING: Removed duplicated region for block: B:91:0x015f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.List<net.media.android.bidder.base.models.internal.AdUnit> a(java.util.List<net.media.android.bidder.base.models.internal.AdUnit> r20, java.lang.String r21, java.util.Map<java.lang.String, java.lang.Object> r22, android.view.View r23) {
            /*
                r19 = this;
                r0 = r19
                r1 = r23
                r0.a(r1)
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
                java.util.Iterator r3 = r20.iterator()
            L_0x0010:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x01fc
                java.lang.Object r4 = r3.next()
                net.media.android.bidder.base.models.internal.AdUnit r4 = (net.media.android.bidder.base.models.internal.AdUnit) r4
                java.util.List r5 = r4.getTargets()
                if (r5 == 0) goto L_0x01f8
                boolean r6 = r5.isEmpty()
                if (r6 == 0) goto L_0x0029
                goto L_0x0010
            L_0x0029:
                java.util.HashMap r6 = new java.util.HashMap
                r6.<init>()
                java.util.Iterator r5 = r5.iterator()
            L_0x0032:
                boolean r7 = r5.hasNext()
                if (r7 == 0) goto L_0x006e
                java.lang.Object r7 = r5.next()
                java.util.Map r7 = (java.util.Map) r7
                if (r7 == 0) goto L_0x0032
                boolean r8 = r7.isEmpty()
                if (r8 == 0) goto L_0x0047
                goto L_0x0032
            L_0x0047:
                java.lang.String r8 = "prefix"
                java.lang.Object r8 = r7.get(r8)
                java.lang.String r8 = (java.lang.String) r8
                if (r8 != 0) goto L_0x0052
                goto L_0x0032
            L_0x0052:
                java.lang.String r9 = "in-app"
                boolean r8 = r8.equalsIgnoreCase(r9)
                if (r8 == 0) goto L_0x0032
                java.lang.String r8 = "key"
                java.lang.Object r8 = r7.get(r8)
                java.lang.String r8 = (java.lang.String) r8
                java.lang.String r9 = "value"
                java.lang.Object r7 = r7.get(r9)
                java.lang.String r7 = (java.lang.String) r7
                r6.put(r8, r7)
                goto L_0x0032
            L_0x006e:
                java.lang.String r5 = "au"
                boolean r7 = r6.containsKey(r5)
                java.lang.String r8 = "max_last"
                java.lang.String r9 = "min_last"
                java.lang.String r10 = "max_first"
                java.lang.String r11 = "min_first"
                java.lang.String r12 = "act"
                java.lang.String r13 = "pid"
                java.lang.String r14 = "id"
                if (r7 != 0) goto L_0x00b0
                boolean r7 = r6.containsKey(r14)
                if (r7 != 0) goto L_0x00b0
                boolean r7 = r6.containsKey(r13)
                if (r7 != 0) goto L_0x00b0
                boolean r7 = r6.containsKey(r12)
                if (r7 != 0) goto L_0x00b0
                boolean r7 = r6.containsKey(r11)
                if (r7 != 0) goto L_0x00b0
                boolean r7 = r6.containsKey(r10)
                if (r7 != 0) goto L_0x00b0
                boolean r7 = r6.containsKey(r9)
                if (r7 != 0) goto L_0x00b0
                boolean r7 = r6.containsKey(r8)
                if (r7 != 0) goto L_0x00b0
                goto L_0x0010
            L_0x00b0:
                boolean r7 = r6.containsKey(r5)
                r16 = 0
                if (r7 == 0) goto L_0x00c8
                java.lang.Object r5 = r6.get(r5)
                java.lang.String r5 = (java.lang.String) r5
                r7 = r21
                boolean r5 = r5.equalsIgnoreCase(r7)
                if (r5 != 0) goto L_0x00ca
                r5 = 0
                goto L_0x00cb
            L_0x00c8:
                r7 = r21
            L_0x00ca:
                r5 = 1
            L_0x00cb:
                boolean r17 = r6.containsKey(r14)
                if (r17 == 0) goto L_0x010f
                if (r1 == 0) goto L_0x010f
                r17 = 0
                android.content.Context r18 = r23.getContext()     // Catch:{ Exception -> 0x00e8 }
                android.content.res.Resources r15 = r18.getResources()     // Catch:{ Exception -> 0x00e8 }
                r22 = r3
                int r3 = r23.getId()     // Catch:{ Exception -> 0x00ea }
                java.lang.String r17 = r15.getResourceName(r3)     // Catch:{ Exception -> 0x00ea }
                goto L_0x00ea
            L_0x00e8:
                r22 = r3
            L_0x00ea:
                r3 = r17
                if (r3 == 0) goto L_0x00fc
                java.lang.Object r15 = r6.get(r14)
                java.lang.String r15 = (java.lang.String) r15
                boolean r3 = r3.equalsIgnoreCase(r15)
                if (r3 != 0) goto L_0x00fc
            L_0x00fa:
                r5 = 0
                goto L_0x0111
            L_0x00fc:
                java.lang.Object r3 = r6.get(r14)     // Catch:{ NumberFormatException -> 0x010d }
                java.lang.String r3 = (java.lang.String) r3     // Catch:{ NumberFormatException -> 0x010d }
                int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x010d }
                int r14 = r23.getId()     // Catch:{ NumberFormatException -> 0x010d }
                if (r14 == r3) goto L_0x0111
                goto L_0x00fa
            L_0x010d:
                goto L_0x0111
            L_0x010f:
                r22 = r3
            L_0x0111:
                boolean r3 = r6.containsKey(r13)
                if (r3 == 0) goto L_0x0159
                if (r1 == 0) goto L_0x0159
                java.lang.Object r3 = r6.get(r13)     // Catch:{ NumberFormatException -> 0x0158 }
                java.lang.String r3 = (java.lang.String) r3     // Catch:{ NumberFormatException -> 0x0158 }
                int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x0158 }
                android.view.ViewParent r13 = r23.getParent()     // Catch:{ NumberFormatException -> 0x0158 }
                r14 = 20
            L_0x0129:
                int r15 = r14 + -1
                if (r14 <= 0) goto L_0x0153
                if (r13 != 0) goto L_0x0130
                goto L_0x0153
            L_0x0130:
                boolean r14 = r13 instanceof android.view.ViewGroup     // Catch:{ NumberFormatException -> 0x0158 }
                if (r14 == 0) goto L_0x013f
                r14 = r13
                android.view.ViewGroup r14 = (android.view.ViewGroup) r14     // Catch:{ NumberFormatException -> 0x0158 }
                int r14 = r14.getId()     // Catch:{ NumberFormatException -> 0x0158 }
                if (r14 != r3) goto L_0x014d
            L_0x013d:
                r15 = 1
                goto L_0x0154
            L_0x013f:
                boolean r14 = r13 instanceof android.view.View     // Catch:{ NumberFormatException -> 0x0158 }
                if (r14 == 0) goto L_0x0153
                r14 = r13
                android.view.View r14 = (android.view.View) r14     // Catch:{ NumberFormatException -> 0x0158 }
                int r14 = r14.getId()     // Catch:{ NumberFormatException -> 0x0158 }
                if (r14 != r3) goto L_0x014d
                goto L_0x013d
            L_0x014d:
                android.view.ViewParent r13 = r13.getParent()     // Catch:{ NumberFormatException -> 0x0158 }
                r14 = r15
                goto L_0x0129
            L_0x0153:
                r15 = 0
            L_0x0154:
                if (r15 != 0) goto L_0x0159
                r5 = 0
                goto L_0x0159
            L_0x0158:
            L_0x0159:
                boolean r3 = r6.containsKey(r12)
                if (r3 == 0) goto L_0x0182
                android.app.Activity r3 = mnetinternal.da.c((android.view.View) r23)
                if (r3 != 0) goto L_0x0169
                android.app.Activity r3 = mnetinternal.da.a()
            L_0x0169:
                if (r3 != 0) goto L_0x016d
            L_0x016b:
                r5 = 0
                goto L_0x0182
            L_0x016d:
                java.lang.Object r12 = r6.get(r12)
                java.lang.String r12 = (java.lang.String) r12
                java.lang.Class r3 = r3.getClass()
                java.lang.String r3 = r3.getCanonicalName()
                boolean r3 = r12.equalsIgnoreCase(r3)
                if (r3 != 0) goto L_0x0182
                goto L_0x016b
            L_0x0182:
                boolean r3 = r6.containsKey(r11)
                r12 = -1
                if (r3 == 0) goto L_0x019e
                int r3 = r0.a
                if (r3 == r12) goto L_0x019e
                java.lang.Object r3 = r6.get(r11)     // Catch:{ NumberFormatException -> 0x019d }
                java.lang.String r3 = (java.lang.String) r3     // Catch:{ NumberFormatException -> 0x019d }
                int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x019d }
                int r11 = r0.a     // Catch:{ NumberFormatException -> 0x019d }
                if (r3 < r11) goto L_0x019e
                r5 = 0
                goto L_0x019e
            L_0x019d:
            L_0x019e:
                boolean r3 = r6.containsKey(r10)
                if (r3 == 0) goto L_0x01b7
                int r3 = r0.a
                if (r3 == r12) goto L_0x01b7
                java.lang.Object r10 = r6.get(r10)     // Catch:{ NumberFormatException -> 0x01b6 }
                java.lang.String r10 = (java.lang.String) r10     // Catch:{ NumberFormatException -> 0x01b6 }
                int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ NumberFormatException -> 0x01b6 }
                if (r3 < r10) goto L_0x01b7
                r5 = 0
                goto L_0x01b7
            L_0x01b6:
            L_0x01b7:
                boolean r3 = r6.containsKey(r9)
                if (r3 == 0) goto L_0x01d2
                int r3 = r0.b
                if (r3 == r12) goto L_0x01d2
                java.lang.Object r3 = r6.get(r9)     // Catch:{ NumberFormatException -> 0x01d1 }
                java.lang.String r3 = (java.lang.String) r3     // Catch:{ NumberFormatException -> 0x01d1 }
                int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x01d1 }
                int r9 = r0.b     // Catch:{ NumberFormatException -> 0x01d1 }
                if (r3 < r9) goto L_0x01d2
                r5 = 0
                goto L_0x01d2
            L_0x01d1:
            L_0x01d2:
                boolean r3 = r6.containsKey(r8)
                if (r3 == 0) goto L_0x01ef
                int r3 = r0.b
                if (r3 == r12) goto L_0x01ef
                java.lang.Object r6 = r6.get(r8)     // Catch:{ NumberFormatException -> 0x01ee }
                java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x01ee }
                int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x01ee }
                if (r3 < r6) goto L_0x01e9
                goto L_0x01eb
            L_0x01e9:
                r16 = r5
            L_0x01eb:
                r5 = r16
                goto L_0x01ef
            L_0x01ee:
            L_0x01ef:
                if (r5 == 0) goto L_0x01f4
                r2.add(r4)
            L_0x01f4:
                r3 = r22
                goto L_0x0010
            L_0x01f8:
                r7 = r21
                goto L_0x0010
            L_0x01fc:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.configs.AdUnitConfig.c.a(java.util.List, java.lang.String, java.util.Map, android.view.View):java.util.List");
        }

        private void a(View view) {
            if (view != null) {
                ViewParent parent = view.getParent();
                int i = 10;
                while (true) {
                    int i2 = i - 1;
                    if (i > 0 && parent != null) {
                        if (cd.a(parent)) {
                            this.a = cd.b(parent);
                            this.b = cd.c(parent);
                        } else if (cc.a(parent)) {
                            this.a = cc.b(parent);
                            this.b = cc.c(parent);
                        }
                        parent = parent.getParent();
                        i = i2;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public boolean disableAdsWithoutConsent() {
        boolean z;
        synchronized (this) {
            z = this.mPublisherConfig != null && this.mPublisherConfig.disableAdsWithoutConsent();
        }
        return z;
    }
}
