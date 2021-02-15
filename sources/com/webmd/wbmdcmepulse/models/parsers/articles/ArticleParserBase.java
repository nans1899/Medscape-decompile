package com.webmd.wbmdcmepulse.models.parsers.articles;

import android.text.Html;
import android.text.TextUtils;
import android.util.Xml;
import com.appboy.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ServerProtocol;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.firebase.messaging.Constants;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.ads.model.AdContentData;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.wbmdcmepulse.exceptions.IncompatibleArticleException;
import com.webmd.wbmdcmepulse.models.articles.Accreditor;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.articles.ArticlePage;
import com.webmd.wbmdcmepulse.models.articles.Contributor;
import com.webmd.wbmdcmepulse.models.articles.ContributorComment;
import com.webmd.wbmdcmepulse.models.articles.CreditInstructions;
import com.webmd.wbmdcmepulse.models.articles.Eligibility;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import com.webmd.wbmdcmepulse.models.articles.Media;
import com.webmd.wbmdcmepulse.models.articles.MiscProvider;
import com.webmd.wbmdcmepulse.models.articles.Provider;
import com.webmd.wbmdcmepulse.models.articles.QuestionPageMapItem;
import com.webmd.wbmdcmepulse.models.articles.References;
import com.webmd.wbmdcmepulse.models.articles.Section;
import com.webmd.wbmdcmepulse.models.articles.Slide;
import com.webmd.wbmdcmepulse.models.articles.SubSection;
import com.webmd.wbmdcmepulse.models.articles.Version;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.media.android.bidder.base.models.MNetUser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class ArticleParserBase {
    private static String CONTRIB_NODE_NAME = "contrib";
    protected final String FALSE_ABBREVIATION = MNetUser.FEMALE;
    protected final String TRUE_ABBREVIATION = "T";
    protected Article mArticle;
    private int mCurrentPage = 0;

    /* access modifiers changed from: protected */
    public void parseActivity(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "activity");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("professional-activity")) {
                    parseProfessionalActivity(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseProfessionalActivity(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "professional-activity");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("title")) {
                    String innerXmlWithoutHtml = getInnerXmlWithoutHtml(xmlPullParser, "title");
                    if (Extensions.isStringNullOrEmpty(this.mArticle.title)) {
                        this.mArticle.title = innerXmlWithoutHtml;
                    }
                } else if (name.equals("release-date")) {
                    this.mArticle.releaseDate = parseDate(xmlPullParser, "release-date");
                } else if (name.equals("cme-flag")) {
                    this.mArticle.cmeFlag = getInnerXmlWithoutHtml(xmlPullParser, "cme-flag");
                } else if (name.equals("expiration-date")) {
                    this.mArticle.expirationDate = parseDate(xmlPullParser, "expiration-date");
                } else if (name.equals("contrib-group")) {
                    parseContribGroup(xmlPullParser);
                } else if (name.equals("eligibility-wrapper")) {
                    xmlPullParser = parseEligibilityWrapper(xmlPullParser, "eligibility-wrapper");
                } else if (name.equals("target-audience-stmt")) {
                    this.mArticle.targetAudienceStatement = parseHtmlParagraph(xmlPullParser, "target-audience-stmt");
                } else if (name.equals("goal-stmt")) {
                    this.mArticle.goalStatement = parseHtmlParagraph(xmlPullParser, "goal-stmt");
                } else if (name.equals("objective-stmt")) {
                    this.mArticle.objectiveStatement = parseObjectiveStatement(xmlPullParser, "objective-stmt");
                } else if (name.equals("instructions-for-credit")) {
                    this.mArticle.creditInstructions = parseInstructionsForCredit(xmlPullParser, "instructions-for-credit");
                } else if (name.equals("study-highlights")) {
                    this.mArticle.studyHighlights = new HtmlObject();
                    this.mArticle.studyHighlights.content = getInnerXml(xmlPullParser, "study-highlights");
                    if (this.mArticle.studyHighlights.content.contains("<ol>")) {
                        this.mArticle.studyHighlights.htmlType = "ol";
                    } else {
                        this.mArticle.studyHighlights.htmlType = "ul";
                    }
                } else if (name.equals("pearls-for-practice")) {
                    this.mArticle.pearlsForPractice = new HtmlObject();
                    this.mArticle.pearlsForPractice.content = getInnerXml(xmlPullParser, "pearls-for-practice");
                    if (this.mArticle.pearlsForPractice.content.contains("<ol>")) {
                        this.mArticle.pearlsForPractice.htmlType = "ol";
                    } else {
                        this.mArticle.pearlsForPractice.htmlType = "ul";
                    }
                } else if (name.equals("misc-provider-statement")) {
                    this.mArticle.miscProvider = parseMiscProviderStatement(xmlPullParser, "misc-provider-statement");
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void parseMetaSegVars(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "metasegvar");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("cg")) {
                    this.mArticle.contentGroup = getInnerXmlWithoutHtml(xmlPullParser, "cg");
                } else if (name.equals("bc")) {
                    this.mArticle.blockCode = getInnerXmlWithoutHtml(xmlPullParser, "bc").split("__");
                } else if (name.equals(AdContentData.LEAD_CONCEPT)) {
                    this.mArticle.leadConcept = getInnerXmlWithoutHtml(xmlPullParser, AdContentData.LEAD_CONCEPT);
                } else if (name.equals(AdContentData.LEAD_SPECIALITY)) {
                    this.mArticle.leadSpec = getInnerXmlWithoutHtml(xmlPullParser, AdContentData.LEAD_SPECIALITY);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseDocument(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        xmlPullParser.require(2, (String) null, "professional-article");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("legacy-id")) {
                    this.mArticle.id = getInnerXmlWithoutHtml(xmlPullParser, "legacy-id");
                } else if (name.equals("content-type")) {
                    this.mArticle.contentType = getInnerXmlWithoutHtml(xmlPullParser, "content-type");
                } else if (name.equals("site-on")) {
                    this.mArticle.siteOn = getInnerXml(xmlPullParser, "site-on");
                } else if (name.equals("article-misc")) {
                    parseArticleMisc(xmlPullParser);
                } else if (name.equals("suppress")) {
                    parserSuppressRules(xmlPullParser);
                } else if (name.equals("activity")) {
                    parseActivity(xmlPullParser);
                } else if (name.equals("qna-id")) {
                    this.mArticle.qnaId = getInnerXmlWithoutHtml(xmlPullParser, "qna-id");
                } else if (name.equals("metasegvar")) {
                    parseMetaSegVars(xmlPullParser);
                } else if (name.equals("supporter")) {
                    parseArticleSupports(xmlPullParser);
                } else if (name.equals("article")) {
                    parseArticle(xmlPullParser);
                } else if (name.equals("versions")) {
                    this.mArticle.versions = parseVersions(xmlPullParser);
                } else if (name.equals("id")) {
                    this.mArticle.assetId = getInnerXmlWithoutHtml(xmlPullParser, "id");
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void parserSuppressRules(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "suppress");
        while (true) {
            boolean z = false;
            while (true) {
                if (xmlPullParser.next() != 3) {
                    String name = xmlPullParser.getName();
                    if (name != null) {
                        if (name.equals("suppress-cme-link")) {
                            String innerXmlWithoutHtml = getInnerXmlWithoutHtml(xmlPullParser, "suppress-cme-link");
                            if (!innerXmlWithoutHtml.equals(MNetUser.FEMALE)) {
                                if (innerXmlWithoutHtml.equals("T")) {
                                    z = true;
                                }
                            }
                        } else {
                            skip(xmlPullParser);
                        }
                    }
                } else {
                    this.mArticle.suppressCmeLink = z;
                    return;
                }
            }
        }
    }

    private List<Version> parseVersions(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, (String) null, "versions");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("version")) {
                    String attributeValue = xmlPullParser.getAttributeValue("", "id");
                    if (attributeValue.equals("4")) {
                        String attributeValue2 = xmlPullParser.getAttributeValue("", "primary");
                        Version parseVersion = parseVersion(xmlPullParser, "dlSlides", "dlBtn");
                        if (parseVersion.buttonUrls != null && parseVersion.buttonUrls.size() > 0) {
                            this.mArticle.slidesDownloadUrl = parseVersion.buttonUrls.get(0);
                        }
                        if (parseVersion.media != null) {
                            if (!Extensions.isStringNullOrEmpty(attributeValue)) {
                                parseVersion.id = attributeValue;
                            }
                            if (attributeValue2 == null || !attributeValue2.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                                parseVersion.isPrimary = false;
                            } else {
                                parseVersion.isPrimary = true;
                            }
                            arrayList.add(parseVersion);
                        }
                    } else if (attributeValue.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                        String attributeValue3 = xmlPullParser.getAttributeValue("", "primary");
                        Version parseVersion2 = parseVersion(xmlPullParser, "downloadbtn", "downloadbtn_bg_pdf");
                        if (parseVersion2.buttonUrls != null) {
                            for (String next : parseVersion2.buttonUrls) {
                                String replace = next.toLowerCase().replace("-", "").replace("_", "");
                                if (!Extensions.isStringNullOrEmpty(replace)) {
                                    if (replace.contains("webreprint")) {
                                        this.mArticle.webReprintUrl = Extensions.getSubstring(next, "http", ContentParser.PDF);
                                    } else if (replace.contains("prescriptiontolearn")) {
                                        this.mArticle.prescriptionToLeanUrl = Extensions.getSubstring(next, "http", ContentParser.PDF);
                                    }
                                }
                            }
                        }
                        if (!Extensions.isStringNullOrEmpty(attributeValue)) {
                            parseVersion2.id = attributeValue;
                        }
                        if (attributeValue3 == null || !attributeValue3.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                            parseVersion2.isPrimary = false;
                        } else {
                            parseVersion2.isPrimary = true;
                        }
                        arrayList.add(parseVersion2);
                    } else {
                        skip(xmlPullParser);
                    }
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return arrayList;
    }

    private Version parseVersion(XmlPullParser xmlPullParser, String str, String str2) throws IOException, XmlPullParserException, IncompatibleArticleException {
        Version version = new Version();
        xmlPullParser.require(2, (String) null, "version");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("title")) {
                    version.title = getInnerXmlWithoutHtml(xmlPullParser, "title");
                } else if (name.equals("p")) {
                    version.buttonUrls = parseVersionButton(getInnerXml(xmlPullParser, "p"), str, str2);
                } else if (name.equals("media")) {
                    version.media = parseMedia(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return version;
    }

    private List<String> parseVersionButton(String str, String str2, String str3) throws IOException, XmlPullParserException, IncompatibleArticleException {
        XmlPullParser initializeXmlParser = initializeXmlParser("<p>" + str + "</p>");
        initializeXmlParser.require(2, (String) null, "p");
        while (initializeXmlParser.next() != 3) {
            String name = initializeXmlParser.getName();
            if (name != null) {
                if (name.equals("div")) {
                    String attributeValue = initializeXmlParser.getAttributeValue("", "id");
                    if (Extensions.isStringNullOrEmpty(attributeValue)) {
                        attributeValue = initializeXmlParser.getAttributeValue("", Name.LABEL);
                    }
                    if (attributeValue.equals(str2)) {
                        return parseButtonDownloadUrl(initializeXmlParser, str3);
                    }
                    skip(initializeXmlParser);
                } else if (!name.equals("input")) {
                    skip(initializeXmlParser);
                } else if (!initializeXmlParser.getAttributeValue("", "name").equals("cme-tv")) {
                    skip(initializeXmlParser);
                } else {
                    throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(3));
                }
            }
        }
        return null;
    }

    private List<String> parseButtonDownloadUrl(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "div");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("div")) {
                    String attributeValue = xmlPullParser.getAttributeValue("", "id");
                    if (Extensions.isStringNullOrEmpty(attributeValue)) {
                        attributeValue = xmlPullParser.getAttributeValue("", Name.LABEL);
                    }
                    if (Extensions.isStringNullOrEmpty(attributeValue)) {
                        skip(xmlPullParser);
                    } else if (attributeValue.equals(str)) {
                        return parseButtonDownloadDiv(xmlPullParser);
                    } else {
                        skip(xmlPullParser);
                    }
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return null;
    }

    private List<String> parseButtonDownloadDiv(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, "", "div");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals(Constants.APPBOY_PUSH_CONTENT_KEY)) {
                    String attributeValue = xmlPullParser.getAttributeValue("", "href");
                    getInnerXml(xmlPullParser, Constants.APPBOY_PUSH_CONTENT_KEY);
                    if (!Extensions.isStringNullOrEmpty(attributeValue)) {
                        arrayList.add(attributeValue);
                    }
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return arrayList;
    }

    private Media parseMedia(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        Media media = new Media();
        xmlPullParser.require(2, (String) null, "media");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("title")) {
                    media.title = getInnerXmlWithoutHtml(xmlPullParser, "title");
                } else if (name.equals("media-format")) {
                    media.format = getInnerXmlWithoutHtml(xmlPullParser, "media-format");
                } else if (name.equals("media-location")) {
                    media.location = getInnerXmlWithoutHtml(xmlPullParser, "media-location");
                } else if (name.equals("video-rss-media-location")) {
                    media.videoRssLocation = getInnerXmlWithoutHtml(xmlPullParser, "video-rss-media-location");
                } else if (name.equals("audio-rss-media-location")) {
                    media.audioRssLocation = getInnerXmlWithoutHtml(xmlPullParser, "audio-rss-media-location");
                } else if (name.equals("duration")) {
                    String innerXmlWithoutHtml = getInnerXmlWithoutHtml(xmlPullParser, "duration");
                    if (Extensions.tryParseFloat(innerXmlWithoutHtml)) {
                        media.duration = Float.parseFloat(innerXmlWithoutHtml);
                    }
                } else if (name.equals("audio-download-bytes")) {
                    String innerXmlWithoutHtml2 = getInnerXmlWithoutHtml(xmlPullParser, "audio-download-bytes");
                    if (Extensions.tryParseInteger(innerXmlWithoutHtml2)) {
                        media.audioBytesDownloaded = Integer.parseInt(innerXmlWithoutHtml2);
                    }
                } else if (name.equals("video-download-bytes")) {
                    String innerXmlWithoutHtml3 = getInnerXmlWithoutHtml(xmlPullParser, "video-download-bytes");
                    if (Extensions.tryParseInteger(innerXmlWithoutHtml3)) {
                        media.videoBytesDownloaded = Integer.parseInt(innerXmlWithoutHtml3);
                    }
                } else if (name.equals(GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH)) {
                    String innerXmlWithoutHtml4 = getInnerXmlWithoutHtml(xmlPullParser, GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH);
                    if (Extensions.tryParseInteger(innerXmlWithoutHtml4)) {
                        media.width = Integer.parseInt(innerXmlWithoutHtml4);
                    }
                } else if (name.equals(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT)) {
                    String innerXmlWithoutHtml5 = getInnerXmlWithoutHtml(xmlPullParser, GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT);
                    if (Extensions.tryParseInteger(innerXmlWithoutHtml5)) {
                        media.height = Integer.parseInt(innerXmlWithoutHtml5);
                    }
                } else if (name.equals("base-folder")) {
                    media.baseFolder = getInnerXmlWithoutHtml(xmlPullParser, "base-folder");
                } else if (name.equals("autoplay")) {
                    String innerXmlWithoutHtml6 = getInnerXmlWithoutHtml(xmlPullParser, "autoplay");
                    if (!Extensions.isStringNullOrEmpty(innerXmlWithoutHtml6)) {
                        if (innerXmlWithoutHtml6.equalsIgnoreCase(MNetUser.FEMALE)) {
                            media.autoPlay = false;
                        } else if (innerXmlWithoutHtml6.equalsIgnoreCase("T")) {
                            media.autoPlay = true;
                        }
                    }
                } else if (name.equals("swf-location")) {
                    media.swfLocation = getInnerXmlWithoutHtml(xmlPullParser, "swf-location");
                } else if (name.equals("config-path")) {
                    media.configPath = getInnerXmlWithoutHtml(xmlPullParser, "config-path");
                    this.mArticle.videoConfigURL = media.configPath;
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return media;
    }

    /* access modifiers changed from: protected */
    public void parseArticleSupports(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "supporter");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("supprtr-grant-badge")) {
                    String innerXml = getInnerXml(xmlPullParser, "supprtr-grant-badge");
                    if (!Extensions.isStringNullOrEmpty(innerXml)) {
                        this.mArticle.supportGrantBadges.add(getUrlFromHtmlImageLink(innerXml));
                    }
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getUrlFromHtmlImageLink(String str) {
        int indexOf = str.indexOf("<img src=\"") + 10;
        String replace = str.substring(indexOf, str.indexOf("\"", indexOf + 1)).replace("/webmd/professional_assets/medscape/images/", "");
        return "https://img.medscape.com/" + replace;
    }

    /* access modifiers changed from: protected */
    public void parseArticleMisc(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "article-misc");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("contrbtr-byline")) {
                    this.mArticle.byLine = getInnerXmlWithoutHtml(xmlPullParser, "contrbtr-byline");
                } else if (name.equals("meta-description")) {
                    this.mArticle.metaDescription = getInnerXmlWithoutHtml(xmlPullParser, "meta-description");
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseArticle(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        xmlPullParser.require(2, (String) null, "article");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("front")) {
                    parseFront(xmlPullParser);
                } else if (name.equals("body")) {
                    parseBody(xmlPullParser);
                } else if (name.equals("back")) {
                    parseBack(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void parseBack(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        xmlPullParser.require(2, (String) null, "back");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("ref-list")) {
                    parseReferenceList(xmlPullParser);
                } else if (name.equals("app-group")) {
                    parseAppGroup(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void parseAppGroup(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "app-group");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals(AdParameterKeys.SECTION_ID)) {
                    parserApp(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void parserApp(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, AdParameterKeys.SECTION_ID);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals(AdParameterKeys.SECONDARY_IDS)) {
                    parserBackSection(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void parserBackSection(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, AdParameterKeys.SECONDARY_IDS);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (!name.equals(AdParameterKeys.SECONDARY_IDS)) {
                    skip(xmlPullParser);
                } else if (xmlPullParser.getAttributeValue("", "sec-type").equals("Sidebar")) {
                    parseSideBar(xmlPullParser, false);
                } else {
                    parserBackSection(xmlPullParser);
                }
            }
        }
    }

    private void parseSideBar(XmlPullParser xmlPullParser, boolean z) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, AdParameterKeys.SECONDARY_IDS);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("title")) {
                    String innerXmlWithoutHtml = getInnerXmlWithoutHtml(xmlPullParser, "title");
                    if (!Extensions.isStringNullOrEmpty(innerXmlWithoutHtml)) {
                        if (innerXmlWithoutHtml.equals("Abbreviations")) {
                            z = true;
                        }
                    }
                } else if (name.equals(AdParameterKeys.SECONDARY_IDS)) {
                    parseSideBar(xmlPullParser, z);
                } else if (!name.equals("p")) {
                    skip(xmlPullParser);
                } else if (!z) {
                    parseContentWrappedInParagraph(xmlPullParser);
                } else {
                    String innerXml = getInnerXml(xmlPullParser, "p");
                    if (!Extensions.isStringNullOrEmpty(innerXml)) {
                        this.mArticle.abbreviations = innerXml.split("<break ></break>");
                    }
                }
                z = false;
            }
        }
    }

    private void parseContentWrappedInParagraph(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "p");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("iframe")) {
                    String attributeValue = xmlPullParser.getAttributeValue("", "src");
                    if (!Extensions.isStringNullOrEmpty(attributeValue)) {
                        if (attributeValue.contains("WebReprint.pdf") || attributeValue.contains("webreprint.pdf")) {
                            this.mArticle.webReprintUrl = attributeValue;
                        } else if (attributeValue.contains("Prescription_to_Learn.pdf")) {
                            this.mArticle.prescriptionToLeanUrl = attributeValue;
                        }
                        getInnerXml(xmlPullParser, "iframe");
                    }
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void parseReferenceList(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (this.mArticle.references == null) {
            this.mArticle.references = new References();
        }
        xmlPullParser.require(2, (String) null, "ref-list");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals(com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST)) {
                    String attributeValue = xmlPullParser.getAttributeValue("", com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST_TYPE_KEY);
                    if (!Extensions.isStringNullOrEmpty(attributeValue)) {
                        this.mArticle.references.displayType = attributeValue;
                    }
                    this.mArticle.references.content = parseList(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private List<String> parseList(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, (String) null, com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals(com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST_ITEM)) {
                    String innerXml = getInnerXml(xmlPullParser, com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST_ITEM);
                    if (!Extensions.isStringNullOrEmpty(innerXml) && !innerXml.equals("<p ></p>")) {
                        arrayList.add(innerXml);
                    }
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void parseFront(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "front");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("article-meta")) {
                    parseArticleMeta(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseArticleMeta(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "article-meta");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("contrib-group")) {
                    parseContribGroup(xmlPullParser);
                } else if (name.equals("title-group")) {
                    parseTitleGroup(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseTitleGroup(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "title-group");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("article-title")) {
                    this.mArticle.title = getInnerXmlWithoutHtml(xmlPullParser, "article-title");
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseBody(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        xmlPullParser.require(2, (String) null, "body");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (!name.equals(AdParameterKeys.SECONDARY_IDS)) {
                    skip(xmlPullParser);
                } else if (xmlPullParser.getAttributeValue("", "sec-type").equals(PlaceFields.PAGE)) {
                    List<Section> parseDefaultSection = parseDefaultSection(xmlPullParser);
                    ArticlePage articlePage = new ArticlePage();
                    articlePage.sections = parseDefaultSection;
                    if (!isPageEmpty(articlePage)) {
                        this.mArticle.pages.add(articlePage);
                        this.mCurrentPage++;
                    }
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        if (this.mArticle.pages.size() == 0) {
            throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(5));
        }
    }

    private boolean isPageEmpty(ArticlePage articlePage) {
        for (Section next : articlePage.sections) {
            if (!Extensions.isStringNullOrEmpty(next.title)) {
                return false;
            }
            Iterator<SubSection> it = next.subsections.iterator();
            while (true) {
                if (it.hasNext()) {
                    Iterator<HtmlObject> it2 = it.next().content.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            HtmlObject next2 = it2.next();
                            if (!Extensions.isStringNullOrEmpty(next2.content) && !next2.content.contains("cmeinfoaddplaceholder")) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public List<Section> parseDefaultSection(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        List<Section> arrayList = new ArrayList<>();
        xmlPullParser.require(2, (String) null, AdParameterKeys.SECONDARY_IDS);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (!name.equals(AdParameterKeys.SECONDARY_IDS)) {
                    skip(xmlPullParser);
                } else if (xmlPullParser.getAttributeValue("", "sec-type").equals("Default")) {
                    arrayList = parseSections(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<Section> parseSections(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, (String) null, AdParameterKeys.SECONDARY_IDS);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (!name.equals(AdParameterKeys.SECONDARY_IDS)) {
                    skip(xmlPullParser);
                } else if (xmlPullParser.getAttributeValue("", "sec-type").equals("section")) {
                    arrayList.add(parseSection(xmlPullParser));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public Section parseSection(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        Slide parseSlide;
        Section section = new Section();
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, (String) null, AdParameterKeys.SECONDARY_IDS);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("script")) {
                    throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(0));
                } else if (name.equals("title")) {
                    String innerXmlWithoutHtml = getInnerXmlWithoutHtml(xmlPullParser, "title");
                    if (!shouldHideNativeContent(innerXmlWithoutHtml)) {
                        section.title = innerXmlWithoutHtml;
                    }
                } else if (name.equals(AdParameterKeys.SECONDARY_IDS)) {
                    if (xmlPullParser.getAttributeValue("", "sec-type").equals("subsection")) {
                        SubSection subSection = new SubSection();
                        subSection.content = parseSubsection(xmlPullParser);
                        section.subsections.add(subSection);
                    } else {
                        skip(xmlPullParser);
                    }
                } else if (name.equals("p")) {
                    HtmlObject htmlObject = new HtmlObject();
                    String innerXml = getInnerXml(xmlPullParser, "p");
                    if (innerXml.contains("pearls_for_practice")) {
                        htmlObject.htmlType = "pearls_for_practice";
                        htmlObject.content = "<placeholder></placeholder>";
                    } else if (innerXml.contains("study_highlights")) {
                        htmlObject.htmlType = "study_highlights";
                        htmlObject.content = "<placeholder></placeholder>";
                    } else if (innerXml.contains("<embed")) {
                        parseEmbeddedVideo(innerXml);
                    } else if (!shouldHideNativeContent(innerXml)) {
                        htmlObject.htmlType = "p";
                        htmlObject.content = innerXml;
                    }
                    arrayList.add(htmlObject);
                } else if (name.equals(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE)) {
                    HtmlObject htmlObject2 = new HtmlObject();
                    htmlObject2.htmlType = com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE;
                    htmlObject2.content = "<table>" + getInnerXml(xmlPullParser, com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE) + "</table>";
                    arrayList.add(htmlObject2);
                } else if (name.equals("div")) {
                    String attributeValue = xmlPullParser.getAttributeValue("", "id");
                    if (Extensions.isStringNullOrEmpty(attributeValue)) {
                        skip(xmlPullParser);
                    } else if (attributeValue.equals("prgteaser")) {
                        throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(1));
                    } else if (!attributeValue.equalsIgnoreCase("simLanding")) {
                        skip(xmlPullParser);
                    } else {
                        throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(2));
                    }
                } else if (name.equals(com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST)) {
                    HtmlObject htmlObject3 = new HtmlObject();
                    if (xmlPullParser.getAttributeValue("", com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST_TYPE_KEY).equals("order")) {
                        htmlObject3.htmlType = "ol";
                        htmlObject3.content = "<list>" + getInnerXml(xmlPullParser, com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST) + "</list>";
                    } else {
                        htmlObject3.htmlType = "ul";
                        htmlObject3.content = "<list>" + getInnerXml(xmlPullParser, com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST) + "</list>";
                    }
                    arrayList.add(htmlObject3);
                } else if (name.equals(AdParameterKeys.SECONDARY_IDS)) {
                    String attributeValue2 = xmlPullParser.getAttributeValue((String) null, "sec-type");
                    if (!(attributeValue2 == null || !attributeValue2.equals(com.webmd.wbmdcmepulse.models.utils.Constants.SECTION_TYPE_SLIDE) || (parseSlide = parseSlide(xmlPullParser)) == null)) {
                        if (parseSlide.longDescription != null && parseSlide.longDescription.size() > 0) {
                            arrayList.addAll(parseSlide.longDescription);
                        }
                        if (!Extensions.isStringNullOrEmpty(parseSlide.objectId)) {
                            HtmlObject htmlObject4 = new HtmlObject();
                            htmlObject4.htmlType = com.webmd.wbmdcmepulse.models.utils.Constants.INLINE_ACTIVITY_TEST;
                            htmlObject4.content = parseSlide.objectId;
                            arrayList.add(htmlObject4);
                        }
                    }
                } else if (name.equals("supplementary-material")) {
                    String parseObjectId = parseObjectId(xmlPullParser);
                    if (!Extensions.isStringNullOrEmpty(parseObjectId)) {
                        HtmlObject htmlObject5 = new HtmlObject();
                        htmlObject5.htmlType = com.webmd.wbmdcmepulse.models.utils.Constants.INLINE_ACTIVITY_TEST;
                        htmlObject5.content = parseObjectId;
                        QuestionPageMapItem questionPageMapItem = new QuestionPageMapItem();
                        questionPageMapItem.pageNumber = Integer.toString(this.mCurrentPage);
                        questionPageMapItem.testId = parseObjectId;
                        this.mArticle.questionPageMap.add(questionPageMapItem);
                        arrayList.add(htmlObject5);
                    } else {
                        skip(xmlPullParser);
                    }
                } else if (!name.equals("")) {
                    skip(xmlPullParser);
                }
            }
        }
        if (arrayList.size() > 0) {
            SubSection subSection2 = new SubSection();
            subSection2.content = arrayList;
            section.subsections.add(subSection2);
        }
        return section;
    }

    private void parseEmbeddedVideo(String str) throws IncompatibleArticleException, IOException, XmlPullParserException {
        if (Extensions.isStringNullOrEmpty(this.mArticle.videoUrl)) {
            XmlPullParser initializeXmlParser = initializeXmlParser("<embed-video>" + str + "</embed-video>");
            initializeXmlParser.require(2, (String) null, "embed-video");
            while (initializeXmlParser.next() != 3) {
                String name = initializeXmlParser.getName();
                if (name == null || !name.equals("embed")) {
                    skip(initializeXmlParser);
                } else {
                    String attributeValue = initializeXmlParser.getAttributeValue("", "videoRss");
                    if (!Extensions.isStringNullOrEmpty(attributeValue)) {
                        this.mArticle.videoUrl = attributeValue;
                    }
                }
            }
            return;
        }
        throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(6));
    }

    private void parseVideoConfig(String str) throws IOException, XmlPullParserException {
        XmlPullParser initializeXmlParser = initializeXmlParser(str);
        initializeXmlParser.require(2, (String) null, "div");
        while (initializeXmlParser.next() != 3) {
            try {
                String name = initializeXmlParser.getName();
                if (name == null || !name.equals("div")) {
                    skip(initializeXmlParser);
                } else {
                    String attributeValue = initializeXmlParser.getAttributeValue("", "data-config");
                    if (!Extensions.isStringNullOrEmpty(attributeValue) && attributeValue.endsWith(".json")) {
                        this.mArticle.videoConfigURL = attributeValue;
                    }
                }
            } catch (Throwable unused) {
                return;
            }
        }
    }

    private String parseDownloadPdf(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        List<String> parseButtonDownloadUrl = parseButtonDownloadUrl(xmlPullParser, "downloadbtn_bg_pdf");
        if (parseButtonDownloadUrl == null || parseButtonDownloadUrl.size() <= 0) {
            return null;
        }
        String str = parseButtonDownloadUrl.get(0);
        return str.contains("=") ? str.substring(str.lastIndexOf("=") + 1) : str;
    }

    /* access modifiers changed from: protected */
    public List<HtmlObject> parseSubsection(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, IncompatibleArticleException {
        Slide parseSlide;
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, (String) null, AdParameterKeys.SECONDARY_IDS);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("script")) {
                    throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(0));
                } else if (name.equals("title")) {
                    HtmlObject htmlObject = new HtmlObject();
                    String innerXml = getInnerXml(xmlPullParser, "title");
                    htmlObject.content = Html.fromHtml(innerXml).toString();
                    if (innerXml.contains("div")) {
                        this.mArticle.prescriptionToLeanUrl = parseDownloadPdf(initializeXmlParser(innerXml));
                        htmlObject.htmlType = "button";
                    } else {
                        htmlObject.htmlType = "h3";
                    }
                    if (Extensions.isStringNullOrEmpty(htmlObject.content)) {
                        continue;
                    } else if (htmlObject.content.contains("advanceplayer")) {
                        throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(4));
                    } else if (!shouldHideNativeContent(htmlObject.content)) {
                        arrayList.add(htmlObject);
                    }
                } else if (name.equals("div")) {
                    String innerXml2 = getInnerXml(xmlPullParser, "div");
                    if (innerXml2.contains("data-config")) {
                        String attributeValue = initializeXmlParser(innerXml2).getAttributeValue("", "data-config");
                        if (!Extensions.isStringNullOrEmpty(attributeValue) && attributeValue.endsWith(".json")) {
                            this.mArticle.videoConfigURL = attributeValue;
                        }
                    } else {
                        String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
                        if (Extensions.isStringNullOrEmpty(attributeValue2)) {
                            skip(xmlPullParser);
                        } else if (attributeValue2.equals("prgteaser")) {
                            throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(1));
                        } else if (!attributeValue2.equalsIgnoreCase("simLanding")) {
                            skip(xmlPullParser);
                        } else {
                            throw new IncompatibleArticleException(IncompatibleArticleException.getErrorMessage(2));
                        }
                    }
                } else if (name.equals("p")) {
                    String innerXml3 = getInnerXml(xmlPullParser, "p");
                    if (innerXml3.contains("<embed")) {
                        parseEmbeddedVideo(innerXml3);
                    } else if (innerXml3.contains("data-config")) {
                        parseVideoConfig(innerXml3);
                    } else if (!shouldHideNativeContent(innerXml3)) {
                        HtmlObject htmlObject2 = new HtmlObject();
                        htmlObject2.htmlType = "p";
                        htmlObject2.content = innerXml3;
                        arrayList.add(htmlObject2);
                    }
                } else if (name.equals(com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE)) {
                    HtmlObject htmlObject3 = new HtmlObject();
                    htmlObject3.htmlType = com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE;
                    htmlObject3.content = "<table>" + getInnerXml(xmlPullParser, com.webmd.wbmdcmepulse.models.utils.Constants.HTML_TYPE_TABLE) + "</table>";
                    arrayList.add(htmlObject3);
                } else if (name.equals(com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST)) {
                    HtmlObject htmlObject4 = new HtmlObject();
                    if (xmlPullParser.getAttributeValue("", com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST_TYPE_KEY).equals("order")) {
                        htmlObject4.htmlType = "ol";
                        htmlObject4.content = "<list>" + getInnerXml(xmlPullParser, com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST) + "</list>";
                    } else {
                        htmlObject4.htmlType = "ul";
                        htmlObject4.content = "<list>" + getInnerXml(xmlPullParser, com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST) + "</list>";
                    }
                    arrayList.add(htmlObject4);
                } else if (name.equals(AdParameterKeys.SECONDARY_IDS)) {
                    String attributeValue3 = xmlPullParser.getAttributeValue((String) null, "sec-type");
                    if (!(attributeValue3 == null || !attributeValue3.equals(com.webmd.wbmdcmepulse.models.utils.Constants.SECTION_TYPE_SLIDE) || (parseSlide = parseSlide(xmlPullParser)) == null)) {
                        if (parseSlide.longDescription != null && parseSlide.longDescription.size() > 0) {
                            arrayList.addAll(parseSlide.longDescription);
                        }
                        if (!Extensions.isStringNullOrEmpty(parseSlide.objectId)) {
                            HtmlObject htmlObject5 = new HtmlObject();
                            htmlObject5.htmlType = com.webmd.wbmdcmepulse.models.utils.Constants.INLINE_ACTIVITY_TEST;
                            htmlObject5.content = parseSlide.objectId;
                            QuestionPageMapItem questionPageMapItem = new QuestionPageMapItem();
                            questionPageMapItem.pageNumber = Integer.toString(this.mCurrentPage);
                            questionPageMapItem.testId = parseSlide.objectId;
                            this.mArticle.questionPageMap.add(questionPageMapItem);
                            arrayList.add(htmlObject5);
                        }
                    }
                } else if (name.equals("supplementary-material")) {
                    String parseObjectId = parseObjectId(xmlPullParser);
                    if (!Extensions.isStringNullOrEmpty(parseObjectId)) {
                        HtmlObject htmlObject6 = new HtmlObject();
                        htmlObject6.htmlType = com.webmd.wbmdcmepulse.models.utils.Constants.INLINE_ACTIVITY_TEST;
                        htmlObject6.content = parseObjectId;
                        QuestionPageMapItem questionPageMapItem2 = new QuestionPageMapItem();
                        questionPageMapItem2.pageNumber = Integer.toString(this.mCurrentPage);
                        questionPageMapItem2.testId = parseObjectId;
                        this.mArticle.questionPageMap.add(questionPageMapItem2);
                        arrayList.add(htmlObject6);
                    } else {
                        skip(xmlPullParser);
                    }
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return arrayList;
    }

    private boolean shouldHideNativeContent(String str) {
        String trim = str.toLowerCase().trim();
        if (trim.contains("iframe") || trim.contains("web reprint")) {
            return true;
        }
        if (!trim.contains("div") && !trim.contains("a href")) {
            return false;
        }
        if ((!trim.contains("downloadbtn") || (!trim.contains("prescription to learn") && !trim.contains("web reprint") && !trim.contains("for your patient"))) && !trim.contains("abbreviations") && !trim.contains("translations")) {
            return false;
        }
        return true;
    }

    private Slide parseSlide(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (this.mArticle.slides == null) {
            this.mArticle.slides = new ArrayList<>();
        }
        Slide slide = null;
        xmlPullParser.require(2, (String) null, AdParameterKeys.SECONDARY_IDS);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("fig")) {
                    slide = parserSlideFig(xmlPullParser);
                } else if (name.equals("supplementary-material")) {
                    if (slide == null) {
                        slide = new Slide();
                    }
                    slide.objectId = parseObjectId(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        if (slide != null && !Extensions.isStringNullOrEmpty(slide.graphicUrl)) {
            this.mArticle.slides.add(slide);
        }
        return slide;
    }

    private String parseObjectId(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str = null;
        xmlPullParser.require(2, (String) null, "supplementary-material");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("object-id")) {
                    str = getInnerXmlWithoutHtml(xmlPullParser, "object-id");
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return str;
    }

    private Slide parserSlideFig(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        Slide slide = new Slide();
        xmlPullParser.require(2, (String) null, "fig");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals(Constants.ScionAnalytics.PARAM_LABEL)) {
                    slide.label = getInnerXmlWithoutHtml(xmlPullParser, Constants.ScionAnalytics.PARAM_LABEL);
                } else if (name.equals(ShareConstants.FEED_CAPTION_PARAM)) {
                    slide.caption = getInnerXmlWithoutHtml(xmlPullParser, ShareConstants.FEED_CAPTION_PARAM);
                } else if (name.equals("graphic")) {
                    String attributeValue = xmlPullParser.getAttributeValue("", "xlink:href");
                    if (attributeValue != null) {
                        slide.graphicUrl = attributeValue;
                        if (slide.graphicUrl.contains("/article/")) {
                            slide.graphicUrl = slide.graphicUrl.replace("/content", "");
                        }
                        slide.graphicUrl = Utilities.getImageUrl(slide.graphicUrl);
                    }
                    skip(xmlPullParser);
                } else if (name.equals(GraphicParser.XML_ATTRIBUTE_VALUE_ALT_TEXT)) {
                    slide.altText = getInnerXmlWithoutHtml(xmlPullParser, GraphicParser.XML_ATTRIBUTE_VALUE_ALT_TEXT);
                } else if (name.equals("long-desc")) {
                    slide.longDescription = parserLongDescription(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return slide;
    }

    private List<HtmlObject> parserLongDescription(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, (String) null, "long-desc");
        while (xmlPullParser.next() != 3) {
            HtmlObject htmlObject = new HtmlObject();
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("h3")) {
                    htmlObject.htmlType = "h3";
                    htmlObject.content = getInnerXml(xmlPullParser, "h3");
                } else if (name.equals("p")) {
                    htmlObject.htmlType = "p";
                    htmlObject.content = getInnerXml(xmlPullParser, "p");
                } else if (name.equals(com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST)) {
                    String attributeValue = xmlPullParser.getAttributeValue((String) null, com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST_TYPE_KEY);
                    if (Extensions.isStringNullOrEmpty(attributeValue) || (!attributeValue.equals("order") && !attributeValue.equals(com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST_TYPE_VALUE_BULLET))) {
                        htmlObject.htmlType = "ul";
                    } else {
                        htmlObject.htmlType = "ol";
                    }
                    htmlObject.content = getInnerXml(xmlPullParser, com.webmd.wbmdcmepulse.models.utils.Constants.XML_LIST);
                } else if (name.equals("ol")) {
                    htmlObject.htmlType = "ol";
                    htmlObject.content = getInnerXml(xmlPullParser, "ol");
                } else if (name.equals("ul")) {
                    htmlObject.htmlType = "ul";
                    htmlObject.content = getInnerXml(xmlPullParser, "ol");
                } else {
                    skip(xmlPullParser);
                }
            }
            if (htmlObject.content != null) {
                arrayList.add(htmlObject);
            }
        }
        return arrayList;
    }

    private CreditInstructions parseInstructionsForCredit(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        CreditInstructions creditInstructions = new CreditInstructions();
        creditInstructions.Paragraphs = new LinkedList();
        xmlPullParser.require(2, (String) null, str);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("p")) {
                    creditInstructions.Paragraphs.add(getInnerXml(xmlPullParser, "p"));
                } else if (name.equals("ol")) {
                    creditInstructions.InstructionList = parseHtmlLists(xmlPullParser, "ol");
                } else if (name.equals("ul")) {
                    creditInstructions.InstructionList = parseHtmlLists(xmlPullParser, "ul");
                }
            }
        }
        return creditInstructions;
    }

    private List<HtmlObject> parseObjectiveStatement(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, (String) null, str);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("p")) {
                    HtmlObject htmlObject = new HtmlObject();
                    htmlObject.htmlType = "p";
                    htmlObject.content = getInnerXml(xmlPullParser, "p");
                    arrayList.add(htmlObject);
                } else if (name.equals("ol")) {
                    HtmlObject htmlObject2 = new HtmlObject();
                    htmlObject2.htmlType = "ol";
                    htmlObject2.content = getInnerXml(xmlPullParser, "ol");
                    arrayList.add(htmlObject2);
                } else if (name.equals("ul")) {
                    HtmlObject htmlObject3 = new HtmlObject();
                    htmlObject3.htmlType = "ul";
                    htmlObject3.content = getInnerXml(xmlPullParser, "ul");
                    arrayList.add(htmlObject3);
                }
            }
        }
        return arrayList;
    }

    private List<String> parseHtmlLists(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        LinkedList linkedList = new LinkedList();
        xmlPullParser.require(2, (String) null, str);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null && name.equals("li")) {
                linkedList.add(getInnerXmlWithoutHtml(xmlPullParser, "li"));
            }
        }
        return linkedList;
    }

    private String parseHtmlParagraph(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, str);
        String str2 = "";
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null && name.equals("p")) {
                str2 = getInnerXmlWithoutHtml(xmlPullParser, "p");
            }
        }
        return str2;
    }

    private XmlPullParser parseEligibilityWrapper(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        LinkedList linkedList = new LinkedList();
        xmlPullParser.require(2, (String) null, str);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("eligibility")) {
                    linkedList.add(parseEligibility(xmlPullParser, "eligibility"));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        this.mArticle.eligibilities = linkedList;
        return xmlPullParser;
    }

    private Eligibility parseEligibility(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        Eligibility eligibility = new Eligibility();
        eligibility.Providers = new ArrayList();
        eligibility.Accreditors = new ArrayList();
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        if (attributeValue != null && Extensions.tryParseInteger(attributeValue)) {
            eligibility.type = Integer.parseInt(attributeValue);
        }
        xmlPullParser.require(2, (String) null, str);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("name")) {
                    eligibility.Name = getInnerXmlWithoutHtml(xmlPullParser, "name");
                }
                if (name.equals("provider")) {
                    eligibility.Providers.add(parseProvider(xmlPullParser, "provider"));
                } else if (name.equals("max-credits")) {
                    eligibility.MaxCredits = Float.parseFloat(getInnerXmlWithoutHtml(xmlPullParser, "max-credits"));
                } else if (name.equals("cme-us")) {
                    eligibility.CmeUs = getInnerXmlWithoutHtml(xmlPullParser, "cme-us");
                } else if (name.equals("credit-allocation")) {
                    eligibility.CreditAllocation = getInnerXmlWithoutHtml(xmlPullParser, "credit-allocation");
                } else if (name.equals("acpe")) {
                    eligibility.CreditAllocation = getInnerXmlWithoutHtml(xmlPullParser, "acpe");
                } else if (name.equals("accreditor")) {
                    String attributeValue2 = xmlPullParser.getAttributeValue("", "rank");
                    if (!Extensions.isStringNullOrEmpty(attributeValue2)) {
                        Accreditor parseAccreditor = parseAccreditor(xmlPullParser, "accreditor");
                        parseAccreditor.rank = attributeValue2;
                        eligibility.Accreditors.add(parseAccreditor);
                    } else {
                        skip(xmlPullParser);
                    }
                } else if (name.equals("credit-statement")) {
                    eligibility.CreditStatement = getInnerXml(xmlPullParser, "credit-statement");
                } else if (name.equals("passing-score")) {
                    String innerXmlWithoutHtml = getInnerXmlWithoutHtml(xmlPullParser, "passing-score");
                    if (Extensions.tryParseDouble(innerXmlWithoutHtml)) {
                        eligibility.passingScore = Double.parseDouble(innerXmlWithoutHtml);
                    } else {
                        eligibility.passingScore = -1.0d;
                    }
                } else if (name.equals("eval-required")) {
                    String innerXmlWithoutHtml2 = getInnerXmlWithoutHtml(xmlPullParser, "eval-required");
                    if (Extensions.isStringNullOrEmpty(innerXmlWithoutHtml2) || !innerXmlWithoutHtml2.equals("T")) {
                        eligibility.isEvalRequired = false;
                    } else {
                        eligibility.isEvalRequired = true;
                    }
                } else if (name.equals("accrd-stmnt")) {
                    eligibility.AccreditationStatement = getInnerXml(xmlPullParser, "accrd-stmnt");
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return eligibility;
    }

    private Accreditor parseAccreditor(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        Accreditor accreditor = new Accreditor();
        xmlPullParser.require(2, (String) null, str);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("id")) {
                    accreditor.id = getInnerXmlWithoutHtml(xmlPullParser, "id");
                } else if (name.equals("elig-audience")) {
                    accreditor.eligbleAudience = getInnerXmlWithoutHtml(xmlPullParser, "elig-audience");
                } else if (name.equals("credit-description")) {
                    accreditor.creditDescription = getInnerXmlWithoutHtml(xmlPullParser, "credit-description");
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return accreditor;
    }

    private Provider parseProvider(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        Provider provider = new Provider();
        provider.ContactInfo = new HashMap<>();
        xmlPullParser.require(2, (String) null, str);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("id")) {
                    provider.Id = Integer.parseInt(getInnerXmlWithoutHtml(xmlPullParser, "id"));
                } else if (name.equals("name")) {
                    provider.Name = getInnerXmlWithoutHtml(xmlPullParser, "name");
                } else if (name.equals("prvdr-logo")) {
                    provider.LogoUri = getInnerXmlWithoutHtml(xmlPullParser, "prvdr-logo");
                } else if (name.equals("cme_cntc_info")) {
                    provider.ContactInfo.put(FeedConstants.CME_ITEM, getInnerXml(xmlPullParser, "cme_cntc_info"));
                } else if (name.equals("nurse_cntc_info")) {
                    provider.ContactInfo.put("nurse", getInnerXml(xmlPullParser, "nurse_cntc_info"));
                } else if (name.equals("pharma_cntc_info")) {
                    provider.ContactInfo.put("pharma", getInnerXml(xmlPullParser, "pharma_cntc_info"));
                } else if (name.equals("psych_cntc_info")) {
                    provider.ContactInfo.put("psych", getInnerXml(xmlPullParser, "psych_cntc_info"));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return provider;
    }

    /* access modifiers changed from: protected */
    public XmlPullParser parseContribGroup(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, "contrib-group");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (!xmlPullParser.getName().equals(CONTRIB_NODE_NAME)) {
                    skip(xmlPullParser);
                } else if (xmlPullParser.getAttributeValue(0) != null) {
                    this.mArticle.contributors.add(parseContributor(xmlPullParser, CONTRIB_NODE_NAME));
                }
            }
        }
        return xmlPullParser;
    }

    private Contributor parseContributor(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        Contributor contributor = new Contributor();
        contributor.comments = new LinkedList();
        xmlPullParser.require(2, (String) null, str);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("name")) {
                    contributor = parseContributor(xmlPullParser, "name");
                } else if (name.equals("surname")) {
                    contributor.name = getInnerXmlWithoutHtml(xmlPullParser, "surname");
                } else if (name.equals("role")) {
                    contributor.contribType = getInnerXmlWithoutHtml(xmlPullParser, "role");
                } else if (name.equals("author-comment")) {
                    contributor.comments.add(parseContributorComment(xmlPullParser));
                } else if (name.equals("ext-link")) {
                    contributor.image = getInnerXml(xmlPullParser, "ext-link");
                    contributor.image = Utilities.getImageUrl(xmlPullParser.getAttributeValue("", "xlink:href"));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return contributor;
    }

    private ContributorComment parseContributorComment(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        ContributorComment contributorComment = new ContributorComment();
        xmlPullParser.require(2, (String) null, "author-comment");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("title")) {
                    contributorComment.title = getInnerXmlWithoutHtml(xmlPullParser, "title");
                } else if (name.equals("p")) {
                    contributorComment.body = getInnerXmlWithoutHtml(xmlPullParser, "p");
                }
            }
        }
        return contributorComment;
    }

    /* access modifiers changed from: protected */
    public void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() == 2) {
            int i = 1;
            while (i != 0) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    i++;
                } else if (next == 3) {
                    i--;
                }
            }
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: protected */
    public XmlPullParser initializeXmlParser(String str) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        newPullParser.setInput(getInputStream(str), (String) null);
        newPullParser.nextTag();
        return newPullParser;
    }

    private InputStream getInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
    }

    private boolean isCurrentNodeNext(String[] strArr, String str, int i) {
        return str.equals(strArr[i + 1]);
    }

    private boolean isLastNodeNext(String[] strArr, int i) {
        return strArr.length + -2 == i;
    }

    /* access modifiers changed from: protected */
    public Calendar parseDate(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, str);
        int i = 1;
        int i2 = 1;
        int i3 = 1;
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("day")) {
                    i3 = Integer.parseInt(getInnerXmlWithoutHtml(xmlPullParser, "day"));
                }
                if (name.equals("month")) {
                    i2 = Integer.parseInt(getInnerXmlWithoutHtml(xmlPullParser, "month")) - 1;
                }
                if (name.equals("year")) {
                    i = Integer.parseInt(getInnerXmlWithoutHtml(xmlPullParser, "year"));
                }
            }
        }
        return new GregorianCalendar(i, i2, i3);
    }

    private String buildInlineReference(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<sup type=\"ref\"><small>");
        stringBuffer.append("<a href=\"ref://");
        String replace = str.replace("[", "").replace("]", "").replace("{", "").replace("}", "");
        stringBuffer.append(replace);
        stringBuffer.append("\">");
        stringBuffer.append("<![CDATA[(");
        stringBuffer.append(replace);
        stringBuffer.append(")]]>");
        stringBuffer.append("</a>");
        stringBuffer.append("</small></sup>");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public String getInnerXml(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        ArticleParserBase articleParserBase = this;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        StringBuilder sb = new StringBuilder();
        int i = 2;
        xmlPullParser2.require(2, (String) null, str);
        int i2 = 1;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (i2 != 0) {
            int next = xmlPullParser.next();
            if (next == i) {
                String name = xmlPullParser.getName();
                if (!name.equals("b")) {
                    i2++;
                }
                if (name.equals(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE)) {
                    z3 = true;
                } else if (!name.equals("sup")) {
                    StringBuilder sb2 = new StringBuilder();
                    int i3 = 0;
                    while (i3 < xmlPullParser.getAttributeCount()) {
                        if (!Extensions.isStringNullOrEmpty(xmlPullParser2.getAttributeValue(i3))) {
                            String trim = xmlPullParser2.getAttributeValue(i3).replaceAll("\\s+", "").trim();
                            sb2.append(xmlPullParser2.getAttributeName(i3) + "=\"" + TextUtils.htmlEncode(trim) + "\" ");
                        }
                        i3++;
                    }
                    sb.append(HtmlObject.HtmlMarkUp.OPEN_BRACKER + name + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + sb2.toString() + HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
                } else if (isReferenceNode(xmlPullParser)) {
                    z = true;
                } else {
                    z2 = true;
                }
            } else if (next == 3) {
                String name2 = xmlPullParser.getName();
                if (!name2.equals("b")) {
                    i2--;
                }
                if (z) {
                    z = false;
                } else if (z2) {
                    z2 = false;
                } else if (z3) {
                    z3 = false;
                } else if (i2 > 0) {
                    sb.append("</" + name2 + HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
                }
            } else if (next == 4) {
                String text = xmlPullParser.getText();
                if (text == null || text.trim().isEmpty()) {
                    sb.append("");
                } else {
                    String replaceAll = text.replaceAll("\\s+", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    if (z) {
                        sb.append(articleParserBase.buildInlineReference(replaceAll));
                    } else if (z2) {
                        sb.append(articleParserBase.parseNonRefSup(replaceAll));
                    } else if (!z3) {
                        sb.append("<![CDATA[" + replaceAll + "]]>");
                    }
                }
            }
            i = 2;
            articleParserBase = this;
        }
        return sb.toString().replace("&", "&amp;");
    }

    private String parseNonRefSup(String str) {
        String replace = str.replace("[", "(").replace("]", ")").replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "");
        return "<sup><small><![CDATA[" + replace + "]]></small></sup>";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r3 = r3.getAttributeValue("", "type");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isReferenceNode(org.xmlpull.v1.XmlPullParser r3) {
        /*
            r2 = this;
            java.lang.String r0 = r3.getName()
            if (r0 == 0) goto L_0x0022
            java.lang.String r1 = "sup"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0022
            java.lang.String r0 = ""
            java.lang.String r1 = "type"
            java.lang.String r3 = r3.getAttributeValue(r0, r1)
            if (r3 == 0) goto L_0x0022
            java.lang.String r0 = "ref"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0022
            r3 = 1
            return r3
        L_0x0022:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.models.parsers.articles.ArticleParserBase.isReferenceNode(org.xmlpull.v1.XmlPullParser):boolean");
    }

    /* access modifiers changed from: protected */
    public String getInnerXmlWithoutHtml(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        StringBuilder sb = new StringBuilder();
        xmlPullParser.require(2, (String) null, str);
        boolean z = false;
        int i = 1;
        while (i != 0 && !z) {
            int next = xmlPullParser.next();
            if (next != 2) {
                if (next != 3) {
                    if (next != 4) {
                        z = true;
                    } else {
                        String text = xmlPullParser.getText();
                        if (text == null || text.trim().isEmpty()) {
                            sb.append("");
                        } else {
                            sb.append(text);
                        }
                    }
                } else if (!xmlPullParser.getName().equals("b")) {
                    i--;
                }
            } else if (!xmlPullParser.getName().equals("b")) {
                i++;
            }
        }
        String replace = sb.toString().replace("&", "&amp;");
        xmlPullParser.require(3, (String) null, str);
        return replace;
    }

    /* access modifiers changed from: protected */
    public XmlPullParser traverseToChildNode(XmlPullParser xmlPullParser, String[] strArr, int i) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, (String) null, strArr[i]);
        if (strArr.length <= 1) {
            xmlPullParser.next();
            return xmlPullParser;
        }
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (isLastNodeNext(strArr, i)) {
                    if (isCurrentNodeNext(strArr, name, i)) {
                        return xmlPullParser;
                    }
                    skip(xmlPullParser);
                } else if (isCurrentNodeNext(strArr, name, i)) {
                    return traverseToChildNode(xmlPullParser, strArr, i + 1);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return xmlPullParser;
    }

    private MiscProvider parseMiscProviderStatement(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        String attributeValue;
        String text;
        MiscProvider miscProvider = new MiscProvider();
        xmlPullParser.require(2, (String) null, str);
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (!xmlPullParser.getName().equals("b")) {
                    i++;
                }
                if (xmlPullParser.getName().equals("graphic") && (attributeValue = xmlPullParser.getAttributeValue("", "xlink:href")) != null) {
                    miscProvider.LogoUri = Utilities.getImageUrl(attributeValue);
                }
            } else if (next != 3) {
                if (next == 4 && i == 2 && (text = xmlPullParser.getText()) != null && !text.trim().isEmpty()) {
                    miscProvider.Statement = xmlPullParser.getText().trim();
                }
            } else if (!xmlPullParser.getName().equals("b")) {
                i--;
            }
        }
        return miscProvider;
    }
}
