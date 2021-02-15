package com.medscape.android.reference;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Xml;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.reference.exception.ContentVersionNotSupportedException;
import com.medscape.android.reference.model.ClinicalReferenceContent;
import com.medscape.android.reference.model.Contributor;
import com.medscape.android.reference.model.Figure;
import com.medscape.android.reference.model.Para;
import com.medscape.android.reference.model.ReferenceItem;
import com.medscape.android.reference.model.Sect1;
import com.medscape.android.reference.model.Sect2;
import com.medscape.android.reference.model.Table;
import com.medscape.android.reference.style.CrossLinkClickableSpan;
import com.medscape.android.reference.style.NativeArticleSideHeaderSpan;
import com.medscape.android.util.StringUtil;
import com.wbmd.ads.model.AdContentData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Stack;
import org.apache.commons.io.IOUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ClinicalReferenceContentParser {
    private static final String A = "a";
    private static final String ADS_SUPPORT = "ads-support";
    private static final String ARTICLE = "article";
    private static final String ASIDE = "aside";
    private static final String AT_CLASS = "class";
    private static final String AT_EXTENSION = "extension_format";
    private static final String AT_FILEREF = "fileref";
    private static final String AT_FORMAT = "format";
    private static final String AT_HREF = "href";
    private static final String AT_ID = "id";
    private static final String AT_LINK_REF = "link_ref";
    private static final String AT_LINK_TYPE = "link_type";
    private static final String AT_REF = "ref";
    private static final String AT_TYPE = "type";
    private static final String AT_VAL_THUMB = "IMAGE_THUMB";
    private static final String AT_VERSION = "version";
    private static final String AT_VERSION_MEDS_20 = "MEDS_2.0";
    private static final String AUTHORS = "authors";
    private static final String BLOCKQUOTE = "blockquote";
    private static final String CAPTION = "caption";
    private static final String CONTENT_SECTION = "content_section";
    private static final String CONTRIBUTOR = "contrbtr_element";
    private static final String CONTRIBUTOR_BIO = "contrbtr_bio";
    private static final String CONTRIBUTOR_DISCLOSURE = "contrbtr_disclsr";
    private static final String CONTRIBUTOR_GROUP = "contrbtr_group";
    private static final String CONTRIBUTOR_NAME = "contrbtr_nm";
    private static final String CONTRIBUTOR_TITLE = "contrbtr_title";
    private static final String CONTRIBUTOR_TYPE_LABEL = "contrbtr_type_lbl";
    private static final String EM = "em";
    private static final String FIGURE = "figure";
    private static final String GRAPHIC = "graphic";
    private static final String ITEMIZEDLIST = "itemizedlist";
    private static final String LAST_UPDATE = "last_updated";
    private static final String LINK = "link";
    private static final String LISTITEM = "listitem";
    private static final String METADATA = "metadata";
    private static final String METADATA_SECTION = "metadata_section";
    private static final String ORDEREDLIST = "orderedlist";
    private static final String PARA = "para";
    private static final String PGROUP = "pgroup";
    private static final String REFGRP = "refgrp";
    private static final String RITEM = "ritem";
    private static final String SECT1 = "sect1";
    private static final String SECT2 = "sect2";
    private static final String SITE_METADATA = "site-meta";
    private static final String STRONG = "strong";
    private static final String SUP = "sup";
    private static final String TABLE = "table";
    private static final String TBODY = "tbody";
    private static final String TD = "td";
    private static final String TH = "th";
    private static final String THEAD = "thead";
    private static final String TITLE = "title";
    private static final String TR = "tr";
    private static ClinicalReferenceContentParser instance = new ClinicalReferenceContentParser();
    private static final String ns = null;
    private ClinicalReferenceContentDecorater decorater;
    boolean isInMediaSecton = false;
    private ClinicalReferenceContent mContent;
    private Context mContext;
    int paraCounter;
    int uniqueTableID = 1;

    public static ClinicalReferenceContentParser get() {
        return instance;
    }

    public ClinicalReferenceContent parse(String str, Context context) throws XmlPullParserException, IOException, ContentVersionNotSupportedException {
        if (new File(str).exists()) {
            return parse((InputStream) new FileInputStream(new File(str)), context);
        }
        return null;
    }

    public ClinicalReferenceContent parse(InputStream inputStream, Context context) throws XmlPullParserException, IOException, ContentVersionNotSupportedException {
        this.uniqueTableID = 1;
        this.mContext = context;
        this.decorater = new ClinicalReferenceContentDecorater(context);
        XmlPullParser newPullParser = Xml.newPullParser();
        boolean z = false;
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        newPullParser.setInput(inputStream, (String) null);
        newPullParser.nextTag();
        this.mContent = new ClinicalReferenceContent();
        String attributeValue = newPullParser.getAttributeValue(ns, "version");
        if (attributeValue == null || !attributeValue.equals(AT_VERSION_MEDS_20)) {
            throw new ContentVersionNotSupportedException("XML content not MEDS 2.0");
        }
        boolean z2 = false;
        while (newPullParser.next() != 3) {
            if (newPullParser.getEventType() == 2) {
                String name = newPullParser.getName();
                if (name == null) {
                    skip(newPullParser);
                } else if (name.equals(CONTENT_SECTION)) {
                    readContent(newPullParser);
                    z = true;
                } else if (name.equals(METADATA_SECTION)) {
                    readMetaDataSection(newPullParser);
                    z2 = true;
                } else {
                    skip(newPullParser);
                }
                if (z && z2) {
                    break;
                }
            }
        }
        return this.mContent;
    }

    private void readContent(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, CONTENT_SECTION);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals("title")) {
                    this.mContent.title = parseTitle(xmlPullParser);
                } else if (name.equals(SECT1)) {
                    this.mContent.addSection(readSection(xmlPullParser));
                } else if (name.equals(REFGRP)) {
                    this.mContent.references = readReferences(xmlPullParser);
                } else if (name.equals(AUTHORS)) {
                    this.mContent.contributors = readAuthors(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void readMetaDataSection(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, METADATA_SECTION);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals(METADATA)) {
                    readMetaData(xmlPullParser);
                } else if (name.equals(SITE_METADATA)) {
                    readSiteMetaData(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void readMetaData(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, METADATA);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals(LAST_UPDATE)) {
                    this.mContent.lastUpdate = parseLastUpdate(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private void readSiteMetaData(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, SITE_METADATA);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals(ADS_SUPPORT)) {
                    this.mContent.adSupportMap = readAdsSupport(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
    }

    private HashMap<String, String> readAdsSupport(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, ADS_SUPPORT);
        HashMap<String, String> hashMap = new HashMap<>();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals("site")) {
                    hashMap.put(name, readText(xmlPullParser));
                } else if (name.equals("affiliate")) {
                    hashMap.put(name, readText(xmlPullParser));
                } else if (name.equals("pclass")) {
                    hashMap.put(name, readText(xmlPullParser));
                } else if (name.equals("artid")) {
                    hashMap.put(name, readText(xmlPullParser));
                } else if (name.equals("cg")) {
                    hashMap.put(name, readText(xmlPullParser));
                } else if (name.equals("ac")) {
                    hashMap.put(name, readText(xmlPullParser));
                } else if (name.equals(AdContentData.RELATED_SPECIALITY)) {
                    hashMap.put(name, readText(xmlPullParser));
                } else if (name.equals("bc")) {
                    hashMap.put(name, readText(xmlPullParser));
                } else if (name.equals("article-id")) {
                    hashMap.put(name, readText(xmlPullParser));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return hashMap;
    }

    private Sect1 readSection(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, SECT1);
        Sect1 sect1 = new Sect1();
        sect1.id = xmlPullParser.getAttributeValue(ns, "id");
        sect1.type = xmlPullParser.getAttributeValue(ns, "type");
        if ("media".equalsIgnoreCase(sect1.type)) {
            this.isInMediaSecton = true;
        }
        Sect2 sect2 = null;
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals("title")) {
                    sect1.title = parseTitle(xmlPullParser);
                } else if (name.equals(SECT2)) {
                    this.paraCounter = 0;
                    sect1.addSubsection(readSubsection(xmlPullParser));
                } else if (name.equals(PGROUP)) {
                    if (sect2 == null) {
                        this.paraCounter = 0;
                        sect2 = new Sect2();
                        sect1.addSubsection(sect2);
                    }
                    sect2.addPara(readPara(xmlPullParser, (Contributor) null, PGROUP));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        if ("media".equalsIgnoreCase(sect1.type)) {
            this.isInMediaSecton = false;
        }
        return sect1;
    }

    private Sect2 readSubsection(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, SECT2);
        Sect2 sect2 = new Sect2();
        sect2.id = xmlPullParser.getAttributeValue(ns, "id");
        sect2.type = xmlPullParser.getAttributeValue(ns, "type");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals("title")) {
                    sect2.title = parseTitle(xmlPullParser);
                } else if (name.equals(PGROUP)) {
                    sect2.addPara(readPara(xmlPullParser, (Contributor) null, PGROUP));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return sect2;
    }

    private ArrayList<Contributor> readAuthors(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, AUTHORS);
        ArrayList<Contributor> arrayList = new ArrayList<>();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals(CONTRIBUTOR_GROUP)) {
                    arrayList.add(readContributorGroup(xmlPullParser));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return arrayList;
    }

    private Contributor readContributorGroup(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, CONTRIBUTOR_GROUP);
        Contributor contributor = new Contributor();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals(CONTRIBUTOR_TYPE_LABEL)) {
                    contributor.typeLabel = readText(xmlPullParser);
                } else if (name.equals(CONTRIBUTOR)) {
                    contributor.contributorPara = readPara(xmlPullParser, contributor, CONTRIBUTOR);
                } else if (name.equals(PGROUP)) {
                    contributor.contributorPara = readPara(xmlPullParser, contributor, PGROUP);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return contributor;
    }

    private LinkedHashMap<String, ReferenceItem> readReferences(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, REFGRP);
        LinkedHashMap<String, ReferenceItem> linkedHashMap = new LinkedHashMap<>();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals(RITEM)) {
                    ReferenceItem readReferenceItem = readReferenceItem(xmlPullParser);
                    linkedHashMap.put(readReferenceItem.id, readReferenceItem);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return linkedHashMap;
    }

    private ReferenceItem readReferenceItem(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, RITEM);
        ReferenceItem referenceItem = new ReferenceItem();
        referenceItem.id = xmlPullParser.getAttributeValue(ns, "id");
        referenceItem.href = xmlPullParser.getAttributeValue(ns, AT_REF);
        referenceItem.para = readReferencePara(xmlPullParser, referenceItem.id);
        return referenceItem;
    }

    private Para readReferencePara(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        CrossLink parseA;
        int i = 2;
        xmlPullParser.require(2, ns, RITEM);
        Para para = new Para();
        new SpannableStringBuilder();
        int next = xmlPullParser.next();
        Stack stack = new Stack();
        boolean z = false;
        CrossLink crossLink = null;
        while (!z) {
            String name = xmlPullParser.getName();
            if (next != i) {
                if (next != 3) {
                    if (next == 4) {
                        para.append(this.decorater.handleText(xmlPullParser.getText()));
                    }
                } else if (name.equals(PARA)) {
                    stack.pop();
                } else if (name.equals(STRONG)) {
                    this.decorater.handle(para, ((Integer) stack.pop()).intValue(), new StyleSpan(1));
                } else if (name.equals("em")) {
                    this.decorater.handle(para, ((Integer) stack.pop()).intValue(), new StyleSpan(3));
                } else if (name.equals(SUP)) {
                    this.decorater.handleSuperscript(para, ((Integer) stack.pop()).intValue());
                } else if (name.equals("link")) {
                    this.decorater.handle(para, ((Integer) stack.pop()).intValue(), new CrossLinkClickableSpan(crossLink, this.mContext));
                } else if (name.equals("a")) {
                    this.decorater.handle(para, ((Integer) stack.pop()).intValue(), new CrossLinkClickableSpan(crossLink, this.mContext));
                } else if (name.equals(RITEM)) {
                    String str2 = str;
                    z = true;
                }
                String str3 = str;
            } else if (name.equals(PARA)) {
                this.decorater.handleRItem(para, str);
                stack.push(Integer.valueOf(para.length()));
            } else {
                String str4 = str;
                if (name.equals(STRONG)) {
                    stack.push(Integer.valueOf(para.length()));
                } else if (name.equals("em")) {
                    stack.push(Integer.valueOf(para.length()));
                } else if (name.equals(SUP)) {
                    stack.push(Integer.valueOf(para.length()));
                } else {
                    if (name.equals("link")) {
                        parseA = parseLink(xmlPullParser);
                        stack.push(Integer.valueOf(para.length()));
                    } else if (name.equals("a")) {
                        parseA = parseA(xmlPullParser);
                        stack.push(Integer.valueOf(para.length()));
                    } else {
                        skip(xmlPullParser);
                    }
                    crossLink = parseA;
                }
            }
            next = xmlPullParser.next();
            i = 2;
        }
        return para;
    }

    private Para readSimplePara(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        CrossLink crossLink;
        boolean z;
        CrossLink parseA;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        xmlPullParser2.require(2, ns, str);
        Para para = new Para();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int next = xmlPullParser.next();
        Stack stack = new Stack();
        CrossLink crossLink2 = null;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        while (!z2) {
            String name = xmlPullParser.getName();
            boolean z7 = z2;
            boolean z8 = z6;
            boolean z9 = z4;
            Object obj = BLOCKQUOTE;
            CharSequence charSequence = IOUtils.LINE_SEPARATOR_UNIX;
            Object obj2 = ASIDE;
            boolean z10 = z5;
            if (next != 2) {
                if (next != 3) {
                    if (next == 4) {
                        para.append(this.decorater.handleText(xmlPullParser.getText()));
                    }
                } else if (name.equals(PARA)) {
                    stack.pop();
                    if (!z3) {
                        this.decorater.handleSimpleParaEnd(para, spannableStringBuilder);
                    }
                } else if (name.equals(STRONG)) {
                    this.decorater.handle(para, ((Integer) stack.pop()).intValue(), new StyleSpan(1));
                } else if (name.equals("em")) {
                    int intValue = ((Integer) stack.pop()).intValue();
                    this.decorater.handle(para, intValue, new StyleSpan(3));
                    this.decorater.handle(para, intValue, new NativeArticleSideHeaderSpan());
                } else if (name.equals(SUP)) {
                    this.decorater.handleSuperscript(para, ((Integer) stack.pop()).intValue());
                } else if (name.equals("link")) {
                    this.decorater.handle(para, ((Integer) stack.pop()).intValue(), new CrossLinkClickableSpan(crossLink2, this.mContext));
                } else if (name.equals("a")) {
                    this.decorater.handle(para, ((Integer) stack.pop()).intValue(), new CrossLinkClickableSpan(crossLink2, this.mContext));
                } else {
                    if (name.equals(LISTITEM)) {
                        z5 = z10;
                        this.decorater.handleListItemEnd(para, ((Integer) stack.pop()).intValue(), z5);
                        z2 = z7;
                        z6 = z8;
                        z4 = z9;
                        z3 = false;
                    } else {
                        z5 = z10;
                        if (name.equals(obj2)) {
                            para.append(charSequence);
                            stack.push(Integer.valueOf(para.length()));
                        } else if (name.equals(obj)) {
                            stack.push(Integer.valueOf(para.length()));
                        } else if (name.equals(str)) {
                            z6 = z8;
                            z4 = z9;
                            z2 = true;
                        }
                        crossLink = crossLink2;
                        z10 = z5;
                    }
                    next = xmlPullParser.next();
                    XmlPullParser xmlPullParser3 = xmlPullParser;
                    String str2 = str;
                }
                crossLink = crossLink2;
            } else {
                Object obj3 = SUP;
                CharSequence charSequence2 = charSequence;
                Object obj4 = obj2;
                Object obj5 = obj;
                crossLink = crossLink2;
                Object obj6 = obj5;
                if (name.equals(PARA)) {
                    if (!z3) {
                        if (z9) {
                            this.decorater.handleParaStart(para, spannableStringBuilder);
                        } else {
                            z4 = true;
                            stack.push(Integer.valueOf(para.length()));
                            z2 = z7;
                            z6 = z8;
                            crossLink2 = crossLink;
                            z5 = z10;
                            next = xmlPullParser.next();
                            XmlPullParser xmlPullParser32 = xmlPullParser;
                            String str22 = str;
                        }
                    }
                    z4 = z9;
                    stack.push(Integer.valueOf(para.length()));
                    z2 = z7;
                    z6 = z8;
                    crossLink2 = crossLink;
                    z5 = z10;
                    next = xmlPullParser.next();
                    XmlPullParser xmlPullParser322 = xmlPullParser;
                    String str222 = str;
                } else if (name.equals(STRONG)) {
                    stack.push(Integer.valueOf(para.length()));
                } else if (name.equals("em")) {
                    stack.push(Integer.valueOf(para.length()));
                } else if (name.equals(obj3)) {
                    stack.push(Integer.valueOf(para.length()));
                } else {
                    if (name.equals("link")) {
                        parseA = parseLink(xmlPullParser);
                        stack.push(Integer.valueOf(para.length()));
                    } else if (name.equals("a")) {
                        parseA = parseA(xmlPullParser);
                        stack.push(Integer.valueOf(para.length()));
                    } else if (name.equals(ITEMIZEDLIST) || name.equals(ORDEREDLIST)) {
                        xmlPullParser.next();
                        z5 = name.equals(ORDEREDLIST);
                        z2 = z7;
                        z4 = z9;
                        crossLink2 = crossLink;
                        z6 = true;
                        next = xmlPullParser.next();
                        XmlPullParser xmlPullParser3222 = xmlPullParser;
                        String str2222 = str;
                    } else if (name.equals(LISTITEM)) {
                        this.decorater.handleListItemStart(para, z8);
                        stack.push(Integer.valueOf(para.length()));
                        z2 = z7;
                        z4 = z9;
                        crossLink2 = crossLink;
                        z5 = z10;
                        z3 = true;
                        z6 = false;
                        next = xmlPullParser.next();
                        XmlPullParser xmlPullParser32222 = xmlPullParser;
                        String str22222 = str;
                    } else {
                        z = z8;
                        if (name.equals(obj4)) {
                            para.append(charSequence2);
                            stack.push(Integer.valueOf(para.length()));
                        } else if (name.equals(obj6)) {
                            stack.push(Integer.valueOf(para.length()));
                        } else {
                            skip(xmlPullParser);
                        }
                        z6 = z;
                        z2 = z7;
                        z4 = z9;
                        crossLink2 = crossLink;
                        z5 = z10;
                        next = xmlPullParser.next();
                        XmlPullParser xmlPullParser322222 = xmlPullParser;
                        String str222222 = str;
                    }
                    crossLink2 = parseA;
                    z2 = z7;
                    z6 = z8;
                    z4 = z9;
                    z5 = z10;
                    next = xmlPullParser.next();
                    XmlPullParser xmlPullParser3222222 = xmlPullParser;
                    String str2222222 = str;
                }
            }
            z = z8;
            z6 = z;
            z2 = z7;
            z4 = z9;
            crossLink2 = crossLink;
            z5 = z10;
            next = xmlPullParser.next();
            XmlPullParser xmlPullParser32222222 = xmlPullParser;
            String str22222222 = str;
        }
        return para;
    }

    /* JADX WARNING: Removed duplicated region for block: B:122:0x03cf  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x03e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.medscape.android.reference.model.Para readPara(org.xmlpull.v1.XmlPullParser r36, com.medscape.android.reference.model.Contributor r37, java.lang.String r38) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r35 = this;
            r0 = r35
            r1 = r36
            r2 = r38
            java.lang.String r3 = ns
            r4 = 2
            r1.require(r4, r3, r2)
            com.medscape.android.reference.model.Para r3 = new com.medscape.android.reference.model.Para
            r3.<init>()
            android.text.SpannableStringBuilder r5 = new android.text.SpannableStringBuilder
            r5.<init>()
            int r6 = r36.next()
            java.util.Stack r7 = new java.util.Stack
            r7.<init>()
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
        L_0x002e:
            if (r11 != 0) goto L_0x068c
            java.lang.String r8 = r36.getName()
            java.lang.String r4 = "tr"
            r21 = r11
            java.lang.String r11 = "table"
            r22 = r10
            java.lang.String r10 = "blockquote"
            r23 = r15
            java.lang.String r15 = "aside"
            java.lang.String r1 = "listitem"
            java.lang.String r2 = "itemizedlist"
            r24 = r11
            java.lang.String r11 = "a"
            r25 = r4
            java.lang.String r4 = "link"
            r26 = r10
            java.lang.String r10 = "sup"
            r27 = r15
            java.lang.String r15 = "em"
            r28 = r2
            java.lang.String r2 = "strong"
            r29 = r1
            java.lang.String r1 = "para"
            r30 = r11
            java.lang.String r11 = "orderedlist"
            r31 = r11
            java.lang.String r11 = "title"
            r32 = r4
            r4 = 2
            if (r6 == r4) goto L_0x035a
            r4 = 3
            if (r6 == r4) goto L_0x0094
            r1 = 4
            if (r6 == r1) goto L_0x0086
        L_0x0071:
            r4 = r36
            r6 = r17
            r10 = r22
            r1 = -1
            r30 = 2
            r17 = r12
            r12 = r18
            r18 = r19
            r19 = r14
        L_0x0082:
            r14 = r37
            goto L_0x064f
        L_0x0086:
            com.medscape.android.reference.ClinicalReferenceContentDecorater r1 = r0.decorater
            java.lang.String r2 = r36.getText()
            android.text.SpannableStringBuilder r1 = r1.handleText(r2)
            r3.append(r1)
            goto L_0x0071
        L_0x0094:
            boolean r6 = r8.equals(r11)
            if (r6 == 0) goto L_0x00d2
            if (r12 == 0) goto L_0x00a4
            com.medscape.android.reference.style.TitleClickableSpan r1 = new com.medscape.android.reference.style.TitleClickableSpan
            android.content.Context r2 = r0.mContext
            r1.<init>(r12, r2)
            goto L_0x00a5
        L_0x00a4:
            r1 = 0
        L_0x00a5:
            java.lang.Object r2 = r7.pop()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            com.medscape.android.reference.ClinicalReferenceContentDecorater r4 = r0.decorater
            com.medscape.android.reference.model.Para r1 = r4.handleTitle(r3, r2, r1)
            r3 = 2
            if (r2 >= r3) goto L_0x00be
            int r3 = r5.length()
            int r9 = r2 + r3
        L_0x00be:
            r4 = r36
            r3 = r1
        L_0x00c1:
            r31 = r19
            r11 = r21
            r10 = r22
            r15 = r23
            r1 = -1
        L_0x00ca:
            r30 = 2
            r19 = r14
        L_0x00ce:
            r14 = r37
            goto L_0x067e
        L_0x00d2:
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x00fd
            if (r14 <= 0) goto L_0x00e2
            int r1 = r3.length()
            if (r1 != r14) goto L_0x00e2
            r4 = 1
            goto L_0x00e3
        L_0x00e2:
            r4 = 0
        L_0x00e3:
            if (r13 != 0) goto L_0x00ea
            com.medscape.android.reference.ClinicalReferenceContentDecorater r1 = r0.decorater
            r1.handleParagraph(r3, r9, r4)
        L_0x00ea:
            r4 = r36
            r14 = r37
            r31 = r19
            r11 = r21
            r10 = r22
            r15 = r23
            r1 = -1
            r19 = 0
            r30 = 2
            goto L_0x067e
        L_0x00fd:
            boolean r1 = r8.equals(r2)
            if (r1 == 0) goto L_0x011a
            com.medscape.android.reference.ClinicalReferenceContentDecorater r1 = r0.decorater
            java.lang.Object r2 = r7.pop()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            android.text.style.StyleSpan r4 = new android.text.style.StyleSpan
            r6 = 1
            r4.<init>(r6)
            r1.handle(r3, r2, r4)
            goto L_0x0071
        L_0x011a:
            boolean r1 = r8.equals(r15)
            if (r1 == 0) goto L_0x013e
            java.lang.Object r1 = r7.pop()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            com.medscape.android.reference.ClinicalReferenceContentDecorater r2 = r0.decorater
            android.text.style.StyleSpan r6 = new android.text.style.StyleSpan
            r6.<init>(r4)
            r2.handle(r3, r1, r6)
            r1 = -1
            if (r14 != r1) goto L_0x013b
            int r14 = r3.length()
        L_0x013b:
            r4 = r36
            goto L_0x00c1
        L_0x013e:
            boolean r1 = r8.equals(r10)
            if (r1 == 0) goto L_0x0155
            com.medscape.android.reference.ClinicalReferenceContentDecorater r1 = r0.decorater
            java.lang.Object r2 = r7.pop()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r1.handleSuperscript(r3, r2)
            goto L_0x0071
        L_0x0155:
            r6 = r32
            boolean r1 = r8.equals(r6)
            if (r1 == 0) goto L_0x0175
            com.medscape.android.reference.ClinicalReferenceContentDecorater r1 = r0.decorater
            java.lang.Object r2 = r7.pop()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            com.medscape.android.reference.style.CrossLinkClickableSpan r4 = new com.medscape.android.reference.style.CrossLinkClickableSpan
            android.content.Context r6 = r0.mContext
            r4.<init>(r12, r6)
            r1.handle(r3, r2, r4)
            goto L_0x0071
        L_0x0175:
            r1 = r30
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x0195
            com.medscape.android.reference.ClinicalReferenceContentDecorater r1 = r0.decorater
            java.lang.Object r2 = r7.pop()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            com.medscape.android.reference.style.CrossLinkClickableSpan r4 = new com.medscape.android.reference.style.CrossLinkClickableSpan
            android.content.Context r6 = r0.mContext
            r4.<init>(r12, r6)
            r1.handle(r3, r2, r4)
            goto L_0x0071
        L_0x0195:
            r1 = r29
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x01c4
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto L_0x01a5
            r1 = 0
            goto L_0x01af
        L_0x01a5:
            java.lang.Object r1 = r7.pop()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
        L_0x01af:
            com.medscape.android.reference.ClinicalReferenceContentDecorater r2 = r0.decorater
            r6 = r16
            r2.handleListItemEnd(r3, r1, r6)
            r4 = r36
            r31 = r19
            r11 = r21
            r10 = r22
            r15 = r23
            r1 = -1
            r13 = 0
            goto L_0x00ca
        L_0x01c4:
            r6 = r16
            r1 = r28
            boolean r1 = r8.equals(r1)
            if (r1 != 0) goto L_0x0329
            r1 = r31
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x01d8
            goto L_0x0329
        L_0x01d8:
            r1 = r27
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x01fe
            java.lang.Object r1 = r7.pop()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            com.medscape.android.reference.ClinicalReferenceContentDecorater r2 = r0.decorater
            r2.handleAside(r3, r1)
            r2 = 2
            if (r1 >= r2) goto L_0x01f8
            int r2 = r5.length()
            int r9 = r1 + r2
        L_0x01f8:
            r4 = r36
            r16 = r6
            goto L_0x00c1
        L_0x01fe:
            r1 = r26
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x021f
            java.lang.Object r1 = r7.pop()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            com.medscape.android.reference.ClinicalReferenceContentDecorater r2 = r0.decorater
            r2.handleBlockQuote(r3, r1)
            r2 = 2
            if (r1 >= r2) goto L_0x01f8
            int r4 = r5.length()
            int r9 = r1 + r4
            goto L_0x01f8
        L_0x021f:
            r1 = r25
            r2 = 2
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x0249
            r1 = r17
            if (r1 == 0) goto L_0x0235
            r10 = r18
            r11 = r19
            r1.addRow(r10, r11)
            goto L_0x0345
        L_0x0235:
            r4 = r36
            r16 = r6
            r17 = r12
            r12 = r18
            r18 = r19
            r10 = r22
            r30 = 2
            r6 = r1
            r19 = r14
            r1 = -1
            goto L_0x0082
        L_0x0249:
            r1 = r17
            r10 = r18
            r11 = r19
            java.lang.String r15 = "thead"
            boolean r15 = r8.equals(r15)
            if (r15 == 0) goto L_0x026e
            r4 = r36
            r17 = r1
            r16 = r6
            r18 = r10
            r19 = r14
            r11 = r21
            r10 = r22
            r15 = r23
            r1 = -1
            r30 = 2
            r31 = 0
            goto L_0x00ce
        L_0x026e:
            r15 = r24
            boolean r15 = r8.equals(r15)
            if (r15 == 0) goto L_0x029d
            if (r1 == 0) goto L_0x0345
            java.lang.String r4 = r1.id
            boolean r4 = com.medscape.android.util.StringUtil.isNotEmpty(r4)
            if (r4 == 0) goto L_0x0345
            com.medscape.android.reference.model.ClinicalReferenceContent r4 = r0.mContent
            r4.addTable(r1)
            com.medscape.android.reference.ClinicalReferenceContentDecorater r4 = r0.decorater
            java.lang.Object r8 = r7.pop()
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            com.medscape.android.reference.style.NativeArticleTableSpan r15 = new com.medscape.android.reference.style.NativeArticleTableSpan
            java.lang.String r2 = r1.id
            r15.<init>(r2)
            r4.handle(r3, r8, r15)
            goto L_0x0345
        L_0x029d:
            java.lang.String r2 = "contrbtr_nm"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x02bc
            com.medscape.android.reference.ClinicalReferenceContentDecorater r2 = r0.decorater
            java.lang.Object r4 = r7.pop()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            android.text.style.StyleSpan r8 = new android.text.style.StyleSpan
            r15 = 1
            r8.<init>(r15)
            r2.handle(r3, r4, r8)
            goto L_0x0345
        L_0x02bc:
            r15 = 1
            java.lang.String r2 = "contrbtr_title"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x02d6
            com.medscape.android.reference.ClinicalReferenceContentDecorater r2 = r0.decorater
            java.lang.Object r4 = r7.pop()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r2.addLineBreak(r3, r5, r4)
            goto L_0x0345
        L_0x02d6:
            java.lang.String r2 = "contrbtr_bio"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x02ee
            com.medscape.android.reference.ClinicalReferenceContentDecorater r2 = r0.decorater
            java.lang.Object r4 = r7.pop()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r2.addLineBreak(r3, r5, r4)
            goto L_0x0345
        L_0x02ee:
            java.lang.String r2 = "contrbtr_disclsr"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x02fa
            r7.pop()
            goto L_0x0345
        L_0x02fa:
            r2 = r38
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x0345
            java.lang.String r8 = "pgroup"
            boolean r8 = r2.equals(r8)
            if (r8 == 0) goto L_0x0313
            int r8 = r0.paraCounter
            if (r8 != r4) goto L_0x0313
            java.lang.String r4 = " "
            r3.append(r4)
        L_0x0313:
            r4 = r36
            r17 = r1
            r16 = r6
            r18 = r10
            r31 = r11
            r19 = r14
            r10 = r22
            r15 = r23
            r1 = -1
            r11 = 1
            r30 = 2
            goto L_0x00ce
        L_0x0329:
            r2 = r38
            r1 = r17
            r10 = r18
            r11 = r19
            com.medscape.android.reference.ClinicalReferenceContentDecorater r4 = r0.decorater
            java.lang.Object r8 = r7.pop()
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            com.medscape.android.reference.style.NativeArticleListSpan r15 = new com.medscape.android.reference.style.NativeArticleListSpan
            r15.<init>()
            r4.handle(r3, r8, r15)
        L_0x0345:
            r4 = r36
            r16 = r6
            r18 = r11
            r17 = r12
            r19 = r14
            r30 = 2
            r14 = r37
            r6 = r1
            r12 = r10
            r10 = r22
            r1 = -1
            goto L_0x064f
        L_0x035a:
            r20 = r18
            r18 = r19
            r4 = r31
            r6 = r32
            r31 = 1
            r19 = r14
            r14 = r30
            r30 = 2
            r34 = r17
            r17 = r12
            r12 = r28
            r28 = r26
            r26 = r29
            r29 = r27
            r27 = r24
            r24 = r34
            boolean r32 = r8.equals(r11)
            if (r32 == 0) goto L_0x03a7
            r4 = r36
            com.medscape.android.contentviewer.CrossLink r12 = r0.parseLinkAttribute(r4, r11)
            com.medscape.android.reference.ClinicalReferenceContentDecorater r1 = r0.decorater
            r1.handleTitleStart(r3, r5)
            int r1 = r3.length()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r7.push(r1)
            r14 = r37
            r31 = r18
            r18 = r20
            r11 = r21
            r10 = r22
            r15 = r23
            r17 = r24
            r1 = -1
            goto L_0x067e
        L_0x03a7:
            r32 = r4
            r33 = r26
            r4 = r36
            boolean r1 = r8.equals(r1)
            if (r1 == 0) goto L_0x03f4
            if (r13 != 0) goto L_0x03bf
            if (r23 == 0) goto L_0x03bd
            com.medscape.android.reference.ClinicalReferenceContentDecorater r1 = r0.decorater
            r1.handleParaStart(r3, r5)
            goto L_0x03bf
        L_0x03bd:
            r15 = 1
            goto L_0x03c1
        L_0x03bf:
            r15 = r23
        L_0x03c1:
            int r1 = r3.length()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r7.push(r1)
            r1 = -1
            if (r9 != r1) goto L_0x03e0
            r14 = r37
            r12 = r17
            r31 = r18
            r18 = r20
            r11 = r21
            r10 = r22
            r17 = r24
            r9 = 0
            goto L_0x067e
        L_0x03e0:
            int r9 = r3.length()
            r14 = r37
            r12 = r17
            r31 = r18
            r18 = r20
            r11 = r21
            r10 = r22
        L_0x03f0:
            r17 = r24
            goto L_0x067e
        L_0x03f4:
            r1 = -1
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0410
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
        L_0x0406:
            r14 = r37
        L_0x0408:
            r12 = r20
            r10 = r22
        L_0x040c:
            r6 = r24
            goto L_0x064f
        L_0x0410:
            boolean r2 = r8.equals(r15)
            java.lang.String r11 = "\n"
            if (r2 == 0) goto L_0x0441
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
            java.lang.String r2 = r3.toString()
            boolean r2 = r2.endsWith(r11)
            r14 = r37
            if (r2 == 0) goto L_0x0408
            r12 = r17
            r31 = r18
            r18 = r20
            r11 = r21
            r10 = r22
            r15 = r23
            r17 = r24
            r19 = -1
            goto L_0x067e
        L_0x0441:
            boolean r2 = r8.equals(r10)
            if (r2 == 0) goto L_0x0453
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
            goto L_0x0406
        L_0x0453:
            boolean r2 = r8.equals(r6)
            if (r2 == 0) goto L_0x0476
            com.medscape.android.contentviewer.CrossLink r12 = r35.parseLink(r36)
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
        L_0x0468:
            r14 = r37
            r31 = r18
            r18 = r20
            r11 = r21
            r10 = r22
            r15 = r23
            goto L_0x03f0
        L_0x0476:
            boolean r2 = r8.equals(r14)
            if (r2 == 0) goto L_0x048c
            com.medscape.android.contentviewer.CrossLink r12 = r35.parseA(r36)
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
            goto L_0x0468
        L_0x048c:
            boolean r2 = r8.equals(r12)
            if (r2 != 0) goto L_0x065c
            r2 = r32
            boolean r6 = r8.equals(r2)
            if (r6 == 0) goto L_0x04a2
            r14 = r37
            r12 = r20
            r6 = r24
            goto L_0x0664
        L_0x04a2:
            r6 = r33
            boolean r2 = r8.equals(r6)
            if (r2 == 0) goto L_0x04ce
            com.medscape.android.reference.ClinicalReferenceContentDecorater r2 = r0.decorater
            r10 = r22
            r2.handleListItemStart(r3, r10)
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
            r14 = r37
            r12 = r17
            r31 = r18
            r18 = r20
            r11 = r21
            r15 = r23
            r17 = r24
            r10 = 0
            r13 = 1
            goto L_0x067e
        L_0x04ce:
            r10 = r22
            r2 = r29
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x04ec
            r3.append(r11)
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
        L_0x04e6:
            r14 = r37
            r12 = r20
            goto L_0x040c
        L_0x04ec:
            r2 = r28
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0500
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
            goto L_0x04e6
        L_0x0500:
            r2 = r27
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x052a
            com.medscape.android.reference.model.Table r2 = r35.parseTable(r36)
            int r6 = r3.length()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r7.push(r6)
            r36.next()
            r14 = r37
            r12 = r17
            r31 = r18
            r18 = r20
            r11 = r21
            r15 = r23
            r17 = r2
            goto L_0x067e
        L_0x052a:
            java.lang.String r2 = "caption"
            boolean r6 = r8.equals(r2)
            if (r6 == 0) goto L_0x0545
            if (r24 == 0) goto L_0x053d
            com.medscape.android.reference.model.Para r2 = r0.readSimplePara(r4, r2)
            r6 = r24
            r6.caption = r2
            goto L_0x053f
        L_0x053d:
            r6 = r24
        L_0x053f:
            r14 = r37
            r12 = r20
            goto L_0x064f
        L_0x0545:
            r6 = r24
            java.lang.String r2 = "thead"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x055e
            r36.next()
            r14 = r37
            r12 = r17
            r18 = r20
            r11 = r21
            r15 = r23
            goto L_0x0659
        L_0x055e:
            java.lang.String r2 = "tbody"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x056a
            r36.next()
            goto L_0x053f
        L_0x056a:
            r2 = r25
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0585
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r14 = r37
            r12 = r17
            r31 = r18
            r11 = r21
            r15 = r23
            r18 = r2
            goto L_0x0659
        L_0x0585:
            java.lang.String r2 = "td"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x059c
            java.lang.String r2 = "td"
            com.medscape.android.reference.model.Para r2 = r0.readSimplePara(r4, r2)
            r12 = r20
            if (r20 == 0) goto L_0x0082
            r12.add(r2)
            goto L_0x0082
        L_0x059c:
            r12 = r20
            java.lang.String r2 = "th"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x05b3
            java.lang.String r2 = "th"
            com.medscape.android.reference.model.Para r2 = r0.readSimplePara(r4, r2)
            if (r12 == 0) goto L_0x0082
            r12.add(r2)
            goto L_0x0082
        L_0x05b3:
            java.lang.String r2 = "figure"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x05e0
            com.medscape.android.reference.model.Figure r2 = r35.readFigure(r36)
            if (r2 == 0) goto L_0x0082
            java.lang.String r8 = r2.id
            boolean r8 = com.medscape.android.util.StringUtil.isNotEmpty(r8)
            if (r8 == 0) goto L_0x0082
            boolean r8 = r0.isInMediaSecton
            if (r8 == 0) goto L_0x05d2
            com.medscape.android.reference.model.ClinicalReferenceContent r8 = r0.mContent
            r8.addFigure(r2)
        L_0x05d2:
            com.medscape.android.reference.ClinicalReferenceContentDecorater r8 = r0.decorater
            com.medscape.android.reference.style.NativeArticleFigureSpan r11 = new com.medscape.android.reference.style.NativeArticleFigureSpan
            java.lang.String r2 = r2.id
            r11.<init>(r2)
            r8.handleFigure(r3, r5, r11)
            goto L_0x0082
        L_0x05e0:
            java.lang.String r2 = "contrbtr_nm"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x05f5
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
            goto L_0x0082
        L_0x05f5:
            java.lang.String r2 = "contrbtr_title"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x061c
            if (r3 == 0) goto L_0x060e
            int r2 = r3.length()
            if (r2 <= 0) goto L_0x060e
            java.lang.String r2 = r3.toString()
            r14 = r37
            r14.author = r2
            goto L_0x0610
        L_0x060e:
            r14 = r37
        L_0x0610:
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
            goto L_0x064f
        L_0x061c:
            r14 = r37
            java.lang.String r2 = "contrbtr_bio"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0635
            r3.append(r11)
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
            goto L_0x064f
        L_0x0635:
            java.lang.String r2 = "contrbtr_disclsr"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x064c
            r3.append(r11)
            int r2 = r3.length()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.push(r2)
            goto L_0x064f
        L_0x064c:
            r35.skip(r36)
        L_0x064f:
            r31 = r18
            r11 = r21
            r15 = r23
        L_0x0655:
            r18 = r12
            r12 = r17
        L_0x0659:
            r17 = r6
            goto L_0x067e
        L_0x065c:
            r14 = r37
            r12 = r20
            r6 = r24
            r2 = r32
        L_0x0664:
            int r10 = r3.length()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r7.push(r10)
            r36.next()
            boolean r16 = r8.equals(r2)
            r31 = r18
            r11 = r21
            r15 = r23
            r10 = 1
            goto L_0x0655
        L_0x067e:
            int r6 = r36.next()
            r2 = r38
            r1 = r4
            r14 = r19
            r19 = r31
            r4 = 2
            goto L_0x002e
        L_0x068c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.reference.ClinicalReferenceContentParser.readPara(org.xmlpull.v1.XmlPullParser, com.medscape.android.reference.model.Contributor, java.lang.String):com.medscape.android.reference.model.Para");
    }

    private CrossLink parseLinkAttribute(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, str);
        String attributeValue = xmlPullParser.getAttributeValue(ns, AT_LINK_REF);
        String attributeValue2 = xmlPullParser.getAttributeValue(ns, AT_LINK_TYPE);
        if (!StringUtil.isNotEmpty(attributeValue) || !StringUtil.isNotEmpty(attributeValue)) {
            return null;
        }
        return new CrossLink(attributeValue2, attributeValue);
    }

    private CrossLink parseLink(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, "link");
        return new CrossLink(xmlPullParser.getAttributeValue(ns, "type"), xmlPullParser.getAttributeValue(ns, AT_REF));
    }

    private CrossLink parseA(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, "a");
        return new CrossLink(CrossLink.Type.A, xmlPullParser.getAttributeValue(ns, "href"));
    }

    private String parseLastUpdate(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, ns, LAST_UPDATE);
        String readText = readText(xmlPullParser);
        xmlPullParser.require(3, ns, LAST_UPDATE);
        return readText;
    }

    private String parseTitle(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, ns, "title");
        String readText = readText(xmlPullParser);
        xmlPullParser.require(3, ns, "title");
        return readText;
    }

    private Table parseTable(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, "table");
        String attributeValue = xmlPullParser.getAttributeValue(ns, "id");
        String attributeValue2 = xmlPullParser.getAttributeValue(ns, "class");
        if (StringUtil.isNullOrEmpty(attributeValue)) {
            attributeValue = "UniqueTableID" + Integer.toString(this.uniqueTableID);
            this.uniqueTableID++;
        }
        if (StringUtil.isNullOrEmpty(attributeValue2)) {
            attributeValue2 = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        return new Table(attributeValue, attributeValue2);
    }

    private Table readTable(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, "table");
        Table table = new Table(xmlPullParser.getAttributeValue(ns, "id"), xmlPullParser.getAttributeValue(ns, "class"));
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (!name.equals("title") && !name.equals("caption")) {
                    skip(xmlPullParser);
                }
            }
        }
        return table;
    }

    private Figure readFigure(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, ns, FIGURE);
        Figure figure = new Figure();
        figure.id = xmlPullParser.getAttributeValue(ns, "id");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null) {
                    skip(xmlPullParser);
                } else if (name.equals("title")) {
                    figure.title = readSimplePara(xmlPullParser, "title");
                } else if (name.equals("caption")) {
                    figure.caption = readSimplePara(xmlPullParser, "caption");
                } else if (name.equals(GRAPHIC)) {
                    figure.getClass();
                    Figure.Graphic graphic = new Figure.Graphic();
                    graphic.id = xmlPullParser.getAttributeValue(ns, "id");
                    graphic.format = xmlPullParser.getAttributeValue(ns, AT_FORMAT);
                    graphic.extension = xmlPullParser.getAttributeValue(ns, AT_EXTENSION);
                    graphic.href = xmlPullParser.getAttributeValue(ns, AT_FILEREF);
                    if (AT_VAL_THUMB.equals(graphic.format)) {
                        figure.thumbnail = graphic;
                    } else {
                        figure.graphic = graphic;
                    }
                    xmlPullParser.next();
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return figure;
    }

    private String readText(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (xmlPullParser.next() != 4) {
            return null;
        }
        String text = xmlPullParser.getText();
        xmlPullParser.nextTag();
        return text;
    }

    private void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
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
}
