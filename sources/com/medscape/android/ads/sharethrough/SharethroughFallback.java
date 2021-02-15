package com.medscape.android.ads.sharethrough;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B)\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0013H\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/medscape/android/ads/sharethrough/SharethroughFallback;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "header", "", "text", "deepLink", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeepLink", "()Ljava/lang/String;", "setDeepLink", "(Ljava/lang/String;)V", "getHeader", "setHeader", "getText", "setText", "describeContents", "", "writeToParcel", "", "dest", "flags", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SharethroughFallback.kt */
public final class SharethroughFallback implements Parcelable {
    public static final Parcelable.Creator<SharethroughFallback> CREATOR = new SharethroughFallback$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private String deepLink;
    private String header;
    private String text;

    public SharethroughFallback() {
        this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
    }

    public int describeContents() {
        return 0;
    }

    public SharethroughFallback(String str, String str2, String str3) {
        this.header = str;
        this.text = str2;
        this.deepLink = str3;
    }

    public final String getHeader() {
        return this.header;
    }

    public final void setHeader(String str) {
        this.header = str;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        this.text = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SharethroughFallback(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3);
    }

    public final String getDeepLink() {
        return this.deepLink;
    }

    public final void setDeepLink(String str) {
        this.deepLink = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SharethroughFallback(Parcel parcel) {
        this(parcel.readString(), parcel.readString(), parcel.readString());
        Intrinsics.checkNotNullParameter(parcel, "source");
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeString(this.header);
        parcel.writeString(this.text);
        parcel.writeString(this.deepLink);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/medscape/android/ads/sharethrough/SharethroughFallback$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/medscape/android/ads/sharethrough/SharethroughFallback;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SharethroughFallback.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
