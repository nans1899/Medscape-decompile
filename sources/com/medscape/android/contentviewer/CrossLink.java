package com.medscape.android.contentviewer;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.medscape.android.Constants;
import com.medscape.android.util.StringUtil;

public class CrossLink {
    public String ref;
    public String[] refs;
    public Type type;

    public CrossLink(Type type2) {
        this.type = type2;
    }

    public CrossLink(String str, String str2) {
        if (Constants.OMNITURE_MLINK_CALC.equalsIgnoreCase(str)) {
            this.type = Type.CALC;
        } else if (Name.LABEL.equalsIgnoreCase(str)) {
            this.type = Type.CLASS;
        } else if ("media".equalsIgnoreCase(str)) {
            this.type = Type.MEDIAGALLERY;
        } else if (com.appboy.Constants.APPBOY_PUSH_CONTENT_KEY.equalsIgnoreCase(str)) {
            this.type = Type.A;
        } else if ("references".equalsIgnoreCase(str)) {
            this.type = Type.REFLINK;
        } else if ("referencesection".equalsIgnoreCase(str)) {
            this.type = Type.REFERENCES;
        } else if ("contributorsection".equalsIgnoreCase(str)) {
            this.type = Type.CONTRIBUTORS;
        } else if ("content".equalsIgnoreCase(str)) {
            this.type = Type.CONTENTLINK;
        } else if ("drug".equalsIgnoreCase(str)) {
            this.type = Type.DRUG;
        } else if ("video".equalsIgnoreCase(str)) {
            this.type = Type.VIDEO;
        } else if (MessengerShareContentUtility.MEDIA_IMAGE.equalsIgnoreCase(str)) {
            this.type = Type.IMAGE;
        } else {
            this.type = Type.DISABLED;
        }
        if (StringUtil.isNotEmpty(str2)) {
            this.ref = str2;
            this.refs = str2.split("\\|");
        }
    }

    public CrossLink(Type type2, String str) {
        this.type = type2;
        if (StringUtil.isNotEmpty(str)) {
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

    /* renamed from: com.medscape.android.contentviewer.CrossLink$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.medscape.android.contentviewer.CrossLink$Type[] r0 = com.medscape.android.contentviewer.CrossLink.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type = r0
                com.medscape.android.contentviewer.CrossLink$Type r1 = com.medscape.android.contentviewer.CrossLink.Type.CALC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.medscape.android.contentviewer.CrossLink$Type r1 = com.medscape.android.contentviewer.CrossLink.Type.CLASS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.medscape.android.contentviewer.CrossLink$Type r1 = com.medscape.android.contentviewer.CrossLink.Type.MEDIAGALLERY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.medscape.android.contentviewer.CrossLink$Type r1 = com.medscape.android.contentviewer.CrossLink.Type.A     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.medscape.android.contentviewer.CrossLink$Type r1 = com.medscape.android.contentviewer.CrossLink.Type.REFLINK     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.medscape.android.contentviewer.CrossLink$Type r1 = com.medscape.android.contentviewer.CrossLink.Type.REFERENCES     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.medscape.android.contentviewer.CrossLink$Type r1 = com.medscape.android.contentviewer.CrossLink.Type.CONTRIBUTORS     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.medscape.android.contentviewer.CrossLink$Type r1 = com.medscape.android.contentviewer.CrossLink.Type.CONTENTLINK     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$medscape$android$contentviewer$CrossLink$Type     // Catch:{ NoSuchFieldError -> 0x006c }
                com.medscape.android.contentviewer.CrossLink$Type r1 = com.medscape.android.contentviewer.CrossLink.Type.DRUG     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.contentviewer.CrossLink.AnonymousClass1.<clinit>():void");
        }
    }

    public enum Type {
        CALC,
        CLASS,
        MEDIAGALLERY,
        A,
        REFLINK,
        REFERENCES,
        CONTRIBUTORS,
        CONTENTLINK,
        DRUG,
        VIDEO,
        IMAGE,
        DISABLED;

        public String toString() {
            switch (AnonymousClass1.$SwitchMap$com$medscape$android$contentviewer$CrossLink$Type[ordinal()]) {
                case 1:
                    return "CALC";
                case 2:
                    return "CLASS";
                case 3:
                    return "MEDIAGALLERY";
                case 4:
                    return ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                case 5:
                    return "REFLINK";
                case 6:
                    return "REFERENCES";
                case 7:
                    return "CONTRIBUTORS";
                case 8:
                    return "CONTENTLINK";
                case 9:
                    return "DRUG";
                default:
                    return "DISABLED";
            }
        }
    }
}
