package com.qxmd.eventssdkandroid.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class QxError implements Parcelable {
    public static final Parcelable.Creator<QxError> CREATOR = new Parcelable.Creator<QxError>() {
        public QxError createFromParcel(Parcel parcel) {
            QxError qxError = new QxError();
            Integer num = (Integer) parcel.readValue(Integer.class.getClassLoader());
            if (num == null) {
                qxError.errorType = ErrorType.NOT_SET;
            } else {
                qxError.errorType = ErrorType.values()[num.intValue()];
            }
            Integer num2 = (Integer) parcel.readValue(Integer.class.getClassLoader());
            if (num2 != null) {
                qxError.code = num2.intValue();
            }
            qxError.title = (String) parcel.readValue(String.class.getClassLoader());
            qxError.message = (String) parcel.readValue(String.class.getClassLoader());
            return qxError;
        }

        public QxError[] newArray(int i) {
            return new QxError[i];
        }
    };
    public int code;
    public ErrorType errorType;
    public String message;
    public String title;

    public int describeContents() {
        return 0;
    }

    /* renamed from: com.qxmd.eventssdkandroid.model.QxError$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$qxmd$eventssdkandroid$model$QxError$ErrorType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.qxmd.eventssdkandroid.model.QxError$ErrorType[] r0 = com.qxmd.eventssdkandroid.model.QxError.ErrorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$qxmd$eventssdkandroid$model$QxError$ErrorType = r0
                com.qxmd.eventssdkandroid.model.QxError$ErrorType r1 = com.qxmd.eventssdkandroid.model.QxError.ErrorType.NOT_SET     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$qxmd$eventssdkandroid$model$QxError$ErrorType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.qxmd.eventssdkandroid.model.QxError$ErrorType r1 = com.qxmd.eventssdkandroid.model.QxError.ErrorType.HTML     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$qxmd$eventssdkandroid$model$QxError$ErrorType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.qxmd.eventssdkandroid.model.QxError$ErrorType r1 = com.qxmd.eventssdkandroid.model.QxError.ErrorType.API     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qxmd.eventssdkandroid.model.QxError.AnonymousClass2.<clinit>():void");
        }
    }

    public enum ErrorType {
        NOT_SET,
        HTML,
        API,
        PARSER,
        FACEBOOK;

        public String toString() {
            int i = AnonymousClass2.$SwitchMap$com$qxmd$eventssdkandroid$model$QxError$ErrorType[ordinal()];
            if (i != 2) {
                return i != 3 ? "" : "API";
            }
            return "HTML";
        }
    }

    public QxError() {
    }

    public QxError(ErrorType errorType2, int i, String str) {
        this(errorType2, i, (String) null, str);
    }

    public QxError(ErrorType errorType2, int i, String str, String str2) {
        this.errorType = errorType2;
        this.code = i;
        this.title = str == null ? "Error" : str;
        this.message = str2 == null ? "" : str2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(Integer.valueOf(this.errorType.ordinal()));
        parcel.writeValue(Integer.valueOf(this.code));
        parcel.writeValue(this.title);
        parcel.writeValue(this.message);
    }

    public String toString() {
        return this.title + IOUtils.LINE_SEPARATOR_UNIX + this.message + "\n(Status: " + this.code + ")";
    }

    public static String concatenateErrors(List<QxError> list) {
        if (list == null) {
            return null;
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
