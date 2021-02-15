package com.wbmd.omniture;

import com.facebook.places.model.PlaceFields;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010%\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001*B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BÝ\u0001\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0007\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\u0006\u0010\u0012\u001a\u00020\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0007\u0012\u0006\u0010\u0015\u001a\u00020\u0007\u0012\u0006\u0010\u0016\u001a\u00020\u0007\u0012\u0006\u0010\u0017\u001a\u00020\u0007\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u0007\u0012\u0006\u0010\u001b\u001a\u00020\u0007\u0012\u0006\u0010\u001c\u001a\u00020\u0007\u0012\u0006\u0010\u001d\u001a\u00020\u0007\u0012\u0006\u0010\u001e\u001a\u00020\u0007\u0012\u0006\u0010\u001f\u001a\u00020\u0007\u0012\u0006\u0010 \u001a\u00020\u0007\u0012\u0006\u0010!\u001a\u00020\u0007¢\u0006\u0002\u0010\"J\u0014\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010$R\u000e\u0010\u0012\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010$X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010\u001a\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/wbmd/omniture/PageView;", "", "builder", "Lcom/wbmd/omniture/PageView$PageViewBuilder;", "(Lcom/wbmd/omniture/PageView$PageViewBuilder;)V", "page", "", "", "mmodule", "mlink", "userId", "countryOfPractice", "profession", "specialty", "site", "professionID", "specialtyID", "channel", "affiliateUserId", "leadConceptId", "leadSpecialtyId", "contentTypeId", "occupationId", "mTopic", "chronicleID", "Lcom/wbmd/omniture/ChronicleID;", "pgtitle", "searchFilter", "searchResults", "searchQuery", "cmsid", "incomingSource", "userSeg", "exitUrl", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/wbmd/omniture/ChronicleID;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "data", "", "getPage", "()Ljava/util/List;", "setPage", "(Ljava/util/List;)V", "getDataMap", "PageViewBuilder", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PageView.kt */
public final class PageView {
    private String affiliateUserId;
    private String channel;
    private ChronicleID chronicleID;
    private String cmsid;
    private String contentTypeId;
    private String countryOfPractice;
    private Map<String, Object> data;
    private String exitUrl;
    private String incomingSource;
    private String leadConceptId;
    private String leadSpecialtyId;
    private String mTopic;
    private String mlink;
    private String mmodule;
    private String occupationId;
    private List<String> page;
    private String pgtitle;
    private String profession;
    private String professionID;
    private String searchFilter;
    private String searchQuery;
    private String searchResults;
    private String site;
    private String specialty;
    private String specialtyID;
    private String userId;
    private String userSeg;

    public /* synthetic */ PageView(PageViewBuilder pageViewBuilder, DefaultConstructorMarker defaultConstructorMarker) {
        this(pageViewBuilder);
    }

    public PageView(List<String> list, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, ChronicleID chronicleID2, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24) {
        List<String> list2 = list;
        String str25 = str;
        String str26 = str2;
        String str27 = str3;
        String str28 = str4;
        String str29 = str5;
        String str30 = str6;
        String str31 = str7;
        String str32 = str8;
        String str33 = str9;
        String str34 = str10;
        String str35 = str11;
        String str36 = str12;
        String str37 = str13;
        String str38 = str15;
        Intrinsics.checkNotNullParameter(list2, PlaceFields.PAGE);
        Intrinsics.checkNotNullParameter(str25, "mmodule");
        Intrinsics.checkNotNullParameter(str26, "mlink");
        Intrinsics.checkNotNullParameter(str27, Constants.USER_TAG_KEY_USER_ID);
        Intrinsics.checkNotNullParameter(str28, "countryOfPractice");
        Intrinsics.checkNotNullParameter(str29, "profession");
        Intrinsics.checkNotNullParameter(str30, OmnitureConstants.OMNITURE_FILTER_SPECIALTY);
        Intrinsics.checkNotNullParameter(str31, "site");
        Intrinsics.checkNotNullParameter(str32, "professionID");
        Intrinsics.checkNotNullParameter(str33, "specialtyID");
        Intrinsics.checkNotNullParameter(str34, "channel");
        Intrinsics.checkNotNullParameter(str35, "affiliateUserId");
        Intrinsics.checkNotNullParameter(str36, "leadConceptId");
        Intrinsics.checkNotNullParameter(str37, "leadSpecialtyId");
        Intrinsics.checkNotNullParameter(str14, "contentTypeId");
        Intrinsics.checkNotNullParameter(str15, "occupationId");
        Intrinsics.checkNotNullParameter(str16, "mTopic");
        Intrinsics.checkNotNullParameter(str17, "pgtitle");
        Intrinsics.checkNotNullParameter(str18, "searchFilter");
        Intrinsics.checkNotNullParameter(str19, "searchResults");
        Intrinsics.checkNotNullParameter(str20, "searchQuery");
        Intrinsics.checkNotNullParameter(str21, "cmsid");
        Intrinsics.checkNotNullParameter(str22, "incomingSource");
        Intrinsics.checkNotNullParameter(str23, "userSeg");
        Intrinsics.checkNotNullParameter(str24, "exitUrl");
        this.page = list2;
        this.mmodule = str25;
        this.mlink = str26;
        this.userId = str27;
        this.countryOfPractice = str28;
        this.profession = str29;
        this.specialty = str30;
        this.site = str31;
        this.professionID = str32;
        this.specialtyID = str33;
        this.channel = str34;
        this.affiliateUserId = str35;
        this.leadConceptId = str36;
        this.leadSpecialtyId = str37;
        this.contentTypeId = str14;
        this.occupationId = str15;
        this.mTopic = str16;
        this.chronicleID = chronicleID2;
        this.pgtitle = str17;
        this.searchFilter = str18;
        this.searchResults = str19;
        this.searchQuery = str20;
        this.cmsid = str21;
        this.incomingSource = str22;
        this.userSeg = str23;
        this.exitUrl = str24;
        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        this.data = linkedHashMap;
        ExtensionsKt.addIfValueIsNotNullOrEmpty(linkedHashMap, "wapp.mmodule", this.mmodule);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.mlink", this.mlink);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.regid", this.userId);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.dcntry", this.countryOfPractice);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.dprofsn", this.profession);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.dspclty", this.specialty);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.site", this.site);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.dprofsnid", this.professionID);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.dspcltyid", this.specialtyID);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.chn", this.channel);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, OmnitureKeys.AFFILIATE_USER_ID, this.affiliateUserId);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.ldcncptid", this.leadConceptId);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.ldspecid", this.leadSpecialtyId);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.contypeid", this.contentTypeId);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.doccptnid", this.occupationId);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, OmnitureKeys.MTOPIC, this.mTopic);
        Map<String, Object> map = this.data;
        ChronicleID chronicleID3 = this.chronicleID;
        ExtensionsKt.addIfValueIsNotNullOrEmpty(map, "wapp.asset", chronicleID3 != null ? chronicleID3.get() : null);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.pgtitle", this.pgtitle);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.filter", this.searchFilter);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.results", this.searchResults);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.query", this.searchQuery);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, OmnitureKeys.CMSID, this.cmsid);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, OmnitureKeys.INCOMING_SOURCE, this.incomingSource);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, OmnitureKeys.USER_SEG, this.userSeg);
        ExtensionsKt.addIfValueIsNotNullOrEmpty(this.data, "wapp.exiturl", this.exitUrl);
    }

    public final List<String> getPage() {
        return this.page;
    }

    public final void setPage(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.page = list;
    }

    private PageView(PageViewBuilder pageViewBuilder) {
        this(pageViewBuilder.getPage$wbmd_omniture_release(), pageViewBuilder.getMmodule$wbmd_omniture_release(), pageViewBuilder.getMlink$wbmd_omniture_release(), pageViewBuilder.getUserId$wbmd_omniture_release(), pageViewBuilder.getCountryOfPractice$wbmd_omniture_release(), pageViewBuilder.getProfession$wbmd_omniture_release(), pageViewBuilder.getSpecialty$wbmd_omniture_release(), pageViewBuilder.getSite$wbmd_omniture_release(), pageViewBuilder.getProfessionId$wbmd_omniture_release(), pageViewBuilder.getSpecialtyId$wbmd_omniture_release(), pageViewBuilder.getChannel$wbmd_omniture_release(), pageViewBuilder.getAffiliateUserId$wbmd_omniture_release(), pageViewBuilder.getLeadConceptId$wbmd_omniture_release(), pageViewBuilder.getLeadSpecialtyId$wbmd_omniture_release(), pageViewBuilder.getContentTypeId$wbmd_omniture_release(), pageViewBuilder.getOccupationId$wbmd_omniture_release(), pageViewBuilder.getMTopic$wbmd_omniture_release(), pageViewBuilder.getChronicleID$wbmd_omniture_release(), pageViewBuilder.getPgtitle$wbmd_omniture_release(), pageViewBuilder.getSearchFilter$wbmd_omniture_release(), pageViewBuilder.getSearchResults$wbmd_omniture_release(), pageViewBuilder.getSearchQuery$wbmd_omniture_release(), pageViewBuilder.getCmsid$wbmd_omniture_release(), pageViewBuilder.getIncomingSource$wbmd_omniture_release(), pageViewBuilder.getUserSeg$wbmd_omniture_release(), pageViewBuilder.getExitUrl$wbmd_omniture_release());
    }

    public final Map<String, Object> getDataMap() {
        return this.data;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010!\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010]\u001a\u00020^J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0004J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0004J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0004J\u000e\u0010'\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0004J\u000e\u0010*\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0004J\u000e\u0010-\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0004J\u000e\u00100\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0004J\u000e\u00103\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u0004J\u0014\u00103\u001a\u00020\u00002\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000404J\u000e\u00109\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0004J\u000e\u0010<\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u0004J\u000e\u0010?\u001a\u00020\u00002\u0006\u0010_\u001a\u00020\u0004J\u000e\u0010E\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u0004J\u000e\u0010H\u001a\u00020\u00002\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010K\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u0004J\u000e\u0010N\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u0004J\u000e\u0010Q\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\u0004J\u000e\u0010T\u001a\u00020\u00002\u0006\u0010`\u001a\u00020\u0004J\u000e\u0010W\u001a\u00020\u00002\u0006\u0010W\u001a\u00020\u0004J\u000e\u0010Z\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001a\u0010'\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR\u001a\u0010-\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0006\"\u0004\b/\u0010\bR\u001a\u00100\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0006\"\u0004\b2\u0010\bR \u00103\u001a\b\u0012\u0004\u0012\u00020\u000404X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0006\"\u0004\b;\u0010\bR\u001a\u0010<\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0006\"\u0004\b>\u0010\bR\u001a\u0010?\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0006\"\u0004\bA\u0010\bR\u001a\u0010B\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0006\"\u0004\bD\u0010\bR\u001a\u0010E\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0006\"\u0004\bG\u0010\bR\u001a\u0010H\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0006\"\u0004\bJ\u0010\bR\u001a\u0010K\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0006\"\u0004\bM\u0010\bR\u001a\u0010N\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u0006\"\u0004\bP\u0010\bR\u001a\u0010Q\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0006\"\u0004\bS\u0010\bR\u001a\u0010T\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\u0006\"\u0004\bV\u0010\bR\u001a\u0010W\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010\u0006\"\u0004\bY\u0010\bR\u001a\u0010Z\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\u0006\"\u0004\b\\\u0010\b¨\u0006a"}, d2 = {"Lcom/wbmd/omniture/PageView$PageViewBuilder;", "", "()V", "affiliateUserId", "", "getAffiliateUserId$wbmd_omniture_release", "()Ljava/lang/String;", "setAffiliateUserId$wbmd_omniture_release", "(Ljava/lang/String;)V", "channel", "getChannel$wbmd_omniture_release", "setChannel$wbmd_omniture_release", "chronicleID", "Lcom/wbmd/omniture/ChronicleID;", "getChronicleID$wbmd_omniture_release", "()Lcom/wbmd/omniture/ChronicleID;", "setChronicleID$wbmd_omniture_release", "(Lcom/wbmd/omniture/ChronicleID;)V", "cmsid", "getCmsid$wbmd_omniture_release", "setCmsid$wbmd_omniture_release", "contentTypeId", "getContentTypeId$wbmd_omniture_release", "setContentTypeId$wbmd_omniture_release", "countryOfPractice", "getCountryOfPractice$wbmd_omniture_release", "setCountryOfPractice$wbmd_omniture_release", "exitUrl", "getExitUrl$wbmd_omniture_release", "setExitUrl$wbmd_omniture_release", "incomingSource", "getIncomingSource$wbmd_omniture_release", "setIncomingSource$wbmd_omniture_release", "leadConceptId", "getLeadConceptId$wbmd_omniture_release", "setLeadConceptId$wbmd_omniture_release", "leadSpecialtyId", "getLeadSpecialtyId$wbmd_omniture_release", "setLeadSpecialtyId$wbmd_omniture_release", "mTopic", "getMTopic$wbmd_omniture_release", "setMTopic$wbmd_omniture_release", "mlink", "getMlink$wbmd_omniture_release", "setMlink$wbmd_omniture_release", "mmodule", "getMmodule$wbmd_omniture_release", "setMmodule$wbmd_omniture_release", "occupationId", "getOccupationId$wbmd_omniture_release", "setOccupationId$wbmd_omniture_release", "page", "", "getPage$wbmd_omniture_release", "()Ljava/util/List;", "setPage$wbmd_omniture_release", "(Ljava/util/List;)V", "pgtitle", "getPgtitle$wbmd_omniture_release", "setPgtitle$wbmd_omniture_release", "profession", "getProfession$wbmd_omniture_release", "setProfession$wbmd_omniture_release", "professionId", "getProfessionId$wbmd_omniture_release", "setProfessionId$wbmd_omniture_release", "publicationId", "getPublicationId$wbmd_omniture_release", "setPublicationId$wbmd_omniture_release", "searchFilter", "getSearchFilter$wbmd_omniture_release", "setSearchFilter$wbmd_omniture_release", "searchQuery", "getSearchQuery$wbmd_omniture_release", "setSearchQuery$wbmd_omniture_release", "searchResults", "getSearchResults$wbmd_omniture_release", "setSearchResults$wbmd_omniture_release", "site", "getSite$wbmd_omniture_release", "setSite$wbmd_omniture_release", "specialty", "getSpecialty$wbmd_omniture_release", "setSpecialty$wbmd_omniture_release", "specialtyId", "getSpecialtyId$wbmd_omniture_release", "setSpecialtyId$wbmd_omniture_release", "userId", "getUserId$wbmd_omniture_release", "setUserId$wbmd_omniture_release", "userSeg", "getUserSeg$wbmd_omniture_release", "setUserSeg$wbmd_omniture_release", "build", "Lcom/wbmd/omniture/PageView;", "professionID", "specialtyID", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: PageView.kt */
    public static final class PageViewBuilder {
        private String affiliateUserId = "";
        private String channel = "";
        private ChronicleID chronicleID;
        private String cmsid = "";
        private String contentTypeId = "";
        private String countryOfPractice = "";
        private String exitUrl = "";
        private String incomingSource = "";
        private String leadConceptId = "";
        private String leadSpecialtyId = "";
        private String mTopic = "";
        private String mlink = "";
        private String mmodule = "";
        private String occupationId = "";
        private List<String> page = new ArrayList();
        private String pgtitle = "";
        private String profession = "";
        private String professionId = "";
        private String publicationId = "";
        private String searchFilter = "";
        private String searchQuery = "";
        private String searchResults = "";
        private String site = "";
        private String specialty = "";
        private String specialtyId = "";
        private String userId = "";
        private String userSeg = "";

        public final List<String> getPage$wbmd_omniture_release() {
            return this.page;
        }

        public final void setPage$wbmd_omniture_release(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.page = list;
        }

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

        public final String getUserId$wbmd_omniture_release() {
            return this.userId;
        }

        public final void setUserId$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.userId = str;
        }

        public final String getCountryOfPractice$wbmd_omniture_release() {
            return this.countryOfPractice;
        }

        public final void setCountryOfPractice$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.countryOfPractice = str;
        }

        public final String getProfession$wbmd_omniture_release() {
            return this.profession;
        }

        public final void setProfession$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.profession = str;
        }

        public final String getSpecialty$wbmd_omniture_release() {
            return this.specialty;
        }

        public final void setSpecialty$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.specialty = str;
        }

        public final String getSite$wbmd_omniture_release() {
            return this.site;
        }

        public final void setSite$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.site = str;
        }

        public final String getProfessionId$wbmd_omniture_release() {
            return this.professionId;
        }

        public final void setProfessionId$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.professionId = str;
        }

        public final String getSpecialtyId$wbmd_omniture_release() {
            return this.specialtyId;
        }

        public final void setSpecialtyId$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.specialtyId = str;
        }

        public final String getChannel$wbmd_omniture_release() {
            return this.channel;
        }

        public final void setChannel$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.channel = str;
        }

        public final String getAffiliateUserId$wbmd_omniture_release() {
            return this.affiliateUserId;
        }

        public final void setAffiliateUserId$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.affiliateUserId = str;
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

        public final String getOccupationId$wbmd_omniture_release() {
            return this.occupationId;
        }

        public final void setOccupationId$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.occupationId = str;
        }

        public final String getMTopic$wbmd_omniture_release() {
            return this.mTopic;
        }

        public final void setMTopic$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.mTopic = str;
        }

        public final String getPublicationId$wbmd_omniture_release() {
            return this.publicationId;
        }

        public final void setPublicationId$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.publicationId = str;
        }

        public final ChronicleID getChronicleID$wbmd_omniture_release() {
            return this.chronicleID;
        }

        public final void setChronicleID$wbmd_omniture_release(ChronicleID chronicleID2) {
            this.chronicleID = chronicleID2;
        }

        public final String getPgtitle$wbmd_omniture_release() {
            return this.pgtitle;
        }

        public final void setPgtitle$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.pgtitle = str;
        }

        public final String getSearchFilter$wbmd_omniture_release() {
            return this.searchFilter;
        }

        public final void setSearchFilter$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.searchFilter = str;
        }

        public final String getSearchResults$wbmd_omniture_release() {
            return this.searchResults;
        }

        public final void setSearchResults$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.searchResults = str;
        }

        public final String getSearchQuery$wbmd_omniture_release() {
            return this.searchQuery;
        }

        public final void setSearchQuery$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.searchQuery = str;
        }

        public final String getCmsid$wbmd_omniture_release() {
            return this.cmsid;
        }

        public final void setCmsid$wbmd_omniture_release(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.cmsid = str;
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

        public final PageViewBuilder page(String str) {
            Intrinsics.checkNotNullParameter(str, PlaceFields.PAGE);
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.page.add(str);
            return pageViewBuilder;
        }

        public final PageViewBuilder page(List<String> list) {
            Intrinsics.checkNotNullParameter(list, PlaceFields.PAGE);
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.page = list;
            return pageViewBuilder;
        }

        public final PageViewBuilder mmodule(String str) {
            Intrinsics.checkNotNullParameter(str, "mmodule");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.mmodule = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder mlink(String str) {
            Intrinsics.checkNotNullParameter(str, "mlink");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.mlink = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder userId(String str) {
            Intrinsics.checkNotNullParameter(str, Constants.USER_TAG_KEY_USER_ID);
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.userId = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder countryOfPractice(String str) {
            Intrinsics.checkNotNullParameter(str, "countryOfPractice");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.countryOfPractice = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder profession(String str) {
            Intrinsics.checkNotNullParameter(str, "profession");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.profession = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder site(String str) {
            Intrinsics.checkNotNullParameter(str, "site");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.site = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder specialty(String str) {
            Intrinsics.checkNotNullParameter(str, OmnitureConstants.OMNITURE_FILTER_SPECIALTY);
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.specialty = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder professionId(String str) {
            Intrinsics.checkNotNullParameter(str, "professionID");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.professionId = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder specialtyId(String str) {
            Intrinsics.checkNotNullParameter(str, "specialtyID");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.specialtyId = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder channel(String str) {
            Intrinsics.checkNotNullParameter(str, "channel");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.channel = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder affiliateUserId(String str) {
            Intrinsics.checkNotNullParameter(str, "affiliateUserId");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.affiliateUserId = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder leadConceptId(String str) {
            Intrinsics.checkNotNullParameter(str, "leadConceptId");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.leadConceptId = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder leadSpecialtyId(String str) {
            Intrinsics.checkNotNullParameter(str, "leadSpecialtyId");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.leadSpecialtyId = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder contentTypeId(String str) {
            Intrinsics.checkNotNullParameter(str, "contentTypeId");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.contentTypeId = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder occupationId(String str) {
            Intrinsics.checkNotNullParameter(str, "occupationId");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.occupationId = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder mTopic(String str) {
            Intrinsics.checkNotNullParameter(str, "mTopic");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.mTopic = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder chronicleID(ChronicleID chronicleID2) {
            Intrinsics.checkNotNullParameter(chronicleID2, "chronicleID");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.chronicleID = chronicleID2;
            return pageViewBuilder;
        }

        public final PageViewBuilder pgtitle(String str) {
            Intrinsics.checkNotNullParameter(str, "pgtitle");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.pgtitle = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder searchFilter(String str) {
            Intrinsics.checkNotNullParameter(str, "searchFilter");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.searchFilter = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder searchResults(String str) {
            Intrinsics.checkNotNullParameter(str, "searchResults");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.searchResults = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder searchQuery(String str) {
            Intrinsics.checkNotNullParameter(str, "searchQuery");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.searchQuery = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder cmsid(String str) {
            Intrinsics.checkNotNullParameter(str, "cmsid");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.cmsid = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder incomingSource(String str) {
            Intrinsics.checkNotNullParameter(str, "incomingSource");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.incomingSource = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder userSeg(String str) {
            Intrinsics.checkNotNullParameter(str, "userSeg");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.userSeg = str;
            return pageViewBuilder;
        }

        public final PageViewBuilder exitUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "exitUrl");
            PageViewBuilder pageViewBuilder = this;
            pageViewBuilder.exitUrl = str;
            return pageViewBuilder;
        }

        public final PageView build() {
            return new PageView(this, (DefaultConstructorMarker) null);
        }
    }
}
