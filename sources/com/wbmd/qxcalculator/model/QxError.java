package com.wbmd.qxcalculator.model;

import java.util.List;
import org.apache.commons.io.IOUtils;

public class QxError {
    public int code;
    public ErrorType errorType;
    public int identifier;
    public String message;
    public String title;

    /* renamed from: com.wbmd.qxcalculator.model.QxError$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$QxError$ErrorType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.wbmd.qxcalculator.model.QxError$ErrorType[] r0 = com.wbmd.qxcalculator.model.QxError.ErrorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$model$QxError$ErrorType = r0
                com.wbmd.qxcalculator.model.QxError$ErrorType r1 = com.wbmd.qxcalculator.model.QxError.ErrorType.NOT_SET     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$QxError$ErrorType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.model.QxError$ErrorType r1 = com.wbmd.qxcalculator.model.QxError.ErrorType.HTML     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$QxError$ErrorType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.model.QxError$ErrorType r1 = com.wbmd.qxcalculator.model.QxError.ErrorType.API     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.QxError.AnonymousClass1.<clinit>():void");
        }
    }

    public enum ErrorType {
        NOT_SET,
        HTML,
        API,
        FACEBOOK,
        PROCESSING;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$wbmd$qxcalculator$model$QxError$ErrorType[ordinal()];
            if (i != 2) {
                return i != 3 ? "" : "API";
            }
            return "HTML";
        }
    }

    public QxError(ErrorType errorType2, int i, String str, String str2) {
        this(errorType2, -1, i, str, str2);
    }

    public QxError(ErrorType errorType2, int i, int i2, String str, String str2) {
        this.errorType = errorType2;
        this.identifier = i;
        this.code = i2;
        this.title = str == null ? "Error" : str;
        this.message = str2 == null ? "" : str2;
    }

    public String toString() {
        return this.title + IOUtils.LINE_SEPARATOR_UNIX + this.message + "\n(ID: " + this.identifier + "; Status: " + this.code + ")";
    }

    public boolean isInvalidAuthKeyError() {
        return this.identifier == 401;
    }

    public boolean isInvalidDeviceIdError() {
        return this.identifier == 31;
    }

    public static String concatenateErrorsIntoString(List<QxError> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (QxError next : list) {
            if (sb.length() > 0) {
                sb.append("\n\n");
            }
            sb.append(next.toString());
        }
        return sb.toString();
    }
}
