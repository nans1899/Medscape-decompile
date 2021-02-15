package com.webmd.wbmdcmepulse.models.parsers.articles;

import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdcmepulse.models.articles.HtmlList;
import com.webmd.wbmdcmepulse.models.articles.HtmlListItem;
import com.webmd.wbmdcmepulse.models.articles.HtmlListNode;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import com.webmd.wbmdcmepulse.models.articles.HtmlTable;
import com.webmd.wbmdcmepulse.models.articles.HtmlTableRow;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.unbescape.html.HtmlEscape;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class HtmlParser extends ArticleParserBase {
    public List<HtmlListItem> buildListViewString(HtmlObject htmlObject) throws IOException, XmlPullParserException {
        if (htmlObject == null || Extensions.isStringNullOrEmpty(htmlObject.content)) {
            return null;
        }
        return buildListViewStringRecursive(initializeXmlParser(htmlObject.content), htmlObject.htmlType, 0);
    }

    private List<HtmlListItem> buildListViewStringRecursive(XmlPullParser xmlPullParser, String str, int i) throws IOException, XmlPullParserException {
        String str2;
        ArrayList arrayList = new ArrayList();
        String attributeValue = xmlPullParser.getAttributeValue("", Constants.XML_LIST_TYPE_KEY);
        String str3 = (Extensions.isStringNullOrEmpty(attributeValue) || !attributeValue.equals("order")) ? "ul" : "ol";
        xmlPullParser.require(2, (String) null, Constants.XML_LIST);
        int i2 = 1;
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null && name.equals(Constants.XML_LIST_ITEM)) {
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                if (str3.equals("ol")) {
                    sb.append(i2);
                    sb.append(".");
                    str2 = sb.toString();
                } else {
                    sb.append("&#8226;");
                    str2 = sb.toString();
                }
                sb2.append(getInnerXml(xmlPullParser, Constants.XML_LIST_ITEM));
                HtmlListItem htmlListItem = new HtmlListItem();
                htmlListItem.marker = str2;
                htmlListItem.value = sb2.toString();
                htmlListItem.depth = i;
                if (htmlListItem.value.contains("<list")) {
                    String substring = htmlListItem.value.substring(htmlListItem.value.indexOf("<list "), htmlListItem.value.lastIndexOf("</list>") + 7);
                    htmlListItem.value = htmlListItem.value.replace(substring, "");
                    arrayList.add(htmlListItem);
                    arrayList.addAll(buildListViewStringRecursive(initializeXmlParser(substring), "ul", i + 1));
                } else {
                    arrayList.add(htmlListItem);
                }
                i2++;
            }
        }
        return arrayList;
    }

    public static String unescapeHtml(String str) {
        String unescapeHtml = HtmlEscape.unescapeHtml(str);
        return unescapeHtml.contains("]]]>") ? unescapeHtml.replace("]]]>", "] ]]>") : unescapeHtml;
    }

    public static String decodeOpenBracket(String str) {
        return str.contains("&#93;") ? str.replace("&#93;", "]") : str;
    }

    public String parseHtmlTableString(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, Constants.HTML_TYPE_TABLE);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("tbody")) {
                    return getInnerXml(xmlPullParser, "tbody");
                }
                skip(xmlPullParser);
            }
        }
        return null;
    }

    public HtmlTable parseHtmlTable(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        HtmlTable htmlTable = new HtmlTable();
        xmlPullParser.require(2, (String) null, Constants.HTML_TYPE_TABLE);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("tbody")) {
                    htmlTable = parseTableBody(xmlPullParser);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return htmlTable;
    }

    public List<String> parseHtmlList(String str, String str2, String str3) throws IOException, XmlPullParserException {
        XmlPullParser initializeXmlParser = initializeXmlParser(str);
        ArrayList arrayList = new ArrayList();
        initializeXmlParser.require(2, (String) null, str2);
        while (initializeXmlParser.next() != 3) {
            String name = initializeXmlParser.getName();
            if (name != null && name.equals(str3)) {
                arrayList.add(getInnerXmlWithoutHtml(initializeXmlParser, str3));
            }
        }
        return arrayList;
    }

    public ArrayList<ContentValueTypePair> parseHtmlList(String str, String str2) {
        if (str == null) {
            return new ArrayList<>();
        }
        try {
            return traverseHtmlList(0, 1, str2, Jsoup.parse(str).body().childNodes(), new ArrayList());
        } catch (Throwable th) {
            th.printStackTrace();
            return new ArrayList<>();
        }
    }

    private ArrayList<ContentValueTypePair> traverseHtmlList(int i, int i2, String str, List<Node> list, ArrayList<ContentValueTypePair> arrayList) {
        for (Node next : list) {
            if (next.childNodeSize() <= 1) {
                arrayList.add(new ContentValueTypePair(new ListItem(next.outerHtml().replace("<li>", "").replace("</li>", ""), str, i, i2), "li"));
                i2++;
            } else if (((Element) next).tag() != null) {
                StringBuilder sb = new StringBuilder();
                ArrayList<Node> arrayList2 = new ArrayList<>();
                for (Node next2 : next.childNodes()) {
                    if (next2.nodeName().equals("ul") || next2.nodeName().equals("ol")) {
                        arrayList2.add(next2);
                    } else {
                        sb.append(next2.toString());
                    }
                }
                String sb2 = sb.toString();
                if (!StringExtensions.isNullOrEmpty(sb2)) {
                    arrayList.add(new ContentValueTypePair(new ListItem(sb2.replace("<li>", "").replace("</li>", ""), str, i, i2), "li"));
                    i2++;
                }
                for (Node node : arrayList2) {
                    traverseHtmlList(i + 1, 1, node.nodeName(), node.childNodes(), arrayList);
                }
            }
        }
        return arrayList;
    }

    public List<String> parseXmlListWithHtmlContent(String str, String str2, String str3) throws IOException, XmlPullParserException {
        if (!str.startsWith("<list") || str.startsWith("<list-item")) {
            str = "<list>" + str + "</list>";
        }
        XmlPullParser initializeXmlParser = initializeXmlParser(str);
        ArrayList arrayList = new ArrayList();
        initializeXmlParser.require(2, (String) null, str2);
        while (initializeXmlParser.next() != 3) {
            String name = initializeXmlParser.getName();
            if (name != null && name.equals(str3)) {
                arrayList.add(getInnerXml(initializeXmlParser, str3));
            }
        }
        return arrayList;
    }

    public List<String> parseOrderedHtmlList(String str) throws IOException, XmlPullParserException {
        XmlPullParser initializeXmlParser = initializeXmlParser(str);
        ArrayList arrayList = new ArrayList();
        initializeXmlParser.require(2, (String) null, "ol");
        while (initializeXmlParser.next() != 3) {
            String name = initializeXmlParser.getName();
            if (name != null && name.equals("li")) {
                arrayList.add(getInnerXmlWithoutHtml(initializeXmlParser, "li"));
            }
        }
        return arrayList;
    }

    public List<String> parseUnOrderedHtmlList(String str) throws IOException, XmlPullParserException {
        XmlPullParser initializeXmlParser = initializeXmlParser(str);
        ArrayList arrayList = new ArrayList();
        initializeXmlParser.require(2, (String) null, "ul");
        while (initializeXmlParser.next() != 3) {
            String name = initializeXmlParser.getName();
            if (name != null && name.equals("li")) {
                arrayList.add(getInnerXmlWithoutHtml(initializeXmlParser, "li"));
            }
        }
        return arrayList;
    }

    public HtmlList parseOrderedXmlHtmlList(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, Constants.XML_LIST);
        return parseXmlList(xmlPullParser, Constants.XML_ORDERED_LIST);
    }

    public HtmlList parseUnOrderedHtmlList(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, (String) null, Constants.XML_LIST);
        return parseXmlList(xmlPullParser, Constants.XML_UNORDERED_LIST);
    }

    private HtmlList parseXmlList(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        HtmlList htmlList = new HtmlList();
        if (str.equals(Constants.XML_UNORDERED_LIST)) {
            htmlList.setListType("ul");
        }
        if (str.equals(Constants.XML_ORDERED_LIST)) {
            htmlList.setListType("ol");
        }
        xmlPullParser.require(2, (String) null, Constants.XML_LIST);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals(Constants.XML_LIST)) {
                    HtmlListNode htmlListNode = new HtmlListNode();
                    htmlListNode.type = "li";
                    htmlListNode.value = getInnerXml(xmlPullParser, Constants.XML_LIST_ITEM);
                    htmlList.addListItem(htmlListNode);
                } else if (name.equals(Constants.XML_LIST_ITEM)) {
                    HtmlListNode htmlListNode2 = new HtmlListNode();
                    htmlListNode2.type = "li";
                    htmlListNode2.value = getInnerXml(xmlPullParser, Constants.XML_LIST_ITEM);
                    htmlList.addListItem(htmlListNode2);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return htmlList;
    }

    private HtmlTable parseTableBody(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        HtmlTable htmlTable = new HtmlTable();
        xmlPullParser.require(2, (String) null, "tbody");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("tr")) {
                    htmlTable.tableRows.add(parseTableRow(xmlPullParser, htmlTable.tableRows.size()));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return htmlTable;
    }

    private HtmlTableRow parseTableRow(XmlPullParser xmlPullParser, int i) throws IOException, XmlPullParserException {
        HtmlTableRow htmlTableRow = null;
        xmlPullParser.require(2, (String) null, "tr");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals(HtmlTableRow.ROW_TYPE_TABLE_HEADER)) {
                    if (htmlTableRow == null) {
                        htmlTableRow = new HtmlTableRow(HtmlTableRow.ROW_NUMBER_HEADER, HtmlTableRow.ROW_TYPE_TABLE_HEADER);
                    }
                    htmlTableRow.addRowItem(getInnerXml(xmlPullParser, HtmlTableRow.ROW_TYPE_TABLE_HEADER));
                } else if (name.equals(HtmlTableRow.Row_TYPE_TABLE_DATA)) {
                    if (htmlTableRow == null) {
                        htmlTableRow = new HtmlTableRow(i, HtmlTableRow.Row_TYPE_TABLE_DATA);
                    }
                    htmlTableRow.addRowItem(getInnerXml(xmlPullParser, HtmlTableRow.Row_TYPE_TABLE_DATA));
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return htmlTableRow;
    }
}
