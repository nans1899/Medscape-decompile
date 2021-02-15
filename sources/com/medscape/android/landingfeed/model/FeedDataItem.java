package com.medscape.android.landingfeed.model;

import com.google.gson.annotations.SerializedName;
import com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\bI\b\u0016\u0018\u00002\u00020\u0001B\u0004\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0012\b\u0002\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\u0012\b\u0002\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010$\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u0019\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010$\u0012\u001c\b\u0002\u0010,\u001a\u0016\u0012\u0004\u0012\u00020.\u0018\u00010-j\n\u0012\u0004\u0012\u00020.\u0018\u0001`/\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u00010$\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u00104R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u001c\u0010(\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00108\"\u0004\b:\u0010;R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u00108R\"\u00100\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u000e¢\u0006\u0010\n\u0002\u0010A\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u00108R\u0019\u0010)\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u0019¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u00108R2\u0010,\u001a\u0016\u0012\u0004\u0012\u00020.\u0018\u00010-j\n\u0012\u0004\u0012\u00020.\u0018\u0001`/8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u00108R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u00108R\u001c\u00103\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u00108\"\u0004\bM\u0010;R \u00102\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u00108\"\u0004\bO\u0010;R \u00101\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u00108\"\u0004\bQ\u0010;R\u0018\u0010'\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u00108R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006X\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bS\u0010TR\"\u0010+\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u000e¢\u0006\u0010\n\u0002\u0010A\u001a\u0004\bV\u0010>\"\u0004\bW\u0010@R\u0013\u0010!\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bX\u00108R \u0010\u001a\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u00108\"\u0004\bZ\u0010;R(\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\b8\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010D\"\u0004\b\\\u0010]R\u001e\u0010&\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u0010\n\u0002\u0010A\u001a\u0004\b&\u0010>\"\u0004\b^\u0010@R\u001e\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u0010\n\u0002\u0010A\u001a\u0004\b#\u0010>\"\u0004\b_\u0010@R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u00108R\u001a\u0010\"\u001a\u0004\u0018\u00010\u00108\u0006X\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\ba\u0010TR\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u001d8\u0006X\u0004¢\u0006\n\n\u0002\u0010d\u001a\u0004\bb\u0010cR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\be\u00108R \u0010\u001e\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u00108\"\u0004\bg\u0010;R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u00108R\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006X\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bi\u0010TR\u001c\u0010%\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u00108\"\u0004\bk\u0010;R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bl\u00108R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bm\u00108R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u00108\"\u0004\bo\u0010;R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u00108\"\u0004\bq\u0010;R \u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00198\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010DR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bs\u00108R\u0018\u0010 \u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bt\u00108R\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00108\u0006X\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bu\u0010TR \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u00108\"\u0004\bw\u0010;¨\u0006x"}, d2 = {"Lcom/medscape/android/landingfeed/model/FeedDataItem;", "Ljava/io/Serializable;", "contentId", "", "author", "Lcom/medscape/android/landingfeed/model/Author;", "contentTypeId", "imageUrls", "", "type", "subtype", "url", "uniqueId", "title", "body", "replyCount", "", "downvoteCount", "tags", "upvoteCount", "postId", "category", "latestPostDate", "publishDate", "typeOfCredits", "", "imageUrl", "cmeType", "numberOfCredits", "", "publicationName", "chiefEditor", "updatedDate", "headline", "numberOfComments", "isSaved", "", "source", "isMedlineArticle", "description", "byLine", "cmeCredits", "Lcom/medscape/android/landingfeed/model/Eligibility;", "enabled", "configItems", "Ljava/util/ArrayList;", "Lcom/medscape/android/analytics/remoteconfig/feed/FeedConfigModel;", "Lkotlin/collections/ArrayList;", "categoryHighlighted", "customTitle", "customBody", "ctaLink", "(Ljava/lang/String;Lcom/medscape/android/landingfeed/model/Author;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Ljava/util/ArrayList;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuthor", "()Lcom/medscape/android/landingfeed/model/Author;", "getBody", "()Ljava/lang/String;", "getByLine", "setByLine", "(Ljava/lang/String;)V", "getCategory", "getCategoryHighlighted", "()Ljava/lang/Boolean;", "setCategoryHighlighted", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getChiefEditor", "getCmeCredits", "()Ljava/util/List;", "getCmeType", "getConfigItems", "()Ljava/util/ArrayList;", "setConfigItems", "(Ljava/util/ArrayList;)V", "getContentId", "getContentTypeId", "getCtaLink", "setCtaLink", "getCustomBody", "setCustomBody", "getCustomTitle", "setCustomTitle", "getDescription", "getDownvoteCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEnabled", "setEnabled", "getHeadline", "getImageUrl", "setImageUrl", "getImageUrls", "setImageUrls", "(Ljava/util/List;)V", "setMedlineArticle", "setSaved", "getLatestPostDate", "getNumberOfComments", "getNumberOfCredits", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getPostId", "getPublicationName", "setPublicationName", "getPublishDate", "getReplyCount", "getSource", "setSource", "getSubtype", "getTags", "getTitle", "setTitle", "getType", "setType", "getTypeOfCredits", "getUniqueId", "getUpdatedDate", "getUpvoteCount", "getUrl", "setUrl", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedDataItem.kt */
public class FeedDataItem implements Serializable {
    private final Author author;
    private final String body;
    private String byLine;
    @SerializedName("category")
    private final String category;
    @SerializedName("categoryHighlighted")
    private Boolean categoryHighlighted;
    @SerializedName("chiefEditor")
    private final String chiefEditor;
    private final List<Eligibility> cmeCredits;
    @SerializedName("cmeType")
    private final String cmeType;
    @SerializedName("configs")
    private ArrayList<FeedConfigModel> configItems;
    @SerializedName("contentId")
    private final String contentId;
    @SerializedName("contentTypeId")
    private final String contentTypeId;
    private String ctaLink;
    @SerializedName("customBody")
    private String customBody;
    @SerializedName("customTitle")
    private String customTitle;
    @SerializedName("metaDesc")
    private final String description;
    @SerializedName("downvoteCount")
    private final Integer downvoteCount;
    @SerializedName("enabled")
    private Boolean enabled;
    private final String headline;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("imageUrls")
    private List<String> imageUrls;
    private Boolean isMedlineArticle;
    private Boolean isSaved;
    @SerializedName("latestPostDate")
    private final String latestPostDate;
    @SerializedName("numberOfComments")
    private final Integer numberOfComments;
    @SerializedName("numberOfCredits")
    private final Double numberOfCredits;
    private final String postId;
    @SerializedName("publicationName")
    private String publicationName;
    @SerializedName("publicationDate")
    private final String publishDate;
    @SerializedName("replyCount")
    private final Integer replyCount;
    private String source;
    private final String subtype;
    private final String tags;
    private String title;
    private String type;
    @SerializedName("typeOfCredits")
    private final List<String> typeOfCredits;
    @SerializedName("uniqueId")
    private final String uniqueId;
    @SerializedName("updatedDate")
    private final String updatedDate;
    @SerializedName("upvoteCount")
    private final Integer upvoteCount;
    @SerializedName("uri")
    private String url;

    public FeedDataItem() {
        this((String) null, (Author) null, (String) null, (List) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (Integer) null, (Integer) null, (String) null, (Integer) null, (String) null, (String) null, (String) null, (String) null, (List) null, (String) null, (String) null, (Double) null, (String) null, (String) null, (String) null, (String) null, (Integer) null, (Boolean) null, (String) null, (Boolean) null, (String) null, (String) null, (List) null, (Boolean) null, (ArrayList) null, (Boolean) null, (String) null, (String) null, (String) null, -1, 127, (DefaultConstructorMarker) null);
    }

    public FeedDataItem(String str, Author author2, String str2, List<String> list, String str3, String str4, String str5, String str6, String str7, String str8, Integer num, Integer num2, String str9, Integer num3, String str10, String str11, String str12, String str13, List<String> list2, String str14, String str15, Double d, String str16, String str17, String str18, String str19, Integer num4, Boolean bool, String str20, Boolean bool2, String str21, String str22, List<? extends Eligibility> list3, Boolean bool3, ArrayList<FeedConfigModel> arrayList, Boolean bool4, String str23, String str24, String str25) {
        this.contentId = str;
        this.author = author2;
        this.contentTypeId = str2;
        this.imageUrls = list;
        this.type = str3;
        this.subtype = str4;
        this.url = str5;
        this.uniqueId = str6;
        this.title = str7;
        this.body = str8;
        this.replyCount = num;
        this.downvoteCount = num2;
        this.tags = str9;
        this.upvoteCount = num3;
        this.postId = str10;
        this.category = str11;
        this.latestPostDate = str12;
        this.publishDate = str13;
        this.typeOfCredits = list2;
        this.imageUrl = str14;
        this.cmeType = str15;
        this.numberOfCredits = d;
        this.publicationName = str16;
        this.chiefEditor = str17;
        this.updatedDate = str18;
        this.headline = str19;
        this.numberOfComments = num4;
        this.isSaved = bool;
        this.source = str20;
        this.isMedlineArticle = bool2;
        this.description = str21;
        this.byLine = str22;
        this.cmeCredits = list3;
        this.enabled = bool3;
        this.configItems = arrayList;
        this.categoryHighlighted = bool4;
        this.customTitle = str23;
        this.customBody = str24;
        this.ctaLink = str25;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ FeedDataItem(java.lang.String r40, com.medscape.android.landingfeed.model.Author r41, java.lang.String r42, java.util.List r43, java.lang.String r44, java.lang.String r45, java.lang.String r46, java.lang.String r47, java.lang.String r48, java.lang.String r49, java.lang.Integer r50, java.lang.Integer r51, java.lang.String r52, java.lang.Integer r53, java.lang.String r54, java.lang.String r55, java.lang.String r56, java.lang.String r57, java.util.List r58, java.lang.String r59, java.lang.String r60, java.lang.Double r61, java.lang.String r62, java.lang.String r63, java.lang.String r64, java.lang.String r65, java.lang.Integer r66, java.lang.Boolean r67, java.lang.String r68, java.lang.Boolean r69, java.lang.String r70, java.lang.String r71, java.util.List r72, java.lang.Boolean r73, java.util.ArrayList r74, java.lang.Boolean r75, java.lang.String r76, java.lang.String r77, java.lang.String r78, int r79, int r80, kotlin.jvm.internal.DefaultConstructorMarker r81) {
        /*
            r39 = this;
            r0 = r79
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x000b
            r1 = r2
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x000d
        L_0x000b:
            r1 = r40
        L_0x000d:
            r3 = r0 & 2
            if (r3 == 0) goto L_0x0015
            r3 = r2
            com.medscape.android.landingfeed.model.Author r3 = (com.medscape.android.landingfeed.model.Author) r3
            goto L_0x0017
        L_0x0015:
            r3 = r41
        L_0x0017:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x001f
            r4 = r2
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0021
        L_0x001f:
            r4 = r42
        L_0x0021:
            r5 = r0 & 8
            if (r5 == 0) goto L_0x0029
            r5 = r2
            java.util.List r5 = (java.util.List) r5
            goto L_0x002b
        L_0x0029:
            r5 = r43
        L_0x002b:
            r6 = r0 & 16
            if (r6 == 0) goto L_0x0033
            r6 = r2
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0035
        L_0x0033:
            r6 = r44
        L_0x0035:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x003d
            r7 = r2
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x003f
        L_0x003d:
            r7 = r45
        L_0x003f:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0047
            r8 = r2
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x0049
        L_0x0047:
            r8 = r46
        L_0x0049:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0051
            r9 = r2
            java.lang.String r9 = (java.lang.String) r9
            goto L_0x0053
        L_0x0051:
            r9 = r47
        L_0x0053:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x005b
            r10 = r2
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x005d
        L_0x005b:
            r10 = r48
        L_0x005d:
            r11 = r0 & 512(0x200, float:7.175E-43)
            if (r11 == 0) goto L_0x0065
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x0067
        L_0x0065:
            r11 = r49
        L_0x0067:
            r12 = r0 & 1024(0x400, float:1.435E-42)
            if (r12 == 0) goto L_0x006f
            r12 = r2
            java.lang.Integer r12 = (java.lang.Integer) r12
            goto L_0x0071
        L_0x006f:
            r12 = r50
        L_0x0071:
            r13 = r0 & 2048(0x800, float:2.87E-42)
            if (r13 == 0) goto L_0x0079
            r13 = r2
            java.lang.Integer r13 = (java.lang.Integer) r13
            goto L_0x007b
        L_0x0079:
            r13 = r51
        L_0x007b:
            r14 = r0 & 4096(0x1000, float:5.74E-42)
            if (r14 == 0) goto L_0x0083
            r14 = r2
            java.lang.String r14 = (java.lang.String) r14
            goto L_0x0085
        L_0x0083:
            r14 = r52
        L_0x0085:
            r15 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r15 == 0) goto L_0x008d
            r15 = r2
            java.lang.Integer r15 = (java.lang.Integer) r15
            goto L_0x008f
        L_0x008d:
            r15 = r53
        L_0x008f:
            r81 = r15
            r15 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r15 == 0) goto L_0x0099
            r15 = r2
            java.lang.String r15 = (java.lang.String) r15
            goto L_0x009b
        L_0x0099:
            r15 = r54
        L_0x009b:
            r16 = 32768(0x8000, float:4.5918E-41)
            r16 = r0 & r16
            if (r16 == 0) goto L_0x00a7
            r16 = r2
            java.lang.String r16 = (java.lang.String) r16
            goto L_0x00a9
        L_0x00a7:
            r16 = r55
        L_0x00a9:
            r17 = 65536(0x10000, float:9.18355E-41)
            r17 = r0 & r17
            if (r17 == 0) goto L_0x00b4
            r17 = r2
            java.lang.String r17 = (java.lang.String) r17
            goto L_0x00b6
        L_0x00b4:
            r17 = r56
        L_0x00b6:
            r18 = 131072(0x20000, float:1.83671E-40)
            r18 = r0 & r18
            if (r18 == 0) goto L_0x00c1
            r18 = r2
            java.lang.String r18 = (java.lang.String) r18
            goto L_0x00c3
        L_0x00c1:
            r18 = r57
        L_0x00c3:
            r19 = 262144(0x40000, float:3.67342E-40)
            r19 = r0 & r19
            if (r19 == 0) goto L_0x00ce
            r19 = r2
            java.util.List r19 = (java.util.List) r19
            goto L_0x00d0
        L_0x00ce:
            r19 = r58
        L_0x00d0:
            r20 = 524288(0x80000, float:7.34684E-40)
            r20 = r0 & r20
            if (r20 == 0) goto L_0x00db
            r20 = r2
            java.lang.String r20 = (java.lang.String) r20
            goto L_0x00dd
        L_0x00db:
            r20 = r59
        L_0x00dd:
            r21 = 1048576(0x100000, float:1.469368E-39)
            r21 = r0 & r21
            if (r21 == 0) goto L_0x00e8
            r21 = r2
            java.lang.String r21 = (java.lang.String) r21
            goto L_0x00ea
        L_0x00e8:
            r21 = r60
        L_0x00ea:
            r22 = 2097152(0x200000, float:2.938736E-39)
            r22 = r0 & r22
            if (r22 == 0) goto L_0x00f5
            r22 = r2
            java.lang.Double r22 = (java.lang.Double) r22
            goto L_0x00f7
        L_0x00f5:
            r22 = r61
        L_0x00f7:
            r23 = 4194304(0x400000, float:5.877472E-39)
            r23 = r0 & r23
            if (r23 == 0) goto L_0x0102
            r23 = r2
            java.lang.String r23 = (java.lang.String) r23
            goto L_0x0104
        L_0x0102:
            r23 = r62
        L_0x0104:
            r24 = 8388608(0x800000, float:1.17549435E-38)
            r24 = r0 & r24
            if (r24 == 0) goto L_0x010f
            r24 = r2
            java.lang.String r24 = (java.lang.String) r24
            goto L_0x0111
        L_0x010f:
            r24 = r63
        L_0x0111:
            r25 = 16777216(0x1000000, float:2.3509887E-38)
            r25 = r0 & r25
            if (r25 == 0) goto L_0x011c
            r25 = r2
            java.lang.String r25 = (java.lang.String) r25
            goto L_0x011e
        L_0x011c:
            r25 = r64
        L_0x011e:
            r26 = 33554432(0x2000000, float:9.403955E-38)
            r26 = r0 & r26
            if (r26 == 0) goto L_0x0129
            r26 = r2
            java.lang.String r26 = (java.lang.String) r26
            goto L_0x012b
        L_0x0129:
            r26 = r65
        L_0x012b:
            r27 = 67108864(0x4000000, float:1.5046328E-36)
            r27 = r0 & r27
            if (r27 == 0) goto L_0x0136
            r27 = r2
            java.lang.Integer r27 = (java.lang.Integer) r27
            goto L_0x0138
        L_0x0136:
            r27 = r66
        L_0x0138:
            r28 = 134217728(0x8000000, float:3.85186E-34)
            r28 = r0 & r28
            if (r28 == 0) goto L_0x0143
            r28 = r2
            java.lang.Boolean r28 = (java.lang.Boolean) r28
            goto L_0x0145
        L_0x0143:
            r28 = r67
        L_0x0145:
            r29 = 268435456(0x10000000, float:2.5243549E-29)
            r29 = r0 & r29
            if (r29 == 0) goto L_0x0150
            r29 = r2
            java.lang.String r29 = (java.lang.String) r29
            goto L_0x0152
        L_0x0150:
            r29 = r68
        L_0x0152:
            r30 = 536870912(0x20000000, float:1.0842022E-19)
            r30 = r0 & r30
            if (r30 == 0) goto L_0x015d
            r30 = r2
            java.lang.Boolean r30 = (java.lang.Boolean) r30
            goto L_0x015f
        L_0x015d:
            r30 = r69
        L_0x015f:
            r31 = 1073741824(0x40000000, float:2.0)
            r31 = r0 & r31
            if (r31 == 0) goto L_0x016a
            r31 = r2
            java.lang.String r31 = (java.lang.String) r31
            goto L_0x016c
        L_0x016a:
            r31 = r70
        L_0x016c:
            r32 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r32
            if (r0 == 0) goto L_0x0176
            r0 = r2
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0178
        L_0x0176:
            r0 = r71
        L_0x0178:
            r32 = r80 & 1
            if (r32 == 0) goto L_0x0181
            r32 = r2
            java.util.List r32 = (java.util.List) r32
            goto L_0x0183
        L_0x0181:
            r32 = r72
        L_0x0183:
            r33 = r80 & 2
            r34 = 0
            if (r33 == 0) goto L_0x018e
            java.lang.Boolean r33 = java.lang.Boolean.valueOf(r34)
            goto L_0x0190
        L_0x018e:
            r33 = r73
        L_0x0190:
            r35 = r80 & 4
            if (r35 == 0) goto L_0x0199
            r35 = r2
            java.util.ArrayList r35 = (java.util.ArrayList) r35
            goto L_0x019b
        L_0x0199:
            r35 = r74
        L_0x019b:
            r36 = r80 & 8
            if (r36 == 0) goto L_0x01a4
            java.lang.Boolean r34 = java.lang.Boolean.valueOf(r34)
            goto L_0x01a6
        L_0x01a4:
            r34 = r75
        L_0x01a6:
            r36 = r80 & 16
            if (r36 == 0) goto L_0x01af
            r36 = r2
            java.lang.String r36 = (java.lang.String) r36
            goto L_0x01b1
        L_0x01af:
            r36 = r76
        L_0x01b1:
            r37 = r80 & 32
            if (r37 == 0) goto L_0x01ba
            r37 = r2
            java.lang.String r37 = (java.lang.String) r37
            goto L_0x01bc
        L_0x01ba:
            r37 = r77
        L_0x01bc:
            r38 = r80 & 64
            if (r38 == 0) goto L_0x01c3
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x01c5
        L_0x01c3:
            r2 = r78
        L_0x01c5:
            r40 = r39
            r41 = r1
            r42 = r3
            r43 = r4
            r44 = r5
            r45 = r6
            r46 = r7
            r47 = r8
            r48 = r9
            r49 = r10
            r50 = r11
            r51 = r12
            r52 = r13
            r53 = r14
            r54 = r81
            r55 = r15
            r56 = r16
            r57 = r17
            r58 = r18
            r59 = r19
            r60 = r20
            r61 = r21
            r62 = r22
            r63 = r23
            r64 = r24
            r65 = r25
            r66 = r26
            r67 = r27
            r68 = r28
            r69 = r29
            r70 = r30
            r71 = r31
            r72 = r0
            r73 = r32
            r74 = r33
            r75 = r35
            r76 = r34
            r77 = r36
            r78 = r37
            r79 = r2
            r40.<init>(r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.model.FeedDataItem.<init>(java.lang.String, com.medscape.android.landingfeed.model.Author, java.lang.String, java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.String, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Boolean, java.lang.String, java.lang.Boolean, java.lang.String, java.lang.String, java.util.List, java.lang.Boolean, java.util.ArrayList, java.lang.Boolean, java.lang.String, java.lang.String, java.lang.String, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final Author getAuthor() {
        return this.author;
    }

    public final String getContentTypeId() {
        return this.contentTypeId;
    }

    public final List<String> getImageUrls() {
        return this.imageUrls;
    }

    public final void setImageUrls(List<String> list) {
        this.imageUrls = list;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getSubtype() {
        return this.subtype;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final String getUniqueId() {
        return this.uniqueId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getBody() {
        return this.body;
    }

    public final Integer getReplyCount() {
        return this.replyCount;
    }

    public final Integer getDownvoteCount() {
        return this.downvoteCount;
    }

    public final String getTags() {
        return this.tags;
    }

    public final Integer getUpvoteCount() {
        return this.upvoteCount;
    }

    public final String getPostId() {
        return this.postId;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getLatestPostDate() {
        return this.latestPostDate;
    }

    public final String getPublishDate() {
        return this.publishDate;
    }

    public final List<String> getTypeOfCredits() {
        return this.typeOfCredits;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public final String getCmeType() {
        return this.cmeType;
    }

    public final Double getNumberOfCredits() {
        return this.numberOfCredits;
    }

    public final String getPublicationName() {
        return this.publicationName;
    }

    public final void setPublicationName(String str) {
        this.publicationName = str;
    }

    public final String getChiefEditor() {
        return this.chiefEditor;
    }

    public final String getUpdatedDate() {
        return this.updatedDate;
    }

    public final String getHeadline() {
        return this.headline;
    }

    public final Integer getNumberOfComments() {
        return this.numberOfComments;
    }

    public final Boolean isSaved() {
        return this.isSaved;
    }

    public final void setSaved(Boolean bool) {
        this.isSaved = bool;
    }

    public final String getSource() {
        return this.source;
    }

    public final void setSource(String str) {
        this.source = str;
    }

    public final Boolean isMedlineArticle() {
        return this.isMedlineArticle;
    }

    public final void setMedlineArticle(Boolean bool) {
        this.isMedlineArticle = bool;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getByLine() {
        return this.byLine;
    }

    public final void setByLine(String str) {
        this.byLine = str;
    }

    public final List<Eligibility> getCmeCredits() {
        return this.cmeCredits;
    }

    public final Boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(Boolean bool) {
        this.enabled = bool;
    }

    public final ArrayList<FeedConfigModel> getConfigItems() {
        return this.configItems;
    }

    public final void setConfigItems(ArrayList<FeedConfigModel> arrayList) {
        this.configItems = arrayList;
    }

    public final Boolean getCategoryHighlighted() {
        return this.categoryHighlighted;
    }

    public final void setCategoryHighlighted(Boolean bool) {
        this.categoryHighlighted = bool;
    }

    public final String getCustomTitle() {
        return this.customTitle;
    }

    public final void setCustomTitle(String str) {
        this.customTitle = str;
    }

    public final String getCustomBody() {
        return this.customBody;
    }

    public final void setCustomBody(String str) {
        this.customBody = str;
    }

    public final String getCtaLink() {
        return this.ctaLink;
    }

    public final void setCtaLink(String str) {
        this.ctaLink = str;
    }
}
