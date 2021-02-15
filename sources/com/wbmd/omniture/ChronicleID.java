package com.wbmd.omniture;

import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/wbmd/omniture/ChronicleID;", "", "contentId", "", "assetId", "pageName", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "get", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ChronicleID.kt */
public final class ChronicleID {
    private final String assetId;
    private final String contentId;
    private final List<String> pageName;

    public ChronicleID(String str, String str2, List<String> list) {
        this.contentId = str;
        this.assetId = str2;
        this.pageName = list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0024, code lost:
        if (r0 != null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0044, code lost:
        if (r7 != null) goto L_0x0048;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String get() {
        /*
            r9 = this;
            java.util.List<java.lang.String> r0 = r9.pageName
            r1 = 1
            java.lang.String r2 = ""
            r3 = 39
            if (r0 == 0) goto L_0x0027
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            com.wbmd.omniture.PageNameFormatter r5 = new com.wbmd.omniture.PageNameFormatter
            r5.<init>(r0)
            java.lang.String r0 = r5.formatPageName$wbmd_omniture_release(r1)
            r4.append(r0)
            r4.append(r3)
            java.lang.String r0 = r4.toString()
            if (r0 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r0 = r2
        L_0x0028:
            kotlin.jvm.internal.StringCompanionObject r4 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            r4 = 3
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r6 = 0
            java.lang.String r7 = r9.contentId
            if (r7 == 0) goto L_0x0047
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r3)
            r8.append(r7)
            r8.append(r3)
            java.lang.String r7 = r8.toString()
            if (r7 == 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r7 = r2
        L_0x0048:
            r5[r6] = r7
            java.lang.String r6 = r9.assetId
            if (r6 == 0) goto L_0x0063
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r3)
            r7.append(r6)
            r7.append(r3)
            java.lang.String r3 = r7.toString()
            if (r3 == 0) goto L_0x0063
            r2 = r3
        L_0x0063:
            r5[r1] = r2
            r1 = 2
            r5[r1] = r0
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r5, r4)
            java.lang.String r1 = "[%s], [%s], [%s]"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            java.lang.String r1 = "java.lang.String.format(format, *args)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.omniture.ChronicleID.get():java.lang.String");
    }
}
