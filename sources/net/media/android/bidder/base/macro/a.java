package net.media.android.bidder.base.macro;

import java.util.Arrays;
import net.media.android.bidder.base.macro.c;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class a {
    private static a b;
    private c a = new c.a().a(Arrays.asList(new String[]{"${ACID}", "${AUCTION_PRICE}", "${REQ_URL}", "${ADID}", "${ADID_HASH}", "${KEYWORDS}"})).a();

    private a() {
    }

    public static String a(String str, final BidResponse bidResponse) {
        if (b == null) {
            b = new a();
        }
        return b.a.a(str, (c.b) new c.b() {
            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object a(java.lang.String r7) {
                /*
                    r6 = this;
                    int r0 = r7.hashCode()
                    r1 = 5
                    r2 = 4
                    r3 = 3
                    r4 = 2
                    r5 = 1
                    switch(r0) {
                        case -1920064857: goto L_0x003f;
                        case -1359037946: goto L_0x0035;
                        case -1137712356: goto L_0x002b;
                        case 1173876969: goto L_0x0021;
                        case 1173906760: goto L_0x0017;
                        case 1514122821: goto L_0x000d;
                        default: goto L_0x000c;
                    }
                L_0x000c:
                    goto L_0x0049
                L_0x000d:
                    java.lang.String r0 = "${ADID_HASH}"
                    boolean r0 = r7.equals(r0)
                    if (r0 == 0) goto L_0x0049
                    r0 = 4
                    goto L_0x004a
                L_0x0017:
                    java.lang.String r0 = "${ADID}"
                    boolean r0 = r7.equals(r0)
                    if (r0 == 0) goto L_0x0049
                    r0 = 3
                    goto L_0x004a
                L_0x0021:
                    java.lang.String r0 = "${ACID}"
                    boolean r0 = r7.equals(r0)
                    if (r0 == 0) goto L_0x0049
                    r0 = 0
                    goto L_0x004a
                L_0x002b:
                    java.lang.String r0 = "${KEYWORDS}"
                    boolean r0 = r7.equals(r0)
                    if (r0 == 0) goto L_0x0049
                    r0 = 5
                    goto L_0x004a
                L_0x0035:
                    java.lang.String r0 = "${REQ_URL}"
                    boolean r0 = r7.equals(r0)
                    if (r0 == 0) goto L_0x0049
                    r0 = 2
                    goto L_0x004a
                L_0x003f:
                    java.lang.String r0 = "${AUCTION_PRICE}"
                    boolean r0 = r7.equals(r0)
                    if (r0 == 0) goto L_0x0049
                    r0 = 1
                    goto L_0x004a
                L_0x0049:
                    r0 = -1
                L_0x004a:
                    if (r0 == 0) goto L_0x00b5
                    if (r0 == r5) goto L_0x00aa
                    r5 = 0
                    if (r0 == r4) goto L_0x0090
                    if (r0 == r3) goto L_0x0087
                    if (r0 == r2) goto L_0x0079
                    if (r0 != r1) goto L_0x0062
                    net.media.android.bidder.base.models.internal.BidResponse r7 = r3
                    java.lang.String r7 = r7.getKeywords()
                    java.lang.String r7 = org.json.JSONObject.quote(r7)
                    return r7
                L_0x0062:
                    java.lang.RuntimeException r0 = new java.lang.RuntimeException
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "unsupported macro: "
                    r1.append(r2)
                    r1.append(r7)
                    java.lang.String r7 = r1.toString()
                    r0.<init>(r7)
                    throw r0
                L_0x0079:
                    android.content.Context r7 = net.media.android.bidder.base.MNet.getContext()     // Catch:{ NoSuchAlgorithmException -> 0x0086 }
                    java.lang.String r7 = mnetinternal.cv.d(r7)     // Catch:{ NoSuchAlgorithmException -> 0x0086 }
                    java.lang.String r7 = mnetinternal.cf.a((java.lang.String) r7)     // Catch:{ NoSuchAlgorithmException -> 0x0086 }
                    return r7
                L_0x0086:
                    return r5
                L_0x0087:
                    android.content.Context r7 = net.media.android.bidder.base.MNet.getContext()
                    java.lang.String r7 = mnetinternal.cv.d(r7)
                    return r7
                L_0x0090:
                    net.media.android.bidder.base.models.internal.BidResponse r7 = r3
                    java.lang.String r7 = r7.getContextLink()
                    if (r7 != 0) goto L_0x0099
                    return r5
                L_0x0099:
                    net.media.android.bidder.base.models.internal.BidResponse r7 = r3
                    java.lang.String r7 = r7.getContextLink()
                    net.media.android.bidder.base.models.internal.BidResponse r0 = r3
                    java.lang.String r0 = r0.getKeywords()
                    java.lang.String r7 = net.media.android.bidder.base.macro.a.b(r7, r0)
                    return r7
                L_0x00aa:
                    net.media.android.bidder.base.models.internal.BidResponse r7 = r3
                    double r0 = r7.getOriginalBid()
                    java.lang.Double r7 = java.lang.Double.valueOf(r0)
                    return r7
                L_0x00b5:
                    net.media.android.bidder.base.models.internal.BidResponse r7 = r3
                    java.lang.String r7 = r7.getAdCycleId()
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.macro.a.AnonymousClass1.a(java.lang.String):java.lang.Object");
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:3|(3:5|6|7)|8|9|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return r8;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0054 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.lang.String r8, java.lang.String r9) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 == 0) goto L_0x0008
            r8 = 0
            return r8
        L_0x0008:
            net.media.android.bidder.base.configs.AdUnitConfig r0 = net.media.android.bidder.base.configs.AdUnitConfig.getInstance()
            net.media.android.bidder.base.models.internal.PublisherConfig r0 = r0.getPublisherConfig()
            boolean r0 = r0.appendKeywordsReqUrl()
            java.lang.String r1 = "UTF-8"
            if (r0 == 0) goto L_0x0054
            java.lang.String r0 = java.net.URLDecoder.decode(r8)     // Catch:{ Exception -> 0x0054 }
            android.net.Uri r0 = android.net.Uri.parse(r0)     // Catch:{ Exception -> 0x0054 }
            android.net.Uri$Builder r0 = r0.buildUpon()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r2 = "keywords"
            r0.appendQueryParameter(r2, r9)     // Catch:{ Exception -> 0x0054 }
            android.net.Uri r9 = r0.build()     // Catch:{ Exception -> 0x0054 }
            java.net.URI r0 = new java.net.URI     // Catch:{ Exception -> 0x0054 }
            java.lang.String r3 = r9.getScheme()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r4 = r9.getAuthority()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r5 = r9.getPath()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r6 = r9.getQuery()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r7 = r9.getFragment()     // Catch:{ Exception -> 0x0054 }
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0054 }
            java.net.URL r9 = r0.toURL()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r8 = java.net.URLEncoder.encode(r9, r1)     // Catch:{ Exception -> 0x0054 }
            return r8
        L_0x0054:
            java.lang.String r8 = java.net.URLEncoder.encode(r8, r1)     // Catch:{ UnsupportedEncodingException -> 0x0058 }
        L_0x0058:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.macro.a.b(java.lang.String, java.lang.String):java.lang.String");
    }
}
