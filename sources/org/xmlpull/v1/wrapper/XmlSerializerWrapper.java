package org.xmlpull.v1.wrapper;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public interface XmlSerializerWrapper extends XmlSerializer {
    public static final String NO_NAMESPACE = "";
    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

    XmlSerializerWrapper attribute(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException;

    XmlSerializerWrapper element(String str, String str2) throws IOException, XmlPullParserException;

    XmlSerializerWrapper element(String str, String str2, String str3) throws IOException, XmlPullParserException;

    XmlSerializerWrapper endTag(String str) throws IOException, IllegalArgumentException, IllegalStateException;

    String escapeAttributeValue(String str) throws IllegalArgumentException;

    String escapeText(String str) throws IllegalArgumentException;

    void event(XmlPullParser xmlPullParser) throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException;

    void fragment(String str) throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException;

    String getCurrentNamespaceForElements();

    String setCurrentNamespaceForElements(String str);

    XmlSerializerWrapper startTag(String str) throws IOException, IllegalArgumentException, IllegalStateException;

    void wiriteStringElement(String str, String str2, String str3) throws XmlPullParserException, IOException, IllegalArgumentException;

    void writeDouble(double d) throws XmlPullParserException, IOException, IllegalArgumentException;

    void writeDoubleElement(String str, String str2, double d) throws XmlPullParserException, IOException, IllegalArgumentException;

    void writeFloat(float f) throws XmlPullParserException, IOException, IllegalArgumentException;

    void writeFloatElement(String str, String str2, float f) throws XmlPullParserException, IOException, IllegalArgumentException;

    void writeInt(int i) throws XmlPullParserException, IOException, IllegalArgumentException;

    void writeIntElement(String str, String str2, int i) throws XmlPullParserException, IOException, IllegalArgumentException;

    void writeString(String str) throws XmlPullParserException, IOException, IllegalArgumentException;
}
