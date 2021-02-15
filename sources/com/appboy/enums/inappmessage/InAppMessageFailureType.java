package com.appboy.enums.inappmessage;

import com.appboy.models.IPutIntoJson;

public enum InAppMessageFailureType implements IPutIntoJson<String> {
    IMAGE_DOWNLOAD,
    TEMPLATE_REQUEST,
    ZIP_ASSET_DOWNLOAD,
    DISPLAY_VIEW_GENERATION;

    /* renamed from: com.appboy.enums.inappmessage.InAppMessageFailureType$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.appboy.enums.inappmessage.InAppMessageFailureType[] r0 = com.appboy.enums.inappmessage.InAppMessageFailureType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.appboy.enums.inappmessage.InAppMessageFailureType r1 = com.appboy.enums.inappmessage.InAppMessageFailureType.IMAGE_DOWNLOAD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appboy.enums.inappmessage.InAppMessageFailureType r1 = com.appboy.enums.inappmessage.InAppMessageFailureType.TEMPLATE_REQUEST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.appboy.enums.inappmessage.InAppMessageFailureType r1 = com.appboy.enums.inappmessage.InAppMessageFailureType.ZIP_ASSET_DOWNLOAD     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.appboy.enums.inappmessage.InAppMessageFailureType r1 = com.appboy.enums.inappmessage.InAppMessageFailureType.DISPLAY_VIEW_GENERATION     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appboy.enums.inappmessage.InAppMessageFailureType.AnonymousClass1.<clinit>():void");
        }
    }

    public String forJsonPut() {
        int i = AnonymousClass1.a[ordinal()];
        if (i == 1) {
            return "if";
        }
        if (i == 2) {
            return "tf";
        }
        if (i == 3) {
            return "zf";
        }
        if (i != 4) {
            return null;
        }
        return "vf";
    }
}
