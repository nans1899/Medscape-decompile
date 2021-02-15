package com.wbmd.omniture;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010%\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0018B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B]\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013R\u000e\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u000e\u0010\f\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/wbmd/omniture/Action;", "", "builder", "Lcom/wbmd/omniture/Action$ActionBuilder;", "(Lcom/wbmd/omniture/Action$ActionBuilder;)V", "mmodule", "", "mlink", "channel", "leadConceptId", "leadSpecialtyId", "contentTypeId", "pgtitle", "searchQuery", "incomingSource", "userSeg", "exitUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "data", "", "getMlink", "()Ljava/lang/String;", "getMmodule", "getDataMap", "ActionBuilder", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Action.kt */
public final class Action {
    private String channel;
    private String contentTypeId;
    private Map<String, Object> data;
    private String exitUrl;
    private String incomingSource;
    private String leadConceptId;
    private String leadSpecialtyId;
    private final String mlink;
    private final String mmodule;
    private String pgtitle;
    private String searchQuery;
    private String userSeg;

    public /* synthetic */ Action(ActionBuilder actionBuilder, DefaultConstructorMarker defaultConstructorMarker) {
        this(actionBuilder);
    }

    public Action(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        Intrinsics.checkNotNullParameter(str, "mmodule");
        Intrinsics.checkNotNullParameter(str2, "mlink");
        Intrinsics.checkNotNullParameter(str3, "channel");
        Intrinsics.checkNotNullParameter(str4, "leadConceptId");
        Intrinsics.checkNotNullParameter(str5, "leadSpecialtyId");
        Intrinsics.checkNotNullParameter(str6, "contentTypeId");
        Intrinsics.checkNotNullParameter(str7, "pgtitle");
        Intrinsics.checkNotNullParameter(str8, "searchQuery");
        Intrinsics.checkNotNullParameter(str9, "incomingSource");
        Intrinsics.checkNotNullParameter(str10, "userSeg");
        Intrinsics.checkNotNullParameter(str11, "exitUrl");
        this.mmodule = str;
        this.mlink = str2;
        this.channel = str3;
        this.leadConceptId = str4;
        this.leadSpecialtyId = str5;
        this.contentTypeId = str6;
        this.pgtitle = str7;
        this.searchQuery = str8;
        this.incomingSource = str9;
        this.userSeg = str10;
        this.exitUrl = str11;
        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        this.data = linkedHashMap;
        ExtensionsKt.addIfValueIsNotNullOrEmpty(linkedHashMap, "wapp.mmodule", this.mmodule);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.mlink", this.mlink);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.chn", this.channel);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.ldcncptid", this.leadConceptId);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.ldspecid", this.leadSpecialtyId);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.contypeid", this.contentTypeId);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.pgtitle", this.pgtitle);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.query", this.searchQuery);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, OmnitureKeys.INCOMING_SOURCE, this.incomingSource);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, OmnitureKeys.USER_SEG, this.userSeg);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.exiturl", this.exitUrl);
    }

    public final String getMmodule() {
        return this.mmodule;
    }

    public final String getMlink() {
        return this.mlink;
    }

    private Action(ActionBuilder actionBuilder) {
        this(actionBuilder.getMmodule$wbmd_omniture_release(), actionBuilder.getMlink$wbmd_omniture_release(), actionBuilder.getChannel$wbmd_omniture_release(), actionBuilder.getLeadConceptId$wbmd_omniture_release(), actionBuilder.getLeadSpecialtyId$wbmd_omniture_release(), actionBuilder.getContentTypeId$wbmd_omniture_release(), actionBuilder.getPgtitle$wbmd_omniture_release(), actionBuilder.getSearchQuery$wbmd_omniture_release(), actionBuilder.getIncomingSource$wbmd_omniture_release(), actionBuilder.getUserSeg$wbmd_omniture_release(), actionBuilder.getExitUrl$wbmd_omniture_release());
    }

    public final Map<String, Object> getDataMap() {
        return this.data;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010'\u001a\u00020(J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0004J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0004J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\b¨\u0006)"}, d2 = {"Lcom/wbmd/omniture/Action$ActionBuilder;", "", "()V", "channel", "", "getChannel$wbmd_omniture_release", "()Ljava/lang/String;", "setChannel$wbmd_omniture_release", "(Ljava/lang/String;)V", "contentTypeId", "getContentTypeId$wbmd_omniture_release", "setContentTypeId$wbmd_omniture_release", "exitUrl", "getExitUrl$wbmd_omniture_release", "setExitUrl$wbmd_omniture_release", "incomingSource", "getIncomingSource$wbmd_omniture_release", "setIncomingSource$wbmd_omniture_release", "leadConceptId", "getLeadConceptId$wbmd_omniture_release", "setLeadConceptId$wbmd_omniture_release", "leadSpecialtyId", "getLeadSpecialtyId$wbmd_omniture_release", "setLeadSpecialtyId$wbmd_omniture_release", "mlink", "getMlink$wbmd_omniture_release", "setMlink$wbmd_omniture_release", "mmodule", "getMmodule$wbmd_omniture_release", "setMmodule$wbmd_omniture_release", "pgtitle", "getPgtitle$wbmd_omniture_release", "setPgtitle$wbmd_omniture_release", "searchQuery", "getSearchQuery$wbmd_omniture_release", "setSearchQuery$wbmd_omniture_release", "userSeg", "getUserSeg$wbmd_omniture_release", "setUserSeg$wbmd_omniture_release", "build", "Lcom/wbmd/omniture/Action;", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: Action.kt */
    public static final class ActionBuilder {
        private String channel = "";
        private String contentTypeId = "";
        private String exitUrl = "";
        private String incomingSource = "";
        private String leadConceptId = "";
        private String leadSpecialtyId = "";
        private String mlink = "";
        private String mmodule = "";
        private String pgtitle = "";
        private String searchQuery = "";
        private String userSeg = "";

        public final String getMmodule$wbmd_omniture_release() {
            return this.mmodule;
        }

        public final void setMmodule$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.mmodule = str;
        }

        public final String getMlink$wbmd_omniture_release() {
            return this.mlink;
        }

        public final void setMlink$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.mlink = str;
        }

        public final String getChannel$wbmd_omniture_release() {
            return this.channel;
        }

        public final void setChannel$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.channel = str;
        }

        public final String getLeadConceptId$wbmd_omniture_release() {
            return this.leadConceptId;
        }

        public final void setLeadConceptId$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.leadConceptId = str;
        }

        public final String getLeadSpecialtyId$wbmd_omniture_release() {
            return this.leadSpecialtyId;
        }

        public final void setLeadSpecialtyId$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.leadSpecialtyId = str;
        }

        public final String getContentTypeId$wbmd_omniture_release() {
            return this.contentTypeId;
        }

        public final void setContentTypeId$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.contentTypeId = str;
        }

        public final String getPgtitle$wbmd_omniture_release() {
            return this.pgtitle;
        }

        public final void setPgtitle$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.pgtitle = str;
        }

        public final String getSearchQuery$wbmd_omniture_release() {
            return this.searchQuery;
        }

        public final void setSearchQuery$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.searchQuery = str;
        }

        public final String getIncomingSource$wbmd_omniture_release() {
            return this.incomingSource;
        }

        public final void setIncomingSource$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.incomingSource = str;
        }

        public final String getUserSeg$wbmd_omniture_release() {
            return this.userSeg;
        }

        public final void setUserSeg$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.userSeg = str;
        }

        public final String getExitUrl$wbmd_omniture_release() {
            return this.exitUrl;
        }

        public final void setExitUrl$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.exitUrl = str;
        }

        public final ActionBuilder mmodule(String str) {
            Intrinsics.checkNotNullParameter(str, "mmodule");
            ActionBuilder actionBuilder = this;
            actionBuilder.mmodule = str;
            return actionBuilder;
        }

        public final ActionBuilder mlink(String str) {
            Intrinsics.checkNotNullParameter(str, "mlink");
            ActionBuilder actionBuilder = this;
            actionBuilder.mlink = str;
            return actionBuilder;
        }

        public final ActionBuilder channel(String str) {
            Intrinsics.checkNotNullParameter(str, "channel");
            ActionBuilder actionBuilder = this;
            actionBuilder.channel = str;
            return actionBuilder;
        }

        public final ActionBuilder leadConceptId(String str) {
            Intrinsics.checkNotNullParameter(str, "leadConceptId");
            ActionBuilder actionBuilder = this;
            actionBuilder.leadConceptId = str;
            return actionBuilder;
        }

        public final ActionBuilder leadSpecialtyId(String str) {
            Intrinsics.checkNotNullParameter(str, "leadSpecialtyId");
            ActionBuilder actionBuilder = this;
            actionBuilder.leadSpecialtyId = str;
            return actionBuilder;
        }

        public final ActionBuilder contentTypeId(String str) {
            Intrinsics.checkNotNullParameter(str, "contentTypeId");
            ActionBuilder actionBuilder = this;
            actionBuilder.contentTypeId = str;
            return actionBuilder;
        }

        public final ActionBuilder pgtitle(String str) {
            Intrinsics.checkNotNullParameter(str, "pgtitle");
            ActionBuilder actionBuilder = this;
            actionBuilder.pgtitle = str;
            return actionBuilder;
        }

        public final ActionBuilder searchQuery(String str) {
            Intrinsics.checkNotNullParameter(str, "searchQuery");
            ActionBuilder actionBuilder = this;
            actionBuilder.searchQuery = str;
            return actionBuilder;
        }

        public final ActionBuilder incomingSource(String str) {
            Intrinsics.checkNotNullParameter(str, "incomingSource");
            ActionBuilder actionBuilder = this;
            actionBuilder.incomingSource = str;
            return actionBuilder;
        }

        public final ActionBuilder userSeg(String str) {
            Intrinsics.checkNotNullParameter(str, "userSeg");
            ActionBuilder actionBuilder = this;
            actionBuilder.userSeg = str;
            return actionBuilder;
        }

        public final ActionBuilder exitUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "exitUrl");
            ActionBuilder actionBuilder = this;
            actionBuilder.exitUrl = str;
            return actionBuilder;
        }

        public final Action build() {
            return new Action(this, (DefaultConstructorMarker) null);
        }
    }
}
