package com.webmd.wbmdcmepulse.models.parsers.articles;

import android.util.Xml;
import com.webmd.wbmdcmepulse.models.articles.Graphic;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class GraphicParser {
    private static final String CONTENT_TYPE_XML_ATTRIBUTE = "content-type";
    private static final String FIG_XML_TAG = "fig";
    private static final String GRAPHIC_XML_TAG = "graphic";
    private static final String LABEL_XML_TAG = "label";
    private static final String LINK_XML_ATTRIBUTE = "xlink:href";
    private static final String OBJECT_ID_XML_TAG = "object-id";
    public static final String XML_ATRIBUTE_VALUE_HEIGHT = "height";
    public static final String XML_ATTRIBUTE_VALUE_ALT_TEXT = "alt-text";
    public static final String XML_ATTRIBUTE_VALUE_WIDTH = "width";
    private final String DEFAULT_NAMESPACE = null;
    private final String XML;

    public GraphicParser(String str) {
        this.XML = str;
    }

    public Graphic Parse() throws XmlPullParserException, IOException {
        return parseFig(initializeXmlParser(this.XML));
    }

    private Graphic parseFig(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        Graphic graphic = new Graphic();
        xmlPullParser.require(2, this.DEFAULT_NAMESPACE, FIG_XML_TAG);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals(GRAPHIC_XML_TAG)) {
                    graphic.url = xmlPullParser.getAttributeValue(this.DEFAULT_NAMESPACE, LINK_XML_ATTRIBUTE);
                    graphic.url = graphic.url.replace("/webmd/professional_assets/medscape/images/", "https://img.medscape.com/");
                    graphic = parseGraphic(xmlPullParser, graphic);
                } else if (name.equals("label")) {
                    graphic.label = getInnerXmlWithoutHtml(xmlPullParser, "label");
                } else if (name.equals(OBJECT_ID_XML_TAG)) {
                    String attributeValue = xmlPullParser.getAttributeValue("", CONTENT_TYPE_XML_ATTRIBUTE);
                    if (attributeValue.equals(XML_ATRIBUTE_VALUE_HEIGHT)) {
                        graphic.height = getInnerXmlWithoutHtml(xmlPullParser, OBJECT_ID_XML_TAG);
                    } else if (attributeValue.equals(XML_ATTRIBUTE_VALUE_WIDTH)) {
                        graphic.width = getInnerXmlWithoutHtml(xmlPullParser, OBJECT_ID_XML_TAG);
                    } else {
                        skip(xmlPullParser);
                    }
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return graphic;
    }

    private Graphic parseGraphic(XmlPullParser xmlPullParser, Graphic graphic) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, this.DEFAULT_NAMESPACE, GRAPHIC_XML_TAG);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null && name.equals(OBJECT_ID_XML_TAG)) {
                String attributeValue = xmlPullParser.getAttributeValue(this.DEFAULT_NAMESPACE, CONTENT_TYPE_XML_ATTRIBUTE);
                if (attributeValue == null) {
                    skip(xmlPullParser);
                } else if (attributeValue.equals(XML_ATTRIBUTE_VALUE_ALT_TEXT)) {
                    graphic.altText = getInnerXmlWithoutHtml(xmlPullParser, OBJECT_ID_XML_TAG);
                } else if (attributeValue.equals(XML_ATTRIBUTE_VALUE_WIDTH)) {
                    graphic.width = getInnerXmlWithoutHtml(xmlPullParser, OBJECT_ID_XML_TAG);
                } else if (attributeValue.equals(XML_ATRIBUTE_VALUE_HEIGHT)) {
                    graphic.height = getInnerXmlWithoutHtml(xmlPullParser, OBJECT_ID_XML_TAG);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return graphic;
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
    public String getInnerXmlWithoutHtml(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        StringBuilder sb = new StringBuilder();
        xmlPullParser.require(2, (String) null, str);
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next != 2) {
                if (next != 3) {
                    if (next == 4) {
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
}
