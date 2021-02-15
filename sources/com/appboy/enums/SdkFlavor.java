package com.appboy.enums;

import com.appboy.models.IPutIntoJson;

public enum SdkFlavor implements IPutIntoJson<String> {
    UNITY,
    REACT,
    CORDOVA,
    XAMARIN,
    SEGMENT,
    MPARTICLE;

    /* renamed from: com.appboy.enums.SdkFlavor$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.appboy.enums.SdkFlavor[] r0 = com.appboy.enums.SdkFlavor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.appboy.enums.SdkFlavor r1 = com.appboy.enums.SdkFlavor.UNITY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appboy.enums.SdkFlavor r1 = com.appboy.enums.SdkFlavor.REACT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.appboy.enums.SdkFlavor r1 = com.appboy.enums.SdkFlavor.CORDOVA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.appboy.enums.SdkFlavor r1 = com.appboy.enums.SdkFlavor.XAMARIN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.appboy.enums.SdkFlavor r1 = com.appboy.enums.SdkFlavor.SEGMENT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.appboy.enums.SdkFlavor r1 = com.appboy.enums.SdkFlavor.MPARTICLE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appboy.enums.SdkFlavor.AnonymousClass1.<clinit>():void");
        }
    }

    public String forJsonPut() {
        switch (AnonymousClass1.a[ordinal()]) {
            case 1:
                return "unity";
            case 2:
                return "react";
            case 3:
                return "cordova";
            case 4:
                return "xamarin";
            case 5:
                return "segment";
            case 6:
                return "mparticle";
            default:
                return null;
        }
    }
}
