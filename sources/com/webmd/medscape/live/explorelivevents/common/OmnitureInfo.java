package com.webmd.medscape.live.explorelivevents.common;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0016B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B?\b\u0000\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007¢\u0006\u0002\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/OmnitureInfo;", "", "builder", "Lcom/webmd/medscape/live/explorelivevents/common/OmnitureInfo$Builder;", "(Lcom/webmd/medscape/live/explorelivevents/common/OmnitureInfo$Builder;)V", "pageNames", "", "", "channel", "mModule", "mLink", "userSeg", "exitUrl", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannel", "()Ljava/lang/String;", "getExitUrl", "getMLink", "getMModule", "getPageNames", "()Ljava/util/List;", "getUserSeg", "Builder", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureInfo.kt */
public final class OmnitureInfo {
    private final String channel;
    private final String exitUrl;
    private final String mLink;
    private final String mModule;
    private final List<String> pageNames;
    private final String userSeg;

    public OmnitureInfo(List<String> list, String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(list, "pageNames");
        Intrinsics.checkNotNullParameter(str, "channel");
        Intrinsics.checkNotNullParameter(str2, "mModule");
        Intrinsics.checkNotNullParameter(str3, "mLink");
        Intrinsics.checkNotNullParameter(str4, "userSeg");
        Intrinsics.checkNotNullParameter(str5, "exitUrl");
        this.pageNames = list;
        this.channel = str;
        this.mModule = str2;
        this.mLink = str3;
        this.userSeg = str4;
        this.exitUrl = str5;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OmnitureInfo(List list, String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : list, str, str2, str3, str4, str5);
    }

    public final List<String> getPageNames() {
        return this.pageNames;
    }

    public final String getChannel() {
        return this.channel;
    }

    public final String getMModule() {
        return this.mModule;
    }

    public final String getMLink() {
        return this.mLink;
    }

    public final String getUserSeg() {
        return this.userSeg;
    }

    public final String getExitUrl() {
        return this.exitUrl;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OmnitureInfo(Builder builder) {
        this(builder.getPageNames$wbmd_explorelivevents_release(), builder.getChannel$wbmd_explorelivevents_release(), builder.getMModule$wbmd_explorelivevents_release(), builder.getMLink$wbmd_explorelivevents_release(), builder.getUserSeg$wbmd_explorelivevents_release(), builder.getExitUrl$wbmd_explorelivevents_release());
        Intrinsics.checkNotNullParameter(builder, "builder");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001BG\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004\u0012\b\b\u0002\u0010\t\u001a\u00020\u0004¢\u0006\u0002\u0010\nJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÀ\u0003¢\u0006\u0002\b\u001eJ\u000e\u0010\u001f\u001a\u00020\u0004HÀ\u0003¢\u0006\u0002\b J\u000e\u0010!\u001a\u00020\u0004HÀ\u0003¢\u0006\u0002\b\"J\u000e\u0010#\u001a\u00020\u0004HÀ\u0003¢\u0006\u0002\b$J\u000e\u0010%\u001a\u00020\u0004HÀ\u0003¢\u0006\u0002\b&J\u000e\u0010'\u001a\u00020\u0004HÀ\u0003¢\u0006\u0002\b(JK\u0010)\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u0004HÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0004J\t\u0010-\u001a\u00020.HÖ\u0001J\u000e\u0010/\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u00100\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0004J\u0014\u0010\u0002\u001a\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\t\u00101\u001a\u00020\u0004HÖ\u0001J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0004R\u001a\u0010\u0005\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u00062"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/OmnitureInfo$Builder;", "", "pageNames", "", "", "channel", "mModule", "mLink", "userSeg", "exitUrl", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannel$wbmd_explorelivevents_release", "()Ljava/lang/String;", "setChannel$wbmd_explorelivevents_release", "(Ljava/lang/String;)V", "getExitUrl$wbmd_explorelivevents_release", "setExitUrl$wbmd_explorelivevents_release", "getMLink$wbmd_explorelivevents_release", "setMLink$wbmd_explorelivevents_release", "getMModule$wbmd_explorelivevents_release", "setMModule$wbmd_explorelivevents_release", "getPageNames$wbmd_explorelivevents_release", "()Ljava/util/List;", "setPageNames$wbmd_explorelivevents_release", "(Ljava/util/List;)V", "getUserSeg$wbmd_explorelivevents_release", "setUserSeg$wbmd_explorelivevents_release", "build", "Lcom/webmd/medscape/live/explorelivevents/common/OmnitureInfo;", "component1", "component1$wbmd_explorelivevents_release", "component2", "component2$wbmd_explorelivevents_release", "component3", "component3$wbmd_explorelivevents_release", "component4", "component4$wbmd_explorelivevents_release", "component5", "component5$wbmd_explorelivevents_release", "component6", "component6$wbmd_explorelivevents_release", "copy", "equals", "", "other", "hashCode", "", "link", "module", "toString", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: OmnitureInfo.kt */
    public static final class Builder {
        private String channel;
        private String exitUrl;
        private String mLink;
        private String mModule;
        private List<String> pageNames;
        private String userSeg;

        public Builder() {
            this((List) null, (String) null, (String) null, (String) null, (String) null, (String) null, 63, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Builder copy$default(Builder builder, List<String> list, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                list = builder.pageNames;
            }
            if ((i & 2) != 0) {
                str = builder.channel;
            }
            String str6 = str;
            if ((i & 4) != 0) {
                str2 = builder.mModule;
            }
            String str7 = str2;
            if ((i & 8) != 0) {
                str3 = builder.mLink;
            }
            String str8 = str3;
            if ((i & 16) != 0) {
                str4 = builder.userSeg;
            }
            String str9 = str4;
            if ((i & 32) != 0) {
                str5 = builder.exitUrl;
            }
            return builder.copy(list, str6, str7, str8, str9, str5);
        }

        public final List<String> component1$wbmd_explorelivevents_release() {
            return this.pageNames;
        }

        public final String component2$wbmd_explorelivevents_release() {
            return this.channel;
        }

        public final String component3$wbmd_explorelivevents_release() {
            return this.mModule;
        }

        public final String component4$wbmd_explorelivevents_release() {
            return this.mLink;
        }

        public final String component5$wbmd_explorelivevents_release() {
            return this.userSeg;
        }

        public final String component6$wbmd_explorelivevents_release() {
            return this.exitUrl;
        }

        public final Builder copy(List<String> list, String str, String str2, String str3, String str4, String str5) {
            Intrinsics.checkNotNullParameter(list, "pageNames");
            Intrinsics.checkNotNullParameter(str, "channel");
            Intrinsics.checkNotNullParameter(str2, "mModule");
            Intrinsics.checkNotNullParameter(str3, "mLink");
            Intrinsics.checkNotNullParameter(str4, "userSeg");
            Intrinsics.checkNotNullParameter(str5, "exitUrl");
            return new Builder(list, str, str2, str3, str4, str5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) obj;
            return Intrinsics.areEqual((Object) this.pageNames, (Object) builder.pageNames) && Intrinsics.areEqual((Object) this.channel, (Object) builder.channel) && Intrinsics.areEqual((Object) this.mModule, (Object) builder.mModule) && Intrinsics.areEqual((Object) this.mLink, (Object) builder.mLink) && Intrinsics.areEqual((Object) this.userSeg, (Object) builder.userSeg) && Intrinsics.areEqual((Object) this.exitUrl, (Object) builder.exitUrl);
        }

        public int hashCode() {
            List<String> list = this.pageNames;
            int i = 0;
            int hashCode = (list != null ? list.hashCode() : 0) * 31;
            String str = this.channel;
            int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mModule;
            int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.mLink;
            int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.userSeg;
            int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.exitUrl;
            if (str5 != null) {
                i = str5.hashCode();
            }
            return hashCode5 + i;
        }

        public String toString() {
            return "Builder(pageNames=" + this.pageNames + ", channel=" + this.channel + ", mModule=" + this.mModule + ", mLink=" + this.mLink + ", userSeg=" + this.userSeg + ", exitUrl=" + this.exitUrl + ")";
        }

        public Builder(List<String> list, String str, String str2, String str3, String str4, String str5) {
            Intrinsics.checkNotNullParameter(list, "pageNames");
            Intrinsics.checkNotNullParameter(str, "channel");
            Intrinsics.checkNotNullParameter(str2, "mModule");
            Intrinsics.checkNotNullParameter(str3, "mLink");
            Intrinsics.checkNotNullParameter(str4, "userSeg");
            Intrinsics.checkNotNullParameter(str5, "exitUrl");
            this.pageNames = list;
            this.channel = str;
            this.mModule = str2;
            this.mLink = str3;
            this.userSeg = str4;
            this.exitUrl = str5;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ Builder(java.util.List r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, int r11, kotlin.jvm.internal.DefaultConstructorMarker r12) {
            /*
                r4 = this;
                r12 = r11 & 1
                if (r12 == 0) goto L_0x000b
                java.util.ArrayList r5 = new java.util.ArrayList
                r5.<init>()
                java.util.List r5 = (java.util.List) r5
            L_0x000b:
                r12 = r11 & 2
                java.lang.String r0 = ""
                if (r12 == 0) goto L_0x0013
                r12 = r0
                goto L_0x0014
            L_0x0013:
                r12 = r6
            L_0x0014:
                r6 = r11 & 4
                if (r6 == 0) goto L_0x001a
                r1 = r0
                goto L_0x001b
            L_0x001a:
                r1 = r7
            L_0x001b:
                r6 = r11 & 8
                if (r6 == 0) goto L_0x0021
                r2 = r0
                goto L_0x0022
            L_0x0021:
                r2 = r8
            L_0x0022:
                r6 = r11 & 16
                if (r6 == 0) goto L_0x0028
                r3 = r0
                goto L_0x0029
            L_0x0028:
                r3 = r9
            L_0x0029:
                r6 = r11 & 32
                if (r6 == 0) goto L_0x002e
                goto L_0x002f
            L_0x002e:
                r0 = r10
            L_0x002f:
                r6 = r4
                r7 = r5
                r8 = r12
                r9 = r1
                r10 = r2
                r11 = r3
                r12 = r0
                r6.<init>(r7, r8, r9, r10, r11, r12)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.common.OmnitureInfo.Builder.<init>(java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final List<String> getPageNames$wbmd_explorelivevents_release() {
            return this.pageNames;
        }

        public final void setPageNames$wbmd_explorelivevents_release(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.pageNames = list;
        }

        public final String getChannel$wbmd_explorelivevents_release() {
            return this.channel;
        }

        public final void setChannel$wbmd_explorelivevents_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.channel = str;
        }

        public final String getMModule$wbmd_explorelivevents_release() {
            return this.mModule;
        }

        public final void setMModule$wbmd_explorelivevents_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.mModule = str;
        }

        public final String getMLink$wbmd_explorelivevents_release() {
            return this.mLink;
        }

        public final void setMLink$wbmd_explorelivevents_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.mLink = str;
        }

        public final String getUserSeg$wbmd_explorelivevents_release() {
            return this.userSeg;
        }

        public final void setUserSeg$wbmd_explorelivevents_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.userSeg = str;
        }

        public final String getExitUrl$wbmd_explorelivevents_release() {
            return this.exitUrl;
        }

        public final void setExitUrl$wbmd_explorelivevents_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.exitUrl = str;
        }

        public final Builder pageNames(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "pageNames");
            Builder builder = this;
            builder.pageNames = list;
            return builder;
        }

        public final Builder channel(String str) {
            Intrinsics.checkNotNullParameter(str, "channel");
            Builder builder = this;
            builder.channel = str;
            return builder;
        }

        public final Builder module(String str) {
            Intrinsics.checkNotNullParameter(str, "mModule");
            Builder builder = this;
            builder.mModule = str;
            return builder;
        }

        public final Builder link(String str) {
            Intrinsics.checkNotNullParameter(str, "mLink");
            Builder builder = this;
            builder.mLink = str;
            return builder;
        }

        public final Builder userSeg(String str) {
            Intrinsics.checkNotNullParameter(str, "userSeg");
            Builder builder = this;
            builder.userSeg = str;
            return builder;
        }

        public final Builder exitUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "exitUrl");
            Builder builder = this;
            builder.exitUrl = str;
            return builder;
        }

        public final OmnitureInfo build() {
            return new OmnitureInfo(this);
        }
    }
}
