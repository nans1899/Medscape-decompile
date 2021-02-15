package net.media.android.bidder.base.macro;

import java.util.Arrays;
import net.media.android.bidder.base.macro.c;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class b {
    /* access modifiers changed from: private */
    public static String b;
    private static b c;
    private c a = new c.a().a(Arrays.asList(new String[]{"%24%7BPID%7D", "%24%7BPN%7D", "%24%7BOGBDP%7D", "%24%7BBDP%7D", "%24%7BCBDP%7D", "%24%7BCLSPRC%7D", "%24%7BRT%7D", "%24%7BREQURL%7D"})).a();

    private b() {
    }

    public static String a(String str, final BidResponse bidResponse) {
        if (c == null) {
            c = new b();
        }
        return c.a.a(str, (c.b) new c.b() {
            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object a(java.lang.String r4) {
                /*
                    r3 = this;
                    int r0 = r4.hashCode()
                    r1 = 1
                    switch(r0) {
                        case -1486502067: goto L_0x004f;
                        case -1081433840: goto L_0x0045;
                        case -101622766: goto L_0x003b;
                        case 796518763: goto L_0x0031;
                        case 798544551: goto L_0x0027;
                        case 873402597: goto L_0x001d;
                        case 1904967416: goto L_0x0013;
                        case 1983502686: goto L_0x0009;
                        default: goto L_0x0008;
                    }
                L_0x0008:
                    goto L_0x0059
                L_0x0009:
                    java.lang.String r0 = "%24%7BCBDP%7D"
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L_0x0059
                    r0 = 4
                    goto L_0x005a
                L_0x0013:
                    java.lang.String r0 = "%24%7BREQURL%7D"
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L_0x0059
                    r0 = 7
                    goto L_0x005a
                L_0x001d:
                    java.lang.String r0 = "%24%7BOGBDP%7D"
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L_0x0059
                    r0 = 2
                    goto L_0x005a
                L_0x0027:
                    java.lang.String r0 = "%24%7BRT%7D"
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L_0x0059
                    r0 = 6
                    goto L_0x005a
                L_0x0031:
                    java.lang.String r0 = "%24%7BPN%7D"
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L_0x0059
                    r0 = 1
                    goto L_0x005a
                L_0x003b:
                    java.lang.String r0 = "%24%7BCLSPRC%7D"
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L_0x0059
                    r0 = 5
                    goto L_0x005a
                L_0x0045:
                    java.lang.String r0 = "%24%7BPID%7D"
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L_0x0059
                    r0 = 0
                    goto L_0x005a
                L_0x004f:
                    java.lang.String r0 = "%24%7BBDP%7D"
                    boolean r0 = r4.equals(r0)
                    if (r0 == 0) goto L_0x0059
                    r0 = 3
                    goto L_0x005a
                L_0x0059:
                    r0 = -1
                L_0x005a:
                    switch(r0) {
                        case 0: goto L_0x00d2;
                        case 1: goto L_0x00cb;
                        case 2: goto L_0x00c0;
                        case 3: goto L_0x00b5;
                        case 4: goto L_0x00aa;
                        case 5: goto L_0x009f;
                        case 6: goto L_0x0083;
                        case 7: goto L_0x0074;
                        default: goto L_0x005d;
                    }
                L_0x005d:
                    java.lang.RuntimeException r0 = new java.lang.RuntimeException
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "unsupported macro: "
                    r1.append(r2)
                    r1.append(r4)
                    java.lang.String r4 = r1.toString()
                    r0.<init>(r4)
                    throw r0
                L_0x0074:
                    net.media.android.bidder.base.models.internal.BidResponse r4 = r3     // Catch:{ UnsupportedEncodingException -> 0x0081 }
                    java.lang.String r4 = r4.getContextLink()     // Catch:{ UnsupportedEncodingException -> 0x0081 }
                    java.lang.String r0 = "UTF-8"
                    java.lang.String r4 = java.net.URLEncoder.encode(r4, r0)     // Catch:{ UnsupportedEncodingException -> 0x0081 }
                    return r4
                L_0x0081:
                    r4 = 0
                    return r4
                L_0x0083:
                    java.lang.String r4 = ""
                    java.lang.String unused = net.media.android.bidder.base.macro.b.b = r4     // Catch:{ InterruptedException -> 0x009a }
                    java.util.concurrent.CountDownLatch r4 = new java.util.concurrent.CountDownLatch     // Catch:{ InterruptedException -> 0x009a }
                    r4.<init>(r1)     // Catch:{ InterruptedException -> 0x009a }
                    net.media.android.bidder.base.models.internal.BidResponse r0 = r3     // Catch:{ InterruptedException -> 0x009a }
                    net.media.android.bidder.base.macro.b$1$1 r1 = new net.media.android.bidder.base.macro.b$1$1     // Catch:{ InterruptedException -> 0x009a }
                    r1.<init>(r4)     // Catch:{ InterruptedException -> 0x009a }
                    r0.getAdCode(r1)     // Catch:{ InterruptedException -> 0x009a }
                    r4.await()     // Catch:{ InterruptedException -> 0x009a }
                L_0x009a:
                    java.lang.String r4 = net.media.android.bidder.base.macro.b.b
                    return r4
                L_0x009f:
                    net.media.android.bidder.base.models.internal.BidResponse r4 = r3
                    double r0 = r4.getClosingPrice()
                    java.lang.Double r4 = java.lang.Double.valueOf(r0)
                    return r4
                L_0x00aa:
                    net.media.android.bidder.base.models.internal.BidResponse r4 = r3
                    double r0 = r4.getCbdp()
                    java.lang.Double r4 = java.lang.Double.valueOf(r0)
                    return r4
                L_0x00b5:
                    net.media.android.bidder.base.models.internal.BidResponse r4 = r3
                    double r0 = r4.getBid()
                    java.lang.Double r4 = java.lang.Double.valueOf(r0)
                    return r4
                L_0x00c0:
                    net.media.android.bidder.base.models.internal.BidResponse r4 = r3
                    double r0 = r4.getOriginalBid()
                    java.lang.Double r4 = java.lang.Double.valueOf(r0)
                    return r4
                L_0x00cb:
                    net.media.android.bidder.base.models.internal.BidResponse r4 = r3
                    java.lang.String r4 = r4.getBidderName()
                    return r4
                L_0x00d2:
                    net.media.android.bidder.base.models.internal.BidResponse r4 = r3
                    java.lang.String r4 = r4.getBidderId()
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.macro.b.AnonymousClass1.a(java.lang.String):java.lang.Object");
            }
        });
    }
}
