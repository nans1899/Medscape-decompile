package com.appboy.enums;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;

public enum Gender implements IPutIntoJson<String> {
    MALE,
    FEMALE,
    OTHER,
    UNKNOWN,
    NOT_APPLICABLE,
    PREFER_NOT_TO_SAY;

    /* renamed from: com.appboy.enums.Gender$1  reason: invalid class name */
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
                com.appboy.enums.Gender[] r0 = com.appboy.enums.Gender.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.appboy.enums.Gender r1 = com.appboy.enums.Gender.MALE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appboy.enums.Gender r1 = com.appboy.enums.Gender.FEMALE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.appboy.enums.Gender r1 = com.appboy.enums.Gender.OTHER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.appboy.enums.Gender r1 = com.appboy.enums.Gender.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.appboy.enums.Gender r1 = com.appboy.enums.Gender.NOT_APPLICABLE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.appboy.enums.Gender r1 = com.appboy.enums.Gender.PREFER_NOT_TO_SAY     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appboy.enums.Gender.AnonymousClass1.<clinit>():void");
        }
    }

    public String forJsonPut() {
        switch (AnonymousClass1.a[ordinal()]) {
            case 1:
                return "m";
            case 2:
                return "f";
            case 3:
                return "o";
            case 4:
                return "u";
            case 5:
                return Constants.APPBOY_PUSH_CUSTOM_NOTIFICATION_ID;
            case 6:
                return "p";
            default:
                return null;
        }
    }
}
