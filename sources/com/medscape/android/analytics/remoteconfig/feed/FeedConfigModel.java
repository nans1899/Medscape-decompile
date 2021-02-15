package com.medscape.android.analytics.remoteconfig.feed;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/feed/FeedConfigModel;", "", "feedType", "", "position", "", "repeatEvery", "nextRepeatPosition", "(Ljava/lang/String;III)V", "getFeedType", "()Ljava/lang/String;", "setFeedType", "(Ljava/lang/String;)V", "getNextRepeatPosition", "()I", "setNextRepeatPosition", "(I)V", "getPosition", "setPosition", "getRepeatEvery", "setRepeatEvery", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedConfigModel.kt */
public final class FeedConfigModel {
    @SerializedName("feedType")
    private String feedType;
    private int nextRepeatPosition;
    @SerializedName("position")
    private int position;
    @SerializedName("repeatEvery")
    private int repeatEvery;

    public FeedConfigModel() {
        this((String) null, 0, 0, 0, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ FeedConfigModel copy$default(FeedConfigModel feedConfigModel, String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = feedConfigModel.feedType;
        }
        if ((i4 & 2) != 0) {
            i = feedConfigModel.position;
        }
        if ((i4 & 4) != 0) {
            i2 = feedConfigModel.repeatEvery;
        }
        if ((i4 & 8) != 0) {
            i3 = feedConfigModel.nextRepeatPosition;
        }
        return feedConfigModel.copy(str, i, i2, i3);
    }

    public final String component1() {
        return this.feedType;
    }

    public final int component2() {
        return this.position;
    }

    public final int component3() {
        return this.repeatEvery;
    }

    public final int component4() {
        return this.nextRepeatPosition;
    }

    public final FeedConfigModel copy(String str, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(str, "feedType");
        return new FeedConfigModel(str, i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeedConfigModel)) {
            return false;
        }
        FeedConfigModel feedConfigModel = (FeedConfigModel) obj;
        return Intrinsics.areEqual((Object) this.feedType, (Object) feedConfigModel.feedType) && this.position == feedConfigModel.position && this.repeatEvery == feedConfigModel.repeatEvery && this.nextRepeatPosition == feedConfigModel.nextRepeatPosition;
    }

    public int hashCode() {
        String str = this.feedType;
        return ((((((str != null ? str.hashCode() : 0) * 31) + this.position) * 31) + this.repeatEvery) * 31) + this.nextRepeatPosition;
    }

    public String toString() {
        return "FeedConfigModel(feedType=" + this.feedType + ", position=" + this.position + ", repeatEvery=" + this.repeatEvery + ", nextRepeatPosition=" + this.nextRepeatPosition + ")";
    }

    public FeedConfigModel(String str, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(str, "feedType");
        this.feedType = str;
        this.position = i;
        this.repeatEvery = i2;
        this.nextRepeatPosition = i3;
    }

    public final String getFeedType() {
        return this.feedType;
    }

    public final void setFeedType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.feedType = str;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }

    public final int getRepeatEvery() {
        return this.repeatEvery;
    }

    public final void setRepeatEvery(int i) {
        this.repeatEvery = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FeedConfigModel(String str, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? "" : str, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    public final int getNextRepeatPosition() {
        return this.nextRepeatPosition;
    }

    public final void setNextRepeatPosition(int i) {
        this.nextRepeatPosition = i;
    }
}
