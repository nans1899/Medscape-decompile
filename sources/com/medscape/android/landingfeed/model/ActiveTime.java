package com.medscape.android.landingfeed.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 &2\u00020\u0001:\u0001&B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J=\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016¨\u0006'"}, d2 = {"Lcom/medscape/android/landingfeed/model/ActiveTime;", "", "itemId", "", "contentType", "currentTime", "", "milliseconds", "active", "", "(Ljava/lang/String;Ljava/lang/String;JJZ)V", "getActive", "()Z", "setActive", "(Z)V", "getContentType", "()Ljava/lang/String;", "setContentType", "(Ljava/lang/String;)V", "getCurrentTime", "()J", "setCurrentTime", "(J)V", "getItemId", "setItemId", "getMilliseconds", "setMilliseconds", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ActiveTime.kt */
public final class ActiveTime {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ITEM_ID = "contentID";
    public static final String TIME_SPENT = "timeSpent";
    private boolean active;
    private String contentType;
    private long currentTime;
    private String itemId;
    private long milliseconds;

    public static /* synthetic */ ActiveTime copy$default(ActiveTime activeTime, String str, String str2, long j, long j2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = activeTime.itemId;
        }
        if ((i & 2) != 0) {
            str2 = activeTime.contentType;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            j = activeTime.currentTime;
        }
        long j3 = j;
        if ((i & 8) != 0) {
            j2 = activeTime.milliseconds;
        }
        long j4 = j2;
        if ((i & 16) != 0) {
            z = activeTime.active;
        }
        return activeTime.copy(str, str3, j3, j4, z);
    }

    public final String component1() {
        return this.itemId;
    }

    public final String component2() {
        return this.contentType;
    }

    public final long component3() {
        return this.currentTime;
    }

    public final long component4() {
        return this.milliseconds;
    }

    public final boolean component5() {
        return this.active;
    }

    public final ActiveTime copy(String str, String str2, long j, long j2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "itemId");
        return new ActiveTime(str, str2, j, j2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActiveTime)) {
            return false;
        }
        ActiveTime activeTime = (ActiveTime) obj;
        return Intrinsics.areEqual((Object) this.itemId, (Object) activeTime.itemId) && Intrinsics.areEqual((Object) this.contentType, (Object) activeTime.contentType) && this.currentTime == activeTime.currentTime && this.milliseconds == activeTime.milliseconds && this.active == activeTime.active;
    }

    public int hashCode() {
        String str = this.itemId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.contentType;
        if (str2 != null) {
            i = str2.hashCode();
        }
        long j = this.currentTime;
        long j2 = this.milliseconds;
        int i2 = (((((hashCode + i) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        boolean z = this.active;
        if (z) {
            z = true;
        }
        return i2 + (z ? 1 : 0);
    }

    public String toString() {
        return "ActiveTime(itemId=" + this.itemId + ", contentType=" + this.contentType + ", currentTime=" + this.currentTime + ", milliseconds=" + this.milliseconds + ", active=" + this.active + ")";
    }

    public ActiveTime(String str, String str2, long j, long j2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "itemId");
        this.itemId = str;
        this.contentType = str2;
        this.currentTime = j;
        this.milliseconds = j2;
        this.active = z;
    }

    public final String getItemId() {
        return this.itemId;
    }

    public final void setItemId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.itemId = str;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final void setContentType(String str) {
        this.contentType = str;
    }

    public final long getCurrentTime() {
        return this.currentTime;
    }

    public final void setCurrentTime(long j) {
        this.currentTime = j;
    }

    public final long getMilliseconds() {
        return this.milliseconds;
    }

    public final void setMilliseconds(long j) {
        this.milliseconds = j;
    }

    public final boolean getActive() {
        return this.active;
    }

    public final void setActive(boolean z) {
        this.active = z;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/medscape/android/landingfeed/model/ActiveTime$Companion;", "", "()V", "ITEM_ID", "", "TIME_SPENT", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ActiveTime.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
