package com.medscape.android.landingfeed.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/medscape/android/landingfeed/model/LandingFeedApiRequest;", "", "homepageId", "", "page", "size", "type", "", "(IIILjava/lang/String;)V", "getHomepageId", "()I", "getPage", "getSize", "getType", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LandingFeedApiRequest.kt */
public final class LandingFeedApiRequest {
    private final int homepageId;
    private final int page;
    private final int size;
    private final String type;

    public static /* synthetic */ LandingFeedApiRequest copy$default(LandingFeedApiRequest landingFeedApiRequest, int i, int i2, int i3, String str, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = landingFeedApiRequest.homepageId;
        }
        if ((i4 & 2) != 0) {
            i2 = landingFeedApiRequest.page;
        }
        if ((i4 & 4) != 0) {
            i3 = landingFeedApiRequest.size;
        }
        if ((i4 & 8) != 0) {
            str = landingFeedApiRequest.type;
        }
        return landingFeedApiRequest.copy(i, i2, i3, str);
    }

    public final int component1() {
        return this.homepageId;
    }

    public final int component2() {
        return this.page;
    }

    public final int component3() {
        return this.size;
    }

    public final String component4() {
        return this.type;
    }

    public final LandingFeedApiRequest copy(int i, int i2, int i3, String str) {
        Intrinsics.checkNotNullParameter(str, "type");
        return new LandingFeedApiRequest(i, i2, i3, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LandingFeedApiRequest)) {
            return false;
        }
        LandingFeedApiRequest landingFeedApiRequest = (LandingFeedApiRequest) obj;
        return this.homepageId == landingFeedApiRequest.homepageId && this.page == landingFeedApiRequest.page && this.size == landingFeedApiRequest.size && Intrinsics.areEqual((Object) this.type, (Object) landingFeedApiRequest.type);
    }

    public int hashCode() {
        int i = ((((this.homepageId * 31) + this.page) * 31) + this.size) * 31;
        String str = this.type;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "LandingFeedApiRequest(homepageId=" + this.homepageId + ", page=" + this.page + ", size=" + this.size + ", type=" + this.type + ")";
    }

    public LandingFeedApiRequest(int i, int i2, int i3, String str) {
        Intrinsics.checkNotNullParameter(str, "type");
        this.homepageId = i;
        this.page = i2;
        this.size = i3;
        this.type = str;
    }

    public final int getHomepageId() {
        return this.homepageId;
    }

    public final int getPage() {
        return this.page;
    }

    public final int getSize() {
        return this.size;
    }

    public final String getType() {
        return this.type;
    }
}
