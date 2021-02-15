package net.media.android.bidder.base.macro;

import java.util.Arrays;
import net.media.android.bidder.base.macro.c;
import net.media.android.bidder.base.models.internal.BidResponse;

public class ServerExtraMacros {
    private static ServerExtraMacros sInstance;
    private c mMacroProcessor = new c.a().a(Arrays.asList(new String[]{"${DFPBD}", "${ACID}"})).a();

    private ServerExtraMacros() {
    }

    public static String process(final String str, final BidResponse bidResponse) {
        if (sInstance == null) {
            sInstance = new ServerExtraMacros();
        }
        return sInstance.mMacroProcessor.a(str, (c.b) new c.b() {
            /* JADX WARNING: Removed duplicated region for block: B:13:0x002a  */
            /* JADX WARNING: Removed duplicated region for block: B:22:0x004d  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object a(java.lang.String r10) {
                /*
                    r9 = this;
                    int r0 = r10.hashCode()
                    r1 = 1173876969(0x45f7f0e9, float:7934.114)
                    r2 = 0
                    java.lang.String r3 = "${DFPBD}"
                    r4 = 1
                    if (r0 == r1) goto L_0x001b
                    r1 = 2119310660(0x7e521d44, float:6.982246E37)
                    if (r0 == r1) goto L_0x0013
                    goto L_0x0025
                L_0x0013:
                    boolean r0 = r10.equals(r3)
                    if (r0 == 0) goto L_0x0025
                    r0 = 0
                    goto L_0x0026
                L_0x001b:
                    java.lang.String r0 = "${ACID}"
                    boolean r0 = r10.equals(r0)
                    if (r0 == 0) goto L_0x0025
                    r0 = 1
                    goto L_0x0026
                L_0x0025:
                    r0 = -1
                L_0x0026:
                    java.lang.String r1 = "##ServerExtra##"
                    if (r0 == 0) goto L_0x004d
                    java.lang.String r2 = ""
                    if (r0 == r4) goto L_0x0043
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r3 = "Macro not found "
                    r0.append(r3)
                    r0.append(r10)
                    java.lang.String r10 = r0.toString()
                    net.media.android.bidder.base.logging.Logger.error(r1, r10)
                    return r2
                L_0x0043:
                    net.media.android.bidder.base.models.internal.BidResponse r10 = r3
                    if (r10 == 0) goto L_0x004c
                    java.lang.String r10 = r10.getAdCycleId()
                    return r10
                L_0x004c:
                    return r2
                L_0x004d:
                    net.media.android.bidder.base.models.internal.BidResponse r0 = r3
                    double r5 = r0.getDfpBid()
                    r7 = 0
                    int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                    if (r0 != 0) goto L_0x0082
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r2 = "dfpbid 0, macro"
                    r0.append(r2)
                    r0.append(r10)
                    java.lang.String r10 = " template "
                    r0.append(r10)
                    java.lang.String r10 = r2
                    r0.append(r10)
                    java.lang.String r10 = " dfpbdmacro "
                    r0.append(r10)
                    r0.append(r3)
                    java.lang.String r10 = r0.toString()
                    net.media.android.bidder.base.logging.Logger.debug(r1, r10)
                    java.lang.String r10 = "0.00"
                    return r10
                L_0x0082:
                    java.util.Locale r10 = java.util.Locale.US
                    java.lang.Object[] r0 = new java.lang.Object[r4]
                    net.media.android.bidder.base.models.internal.BidResponse r1 = r3
                    double r3 = r1.getDfpBid()
                    java.lang.Double r1 = java.lang.Double.valueOf(r3)
                    r0[r2] = r1
                    java.lang.String r1 = "%.2f"
                    java.lang.String r10 = java.lang.String.format(r10, r1, r0)
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.macro.ServerExtraMacros.AnonymousClass1.a(java.lang.String):java.lang.Object");
            }
        });
    }
}
