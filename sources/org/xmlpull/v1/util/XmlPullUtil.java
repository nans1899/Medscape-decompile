package org.xmlpull.v1.util;

import com.facebook.internal.ServerProtocol;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class XmlPullUtil {
    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

    private static boolean isS(char c) {
        return c == ' ' || c == 10 || c == 13 || c == 9;
    }

    private XmlPullUtil() {
    }

    public static String getAttributeValue(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("", str);
    }

    public static String getPITarget(XmlPullParser xmlPullParser) throws IllegalStateException {
        try {
            int eventType = xmlPullParser.getEventType();
            if (eventType == 8) {
                String text = xmlPullParser.getText();
                for (int i = 0; i < text.length(); i++) {
                    if (isS(text.charAt(i))) {
                        return text.substring(0, i);
                    }
                }
                return text;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("parser must be on processing instruction and not ");
            stringBuffer.append(XmlPullParser.TYPES[eventType]);
            stringBuffer.append(xmlPullParser.getPositionDescription());
            throw new IllegalStateException(stringBuffer.toString());
        } catch (XmlPullParserException e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("could not determine parser state: ");
            stringBuffer2.append(e);
            stringBuffer2.append(xmlPullParser.getPositionDescription());
            throw new IllegalStateException(stringBuffer2.toString());
        }
    }

    public static String getPIData(XmlPullParser xmlPullParser) throws IllegalStateException {
        try {
            int eventType = xmlPullParser.getEventType();
            if (eventType == 8) {
                String text = xmlPullParser.getText();
                int i = -1;
                for (int i2 = 0; i2 < text.length(); i2++) {
                    if (isS(text.charAt(i2))) {
                        i = i2;
                    } else if (i > 0) {
                        return text.substring(i2);
                    }
                }
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("parser must be on processing instruction and not ");
            stringBuffer.append(XmlPullParser.TYPES[eventType]);
            stringBuffer.append(xmlPullParser.getPositionDescription());
            throw new IllegalStateException(stringBuffer.toString());
        } catch (XmlPullParserException e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("could not determine parser state: ");
            stringBuffer2.append(e);
            stringBuffer2.append(xmlPullParser.getPositionDescription());
            throw new IllegalStateException(stringBuffer2.toString());
        }
    }

    public static void skipSubTree(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, (String) null, (String) null);
        int i = 1;
        while (i > 0) {
            int next = xmlPullParser.next();
            if (next == 3) {
                i--;
            } else if (next == 2) {
                i++;
            }
        }
    }

    public static void nextStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.nextTag() != 2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("expected START_TAG and not ");
            stringBuffer.append(xmlPullParser.getPositionDescription());
            throw new XmlPullParserException(stringBuffer.toString());
        }
    }

    public static void nextStartTag(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.nextTag();
        xmlPullParser.require(2, (String) null, str);
    }

    public static void nextStartTag(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        xmlPullParser.nextTag();
        xmlPullParser.require(2, str, str2);
    }

    public static void nextEndTag(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        xmlPullParser.nextTag();
        xmlPullParser.require(3, str, str2);
    }

    public static String nextText(XmlPullParser xmlPullParser, String str, String str2) throws IOException, XmlPullParserException {
        if (str2 != null) {
            xmlPullParser.require(2, str, str2);
            return xmlPullParser.nextText();
        }
        throw new XmlPullParserException("name for element can not be null");
    }

    public static String getRequiredAttributeValue(XmlPullParser xmlPullParser, String str, String str2) throws IOException, XmlPullParserException {
        String attributeValue = xmlPullParser.getAttributeValue(str, str2);
        if (attributeValue != null) {
            return attributeValue;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("required attribute ");
        stringBuffer.append(str2);
        stringBuffer.append(" is not present");
        throw new XmlPullParserException(stringBuffer.toString());
    }

    public static void nextEndTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.nextTag() != 3) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("expected END_TAG and not");
            stringBuffer.append(xmlPullParser.getPositionDescription());
            throw new XmlPullParserException(stringBuffer.toString());
        }
    }

    public static boolean matches(XmlPullParser xmlPullParser, int i, String str, String str2) throws XmlPullParserException {
        return i == xmlPullParser.getEventType() && (str == null || str.equals(xmlPullParser.getNamespace())) && (str2 == null || str2.equals(xmlPullParser.getName()));
    }

    public static void writeSimpleElement(XmlSerializer xmlSerializer, String str, String str2, String str3) throws IOException, XmlPullParserException {
        if (str2 != null) {
            xmlSerializer.startTag(str, str2);
            if (str3 == null) {
                xmlSerializer.attribute("http://www.w3.org/2001/XMLSchema-instance", "nil", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            } else {
                xmlSerializer.text(str3);
            }
            xmlSerializer.endTag(str, str2);
            return;
        }
        throw new XmlPullParserException("name for element can not be null");
    }
}
