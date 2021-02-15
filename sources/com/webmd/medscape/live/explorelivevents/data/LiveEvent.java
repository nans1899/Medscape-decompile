package com.webmd.medscape.live.explorelivevents.data;

import com.google.gson.annotations.Expose;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\fHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003Jm\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001J\u0013\u0010'\u001a\u00020\f2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u001a\u0010\r\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0014R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010¨\u0006,"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "", "date", "", "excludeIfCountry", "header", "includeIfCountry", "location", "registerLabel", "registerLink", "title", "isAccredited", "", "inFavorites", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getDate", "()Ljava/lang/String;", "getExcludeIfCountry", "getHeader", "getInFavorites", "()Z", "setInFavorites", "(Z)V", "getIncludeIfCountry", "getLocation", "getRegisterLabel", "getRegisterLink", "getTitle", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEvent.kt */
public final class LiveEvent {
    @Expose
    private final String date;
    @Expose
    private final String excludeIfCountry;
    @Expose
    private final String header;
    private boolean inFavorites;
    @Expose
    private final String includeIfCountry;
    @Expose
    private final boolean isAccredited;
    @Expose
    private final String location;
    @Expose
    private final String registerLabel;
    @Expose
    private final String registerLink;
    @Expose
    private final String title;

    public static /* synthetic */ LiveEvent copy$default(LiveEvent liveEvent, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, int i, Object obj) {
        LiveEvent liveEvent2 = liveEvent;
        int i2 = i;
        return liveEvent.copy((i2 & 1) != 0 ? liveEvent2.date : str, (i2 & 2) != 0 ? liveEvent2.excludeIfCountry : str2, (i2 & 4) != 0 ? liveEvent2.header : str3, (i2 & 8) != 0 ? liveEvent2.includeIfCountry : str4, (i2 & 16) != 0 ? liveEvent2.location : str5, (i2 & 32) != 0 ? liveEvent2.registerLabel : str6, (i2 & 64) != 0 ? liveEvent2.registerLink : str7, (i2 & 128) != 0 ? liveEvent2.title : str8, (i2 & 256) != 0 ? liveEvent2.isAccredited : z, (i2 & 512) != 0 ? liveEvent2.inFavorites : z2);
    }

    public final String component1() {
        return this.date;
    }

    public final boolean component10() {
        return this.inFavorites;
    }

    public final String component2() {
        return this.excludeIfCountry;
    }

    public final String component3() {
        return this.header;
    }

    public final String component4() {
        return this.includeIfCountry;
    }

    public final String component5() {
        return this.location;
    }

    public final String component6() {
        return this.registerLabel;
    }

    public final String component7() {
        return this.registerLink;
    }

    public final String component8() {
        return this.title;
    }

    public final boolean component9() {
        return this.isAccredited;
    }

    public final LiveEvent copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(str, OmnitureConstants.OMNITURE_FILTER_DATE);
        Intrinsics.checkNotNullParameter(str2, "excludeIfCountry");
        Intrinsics.checkNotNullParameter(str3, "header");
        String str9 = str4;
        Intrinsics.checkNotNullParameter(str9, "includeIfCountry");
        String str10 = str5;
        Intrinsics.checkNotNullParameter(str10, "location");
        String str11 = str6;
        Intrinsics.checkNotNullParameter(str11, "registerLabel");
        String str12 = str7;
        Intrinsics.checkNotNullParameter(str12, "registerLink");
        String str13 = str8;
        Intrinsics.checkNotNullParameter(str13, "title");
        return new LiveEvent(str, str2, str3, str9, str10, str11, str12, str13, z, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LiveEvent)) {
            return false;
        }
        LiveEvent liveEvent = (LiveEvent) obj;
        return Intrinsics.areEqual((Object) this.date, (Object) liveEvent.date) && Intrinsics.areEqual((Object) this.excludeIfCountry, (Object) liveEvent.excludeIfCountry) && Intrinsics.areEqual((Object) this.header, (Object) liveEvent.header) && Intrinsics.areEqual((Object) this.includeIfCountry, (Object) liveEvent.includeIfCountry) && Intrinsics.areEqual((Object) this.location, (Object) liveEvent.location) && Intrinsics.areEqual((Object) this.registerLabel, (Object) liveEvent.registerLabel) && Intrinsics.areEqual((Object) this.registerLink, (Object) liveEvent.registerLink) && Intrinsics.areEqual((Object) this.title, (Object) liveEvent.title) && this.isAccredited == liveEvent.isAccredited && this.inFavorites == liveEvent.inFavorites;
    }

    public int hashCode() {
        String str = this.date;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.excludeIfCountry;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.header;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.includeIfCountry;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.location;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.registerLabel;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.registerLink;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.title;
        if (str8 != null) {
            i = str8.hashCode();
        }
        int i2 = (hashCode7 + i) * 31;
        boolean z = this.isAccredited;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i3 = (i2 + (z ? 1 : 0)) * 31;
        boolean z3 = this.inFavorites;
        if (!z3) {
            z2 = z3;
        }
        return i3 + (z2 ? 1 : 0);
    }

    public String toString() {
        return "LiveEvent(date=" + this.date + ", excludeIfCountry=" + this.excludeIfCountry + ", header=" + this.header + ", includeIfCountry=" + this.includeIfCountry + ", location=" + this.location + ", registerLabel=" + this.registerLabel + ", registerLink=" + this.registerLink + ", title=" + this.title + ", isAccredited=" + this.isAccredited + ", inFavorites=" + this.inFavorites + ")";
    }

    public LiveEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(str, OmnitureConstants.OMNITURE_FILTER_DATE);
        Intrinsics.checkNotNullParameter(str2, "excludeIfCountry");
        Intrinsics.checkNotNullParameter(str3, "header");
        Intrinsics.checkNotNullParameter(str4, "includeIfCountry");
        Intrinsics.checkNotNullParameter(str5, "location");
        Intrinsics.checkNotNullParameter(str6, "registerLabel");
        Intrinsics.checkNotNullParameter(str7, "registerLink");
        Intrinsics.checkNotNullParameter(str8, "title");
        this.date = str;
        this.excludeIfCountry = str2;
        this.header = str3;
        this.includeIfCountry = str4;
        this.location = str5;
        this.registerLabel = str6;
        this.registerLink = str7;
        this.title = str8;
        this.isAccredited = z;
        this.inFavorites = z2;
    }

    public final String getDate() {
        return this.date;
    }

    public final String getExcludeIfCountry() {
        return this.excludeIfCountry;
    }

    public final String getHeader() {
        return this.header;
    }

    public final String getIncludeIfCountry() {
        return this.includeIfCountry;
    }

    public final String getLocation() {
        return this.location;
    }

    public final String getRegisterLabel() {
        return this.registerLabel;
    }

    public final String getRegisterLink() {
        return this.registerLink;
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean isAccredited() {
        return this.isAccredited;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ LiveEvent(java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, boolean r23, boolean r24, int r25, kotlin.jvm.internal.DefaultConstructorMarker r26) {
        /*
            r14 = this;
            r0 = r25
            r1 = r0 & 256(0x100, float:3.59E-43)
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r12 = 0
            goto L_0x000b
        L_0x0009:
            r12 = r23
        L_0x000b:
            r0 = r0 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L_0x0011
            r13 = 0
            goto L_0x0013
        L_0x0011:
            r13 = r24
        L_0x0013:
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r18
            r8 = r19
            r9 = r20
            r10 = r21
            r11 = r22
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.data.LiveEvent.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final boolean getInFavorites() {
        return this.inFavorites;
    }

    public final void setInFavorites(boolean z) {
        this.inFavorites = z;
    }
}
