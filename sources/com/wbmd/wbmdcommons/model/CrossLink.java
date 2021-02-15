package com.wbmd.wbmdcommons.model;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.share.internal.ShareConstants;
import com.wbmd.wbmdcommons.extensions.StringExtensions;

public class CrossLink {
    public String ref;
    public String[] refs;
    public Type type;

    public CrossLink(Type type2) {
        this.type = type2;
    }

    public CrossLink(String str, String str2) {
        if (Name.LABEL.equalsIgnoreCase(str)) {
            this.type = Type.CLASS;
        } else if ("media".equalsIgnoreCase(str)) {
            this.type = Type.MEDIAGALLERY;
        } else if ("content".equalsIgnoreCase(str)) {
            this.type = Type.CONTENTLINK;
        } else if ("drug".equalsIgnoreCase(str)) {
            this.type = Type.DRUG;
        } else if (MessengerShareContentUtility.MEDIA_IMAGE.equalsIgnoreCase(str)) {
            this.type = Type.IMAGE;
        } else {
            this.type = Type.DISABLED;
        }
        if (!StringExtensions.isNullOrEmpty(str2)) {
            this.ref = str2;
            this.refs = str2.split("\\|");
        }
    }

    public CrossLink(Type type2, String str) {
        this.type = type2;
        if (!StringExtensions.isNullOrEmpty(str)) {
            this.ref = str;
            this.refs = str.split("\\|");
        }
    }

    public CrossLink(Type type2, String[] strArr) {
        this.type = type2;
        this.refs = strArr;
    }

    public String toString() {
        return "Crosslink type=" + this.type + " ref=" + this.ref;
    }

    /* renamed from: com.wbmd.wbmdcommons.model.CrossLink$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$wbmdcommons$model$CrossLink$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.wbmd.wbmdcommons.model.CrossLink$Type[] r0 = com.wbmd.wbmdcommons.model.CrossLink.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$wbmdcommons$model$CrossLink$Type = r0
                com.wbmd.wbmdcommons.model.CrossLink$Type r1 = com.wbmd.wbmdcommons.model.CrossLink.Type.CLASS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$wbmdcommons$model$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.wbmdcommons.model.CrossLink$Type r1 = com.wbmd.wbmdcommons.model.CrossLink.Type.MEDIAGALLERY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$wbmdcommons$model$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.wbmdcommons.model.CrossLink$Type r1 = com.wbmd.wbmdcommons.model.CrossLink.Type.IMAGE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$wbmdcommons$model$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.wbmdcommons.model.CrossLink$Type r1 = com.wbmd.wbmdcommons.model.CrossLink.Type.CONTENTLINK     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$wbmd$wbmdcommons$model$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.wbmd.wbmdcommons.model.CrossLink$Type r1 = com.wbmd.wbmdcommons.model.CrossLink.Type.DRUG     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmdcommons.model.CrossLink.AnonymousClass1.<clinit>():void");
        }
    }

    public enum Type {
        CLASS,
        MEDIAGALLERY,
        CONTENTLINK,
        DRUG,
        IMAGE,
        DISABLED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$wbmd$wbmdcommons$model$CrossLink$Type[ordinal()];
            if (i == 1) {
                return "CLASS";
            }
            if (i == 2) {
                return "MEDIAGALLERY";
            }
            if (i == 3) {
                return ShareConstants.IMAGE_URL;
            }
            if (i != 4) {
                return i != 5 ? "DISABLED" : "DRUG";
            }
            return "CONTENTLINK";
        }
    }
}
