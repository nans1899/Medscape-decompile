package com.wbmd.qxcalculator.util.legacy;

import android.util.Xml;
import com.wbmd.qxcalculator.util.legacy.ContentItem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlSerializer;

public class ContentParser {
    public static final String CALCULATOR = "calculator";
    public static final String CAMPAIGN = "campaign";
    public static final String CUSTOM = "custom";
    public static final String GROUP = "group";
    public static final String GUIDE = "guide";
    public static final String ID = "id";
    public static final String ITEM = "item";
    public static final String PARENT_GROUPS = "parent_groups";
    public static final String PDF = "pdf";
    public static final String REGION = "region";
    public static final String REGIONS = "regions";
    public static final String RESOURCE = "resource";
    public static final String SPONSOR = "sponsor";
    public static final String SUBTITLE = "subtitle";
    public static final String TAGS = "tags";
    public static final String TITLE = "title";
    public static final String TRACKER = "tracker";
    public static final String TYPE = "type";
    public static final String URL_EXTERNAL = "urlExternal";
    public static final String URL_INTERNAL = "urlInternal";
    public static final String XML = "xml";

    public static List<ContentNode> parse(File file) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
        return parse((InputStream) new FileInputStream(file));
    }

    public static List<ContentNode> parse(String str) throws ParserConfigurationException, SAXException, IOException {
        return parse((InputStream) new ByteArrayInputStream(str.getBytes()));
    }

    public static List<ContentNode> parse(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        ContentHandler contentHandler = new ContentHandler();
        newInstance.newSAXParser().parse(inputStream, contentHandler);
        return contentHandler.getList();
    }

    public static void serialize(File file, List<ContentNode> list) throws IllegalArgumentException, IllegalStateException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(serialize(list).getBytes());
        fileOutputStream.close();
    }

    public static String serialize(List<ContentNode> list) throws IllegalArgumentException, IllegalStateException, IOException {
        XmlSerializer newSerializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();
        newSerializer.setOutput(stringWriter);
        newSerializer.startDocument("UTF-8", true);
        newSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        newSerializer.startTag("", "xml");
        serialize(newSerializer, list);
        newSerializer.endTag("", "xml");
        newSerializer.endDocument();
        return stringWriter.toString();
    }

    private static void serialize(XmlSerializer xmlSerializer, List<ContentNode> list) throws IllegalArgumentException, IllegalStateException, IOException {
        if (list != null) {
            for (ContentNode next : list) {
                if (next instanceof ContentGroup) {
                    serialize(xmlSerializer, (ContentGroup) next);
                } else {
                    serialize(xmlSerializer, (ContentItem) next);
                }
            }
        }
    }

    private static void serialize(XmlSerializer xmlSerializer, ContentGroup contentGroup) throws IllegalArgumentException, IllegalStateException, IOException {
        xmlSerializer.startTag("", GROUP);
        Integer identifier = contentGroup.getIdentifier();
        String title = contentGroup.getTitle();
        List<ContentNode> children = contentGroup.getChildren();
        if (identifier != null) {
            xmlSerializer.startTag("", "id");
            xmlSerializer.text(String.valueOf(identifier.intValue()));
            xmlSerializer.endTag("", "id");
        }
        if (title != null) {
            xmlSerializer.startTag("", "title");
            xmlSerializer.text(title);
            xmlSerializer.endTag("", "title");
        }
        if (children != null) {
            serialize(xmlSerializer, children);
        }
        xmlSerializer.endTag("", GROUP);
    }

    private static void serialize(XmlSerializer xmlSerializer, ContentItem contentItem) throws IllegalArgumentException, IllegalStateException, IOException {
        xmlSerializer.startTag("", ITEM);
        String title = contentItem.getTitle();
        String subtitle = contentItem.getSubtitle();
        ContentItem.ItemType type = contentItem.getType();
        String resource = contentItem.getResource();
        String sponsor = contentItem.getSponsor();
        String campaign = contentItem.getCampaign();
        String tracker = contentItem.getTracker();
        String tags = contentItem.getTags();
        String parentGroups = contentItem.getParentGroups();
        if (title != null) {
            xmlSerializer.startTag("", "title");
            xmlSerializer.text(title);
            xmlSerializer.endTag("", "title");
        }
        if (subtitle != null) {
            xmlSerializer.startTag("", "subtitle");
            xmlSerializer.text(subtitle);
            xmlSerializer.endTag("", "subtitle");
        }
        if (sponsor != null) {
            xmlSerializer.startTag("", SPONSOR);
            xmlSerializer.text(sponsor);
            xmlSerializer.endTag("", SPONSOR);
        }
        if (campaign != null) {
            xmlSerializer.startTag("", "campaign");
            xmlSerializer.text(campaign);
            xmlSerializer.endTag("", "campaign");
        }
        if (tracker != null) {
            xmlSerializer.startTag("", "tracker");
            xmlSerializer.text(tracker);
            xmlSerializer.endTag("", "tracker");
        }
        if (type != ContentItem.ItemType.NONE) {
            xmlSerializer.startTag("", "type");
            if (type == ContentItem.ItemType.CUSTOM) {
                xmlSerializer.text("custom");
            } else if (type == ContentItem.ItemType.CALCULATOR) {
                xmlSerializer.text(CALCULATOR);
            } else if (type == ContentItem.ItemType.GUIDE) {
                xmlSerializer.text(GUIDE);
            } else if (type == ContentItem.ItemType.PDF) {
                xmlSerializer.text(PDF);
            } else if (type == ContentItem.ItemType.URL_INTERNAL) {
                xmlSerializer.text(URL_INTERNAL);
            } else if (type == ContentItem.ItemType.URL_EXTERNAL) {
                xmlSerializer.text(URL_EXTERNAL);
            }
            xmlSerializer.endTag("", "type");
        }
        if (resource != null) {
            xmlSerializer.startTag("", RESOURCE);
            xmlSerializer.text(resource);
            xmlSerializer.endTag("", RESOURCE);
        }
        if (tags != null) {
            xmlSerializer.startTag("", TAGS);
            xmlSerializer.text(tags);
            xmlSerializer.endTag("", TAGS);
        }
        if (parentGroups != null) {
            xmlSerializer.startTag("", PARENT_GROUPS);
            xmlSerializer.text(parentGroups);
            xmlSerializer.endTag("", PARENT_GROUPS);
        }
        xmlSerializer.endTag("", ITEM);
    }

    private static class ContentHandler extends DefaultHandler {
        private StringBuilder mBuilder;
        private List<ContentNode> mContentNodes;
        private ContentGroup mCurrentContentGroup;
        private ContentItem mCurrentContentItem;

        private ContentHandler() {
        }

        public List<ContentNode> getList() {
            return this.mContentNodes;
        }

        public void characters(char[] cArr, int i, int i2) throws SAXException {
            super.characters(cArr, i, i2);
            this.mBuilder.append(cArr, i, i2);
        }

        public void startDocument() throws SAXException {
            super.startDocument();
            this.mBuilder = new StringBuilder();
            this.mContentNodes = new ArrayList();
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            super.startElement(str, str2, str3, attributes);
            this.mBuilder.setLength(0);
            if (str2.equalsIgnoreCase(ContentParser.GROUP)) {
                ContentGroup contentGroup = new ContentGroup();
                ContentGroup contentGroup2 = this.mCurrentContentGroup;
                if (contentGroup2 == null) {
                    this.mContentNodes.add(contentGroup);
                } else {
                    contentGroup2.addChild(contentGroup);
                }
                this.mCurrentContentGroup = contentGroup;
            } else if (str2.equalsIgnoreCase(ContentParser.ITEM)) {
                ContentItem contentItem = new ContentItem();
                ContentGroup contentGroup3 = this.mCurrentContentGroup;
                if (contentGroup3 == null) {
                    this.mContentNodes.add(contentItem);
                } else {
                    contentGroup3.addChild(contentItem);
                }
                this.mCurrentContentItem = contentItem;
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            super.endElement(str, str2, str3);
            String sb = this.mBuilder.toString();
            if (str2.equalsIgnoreCase("title")) {
                ContentItem contentItem = this.mCurrentContentItem;
                if (contentItem != null) {
                    contentItem.setTitle(sb);
                } else {
                    this.mCurrentContentGroup.setTitle(sb);
                }
            } else if (str2.equalsIgnoreCase("subtitle")) {
                this.mCurrentContentItem.setSubtitle(sb);
            } else if (str2.equalsIgnoreCase("type")) {
                if (sb.equalsIgnoreCase("custom")) {
                    this.mCurrentContentItem.setType(ContentItem.ItemType.CUSTOM);
                } else if (sb.equalsIgnoreCase(ContentParser.CALCULATOR)) {
                    this.mCurrentContentItem.setType(ContentItem.ItemType.CALCULATOR);
                } else if (sb.equalsIgnoreCase(ContentParser.GUIDE)) {
                    this.mCurrentContentItem.setType(ContentItem.ItemType.GUIDE);
                } else if (sb.equalsIgnoreCase(ContentParser.PDF)) {
                    this.mCurrentContentItem.setType(ContentItem.ItemType.PDF);
                } else if (sb.equalsIgnoreCase(ContentParser.URL_INTERNAL)) {
                    this.mCurrentContentItem.setType(ContentItem.ItemType.URL_INTERNAL);
                } else if (sb.equalsIgnoreCase(ContentParser.URL_EXTERNAL)) {
                    this.mCurrentContentItem.setType(ContentItem.ItemType.URL_EXTERNAL);
                } else {
                    this.mCurrentContentItem.setType(ContentItem.ItemType.NONE);
                }
            } else if (str2.equalsIgnoreCase(ContentParser.RESOURCE)) {
                this.mCurrentContentItem.setResource(sb);
            } else if (str2.equalsIgnoreCase(ContentParser.SPONSOR)) {
                this.mCurrentContentItem.setSponsor(sb);
            } else if (str2.equalsIgnoreCase("campaign")) {
                this.mCurrentContentItem.setCampaign(sb);
            } else if (str2.equalsIgnoreCase("tracker")) {
                this.mCurrentContentItem.setTracker(sb);
            } else if (str2.equalsIgnoreCase(ContentParser.TAGS)) {
                this.mCurrentContentItem.setTags(sb);
            } else if (str2.equalsIgnoreCase(ContentParser.PARENT_GROUPS)) {
                this.mCurrentContentItem.setParentGroups(sb);
            } else if (str2.equalsIgnoreCase(ContentParser.REGION)) {
                ContentItem contentItem2 = this.mCurrentContentItem;
                if (contentItem2 != null) {
                    contentItem2.addRegion(sb);
                } else {
                    this.mCurrentContentGroup.addRegion(sb);
                }
            } else if (str2.equalsIgnoreCase("id")) {
                this.mCurrentContentGroup.setIdentifier(Integer.valueOf(sb));
            } else if (str2.equalsIgnoreCase(ContentParser.ITEM)) {
                this.mCurrentContentItem = null;
            } else if (str2.equalsIgnoreCase(ContentParser.GROUP)) {
                this.mCurrentContentGroup = (ContentGroup) this.mCurrentContentGroup.getParent();
            }
        }
    }
}
